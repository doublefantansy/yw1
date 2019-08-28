package hzkj.cc.yw.presenter;

import java.util.Date;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverProcedureEachContract;
import hzkj.cc.yw.model.DeliverProcedureEachModel;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;

public class DeliverProcedureEachPresenter extends
    BasePresenter<DeliverProcedureEachContract.View, DeliverProcedureEachContract.Model> implements
    DeliverProcedureEachContract.Presenter {

  int pageNum;
//  boolean isFirst;

  @Override
  DeliverProcedureEachContract.Model createModel() {
    return new DeliverProcedureEachModel();
  }

  @Override
  public void returnDataFail(String msg, Object object) {
//    super.returnDataFail(msg, object);
    if (pageNum == 1) {
      getView().updateFailUi(msg, REFRESHERROR);
    } else {
      getView().updateFailUi(msg, LOADMOREERROR);
    }
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == DeliverProcedureEachModel.GETDELIVERINFOS) {
      if (pageNum > 1) {
        if (response.getCode() == RetrofitFactory.NODATA) {
          getView().updateSuccessUi(response.getData(), LOADMOREEMPTY);
        } else if (response.getCode() == RetrofitFactory.GETSUCCESS) {
          getView().updateSuccessUi(response.getData(), LOADMORESUCCESS);
        }
      } else {
        getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
      }
    } else if (tag == DeliverProcedureEachModel.GETDELIVER) {
      if (response.getCode() == RetrofitFactory.UPDATESUCCESS) {
        getView().updateSuccessUi(null, UPDATESUCCESS);
      }
    }
  }

  @Override
  public void startGetProcedureDeliverInfo(int type, int pageNum) {
    this.pageNum = pageNum;
    getModel().getProcedureDeliverInfo(type, pageNum);
  }

  @Override
  public void startGetDeliver(int deliverId) {
    getModel().getDeliver(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId(), deliverId, TimeUtil.dateToString(new Date(), TimeUtil.YMDHMS));
  }
}
