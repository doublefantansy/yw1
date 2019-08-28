package hzkj.cc.yw.ui.activity;

import android.os.Bundle;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.ReviewContract;
import hzkj.cc.yw.presenter.ReviewPresenter;

public class ReviewActivity extends BaseActivity<ReviewContract.Presenter> implements
    ReviewContract.View {

//  @BindView(R.id.stateLayout)
//  StatefulLayout statefulLayout;
//  @BindView(R.id.recyclerView)
//  RecyclerView recyclerView;
//  List<TransActionInfo> reviewInfos = new ArrayList<>();
//  ReviewAdapter adapter;
//  @BindView(R.id.easy_indicator)
//  EasyIndicator easyIndicator;
//  int current = 0;

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  ReviewContract.Presenter createPresenter() {
    return new ReviewPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_reviewinfo;
  }

  @Override
  public void initView() {
//    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//    recyclerView.setLayoutManager(layoutManager);
//    easyIndicator.setTabTitles(new String[]{"全部", "已审批", "未审批"});
//    easyIndicator.setOnTabClickListener(new EasyIndicator.onTabClickListener() {
//      @Override
//      public void onTabClick(String title, int position) {
//        current = position;
//        statefulLayout.showLoading();
//        if (position == 1) {
//          getPresenter().startGetReviewInfos("all_review");
//        } else if (position == 2) {
//          getPresenter().startGetReviewInfos("all_noreview");
//        } else {
//          getPresenter().startGetReviewInfos("all");
//        }
//      }
//    });
//    statefulLayout.showLoading();
  }

//  @Subscribe(sticky = true)
//  public void getEvent(Integer i) {
////    if (current == 0) {
////      getPresenter().startGetReviewInfos("all");
////    } else if (current == 1) {
////      getPresenter().startGetReviewInfos("all_review");
////    } else {
////      getPresenter().startGetReviewInfos("all_noreview");
////    }
//  }

  @Override
  public void initData() {
//    EventBus.getDefault()
//        .register(this);
//    getPresenter().startGetReviewInfos("all");
//    adapter = new ReviewAdapter(this, reviewInfos);
//    recyclerView.setAdapter(adapter);
//    adapter.setListenner(new ClickItemListenner() {
//      @Override
//      public void click(final int p) {
//        UiUtil.jumpToActivity(ReviewActivity.this, RentCarReviewActivity.class,
//            new ArrayMap<String, String>() {{
//              put("code", reviewInfos.get(p)
//                  .getN_bussiness_id() + "");
//              put("transAction", reviewInfos.get(p)
//                  .getN_transAction_id() + "");
//              put("businessState", reviewInfos.get(p)
//                  .getN_business_state() + "");
//            }}, false, false, -1);
//      }
//    });
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
//    if (tag == 0) {
//      statefulLayout.showContent();
//      reviewInfos.clear();
//      reviewInfos.addAll(((List<TransActionInfo>) object));
//      adapter.notifyDataSetChanged();
//    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
