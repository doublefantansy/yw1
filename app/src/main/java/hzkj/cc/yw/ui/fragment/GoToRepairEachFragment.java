package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.ccrecyclerview.RecyclerLayout.StateLayoutRefreshListenner;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.GoToRepairAdapter;
import hzkj.cc.yw.bean.RepairInfo;
import hzkj.cc.yw.contract.GoToRepairEachContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.GoToRepairEachPresenter;
import hzkj.cc.yw.ui.activity.GoToRepairDetailActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GoToRepairEachFragment extends
    BaseFragment<GoToRepairEachContract.Presenter> implements GoToRepairEachContract.View {

  @BindView(R.id.layout)
  RecyclerLayout layout;

  GoToRepairAdapter adapter;
  List<RepairInfo> datas = new ArrayList<>();
  int pageNum = 1;
//    boolean first = true;

  public static GoToRepairEachFragment getInstance(int type) {
    GoToRepairEachFragment fragment = new GoToRepairEachFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("type", type);
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override
  void doOnVisible() {
  }

  @Override
  GoToRepairEachContract.Presenter createPresenter() {
    return new GoToRepairEachPresenter();
  }

  @Override
  public void getData() {
  }

  /**
   *
   */
  @Override
  public int getLayoutId() {
    return R.layout.fragment_go_to_repair_each;
  }

  @Override
  public void initView() {
    adapter = new GoToRepairAdapter(getActivity(), datas);
    layout.init(adapter,true);
    layout.setRefreshListenner(new CcRrefreshAndLoadMoreRecyclerView.RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getPresenter().startGetRepairInfo(getArguments().getInt("type"), pageNum);
      }
    });
    layout.setLoadMoreListenner(new CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getPresenter().startGetRepairInfo(getArguments().getInt("type"), pageNum);
      }
    });
    layout.setStateLayoutRefreshListenner(new StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getPresenter().startGetRepairInfo(getArguments().getInt("type"), pageNum);
      }
    });
    layout.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), GoToRepairDetailActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position)
                  .getBackInfoId() + "");
            }}, false, false, 0);
      }
    });
  }

  @Override
  public void initData() {
    getPresenter().startGetRepairInfo(getArguments().getInt("type"), pageNum);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
//    super.updateFailUi(msg, object);
    int tag = (int) object;
    if (tag == BasePresenter.LOADMOREERROR) {
      layout.loadComplete(false, false);
    } else {
      layout.refreshComplete(false);
    }
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case BasePresenter.REFRESHSUCCESS: {
        datas.clear();
        datas.addAll((Collection<? extends RepairInfo>) object);
        layout.refreshComplete(true);
        break;
      }
      case BasePresenter.LOADMORESUCCESS: {
        datas.addAll((Collection<? extends RepairInfo>) object);
        layout.loadComplete(false, true);
        break;
      }
      case BasePresenter.LOADMOREEMPTY: {
        layout.loadComplete(true, true);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
