package hzkj.cc.yw.presenter;


import com.vise.log.ViseLog;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.contract.RentCarDetailContract;
import hzkj.cc.yw.model.RentCarDetailModel;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RentCarDetailPresenter extends
    BasePresenter<RentCarDetailContract.View, RentCarDetailContract.Model> implements
    RentCarDetailContract.Presenter {

  @Override
  RentCarDetailContract.Model createModel() {
    return new RentCarDetailModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    getView().updateSuccessUi(response.getData(), tag);
  }

  @Override
  public void startGetDetail(int id, int type) {
    getModel().getDetail(id, type);
  }

  @Override
  public void startUploadPic(int id, int type, File file) {
    getModel()
        .uploadPic(id, type,
            MultipartBody.Part.createFormData("file", file.getName(),
                RequestBody.create(MediaType.parse("multipart/form-data"), file)));
  }

}
