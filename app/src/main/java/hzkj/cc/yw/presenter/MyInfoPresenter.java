package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.MyInfoContract;
import hzkj.cc.yw.model.MyInfoModel;

public class MyInfoPresenter extends
    BasePresenter<MyInfoContract.View, MyInfoContract.Model> implements MyInfoContract.Presenter {

  @Override
  MyInfoContract.Model createModel() {
    return new MyInfoModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
//        if (response.getData() instanceof Integer) {
//            getModel().getIconUrl((RxFragment) getView());
//        } else {
    getView().updateSuccessUi(response.getData(), 1);
//        }
  }
}
//
//    @Override
//    public void startGetIconUrl() {
//        getModel().getIconUrl((RxFragment) getView());
//    }
//}
