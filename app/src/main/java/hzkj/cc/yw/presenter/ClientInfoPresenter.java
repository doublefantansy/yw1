package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ClientInfoContract;
import hzkj.cc.yw.model.ClientInfoModel;

public class ClientInfoPresenter extends
    BasePresenter<ClientInfoContract.View, ClientInfoContract.Model> implements
    ClientInfoContract.Presenter {

  @Override
  ClientInfoContract.Model createModel() {
    return new ClientInfoModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }

  @Override
  public void startGetOne(int id) {
    getModel().getOne(id);
  }
}
