package hzkj.cc.yw.contract;

import hzkj.cc.yw.bean.RentCarInfo;

public interface RentCarReviewDetailContract {

  interface Model extends BaseContract.Model {

    void putRentInfo(int type,RentCarInfo rentCarInfo,int userId);

    void getDetail(int id,int type);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startPutRentInfo(int type, RentCarInfo rentCarInfo, int userId);

    void check(String text, int type);

    void startGetDetail(int id,int type);

  }
}
