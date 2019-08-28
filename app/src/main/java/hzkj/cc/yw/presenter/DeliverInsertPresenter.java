package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vise.log.ViseLog;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SingleGood;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverInsertContract;
import hzkj.cc.yw.model.DeliverInsertModel;
import java.util.List;

public class DeliverInsertPresenter extends
    BasePresenter<DeliverInsertContract.View, DeliverInsertContract.Model> implements
    DeliverInsertContract.Presenter {

  public static final int USERNOTEND = 10;
  public static final int USER = 0;
  public static final int DELIVERCOMPANY = 1;
  public static final int DEPARTMENT = 2;
  public static final int DEPARTMENTEND = 3;
  public static final int USEREMTPY = 4;
  public static final int INSERTDELIVER = 5;

  @Override
  public void startInsertDeliverInfo(int type, int kdgs, String kddh, String fhsj, int fhrId,
      int sjrid, List<SingleGood> singleGoods) {
    this.type = type;
    getModel().insertDeliverInfo(kdgs, kddh, fhsj, fhrId, sjrid, singleGoods);
  }


  @Override
  DeliverInsertContract.Model createModel() {
    return new DeliverInsertModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    switch (type) {
      case USER: {
        if (response.getCode() == RetrofitFactory.NODATA) {
          getView().updateSuccessUi(response.getData(), USEREMTPY);
        } else {
          getView().updateSuccessUi(response.getData(), USER);
        }
        break;
      }
      case USERNOTEND: {
        getView().updateSuccessUi(response.getData(), USERNOTEND);
        break;
      }
      case DELIVERCOMPANY: {
        getView().updateSuccessUi(response.getData(), DELIVERCOMPANY);
        break;
      }
      case INSERTDELIVER: {
        getView().updateSuccessUi(response.getData(), INSERTDELIVER);
        break;
      }
      case DEPARTMENT: {
        if (response.getCode() == RetrofitFactory.DEPARTMENTEMPTY) {
          getView().updateSuccessUi(response.getData(), DEPARTMENTEND);
        } else {
          getView().updateSuccessUi(response.getData(), DEPARTMENT);
        }
        break;
      }
    }
  }

  @Override
  public void startUserByDepartmentId(int id, int type) {
    this.type = type;
    getModel().getUserByDepartmentId(id, (RxAppCompatActivity) getView());
  }

  @Override
  public void startGetDepartment(int id, int type) {
    this.type = type;
    getModel().getDepartments(id, (RxAppCompatActivity) getView());
  }

  @Override
  public void startGetDeliverCompanys(int type) {
    this.type = type;
    getModel().getDeliverCompanys((RxAppCompatActivity) getView());
  }


  @Override
  public void check(List<SingleGood> datas, String displayGetPerson, String displayDeliverCompany,
      String displayDeliverId) {
    if (datas.size() == 0) {
      getView().updateCheckFailUi("请添加至少一件货物", 0);
    } else if (displayGetPerson.equals("")) {
      getView().updateCheckFailUi("请选择收货人", 0);
    } else if (displayDeliverCompany.equals("")) {
      getView().updateCheckFailUi("请选择快递公司", 0);
    } else if (displayDeliverId.equals("")) {
      getView().updateCheckFailUi("请填写快递单号", 0);
    } else {
      getView().updateCheckSuccess(null);
    }
  }
}
