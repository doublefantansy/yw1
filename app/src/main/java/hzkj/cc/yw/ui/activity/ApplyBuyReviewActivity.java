package hzkj.cc.yw.ui.activity;

import android.view.View;
import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.ApplyBuyReviewContract;
import hzkj.cc.yw.presenter.ApplyBuyReviewPresenter;
import hzkj.cc.yw.ui.fragment.ApplyBuyDetailFragment;
import hzkj.cc.yw.ui.fragment.ApplyBuyRecordFragment;
import java.util.ArrayList;

public class ApplyBuyReviewActivity extends
    BaseActivity<ApplyBuyReviewContract.Presenter> implements ApplyBuyReviewContract.View {

  int id;
  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_apply_buy_review;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  ApplyBuyReviewContract.Presenter createPresenter() {
    return new ApplyBuyReviewPresenter();
  }


  @Override
  public void initView() {
    bar.setTitle("申购相关");
    id = Integer.valueOf(getIntent().getStringExtra("id"));
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), new ArrayList<BottomChild>() {{
      add(new BottomChild("申购详情", ApplyBuyDetailFragment.newInstance(id),
          getResources().getDrawable(R.drawable.ic_wuzi),
          getResources().getDrawable(R.drawable.ic_wuzi_2)));
      add(new BottomChild("流程记录", ApplyBuyRecordFragment.newInstance(id),
          getResources().getDrawable(R.drawable.ic_icon_test_2),
          getResources().getDrawable(R.drawable.ic_icon_test_3)));
    }}, 0);
    bar.setLeftClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }


  @Override
  public void updateCheckSuccess(Object object) {
  }
}
