package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface ApplyBuyRecordContract {

  interface Model extends BaseContract.Model {

    void getRecord(int id, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetRecord(int id);
  }
}
