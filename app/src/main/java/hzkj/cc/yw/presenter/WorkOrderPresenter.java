package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.WorkOrderContract;
import hzkj.cc.yw.contract.WorkOrderContract.Model;
import hzkj.cc.yw.model.WorkOrderModel;

public class WorkOrderPresenter extends BasePresenter<WorkOrderContract.View, WorkOrderContract.Model> implements WorkOrderContract.Presenter {

  @Override
  Model createModel() {
    return new WorkOrderModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }
}
