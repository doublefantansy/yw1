package hzkj.cc.yw.model;

import java.util.List;

import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairContract;
import hzkj.cc.yw.httpservice.CommonService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoToRepairModel extends BaseModel<GoToRepairContract.Presenter> implements
    GoToRepairContract.Model {

  @Override
  public void getAgencyList(int id) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(CommonService.class)
        .getDepartmentByParentId(id)
        .compose(getProvider().<Response<List<Department>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getProjectList(int id) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(CommonService.class)
        .getProjectByProjectId(id)
        .compose(getProvider().<Response<List<ProjectInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
