package hzkj.cc.yw.ui.activity;

import android.view.View;
import butterknife.BindView;
import com.xuexiang.xui.widget.actionbar.TitleBar.Action;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog.ListCallback;
import hzkj.cc.library.LoadingDialog;
import hzkj.cc.menuview.Content;
import hzkj.cc.menuview.Content.OnSelectItemListenner;
import hzkj.cc.menuview.ListItem;
import hzkj.cc.menuview.MenuPopupView;
import hzkj.cc.mybottomnavigation.BottomChild;
import hzkj.cc.mybottomnavigation.MyBottomNavigation;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Event;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.contract.ClientManageContract;
import hzkj.cc.yw.contract.ClientManageContract.Presenter;
import hzkj.cc.yw.presenter.ClientManagePresenter;
import hzkj.cc.yw.ui.fragment.ClientFragment;
import hzkj.cc.yw.ui.fragment.ClientSearchFragment;
import hzkj.cc.yw.utils.StorageUtil;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class ClientManageActivity extends BaseActivity<ClientManageContract.Presenter> implements
    ClientManageContract.View {

  @BindView(R.id.bottomNavigation)
  MyBottomNavigation bottomNavigation;
  List<ProjectInfo> projectInfos;
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
  Presenter createPresenter() {
    return new ClientManagePresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_client_manage;
  }

  @Override
  public void initView() {
    loadingDialog = new LoadingDialog(this);
    bar.setTitle("客户管理");
    bar.addAction(new Action() {
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
        MenuPopupView menuPopupView = new MenuPopupView(ClientManageActivity.this, Content.LIST,
            new ArrayList<ListItem>() {{
              add(new ListItem(R.drawable.ic_xiangmuchaxun, "项目查询"));
            }});
        menuPopupView.setArrowLocation(Content.ARROW_RIGHT);
        menuPopupView.showDown(view);
        menuPopupView.setSelectItemListenner(new OnSelectItemListenner() {
          @Override
          public void onSelectItem(int position) {
            getPresenter()
                .startGetProjects(
                    StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class).getDepartment()
                        .getDeptId());
            loadingDialog.show();
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
    bottomNavigation.setTextColors(new ArrayList<Integer>() {{
      add(getColor(R.color.bottom_deep_blue));
      add(getColor(R.color.bottom_client_blue));
    }});
    bottomNavigation.initBottomChildren(getSupportFragmentManager(), new ArrayList<BottomChild>() {{
      add(new BottomChild("查询", ClientSearchFragment.newInstance(),
          getDrawable(R.drawable.ic_sousuo_2),
          getDrawable(R.drawable.ic_sousuo_3)));
      add(new BottomChild("客户", ClientFragment.newInstance(),
          getDrawable(R.drawable.bottom_client_unselected),
          getDrawable(R.drawable.bottom_client_selected)));
    }}, 1);
//
  }

  @Override
  public void initData() {
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    loadingDialog.dismiss();
    projectInfos = (List<ProjectInfo>) object;
    final List<String> names = new ArrayList<>();
    for (ProjectInfo projectInfo : projectInfos) {
      names.add(projectInfo.getProject_name());
    }
    new MaterialDialog.Builder(this).items(names).title("项目选择").itemsCallback(new ListCallback() {
      @Override
      public void onSelection(MaterialDialog dialog, View itemView, int position,
          CharSequence text) {
        EventBus.getDefault()
            .postSticky(new Event(1000, projectInfos.get(position).getProject_id()));
      }
    }).show();
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
