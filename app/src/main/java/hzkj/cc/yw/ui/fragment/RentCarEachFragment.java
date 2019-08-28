package hzkj.cc.yw.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import butterknife.BindView;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.LoadMoreListenner;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView.RefreshListenner;
import hzkj.cc.ccrecyclerview.ClickItemListenner;
import hzkj.cc.ccrecyclerview.RecyclerLayout;
import hzkj.cc.ccrecyclerview.RecyclerLayout.StateLayoutRefreshListenner;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.RentCarAdapter;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.contract.RentCarEachContract;
import hzkj.cc.yw.contract.RentCarEachContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.RentCarEachPresenter;
import hzkj.cc.yw.ui.activity.RentCarDetailActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RentCarEachFragment extends BaseFragment<RentCarEachContract.Presenter> implements
    RentCarEachContract.View {

  @BindView(R.id.recyclerLayout)
  RecyclerLayout recyclerLayout;
  RentCarAdapter adapter;
  List<RentCarInfo> datas = new ArrayList<>();
  int pageNum = 1;

  @Override
  public void doOnVisible() {
    recyclerLayout.onResume();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    recyclerLayout.onResume();
  }

  @Override
  Presenter createPresenter() {
    return new RentCarEachPresenter();
  }

  public static RentCarEachFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    RentCarEachFragment fragment = new RentCarEachFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void getData() {
    getPresenter().startGetRentcarInfos(getArguments().getInt("type"), pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_rentcar_each;
  }

  @Override
  public void initView() {
    adapter = new RentCarAdapter(getActivity(), datas, false);
    recyclerLayout.init(adapter,true);
    recyclerLayout.setRefreshListenner(new RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    recyclerLayout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    recyclerLayout.setStateLayoutRefreshListenner(new StateLayoutRefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
        getData();
      }
    });
    recyclerLayout.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(final int position) {
        UiUtil.fragmentJumpToActivity(RentCarEachFragment.this, RentCarDetailActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position).getApplication_id() + "");
              put("type"
                  , getArguments().getInt("type") + "");
            }}, true, 100);
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case BasePresenter
          .REFRESHSUCCESS: {
        datas.clear();
        datas.addAll((Collection<? extends RentCarInfo>) object);
        recyclerLayout.refreshComplete(true);
        break;
      }
      case BasePresenter
          .LOADMOREEMPTY: {
        recyclerLayout.loadComplete(true,true);
        break;
      }
      case BasePresenter
          .LOADMORESUCCESS: {
        datas.addAll((Collection<? extends RentCarInfo>) object);
        recyclerLayout.loadComplete(false,true);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
