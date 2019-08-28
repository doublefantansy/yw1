package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import hzkj.cc.yw.bean.SingleGood;
import java.util.List;

public interface DeliverInsertContract {

  interface Model extends BaseContract.Model {

    void getUserByDepartmentId(int id, RxAppCompatActivity appCompatActivity);

    void getDeliverCompanys(RxAppCompatActivity appCompatActivity);

    void getDepartments(int id, RxAppCompatActivity appCompatActivity);

    void insertDeliverInfo(int kdgs, String kddh, String fhsj, int fhrId, int sjrid,
        List<SingleGood> singleGoods);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startUserByDepartmentId(int id, int type);

    void startGetDepartment(int id, int type);

    void startGetDeliverCompanys(int type);

    void startInsertDeliverInfo(int type, int kdgs, String kddh, String fhsj, int fhrId, int sjrid,
        List<SingleGood> singleGoods);

    void check(List<SingleGood> datas, String displayGetPerson, String displayDeliverCompany,
        String displayDeliverId);
  }
}
