package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairContract;
import hzkj.cc.yw.model.GoToRepairModel;

public class GoToRepairPresenter extends
    BasePresenter<GoToRepairContract.View, GoToRepairContract.Model> implements
    GoToRepairContract.Presenter {

  @Override
  GoToRepairContract.Model createModel() {
    return new GoToRepairModel();
  }

  @Override
  public void checkDrawer(String startDate, String endDate, int id) {
    ViseLog.d(startDate + "|" + endDate + "|" + id);
    if (id == -1) {
      getView().updateCheckFailUi("请选择一种时间类型", 0);
    } else if (startDate.isEmpty() | endDate.isEmpty()) {
      getView().updateCheckFailUi("时间不能为空", 1);
    } else if (startDate.compareTo(endDate) > 0) {
      getView().updateCheckFailUi("起始时间不能晚于结束时间", 1);
    } else {
      getView().updateCheckSuccess(null);
    }
  }

  @Override
  public void startGetAgencyList(int id) {
    getModel().getAgencyList(id);
  }

  @Override
  public void startGetProjectList(int id) {
    getModel().getProjectList(id);
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == RetrofitFactory.DEPARTMENTEMPTY) {
      getView().updateSuccessUi(response.getData(), DEPARTMENTEMPTY);
    } else if (response.getCode() == RetrofitFactory.PROJECTSUCCESS) {
      getView().updateSuccessUi(response.getData(), PROJECTSUCCESS);
    } else if (response.getCode() == RetrofitFactory.DEPARTSUCCESS) {
      getView().updateSuccessUi(response.getData(), DEPARTSUCCESS);
    }
  }
}
