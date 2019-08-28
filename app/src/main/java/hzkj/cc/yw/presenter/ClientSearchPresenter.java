package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.ClientSearchContract;
import hzkj.cc.yw.contract.ClientSearchContract.Model;
import hzkj.cc.yw.model.ClientSearchModel;

public class ClientSearchPresenter extends
    BasePresenter<ClientSearchContract.View, ClientSearchContract.Model> implements
    ClientSearchContract.Presenter {

  private int pageNum;

  @Override
  Model createModel() {
    return new ClientSearchModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (pageNum == 1) {
      getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
    } else {
      getView().updateSuccessUi(response.getData(), LOADMORESUCCESS);
    }
  }

  @Override
  public void startSearchByProject(int projectId, int pageNum) {
    this.pageNum = pageNum;
    getModel().searchByProject(projectId, pageNum);
  }
}
