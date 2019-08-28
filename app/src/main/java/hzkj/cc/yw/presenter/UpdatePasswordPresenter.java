package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.UpdatePasswordContract;
import hzkj.cc.yw.model.UpdatePasswordModel;
import hzkj.cc.yw.utils.StorageUtil;

public class UpdatePasswordPresenter extends
    BasePresenter<UpdatePasswordContract.View, UpdatePasswordContract.Model> implements
    UpdatePasswordContract.Presenter {

  @Override
  UpdatePasswordContract.Model createModel() {
    return new UpdatePasswordModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == 602) {
      getView().updateSuccessUi("修改成功", 0);
    } else {
      getView().updateFailUi(response.getMsg(), null);
    }
  }

  @Override
  public void cheak(String oldPassword, String newPassword, String confirmPassword) {
    if (oldPassword.equals("")) {
      getView().updateCheckFailUi("旧密码不能为空", null);
    } else if (newPassword.equals(confirmPassword)) {
      if (newPassword.equals("")) {
        getView().updateCheckFailUi("新密码不能为空", null);
      } else {
        getView().updateCheckSuccess(null);
      }
    } else {
      getView().updateCheckFailUi("两次新密码不一致", null);
    }
  }

  @Override
  public void startUpdatePassword(String oldPassword, String newPassword) {
    UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class);
    userInfo.setPassword(oldPassword);
    getModel().updatePassword(userInfo, newPassword, (RxAppCompatActivity) getView());
  }
}
