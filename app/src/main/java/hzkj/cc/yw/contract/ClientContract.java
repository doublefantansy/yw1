package hzkj.cc.yw.contract;

public interface ClientContract {

  interface Model extends BaseContract.Model {

    void getAll(int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAll(int pageNum);
  }
}
