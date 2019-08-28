package hzkj.cc.yw.contract;

import java.io.File;
import okhttp3.MultipartBody.Part;

public interface RentCarDetailContract {

  interface Model extends BaseContract.Model {

    void getDetail(int id, int type);

    void uploadPic(int id, int type, Part file);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetDetail(int id, int type);

    void startUploadPic(int id, int type, File file);


  }
}
