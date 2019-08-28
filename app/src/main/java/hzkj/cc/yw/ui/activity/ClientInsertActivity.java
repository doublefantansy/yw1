package hzkj.cc.yw.ui.activity;

import android.support.annotation.NonNull;
import android.view.View;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.validation.METValidator;
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ClientTypeInfo;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.ProjectTypeInfo;
import hzkj.cc.yw.contract.ClientInsertContract;
import hzkj.cc.yw.presenter.ClientInsertPresenter;

public class ClientInsertActivity extends BaseActivity<ClientInsertContract.Presenter> implements
    ClientInsertContract.View {

  @Override
  public void getData() {
  }

  @BindView(R.id.clientNameEdit)
  MaterialEditText clientNameEdit;
  @BindView(R.id.clientTypeChoose)
  MaterialSpinner clientTypeChoose;
  @BindView(R.id.projectTypeChoose)
  MaterialSpinner projectTypeChoose;
  @BindView(R.id.projectChoose)
  MaterialSpinner projectChoose;
  @BindView(R.id.contactPersonEdit)
  MaterialEditText contactPersonEdit;
  @BindView(R.id.contactPersonSectionEdit)
  MaterialEditText contactPersonSectionEdit;
  @BindView(R.id.contactPersonTelEdit)
  MaterialEditText contactPersonTelEdit;
  @BindView(R.id.contactPersonJobEdit)
  MaterialEditText contactPersonJobEdit;
  @BindView(R.id.addressEdit)
  MaterialEditText addressEdit;
  List<ClientTypeInfo> clientTypeInfos;
  List<ProjectInfo> projectInfos;
  List<ProjectTypeInfo> projectTypeInfos;
  List<String> projectTypes = new ArrayList<String>() {{
    add("");
  }};
  List<String> projectNames = new ArrayList<String>() {{
    add("");
  }};
  List<String> clientTypeNames = new ArrayList<String>() {{
    add("");
  }};

  //    private boolean first = true;
  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  ClientInsertContract.Presenter createPresenter() {
    return new ClientInsertPresenter();
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_client_insert;
  }

  @Override
  public void initView() {
    projectChoose.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
      @Override
      public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        view.setError(null);
      }
    });
    projectTypeChoose.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
      @Override
      public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        view.setError(null);
      }
    });
    clientTypeChoose.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
      @Override
      public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        view.setError(null);
      }
    });
    bar.addAction(new TitleBar.Action() {
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
        getPresenter().cheak(clientNameEdit, clientTypeChoose, projectTypeChoose, projectChoose);
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
    clientNameEdit.addValidator(new METValidator("不能为空") {
      @Override
      public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        if (text.toString()
            .equals("")) {
          return false;
        }
        return true;
      }
    });
    projectTypeChoose.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
      @Override
      public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
        if (position != 0) {
          getPresenter().startGetProjects(projectTypeInfos.get(position - 1)
              .getProjectType_code());
        } else {
          projectNames.clear();
          projectChoose.setItems(projectNames);
        }
      }
    });
  }

  @Override
  public void initData() {
    getPresenter().startGetClientTypeInfos();
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    switch (tag) {
      case 0: {
        projectInfos = (List<ProjectInfo>) object;
        for (ProjectInfo projectInfo : projectInfos) {
          projectNames.add(projectInfo.getProject_name());
        }
        projectChoose.setItems(projectNames);
        break;
      }
      case 1: {
        clientTypeInfos = (List<ClientTypeInfo>) object;
        for (ClientTypeInfo clientTypeInfo : clientTypeInfos) {
          clientTypeNames.add(clientTypeInfo.getCustomerType_name());
        }
        clientTypeChoose.setItems(clientTypeNames);
        getPresenter().startGetProjectTypeInfos();
        break;
      }
      case 2: {
        EventBus.getDefault()
            .postSticky(1);
        finish();
        break;
      }
      case 4: {
        projectNames.clear();
        projectChoose.setItems(projectNames);
        break;
      }
      case 5: {
        projectTypeInfos = (List<ProjectTypeInfo>) object;
        for (ProjectTypeInfo projectTypeInfo : projectTypeInfos) {
          projectTypes.add(projectTypeInfo.getProjectType_name());
        }
        projectTypeChoose.setItems(projectTypes);
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
//        getPresenter().startInsertClientInfo(clientNameEdit.getText()
//                .toString(), clientTypeInfos.get(clientTypeChoose.getSelectedIndex() - 1)
//                .getCustomerType_code(), projectInfos.get(projectChoose.getSelectedIndex() - 1)
//                .getProject_code(), contactPersonEdit.getText()
//                .toString(), contactPersonSectionEdit.getText()
//                .toString(), contactPersonJobEdit.getText()
//                .toString(), addressEdit.getText()
//                .toString(), contactPersonTelEdit.getText()
//                .toString(), projectInfos.get(projectTypeChoose.getSelectedIndex() - 1)
//                .getProject_type());
  }
}
