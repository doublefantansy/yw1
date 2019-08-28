package hzkj.cc.yw.ui.activity;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.progress.loading.MiniLoadingView;

import butterknife.BindView;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.UpdatePasswordContract;
import hzkj.cc.yw.presenter.UpdatePasswordPresenter;

public class UpdatePasswordActivity extends
    BaseActivity<UpdatePasswordContract.Presenter> implements UpdatePasswordContract.View {

  @BindView(R.id.submit)
  ButtonView submit;
  @BindView(R.id.newPassword)
  MaterialEditText newPassword;
  @BindView(R.id.confirmPassword)
  MaterialEditText confirmPassword;
  @BindView(R.id.oldPassword)
  MaterialEditText oldPassword;
  Snackbar snackbar;
  @BindView(R.id.loading)
  MiniLoadingView loadingView;

  @Override
  UpdatePasswordContract.Presenter createPresenter() {
    return new UpdatePasswordPresenter();
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
    return R.layout.update_password;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void initView() {
    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingView.setVisibility(View.VISIBLE);
        getPresenter().cheak(oldPassword.getText()
            .toString(), newPassword.getText()
            .toString(), confirmPassword.getText()
            .toString());
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    loadingView.setVisibility(View.GONE);
    new CcDialog(this, CcDialog.SUCCESS_DIALOG).setMessage((String) object)
        .setCancelListener(new CancelListener() {
          @Override
          public void onClick(CcDialog dialog) {
            finish();
          }
        })
        .showDialog();
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    super.updateFailUi(msg, object);
    loadingView.setVisibility(View.GONE);
  }

  @Override
  public void updateCheckSuccess(Object object) {
//        loadingView.setVisibility(View.GONE);
    getPresenter().startUpdatePassword(oldPassword.getText()
        .toString(), newPassword.getText()
        .toString());
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    super.updateCheckFailUi(msg, object);
    loadingView.setVisibility(View.GONE);
  }
}
