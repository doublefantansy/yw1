package hzkj.cc.yw.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.actionbar.TitleBar.Action;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.Builder;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.InputCallback;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallbackSingleChoice;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.ClickItemListenner;
import hzkj.cc.yw.adapter.RentCarSearchAdapter;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.RentCarContract;
import hzkj.cc.yw.presenter.BasePresenter;
import hzkj.cc.yw.presenter.RentCarPresenter;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RentCarActivity extends BaseActivity<RentCarContract.Presenter> implements
    RentCarContract.View {

  @BindView(R.id.search)
  LinearLayout search;
  @BindView(R.id.rentCarUsers)
  TextView rentCarUsers;
  @BindView(R.id.rentCarUsersIcon)
  ImageView rentCarUsersIcon;
  @BindView(R.id.rentCarProject)
  TextView rentCarProject;
  @BindView(R.id.rentCarProjectImg)
  ImageView rentCarProjectImg;
  @BindView(R.id.rentCarTimeImg)
  ImageView rentCarTimeImg;
  @BindView(R.id.rentCarTime)
  TextView rentCarTime;
  @BindView(R.id.rentCarReason)
  TextView rentCarReason;
  @BindView(R.id.rentCarReasonIcon)
  ImageView rentCarReasonIcon;
  @BindView(R.id.rentCarOwnerName)
  TextView rentCarOwnerName;
  @BindView(R.id.rentCarOwnerNameIcon)
  ImageView rentCarOwnerNameIcon;
  @BindView(R.id.rentCarOwnerPhone)
  TextView rentCarOwnerPhone;
  @BindView(R.id.rentCarOwnerPhoneIcon)
  ImageView rentCarOwnerPhoneIcon;
  @BindView(R.id.rentCarType)
  TextView rentCarType;
  @BindView(R.id.rentCarTypeIcon)
  ImageView rentCarTypeIcon;
  @BindView(R.id.rentCarId)
  TextView rentCarId;
  @BindView(R.id.rentCarIdIcon)
  ImageView rentCarIdIcon;
  @BindView(R.id.rentCarCostPerDay)
  TextView rentCarCostPerDay;
  @BindView(R.id.rentCarCostPerDayIcon)
  ImageView rentCarCostPerDayIcon;
  @BindView(R.id.rentCarDays)
  TextView rentCarDays;
  @BindView(R.id.rentCarDaysIcon)
  ImageView rentCarDaysIcon;
  @BindView(R.id.rentCarCostSum)
  TextView rentCarCostSum;
  @BindView(R.id.rentCarCostSumIcon)
  ImageView rentCarCostSumIcon;
  @BindView(R.id.rentCarCostConstitute)
  TextView rentCarCostConstitute;
  @BindView(R.id.rentCarCostConstituteIcon)
  ImageView rentCarCostConstituteIcon;
  @BindView(R.id.scroll)
  NestedScrollView scroll;
  @BindView(R.id.edit)
  EditText edit;
  @BindView(R.id.del)
  ImageView del;
  @BindView(R.id.back)
  ImageView back;
  @BindView(R.id.recyclerView)
  RecyclerView recyclerView;
  @BindView(R.id.selectedUsers)
  TextView selectedUsers;
  String users = new String();
  Integer selectProjectIndex = 0;
  hzkj.cc.library.LoadingDialog loadingDialog;
  String inputedReason = "";
  String inputedOwnerName = "";
  String inputedOwnerPhone = "";
  String inputedCarType = "";
  String inputedCarId = "";
  String inputedCostPeyDay = "";
  String inputedDays = "";
  String inputedCostSum = "";
  String inputedCostConstitute = "";
  //  List<UserInfo> selectUserInfos = new ArrayList<>();
  List<UserInfo> userInfos = new ArrayList<>();
  List<ProjectInfo> projectInfos;
  Action defalutAction;
  Action searchAction;
  private RentCarSearchAdapter adapter;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  RentCarContract.Presenter createPresenter() {
    return new RentCarPresenter();
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_rentcar;
  }

  @Override
  public void initView() {
    bar.setTitle("租车申请");
    ViseLog.d(StorageUtil.getData(StorageUtil.KEY_TOKEN, String.class));
    defalutAction = new Action() {
      @Override
      public String getText() {
        return "提交";
      }

      @Override
      public int getDrawable() {
        return 0;
      }

      @Override
      public void performAction(View view) {
        getPresenter().check(new ArrayList<TextView>() {{
          add(rentCarTime);
          add(rentCarProject);
          add(rentCarUsers);
          add(rentCarReason);
          add(rentCarOwnerName);
          add(rentCarOwnerPhone);
          add(rentCarType);
          add(rentCarId);
          add(rentCarCostPerDay);
          add(rentCarDays);
          add(rentCarCostSum);
          add(rentCarCostConstitute);
        }});
      }

      @Override
      public int leftPadding() {
        return 0;
      }

      @Override
      public int rightPadding() {
        return 0;
      }
    };
    bar.addAction(defalutAction);
    searchAction = new Action() {
      @Override
      public String getText() {
        return "选择完成";
      }

      @Override
      public int getDrawable() {
        return 0;
      }

      @Override
      public void performAction(View view) {
        dismissSearch();
        if (!users.equals("")) {
          rentCarUsers.setTextColor(getColor(R.color.black));
        } else {
          rentCarUsers.setTextColor(getColor(R.color.red));
        }
        rentCarUsers.setText(users);
      }

      @Override
      public int leftPadding() {
        return 0;
      }

      @Override
      public int rightPadding() {
        return 0;
      }
    };
    bar.addAction(searchAction);
    bar.getViewByAction(searchAction).setVisibility(View.GONE);
    loadingDialog = new hzkj.cc.library.LoadingDialog(RentCarActivity.this);
    rentCarUsersIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        bar.getViewByAction(defalutAction).setVisibility(View.GONE);
        bar.getViewByAction(searchAction).setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);
        scroll.setVisibility(View.GONE);
        search.setAnimation(AnimationUtils.makeInAnimation(RentCarActivity.this, false));
//        loadingDialog.show();
//        getPresenter().startGetAuthorityUsers(AuthorityInfo.RENTCARAPPLY);
      }
    });
//    search.setOnSearchViewListener(new SearchViewListener() {
//      @Override
//      public void onSearchViewShown() {
//
//      }
//
//      @Override
//      public void onSearchViewClosed() {
//        search.setVisibility(View.GONE);
//        scroll.setVisibility(View.VISIBLE);
//        bar.getViewByAction(defalutAction).setVisibility(View.VISIBLE);
//        bar.getViewByAction(searchAction).setVisibility(View.GONE);
//      }
//    });
//    search.setVoiceSearch(false);
//    search.setHint("请输入姓名进行模糊查询");
    edit.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().length() > 0) {
          getPresenter().startSearchUserByLike(s.toString());
        } else {
          userInfos.clear();
          adapter.notifyDataSetChanged();
        }
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });
    rentCarProjectImg.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startGetProjects();
      }
    });
    rentCarTimeImg.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(RentCarActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            rentCarTime.setText(TimeUtil.dateToString(date, TimeUtil.YMD));
            rentCarTime.setTextColor(getColor(R.color.black));
          }
        })
            .setTitleText("日期选择")
            .build().show();
      }
    });
    rentCarReasonIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("用车事由").input("", inputedReason,
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                inputedReason = input.toString();
                rentCarReason.setText(inputedReason);
                rentCarReason.setTextColor(getColor(R.color.black));
              }
            }).build().show();
      }
    });
    rentCarOwnerNameIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("车主姓名").input("", inputedOwnerName,
            new InputCallback() {
              @Override
              public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                inputedOwnerName = input.toString();
                rentCarOwnerName.setText(inputedOwnerName);
                rentCarOwnerName.setTextColor(getColor(R.color.black));
              }
            }).build().show();
      }
    });
    rentCarOwnerPhoneIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("车主联系电话")
            .inputType(InputType.TYPE_CLASS_NUMBER)
            .inputRange(11, 11)
            .input("", inputedOwnerPhone,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedOwnerPhone = input.toString();
                    rentCarOwnerPhone.setText(inputedOwnerPhone);
                    rentCarOwnerPhone.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarTypeIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("车型")
            .input("", inputedCarType,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedCarType = input.toString();
                    rentCarType.setText(inputedCarType);
                    rentCarType.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarIdIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("车牌")
            .input("", inputedCarId,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedCarId = input.toString();
                    rentCarId.setText(inputedCarId);
                    rentCarId.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarCostPerDayIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("租车日均价(元)")
            .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
            .input("", inputedCostPeyDay,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedCostPeyDay = input.toString();
                    rentCarCostPerDay.setText(inputedCostPeyDay);
                    rentCarCostPerDay.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarDaysIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("租车天数")
            .inputType(InputType.TYPE_CLASS_NUMBER)
            .input("", inputedDays,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedDays = input.toString();
                    rentCarDays.setText(inputedDays);
                    rentCarDays.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarCostSumIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("费用总计(元)")
            .inputType(InputType.TYPE_NUMBER_FLAG_DECIMAL)
            .input("", inputedCostSum,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedCostSum = input.toString();
                    rentCarCostSum.setText(inputedCostSum);
                    rentCarCostSum.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    rentCarCostConstituteIcon.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(RentCarActivity.this).title("费用构成")
            .input("", inputedCostConstitute,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    inputedCostConstitute = input.toString();
                    rentCarCostConstitute.setText(inputedCostConstitute);
                    rentCarCostConstitute.setTextColor(getColor(R.color.black));
                  }
                })
            .build().show();
      }
    });
    adapter = new RentCarSearchAdapter(RentCarActivity.this, userInfos);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(adapter);
    adapter.setClickItemListenner(new ClickItemListenner() {
      @Override
      public void click(int p) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < adapter.getSelectedUsers().size(); i++) {
          stringBuffer.append(i != 0 ? "," + adapter.getSelectedUsers().get(i).getUser_Name()
              : adapter.getSelectedUsers().get(i).getUser_Name());
        }
        users = stringBuffer.toString();
        selectedUsers.setVisibility(users.equals("") ? View.GONE : View.VISIBLE);
        selectedUsers.setText(users);
      }

      @Override
      public void click(int p, int tag) {
      }
    });
    back.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        dismissSearch();
      }
    });
    del.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        edit.setText("");
      }
    });
  }

  void dismissSearch() {
    bar.getViewByAction(defalutAction).setVisibility(View.VISIBLE);
    bar.getViewByAction(searchAction).setVisibility(View.GONE);
    search.setVisibility(View.GONE);
    scroll.setAnimation(AnimationUtils.makeInAnimation(RentCarActivity.this, false));
    scroll.setVisibility(View.VISIBLE);
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case RentCarPresenter.USERS: {
        userInfos.clear();
        userInfos.addAll((List<UserInfo>) object);
        adapter.notifyDataSetChanged();
        break;
      }
      case RentCarPresenter.USEREMPTY: {
        ViseLog.d(object);
        userInfos.clear();
        adapter.notifyDataSetChanged();
        break;
      }
      case RentCarPresenter.PROJECTS: {
        loadingDialog.dismiss();
        projectInfos = (List<ProjectInfo>) object;
        List<String> names = new ArrayList<>();
        for (ProjectInfo projectInfo : projectInfos) {
          names.add(projectInfo.getProject_name());
        }
        new Builder(this).items(names).title("项目选择")
            .itemsCallbackSingleChoice(selectProjectIndex, new ListCallbackSingleChoice() {
              @Override
              public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                  CharSequence text) {
                selectProjectIndex = which;
                rentCarProject.setText(text);
                rentCarProject.setTextColor(getColor(R.color.black));
                return true;
              }
            })
            .positiveText("选择")
            .negativeText("取消").show();
        break;
      }
      case BasePresenter.UPDATESUCCESS: {
        new CcDialog(RentCarActivity.this, CcDialog.SUCCESS_DIALOG).setMessage("提交成功")
            .setCancelListener(
                new CancelListener() {
                  @Override
                  public void onClick(CcDialog dialog) {
                    setResult(100);
                    finish();
                  }
                }).showDialog();
        break;
      }
    }
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    TextView textView = (TextView) object;
    textView.setTextColor(getColor(R.color.red));
    textView.setText(msg);
  }


  @Override
  public void updateCheckSuccess(Object object) {
    getPresenter().startPostRentCar(
        new RentCarInfo(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class),
            rentCarTime.getText().toString(), projectInfos.get(selectProjectIndex),
            adapter.getSelectedUsers(), rentCarReason.getText().toString(),
            rentCarOwnerName.getText().toString(), rentCarOwnerPhone.getText().toString(),
            rentCarType.getText().toString(), rentCarId.getText().toString(),
            rentCarCostPerDay.getText().toString(), rentCarDays.getText().toString(),
            rentCarCostSum.getText().toString(), rentCarCostConstitute.getText().toString(),
            TimeUtil.calenderToString(Calendar.getInstance(), TimeUtil.YMDHMS)));
  }
}
