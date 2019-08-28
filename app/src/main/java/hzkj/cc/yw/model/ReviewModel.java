package hzkj.cc.yw.model;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.TransActionInfo;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.ReviewContract;
import hzkj.cc.yw.httpservice.ReviewService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReviewModel extends BaseModel<ReviewContract.Presenter> implements
    ReviewContract.Model {

  @Override
  public void getReviewInfos(String status, RxAppCompatActivity appCompatActivity) {
    RetrofitFactory.getInstance()
        .getRetrofit()
        .create(ReviewService.class)
        .getReviewInfos(status)
        .compose(appCompatActivity.<Response<List<TransActionInfo>>>bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), 0));
  }
}
