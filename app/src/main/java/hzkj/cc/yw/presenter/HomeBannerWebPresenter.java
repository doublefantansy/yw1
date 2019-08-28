package hzkj.cc.yw.presenter;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.HomeBannerWebContract;
import hzkj.cc.yw.model.HomeBannerWebModel;

public class HomeBannerWebPresenter extends
    BasePresenter<HomeBannerWebContract.View, HomeBannerWebContract.Model> implements
    HomeBannerWebContract.Presenter {

  @Override
  HomeBannerWebContract.Model createModel() {
    return new HomeBannerWebModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
  }
}
