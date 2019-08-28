package hzkj.cc.yw.ui.fragment;

import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.stateful.StateFulLayout.RefreshListenner;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ApplyBuyAdapter;
import hzkj.cc.yw.bean.ApplyBuyInfo;
import hzkj.cc.yw.contract.ApplyBuySearchContract;
import hzkj.cc.yw.presenter.ApplyBuySearchPresenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.ui.activity.ApplyBuyReviewActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplyBuySearchFragment extends
    BaseFragment<ApplyBuySearchContract.Presenter> implements ApplyBuySearchContract.View {

  @BindView(R.id.layout)
  StateFulLayout stateLayout;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  boolean isSearchingId;
  ApplyBuyAdapter adapter;
  List<ApplyBuyInfo> datas;
  int id;
  String startTime;
  String endTime;
  int project;
  int status;

  @Override
  void doOnVisible() {
  }

  @Override
  ApplyBuySearchContract.Presenter createPresenter() {
    return new ApplyBuySearchPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_apply_buy_search;
  }

  @Override
  public void initView() {
    datas = new ArrayList<>();
    adapter = new ApplyBuyAdapter(datas, getActivity());
    recyclerView.init(adapter);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setRefreshEnable(false);
    stateLayout.init(new RefreshListenner() {
      @Override
      public void refresh() {
//        if (isSearchingId) {
//          getPresenter().startGetDataById(id);
//        } else {
          getPresenter().startGetDataByTime(startTime, endTime, project, status);
//        }
      }
    }, recyclerView);
    recyclerView.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), ApplyBuyReviewActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position)
                  .getBuyId() + "");
            }}, false, false, -1);
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.NORMAL) {
      stateLayout.showState(StateFulLayout.CONTENT);
      datas.clear();
      datas.addAll((Collection<? extends ApplyBuyInfo>) object);
      recyclerView.refreshComplete(true);
    } else {
      stateLayout.showState(StateFulLayout.EMPTY);
    }
  }


  @Override
  public void updateCheckSuccess(Object object) {
  }

//  @Override
//  public void notifyGetDataById(int id) {
//    this.id = id;
//    stateLayout.showState(StateFulLayout.LOADING);
//    isSearchingId = true;
//    getPresenter().startGetDataById(this.id);
//  }

  @Override
  public void notifyGetDataByTime(String startTime, String endTime, int project,
      int status) {
    stateLayout.showState(StateFulLayout.LOADING);
    isSearchingId = false;
    this.startTime = startTime;
    this.endTime = endTime;
    this.project = project;
    this.status = status;
    getPresenter().startGetDataByTime(startTime, endTime, project, status);
  }
}
