package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairEachContract;
import hzkj.cc.yw.model.GoToRepairEachModel;
import hzkj.cc.yw.utils.StorageUtil;

public class GoToRepairEachPresenter extends
    BasePresenter<GoToRepairEachContract.View, GoToRepairEachContract.Model> implements
    GoToRepairEachContract.Presenter {

  int pageNum;

  @Override
  GoToRepairEachContract.Model createModel() {
    return new GoToRepairEachModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
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
  public void returnDataFail(String msg, Object object) {
    if (pageNum == 1) {
      getView().updateFailUi(null, REFRESHERROR);
    } else {
      getView().updateFailUi(null, LOADMOREERROR);
    }
  }

  @Override
  public void startGetRepairInfo(int type, int pageNum) {
    this.pageNum = pageNum;
    getModel().getRepairInfo(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId(), type, pageNum);
  }
}
