package hzkj.cc.yw.presenter;

import android.widget.TextView;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarContract;
import hzkj.cc.yw.contract.RentCarContract.Model;
import hzkj.cc.yw.model.RentCarModel;
import hzkj.cc.yw.utils.StorageUtil;
import java.util.List;

public class RentCarPresenter extends
    BasePresenter<RentCarContract.View, RentCarContract.Model> implements
    RentCarContract.Presenter {

  public static final int USERS = 0;
  public static final int USEREMPTY = 1;
  public static final int PROJECTS = 2;
  public static final int PROJECTEMPTY = 3;

  @Override
  Model createModel() {
    return new RentCarModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (tag == RentCarModel.USERS) {
      getView().updateSuccessUi(response.getData(),
          response.getCode() == RetrofitFactory.NODATA ? USEREMPTY : USERS);
    } else if (tag == RentCarModel.PROJECTS) {
      getView().updateSuccessUi(response.getData(),
          response.getCode() == RetrofitFactory.NODATA ? PROJECTEMPTY : PROJECTS);
    } else if (response.getCode() == RetrofitFactory.UPDATESUCCESS) {
      getView().updateSuccessUi(null, UPDATESUCCESS);
    }
  }
//  @Override
//  public void startGetAuthorityUsers(int moduleId) {
//    getModel().getAuthorityUsers(moduleId);
//  }

  @Override
  public void startGetProjects() {
    getModel().getProjects(
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptId());
  }


  @Override
  public void check(List<TextView> textViews) {
    int count = 0;
    for (int i = 0; i < textViews.size(); i++) {
      if (textViews.get(i).getText().toString().equals("") | textViews.get(i).getText().toString()
          .equals("请完善信息")) {
        count++;
        getView().updateCheckFailUi("请完善信息", textViews.get(i));
      }
    }
    if (count == 0) {
      getView().
          updateCheckSuccess(null);
    }
  }

  @Override
  public void startPostRentCar(RentCarInfo rentCarInfo) {
    getModel().postRentCar(rentCarInfo);
  }

  @Override
  public void startSearchUserByLike(String str) {
    getModel().searchUserByLike(str);
  }
}
