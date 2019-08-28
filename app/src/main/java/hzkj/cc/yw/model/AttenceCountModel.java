package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.vise.log.ViseLog;

import java.util.List;

import hzkj.cc.yw.bean.AttenceCount;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceCountContract;
import hzkj.cc.yw.httpservice.AttenceService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttenceCountModel extends BaseModel<AttenceCountContract.Presenter> implements
    AttenceCountContract.Model {

  @Override
  public void getAttenceCount(String mobile, String startDate, String endDate,
      RxFragment fragment) {
    ViseLog.d(mobile + "|" + startDate + "|" + endDate);
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getAttenceCount(mobile, startDate, endDate)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(fragment.<Response<List<AttenceCount>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
