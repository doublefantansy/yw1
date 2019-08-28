package hzkj.cc.yw.model;

import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceInContract;
import hzkj.cc.yw.httpservice.AttenceService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttenceInModel extends BaseModel<AttenceInContract.Presenter> implements
    AttenceInContract.Model {

  @Override
  public void getLimitTime() {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getLimitTime()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getAttenceStatus(String mobile, String today) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getAttenceMessage(mobile, today, today)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
