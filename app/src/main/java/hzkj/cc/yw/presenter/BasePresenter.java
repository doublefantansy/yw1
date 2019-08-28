package hzkj.cc.yw.presenter;

import com.trello.rxlifecycle.ActivityLifecycleProvider;

import hzkj.cc.yw.contract.BaseContract;

/**
 * @author cc
 */
public abstract class BasePresenter<V extends BaseContract.View, M extends BaseContract.Model> implements
    BaseContract.Presenter {

  private M model;
  private V view;
  int type;
  //    public static final int DATAEMPTY = 0;
  public static final int FIRSTSUCCESS = 111111111;
  public static final int EMPTY = 11111110;
  public static final int REFRESHSUCCESS = 1;
  public static final int LOADMORESUCCESS = 3;
  public static final int LOADMOREEMPTY = 5;
  public static final int FIRSTDATAEMPTY = 2;
  public static final int NORMAL = 7;
  public static final int UPDATESUCCESS = 9;
  public static final int INSERTSUCCESS = 8;
  public static final int REFRESHERROR = 10;
  public static final int LOADMOREERROR = 11;
  public static final int FIRSTNETERROR = 111;
  public static final int PROJECTEMPTY = 15;
  public static final int DEPARTMENTEMPTY = 12;
  public static final int PROJECTSUCCESS = 13;
  public static final int DEPARTSUCCESS = 14;

  BasePresenter() {
    model = createModel();
  }

  public V getView() {
    return view;
  }

  public M getModel() {
    return model;
  }

  /**
   * 生成对应model类
   *
   * @return model
   */
  abstract M createModel();

  /**
   * view销毁时解除引用绑定
   */
  @Override
  public void unSetView() {
    view = null;
  }

  @Override
  public void returnDataFail(String msg, Object object) {
    view.updateFailUi(msg, object);
  }

  @Override
  public void setView(BaseContract.View view, ActivityLifecycleProvider provider) {
    this.view = (V) view;
    model.setPresenter(this);
    model.setLifeCircle(provider);
  }
}
