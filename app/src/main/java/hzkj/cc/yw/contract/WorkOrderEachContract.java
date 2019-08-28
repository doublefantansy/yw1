package hzkj.cc.yw.contract;

public interface WorkOrderEachContract {

  interface View extends BaseContract.View {

    void refresh();
  }

  interface Presenter extends BaseContract.Presenter {

    void startGetWorkOrders(boolean isRefresh, int type);
  }

  interface Model extends BaseContract.Model {

    void getWorkOrders(int userId, int pageNum, int type);
  }
}
