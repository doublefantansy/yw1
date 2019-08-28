package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ClientAdapter;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.Event;
import hzkj.cc.yw.contract.ClientSearchContract;
import hzkj.cc.yw.contract.ClientSearchContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.ClientSearchPresenter;
import hzkj.cc.yw.ui.activity.ClientInfoActivity;
import hzkj.cc.yw.utils.GsonUtil;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ClientSearchFragment extends BaseFragment<ClientSearchContract.Presenter> implements
    ClientSearchContract.View {

  @BindView(R.id.recycler)
  RecyclerLayout recyclerLayout;
  ClientAdapter adapter;
  List<ClientInfo> datas = new ArrayList<>();
  int pageNum = 1;
  int projectId;

  @Override
  void doOnVisible() {
  }

  @Override
  public boolean isGetDataAtFirst() {
    return false;
  }

  @Override
  Presenter createPresenter() {
    return new ClientSearchPresenter();
  }

  public static ClientSearchFragment newInstance() {
    Bundle args = new Bundle();
    ClientSearchFragment fragment = new ClientSearchFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
    getPresenter().startSearchByProject(projectId, pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_client_search;
  }

  @Override
  public void initView() {
    EventBus.getDefault().register(this);
    adapter = new ClientAdapter(getActivity(), datas);
    recyclerLayout.init(adapter, false);
    recyclerLayout.setRefreshEnable(false);
    recyclerLayout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    recyclerLayout.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), ClientInfoActivity.class,
            new ArrayMap<String, String>() {{
              put("clientInfo", GsonUtil.objectToString(datas.get(position)));
            }}, false, false, 1);
      }
    });
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(sticky = true)
  void getEvent(Event event) {
    if (event.getStatus() == 1000) {
      projectId = (Integer) event.getObject();
      recyclerLayout.showLoading();
      getData();
    }
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.REFRESHSUCCESS) {
      datas.clear();
      datas.addAll((Collection<? extends ClientInfo>) object);
      recyclerLayout.refreshComplete(true);
    } else {
      datas.addAll((Collection<? extends ClientInfo>) object);
      recyclerLayout.loadComplete(true, true);
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
