package hzkj.cc.yw.ui.activity;

import android.view.View;
import butterknife.BindView;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.textview.badge.BadgeView;
import hzkj.cc.menuview.Content;
import hzkj.cc.menuview.ListItem;
import hzkj.cc.menuview.MenuPopupView;
import hzkj.cc.my_fragment_viewpager.FragmentViewPager;
import hzkj.cc.my_fragment_viewpager.OnMovePageListenner;
import hzkj.cc.my_fragment_viewpager.ScrollItem;
import hzkj.cc.yw.R;
import hzkj.cc.yw.contract.DeliverContract;
import hzkj.cc.yw.presenter.DeliverPresenter;
import hzkj.cc.yw.ui.fragment.DeliverProcedureFragment;
import hzkj.cc.yw.ui.fragment.DeliverSingleFragment;
import hzkj.cc.yw.utils.UiUtil;
import java.util.ArrayList;

public class DeliverActivity extends BaseActivity<DeliverContract.Presenter> implements
    DeliverContract.View {

  @BindView(R.id.fragmentViewPager)
  FragmentViewPager fragmentViewPager;
  TitleBar.Action action;

  @Override
  DeliverContract.Presenter createPresenter() {
    return new DeliverPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_deliver;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }


  @Override
  public void initView() {

    fragmentViewPager.setList(new ArrayList<ScrollItem>() {{
      add(new ScrollItem("单独发货", new DeliverSingleFragment()));
      add(new ScrollItem("流程发货", new DeliverProcedureFragment()));
    }}, getSupportFragmentManager());
    bar.setTitle("物流管理");
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
        MenuPopupView menuPopupView = new MenuPopupView(DeliverActivity.this, Content.LIST,
            new ArrayList<ListItem>() {{
              add(new ListItem(R.drawable.ic_fahuo, "物流发货"));
            }});
        menuPopupView.setArrowLocation(Content.ARROW_RIGHT);
        menuPopupView.showDown(view);
        menuPopupView.setSelectItemListenner(new Content.OnSelectItemListenner() {
          @Override
          public void onSelectItem(int position) {
            switch (position) {
              case 0: {
                UiUtil
                    .jumpToActivity(DeliverActivity.this, DeliverInsertActivity.class, null, false,
                        false, -1);
                break;
              }
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
    };
    bar.addAction(action);
    fragmentViewPager.setListenner(new OnMovePageListenner() {
      @Override
      public void click(int i) {
        if (i == 0) {
          bar.getViewByAction(action)
              .setVisibility(View.VISIBLE);
        } else {
          bar.getViewByAction(action)
              .setVisibility(View.GONE);
        }
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
  }
}
