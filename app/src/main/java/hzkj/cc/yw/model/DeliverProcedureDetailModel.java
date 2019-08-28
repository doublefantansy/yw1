package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.ProcedureGoods;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverProcedureDetailContract;
import hzkj.cc.yw.httpservice.ProcedureGoodsService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverProcedureDetailModel extends
    BaseModel<DeliverProcedureDetailContract.Presenter> implements
    DeliverProcedureDetailContract.Model {

  public static final int DELIVEPROCEDURERDETAIL = 0;

  @Override
  public void getProcedureDetail(int deliverId) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ProcedureGoodsService.class)
        .get(deliverId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<ProcedureGoods>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), DELIVEPROCEDURERDETAIL));
  }
}
