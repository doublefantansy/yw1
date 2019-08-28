package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ClientManageContract;
import hzkj.cc.yw.contract.ClientManageContract.Model;
import hzkj.cc.yw.contract.ClientManageContract.Presenter;
import hzkj.cc.yw.model.ClientManageModel;

public class ClientManagePresenter extends
    BasePresenter<ClientManageContract.View, ClientManageContract.Model> implements Presenter {

  int pageNum;

  @Override
  Model createModel() {
    return new ClientManageModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == ClientManageModel.GETPROJECTS) {
      getView().updateSuccessUi(response.getData(), tag);
    }
  }

  @Override
  public void startGetProjects(int agencyId) {
    getModel().getProjects(agencyId);
  }
}
