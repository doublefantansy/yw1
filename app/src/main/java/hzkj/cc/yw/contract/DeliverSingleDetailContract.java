package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public interface DeliverSingleDetailContract {

  interface Model extends BaseContract.Model {

    void getSingleGoods(int id, RxAppCompatActivity activity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetSingleGoods(int id);
  }
}
