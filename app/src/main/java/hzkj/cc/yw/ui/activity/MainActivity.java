package hzkj.cc.yw.ui.activity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.mybottomnavigation.OnClickBottomChildListener;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.MainActivityContract;
import hzkj.cc.yw.presenter.MainActivityPresenter;
import hzkj.cc.yw.ui.fragment.HomePageFragment;
import hzkj.cc.yw.ui.fragment.MyInfoFragment;

/**
 * @author cc
 */
public class MainActivity extends BaseActivity<MainActivityContract.Presenter> implements
    MainActivityContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//        initLocationOption();
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public boolean isShowingTitleBar() {
    return false;
  }

  @Override
  public void initView() {
//        ViseLog.d(StorageUtil.getData(StorageUtil.KEY_USER,UserInfo.class));
    List<BottomChild> bottomChildren = new ArrayList<>();
    bottomChildren.add(new BottomChild("工作", new HomePageFragment(),
        getResources().getDrawable(R.drawable.ic_gongzuo_2),
        getResources().getDrawable(R.drawable.ic_gongzuo)));
//        bottomChildren.add(new BottomChild("考勤", new AttenceFragment(), getResources().getDrawable(R.drawable.ic_kaoqin_unselected), getResources().getDrawable(R.drawable.ic_kaoqin_selected)));
    bottomChildren.add(new BottomChild("我的", new MyInfoFragment(),
        getResources().getDrawable(R.drawable.ic_wode_unselected),
        getResources().getDrawable(R.drawable.ic_wode_selected)));
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 0);
    bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
      @Override
      public void onClick(BottomChild bottomChild, int position) {
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  MainActivityContract.Presenter createPresenter() {
    return new MainActivityPresenter();
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
