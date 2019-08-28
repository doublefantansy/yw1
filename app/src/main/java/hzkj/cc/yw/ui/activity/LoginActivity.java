package hzkj.cc.yw.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import butterknife.BindView;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.LoginContract;
import hzkj.cc.yw.presenter.LoginPresenter;
import hzkj.cc.yw.utils.UiUtil;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements
    LoginContract.View {

  @BindView(R.id.button_login)
  RoundButton buttonLogin;
  @BindView(R.id.edit_userName)
  MaterialEditText editUserName;
  @BindView(R.id.edit_passWord)
  MaterialEditText editPassWord;
  CcDialog dialog;

  @Override
  LoginContract.Presenter createPresenter() {
    return new LoginPresenter();
  }

  @Override
  public boolean isShowingTitleBar() {
    return false;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_login;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void initView() {
    dialog = new CcDialog(this, CcDialog.PROGRESS_DIALOG).setMessage("正在登录中");
    buttonLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.showDialog();
        getPresenter().startloginIn(editUserName.getText()
            .toString(), editPassWord.getText()
            .toString());
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    UiUtil.jumpToActivity(this, MainActivity.class, null, true, false, -1);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    dialog.dismissDialog();
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    switch ((int) object) {
      case LoginPresenter.PHONE: {
        editUserName.setError(msg);
        break;
      }
      case LoginPresenter.PASSWORD: {
        editPassWord.setError(msg);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
}
