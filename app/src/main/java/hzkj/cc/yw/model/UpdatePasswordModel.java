package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.UpdatePasswordContract;
import hzkj.cc.yw.httpservice.UserService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdatePasswordModel extends BaseModel<UpdatePasswordContract.Presenter> implements
    UpdatePasswordContract.Model {

  @Override
  public void updatePassword(UserInfo userInfo, String newPassword,
      RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(UserService.class)
        .updatePassword(userInfo, newPassword)
        .compose(appCompatActivity.<Response<UserInfo>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
