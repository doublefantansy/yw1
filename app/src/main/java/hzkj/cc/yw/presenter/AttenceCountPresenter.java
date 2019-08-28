package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.AttenceCountContract;
import hzkj.cc.yw.model.AttenceCountModel;
import hzkj.cc.yw.utils.StorageUtil;

public class AttenceCountPresenter extends
    BasePresenter<AttenceCountContract.View, AttenceCountContract.Model> implements
    AttenceCountContract.Presenter {

  @Override
  AttenceCountContract.Model createModel() {
    return new AttenceCountModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    getView().updateSuccessUi(response.getData(), NORMAL);
  }

  @Override
  public void startGetAttenceCount(String startDate, String endDate) {
    getModel().getAttenceCount(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getMobile(), startDate, endDate, (RxFragment) getView());
  }
}
