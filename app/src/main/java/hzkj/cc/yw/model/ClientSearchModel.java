package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ClientSearchContract;
import hzkj.cc.yw.httpservice.ClientService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClientSearchModel extends BaseModel<ClientSearchContract.Presenter> implements
    ClientSearchContract.Model {

  public static final int PROJECT = 0;

  @Override
  public void searchByProject(int projectId, int pageNum) {
    RetrofitFactory.getInstance().getRetrofit().create(ClientService.class)
        .searchByProjectId(projectId).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<ClientInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), PROJECT));
  }
}
