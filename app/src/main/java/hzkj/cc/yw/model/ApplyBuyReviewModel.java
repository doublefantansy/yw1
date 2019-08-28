package hzkj.cc.yw.model;

import hzkj.cc.yw.contract.ApplyBuyReviewContract;

public class ApplyBuyReviewModel extends BaseModel<ApplyBuyReviewContract.Presenter> implements
    ApplyBuyReviewContract.Model {
//    @Override
//    public void getReviews(int id, RxAppCompatActivity appCompatActivity) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(ApplyInfoService.class)
//                .getApplyBuyReviews(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(appCompatActivity.<Response<List<ApplyBuyInfo>>>bindToLifecycle())
//                .subscribe(new CustomSubscriber<List<ApplyBuyInfo>>(getPresenter()));
//    }
}
