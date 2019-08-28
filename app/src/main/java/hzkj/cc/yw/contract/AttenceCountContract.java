package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface AttenceCountContract {

  interface Model extends BaseContract.Model {

    void getAttenceCount(String mobile, String startDate, String endDate, RxFragment fragment);
  }

  interface View extends BaseContract.View {

    void getSignalByActivity(String startDate, String endDate);
  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAttenceCount(String startDate, String endDate);
  }
}
