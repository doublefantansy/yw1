package hzkj.cc.yw.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import butterknife.BindView;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.badge.BadgeView;
import hzkj.cc.menuview.Content;
import hzkj.cc.menuview.Content.OnSelectItemListenner;
import hzkj.cc.menuview.ListItem;
import hzkj.cc.menuview.MenuPopupView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.contract.RentCarManageContract;
import hzkj.cc.yw.presenter.RentCarManagePresenter;
import hzkj.cc.yw.ui.fragment.RentCarEachFragment;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;
import java.util.List;

public class RentCarManageActivity extends BaseActivity<RentCarManageContract.Presenter> implements
    RentCarManageContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
  List<BottomChild> bottomChildren = new ArrayList<>();

  @Override
  RentCarManageContract.Presenter createPresenter() {
    return new RentCarManagePresenter();
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  public void getData() {
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ((RentCarEachFragment) bottomChildren.get(bottomNavigation.getCurrentIndex()).getFragment())
        .doOnVisible();
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_rentcarmanage;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void initView() {
    bottomChildren
        .add(new BottomChild("待审核", RentCarEachFragment.newInstance(RentCarInfo.HASNOTREVIEW),
            getDrawable(R.drawable.ic_qiyong_2), getDrawable(R.drawable.ic_qiyong)));
    bottomChildren
        .add(new BottomChild("审核通过", RentCarEachFragment.newInstance(RentCarInfo.REVIEWSUCCESS),
            getDrawable(R.drawable.ic_yiguidang_2), getDrawable(R.drawable.ic_yiguidang)));
    bottomChildren
        .add(new BottomChild("审核不通过", RentCarEachFragment.newInstance(RentCarInfo.REVIEWFAIL),
            getDrawable(R.drawable.ic_yitingyong_2), getDrawable(R.drawable.ic_yitingyong)));
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), bottomChildren, 0);
    bottomNavigation.setTextColors(new ArrayList<Integer>() {{
//      add(getColor(R.color.bottom_deep_blue));
      add(getColor(R.color.bottom_blue));
      add(getColor(R.color.bottom_green));
      add(getColor(R.color.bottom_red));
    }});
    bar.setTitle("租车管理");
    bar.addAction(new TitleBar.Action() {
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
        MenuPopupView menuPopupView = new MenuPopupView(RentCarManageActivity.this, Content.LIST,
            new ArrayList<ListItem>() {{
              add(new ListItem(R.drawable.ic_xinzeng, "租车申请"));
//              add(new ListItem(R.drawable.ic_shenhe_5, "租车审核"));
            }});
        menuPopupView.setArrowLocation(Content.ARROW_RIGHT);
        menuPopupView.showDown(view);
        menuPopupView.setSelectItemListenner(new OnSelectItemListenner() {
          @Override
          public void onSelectItem(int position) {
            if (position == 0) {
              UiUtil.jumpToActivity(RentCarManageActivity.this, RentCarActivity.class, null, false,
                  true, 110);
            }
          }
        });
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
    ViseLog.d(getIntent().getIntExtra("count", 0));
    new BadgeView(this).bindTarget(bottomChildren.get(1).getView())
        .setBadgeNumber(Integer.valueOf(getIntent().getIntExtra("count", 0)))
        .setGravityOffset(10, 0, true);
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
