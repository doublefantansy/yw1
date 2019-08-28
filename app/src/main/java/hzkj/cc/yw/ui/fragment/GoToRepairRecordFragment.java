package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.GoToRepairDetailRecordAdapter;
import hzkj.cc.yw.bean.RepairInfoDetail;
import hzkj.cc.yw.contract.GoToRepairRecordContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.GoToRepairRecordPresenter;

public class GoToRepairRecordFragment extends
    BaseFragment<GoToRepairRecordContract.Presenter> implements GoToRepairRecordContract.View {

  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  int id;
  GoToRepairDetailRecordAdapter adapter;
  List<RepairInfoDetail> datas = new ArrayList<>();

  @Override
  void doOnVisible() {
  }

  @Override
  GoToRepairRecordContract.Presenter createPresenter() {
    return new GoToRepairRecordPresenter();
  }

  public static GoToRepairRecordFragment newInstance(int aInt) {
    GoToRepairRecordFragment fragment = new GoToRepairRecordFragment();
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
    return R.layout.fragment_go_to_repair_record;
  }

  @Override
  public void initView() {
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetDetail(id);
      }
    }, recyclerView);
    adapter = new GoToRepairDetailRecordAdapter(getActivity(), datas);
    recyclerView.init(adapter);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setRefreshEnable(false);
  }

  @Override
  public void initData() {
    id = getArguments().getInt("int");
    getPresenter().startGetDetail(id);
    stateFulLayout.showState(StateFulLayout.LOADING);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.EMPTY) {
      stateFulLayout.showState(StateFulLayout.EMPTY);
    } else {
      stateFulLayout.showState(StateFulLayout.CONTENT);
      datas.addAll((Collection<? extends RepairInfoDetail>) object);
    }
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
