package hzkj.cc.yw.contract;

public interface RentCarReviewContract {

  interface Model extends BaseContract.Model {

    void getAll(int type, int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAll(int type, int pageNum);
  }
}
