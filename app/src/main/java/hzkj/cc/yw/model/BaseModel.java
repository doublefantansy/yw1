package hzkj.cc.yw.model;

import com.trello.rxlifecycle.ActivityLifecycleProvider;

import hzkj.cc.yw.contract.BaseContract;

/**
 * @author cc
 */
public abstract class BaseModel<P extends BaseContract.Presenter> implements BaseContract.Model {

  private P presenter;
  ActivityLifecycleProvider provider;

  public ActivityLifecycleProvider getProvider() {
    return provider;
  }

  @Override
  public void setLifeCircle(ActivityLifecycleProvider provider) {
    this.provider = provider;
  }

  @Override
  public void setPresenter(BaseContract.Presenter presenter) {
    this.presenter = (P) presenter;
  }

  public P getPresenter() {
    return presenter;
  }
}
