package hzkj.cc.yw.contract;

public interface ClientInfoContract {

  interface Model extends BaseContract.Model {

    void getOne(int id);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetOne(int id);
  }
}
