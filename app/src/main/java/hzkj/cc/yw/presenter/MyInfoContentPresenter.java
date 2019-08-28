package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vise.log.ViseLog;

import java.io.File;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.MyInfoContentContract;
import hzkj.cc.yw.model.MyInfoContentModel;
import hzkj.cc.yw.utils.StorageUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MyInfoContentPresenter extends
    BasePresenter<MyInfoContentContract.View, MyInfoContentContract.Model> implements
    MyInfoContentContract.Presenter {

  @Override
  MyInfoContentContract.Model createModel() {
    return new MyInfoContentModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getData() instanceof UserInfo) {
      StorageUtil.putData(StorageUtil.KEY_USER, response.getData());
      ViseLog.d(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class));
      getView().updateSuccessUi(response.getData(), 0);
    }
  }

  @Override
  public void startUpdateInfo(UserInfo userInfo, File file) {
    if (file != null) {
      getModel().updateInfo(userInfo, MultipartBody.Part.createFormData("file", file.getName(),
          RequestBody.create(MediaType.parse("multipart/form-data"), file)),
          (RxAppCompatActivity) getView());
    } else {
      getModel().updateInfo(userInfo, null, (RxAppCompatActivity) getView());
    }
  }
}
