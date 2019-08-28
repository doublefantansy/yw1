package hzkj.cc.yw.model;


import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.retrofit.CustomSubscriber;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarDetailContract;
import hzkj.cc.yw.httpservice.RentCarService;
import okhttp3.MultipartBody.Part;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RentCarDetailModel extends BaseModel<RentCarDetailContract.Presenter> implements
    RentCarDetailContract.Model {

  public final static int GETDETAIL = 0;
  public final static int UPLOAD = 10;

  @Override
  public void getDetail(int id, int type) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class).getOne(id, type)
        .compose(getProvider().<Response<RentCarInfo>>bindToLifecycle())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), GETDETAIL));
  }

  @Override
  public void uploadPic(int id, int type, Part file) {
    RetrofitFactory.getInstance().getRetrofit().create(RentCarService.class)
        .uploadPic(type, id, file).compose(getProvider().<Response<String>>bindToLifecycle())
        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new CustomSubscriber(getPresenter(), UPLOAD));
  }

}
