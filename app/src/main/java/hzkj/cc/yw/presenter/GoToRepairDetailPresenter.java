package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.GoToRepairDetailContract;
import hzkj.cc.yw.model.GoToRepairDetailModel;

public class GoToRepairDetailPresenter extends
    BasePresenter<GoToRepairDetailContract.View, GoToRepairDetailContract.Model> implements
    GoToRepairDetailContract.Presenter {

  @Override
  GoToRepairDetailContract.Model createModel() {
    return new GoToRepairDetailModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }
}
