package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverSingleContract;
import hzkj.cc.yw.httpservice.SingleDeliverService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverSingleModel extends BaseModel<DeliverSingleContract.Presenter> implements
    DeliverSingleContract.Model {

  @Override
  public void startGetSingleDeliverInfos(int pageNum, RxFragment fragment) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(DeliverService.class)
//                .getSingleDeliverInfos(pageNum)
//                .compose(fragment.<Response<List<DeliverInfo>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CustomSubscriber(getPresenter()));
  }

  @Override
  public void getDeliver(int id, String nowTime, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(SingleDeliverService.class)
        .put(id, nowTime)
        .compose(fragment.<Response<Integer>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
