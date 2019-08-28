package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import hzkj.cc.yw.bean.RepairInfoDetail;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairRecordContract;
import hzkj.cc.yw.httpservice.GoToRepairService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoToRepairRecordModel extends BaseModel<GoToRepairRecordContract.Presenter> implements
    GoToRepairRecordContract.Model {

  @Override
  public void getDetail(int id, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .getRepairInfoDetails(id)
        .compose(fragment.<Response<List<RepairInfoDetail>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
