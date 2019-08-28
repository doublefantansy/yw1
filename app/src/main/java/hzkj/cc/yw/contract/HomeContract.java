package hzkj.cc.yw.contract;

public interface HomeContract {

  interface Model extends BaseContract.Model {

    void getCounts(String mobile);

    void getBannerItems();
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startGetCounts();

    void startGetBannerItems();
  }
}
