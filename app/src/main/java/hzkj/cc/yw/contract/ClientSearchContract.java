package hzkj.cc.yw.contract;

public interface ClientSearchContract {

  interface Model extends BaseContract.Model {
     void searchByProject(int projectId,int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startSearchByProject(int projectId,int pageNum);
  }
}
