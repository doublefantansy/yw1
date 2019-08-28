package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ClientContract;
import hzkj.cc.yw.httpservice.ClientService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClientModel extends BaseModel<ClientContract.Presenter> implements
    ClientContract.Model {

  public static final int GETALL = 1;

  @Override
  public void getAll(int pageNum) {
    RetrofitFactory.getInstance().getRetrofit().create(ClientService.class).getClientInfo(pageNum)
        .compose(getProvider().<Response<List<ClientInfo>>>bindToLifecycle()).subscribeOn(
        Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETALL));
  }
}
