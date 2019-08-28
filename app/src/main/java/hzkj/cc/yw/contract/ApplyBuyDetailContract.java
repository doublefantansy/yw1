package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface ApplyBuyDetailContract {

  interface Model extends BaseContract.Model {

    void getApplyBuyInfos(int id, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetApplyBuyInfos(int id);
  }
}
