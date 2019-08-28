package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.contract.DeliverProcedureContract;

public class DeliverProcedureModel extends BaseModel<DeliverProcedureContract.Presenter> implements
    DeliverProcedureContract.Model {

  @Override
  public void getDeliverInfos(int pageNum, RxFragment fragment) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(DeliverService.class)
//                .getSome(pageNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(fragment.<Response<List<DeliverInfo>>>bindToLifecycle())
//                .subscribe(new CustomSubscriber(getPresenter(),0));
  }

  @Override
  public void getProcedureDeliver(DeliverInfo deliverInfo, String nowTime, RxFragment fragment) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(DeliverService.class)
//                .getProcedureDeliver(deliverInfo, nowTime)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(fragment.<Response<Integer>>bindToLifecycle())
//                .subscribe(new CustomSubscriber(getPresenter(),0));
  }
}
