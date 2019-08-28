package hzkj.cc.yw.contract;

public interface GoToRepairSearchContract {

  interface Model extends BaseContract.Model {

    void getSearchByRepairId(int id);

    void searchByDate(int type, String startTime, String endTime);

    void searchByProject(int id);
  }

  interface View extends BaseContract.View {

    void notifyGetDataById(int id);

    void notifyGetDataByDate(int dateType, String startTime, String endTime);

    void notifyGetDataByProject(int id);
  }

  interface Presenter extends BaseContract.Presenter {

    void startSearchByRepairId(int id);

    void startSearchByDate(int type, String startTime, String endTime);

    void startSearchByProject(int id);
  }
}
