package hzkj.cc.yw.model;

import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarContract;
import hzkj.cc.yw.httpservice.ProjectService;
import hzkj.cc.yw.httpservice.RentCarService;
import hzkj.cc.yw.httpservice.UserService;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RentCarModel extends BaseModel<RentCarContract.Presenter> implements
    RentCarContract.Model {

  public static final int USERS = 0;
  public static final int PROJECTS = 10;
  public static final int POST = 110;

//  @Override
//  public void getAuthorityUsers(int moduleId) {
//    RetrofitFactory.getInstance().getRetrofit().create(AuthorityService.class)
//        .getModuleAuthority(moduleId).subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .compose(getProvider().<Response<List<UserInfo>>>bindToLifecycle())
//        .subscribe(new CustomSubscriber(getPresenter(), USERS));
//  }

  @Override
  public void getProjects(int departmentId) {
    RetrofitFactory.getInstance().getRetrofit().create(ProjectService.class)
        .get(departmentId).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<ProjectInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), PROJECTS));
  }

  @Override
  public void postRentCar(RentCarInfo rentCarInfo) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .insertRentCarInfo(rentCarInfo).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<Integer>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), POST));
  }

  @Override
  public void searchUserByLike(String str) {
    RetrofitFactory.getInstance().getRetrofit().create(UserService.class).getByLike(str)
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .compose(getProvider().<Response<List<UserInfo>>>bindToLifecycle())
        .subscribe(new CustomSubscriber(getPresenter(), USERS));
  }
}
