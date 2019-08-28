package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ApplyBuyDetailRecordAdapter;
import hzkj.cc.yw.bean.ApplyBuyInfoDetail;
import hzkj.cc.yw.contract.ApplyBuyDetailContract;
import hzkj.cc.yw.presenter.ApplyBuyDetailPresenter;

public class ApplyBuyDetailFragment extends
    BaseFragment<ApplyBuyDetailContract.Presenter> implements ApplyBuyDetailContract.View {

  int id;
  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  ApplyBuyDetailRecordAdapter adapter;
  List<ApplyBuyInfoDetail> details = new ArrayList<>();

  public static ApplyBuyDetailFragment newInstance(int someInt) {
    ApplyBuyDetailFragment fragment = new ApplyBuyDetailFragment();
    Bundle args = new Bundle();
    args.putInt("int", someInt);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  void doOnVisible() {
  }

  @Override
  ApplyBuyDetailContract.Presenter createPresenter() {
    return new ApplyBuyDetailPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_apply_buy_detail;
  }

  @Override
  public void initView() {
    id = getArguments().getInt("int");
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetApplyBuyInfos(id);
      }
    }, recyclerView);
    stateFulLayout.showState(StateFulLayout.LOADING);
    adapter = new ApplyBuyDetailRecordAdapter(details, getActivity());
    recyclerView.init(adapter);
    recyclerView.setRefreshEnable(false);
    recyclerView.setLoadMoreEnable(false);
  }

  @Override
  public void initData() {
    getPresenter().startGetApplyBuyInfos(id);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    stateFulLayout.showState(StateFulLayout.CONTENT);
    details.addAll((Collection<? extends ApplyBuyInfoDetail>) object);
    adapter.notifyDataSetChanged();
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    stateFulLayout.showState(StateFulLayout.NETERROR);
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
