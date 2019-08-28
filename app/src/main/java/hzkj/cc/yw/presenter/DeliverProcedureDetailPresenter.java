package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.DeliverProcedureDetailContract;
import hzkj.cc.yw.model.DeliverProcedureDetailModel;

public class DeliverProcedureDetailPresenter extends
    BasePresenter<DeliverProcedureDetailContract.View, DeliverProcedureDetailContract.Model> implements
    DeliverProcedureDetailContract.Presenter {

  @Override
  DeliverProcedureDetailContract.Model createModel() {
    return new DeliverProcedureDetailModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == DeliverProcedureDetailModel.DELIVEPROCEDURERDETAIL) {
      getView().updateSuccessUi(response.getData(), tag);
    }
  }

  @Override
  public void startGetProcedureDetail(int deliverId) {
    getModel().getProcedureDetail(deliverId);
  }
}
