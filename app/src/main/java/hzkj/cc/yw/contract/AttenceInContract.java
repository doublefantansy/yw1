package hzkj.cc.yw.contract;

public interface AttenceInContract {

  interface Model extends BaseContract.Model {

    void getLimitTime();

    void getAttenceStatus(String mobile, String today);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetLimitTime();

    void startGetAttenceStatus();
  }
}
