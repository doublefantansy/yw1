package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ClientEditContract;
import hzkj.cc.yw.model.ClientEditModel;

public class ClientEditPresenter extends
    BasePresenter<ClientEditContract.View, ClientEditContract.Model> implements
    ClientEditContract.Presenter {

  @Override
  ClientEditContract.Model createModel() {
    return new ClientEditModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), 0);
  }

  @Override
  public void startGetProjects() {
    getModel().getProjects((RxAppCompatActivity) getView());
  }

  @Override
  public void startGetClientTypeInfos() {
    getModel().getClientTypeInfos((RxAppCompatActivity) getView());
  }

  @Override
  public void startUpdateClientInfo(ClientInfo clientInfo) {
    getModel().updateClientInfo(clientInfo, (RxAppCompatActivity) getView());
  }
}
