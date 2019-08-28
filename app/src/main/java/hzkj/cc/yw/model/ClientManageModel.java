package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ClientManageContract;
import hzkj.cc.yw.contract.ClientManageContract.Model;
import hzkj.cc.yw.httpservice.ProjectService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClientManageModel extends BaseModel<ClientManageContract.Presenter> implements Model {

  public static final int GETPROJECTS = 11;

  @Override
  public void getProjects(int agencyId) {
    RetrofitFactory.getInstance().getRetrofit().create(ProjectService.class).get(agencyId)
        .compose(getProvider().<Response<List<ProjectInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETPROJECTS));
  }
}
