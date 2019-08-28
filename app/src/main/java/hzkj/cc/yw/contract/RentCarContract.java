package hzkj.cc.yw.contract;

import android.widget.TextView;
import hzkj.cc.yw.bean.RentCarInfo;
import java.util.List;

public interface RentCarContract {

  interface Model extends BaseContract.Model {

//    void getAuthorityUsers(int moduleId);

    void getProjects(int departmentId);

    void postRentCar(RentCarInfo rentCarInfo);

    void searchUserByLike(String str);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

//    void startGetAuthorityUsers(int moduleId);

    void startGetProjects();

    void check(List<TextView> textViews);

    void startPostRentCar(RentCarInfo rentCarInfo);

    void startSearchUserByLike(String str);
  }
}
