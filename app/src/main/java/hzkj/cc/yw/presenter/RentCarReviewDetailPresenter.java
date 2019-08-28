package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarReviewDetailContract;
import hzkj.cc.yw.contract.RentCarReviewDetailContract.Model;
import hzkj.cc.yw.contract.RentCarReviewDetailContract.Presenter;
import hzkj.cc.yw.model.RentCarReviewDetailModel;

public class RentCarReviewDetailPresenter extends
    BasePresenter<RentCarReviewDetailContract.View, RentCarReviewDetailContract.Model> implements
    Presenter {

  @Override
  Model createModel() {
    return new RentCarReviewDetailModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == RentCarReviewDetailModel.UPDATE) {
      if (response.getCode() == RetrofitFactory.UPDATESUCCESS) {
        getView().updateSuccessUi(null, UPDATESUCCESS);
      }
    } else {
      getView().updateSuccessUi(response.getData(), NORMAL);
    }
  }

  @Override
  public void startPutRentInfo(int type, RentCarInfo rentCarInfo, int userId) {
    getModel().putRentInfo(type, rentCarInfo, userId);
  }

  @Override
  public void check(String text, int type) {
    if (text.equals("")) {
      getView().updateCheckFailUi("不能为空", null);
      return;
    }
    getView().updateCheckSuccess(type);
  }

  @Override
  public void startGetDetail(int id, int type) {
    getModel().getDetail(id, type);
  }
}
