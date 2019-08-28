package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.UserInfo;

public interface UpdatePasswordContract {

  interface Model extends BaseContract.Model {

    void updatePassword(UserInfo userInfo, String newPassword,
        RxAppCompatActivity appCompatActivity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void cheak(String oldPassword, String newPassword, String confirmPassword);

    void startUpdatePassword(String oldPassword, String newPassword);
  }
}
