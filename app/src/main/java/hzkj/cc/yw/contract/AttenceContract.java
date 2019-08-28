package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.AttenceLimitInfo;

public interface AttenceContract {

  interface Model extends BaseContract.Model {

    void getAttenceMessage(String attenceMobile, String attenceDateStart, String attenceDateEnd,
        RxFragment fragment);

    void startAttence(AttenceLimitInfo attenceLimitInfo, String attenceMobile, String attenceDate,
        String attenceTime, String type, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAttenceMessage();

    void startStartAttence(String type);

    void checkDate(String startDate, String endDate);
  }
}
