package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import hzkj.cc.yw.contract.RentCarManageContract;

public class RentCarManageModel extends BaseModel<RentCarManageContract.Presenter> implements
    RentCarManageContract.Model {

  @Override
  public void getAllRentCarInfo(String phone, RxAppCompatActivity appCompatActivity) {
//    RetrofitFactory.getInstance()
//        .getRetrofit()
//        .create(RentCarService.class)
//        .getRentCarInfoByRentCar(phone, pageNum)
//        .compose(appCompatActivity.<Response<List<RentCarInfo>>>bindToLifecycle())
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
