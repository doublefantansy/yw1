package hzkj.cc.yw.model;

import com.vise.log.ViseLog;

import java.util.List;

import hzkj.cc.yw.bean.RepairInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairEachContract;
import hzkj.cc.yw.httpservice.GoToRepairService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoToRepairEachModel extends BaseModel<GoToRepairEachContract.Presenter> implements
    GoToRepairEachContract.Model {

  @Override
  public void getRepairInfo(int id, int type, int pageNum) {
    ViseLog.d(id + "|" + type + "|" + pageNum);
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .getRepairInfos(id, type, pageNum)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<RepairInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
