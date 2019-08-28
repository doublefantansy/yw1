package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.ApplyBuyInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuyEachContract;
import hzkj.cc.yw.httpservice.ApplyInfoService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplyBuyEachModel extends BaseModel<ApplyBuyEachContract.Presenter> implements
    ApplyBuyEachContract.Model {

  @Override
  public void getApplyBuyInfos(String mobile, int type, int pageNum) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ApplyInfoService.class)
        .getAppleBuyInfos(mobile, type, pageNum)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<ApplyBuyInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
