package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.widget.RelativeLayout;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.RefreshListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.ccrecyclerview.RecyclerLayout.StateLayoutRefreshListenner;
import hzkj.cc.library.SmallLoading;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ClickItemListenner;
import hzkj.cc.yw.adapter.DeliverSingleAdapter;
import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Event;
import hzkj.cc.yw.contract.DeliverSingleEachContract;
import hzkj.cc.yw.contract.DeliverSingleEachContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.DeliverSingleEachPresenter;
import hzkj.cc.yw.ui.activity.DeliverSingleDetailActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DeliverSingleEachFragment extends
    BaseFragment<DeliverSingleEachContract.Presenter> implements DeliverSingleEachContract.View {

  private SmallLoading smallLoading;

  @Override
  void doOnVisible() {
    relativeLayout.onResume();
  }

  @Override
  Presenter createPresenter() {
    return new DeliverSingleEachPresenter();
  }

  @BindView(R.id.layout)
  RelativeLayout layout;
  @BindView(R.id.recyclerView)
  RecyclerLayout relativeLayout;
  DeliverSingleAdapter adapter;
  List<DeliverInfo> datas;
  int pageNum = 1;

  public static DeliverSingleEachFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    DeliverSingleEachFragment fragment = new DeliverSingleEachFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Subscribe(sticky = true)
  public void getEvent(Event event) {
    if (event.getStatus() == 9) {
      relativeLayout.onResume();
    }
  }


  @Override
  public void getData() {
    getPresenter()
        .startGetSingleDeliverInfos(getArguments().getInt("type"), pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_deliver_single_each;
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Override
  public void initView() {
    EventBus.getDefault().register(this);
    datas = new ArrayList<>();
    adapter = new DeliverSingleAdapter(getActivity(), datas);
    relativeLayout.init(adapter,true);
    relativeLayout.setRefreshListenner(new RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    relativeLayout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    relativeLayout.setStateLayoutRefreshListenner(new StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    relativeLayout.setClickItemListenner(new hzkj.cc.ccrecyclerview.ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), DeliverSingleDetailActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position).getLogisticsId() + "");
            }}, false, false, 1);
      }
    });
    adapter.setListenner(new ClickItemListenner() {
      @Override
      public void click(final int p) {
        new CcDialog(getActivity(), CcDialog.CHOOSE_DIALOG).setMessage("是否收货")
            .setCancelListener(new CancelListener() {
              @Override
              public void onClick(CcDialog dialog) {
                smallLoading.show();
                getPresenter().startGetDeliver(datas.get(p)
                    .getLogisticsId());
              }
            })
            .showDialog();
      }

      @Override
      public void click(int p, int tag) {
      }
    });
    smallLoading = new SmallLoading(getActivity());
    smallLoading.init(layout);
  }


  @Override
  public void initData() {
//    getData();
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    switch (((Integer) object)) {
      case BasePresenter.REFRESHERROR: {
        relativeLayout.refreshComplete(false);
        break;
      }
      case BasePresenter.LOADMOREERROR: {
        relativeLayout.loadComplete(false,false);
        break;
      }
    }
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case BasePresenter
          .REFRESHSUCCESS: {
        datas.clear();
        datas.addAll((Collection<? extends DeliverInfo>) object);
        relativeLayout.refreshComplete(true);
        break;
      }
      case BasePresenter
          .LOADMORESUCCESS: {
        datas.addAll((Collection<? extends DeliverInfo>) object);
        relativeLayout.loadComplete(true,true);
        break;
      }
      case BasePresenter
          .LOADMOREEMPTY: {
        relativeLayout.loadComplete(false,true);
        break;
      }
      case BasePresenter
          .UPDATESUCCESS: {
        smallLoading.disappear();
        new CcDialog(getActivity(), CcDialog.SUCCESS_DIALOG).setMessage("收货成功").setCancelListener(
            new CancelListener() {
              @Override
              public void onClick(CcDialog dialog) {
                relativeLayout.onResume();
                pageNum = 1;
                getPresenter()
                    .startGetSingleDeliverInfos(getArguments().getInt("type"), pageNum);
              }
            }).showDialog();
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
