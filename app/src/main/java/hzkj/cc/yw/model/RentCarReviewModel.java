package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarReviewContract;
import hzkj.cc.yw.contract.RentCarReviewContract.Model;
import hzkj.cc.yw.httpservice.RentCarService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RentCarReviewModel extends BaseModel<RentCarReviewContract.Presenter> implements
    Model {

  public static final int REVIEWINFOS = 0;

  @Override
  public void getAll(int type, int pageNum) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .getReviewRentCarInfo(type, pageNum)
        .compose(getProvider().<Response<List<RentCarInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), REVIEWINFOS));
  }
}
