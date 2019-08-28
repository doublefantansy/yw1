package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

public interface ReviewContract {

  interface Model extends BaseContract.Model {

    void getReviewInfos(String status, RxAppCompatActivity appCompatActivity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetReviewInfos(String status);
  }
}
