package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.WorkOrderAdapter;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.contract.WorkOrderEachContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.WorkOrderEachPresenter;
import hzkj.cc.yw.ui.activity.WorkOrderProcessActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WorkOrderEachFragment extends
    hzkj.cc.yw.ui.fragment.BaseFragment<WorkOrderEachContract.Presenter> implements
    hzkj.cc.yw.contract.WorkOrderEachContract.View {

  @BindView(R.id.recycler)
  RecyclerLayout recyclerLayout;
  WorkOrderAdapter adapter;
  List<WorkOrderInfo> workOrderInfos = new ArrayList<>();
  boolean isRrefesh = true;

  @Override
  void doOnVisible() {
    recyclerLayout.onResume();
  }

  @Override
  WorkOrderEachContract.Presenter createPresenter() {
    return new WorkOrderEachPresenter();
  }

  public static WorkOrderEachFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    WorkOrderEachFragment fragment = new WorkOrderEachFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
    getPresenter().startGetWorkOrders(isRrefesh, getArguments().getInt("type"));
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_work_order_each;
  }

  @Override
  public void initView() {
    adapter = new WorkOrderAdapter(workOrderInfos, getActivity());
    recyclerLayout.init(adapter, true);
    recyclerLayout.setStateLayoutRefreshListenner(new RecyclerLayout.StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        isRrefesh = true;
        getData();
      }
    });
    recyclerLayout.setLoadMoreListenner(new CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner() {
      @Override
      public void loadMore() {
        isRrefesh = false;
        getData();
      }
    });
    recyclerLayout.setRefreshListenner(new CcRrefreshAndLoadMoreRecyclerView.RefreshListenner() {
      @Override
      public void refresh() {
        isRrefesh = true;
        getData();
      }
    });
    recyclerLayout.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
//        if (getArguments().getInt("type") == WorkOrderInfo.USEING) {
        UiUtil.fragmentJumpToActivity(WorkOrderEachFragment.this, WorkOrderProcessActivity.class,
            new ArrayMap<String, String>() {{
              put("id", String.valueOf(workOrderInfos.get(position).getWorkOrders_id()));
            }}, false, 1234);
//        }
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.REFRESHSUCCESS) {
      workOrderInfos.clear();
      workOrderInfos.addAll((Collection<? extends WorkOrderInfo>) object);
      recyclerLayout.refreshComplete(true);
    } else if (tag == BasePresenter.LOADMOREEMPTY) {
      recyclerLayout.loadComplete(true, true);
    } else {
      workOrderInfos.addAll((Collection<? extends WorkOrderInfo>) object);
      recyclerLayout.loadComplete(false, true);
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }

  @Override
  public void refresh() {
    recyclerLayout.onResume();
  }
}
