package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarEachContract;
import hzkj.cc.yw.contract.RentCarEachContract.Model;
import hzkj.cc.yw.contract.RentCarEachContract.Presenter;
import hzkj.cc.yw.model.RentCarEachModel;
import hzkj.cc.yw.utils.StorageUtil;

public class RentCarEachPresenter extends
    BasePresenter<RentCarEachContract.View, RentCarEachContract.Model> implements Presenter {

  int pageNum;

  @Override
  Model createModel() {
    return new RentCarEachModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == RentCarEachModel.GETRENTCARINFOS) {
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
  }


  @Override
  public void startGetRentcarInfos(int type, int pageNum) {
    this.pageNum = pageNum;
    getModel()
        .getRentcarInfos(type,StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getUserId(),
            pageNum);
  }
}
