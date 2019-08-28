package hzkj.cc.yw.ui.activity;

import android.os.Build.VERSION_CODES;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.configure.TimePickerType;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

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
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.contract.GoToRepairContract;
import hzkj.cc.yw.presenter.GoToRepairPresenter;
import hzkj.cc.yw.ui.fragment.GoToRepairEachFragment;
import hzkj.cc.yw.ui.fragment.GoToRepairSearchFragment;
import hzkj.cc.yw.utils.TimeUtil;


public class GoToRepairActivity extends BaseActivity<GoToRepairContract.Presenter> implements
    GoToRepairContract.View {

  @BindView(R.id.reset)
  ButtonView reset;
  @BindView(R.id.submit)
  ButtonView submit;
  @BindView(R.id.chooseStartDate)
  ButtonView chooseStartDate;
  @BindView(R.id.chooseEndDate)
  ButtonView chooseEndDate;
  @BindView(R.id.displayStartTime)
  TextView displayStartTime;
  @BindView(R.id.displayEndTime)
  TextView displayEndTime;
  @BindView(R.id.timeType)
  RadioGroup timeType;
  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
  TitleBar.Action action;
  GoToRepairSearchFragment fragment;
  int projectId;

  @Override
  GoToRepairContract.Presenter createPresenter() {
    return new GoToRepairPresenter();
  }

  @Override
  public int getDrawLayout() {
    return R.layout.draw_layout_go_to_repair;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_go_to_repair;
  }

  @Override
  public void initView() {
    fragment = new GoToRepairSearchFragment();
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), new ArrayList<BottomChild>() {{
      add(new BottomChild("查询", fragment, getDrawable(R.drawable.ic_sousuo_2),
          getDrawable(R.drawable.ic_sousuo_3)));
      add(new BottomChild("待办", GoToRepairEachFragment.getInstance(1),
          getDrawable(R.drawable.ic_qiyong_2), getDrawable(R.drawable.ic_qiyong)));
      add(new BottomChild("已归档", GoToRepairEachFragment.getInstance(2),
          getDrawable(R.drawable.ic_yiguidang_2), getDrawable(R.drawable.ic_yiguidang)));
      add(new BottomChild("已中止", GoToRepairEachFragment.getInstance(0),
          getDrawable(R.drawable.ic_yitingyong_2), getDrawable(R.drawable.ic_yitingyong)));
    }}, 1);
    bottomNavigation.setTextColors(new ArrayList<Integer>() {{
      add(getColor(R.color.bottom_deep_blue));
      add(getColor(R.color.bottom_blue));
      add(getColor(R.color.bottom_green));
      add(getColor(R.color.bottom_red));
    }});
    bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
      @Override
      public void onClick(BottomChild bottomChild, int position) {
        if (position == 0) {
          bar.getViewByAction(action)
              .setVisibility(View.VISIBLE);
        } else {
          bar.getViewByAction(action)
              .setVisibility(View.GONE);
        }
      }
    });
    bar.setTitle("返修查询");
    action = new TitleBar.Action() {
      @Override
      public String getText() {
        return null;
      }

      @Override
      public int getDrawable() {
        return R.drawable.ic_gengduo_2;
      }

      @Override
      public void performAction(View view) {
        MenuPopupView menuPopupView = new MenuPopupView(GoToRepairActivity.this, Content.LIST,
            new ArrayList<ListItem>() {{
              add(new ListItem(R.drawable.ic_dajianshangpinbaoxiuchaxun, "返修单号查询"));
              add(new ListItem(R.drawable.ic_xiangmuchaxun, "项目查询"));
              add(new ListItem(R.drawable.ic_riqichaxun, "日期查询"));
            }});
        menuPopupView.setSelectItemListenner(new Content.OnSelectItemListenner() {
          @Override
          public void onSelectItem(int position) {
            switch (position) {
              case 0: {
                new MaterialDialog.Builder(GoToRepairActivity.this)
                    .inputType(InputType.TYPE_CLASS_NUMBER)
                    .title("返修单号查询")
                    .input("请输入查询单号", "", new MaterialDialog.InputCallback() {
                      @Override
                      public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        fragment.notifyGetDataById(Integer.valueOf(input.toString()));
                      }
                    })
                    .positiveText("提交")
                    .negativeText("取消")
                    .build()
                    .show();
                break;
              }
              case 1: {
                getPresenter().startGetAgencyList(1);
                break;
              }
              case 2: {
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
              }
              case 3: {
                break;
              }
            }
          }
        });
        menuPopupView.setArrowLocation(Content.ARROW_RIGHT);
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
    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().checkDrawer(displayStartTime.getText()
            .toString(), displayEndTime.getText()
            .toString(), timeType.getCheckedRadioButtonId());
      }
    });
    chooseStartDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(GoToRepairActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            displayStartTime.setText(TimeUtil.dateToString(date, TimeUtil.YMDHMS));
          }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
          @Override
          public void onTimeSelectChanged(Date date) {
            Log.i("pvTime", "onTimeSelectChanged");
          }
        })
            .setType(TimePickerType.ALL)
            .setTitleText("起始时间选择")
            .build()
            .show();
      }
    });
    chooseEndDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(GoToRepairActivity.this, new OnTimeSelectListener() {
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
        displayEndTime.setText("");
        displayStartTime.setText("");
        timeType.clearCheck();
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case GoToRepairPresenter.DEPARTSUCCESS: {
        final List<Department> departments = (List<Department>) object;
        List<String> strings = new ArrayList<>();
        for (Department department : departments) {
          strings.add(department.getDeptName());
        }
        new MaterialDialog.Builder(GoToRepairActivity.this)
            .title("项目查询")
            .items(strings)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int position,
                  CharSequence text) {
                getPresenter().startGetAgencyList(departments.get(position)
                    .getDeptId());
                projectId = departments.get(position)
                    .getDeptId();
              }
            })
            .build()
            .show();
        break;
      }
      case GoToRepairPresenter.PROJECTSUCCESS: {
        final List<ProjectInfo> projectInfos = (List<ProjectInfo>) object;
        List<String> strings = new ArrayList<>();
        for (ProjectInfo projectInfo : projectInfos) {
          strings.add(projectInfo.getProject_name());
        }
        new MaterialDialog.Builder(GoToRepairActivity.this)
            .title("项目查询")
            .items(strings)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int position,
                  CharSequence text) {
                fragment.notifyGetDataByProject(projectInfos.get(position)
                    .getProject_id());
              }
            })
            .build()
            .show();
        break;
      }
      case GoToRepairPresenter.DEPARTMENTEMPTY: {
        getPresenter().startGetProjectList(projectId);
        break;
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
    drawerLayout.closeDrawer(Gravity.RIGHT);
    fragment.notifyGetDataByDate(
        Integer.parseInt((String) drawerLayout.findViewById(timeType.getCheckedRadioButtonId())
            .getTag()), displayStartTime.getText()
            .toString(), displayEndTime.getText()
            .toString());
  }
}
