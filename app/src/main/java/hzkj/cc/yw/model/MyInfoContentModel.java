package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.MyInfoContentContract;
import hzkj.cc.yw.httpservice.UserService;
import hzkj.cc.yw.utils.GsonUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyInfoContentModel extends BaseModel<MyInfoContentContract.Presenter> implements
    MyInfoContentContract.Model {

  @Override
  public void updateInfo(UserInfo userInfo, MultipartBody.Part image,
      RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(UserService.class)
        .updateInfo(
            RequestBody
                .create(MediaType.parse("multipart/form-data"), GsonUtil.objectToString(userInfo)
                    + "")
            , image
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(appCompatActivity.<Response<UserInfo>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
