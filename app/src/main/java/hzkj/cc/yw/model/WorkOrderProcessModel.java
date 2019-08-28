package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.FaultType;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.WorkOrderDetail;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.*;
import hzkj.cc.yw.httpservice.SupplyService;
import hzkj.cc.yw.httpservice.WorkOrderService;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkOrderProcessModel extends BaseModel<WorkOrderProcessContract.Presenter> implements
    WorkOrderProcessContract.Model {

  public static final int GETOLDPARTDEVICE = 0;
  public static final int GETNEWPARTDEVICE = 1;
  public static final int GETONEWORKORDER = 2;
  public static final int UPLOADPIC = 3;
  public static final int UPDATEPROCEDURE = 4;
  public static final int GETFAULTTYPES = 6;
  public static final int UPLOADEVALUATEPIC = 61;

  @Override
  public void getPartDevice(String parentUuid, boolean isOld) {
    RetrofitFactory.getInstance().getRetrofit().create(SupplyService.class)
        .getAllPart(parentUuid)
        .compose(getProvider().<Response<List<SupplyInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new CustomSubscriber(getPresenter(), isOld ? GETOLDPARTDEVICE : GETNEWPARTDEVICE));
  }

  @Override
  public void updateProcedure(WorkOrderInfo workOrderInfo) {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .doProcedure(workOrderInfo)
        .compose(getProvider().<Response<WorkOrderInfo>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new CustomSubscriber(getPresenter(), UPDATEPROCEDURE));
  }

  @Override
  public void getOne(int id) {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .getOne(id)
        .compose(getProvider().<Response<WorkOrderInfo>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new CustomSubscriber(getPresenter(), GETONEWORKORDER));
  }

  @Override
  public void insertPic(Map<String, RequestBody> files, int id, int state, boolean isEvaluate) {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .uploadPic(files, id, state)
        .compose(getProvider().<Response<Integer>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            new CustomSubscriber(getPresenter(), isEvaluate ? UPLOADEVALUATEPIC : UPLOADPIC));
  }

  @Override
  public void updateDetail(WorkOrderDetail detail, int mode) {
  }

  @Override
  public void getFaultTypes() {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .getFaultTypes()
        .compose(getProvider().<Response<List<FaultType>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETFAULTTYPES));
  }
}
