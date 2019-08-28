package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ProcedureAdapter;
import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.contract.GoToRepairReviewContract;
import hzkj.cc.yw.presenter.GoToRepairReviewPresenter;

public class GoToRepairReviewFragment extends
    BaseFragment<GoToRepairReviewContract.Presenter> implements GoToRepairReviewContract.View {

  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  ProcedureAdapter adapter;
  List<Procedure> datas = new ArrayList<>();
  int id;

  @Override
  void doOnVisible() {
  }

  @Override
  GoToRepairReviewContract.Presenter createPresenter() {
    return new GoToRepairReviewPresenter();
  }

  public static GoToRepairReviewFragment newInstance(int aInt) {
    GoToRepairReviewFragment fragment = new GoToRepairReviewFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("int", aInt);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_go_to_repair_review;
  }

  @Override
  public void initView() {
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetReviews(id);
      }
    }, recyclerView);
    adapter = new ProcedureAdapter(getActivity(), datas);
    recyclerView.init(adapter);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setRefreshEnable(false);
  }

  @Override
  public void initData() {
    id = getArguments().getInt("int");
    getPresenter().startGetReviews(id);
    stateFulLayout.showState(StateFulLayout.LOADING);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    stateFulLayout.showState(StateFulLayout.CONTENT);
    datas.addAll((Collection<? extends Procedure>) object);
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
