package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarReviewContract;
import hzkj.cc.yw.contract.RentCarReviewContract.Model;
import hzkj.cc.yw.contract.RentCarReviewContract.Presenter;
import hzkj.cc.yw.model.RentCarReviewModel;

public class RentCarReviewPresenter extends
    BasePresenter<RentCarReviewContract.View, RentCarReviewContract.Model> implements Presenter {

  private int pageNum;

  @Override
  Model createModel() {
    return new RentCarReviewModel();
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
  public void startGetAll(int type, int pageNum) {
    this.pageNum = pageNum;
    getModel().getAll(type, pageNum);
  }
}
