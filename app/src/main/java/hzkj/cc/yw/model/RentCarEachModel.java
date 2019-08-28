package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarEachContract;
import hzkj.cc.yw.contract.RentCarEachContract.Model;
import hzkj.cc.yw.httpservice.RentCarService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RentCarEachModel extends BaseModel<RentCarEachContract.Presenter> implements Model {

  public static final int GETRENTCARINFOS = 0;

  @Override
  public void getRentcarInfos(int type, int userId, int pageNum) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .getRentCarInfoByRentCar(type,userId, pageNum)
        .compose(getProvider().<Response<List<RentCarInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETRENTCARINFOS));
  }
}
