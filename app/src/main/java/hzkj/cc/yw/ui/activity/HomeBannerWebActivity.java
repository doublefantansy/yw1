package hzkj.cc.yw.ui.activity;

import android.widget.LinearLayout;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.HomeBannerWebContract;
import hzkj.cc.yw.presenter.HomeBannerWebPresenter;

public class HomeBannerWebActivity extends BaseActivity<HomeBannerWebContract.Presenter> implements
    HomeBannerWebContract.View {

  @BindView(R.id.layout)
  LinearLayout layout;
  //    @BindView(R.id.agentWeb)
//    AgentWebView agentWebView;
  AgentWeb agentWeb;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  HomeBannerWebContract.Presenter createPresenter() {
    return new HomeBannerWebPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_home_banner_web;
  }

  @Override
  public void initView() {
    agentWeb = AgentWeb.with(this)//传入Activity
        .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1,
            -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
        .useDefaultIndicator()// 使用默认进度条
        .createAgentWeb()//
        .ready()
        .go(getIntent().getStringExtra("url"));
  }

  @Override
  protected void onPause() {
    agentWeb.getWebLifeCycle().onPause();
    super.onPause();
  }

  @Override
  protected void onResume() {
    agentWeb.getWebLifeCycle().onResume();
    super.onResume();
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
