package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuySearchContract;
import hzkj.cc.yw.model.ApplyBuySearchModel;
import hzkj.cc.yw.utils.StorageUtil;

public class ApplyBuySearchPresenter extends
    BasePresenter<ApplyBuySearchContract.View, ApplyBuySearchContract.Model> implements
    ApplyBuySearchContract.Presenter {

  @Override
  ApplyBuySearchContract.Model createModel() {
    return new ApplyBuySearchModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), FIRSTDATAEMPTY);
    } else {
      getView().updateSuccessUi(response.getData(), NORMAL);
    }
  }

  @Override
  public void returnDataFail(String msg, Object object) {
    getView().updateFailUi(null, FIRSTNETERROR);
  }

//  @Override
//  public void startGetDataById(int id) {
//    getModel().getDataById(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
//        .getMobile(), id);
//  }

  @Override
  public void startGetDataByTime(String startTime, String endTime, int project, int status) {
    getModel().getDataByTime(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId(), startTime, endTime, project, status);
  }
}
