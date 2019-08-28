package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ApplyBuyRecordContract;
import hzkj.cc.yw.model.ApplyBuyRecordModel;

public class ApplyBuyRecordPresenter extends
    BasePresenter<ApplyBuyRecordContract.View, ApplyBuyRecordContract.Model> implements
    ApplyBuyRecordContract.Presenter {

  @Override
  ApplyBuyRecordContract.Model createModel() {
    return new ApplyBuyRecordModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), 0);
  }

  @Override
  public void startGetRecord(int id) {
    getModel().getRecord(id, (RxFragment) getView());
  }
}
