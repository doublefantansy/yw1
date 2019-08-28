package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ApplyBuyReviewContract;
import hzkj.cc.yw.model.ApplyBuyReviewModel;

public class ApplyBuyReviewPresenter extends
    BasePresenter<ApplyBuyReviewContract.View, ApplyBuyReviewContract.Model> implements
    ApplyBuyReviewContract.Presenter {

  @Override
  ApplyBuyReviewContract.Model createModel() {
    return new ApplyBuyReviewModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
//        getView().updateSuccessUi(response.getData(),0);
  }
//    @Override
//    public void startGetReviews(int id) {
//        getModel().getReviews(id, (RxAppCompatActivity) getView());
//    }
}
