package hzkj.cc.yw.contract;

public interface WelcomeContract {

  interface Model extends BaseContract.Model {

  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void jumpToLogin();
  }
}
