package hzkj.cc.yw.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import com.xuexiang.xui.widget.actionbar.TitleBar.Action;
import hzkj.cc.mybottomnavigation.*;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.*;
import hzkj.cc.yw.contract.WorkOrderContract;
import hzkj.cc.yw.contract.WorkOrderContract.Presenter;
import hzkj.cc.yw.presenter.WorkOrderPresenter;
import hzkj.cc.yw.ui.fragment.*;
import hzkj.cc.yw.utils.UiUtil;
import java.util.*;

public class WorkOrderActivity extends BaseActivity<WorkOrderContract.Presenter> implements
    WorkOrderContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
  List<BottomChild> fragments;

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
    return new WorkOrderPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ((WorkOrderEachFragment) fragments.get(bottomNavigation.getCurrentIndex()).getFragment())
        .refresh();
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_work_order;
  }

  @Override
  public void initView() {
    bar.setTitle("工单管理");
    bar.addAction(new Action() {
      @Override
      public String getText() {
        return null;
      }

      @Override
      public int getDrawable() {
        return R.drawable.ic_xinshenqing;
      }

      @Override
      public void performAction(View view) {
        UiUtil.jumpToActivity(WorkOrderActivity.this, WorkOrderInsertActivity.class, null, false,
            true, 10);
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
    fragments = new ArrayList<BottomChild>() {{
      add(new BottomChild("未完结", WorkOrderEachFragment.newInstance(WorkOrderInfo.USEING),
          getDrawable(R.drawable.ic_qiyong_2), getDrawable(R.drawable.ic_qiyong)));
      add(new BottomChild("已完结", WorkOrderEachFragment.newInstance(WorkOrderInfo.END),
          getDrawable(R.drawable.ic_yitingyong_2), getDrawable(R.drawable.ic_yitingyong)));
    }};
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), fragments, 0);
    bottomNavigation.setTextColors(new ArrayList<Integer>() {{
      add(getColor(R.color.bottom_blue));
      add(getColor(R.color.bottom_red));
    }});
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
