package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface DeliverSingleContract {

  interface Model extends BaseContract.Model {

    void startGetSingleDeliverInfos(int pageNum, RxFragment fragment);

    void getDeliver(int id, String nowTime, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetSingleDeliverInfos(int pageNum);

    void startGetDeliver(int id);
  }
}
