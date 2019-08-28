package hzkj.cc.yw.ui.activity;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.DeliverSingleDetailAdapter;
import hzkj.cc.yw.bean.SingleGood;
import hzkj.cc.yw.contract.DeliverSingleDetailContract;
import hzkj.cc.yw.presenter.DeliverSingleDetailPresenter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeliverSingleDetailActivity extends
    BaseActivity<DeliverSingleDetailContract.Presenter> implements
    DeliverSingleDetailContract.View {

  int id;
  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  DeliverSingleDetailAdapter adapter;
  List<SingleGood> datas = new ArrayList<>();

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  DeliverSingleDetailContract.Presenter createPresenter() {
    return new DeliverSingleDetailPresenter();
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_deliver_single_detail;
  }

  @Override
  public void initView() {
    bar.setTitle("物流详情");
    id = Integer.parseInt(getIntent().getStringExtra("id"));
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        getPresenter().startGetSingleGoods(id);
      }
    }, recyclerView);
    adapter = new DeliverSingleDetailAdapter(this, datas);
    recyclerView.init(adapter);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setRefreshEnable(false);
    stateFulLayout.showState(StateFulLayout.LOADING);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    stateFulLayout.showState(StateFulLayout.NETERROR);
  }

  @Override
  public void initData() {
    getPresenter().startGetSingleGoods(id);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    stateFulLayout.showState(StateFulLayout.CONTENT);
    datas.addAll((Collection<? extends SingleGood>) object);
  }


  @Override
  public void updateCheckSuccess(Object object) {
  }
}
