package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.GoToRepairReviewContract;
import hzkj.cc.yw.model.GoToRepairReviewModel;

public class GoToRepairReviewPresenter extends
    BasePresenter<GoToRepairReviewContract.View, GoToRepairReviewContract.Model> implements
    GoToRepairReviewContract.Presenter {

  @Override
  GoToRepairReviewContract.Model createModel() {
    return new GoToRepairReviewModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
  }

  @Override
  public void startGetReviews(int id) {
    getModel().getReviews(id, (RxFragment) getView());
  }
}
