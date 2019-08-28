package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

public interface GoToRepairReviewContract {

  interface Model extends BaseContract.Model {

    void getReviews(int id, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetReviews(int id);
  }
}
