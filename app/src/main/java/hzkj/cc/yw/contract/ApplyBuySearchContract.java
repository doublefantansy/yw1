package hzkj.cc.yw.contract;

public interface ApplyBuySearchContract {

  interface Model extends BaseContract.Model {

//    void getDataById(String mobile, int id);

    void getDataByTime(int userId, String startTime, String endTime, int project, int status);
  }

  interface View extends BaseContract.View {

//    void notifyGetDataById(int id);

    void notifyGetDataByTime(String startTime, String endTime, int project,
        int status);
  }

  interface Presenter extends BaseContract.Presenter {

//    void startGetDataById(int id);

    void startGetDataByTime(String startTime, String endTime, int project, int status);
  }
}
