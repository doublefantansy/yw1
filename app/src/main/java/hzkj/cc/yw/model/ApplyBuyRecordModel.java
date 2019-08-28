package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuyRecordContract;
import hzkj.cc.yw.httpservice.ApplyInfoService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplyBuyRecordModel extends BaseModel<ApplyBuyRecordContract.Presenter> implements
    ApplyBuyRecordContract.Model {

  @Override
  public void getRecord(int id, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ApplyInfoService.class)
        .getApplyBuyReviews(id)
        .compose(fragment.<Response<List<Procedure>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
