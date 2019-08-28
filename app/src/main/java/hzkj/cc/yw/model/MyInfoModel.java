package hzkj.cc.yw.model;

import hzkj.cc.yw.contract.MyInfoContract;

public class MyInfoModel extends BaseModel<MyInfoContract.Presenter> implements
    MyInfoContract.Model {
//    @Override
//    public void getIconUrl(RxFragment fragment) {
//        RetrofitFactory.getInstance()
//                .getRetrofit()
//                .create(UserService.class)
//                .getIconUrl(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
//                        .getId() + "")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .compose(fragment.<Response<String>>bindToLifecycle())
//                .subscribe(new CustomSubscriber<String>(getPresenter()));
//    }
}
