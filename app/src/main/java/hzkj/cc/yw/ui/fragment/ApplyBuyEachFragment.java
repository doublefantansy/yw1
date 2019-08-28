package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.RefreshListenner;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.ccrecyclerview.RecyclerLayout.StateLayoutRefreshListenner;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ApplyBuyAdapter;
import hzkj.cc.yw.bean.ApplyBuyInfo;
import hzkj.cc.yw.contract.ApplyBuyEachContract;
import hzkj.cc.yw.presenter.ApplyBuyEachPresenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.ui.activity.ApplyBuyReviewActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplyBuyEachFragment extends BaseFragment<ApplyBuyEachContract.Presenter> implements
    ApplyBuyEachContract.View {

  //  @BindView(R.id.recyclerView)
//  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  @BindView(R.id.recyclerLayout)
  RecyclerLayout layout;
  ApplyBuyAdapter applyBuyAdapter;
  List<ApplyBuyInfo> datas;
  int pageNum;
  //  boolean isfirst = true;
  int type;

  public static ApplyBuyEachFragment getInstance(int isUse) {
    ApplyBuyEachFragment fragment = new ApplyBuyEachFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("type", isUse);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  void doOnVisible() {
  }

  @Override
  ApplyBuyEachContract.Presenter createPresenter() {
    return new ApplyBuyEachPresenter();
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_apply_buy_each;
  }


  @Override
  public void initView() {
    datas = new ArrayList<>();
    applyBuyAdapter = new ApplyBuyAdapter(datas, getActivity());
    layout.init(applyBuyAdapter,true);
    layout.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), ApplyBuyReviewActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position)
                  .getBuyId() + "");
            }}, false, false, -1);
      }
    });
    layout.setStateLayoutRefreshListenner(new StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    layout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    layout.setRefreshListenner(new RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
  }

  @Override
  public void initData() {
    pageNum = 1;
    type = (Integer) getArguments().get("type");
  }

  @Override
  public void getData() {
    getPresenter().startGetApplyBuyInfos(type, pageNum);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    int tag = (int) object;
    if (tag == BasePresenter.REFRESHERROR) {
      layout.refreshComplete(false);
    } else if (tag == BasePresenter.LOADMOREERROR) {
      layout.loadComplete(false,false);
    }
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case BasePresenter
          .LOADMORESUCCESS: {
        datas.addAll((Collection<? extends ApplyBuyInfo>) object);
        layout.loadComplete(false,true);
        break;
      }
      case BasePresenter
          .LOADMOREEMPTY: {
        layout.loadComplete(true,true);
        break;
      }
      case BasePresenter
          .REFRESHSUCCESS: {
        datas.clear();
        datas.addAll((Collection<? extends ApplyBuyInfo>) object);
        layout.refreshComplete(true);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
