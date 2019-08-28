package hzkj.cc.yw.ui.fragment;

import android.support.v4.util.ArrayMap;

import com.vise.log.ViseLog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.stateful.StateFulLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.GoToRepairAdapter;
import hzkj.cc.yw.bean.RepairInfo;
import hzkj.cc.yw.contract.GoToRepairSearchContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.GoToRepairSearchPresenter;
import hzkj.cc.yw.ui.activity.GoToRepairDetailActivity;
import hzkj.cc.yw.utils.UiUtil;

public class GoToRepairSearchFragment extends
    BaseFragment<GoToRepairSearchContract.Presenter> implements GoToRepairSearchContract.View {

  @Override
  void doOnVisible() {
  }

  public static final int SEARCHBYID = 0;
  public static final int SEARCHBYDATE = 1;
  public static final int SEARCHBYPROJECT = 2;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  @BindView(R.id.stateLayout)
  StateFulLayout stateFulLayout;
  GoToRepairAdapter adapter;
  List<RepairInfo> datas = new ArrayList<>();
  int type;
  int id;
  int dateType;
  String startTime;
  String endTime;
  int projectId;

  @Override
  GoToRepairSearchContract.Presenter createPresenter() {
    return new GoToRepairSearchPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_go_to_repair_search;
  }

  @Override
  public void initView() {
    stateFulLayout.init(new StateFulLayout.RefreshListenner() {
      @Override
      public void refresh() {
        switch (type) {
          case SEARCHBYDATE: {
            getPresenter().startSearchByDate(dateType, startTime, endTime);
            break;
          }
          case SEARCHBYID: {
            getPresenter().startSearchByRepairId(id);
            break;
          }
          case SEARCHBYPROJECT: {
            getPresenter().startSearchByProject(projectId);
            break;
          }
        }
      }
    }, recyclerView);
    adapter = new GoToRepairAdapter(getActivity(), datas);
    recyclerView.init(adapter);
    recyclerView.setRefreshEnable(false);
    recyclerView.setLoadMoreEnable(false);
    recyclerView.setClickItemListenner(new ClickItemListenner() {
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
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.EMPTY) {
      stateFulLayout.showState(StateFulLayout.EMPTY);
    } else {
      stateFulLayout.showState(StateFulLayout.CONTENT);
      datas.clear();
      datas.addAll((Collection<? extends RepairInfo>) object);
      recyclerView.refreshComplete(true);
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

  @Override
  public void notifyGetDataById(int id) {
    stateFulLayout.showState(StateFulLayout.LOADING);
    type = SEARCHBYID;
    this.id = id;
    getPresenter().startSearchByRepairId(this.id);
  }

  @Override
  public void notifyGetDataByDate(int dateType, String startTime, String endTime) {
    ViseLog.d(dateType);
    stateFulLayout.showState(StateFulLayout.LOADING);
    type = SEARCHBYDATE;
    this.dateType = dateType;
    this.startTime = startTime;
    this.endTime = endTime;
    getPresenter().startSearchByDate(this.type, this.startTime, this.endTime);
  }

  @Override
  public void notifyGetDataByProject(int id) {
    this.projectId = id;
    type = SEARCHBYPROJECT;
    stateFulLayout.showState(StateFulLayout.LOADING);
    getPresenter().startSearchByProject(id);
  }
}
