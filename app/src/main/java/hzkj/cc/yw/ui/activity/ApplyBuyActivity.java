package hzkj.cc.yw.ui.activity;

import android.support.annotation.NonNull;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallback;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallbackSingleChoice;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

import hzkj.cc.library.LoadingDialog;
import hzkj.cc.library.SmallLoading;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.bean.ProjectInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.menuview.Content;
import hzkj.cc.menuview.ListItem;
import hzkj.cc.menuview.MenuPopupView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.mybottomnavigation.OnClickBottomChildListener;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.contract.ApplyBuyContract;
import hzkj.cc.yw.presenter.ApplyBuyPresenter;
import hzkj.cc.yw.ui.fragment.ApplyBuyEachFragment;
import hzkj.cc.yw.ui.fragment.ApplyBuySearchFragment;
import hzkj.cc.yw.utils.TimeUtil;

public class ApplyBuyActivity extends BaseActivity<ApplyBuyContract.Presenter> implements
    ApplyBuyContract.View {

  @BindView(R.id.relativeLayout)
  RelativeLayout layout;
  @BindView(R.id.chooseStartDate)
  ButtonView chooseStartDate;
  @BindView(R.id.displayStartTime)
  TextView displayStartTime;
  @BindView(R.id.chooseEndDate)
  ButtonView chooseEndDate;
  @BindView(R.id.displayEndTime)
  TextView displayEndTime;
  @BindView(R.id.reset)
  ButtonView reset;
  @BindView(R.id.submit)
  ButtonView submit;
  @BindView(R.id.chooseAgency)
  ButtonView chooseAgency;
  @BindView(R.id.displayAgency)
  TextView displayAgency;
  @BindView(R.id.chooseStatus)
  ButtonView chooseStatus;
  @BindView(R.id.displayStatus)
  TextView displayStatus;
  TitleBar.Action action;
  @BindView(R.id.bottomNavigation)
  MyBottomNavigation myBottomNavigation;

  List<BottomChild> bottomChildren;
  ApplyBuySearchFragment fragment;
  LoadingDialog loadingDialog;
  List<ProjectInfo> projectInfos;
  int selectedAgencyIndex = 0;
  int selectedStatusIndex = 0;
  List<String> statusNames;

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_apply_buy;
  }

  @Override
  public int getDrawLayout() {
    return R.layout.draw_layout_apply_buy;
  }

  @Override
  public void initView() {
    myBottomNavigation.setTextColors(new ArrayList<Integer>() {{
      add(getColor(R.color.bottom_deep_blue));
      add(getColor(R.color.bottom_blue));
      add(getColor(R.color.bottom_green));
      add(getColor(R.color.bottom_red));
    }});
    fragment = new ApplyBuySearchFragment();
    bottomChildren = new ArrayList<BottomChild>() {{
      add(new BottomChild("查询", fragment, getDrawable(R.drawable.ic_sousuo_2),
          getDrawable(R.drawable.ic_sousuo_3)));
      add(new BottomChild("待办", ApplyBuyEachFragment.getInstance(Procedure.isUse),
          getDrawable(R.drawable.ic_qiyong_2), getDrawable(R.drawable.ic_qiyong)));
      add(new BottomChild("已归档", ApplyBuyEachFragment.getInstance(Procedure.isDone),
          getDrawable(R.drawable.ic_yiguidang_2), getDrawable(R.drawable.ic_yiguidang)));
      add(new BottomChild("已中止", ApplyBuyEachFragment.getInstance(Procedure.isAbandon),
          getDrawable(R.drawable.ic_yitingyong_2), getDrawable(R.drawable.ic_yitingyong)));
    }};
    myBottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 1);
    myBottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
      @Override
      public void onClick(BottomChild bottomChild, int position) {
        if (position != 0) {
          bar.getViewByAction(action)
              .setVisibility(View.GONE);
        } else {
          bar.getViewByAction(action)
              .setVisibility(View.VISIBLE);
        }
      }
    });
    bar.setTitle("申购查询");
    action = new TitleBar.Action() {
      @Override
      public String getText() {
        return null;
      }

      @Override
      public int getDrawable() {
        return R.drawable.ic_search;
      }

      @Override
      public void performAction(View view) {
        MenuPopupView menuPopupView = new MenuPopupView(ApplyBuyActivity.this, Content.LIST,
            new ArrayList<ListItem>() {{
              add(new ListItem(R.drawable.ic_riqichaxun, "综合查询"));
              add(new ListItem(R.drawable.ic_wuliudanhaosaoma, "单号查询"));
            }});
        menuPopupView.setArrowLocation(Content.ARROW_RIGHT);
        menuPopupView.setSelectItemListenner(new Content.OnSelectItemListenner() {
          @Override
          public void onSelectItem(int position) {
            if (position == 0) {
              drawerLayout.openDrawer(Gravity.RIGHT);
            } else {
//              new MaterialDialog.Builder(ApplyBuyActivity.this)
//                  .inputType(InputType.TYPE_CLASS_NUMBER)
//                  .title("单号查询")
//                  .input("请输入查询单号", "", new MaterialDialog.InputCallback() {
//                    @Override
//                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
//                      fragment.notifyGetDataByTime(Integer.valueOf(input + ""));
//                    }
//                  })
//                  .positiveText("提交")
//                  .negativeText("取消")
//                  .build()
//                  .show();
            }
          }
        });
        menuPopupView.showDown(view);
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
    bar.addAction(action);
    bar.getViewByAction(action)
        .setVisibility(View.GONE);
    chooseStartDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(ApplyBuyActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            displayStartTime.setText(TimeUtil.dateToString(date, TimeUtil.YMDHMS));
          }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
          @Override
          public void onTimeSelectChanged(Date date) {
          }
        })
            .setType(TimePickerType.ALL)
            .setTitleText("起始日期选择")
            .build()
            .show();
      }
    });
    chooseEndDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(ApplyBuyActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            displayEndTime.setText(TimeUtil.dateToString(date, TimeUtil.YMDHMS));
          }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
          @Override
          public void onTimeSelectChanged(Date date) {
            Log.i("pvTime", "onTimeSelectChanged");
          }
        })
            .setType(TimePickerType.ALL)
            .setTitleText("结束时间选择")
            .build()
            .show();
      }
    });
    reset.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        displayStartTime.setText("");
        displayEndTime.setText("");
        displayAgency.setText("");
        displayStatus.setText("");
      }
    });
    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().checkDate(displayStartTime.getText()
            .toString(), displayEndTime.getText()
            .toString(), displayAgency.getText().toString(), displayStatus.getText().toString());
      }
    });
    loadingDialog = new LoadingDialog(this);

    chooseAgency.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        loadingDialog.show();
        getPresenter().startGetProjects();
      }
    });
    chooseStatus.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        new MaterialDialog.Builder(ApplyBuyActivity.this).title("选择查询状态").items(statusNames)
            .itemsCallbackSingleChoice(
                selectedStatusIndex, new ListCallbackSingleChoice() {
                  @Override
                  public boolean onSelection(MaterialDialog dialog, View itemView, int which,
                      CharSequence text) {
                    selectedStatusIndex = which;
                    displayStatus.setText(text);
                    return true;
                  }
                })
            .show();
      }
    });
    statusNames = new ArrayList<>();
    statusNames.add("已完结");
    statusNames.add("未完结");
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    loadingDialog.dismiss();
    projectInfos = (List<ProjectInfo>) object;
    List<String> projectNames = new ArrayList<>();
    for (ProjectInfo projectInfo : projectInfos) {
      projectNames.add(projectInfo.getProject_name());
    }
    new MaterialDialog.Builder(this).title("选择办事处").items(projectNames).itemsCallbackSingleChoice(
        selectedAgencyIndex, new ListCallbackSingleChoice() {
          @Override
          public boolean onSelection(MaterialDialog dialog, View itemView, int which,
              CharSequence text) {
            selectedAgencyIndex = which;
            displayAgency.setText(text);
            return true;
          }
        }).show();

  }

  @Override
  public void updateCheckSuccess(Object object) {
    drawerLayout.closeDrawer(Gravity.RIGHT);
    fragment.notifyGetDataByTime(displayStartTime.getText()
            .toString(), displayEndTime.getText()
            .toString(), displayAgency.getText().toString().equals("") ? 0 :
            projectInfos.get(selectedAgencyIndex).getProject_id(),
        displayStatus.getText().toString().equals("") ? -1 : selectedStatusIndex == 0 ? 6 : 0);
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  ApplyBuyContract.Presenter createPresenter() {
    return new ApplyBuyPresenter();
  }
}
