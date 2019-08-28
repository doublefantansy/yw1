package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.DeliverSingleDetailContract;
import hzkj.cc.yw.model.DeliverSingleDetailModel;

public class DeliverSingleDetailPresenter extends
    BasePresenter<DeliverSingleDetailContract.View, DeliverSingleDetailContract.Model> implements
    DeliverSingleDetailContract.Presenter {

  @Override
  DeliverSingleDetailContract.Model createModel() {
    return new DeliverSingleDetailModel();
  }


  @Override
  public void returnDataSuccess(Response response, int tag) {
    getView().updateSuccessUi(response.getData(), NORMAL);
  }

  @Override
  public void startGetSingleGoods(int id) {
    getModel().getSingleGoods(id, (RxAppCompatActivity) getView());
  }
}
