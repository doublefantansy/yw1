package hzkj.cc.yw.contract;

public interface RentCarEachContract {

  interface Model extends BaseContract.Model {

    void getRentcarInfos(int type, int mobile, int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetRentcarInfos(int type, int pageNum);
  }
}
