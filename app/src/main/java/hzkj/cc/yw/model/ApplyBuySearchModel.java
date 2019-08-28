package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.ApplyBuyInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuySearchContract;
import hzkj.cc.yw.httpservice.ApplyInfoService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplyBuySearchModel extends BaseModel<ApplyBuySearchContract.Presenter> implements
    ApplyBuySearchContract.Model {

//  @Override
//  public void getDataById(String mobile, int id) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ApplyInfoService.class)
//        .searchById(id, mobile)
//        .compose(getProvider().<Response<List<ApplyBuyInfo>>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
//  }

  @Override
  public void getDataByTime(int userId, String startTime, String endTime, int project,
      int status) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ApplyInfoService.class)
        .searchByTime(userId, startTime, endTime, project, status)
        .compose(getProvider().<Response<List<ApplyBuyInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
