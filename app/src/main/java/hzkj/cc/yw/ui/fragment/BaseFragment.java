package hzkj.cc.yw.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.trello.rxlifecycle.ActivityLifecycleProvider;
import com.trello.rxlifecycle.components.support.RxFragment;
import hzkj.cc.yw.contract.BaseContract;
import hzkj.cc.yw.ui.activity.BaseActivity;

public abstract class BaseFragment<T extends BaseContract.Presenter> extends RxFragment implements
    BaseContract.View {

  private T presenter;
  private Unbinder unbinder;
  View view;
//    boolean first = true;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(getLayoutId(), container, false);
    presenter = createPresenter();
    presenter.setView(this, (ActivityLifecycleProvider) getActivity());
    unbinder = ButterKnife.bind(this, view);
    initView();
    initData();
    if (isGetDataAtFirst()) {
      getData();
    }
    return view;
  }

  @Override
  public boolean isGetDataAtFirst() {
    return true;
  }

  /**
   * 只适用于hide/show的fragment
   */
  abstract void doOnVisible();

  abstract T createPresenter();

  public T getPresenter() {
    return presenter;
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!hidden) {
      doOnVisible();
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    BaseActivity activity = (BaseActivity) getActivity();
    activity.updateCheckFailUi(msg, object);
  }

  @Override
  public void updateFailUi(String msg, Object object) {
    BaseActivity activity = (BaseActivity) getActivity();
    activity.updateFailUi(msg, object);
  }
}
