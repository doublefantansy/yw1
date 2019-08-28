package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.DeliverContract;
import hzkj.cc.yw.model.DeliverModel;

public class DeliverPresenter extends
    BasePresenter<DeliverContract.View, DeliverContract.Model> implements
    DeliverContract.Presenter {

  @Override
  DeliverContract.Model createModel() {
    return new DeliverModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }
}
