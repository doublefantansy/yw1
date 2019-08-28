package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import java.util.Date;
import java.util.Map;

import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.AttenceInContract;
import hzkj.cc.yw.model.AttenceInModel;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;

public class AttenceInPresenter extends
    BasePresenter<AttenceInContract.View, AttenceInContract.Model> implements
    AttenceInContract.Presenter {

  public static final int LIMIT = 100;
  public static final int INISED = 101;
  public static final int OUTISED = 102;
  public static final int NONEISED = 103;

  @Override
  AttenceInContract.Model createModel() {
    return new AttenceInModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (response.getData() instanceof Map) {
      if (((Map) response.getData()).size() == 0) {
        getView().updateSuccessUi(null, EMPTY);
      } else {
        Map<String, Map<String, AttenceInfo>> mapMap = (Map<String, Map<String, AttenceInfo>>) response
            .getData();
        for (Map.Entry<String, Map<String, AttenceInfo>> entry : mapMap.entrySet()) {
          if (entry.getValue()
              .size() == 2) {
            getView().updateSuccessUi("已签到", NONEISED);
          } else {
            for (Map.Entry<String, AttenceInfo> infoEntry : entry.getValue()
                .entrySet()) {
              if (infoEntry.getKey()
                  .equals("0")) {
                getView().updateSuccessUi("已签到", INISED);
              } else {
                getView().updateSuccessUi("已签到", OUTISED);
              }
            }
          }
        }
      }
    } else {
      getView().updateSuccessUi(response.getData(), LIMIT);
    }
  }

  @Override
  public void startGetLimitTime() {
    ViseLog.d("in");
    getModel().getLimitTime();
  }

  @Override
  public void startGetAttenceStatus() {
    getModel().getAttenceStatus(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getMobile(), TimeUtil.dateToString(new Date(), TimeUtil.YMD));
  }
}
