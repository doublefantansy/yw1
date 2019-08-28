package hzkj.cc.yw.contract;

public interface DeliverProcedureDetailContract {

  interface Model extends BaseContract.Model {

    void getProcedureDetail(int deliverId);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetProcedureDetail(int deliverId);
  }
}
