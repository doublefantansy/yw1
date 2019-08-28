package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.RentCarManageContract;
import hzkj.cc.yw.model.RentCarManageModel;
import hzkj.cc.yw.utils.StorageUtil;

public class RentCarManagePresenter extends
    BasePresenter<RentCarManageContract.View, RentCarManageContract.Model> implements
    RentCarManageContract.Presenter {

  @Override
  RentCarManageContract.Model createModel() {
    return new RentCarManageModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
//        ViseLog.d(response);
    getView().updateSuccessUi(response.getData(), 0);
  }

  @Override
  public void startGetAllRentCarInfo() {
    getModel().getAllRentCarInfo(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getMobile(), (RxAppCompatActivity) getView());
  }
}
