package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Map;

import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.AttenceLimitInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceContract;
import hzkj.cc.yw.httpservice.AttenceService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttenceModel extends BaseModel<AttenceContract.Presenter> implements
    AttenceContract.Model {

  @Override
  public void getAttenceMessage(String attenceMobile, String attenceDateStart,
      String attenceDateEnd, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getAttenceMessage(attenceMobile, attenceDateStart, attenceDateEnd)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(fragment.<Response<Map<String, Map<String, AttenceInfo>>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void startAttence(AttenceLimitInfo attenceLimitInfo, String attenceMobile,
      String attenceDate, String attenceTime, String type, RxFragment fragment) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(AttenceService.class)
//                .startAttence(attenceLimitInfo, attenceMobile, attenceDate, attenceTime, type)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(fragment.<Response<Integer>>bindToLifecycle())
//                .subscribe(new CustomSubscriber<Integer>(getPresenter()));
  }
}
