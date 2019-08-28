package hzkj.cc.yw.contract;

public interface DeliverSingleEachContract {

  interface Model extends BaseContract.Model {

    void startGetSingleDeliverInfos(int userId, int type, int pageNum);

    void getDeliver(int id, String nowTime);
  }

  interface View extends BaseContract.View {
  }

  interface Presenter extends BaseContract.Presenter {

    void startGetSingleDeliverInfos(int type, int pageNum);

    void startGetDeliver(int id);
  }
}
