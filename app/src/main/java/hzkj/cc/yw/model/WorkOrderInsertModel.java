package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ClientContactInfo;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.ErrorGradeInfo;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.WorkOrderInsertContract;
import hzkj.cc.yw.httpservice.ClientService;
import hzkj.cc.yw.httpservice.ProjectService;
import hzkj.cc.yw.httpservice.SupplyService;
import hzkj.cc.yw.httpservice.WorkOrderService;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkOrderInsertModel extends BaseModel<WorkOrderInsertContract.Presenter> implements
    WorkOrderInsertContract.Model {

  public static final int CHOOSEPROJECT = 0;
  public static final int CHOOSECLIENT = 10;
  public static final int CHOOSECLIENTCONTACTPERSON = 100;
  public static final int CHOOSEERRORGRADES = 1000;
  public static final int CHOOSEDEVICE = 10000;
  public static final int CREATEWORKORDER = 100200;
  public static final int UPLOADPIC = 1002200;
  public static final int LOCKSUPPLY = 1002201;

  @Override
  public void chooseProject(int agencyId) {
    RetrofitFactory.getInstance().getRetrofit().create(ProjectService.class).get(agencyId)
        .compose(getProvider().<Response<List<ProjectInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CHOOSEPROJECT));
  }

  @Override
  public void chooseClient(int projectId) {
    RetrofitFactory.getInstance().getRetrofit().create(ClientService.class)
        .searchByProjectId(projectId)
        .compose(getProvider().<Response<List<ClientInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CHOOSECLIENT));
  }

  @Override
  public void chooseClientContactPerson(int clientId) {
    RetrofitFactory.getInstance().getRetrofit().create(ClientService.class)
        .searchByCustomerId(clientId)
        .compose(getProvider().<Response<List<ClientContactInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CHOOSECLIENTCONTACTPERSON));
  }

  @Override
  public void chooseErrorgGrade() {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .getErrorGrades()
        .compose(getProvider().<Response<List<ErrorGradeInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CHOOSEERRORGRADES));
  }

  @Override
  public void chooseDevice(int projectId) {
    RetrofitFactory.getInstance().getRetrofit().create(SupplyService.class)
        .getDeviceByProject(projectId)
        .compose(getProvider().<Response<List<SupplyInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CHOOSEDEVICE));
  }

  @Override
  public void createWorkOrder(WorkOrderInfo workOrderInfo) {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .createWorkOrder(workOrderInfo)
        .compose(getProvider().<Response<Integer>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), CREATEWORKORDER));
  }

  @Override
  public void uploadPic(Map<String, RequestBody> files, int id) {
    RetrofitFactory.getInstance().getRetrofit().create(WorkOrderService.class)
        .uploadPic(files, id, 0)
        .compose(getProvider().<Response<Integer>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), UPLOADPIC));
  }

  @Override
  public void lockSupply(int state, int supplyId) {
    RetrofitFactory.getInstance().getRetrofit().create(SupplyService.class)
        .lockSupply(state, supplyId)
        .compose(getProvider().<Response<Integer>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), LOCKSUPPLY));
  }
}
