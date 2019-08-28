package hzkj.cc.yw.ui.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.DeliverProcedureDetailAdapter;
import hzkj.cc.yw.bean.ProcedureGoods;
import hzkj.cc.yw.contract.DeliverProcedureDetailContract;
import hzkj.cc.yw.presenter.DeliverProcedureDetailPresenter;

public class DeliverProcedureDetailActivity extends
    BaseActivity<DeliverProcedureDetailContract.Presenter> implements
    DeliverProcedureDetailContract.View {

  int id;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  DeliverProcedureDetailAdapter adapter;
  List<ProcedureGoods> datas = new ArrayList<>();

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  DeliverProcedureDetailContract.Presenter createPresenter() {
    return new DeliverProcedureDetailPresenter();
  }

  @Override
  public void getData() {
    getPresenter().startGetProcedureDetail(id);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_deliver_procedure_detail;
  }

  @Override
  public void initView() {
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getData();
      }
    }, recyclerView);
    stateFulLayout.showState(StateFulLayout.LOADING);
    adapter = new DeliverProcedureDetailAdapter(datas, this);
    recyclerView.setRefreshEnable(false);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.init(adapter);
    bar.setTitle("物资详情");
  }

  @Override
  public void initData() {
    id = Integer.valueOf(getIntent().getStringExtra("id"));
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    stateFulLayout.showState(StateFulLayout.NETERROR);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    stateFulLayout.showState(StateFulLayout.CONTENT);
    datas.addAll((Collection<? extends ProcedureGoods>) object);
    recyclerView.refreshComplete(true);
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
