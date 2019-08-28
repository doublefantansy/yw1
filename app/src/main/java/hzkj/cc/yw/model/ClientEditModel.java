package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.contract.ClientEditContract;

public class ClientEditModel extends BaseModel<ClientEditContract.Presenter> implements
    ClientEditContract.Model {

  @Override
  public void getProjects(RxAppCompatActivity activity) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(ClientService.class)
//                .getProjects()
//                .compose(activity.<Response<List<ProjectInfo>>>bindToLifecycle())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new CustomSubscriber<List<ProjectInfo>>(getPresenter()));
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
  public void updateClientInfo(ClientInfo clientInfo, RxAppCompatActivity activity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(ClientService.class)
//        .updateClientInfo(clientInfo)
//        .compose(activity.<Response<Integer>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
