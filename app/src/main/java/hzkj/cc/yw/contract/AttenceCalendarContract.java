package hzkj.cc.yw.contract;

public interface AttenceCalendarContract {

  interface Model extends BaseContract.Model {

    void getAttenceMessage(String mobile, String attenceDateStart, String attenceDateEnd);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAttenceMessage(String attenceDateStart, String attenceDateEnd);
  }
}
