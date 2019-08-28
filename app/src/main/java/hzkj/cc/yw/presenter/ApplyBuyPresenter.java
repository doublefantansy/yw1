package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.ApplyBuyContract;
import hzkj.cc.yw.model.ApplyBuyModel;
import hzkj.cc.yw.utils.StorageUtil;

public class ApplyBuyPresenter extends
    BasePresenter<ApplyBuyContract.View, ApplyBuyContract.Model> implements
    ApplyBuyContract.Presenter {

  boolean isRefresh;
  boolean isFirst;
  boolean singleSearch;

  @Override
  ApplyBuyContract.Model createModel() {
    return new ApplyBuyModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), tag);
  }

  @Override
  public void returnDataFail(String msg, Object object) {
    super.returnDataFail(msg, object);
    if (isRefresh) {
      getView().updateFailUi(null, 0);
    } else {
      getView().updateFailUi(null, 1);
    }
//        super.returnDataFail(msg, object);
  }


  @Override
  public void checkDate(String startDate, String endDate, String project, String status) {
    if (startDate.equals("") | endDate.equals("") | project.equals("") | status.equals("")) {
      getView().updateCheckFailUi("不能为空", 0);
    } else if (startDate.compareTo(endDate) > 0) {
      getView().updateCheckFailUi("起始时间不能晚于结束时间", 1);
    } else {
      getView().updateCheckSuccess(null);
    }
  }

  @Override
  public void startGetProjects() {
    getModel().getProjects(
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptId());
  }
}
