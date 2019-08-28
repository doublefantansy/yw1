package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface GoToRepairRecordContract {

  interface Model extends BaseContract.Model {

    void getDetail(int id, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetDetail(int id);
  }
}
