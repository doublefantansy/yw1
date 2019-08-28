package hzkj.cc.yw.ui.activity;

import static me.iwf.photopicker.PhotoPicker.DEFAULT_MAX_COUNT;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.xuexiang.xui.widget.actionbar.TitleBar.Action;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.Builder;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.InputCallback;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallbackSingleChoice;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;
import hzkj.cc.gridview.Util;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.WorkOrderInsertContract;
import hzkj.cc.yw.contract.WorkOrderInsertContract.Presenter;
import hzkj.cc.yw.presenter.WorkOrderInsertPresenter;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPickerActivity;

public class WorkOrderInsertActivity extends
    BaseActivity<WorkOrderInsertContract.Presenter> implements WorkOrderInsertContract.View {

  @BindView(R.id.info)
  LinearLayout info;
  @BindView(R.id.scroll)
  ScrollView scroll;
  @BindView(R.id.agency)
  TextView agency;
  @BindView(R.id.record)
  MultiLineEditText record;
  @BindView(R.id.downRecord)
  ImageView downRecord;
  @BindView(R.id.descpition)
  MultiLineEditText descpition;
  @BindView(R.id.downDescpition)
  ImageView downDescpition;
  @BindView(R.id.chooseProject)
  ImageView chooseProject;
  @BindView(R.id.project)
  TextView project;
  @BindView(R.id.descpitionTitle)
  RelativeLayout descpitionTitle;
  @BindView(R.id.descpitionPart)
  RelativeLayout descpitionPart;
  @BindView(R.id.recordPart)
  RelativeLayout recordPart;
  @BindView(R.id.chooseClient)
  ImageView chooseClient;
  @BindView(R.id.clientName)
  TextView clientName;
  @BindView(R.id.contactPart)
  LinearLayout contactPart;
  @BindView(R.id.clientPart)
  LinearLayout clientPart;
  @BindView(R.id.chooseContactPerson)
  ImageView chooseContactPerson;
  @BindView(R.id.contactPersonName)
  TextView contactPersonName;
  @BindView(R.id.contactPersonTel)
  TextView contactPersonTel;
  @BindView(R.id.clientAddress)
  TextView clientAddress;
  @BindView(R.id.chooseErrorGrade)
  ImageView chooseErrorGrade;
  @BindView(R.id.errorGrade)
  TextView errorGrade;
  @BindView(R.id.chooseDevice)
  ImageView chooseDevice;
  @BindView(R.id.deviceModel)
  TextView deviceModel;
  @BindView(R.id.deviceName)
  TextView deviceName;
  @BindView(R.id.recordTitle)
  RelativeLayout recordTitle;
  @BindView(R.id.inputPart)
  LinearLayout inputPart;
  @BindView(R.id.simpleDescription)
  ImageView simpleDescription;
  @BindView(R.id.simpleDescriptionDisplay)
  TextView simpleDescriptionDisplay;
  @BindView(R.id.chooseEndTime)
  ImageView chooseEndTime;
  @BindView(R.id.endTime)
  TextView endTime;
  @BindView(R.id.pic)
  ImageView pic;
  @BindView(R.id.picDisplay)
  TextView picDisplay;
  @BindView(R.id.projectEmpty)
  ImageView projectEmpty;
  @BindView(R.id.clientEmpty)
  ImageView clientEmpty;
  @BindView(R.id.contactEmpty)
  ImageView contactEmpty;
  @BindView(R.id.simpleDescriptionEmpty)
  ImageView simpleDescriptionEmpty;
  @BindView(R.id.endTimeEmpty)
  ImageView endTimeEmpty;
  @BindView(R.id.errorGradeEmpty)
  ImageView errorGradeEmpty;
  @BindView(R.id.deviceEmpty)
  ImageView deviceEmpty;
  @BindView(R.id.descpitionEmpty)
  ImageView descpitionEmpty;
  @BindView(R.id.picEmpty)
  ImageView picEmpty;
  private TranslateAnimation showAnim;
  private TranslateAnimation hideAnim;
  LoadingDialog loadingDialog;
  int selectedProjectIndex = 0;
  int selectedClientIndex = 0;
  int selectedContactIndex = 0;
  int selectedErrorGradeIndex = 0;
  int selectedDeviceIndex = 0;
  int desCount = 0;
  int recordCount = 0;
  String simpleDescptionText = "";
  List<File> files = new ArrayList<>();
  List<ImageView> emptyImageViews;
  List<TextView> textViews;

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == PhotoPickerActivity.RESULT_OK) {
      files.clear();
      for (String s : data.getExtras().getStringArrayList(PhotoPicker.KEY_SELECTED_PHOTOS)) {
        files.add(new File(s));
      }
      picDisplay
          .setText(
              "已选择" + files.size()
                  + "张");
    }
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  Presenter createPresenter() {
    return new WorkOrderInsertPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_work_order_insert;
  }

  @Override
  public void initView() {
    bar.addAction(new Action() {
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
        getPresenter().check(new ArrayList<String>() {{
          add(project.getText().toString());
          add(clientName.getText().toString());
          add(contactPersonName.getText().toString());
          add(simpleDescptionText);
          add(endTime.getText().toString());
          add(errorGrade.getText().toString());
          add(deviceName.getText().toString());
          add(descpition.getContentText());
          add(picDisplay.getText().toString());
        }});
//        workOrderInfo = new WorkOrderInfo(
//            StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptId(),);
      }

      @Override
      public int leftPadding() {
        return 0;
      }

      @Override
      public int rightPadding() {
        return 0;
      }
    });
    loadingDialog = new LoadingDialog(this);
    showAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
    hideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 1.0f,
        Animation.RELATIVE_TO_SELF, 0.0f,
        Animation.RELATIVE_TO_SELF, 0.0f);
    hideAnim.setDuration(500);
    showAnim.setDuration(500);
    bar.setTitle("创建工单");
    agency.setText(
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptName());
    chooseProject.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startChooseProject(
            StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptId());
      }
    });
    chooseClient.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startChooseClient(selectedProjectIndex);
      }
    });
    chooseContactPerson.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startChooseClientContactPerson(selectedClientIndex);
      }
    });
    chooseErrorGrade.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startChooseErrorGrade();
      }
    });
    chooseDevice.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startChooseDevice(selectedProjectIndex);
      }
    });
    downDescpition.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        desCount++;
        descpitionPart.startAnimation(desCount % 2 == 1 ? showAnim : hideAnim);
        hideAnim.setAnimationListener(new AnimationListener() {
          @Override
          public void onAnimationStart(Animation animation) {
          }

          @Override
          public void onAnimationEnd(Animation animation) {
            descpitionPart.setVisibility(View.GONE);
          }

          @Override
          public void onAnimationRepeat(Animation animation) {
          }
        });
        if (desCount % 2 == 1) {
          descpitionPart.setVisibility(View.VISIBLE);
        }
        scroll.post(new Runnable() {
          @Override
          public void run() {
            scroll.smoothScrollTo(0,
                (descpitionPart.getBottom() + info.getMeasuredHeight() + Util
                    .dipTopx(WorkOrderInsertActivity.this, 12) -
                    getWindowManager().getDefaultDisplay().getHeight() + bar.getHeight()));
          }
        });
      }
    });
    downRecord.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        recordCount++;
        recordPart.startAnimation(recordCount % 2 == 1 ? showAnim : hideAnim);
        hideAnim.setAnimationListener(new AnimationListener() {
          @Override
          public void onAnimationStart(Animation animation) {
          }

          @Override
          public void onAnimationEnd(Animation animation) {
            recordPart.setVisibility(View.GONE);
          }

          @Override
          public void onAnimationRepeat(Animation animation) {
          }
        });
        if (recordCount % 2 == 1) {
          recordPart.setVisibility(View.VISIBLE);
        }
        scroll.post(new Runnable() {
          @Override
          public void run() {
            scroll.smoothScrollTo(0,
                (recordPart.getBottom() + info.getMeasuredHeight() + Util
                    .dipTopx(WorkOrderInsertActivity.this, 12) -
                    getWindowManager().getDefaultDisplay().getHeight() + bar.getHeight()));
          }
        });
      }
    });
    simpleDescription.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(WorkOrderInsertActivity.this).title("报障简要描述")
            .input("请输入", simpleDescptionText,
                new InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    simpleDescptionText = input.toString();
                    simpleDescriptionDisplay.setText(simpleDescptionText);
                  }
                }).show();
      }
    });
    chooseEndTime.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(WorkOrderInsertActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            endTime.setText(TimeUtil.dateToString(date, TimeUtil.YMD));
          }
        }).build().show();
      }
    });
    pic.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        List<String> strings = new ArrayList<>();
        for (File file : files) {
          strings.add(file.getPath());
        }
        PhotoPicker.builder()
            .setPhotoCount(DEFAULT_MAX_COUNT)
            .setSelected((ArrayList<String>) strings)
            .start(WorkOrderInsertActivity.this);
      }
    });
    emptyImageViews = new ArrayList<ImageView>() {{
      add(projectEmpty);
      add(clientEmpty);
      add(contactEmpty);
      add(simpleDescriptionEmpty);
      add(endTimeEmpty);
      add(errorGradeEmpty);
      add(deviceEmpty);
      add(descpitionEmpty);
      add(picEmpty);
    }};
    textViews = new ArrayList<TextView>() {{
      add(project);
      add(clientName);
      add(contactPersonName);
      add(simpleDescriptionDisplay);
      add(endTime);
      add(errorGrade);
      add(deviceName);
      add(descpition.getEditText());
      add(picDisplay);
    }};
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(final Object object, int tag) {
    if (tag == WorkOrderInsertPresenter.GETPROJECTSUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items((Collection) object)
          .itemsCallbackSingleChoice(selectedProjectIndex,
              new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  selectedProjectIndex = which;
                  project.setText(text);
//              selectedProjectId = projectInfos.get(which).getProject_id();
//                  clientPart.startAnimation(showAnim);
//                  clientPart.setVisibility(View.VISIBLE);
                  if (clientPart.getVisibility() == View.GONE) {
                    clientPart.startAnimation(showAnim);
                    clientPart.setVisibility(View.VISIBLE);
                  } else {
                    clientAddress.setText("");
                    clientName.setText("");
                    contactPersonName.setText("");
                    contactPersonTel.setText("");
                  }
                  return true;
                }
              }).title("项目选择").show();
    } else if (tag == WorkOrderInsertPresenter.GETCLIENTSUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items(((List<List<String>>) object).get(0))
          .itemsCallbackSingleChoice(selectedClientIndex,
              new ListCallbackSingleChoice() {
                @Override
                public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                    CharSequence text) {
                  selectedClientIndex = which;
                  clientName.setText(text);
                  clientAddress.setText(((List<List<String>>) object).get(1).get(which));
                  if (contactPart.getVisibility() == View.GONE) {
                    contactPart.startAnimation(showAnim);
                    contactPart.setVisibility(View.VISIBLE);
                  } else {
                    contactPersonName.setText("");
                    contactPersonTel.setText("");
                  }
                  return true;
                }
              }).title("报修单位选择").show();
    } else if (tag == WorkOrderInsertPresenter.GETCLIENTCONTACTPERSONSUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items(((List<List<String>>) object).get(0))
          .itemsCallbackSingleChoice(selectedContactIndex, new ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                CharSequence text) {
              selectedContactIndex = which;
              contactPersonName.setText(text);
              contactPersonTel.setText(((List<List<String>>) object).get(1).get(which));
              if (inputPart.getVisibility() == View.GONE) {
                inputPart.startAnimation(showAnim);
                inputPart.setVisibility(View.VISIBLE);
              }
              return true;
            }
          }).title("联系人选择").show();
    } else if (tag == WorkOrderInsertPresenter.GETPROJECTEMPTY) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this)
          .iconRes(R.drawable.ic_gantanhao)
          .title("项目选择")
          .content("无可选项目").show();
    } else if (tag == WorkOrderInsertPresenter.GETCLIENTEMPTY) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this)
          .iconRes(R.drawable.ic_gantanhao)
          .title("客户选择")
          .content("无可选客户,请重新选择项目").show();
    } else if (tag == WorkOrderInsertPresenter.GETCLIENTCONTACTPERSONEMPTY) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this)
          .iconRes(R.drawable.ic_gantanhao)
          .title("联系人选择")
          .content("无可选联系人,请重新选择客户").show();
    } else if (tag == WorkOrderInsertPresenter.GETERRORGRADESUCCESS) {
      loadingDialog.dismiss();
      new MaterialDialog.Builder(this).items((Collection) object).itemsCallbackSingleChoice(
          selectedErrorGradeIndex, new ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                CharSequence text) {
              selectedErrorGradeIndex = which;
              errorGrade.setText(text);
              return true;
            }
          }).title("故障等级选择").show();
    } else if (tag == WorkOrderInsertPresenter.GETDEVICESUCCESS) {
      loadingDialog.dismiss();
      new Builder(this).items(((List<String>) object))
          .itemsCallbackSingleChoice(selectedDeviceIndex, new ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                CharSequence text) {
              loadingDialog.show();
              getPresenter().startLockSupply(SupplyInfo.LOCK, which);
              selectedDeviceIndex = which;
              return true;
            }
          }).title("设备选择").show();
    } else if (tag == WorkOrderInsertPresenter.CREATESUCCESSS) {
      getPresenter().startUploadPic(files, (Integer) object);
    } else if (tag == WorkOrderInsertPresenter.CREATEFAIL) {
      new CcDialog(this, CcDialog.FAIL_DIALOG).setMessage("提交失败,请重试").showDialog();
    } else if (tag == WorkOrderInsertPresenter.LOCKSUCCESS) {
      loadingDialog.dismiss();
      deviceName
          .setText(((List<List<String>>) object).get(0).get(selectedDeviceIndex).split("\\(")[0]);
      deviceModel.setText(((List<List<String>>) object).get(1).get(selectedDeviceIndex));
    } else {
      loadingDialog.dismiss();
      new CcDialog(this, CcDialog.SUCCESS_DIALOG).setCancelListener(new CancelListener() {
        @Override
        public void onClick(CcDialog dialog) {
          finish();
        }
      }).setMessage("创建成功").showDialog();
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
    getPresenter().startCreateWorkOrder(
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment().getDeptId(),
        selectedProjectIndex, selectedClientIndex, selectedContactIndex, simpleDescptionText,
        endTime.getText().toString(), selectedErrorGradeIndex, selectedDeviceIndex,
        descpition.getContentText());
    loadingDialog.show();
  }

  @Override
  public void updateCheckFailUi(String msg, final Object object) {
    emptyImageViews.get((Integer) object).setVisibility(View.VISIBLE);
    textViews.get((Integer) object).addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
      }

      @Override
      public void afterTextChanged(Editable s) {
        if (!s.toString().equals("")) {
          emptyImageViews.get((Integer) object).setVisibility(View.GONE);
        }
      }
    });
  }
}
