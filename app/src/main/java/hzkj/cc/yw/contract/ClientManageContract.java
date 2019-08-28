package hzkj.cc.yw.contract;

public interface ClientManageContract {

  interface Model extends BaseContract.Model {


    void getProjects(int agencyId);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {


    void startGetProjects(int agencyId);
  }
}
