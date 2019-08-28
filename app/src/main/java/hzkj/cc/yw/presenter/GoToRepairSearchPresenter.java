package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairSearchContract;
import hzkj.cc.yw.model.GoToRepairSearchModel;

public class GoToRepairSearchPresenter extends
    BasePresenter<GoToRepairSearchContract.View, GoToRepairSearchContract.Model> implements
    GoToRepairSearchContract.Presenter {

  @Override
  public void startSearchByDate(int type, String startTime, String endTime) {
    getModel().searchByDate(type, startTime, endTime);
  }

  @Override
  public void startSearchByProject(int id) {
    getModel().searchByProject(id);
  }

  @Override
  GoToRepairSearchContract.Model createModel() {
    return new GoToRepairSearchModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), EMPTY);
    } else {
      getView().updateSuccessUi(response.getData(), NORMAL);
    }
  }

  @Override
  public void startSearchByRepairId(int id) {
    getModel().getSearchByRepairId(id);
  }
}
