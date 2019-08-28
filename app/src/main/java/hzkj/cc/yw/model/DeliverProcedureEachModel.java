package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverProcedureEachContract;
import hzkj.cc.yw.httpservice.DeliverService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverProcedureEachModel extends
    BaseModel<DeliverProcedureEachContract.Presenter> implements
    DeliverProcedureEachContract.Model {

  public static final int GETDELIVERINFOS = 0;
  public static final int GETDELIVER = 1;

  @Override
  public void getProcedureDeliverInfo(int type, int pageNum) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(DeliverService.class)
        .getSome(type, pageNum)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<DeliverInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), GETDELIVERINFOS));
  }

  @Override
  public void getDeliver(int userId, int deliverId, String nowTime) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(DeliverService.class)
        .putOne(userId, deliverId, nowTime)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<Integer>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), GETDELIVER));
  }
}
