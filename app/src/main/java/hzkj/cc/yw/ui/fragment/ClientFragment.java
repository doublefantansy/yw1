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
import hzkj.cc.yw.contract.ClientContract;
import hzkj.cc.yw.contract.ClientContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.ClientPresenter;
import hzkj.cc.yw.ui.activity.ClientInfoActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClientFragment extends BaseFragment<ClientContract.Presenter> implements
    ClientContract.View {

  List<ClientInfo> datas = new ArrayList<>();
  ClientAdapter adapter;
  @BindView(R.id.recycler)
  RecyclerLayout recycler;
  int pageNum = 1;

  @Override
  void doOnVisible() {
  }

  @Override
  Presenter createPresenter() {
    return new ClientPresenter();
  }

  public static ClientFragment newInstance() {
    Bundle args = new Bundle();
    ClientFragment fragment = new ClientFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
    getPresenter().startGetAll(pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_client;
  }

  @Override
  public void initView() {
    adapter = new ClientAdapter(getActivity(), datas);
    recycler.init(adapter, true);
    recycler.setRefreshEnable(false);
    recycler.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    recycler.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.jumpToActivity(getActivity(), ClientInfoActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position).getCustomer_id() + "");
            }}, false, false, 1);
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.REFRESHSUCCESS) {
      datas.clear();
      datas.addAll((Collection<? extends ClientInfo>) object);
      recycler.refreshComplete(true);
    } else {
      datas.addAll((Collection<? extends ClientInfo>) object);
      recycler.loadComplete(true, true);
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
