package hzkj.cc.yw.contract;

public interface DeliverProcedureEachContract {

  interface Model extends BaseContract.Model {

    void getProcedureDeliverInfo(int type, int pageNum);

    void getDeliver(int userId, int deliverId, String nowTime);
  }

  interface View extends BaseContract.View {

    void clickBottom();
  }

  interface Presenter extends BaseContract.Presenter {

    void startGetProcedureDeliverInfo(int type, int pageNum);

    /**
     * 收货
     */
    void startGetDeliver(int deliverId);
  }
}
