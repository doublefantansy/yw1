package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vise.log.ViseLog;

import java.util.Date;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverSingleContract;
import hzkj.cc.yw.model.DeliverSingleModel;
import hzkj.cc.yw.utils.TimeUtil;

public class DeliverSinglePresenter extends
    BasePresenter<DeliverSingleContract.View, DeliverSingleContract.Model> implements
    DeliverSingleContract.Presenter {

  @Override
  DeliverSingleContract.Model createModel() {
    return new DeliverSingleModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (response.getCode() == RetrofitFactory.UPDATESUCCESS) {
      getView().updateSuccessUi("收货成功", UPDATESUCCESS);
    } else if (response.getCode() == RetrofitFactory.GETSUCCESS) {
      getView().updateSuccessUi(response.getData(), NORMAL);
    } else if (response.getCode() == RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), EMPTY);
    }
  }


  @Override
  public void startGetSingleDeliverInfos(int pageNum) {
    getModel().startGetSingleDeliverInfos(pageNum, (RxFragment) getView());
  }

  @Override
  public void startGetDeliver(int id) {
    getModel()
        .getDeliver(id, TimeUtil.dateToString(new Date(), TimeUtil.YMDHMS), (RxFragment) getView());
  }
}
