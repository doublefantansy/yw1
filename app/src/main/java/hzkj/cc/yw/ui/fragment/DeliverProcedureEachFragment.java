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
import hzkj.cc.yw.adapter.DeliverProcedureAdapter;
import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.contract.DeliverProcedureEachContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.DeliverProcedureEachPresenter;
import hzkj.cc.yw.ui.activity.DeliverProcedureDetailActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeliverProcedureEachFragment extends
    BaseFragment<DeliverProcedureEachContract.Presenter> implements
    DeliverProcedureEachContract.View {

  @BindView(R.id.recyclerLayout)
  RecyclerLayout recyclerLayout;
  @BindView(R.id.relativeLayout)
  RelativeLayout relativeLayout;
  DeliverProcedureAdapter adapter;
  List<DeliverInfo> datas = new ArrayList<>();
  int pageNum = 1;
  SmallLoading smallLoading;

  public void setPageNum(int pageNum) {
    this.pageNum = pageNum;
  }

  @Override
  void doOnVisible() {
    recyclerLayout.onResume();
  }

  @Override
  DeliverProcedureEachContract.Presenter createPresenter() {
    return new DeliverProcedureEachPresenter();
  }

  @Override
  public void clickBottom() {
    recyclerLayout.onResume();
  }

  public static DeliverProcedureEachFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    DeliverProcedureEachFragment fragment = new DeliverProcedureEachFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
    getPresenter().startGetProcedureDeliverInfo(getArguments().getInt("type"), pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_deliver_procedure_each;
  }

  @Override
  public void initView() {
    adapter = new DeliverProcedureAdapter(getActivity(), datas);
    recyclerLayout.init(adapter, true);
    smallLoading = new SmallLoading(getActivity());
    smallLoading.init(relativeLayout);
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
    recyclerLayout.setClickItemListenner(new hzkj.cc.ccrecyclerview.ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.fragmentJumpToActivity(DeliverProcedureEachFragment.this,
            DeliverProcedureDetailActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position)
                  .getLogisticsId() + "");
            }}, true, 111);
      }
    });
    recyclerLayout.setRefreshListenner(new RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    recyclerLayout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    recyclerLayout.setStateLayoutRefreshListenner(new StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
  }
//  public void resume() {
//    recyclerLayout.onResume();
//  }

  @Override
  public void initData() {
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    switch (((Integer) object)) {
      case BasePresenter.REFRESHERROR: {
        recyclerLayout.refreshComplete(false);
        break;
      }
      case BasePresenter.LOADMOREERROR: {
        recyclerLayout.loadComplete(false, false);
        break;
      }
    }
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case BasePresenter
          .UPDATESUCCESS: {
        smallLoading.disappear();
        new CcDialog(getActivity(), CcDialog.SUCCESS_DIALOG)
            .setCancelListener(new CancelListener() {
              @Override
              public void onClick(CcDialog dialog) {
                recyclerLayout.onResume();
              }
            })
            .setMessage("收货成功")
            .showDialog();
        break;
      }
      case BasePresenter
          .LOADMORESUCCESS: {
        datas.addAll((Collection<? extends DeliverInfo>) object);
        recyclerLayout.loadComplete(false, true);
        break;
      }
      case BasePresenter
          .LOADMOREEMPTY: {
        recyclerLayout.loadComplete(true, true);
        break;
      }
      case BasePresenter
          .REFRESHSUCCESS: {
        datas.clear();
        datas.addAll((Collection<? extends DeliverInfo>) object);
        recyclerLayout.refreshComplete(true);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
