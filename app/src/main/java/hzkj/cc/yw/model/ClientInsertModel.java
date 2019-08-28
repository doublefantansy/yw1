package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.contract.ClientInsertContract;

/**
 * @author cc
 */
public class ClientInsertModel extends BaseModel<ClientInsertContract.Presenter> implements
    ClientInsertContract.Model {

  @Override
  public void insertClientInfo(ClientInfo clientInfo, RxAppCompatActivity activity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ClientService.class)
//        .insertClientInfo(clientInfo)
//        .compose(activity.<Response<Integer>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getProjects(String projectType, RxAppCompatActivity activity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ClientService.class)
//        .getProjectInfos(projectType)
//        .compose(activity.<Response<List<ProjectInfo>>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getClientTypeInfos(RxAppCompatActivity activity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ClientService.class)
//        .getClientTypeInfos()
//        .compose(activity.<Response<List<ClientTypeInfo>>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getProjectTypeInfos(RxAppCompatActivity activity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ClientService.class)
//        .getProjectTypeInfos()
//        .compose(activity.<Response<List<ProjectTypeInfo>>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
