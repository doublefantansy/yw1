package hzkj.cc.yw.presenter;

import com.vise.log.*;
import hzkj.cc.yw.bean.*;
import hzkj.cc.yw.config.retrofit.*;
import hzkj.cc.yw.contract.*;
import hzkj.cc.yw.model.*;
import hzkj.cc.yw.utils.*;

public class WorkOrderEachPresenter extends
    BasePresenter<WorkOrderEachContract.View, WorkOrderEachContract.Model> implements
    WorkOrderEachContract.Presenter {

  int pageNum;

  @Override
  public void returnDataSuccess(hzkj.cc.yw.bean.Response response, int tag) {
    ViseLog.d(response);
    if (pageNum == 1) {
      getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
    } else {
      if (response.getCode() == RetrofitFactory.NODATA) {
        getView().updateSuccessUi(response.getData(), LOADMOREEMPTY);
      } else {
        getView().updateSuccessUi(response.getData(), LOADMORESUCCESS);
      }
    }
  }

  @Override
  WorkOrderEachContract.Model createModel() {
    return new WorkOrderEachModel();
  }

  @Override
  public void startGetWorkOrders(boolean isRefresh, int type) {
    if (isRefresh) {
      pageNum = 1;
    } else {
      pageNum++;
    }
    getModel()
        .getWorkOrders(
            StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getUserId(),pageNum, type);
  }
}
