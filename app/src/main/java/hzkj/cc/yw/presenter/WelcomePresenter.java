package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.WelcomeContract;
import hzkj.cc.yw.model.WelcomeModel;
import hzkj.cc.yw.ui.activity.LoginActivity;
import hzkj.cc.yw.ui.activity.MainActivity;
import hzkj.cc.yw.utils.StorageUtil;

public class WelcomePresenter extends
    BasePresenter<WelcomeContract.View, WelcomeContract.Model> implements
    WelcomeContract.Presenter {

  @Override
  WelcomeContract.Model createModel() {
    return new WelcomeModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }

  @Override
  public void jumpToLogin() {
    if (StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class) == null) {
      new android.os.Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          getView().updateSuccessUi(LoginActivity.class, 0);
        }
      }, 3000);
    } else {
      new android.os.Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          getView().updateSuccessUi(MainActivity.class, 0);
        }
      }, 3000);
    }
  }
}
