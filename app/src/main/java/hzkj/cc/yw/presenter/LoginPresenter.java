package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.MyApplication;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.LoginContract;
import hzkj.cc.yw.model.LoginModel;
import hzkj.cc.yw.utils.StorageUtil;

public class LoginPresenter extends
    BasePresenter<LoginContract.View, LoginContract.Model> implements LoginContract.Presenter {

  public static final int PHONE = 0;
  public static final int PASSWORD = 1;

  @Override
  LoginContract.Model createModel() {
    return new LoginModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == RetrofitFactory.LOGINSUCCESS) {
      StorageUtil.putData(StorageUtil.KEY_USER, response.getData());
      getView().updateSuccessUi(null, 0);
    } else if (response.getCode() == RetrofitFactory.LOGINERROR) {
      getView().updateFailUi(response.getMsg(), 1);
    }
  }

  @Override
  public void startloginIn(String phone, String passWord) {
    if (phone.isEmpty()) {
      getView().updateCheckFailUi(MyApplication.getApplication()
          .getString(R.string.userIsNull), PHONE);
    } else if (passWord.isEmpty()) {
      getView().updateCheckFailUi(MyApplication.getApplication()
          .getString(R.string.passWordIsNull), PASSWORD);
    } else {
      getModel().loginIn(new UserInfo(phone, passWord), (RxAppCompatActivity) getView());
    }
  }
}
