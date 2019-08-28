package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vise.log.ViseLog;

import java.util.Date;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverProcedureContract;
import hzkj.cc.yw.model.DeliverProcedureModel;
import hzkj.cc.yw.utils.TimeUtil;

public class DeliverProcedurePresenter extends
    BasePresenter<DeliverProcedureContract.View, DeliverProcedureContract.Model> implements
    DeliverProcedureContract.Presenter {

  @Override
  DeliverProcedureContract.Model createModel() {
    return new DeliverProcedureModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (response.getCode() == RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), EMPTY);
    } else {
      getView().updateSuccessUi(response.getData(), NORMAL);
    }
  }

  @Override
  public void returnDataFail(String msg, Object object) {
//        if (isRefresh) {
//            getView().updateFailUi(msg, REFRESHSUCCESS);
//        } else {
//            getView().updateFailUi(msg, LOADMORESUCCESS);
//        }
  }

  @Override
  public void startGetDeliverInfos(int pageNum) {
    getModel().getDeliverInfos(pageNum, (RxFragment) getView());
  }

  @Override
  public void startGetProcedureDeliver(DeliverInfo deliverInfo) {
    getModel().getProcedureDeliver(deliverInfo, TimeUtil.dateToString(new Date(), TimeUtil.YMDHMS),
        (RxFragment) getView());
  }
}
