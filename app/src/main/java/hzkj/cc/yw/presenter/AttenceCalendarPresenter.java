package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.AttenceCalendarContract;
import hzkj.cc.yw.model.AttenceCalendarModel;
import hzkj.cc.yw.utils.StorageUtil;

public class AttenceCalendarPresenter extends
    BasePresenter<AttenceCalendarContract.View, AttenceCalendarContract.Model> implements
    AttenceCalendarContract.Presenter {

  public static final int GETATTENCESUCCESS = 0;

  @Override
  AttenceCalendarContract.Model createModel() {
    return new AttenceCalendarModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    getView().updateSuccessUi(response.getData(), GETATTENCESUCCESS);
  }

  @Override
  public void startGetAttenceMessage(String attenceDateStart, String attenceDateEnd) {
    getModel().getAttenceMessage(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getMobile(), attenceDateStart, attenceDateEnd);
  }
}
