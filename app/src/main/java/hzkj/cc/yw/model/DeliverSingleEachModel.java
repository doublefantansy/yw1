package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverSingleEachContract;
import hzkj.cc.yw.httpservice.SingleDeliverService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverSingleEachModel extends
    BaseModel<DeliverSingleEachContract.Presenter> implements DeliverSingleEachContract.Model {

  public static final int GETDELIVER = 0;
  public static final int GETDELIVERINFOS = 10;
  @Override
  public void startGetSingleDeliverInfos(int userId, int type, int pageNum) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(SingleDeliverService.class)
        .get(userId, type, pageNum)
        .compose(getProvider().<Response<List<DeliverInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETDELIVERINFOS));
  }

  @Override
  public void getDeliver(int id, String nowTime) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(SingleDeliverService.class)
        .put(id, nowTime)
        .compose(getProvider().<Response<Integer>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETDELIVER));
  }
}
