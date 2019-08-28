package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.AttenceInfo;

public interface AttenceMapContract {

  interface Model extends BaseContract.Model {

    void getDepartmentCoordinate(int userId, RxAppCompatActivity activity);

    void attence(AttenceInfo attenceInfo, RxAppCompatActivity activity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startLocate();

    void endLocate();

    void startGetDepartmentCoordinate();

    void startAttence(String antiTude, String longiTude, int type, String description,
        String distance, String limit);

    void checkDistance(double distance, int limitDistance);
  }
}
