package hzkj.cc.yw.contract;

public interface GoToRepairContract {

  interface Model extends BaseContract.Model {

    void getAgencyList(int id);

    void getProjectList(int id);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void checkDrawer(String startDate, String endDate, int id);

    void startGetAgencyList(int id);

    void startGetProjectList(int id);
  }
}
