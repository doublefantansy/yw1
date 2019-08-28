package hzkj.cc.yw.contract;

public interface ApplyBuyEachContract {

  interface Model extends BaseContract.Model {

    void getApplyBuyInfos(String mobile, int type, int pageNum);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetApplyBuyInfos(int type, int pageNum);
  }
}
