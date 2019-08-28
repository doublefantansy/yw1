package hzkj.cc.yw.contract;

import com.trello.rxlifecycle.ActivityLifecycleProvider;
import hzkj.cc.yw.bean.Response;

public interface BaseContract {

  interface Model {

    /**
     * 设置presenter
     */
    void setPresenter(Presenter presenter);

    void setLifeCircle(ActivityLifecycleProvider provider);
  }

  /**
   *
   */
  interface View {

    boolean isGetDataAtFirst();

    void getData();

    /**
     * 传入layoutId
     */
    int getLayoutId();

    /**
     * 初始化界面
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 获取data失败时的ui变化
     */
    void updateFailUi(String msg, Object object);

    /**
     * 获取data成功时的ui变化
     */
    void updateSuccessUi(Object object, int tag);

    /**
     * 校验失败时的ui变化（未经过model）
     */
    void updateCheckFailUi(String msg, Object object);

    void updateCheckSuccess(Object object);
  }

  interface Presenter {

    /**
     * 设置view
     */
    void setView(View view, ActivityLifecycleProvider provider);

    /**
     * view销毁时解除绑定(防止内存泄漏)
     */
    void unSetView();
    /**
     * presenter获取data成功
     *
     * @param response
     */
    /**
     * presenter获取data成功
     *
     * @param msg 错误信息
     */
    void returnDataFail(String msg, Object object);

    void returnDataSuccess(Response response, int tag);
  }
}
