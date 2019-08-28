package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;

import hzkj.cc.yw.bean.UserInfo;
import okhttp3.MultipartBody;

public interface MyInfoContentContract {

  interface Model extends BaseContract.Model {

    void updateInfo(UserInfo userInfo, MultipartBody.Part image,
        RxAppCompatActivity appCompatActivity);
//        void getIconUrl(RxAppCompatActivity appCompatActivity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startUpdateInfo(UserInfo userInfo, File file);
//        void startGetIconUrl();
  }
}
