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
import hzkj.cc.yw.contract.RentCarReviewContract;
import hzkj.cc.yw.contract.RentCarReviewContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.RentCarReviewPresenter;
import hzkj.cc.yw.ui.activity.RentCarReviewDetailActivity;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RentCarReviewFragment extends BaseFragment<RentCarReviewContract.Presenter> implements
    RentCarReviewContract.View {

  @BindView(R.id.recyclerLayout)
  RecyclerLayout recyclerLayout;
  RentCarAdapter adapter;
  List<RentCarInfo> datas = new ArrayList<>();
  int pageNum = 1;

  @Override
  void doOnVisible() {
    recyclerLayout.onResume();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (getArguments().getInt("type") == RentCarInfo.HASNOTREVIEW && resultCode == 100) {
      recyclerLayout.onResume();
    }
  }

  public static RentCarReviewFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt("type", type);
    RentCarReviewFragment fragment = new RentCarReviewFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  Presenter createPresenter() {
    return new RentCarReviewPresenter();
  }

  @Override
  public void getData() {
    getPresenter().startGetAll(getArguments().getInt("type"), pageNum);
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_rentcar_review_each;
  }

  @Override
  public void initView() {
    adapter = new RentCarAdapter(getActivity(), datas, true);
    recyclerLayout.init(adapter,true);
    recyclerLayout.setLoadMoreListenner(new LoadMoreListenner() {
      @Override
      public void loadMore() {
        pageNum++;
        getData();
      }
    });
    recyclerLayout.setRefreshListenner(new RefreshListenner() {
      @Override
      public void refresh() {
        pageNum = 1;
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
        UiUtil.fragmentJumpToActivity(RentCarReviewFragment.this, RentCarReviewDetailActivity.class,
            new ArrayMap<String, String>() {{
              put("id", datas.get(position).getApplication_id() + "");
              put("type"
                  , getArguments().getInt("type") + "");
            }}, true, 10);
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
      datas.addAll((Collection<? extends RentCarInfo>) object);
      recyclerLayout.refreshComplete(true);
    } else if (tag == BasePresenter.LOADMOREEMPTY) {
      recyclerLayout.loadComplete(true,true);
    } else {
      datas.addAll((Collection<? extends RentCarInfo>) object);
      recyclerLayout.loadComplete(false,true);
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
