package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.utils.StorageUtil;
import java.util.List;
import java.util.Map;

import hzkj.cc.yw.bean.HomeBannerItem;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.HomeContract;
import hzkj.cc.yw.httpservice.HomeBannerItemService;
import hzkj.cc.yw.httpservice.HomeCountService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeModel extends BaseModel<HomeContract.Presenter> implements HomeContract.Model {

  public static final int HOMEBANNERITEM = 1;
  public static final int HOMECOUNT = 11;

  @Override
  public void getCounts(String mobile) {
    UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class);
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(HomeCountService.class)
        .getHomeCounts(mobile)
        .compose(getProvider().<Response<Map<String, Integer>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), HOMECOUNT));
  }

  @Override
  public void getBannerItems() {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(HomeBannerItemService.class)
        .getBannerItems()
        .compose(getProvider().<Response<List<HomeBannerItem>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), HOMEBANNERITEM));
  }
}
