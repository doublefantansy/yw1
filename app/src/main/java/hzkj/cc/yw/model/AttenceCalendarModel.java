package hzkj.cc.yw.model;

import java.util.Map;

import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceCalendarContract;
import hzkj.cc.yw.httpservice.AttenceService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttenceCalendarModel extends BaseModel<AttenceCalendarContract.Presenter> implements
    AttenceCalendarContract.Model {

  @Override
  public void getAttenceMessage(String mobile, String attenceDateStart, String attenceDateEnd) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getAttenceMessage(mobile, attenceDateStart, attenceDateEnd)
        .subscribeOn(Schedulers.io())
        .compose(getProvider().<Response<Map<String, Map<String, AttenceInfo>>>>bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
