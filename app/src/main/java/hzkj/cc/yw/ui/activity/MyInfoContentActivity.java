package hzkj.cc.yw.ui.activity;

import static com.wildma.pictureselector.PictureSelector.SELECT_REQUEST_CODE;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import butterknife.BindView;
import com.wildma.pictureselector.PictureSelector;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.progress.loading.MiniLoadingView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.MyInfoContentContract;
import hzkj.cc.yw.presenter.MyInfoContentPresenter;
import hzkj.cc.yw.utils.StorageUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyInfoContentActivity extends BaseActivity<MyInfoContentContract.Presenter> implements
    MyInfoContentContract.View {

  @BindView(R.id.name)
  SuperTextView name;
  @BindView(R.id.sex)
  SuperTextView sex;
  @BindView(R.id.icon)
  RadiusImageView icon;
  @BindView(R.id.loading)
  MiniLoadingView loadingView;
  List<String> sexs = new ArrayList<String>() {{
    add("男");
    add("女");
    add("保密");
  }};

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  MyInfoContentContract.Presenter createPresenter() {
    return new MyInfoContentPresenter();
  }

  @Override
  public boolean isShowingTitleBar() {
    return false;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_my_info_content;
  }

  @Override
  public void initView() {
    UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class);
    name.setRightString(userInfo
        .getUserName());
    sex.setRightString(userInfo
        .getSex());
    icon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        PictureSelector.create(MyInfoContentActivity.this, SELECT_REQUEST_CODE)
            .selectPicture();
      }
    });
    name.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(MyInfoContentActivity.this).title("设置名字")
            .inputType(InputType.TYPE_CLASS_TEXT)
            .input("请输入名字", StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
                .getUserName(), new MaterialDialog.InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class);
                userInfo.setUserName(input + "");
                getPresenter().startUpdateInfo(userInfo, null);
                loadingView.setVisibility(View.VISIBLE);
              }
            })
            .positiveText("提交")
            .negativeText("我不输了")
            .show();
      }
    });
    sex.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(MyInfoContentActivity.this).title("设置性别")
            .items(sexs)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int position,
                  CharSequence text) {
                UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class);
                userInfo.setSex(text + "");
                getPresenter().startUpdateInfo(userInfo, null);
                loadingView.setVisibility(View.VISIBLE);
              }
            })
            .show();
      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == SELECT_REQUEST_CODE) {
      if (data != null) {
        String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
//        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
        getPresenter().startUpdateInfo(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class),
            new File(picturePath));
      }
    }
  }

  @Override
  public void initData() {
    loadIcon();
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    loadIcon();
    name.setRightString(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserName());
    sex.setRightString(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getSex());
  }

  private void loadIcon() {
    loadingView.setVisibility(View.VISIBLE);
//    RequestOptions options = new RequestOptions()
//        .placeholder(R.drawable.ic_personfill2)
//        .error(R.drawable.ic_personfill2)
//        .priority(Priority.HIGH);
//    Glide.with(MyInfoContentActivity.this)
//        .load(RetrofitFactory.URL_TEST + StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
//            .getIcon())
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
