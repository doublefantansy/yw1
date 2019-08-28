package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ApplyBuyContract;
import hzkj.cc.yw.httpservice.ProjectService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ApplyBuyModel extends BaseModel<ApplyBuyContract.Presenter> implements
    ApplyBuyContract.Model {

  public static final int PROJECTS = 0;


  @Override
  public void getProjects(int deptId) {
    RetrofitFactory.getInstance().getRetrofit().create(ProjectService.class).get(deptId)
        .subscribeOn(
            Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<ProjectInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), PROJECTS));

  }
}
