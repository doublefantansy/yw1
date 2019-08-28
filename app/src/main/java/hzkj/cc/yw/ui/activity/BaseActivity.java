package hzkj.cc.yw.ui.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xuexiang.xui.XUI;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.contract.BaseContract;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.UiUtil;

/**
 * @author cc
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends
    RxAppCompatActivity implements BaseContract.View {

  private P presenter;
  private View statusBarView;
  TitleBar bar;
  DrawerLayout drawerLayout;

  public abstract boolean isShowingTitleBar();

  public abstract int getDrawLayout();

  @Override
  public boolean isGetDataAtFirst() {
    return true;
  }

  public P getPresenter() {
    return presenter;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    XUI.initTheme(this);
    super.onCreate(savedInstanceState);
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//      getWindow().addFlags(
//          WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//    }
    if (Build.VERSION.SDK_INT >= 21) {
      View decorView = getWindow().getDecorView();
      int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
          | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
          | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
      getWindow().setNavigationBarColor(Color.TRANSPARENT);
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
    drawerLayout = new DrawerLayout(this);
    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    View view = LayoutInflater.from(this)
        .inflate(R.layout.bar, null);
    bar = view.findViewById(R.id.bar);
    bar.setLeftClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    LinearLayout linearLayout = new LinearLayout(this);
    linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    if (isShowingTitleBar()) {
      linearLayout.addView(view);
    }
    linearLayout.addView(LayoutInflater.from(this)
        .inflate(getLayoutId(), linearLayout, false));
    drawerLayout.addView(linearLayout);
    setContentView(drawerLayout);
    if (getDrawLayout() != 0) {
      View drawView = LayoutInflater.from(this)
          .inflate(getDrawLayout(), drawerLayout, false);
      DrawerLayout.LayoutParams layoutParams = ((DrawerLayout.LayoutParams) drawView
          .getLayoutParams());
      layoutParams.topMargin = UiUtil.dipTopx(this, 20);
      drawView.setLayoutParams(layoutParams);
      drawView.setClickable(true);
      drawerLayout.addView(drawView);
    }
    presenter = createPresenter();
    presenter.setView(this, this);
    ButterKnife.bind(this);
    initData();
    initView();
    if (isGetDataAtFirst()) {
      getData();
    }
//        Window window = getWindow();
//        //After LOLLIPOP not translucent status bar
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //Then call setStatusBarColor.
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(getResources().getColor(R.color.hzBlue));
//
  }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            return true;
//        }
//        return false;
//    }

  @Override
  public void finish() {
    super.finish();
    overridePendingTransition(R.anim.left_in, R.anim.right_out);
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        .show();
  }

  abstract P createPresenter();

  @Override
  protected void onDestroy() {
    super.onDestroy();
    presenter.unSetView();
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    if (((int) object) == CustomSubscriber.TOKENTIMEOUT) {
      new CcDialog(this, CcDialog.FAIL_DIALOG).setMessage(msg)
          .setCancelListener(new CancelListener() {
            @Override
            public void onClick(CcDialog dialog) {
              StorageUtil.clearAll();
              UiUtil.jumpToActivity(BaseActivity.this, LoginActivity.class, null, true, false, -1);
            }
          })
          .showDialog();
    } else {
      Toast.makeText(this, msg, Toast.LENGTH_SHORT)
          .show();
    }
  }
}

