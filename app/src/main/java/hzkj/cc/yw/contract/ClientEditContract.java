package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import hzkj.cc.yw.bean.ClientInfo;

public interface ClientEditContract {

  interface Model extends BaseContract.Model {

    void getProjects(RxAppCompatActivity activity);

    void getClientTypeInfos(RxAppCompatActivity activity);

    void updateClientInfo(ClientInfo clientInfo, RxAppCompatActivity activity);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetProjects();

    void startGetClientTypeInfos();

    void startUpdateClientInfo(ClientInfo clientInfo);
  }
}
