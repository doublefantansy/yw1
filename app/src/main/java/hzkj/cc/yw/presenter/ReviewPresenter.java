package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ReviewContract;
import hzkj.cc.yw.model.ReviewModel;

public class ReviewPresenter extends
    BasePresenter<ReviewContract.View, ReviewContract.Model> implements ReviewContract.Presenter {

  @Override
  ReviewContract.Model createModel() {
    return new ReviewModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (response.getCode() == 300 | response.getCode() == 301) {
      getView().updateSuccessUi(response.getData(), 0);
    }
  }

  @Override
  public void startGetReviewInfos(String status) {
    getModel().getReviewInfos(status, (RxAppCompatActivity) getView());
  }
}
