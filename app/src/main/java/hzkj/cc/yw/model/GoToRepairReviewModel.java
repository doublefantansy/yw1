package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.GoToRepairReviewContract;
import hzkj.cc.yw.httpservice.GoToRepairService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoToRepairReviewModel extends BaseModel<GoToRepairReviewContract.Presenter> implements
    GoToRepairReviewContract.Model {

  @Override
  public void getReviews(int id, RxFragment fragment) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(GoToRepairService.class)
        .getRepairReviews(id)
        .compose(fragment.<Response<List<Procedure>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
