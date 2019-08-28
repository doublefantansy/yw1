package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SingleGood;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverSingleDetailContract;
import hzkj.cc.yw.httpservice.SingleGoodService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverSingleDetailModel extends
    BaseModel<DeliverSingleDetailContract.Presenter> implements DeliverSingleDetailContract.Model {

  @Override
  public void getSingleGoods(int id, RxAppCompatActivity activity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(SingleGoodService.class)
        .get(id)
        .compose(activity.<Response<List<SingleGood>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
