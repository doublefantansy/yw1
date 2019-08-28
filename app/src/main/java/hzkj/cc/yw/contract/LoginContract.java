package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.UserInfo;

public interface LoginContract {

  interface Model extends BaseContract.Model {

    void loginIn(UserInfo userInfo, RxAppCompatActivity activity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startloginIn(String phone, String passWord);
  }
}
