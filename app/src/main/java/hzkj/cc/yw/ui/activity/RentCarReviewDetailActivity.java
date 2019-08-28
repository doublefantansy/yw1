package hzkj.cc.yw.ui.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.BoldTextView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.RentCarReviewDetailContract;
import hzkj.cc.yw.contract.RentCarReviewDetailContract.Presenter;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.RentCarReviewDetailPresenter;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.UiUtil;

public class RentCarReviewDetailActivity extends
    BaseActivity<RentCarReviewDetailContract.Presenter> implements
    RentCarReviewDetailContract.View {

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
  @BindView(R.id.no)
  ButtonView no;
  @BindView(R.id.yes)
  ButtonView yes;
  @BindView(R.id.layout)
  RelativeLayout layout;
  @BindView(R.id.editText)
  MultiLineEditText editText;
  @BindView(R.id.displayError)
  SuperTextView displayError;
  @BindView(R.id.displayReason)
  BoldTextView displayReason;
  @BindView(R.id.procedureMan)
  TextView procedureMan;
  @BindView(R.id.procedureManPart)
  RelativeLayout procedureManPart;
  LoadingDialog loading;
  @BindView(R.id.procedurePart)
  LinearLayout procedurePart;
  @BindView(R.id.picPart)
  LinearLayout picPart;
  @BindView(R.id.pic1)
  ImageView pic1;
  @BindView(R.id.pic2)
  ImageView pic2;
  int type;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  Presenter createPresenter() {
    return new RentCarReviewDetailPresenter();
  }

  @Override
  public void getData() {
    getPresenter().startGetDetail(Integer.valueOf(getIntent().getStringExtra("id")), type);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_rentcar_review;
  }

  @Override
  public void initView() {
    switch (Integer.valueOf(getIntent().getStringExtra("type"))) {
      case RentCarInfo.REVIEWSUCCESS: {
        type = 6;
        break;
      }
      case RentCarInfo.REVIEWFAIL: {
        type = 5;
        break;
      }
    }
    bar.setTitle("审核详情");
    loading = new LoadingDialog(RentCarReviewDetailActivity.this);
    no.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        ViseLog.d(editText.getContentText());
        getPresenter().check(editText.getContentText(), RentCarInfo.REVIEWFAIL);
      }
    });
    yes.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().check(editText.getContentText(), RentCarInfo.REVIEWSUCCESS);
      }
    });
    if (Integer.valueOf(getIntent().getStringExtra("type")) == (RentCarInfo.HASNOTREVIEW)) {
      yes.setVisibility(View.VISIBLE);
      no.setVisibility(View.VISIBLE);
      editText.setVisibility(View.VISIBLE);
      displayReason.setVisibility(View.GONE);
      procedureManPart.setVisibility(View.GONE);
    } else {
      yes.setVisibility(View.GONE);
      no.setVisibility(View.GONE);
      editText.setVisibility(View.GONE);
      displayReason.setVisibility(View.VISIBLE);
      procedureManPart.setVisibility(View.VISIBLE);
      if (Integer.valueOf(getIntent().getStringExtra("type")) == RentCarInfo.REVIEWSUCCESS) {
        picPart.setVisibility(View.VISIBLE);
      }
    }
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == BasePresenter.UPDATESUCCESS) {
      new CcDialog(RentCarReviewDetailActivity.this, CcDialog.SUCCESS_DIALOG).setMessage("审核成功")
          .setCancelListener(
              new CancelListener() {
                @Override
                public void onClick(CcDialog dialog) {
                  setResult(100);
                  finish();
                }
              }).showDialog();
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
            UiUtil.jumpToActivity(RentCarReviewDetailActivity.this, PhotoActivity.class,
                new ArrayMap<String, String>() {{
                  put("url", rentCarInfo.getPic1());
                }}, false, false, 0);
          }
        });
        Glide.with(this).load(RetrofitFactory.URL_TEST + rentCarInfo.getPic1())
            .into(pic1);
      }
      if (rentCarInfo.getPic2() != null) {
        pic2.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            UiUtil.jumpToActivity(RentCarReviewDetailActivity.this, PhotoActivity.class,
                new ArrayMap<String, String>() {{
                  put("url", rentCarInfo.getPic2());
                }}, false, false, 0);
          }
        });
        Glide.with(this).load(RetrofitFactory.URL_TEST + rentCarInfo.getPic2())
           .into(pic2);
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
    rentCarInfo.getProcedure().setReview_reason(editText.getContentText());
    getPresenter().startPutRentInfo((Integer) object, rentCarInfo,
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getUserId());
    loading.show();
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    displayError.setRightString(msg);
    displayError.setRightTextColor(getColor(R.color.red));
    editText.getEditText().addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (count > 0) {
          displayError.setRightString("");
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });
//    super.updateCheckFailUi(msg, object);
  }
}
