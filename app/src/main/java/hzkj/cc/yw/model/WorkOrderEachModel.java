package hzkj.cc.yw.model;

import com.vise.log.*;
import hzkj.cc.yw.bean.*;
import hzkj.cc.yw.config.retrofit.*;
import hzkj.cc.yw.contract.WorkOrderEachContract;
import hzkj.cc.yw.httpservice.*;
import java.util.*;
import rx.android.schedulers.*;
import rx.schedulers.*;

public class WorkOrderEachModel extends BaseModel<WorkOrderEachContract.Presenter> implements
    WorkOrderEachContract.Model {

  public static final int GETWORKORDERS = 0;

  @Override
  public void getWorkOrders(int userId, int pageNum, int type) {
    ViseLog.d(userId + "|" + pageNum + "|" + type);
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .getWorkOrders(userId, type, pageNum)
        .compose(getProvider().<Response<List<WorkOrderInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETWORKORDERS));
  }
}
