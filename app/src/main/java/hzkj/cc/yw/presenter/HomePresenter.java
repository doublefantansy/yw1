package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.HomeContract;
import hzkj.cc.yw.model.HomeModel;
import hzkj.cc.yw.utils.StorageUtil;

public class HomePresenter extends BasePresenter<HomeContract.View, HomeContract.Model> implements
    HomeContract.Presenter {

  @Override
  HomeContract.Model createModel() {
    return new HomeModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() != RetrofitFactory.NODATA) {
      getView().updateSuccessUi(response.getData(), tag);
    }
  }

  @Override
  public void startGetCounts() {
    getModel().getCounts(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId() + "");
  }

  @Override
  public void startGetBannerItems() {
    getModel().getBannerItems();
  }
}
