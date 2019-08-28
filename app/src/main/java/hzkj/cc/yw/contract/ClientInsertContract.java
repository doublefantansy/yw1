package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import hzkj.cc.yw.bean.ClientInfo;

public interface ClientInsertContract {

  interface Model extends BaseContract.Model {

    void insertClientInfo(ClientInfo clientInfo, RxAppCompatActivity activity);

    void getProjects(String projectType, RxAppCompatActivity activity);

    void getClientTypeInfos(RxAppCompatActivity activity);

    void getProjectTypeInfos(RxAppCompatActivity activity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetProjects(String projectType);

    void startGetProjectTypeInfos();

    void cheak(MaterialEditText clientName, MaterialSpinner clientType, MaterialSpinner projectType,
        MaterialSpinner projectName);

    void startGetClientTypeInfos();

    void startInsertClientInfo(String customer_name, String customer_type, String customer_project,
        String customer_contact, String customer_contactDept, String customer_job,
        String customer_address, String customer_phone, String customer_projectType);
  }
}
