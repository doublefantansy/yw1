package hzkj.cc.yw.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.WelcomeContract;
import hzkj.cc.yw.presenter.WelcomePresenter;
import hzkj.cc.yw.utils.UiUtil;

public class WelcomeActivity extends BaseActivity<WelcomeContract.Presenter> implements
    WelcomeContract.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  WelcomeContract.Presenter createPresenter() {
    return new WelcomePresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_welcome;
  }

  @Override
  public void initView() {
    UiUtil.disableStatusBar(this);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public boolean isShowingTitleBar() {
    return false;
  }

  @Override
  public void initData() {
    getPresenter().jumpToLogin();
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    UiUtil.jumpToActivity(this, (Class<? extends Activity>) object, null, true, false, -1);
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
