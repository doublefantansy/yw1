package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.SingleGood;
import java.util.List;

import hzkj.cc.yw.bean.DeliverCompany;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.DeliverInsertContract;
import hzkj.cc.yw.httpservice.CommonService;
import hzkj.cc.yw.httpservice.SingleDeliverService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DeliverInsertModel extends BaseModel<DeliverInsertContract.Presenter> implements
    DeliverInsertContract.Model {

  @Override
  public void getUserByDepartmentId(int id, RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(CommonService.class)
        .getUserInfosByDepartmentId(id)
        .compose(appCompatActivity.<Response<List<UserInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getDeliverCompanys(RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(CommonService.class)
        .getDeliverCompanys()
        .compose(appCompatActivity.<Response<List<DeliverCompany>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void getDepartments(int id, RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(CommonService.class)
        .getDepartmentByParentId(id)
        .compose(appCompatActivity.<Response<List<Department>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }

  @Override
  public void insertDeliverInfo(int kdgs, String kddh, String fhsj, int fhrId, int sjrid,
      List<SingleGood> singleGoods) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(SingleDeliverService.class)
        .post(kdgs, kddh, fhsj, fhrId, sjrid, singleGoods)
        .compose(getProvider().<Response<Integer>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
