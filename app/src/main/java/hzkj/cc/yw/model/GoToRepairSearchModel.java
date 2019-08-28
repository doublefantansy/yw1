package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.RepairInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairSearchContract;
import hzkj.cc.yw.httpservice.GoToRepairService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoToRepairSearchModel extends BaseModel<GoToRepairSearchContract.Presenter> implements
    GoToRepairSearchContract.Model {

  @Override
  public void getSearchByRepairId(int id) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .searchByRepairId(id)
        .compose(getProvider().<Response<List<RepairInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void searchByDate(int type, String startTime, String endTime) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .searchByDate(startTime, endTime, type)
        .compose(getProvider().<Response<List<RepairInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void searchByProject(int id) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .searchByProjectId(id)
        .compose(getProvider().<Response<List<RepairInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
