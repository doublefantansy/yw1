package hzkj.cc.yw.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarDetailContract;
import hzkj.cc.yw.model.RentCarDetailModel;
import hzkj.cc.yw.presenter.RentCarDetailPresenter;
import hzkj.cc.yw.utils.UiUtil;
import java.io.File;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPickerActivity;

public class RentCarDetailActivity extends BaseActivity<RentCarDetailContract.Presenter> implements
    RentCarDetailContract.View {


  RentCarInfo rentCarInfo;
  @BindView(R.id.useStartTime)
  TextView useStartTime;
  @BindView(R.id.project)
  TextView project;
  @BindView(R.id.persons)
  TextView persons;
  @BindView(R.id.reason)
  TextView reason;
  @BindView(R.id.carOwnerName)
  TextView carOwnerName;
  @BindView(R.id.carOwnerTel)
  TextView carOwnerTel;
  @BindView(R.id.carType)
  TextView carType;
  @BindView(R.id.carLicense)
  TextView carLicense;
  @BindView(R.id.perDayPrice)
  TextView perDayPrice;
  @BindView(R.id.days)
  TextView days;
  @BindView(R.id.totalMoney)
  TextView totalMoney;
  @BindView(R.id.totalMoneyComposition)
  TextView totalMoneyComposition;
  @BindView(R.id.displayReason)
  TextView displayReason;
  @BindView(R.id.procedureMan)
  TextView procedureMan;
  @BindView(R.id.procedurePart)
  LinearLayout procedurePart;
  @BindView(R.id.pic1)
  ImageView pic1;
  @BindView(R.id.pic2)
  ImageView pic2;
  @BindView(R.id.picPart)
  LinearLayout picPart;
  private int type;
  boolean isPic1;
  LoadingDialog loadingDialog;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  RentCarDetailContract.Presenter createPresenter() {
    return new RentCarDetailPresenter();
  }


  @Override
  public void getData() {
    getPresenter().startGetDetail(Integer.valueOf(getIntent().getStringExtra("id")), type);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_rentcar_detail;
  }

  @Override
  public void initView() {
    switch (Integer.valueOf(getIntent().getStringExtra("type"))) {
      case RentCarInfo.REVIEWSUCCESS: {
        picPart.setVisibility(View.VISIBLE);
        type = 6;
        break;
      }
      case RentCarInfo.REVIEWFAIL: {
        picPart.setVisibility(View.GONE);
        type = 5;
        break;
      }
      case RentCarInfo.HASNOTREVIEW: {
        procedurePart.setVisibility(View.GONE);
        break;
      }
    }
    bar.setTitle("租车详情");
    loadingDialog = new LoadingDialog(this);
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == PhotoPickerActivity.RESULT_OK) {
      loadingDialog.show();
      if (isPic1) {
        getPresenter().startUploadPic(rentCarInfo.getApplication_id(), 1,
            new File(data.getExtras().getStringArrayList(PhotoPicker.KEY_SELECTED_PHOTOS).get(0)));
      } else {
        getPresenter().startUploadPic(rentCarInfo.getApplication_id(), 2,
            new File(data.getExtras().getStringArrayList(PhotoPicker.KEY_SELECTED_PHOTOS).get(0)));
      }
    }
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(final Object object, int tag) {
    if (tag == RentCarDetailModel.UPLOAD) {
      loadingDialog.dismiss();
      Glide.with(this).load((RetrofitFactory.URL_TEST + (String) object))
          .into(isPic1 ? pic1 : pic2);
      (isPic1 ? pic1 : pic2).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          UiUtil.jumpToActivity(RentCarDetailActivity.this, PhotoActivity.class,
              new ArrayMap<String, String>() {{
                put("url", (String) object);
              }}, false, false, 0);
        }
      });
    } else {
      rentCarInfo = (RentCarInfo) object;
      useStartTime.setText(rentCarInfo.getApplication_date());
      project.setText(rentCarInfo.getProjectInfo().getProject_name());
      StringBuffer stringBuffer = new StringBuffer();
      for (int i = 0; i < rentCarInfo.getPersons().size(); i++) {
        stringBuffer.append(i == 0 ? rentCarInfo.getPersons().get(i).getUser_Name()
            : "," + rentCarInfo.getPersons().get(i).getUser_Name());
      }
      persons.setText(stringBuffer.toString());
      reason.setText(rentCarInfo.getApplication_reason());
      carOwnerName.setText(rentCarInfo.getApplication_carOwnerName());
      carOwnerTel.setText(rentCarInfo.getApplication_carOwnerTel());
      carType.setText(rentCarInfo.getApplication_carType());
      carLicense.setText(rentCarInfo.getApplication_carNum());
      perDayPrice.setText(rentCarInfo.getApplication_price());
      days.setText(rentCarInfo.getApplication_days());
      totalMoney.setText(rentCarInfo.getApplication_totalMoney());
      totalMoneyComposition.setText(rentCarInfo.getApplication_paymentStruct());
      if (type != RentCarInfo.HASNOTREVIEW) {
        displayReason.setText(rentCarInfo.getProcedure().getReview_reason());
        procedureMan.setText(rentCarInfo.getProcedure().getUserInfo().getUser_Name());
      }
      if (rentCarInfo.getPic1() != null) {
        pic1.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            UiUtil.jumpToActivity(RentCarDetailActivity.this, PhotoActivity.class,
                new ArrayMap<String, String>() {{
                  put("url", rentCarInfo.getPic1());
                }}, false, false, 0);
          }
        });
        Glide.with(this).load(RetrofitFactory.URL_TEST + rentCarInfo.getPic1())
            .listener(new RequestListener<Drawable>() {
              @Override
              public boolean onLoadFailed(@Nullable GlideException e, Object model,
                  Target<Drawable> target, boolean isFirstResource) {
                return false;
              }

              @Override
              public boolean onResourceReady(Drawable resource, Object model,
                  Target<Drawable> target,
                  DataSource dataSource, boolean isFirstResource) {
                return false;
              }
            }).into(pic1);
      } else {
        pic1.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            isPic1 = true;
            PhotoPicker.builder()
                .setPhotoCount(1)
                .start(RentCarDetailActivity.this);
          }
        });
      }
      if (rentCarInfo.getPic2() != null) {
        pic2.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            UiUtil.jumpToActivity(RentCarDetailActivity.this, PhotoActivity.class,
                new ArrayMap<String, String>() {{
                  put("url", rentCarInfo.getPic2());
                }}, false, false, 0);
          }
        });
        Glide.with(this).load(RetrofitFactory.URL_TEST + rentCarInfo.getPic2())
            .listener(new RequestListener<Drawable>() {
              @Override
              public boolean onLoadFailed(@Nullable GlideException e, Object model,
                  Target<Drawable> target, boolean isFirstResource) {
                return false;
              }

              @Override
              public boolean onResourceReady(Drawable resource, Object model,
                  Target<Drawable> target,
                  DataSource dataSource, boolean isFirstResource) {
                return false;
              }
            }).into(pic2);
      } else {
        pic2.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            isPic1 = false;
            PhotoPicker.builder()
                .setPhotoCount(1)
                .start(RentCarDetailActivity.this);
          }
        });
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
