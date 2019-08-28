package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuyEachContract;
import hzkj.cc.yw.model.ApplyBuyEachModel;
import hzkj.cc.yw.utils.StorageUtil;

public class ApplyBuyEachPresenter extends
    BasePresenter<ApplyBuyEachContract.View, ApplyBuyEachContract.Model> implements
    ApplyBuyEachContract.Presenter {

  boolean isRefresh;

  @Override
  ApplyBuyEachContract.Model createModel() {
    return new ApplyBuyEachModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (isRefresh) {
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
    if (isRefresh) {
      getView().updateFailUi(null, REFRESHERROR);
    } else {
      getView().updateFailUi(null, LOADMOREERROR);
    }
  }

  @Override
  public void startGetApplyBuyInfos(int type, int pageNum) {
    isRefresh = pageNum == 1 ? true : false;
    getModel().getApplyBuyInfos(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getMobile(), type, pageNum);
  }
}
