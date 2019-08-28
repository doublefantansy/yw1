package hzkj.cc.yw.contract;

public interface ApplyBuyContract {

  interface Model extends BaseContract.Model {

    void getProjects(int deptId);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {


    void checkDate(String startDate, String endDate, String agency, String status);

    void startGetProjects();

  }
}
