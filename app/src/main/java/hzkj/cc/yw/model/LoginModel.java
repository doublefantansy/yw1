package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.LoginContract;
import hzkj.cc.yw.httpservice.UserService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author cc
 */
public class LoginModel extends BaseModel<LoginContract.Presenter> implements LoginContract.Model {

  @Override
  public void loginIn(UserInfo userInfo, RxAppCompatActivity activity) {
//        getPresenter().returnDataSuccess(null);
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(UserService.class)
        .logIn(userInfo)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(activity.<Response<UserInfo>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
