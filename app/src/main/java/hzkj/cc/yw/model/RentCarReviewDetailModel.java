package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarReviewDetailContract;
import hzkj.cc.yw.contract.RentCarReviewDetailContract.Model;
import hzkj.cc.yw.httpservice.RentCarService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RentCarReviewDetailModel extends
    BaseModel<RentCarReviewDetailContract.Presenter> implements Model {

  public static final int UPDATE = 0;
  public static final int DETAIL = 10;
  @Override
  public void putRentInfo(int type, RentCarInfo rentCarInfo, int userId) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .putOne(type, rentCarInfo, userId)
        .compose(getProvider().<Response<Integer>>bindToLifecycle()).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), UPDATE));
  }

  @Override
  public void getDetail(int id,int type) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .getOne(id,type)
        .compose(getProvider().<Response<RentCarInfo>>bindToLifecycle()).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), DETAIL));
  }
}
