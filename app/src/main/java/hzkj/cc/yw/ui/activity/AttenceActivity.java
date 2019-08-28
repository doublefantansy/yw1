package hzkj.cc.yw.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.ButtonView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectChangeListener;
import com.xuexiang.xui.widget.picker.widget.listener.OnTimeSelectListener;

import java.util.ArrayList;
import java.util.Calendar;
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
import hzkj.cc.yw.contract.AttenceContract;
import hzkj.cc.yw.presenter.AttencePresenter;
import hzkj.cc.yw.ui.fragment.AttenceCalendarFragment;
import hzkj.cc.yw.ui.fragment.AttenceCountFragment;
import hzkj.cc.yw.ui.fragment.AttenceFragment;
import hzkj.cc.yw.utils.TimeUtil;

public class AttenceActivity extends BaseActivity<AttenceContract.Presenter> implements
    AttenceContract.View {

  private View view;
  @BindView(R.id.toolbar)
  TitleBar title;
  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
  //    AttenceFragment.AttenceSwitchInterface attenceSwitchInterface;
  @BindView(R.id.chooseStartDate)
  ButtonView chooseStartDate;
  @BindView(R.id.chooseEndDate)
  ButtonView chooseEndDate;
  @BindView(R.id.displayStartTime)
  TextView displayStartTime;
  @BindView(R.id.displayEndTime)
  TextView displayEndTime;
  @BindView(R.id.submit)
  ButtonView submit;
  @BindView(R.id.reset)
  ButtonView reset;
  TitleBar.Action action;
  AttenceFragment attenceFragment;
  AttenceCountFragment attenceCountFragment;
  MenuPopupView attenceMenu;
  MenuPopupView countMenu;
  List<ListItem> listItems = new ArrayList<>();
  AttenceCalendarFragment attenceCalendarFragment;
//    public void setAttenceSwitchInterface(AttenceFragment.AttenceSwitchInterface attenceSwitchInterface) {
//        this.attenceSwitchInterface = attenceSwitchInterface;
//    }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return R.layout.draw_layout_attence_count;
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    attenceFragment.getData();
    attenceCalendarFragment.getData();
//    attenceCountFragment.getData();
  }

  @Override
  AttenceContract.Presenter createPresenter() {
    return new AttencePresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_attence;
  }

  @Override
  public void initView() {
    bar.setTitle("考勤管理");
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
        if (attenceFragment.isVisible()) {
          attenceMenu.showDown(view);
        } else {
          countMenu.showDown(view);
        }
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
    title.setCustomTitle(view);
    attenceMenu = new MenuPopupView(this, Content.LIST, new ArrayList<ListItem>() {
      {
        add(new ListItem(R.drawable.ic_riqichaxun, "考勤日历"));
      }
    });
    attenceMenu.setSelectItemListenner(new Content.OnSelectItemListenner() {
      @Override
      public void onSelectItem(int position) {
//                attenceFragment.showCalender();
      }
    });
    countMenu = new MenuPopupView(this, Content.LIST, new ArrayList<ListItem>() {
      {
        add(new ListItem(R.drawable.ic_riqi, "日期查询"));
        add(new ListItem(R.drawable.ic_kaoqinbaobiao, "本月查询"));
      }
    });
    countMenu.setSelectItemListenner(new Content.OnSelectItemListenner() {
      @Override
      public void onSelectItem(int position) {
        if (position == 0) {
          drawerLayout.openDrawer(Gravity.RIGHT);
        } else {
          attenceCountFragment
              .getSignalByActivity(TimeUtil.getLimitDateString(Calendar.getInstance()
                      .get(Calendar.YEAR), Calendar.getInstance()
                      .get(Calendar.MONTH), TimeUtil.MONTHSTART, TimeUtil.YMD),
                  TimeUtil.dateToString(new Date(), TimeUtil.YMD));
        }
      }
    });
    attenceMenu.setArrowLocation(Content.ARROW_RIGHT);
    countMenu.setArrowLocation(Content.ARROW_RIGHT);
    List<BottomChild> bottomChildren = new ArrayList<>();
    attenceFragment = new AttenceFragment();
    attenceCountFragment = new AttenceCountFragment();
    attenceCalendarFragment = new AttenceCalendarFragment();
    bar.getViewByAction(action)
        .setVisibility(View.GONE);
    bottomChildren.add(new BottomChild("打卡", attenceFragment,
        getResources().getDrawable(R.drawable.ic_daqia_unselected),
        getResources().getDrawable(R.drawable.ic_daqia)));
    bottomChildren.add(new BottomChild("日历", attenceCalendarFragment,
        getResources().getDrawable(R.drawable.ic_rili_2),
        getResources().getDrawable(R.drawable.ic_rili)));
    bottomChildren.add(new BottomChild("统计", attenceCountFragment,
        getResources().getDrawable(R.drawable.ic_tongji_unselected),
        getResources().getDrawable(R.drawable.ic_tongji)));
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 1);
    bottomNavigation.setOnClickBottomChildListener(new OnClickBottomChildListener() {
      @Override
      public void onClick(BottomChild bottomChild, int position) {
        if (position == 2) {
          bar.getViewByAction(action)
              .setVisibility(View.VISIBLE);
        } else {
          bar.getViewByAction(action)
              .setVisibility(View.GONE);
        }
      }
    });
    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    chooseStartDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(AttenceActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            displayStartTime.setText(TimeUtil.dateToString(date, TimeUtil.YMD));
          }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
          @Override
          public void onTimeSelectChanged(Date date) {
          }
        })
//                        .setType(TimePickerType.)
            .setTitleText("起始日期选择")
            .build()
            .show();
      }
    });
    chooseEndDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        new TimePickerBuilder(AttenceActivity.this, new OnTimeSelectListener() {
          @Override
          public void onTimeSelected(Date date, View v) {
            displayEndTime.setText(TimeUtil.dateToString(date, TimeUtil.YMD));
          }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
          @Override
          public void onTimeSelectChanged(Date date) {
          }
        })
//                        .setType(TimePickerType.)
            .setTitleText("结束日期选择")
            .build()
            .show();
      }
    });
    reset.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        displayStartTime.setText("");
        displayEndTime.setText("");
      }
    });
    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().checkDate(displayStartTime.getText()
            .toString(), displayEndTime.getText()
            .toString());
      }
    });
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }

  @Override
  public void updateCheckSuccess(Object object) {
    attenceCountFragment.getSignalByActivity(displayStartTime.getText()
        .toString(), displayEndTime.getText()
        .toString());
    drawerLayout.closeDrawer(Gravity.RIGHT);
  }
}
