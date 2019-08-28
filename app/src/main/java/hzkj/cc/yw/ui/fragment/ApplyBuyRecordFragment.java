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
import hzkj.cc.yw.contract.ApplyBuyRecordContract;
import hzkj.cc.yw.presenter.ApplyBuyRecordPresenter;

public class ApplyBuyRecordFragment extends
    BaseFragment<ApplyBuyRecordContract.Presenter> implements ApplyBuyRecordContract.View {

  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  @BindView(R.id.stateLayout)
  StateFulLayout stateLayout;
  List<Procedure> procedures = new ArrayList<>();
  ProcedureAdapter adapter;
  int id;


  @Override
  void doOnVisible() {
  }

  @Override
  ApplyBuyRecordContract.Presenter createPresenter() {
    return new ApplyBuyRecordPresenter();
  }

  public static ApplyBuyRecordFragment newInstance(int someInt) {
    ApplyBuyRecordFragment fragment = new ApplyBuyRecordFragment();
    Bundle args = new Bundle();
    args.putInt("int", someInt);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_apply_buy_record;
  }

  @Override
  public void initView() {
    adapter = new ProcedureAdapter(getActivity(), procedures);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setRefreshEnable(false);
    recyclerView.init(adapter);
    stateLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetRecord(id);
      }
    }, recyclerView);
    stateLayout.showState(StateFulLayout.LOADING);
  }

  @Override
  public void initData() {
    id = getArguments().getInt("int");
    getPresenter().startGetRecord(id);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    stateLayout.showState(StateFulLayout.NETERROR);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    stateLayout.showState(StateFulLayout.CONTENT);
    procedures.addAll((Collection<? extends Procedure>) object);
//        for (ApplyBuyInfo applyBuyInfo : (List<ApplyBuyInfo>) object) {
//            applyBuyInfos.add(applyBuyInfo.getProcedure());
//        }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
