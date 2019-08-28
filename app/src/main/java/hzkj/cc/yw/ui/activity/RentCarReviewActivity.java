package hzkj.cc.yw.ui.activity;

import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.contract.ReviewInfoContract;
import hzkj.cc.yw.presenter.ReviewInfoPresenter;
import hzkj.cc.yw.ui.fragment.RentCarEachFragment;
import hzkj.cc.yw.ui.fragment.RentCarReviewFragment;
import java.util.ArrayList;

public class RentCarReviewActivity extends BaseActivity<ReviewInfoContract.Presenter> implements
    ReviewInfoContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  ReviewInfoContract.Presenter createPresenter() {
    return new ReviewInfoPresenter();
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
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
    bar.setTitle("租车审核");
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), new ArrayList<BottomChild>() {{
      add(new BottomChild("待审核", RentCarReviewFragment.newInstance(RentCarInfo.HASNOTREVIEW),
          getDrawable(R.drawable.ic_qiyong_2), getDrawable(R.drawable.ic_qiyong)));
      add(new BottomChild("通过", RentCarReviewFragment.newInstance(RentCarInfo.REVIEWSUCCESS),
          getDrawable(R.drawable.ic_yiguidang_2), getDrawable(R.drawable.ic_yiguidang)));
      add(new BottomChild("未通过", RentCarReviewFragment.newInstance(RentCarInfo.REVIEWFAIL),
          getDrawable(R.drawable.ic_yitingyong_2), getDrawable(R.drawable.ic_yitingyong)));
    }}, 0);
    bottomNavigation.setTextColors(new ArrayList<Integer>() {{
//      add(getColor(R.color.bottom_deep_blue));
      add(getColor(R.color.bottom_blue));
      add(getColor(R.color.bottom_green));
      add(getColor(R.color.bottom_red));
    }});
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
