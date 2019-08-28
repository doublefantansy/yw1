package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairRecordContract;
import hzkj.cc.yw.model.GoToRepairRecordModel;

public class GoToRepairRecordPresenter extends
    BasePresenter<GoToRepairRecordContract.View, GoToRepairRecordContract.Model> implements
    GoToRepairRecordContract.Presenter {

  @Override
  GoToRepairRecordContract.Model createModel() {
    return new GoToRepairRecordModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), EMPTY);
    } else {
      getView().updateSuccessUi(response.getData(), REFRESHSUCCESS);
    }
  }

  @Override
  public void startGetDetail(int id) {
    ViseLog.d(id);
    getModel().getDetail(id, (RxFragment) getView());
  }
}
