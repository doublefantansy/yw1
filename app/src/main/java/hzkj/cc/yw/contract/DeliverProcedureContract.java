package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.components.support.RxFragment;

import hzkj.cc.yw.bean.DeliverInfo;

public interface DeliverProcedureContract {

  interface Model extends BaseContract.Model {

    void getDeliverInfos(int pageNum, RxFragment fragment);

    void getProcedureDeliver(DeliverInfo deliverInfo, String nowTime, RxFragment fragment);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetDeliverInfos(int pageNum);

    void startGetProcedureDeliver(DeliverInfo deliverInfo);
  }
}
