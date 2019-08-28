package hzkj.cc.yw.ui.activity;

import com.xuexiang.xui.widget.actionbar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.mybottomnavigation.OnClickBottomChildListener;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.GoToRepairDetailContract;
import hzkj.cc.yw.presenter.GoToRepairDetailPresenter;
import hzkj.cc.yw.ui.fragment.GoToRepairRecordFragment;
import hzkj.cc.yw.ui.fragment.GoToRepairReviewFragment;

public class GoToRepairDetailActivity extends
    BaseActivity<GoToRepairDetailContract.Presenter> implements GoToRepairDetailContract.View {

  int id;
  @BindView(R.id.bottomNavigation)
  MyBottomNavigation myBottomNavigation;
  @BindView(R.id.bar)
  TitleBar bar;
  List<BottomChild> bottomChildren = new ArrayList<>();

  @Override
  GoToRepairDetailContract.Presenter createPresenter() {
    return new GoToRepairDetailPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_go_to_repair_detail;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void initView() {
    bar.setTitle("返修详情");
    id = Integer.parseInt(getIntent().getStringExtra("id"));
    bottomChildren.addAll(new ArrayList<BottomChild>() {{
      add(new BottomChild("返修详情", GoToRepairRecordFragment.newInstance(id),
          getResources().getDrawable(R.drawable.ic_fanxiujilu_2),
          getResources().getDrawable(R.drawable.ic_fanxiujilu)));
      add(new BottomChild("流程记录", GoToRepairReviewFragment.newInstance(id),
          getResources().getDrawable(R.drawable.ic_icon_test_2),
          getResources().getDrawable(R.drawable.ic_icon_test_3)));
    }});
    myBottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 0);
    myBottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
      @Override
      public void onClick(BottomChild bottomChild, int position) {
        bar.setTitle(bottomChild.getName());
      }
    });
  }

  @Override
  public int getDrawLayout() {
    return 0;
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
