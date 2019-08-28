package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ApplyBuyDetailContract;
import hzkj.cc.yw.model.ApplyBuyDetailModel;

public class ApplyBuyDetailPresenter extends
    BasePresenter<ApplyBuyDetailContract.View, ApplyBuyDetailContract.Model> implements
    ApplyBuyDetailContract.Presenter {

  @Override
  ApplyBuyDetailContract.Model createModel() {
    return new ApplyBuyDetailModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), 0);
  }

  @Override
  public void startGetApplyBuyInfos(int id) {
    getModel().getApplyBuyInfos(id, (RxFragment) getView());
  }
}
