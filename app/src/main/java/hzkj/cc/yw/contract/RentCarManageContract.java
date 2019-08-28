package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public interface RentCarManageContract {

  interface Model extends BaseContract.Model {

    void getAllRentCarInfo(String phone, RxAppCompatActivity appCompatActivity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetAllRentCarInfo();
  }
}
