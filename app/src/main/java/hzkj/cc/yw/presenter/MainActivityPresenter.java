package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.MainActivityContract;
import hzkj.cc.yw.model.MainActivityModel;

/**
 * @author cc
 */
public class MainActivityPresenter extends
    BasePresenter<MainActivityContract.View, MainActivityContract.Model> implements
    MainActivityContract.Presenter {

  @Override
  MainActivityContract.Model createModel() {
    return new MainActivityModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), 0);
  }
}
