package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ClientContract;
import hzkj.cc.yw.contract.ClientContract.Model;
import hzkj.cc.yw.model.ClientModel;

public class ClientPresenter extends
    BasePresenter<ClientContract.View, ClientContract.Model> implements ClientContract.Presenter {

  private int pageNum;

  @Override
  Model createModel() {
    return new ClientModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (pageNum == 1) {
      getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
    } else {
      getView().updateSuccessUi(response.getData(), LOADMORESUCCESS);
    }
  }

  @Override
  public void startGetAll(int pageNum) {
    this.pageNum = pageNum;
    getModel().getAll(pageNum);
  }
}
