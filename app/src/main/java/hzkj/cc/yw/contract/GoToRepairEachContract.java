package hzkj.cc.yw.contract;

public interface GoToRepairEachContract {

  interface Model extends BaseContract.Model {

    void getRepairInfo(int id, int type, int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetRepairInfo(int type, int pageNum);
  }
}
