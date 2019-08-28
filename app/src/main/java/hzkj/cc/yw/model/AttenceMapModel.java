package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vise.log.ViseLog;

import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceMapContract;
import hzkj.cc.yw.httpservice.AttenceService;
import hzkj.cc.yw.utils.StorageUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AttenceMapModel extends BaseModel<AttenceMapContract.Presenter> implements
    AttenceMapContract.Model {

  @Override
  public void getDepartmentCoordinate(int userId, RxAppCompatActivity activity) {
    ViseLog.d(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class));
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .getDepartmentCoordinate(userId)
        .compose(activity.<Response<Department>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void attence(AttenceInfo attenceInfo, RxAppCompatActivity activity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(AttenceService.class)
        .startAttence(attenceInfo)
        .compose(activity.<Response<Integer>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
