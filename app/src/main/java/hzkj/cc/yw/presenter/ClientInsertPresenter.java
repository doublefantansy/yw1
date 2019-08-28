package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.components.support.*;
import com.vise.log.*;
import com.xuexiang.xui.widget.edittext.materialedittext.*;
import com.xuexiang.xui.widget.spinner.materialspinner.*;
import hzkj.cc.yw.bean.*;
import hzkj.cc.yw.contract.*;
import hzkj.cc.yw.model.*;
import java.util.*;

public class ClientInsertPresenter extends
    BasePresenter<ClientInsertContract.View, ClientInsertContract.Model> implements
    ClientInsertContract.Presenter {

  @Override
  ClientInsertContract.Model createModel() {
    return new ClientInsertModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getData() instanceof List) {
      if (response.getCode() == 300) {
        if (((List) response.getData()).get(0) instanceof ProjectInfo) {
          getView().updateSuccessUi(response.getData(), 0);
        } else if (((List) response.getData()).get(0) instanceof ClientTypeInfo) {
          getView().updateSuccessUi(response.getData(), 1);
        } else {
          getView().updateSuccessUi(response.getData(), 5);
        }
      } else {
        getView().updateSuccessUi(null, 4);
      }
    } else if (response.getData() instanceof Integer) {
      if (((Integer) response.getData()) == 1) {
        getView().updateSuccessUi(null, 2);
      } else {
        getView().updateSuccessUi(null, 3);
      }
    }
  }

  @Override
  public void startGetProjects(String projectType) {
    ViseLog.d(projectType);
    getModel().getProjects(projectType, (RxAppCompatActivity) getView());
  }

  @Override
  public void startGetProjectTypeInfos() {
    getModel().getProjectTypeInfos((RxAppCompatActivity) getView());
  }

  @Override
  public void cheak(MaterialEditText clientName, MaterialSpinner clientType,
      MaterialSpinner projectType, MaterialSpinner projectName) {
    if (clientName.validate()) {
      if (clientType.getText()
          .toString()
          .equals("")) {
        clientType.setError("不能为空");
      } else if (projectType.getText()
          .toString()
          .equals("")) {
        projectType.setError("不能为空");
      } else if (projectName.getText()
          .toString()
          .equals("")) {
        projectName.setError("不能为空");
      } else {
        getView().updateCheckSuccess(null);
      }
    }
  }

  @Override
  public void startGetClientTypeInfos() {
    getModel().getClientTypeInfos((RxAppCompatActivity) getView());
  }

  @Override
  public void startInsertClientInfo(String customer_name, String customer_type,
      String customer_project, String customer_contact, String customer_contactDept,
      String customer_job, String customer_address, String customer_phone,
      String customer_projectType) {
//    ClientInfo clientInfo = new ClientInfo(customer_name, customer_type, customer_project,
//        customer_contact, customer_contactDept, customer_job, customer_address, customer_phone,
//        customer_projectType);
//    getModel().insertClientInfo(clientInfo, (RxAppCompatActivity) getView());
//        ViseLog.d(clientInfo);
  }
}
