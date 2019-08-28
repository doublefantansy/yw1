package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import hzkj.cc.yw.bean.ApplyBuyInfoDetail;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuyDetailContract;
import hzkj.cc.yw.httpservice.ApplyInfoService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplyBuyDetailModel extends BaseModel<ApplyBuyDetailContract.Presenter> implements
    ApplyBuyDetailContract.Model {

  @Override
  public void getApplyBuyInfos(int id, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ApplyInfoService.class)
        .getApplyBuyDetails(id)
        .compose(fragment.<Response<List<ApplyBuyInfoDetail>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
