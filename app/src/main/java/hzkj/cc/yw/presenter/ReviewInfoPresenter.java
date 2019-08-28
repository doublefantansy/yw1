package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ReviewInfoContract;
import hzkj.cc.yw.contract.ReviewInfoContract.Model;
import hzkj.cc.yw.model.ReviewInfoModel;

public class ReviewInfoPresenter extends
    BasePresenter<ReviewInfoContract.View, ReviewInfoContract.Model> implements
    ReviewInfoContract.Presenter {




  @Override
  Model createModel() {
    return new ReviewInfoModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }
}
