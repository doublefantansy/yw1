package hzkj.cc.yw.ui.fragment;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.progress.loading.MiniLoadingView;
import com.xuexiang.xui.widget.textview.badge.BadgeView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.MyInfoContract;
import hzkj.cc.yw.presenter.MyInfoPresenter;
import hzkj.cc.yw.ui.activity.LoginActivity;
import hzkj.cc.yw.ui.activity.MyInfoContentActivity;
import hzkj.cc.yw.ui.activity.UpdatePasswordActivity;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.UiUtil;

public class MyInfoFragment extends BaseFragment<MyInfoContract.Presenter> implements
    MyInfoContract.View {

  @BindView(R.id.icon)
  RadiusImageView icon;
  @BindView(R.id.name)
  TextView name;
  @BindView(R.id.edit)
  TextView edit;
  @BindView(R.id.loading)
  MiniLoadingView loadingView;
  @BindView(R.id.attence)
  LinearLayout attence;
  @BindView(R.id.updatePassword)
  SuperTextView updatePassword;
  @BindView(R.id.switchUser)
  SuperTextView switchUser;
  @BindView(R.id.setting)
  SuperTextView setting;
  Bitmap bitmap;

  @Override
  void doOnVisible() {
  }

  @Override
  MyInfoContract.Presenter createPresenter() {
    return new MyInfoPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_myinfo;
  }

  @Override
  public void onResume() {
    super.onResume();
    updateInfo();
  }

  private void updateInfo() {
    name.setText(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserName());
    loadingView.setVisibility(View.VISIBLE);
//    RequestOptions options = new RequestOptions()
//        .placeholder(R.drawable.ic_personfill2)
//        .error(R.drawable.ic_personfill2)
//        .priority(Priority.HIGH);
//    Glide.with(getActivity())
//        .load(RetrofitFactory.URL_TEST + StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
//            .getIcon())
//        .apply(options)
//        .listener(new RequestListener<Drawable>() {
//          @Override
//          public boolean onLoadFailed(@Nullable GlideException e, Object model,
//              Target<Drawable> target, boolean isFirstResource) {
//            return false;
//          }
//
//          @Override
//          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
//              DataSource dataSource, boolean isFirstResource) {
//            loadingView.setVisibility(View.GONE);
//            return false;
//          }
//        })
//        .into(icon);
    new BadgeView(getActivity()).setBadgeNumber(1)
        .bindTarget(attence);
  }

  @Override
  public void initView() {
    icon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      }
    });
    updatePassword
        .setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
          @Override
          public void onClickListener(SuperTextView superTextView) {
            UiUtil.jumpToActivity(getActivity(), UpdatePasswordActivity.class, null, false, false,
                -1);
          }
        });
    edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        UiUtil.jumpToActivity(getActivity(), MyInfoContentActivity.class, null, false, false, -1);
      }
    });
    switchUser.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
      @Override
      public void onClickListener(SuperTextView superTextView) {
        StorageUtil.clearAll();
        UiUtil.jumpToActivity(getActivity(), LoginActivity.class, null, true, false, -1);
      }
    });
    setting.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
      @Override
      public void onClickListener(SuperTextView superTextView) {

      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(final Object object, int tag) {
//    RequestOptions options = new RequestOptions()
//        .placeholder(R.drawable.ic_personfill2)
//        .error(R.drawable.ic_personfill2)
//        .priority(Priority.HIGH);
//    Glide.with(MyInfoFragment.this)
//        .load(RetrofitFactory.URL_TEST + object)
//        .apply(options)
//        .listener(new RequestListener<Drawable>() {
//          @Override
//          public boolean onLoadFailed(@Nullable GlideException e, Object model,
//              Target<Drawable> target, boolean isFirstResource) {
//            loadingView.setVisibility(View.GONE);
//            return false;
//          }
//
//          @Override
//          public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
//              DataSource dataSource, boolean isFirstResource) {
//            loadingView.setVisibility(View.GONE);
//            return false;
//          }
//        })
//        .into(icon);
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
