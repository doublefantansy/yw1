package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverSingleEachContract;
import hzkj.cc.yw.model.DeliverSingleEachModel;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;
import java.util.Date;

public class DeliverSingleEachPresenter extends
    BasePresenter<DeliverSingleEachContract.View, DeliverSingleEachContract.Model> implements
    DeliverSingleEachContract.Presenter {

  private int pageNum;

  @Override
  DeliverSingleEachContract.Model createModel() {
    return new DeliverSingleEachModel();
  }

  @Override
  public void returnDataFail(String msg, Object object) {
    if (pageNum == 1) {
      getView().updateFailUi(msg, REFRESHERROR);
    } else {
      getView().updateFailUi(msg, LOADMOREERROR);
    }
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == DeliverSingleEachModel.GETDELIVERINFOS) {
      if (pageNum == 1) {
        getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
      } else {
        if (response.getCode() == RetrofitFactory.NODATA) {
          getView().updateSuccessUi(response.getData(), LOADMOREEMPTY);
        } else if (response.getCode() == RetrofitFactory.GETSUCCESS) {
          getView().updateSuccessUi(response.getData(), LOADMORESUCCESS);
        }
      }
    } else {
      getView().updateSuccessUi(response.getData(), UPDATESUCCESS);
    }
  }

  @Override
  public void startGetSingleDeliverInfos(int type, int pageNum) {
    this.pageNum = pageNum;
    getModel().startGetSingleDeliverInfos(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId(), type, pageNum);
  }

  @Override
  public void startGetDeliver(int id) {
    getModel().getDeliver(id, TimeUtil.dateToString(new Date(), TimeUtil.YMDHMS));
  }
}
