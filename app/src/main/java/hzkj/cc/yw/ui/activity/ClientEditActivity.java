package hzkj.cc.yw.ui.activity;

import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;

import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.ClientTypeInfo;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.contract.ClientEditContract;
import hzkj.cc.yw.presenter.ClientEditPresenter;
import hzkj.cc.yw.utils.GsonUtil;

public class ClientEditActivity extends BaseActivity<ClientEditContract.Presenter> implements
    ClientEditContract.View {

  @BindView(R.id.clientName)
  SuperTextView clientName;
  @BindView(R.id.clientType)
  SuperTextView clientType;
  @BindView(R.id.projectType)
  SuperTextView projectType;
  @BindView(R.id.projectName)
  SuperTextView projectName;
  @BindView(R.id.contactPerson)
  SuperTextView contactPerson;
  @BindView(R.id.contactPersonSection)
  SuperTextView contactPersonSection;
  @BindView(R.id.contactPersonTel)
  SuperTextView contactPersonTel;
  @BindView(R.id.contactPersonJob)
  SuperTextView contactPersonJob;
  @BindView(R.id.address)
  SuperTextView address;
  List<ClientTypeInfo> clientTypeInfos;
  List<ProjectInfo> projectInfos;
  List<String> projectTypes = new ArrayList<>();
  List<String> projectNames = new ArrayList<>();
  List<String> clientTypeNames = new ArrayList<>();
  ClientInfo clientInfo;

  //    public static final String ENTERPRISE="企业";
  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  ClientEditContract.Presenter createPresenter() {
    return new ClientEditPresenter();
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_client_edit;
  }

  @Override
  public void initView() {
    bar.addAction(new TitleBar.Action() {
      @Override
      public String getText() {
        return "保存";
      }

      @Override
      public int getDrawable() {
        return 0;
      }

      @Override
      public void performAction(View view) {
        getPresenter().startUpdateClientInfo(clientInfo);
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
  }

  @Override
  public void initData() {
    getPresenter().startGetProjects();
    ClientInfo newClientInfo = null;
    clientInfo = GsonUtil
        .stringToObject(getIntent().getStringExtra("clientInfo"), ClientInfo.class);
//    try {
////      newClientInfo = (ClientInfo) clientInfo.clone();
//    } catch (CloneNotSupportedException e) {
//      e.printStackTrace();
//    }
    Field[] fields = newClientInfo.getClass()
        .getDeclaredFields();
    for (Field field : fields) {
      try {
        field.setAccessible(true);
        String methodName = field.getName()
            .substring(0, 1)
            .toUpperCase() + field.getName()
            .substring(1);
        Method get = newClientInfo.getClass()
            .getMethod("get" + methodName);
        Method set = newClientInfo.getClass()
            .getMethod("set" + methodName, String.class);
        if (get.invoke(newClientInfo) == null) {
          set.invoke(newClientInfo, "未设置");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    clientName.setRightString(newClientInfo.getCustomer_name());
    clientType.setRightString(newClientInfo.getCustomerType_name());
    projectType.setRightString(newClientInfo.getProjectType_name());
    projectName.setRightString(newClientInfo.getProject_name());
//    contactPerson.setRightString(newClientInfo.getCustomer_contact());
//    contactPersonSection.setRightString(newClientInfo.getCustomer_contactDept());
//    contactPersonTel.setRightString(newClientInfo.getCustomer_phone());
//    contactPersonJob.setRightString(newClientInfo.getCustomer_job());
    address.setRightString(newClientInfo.getCustomer_address());
    clientName.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("客户名称")
            .inputType(
                InputType.TYPE_CLASS_TEXT)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    clientName.setRightString(input);
                    clientInfo.setCustomer_name(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
    clientType.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        getPresenter().startGetClientTypeInfos();
      }
    });
//
    contactPerson.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("主联系人名字")
            .inputType(
                InputType.TYPE_CLASS_TEXT)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    contactPerson.setRightString(input);
//                    clientInfo.setCustomer_contact(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
    contactPersonSection.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("主联系人部门")
            .inputType(
                InputType.TYPE_CLASS_TEXT)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    contactPersonSection.setRightString(input);
//                    clientInfo.setCustomer_contactDept(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
    contactPersonTel.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("主联系人联系方式")
            .inputType(
                InputType.TYPE_CLASS_NUMBER)
            .inputRange(11, 11)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    contactPersonTel.setRightString(input);
//                    clientInfo.setCustomer_phone(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
    contactPersonJob.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("主联系人岗位")
            .inputType(
                InputType.TYPE_CLASS_TEXT)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    contactPersonSection.setRightString(input);
//                    clientInfo.setCustomer_job(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
    address.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
      @Override
      public void onClickListener() {
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("客户地址")
            .inputType(
                InputType.TYPE_CLASS_TEXT)
            .input(
                "",
                "",
                false, new MaterialDialog.InputCallback() {
                  @Override
                  public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                    address.setRightString(input);
                    clientInfo.setCustomer_address(input.toString());
                  }
                }
            )
            .positiveText("完成")
            .negativeText("我不输了")
            .cancelable(false)
            .show();
      }
    });
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (object instanceof Integer) {
      if (((Integer) object) == 1) {
        EventBus.getDefault()
            .postSticky(1);
        finish();
      }
    } else if (object instanceof List) {
      projectTypes.clear();
      projectNames.clear();
      if (((List) object).get(0) instanceof ProjectInfo) {
        projectInfos = (List<ProjectInfo>) object;
        for (ProjectInfo projectInfo : ((List<ProjectInfo>) object)) {
//                    projectTypes.add(projectInfo.getProjectType_Name());
          projectNames.add(projectInfo.getProject_name());
        }
      } else {
        clientTypeNames.clear();
        clientTypeInfos = (List<ClientTypeInfo>) object;
        for (ClientTypeInfo clientTypeInfo : clientTypeInfos) {
          clientTypeNames.add(clientTypeInfo.getCustomerType_name());
        }
        new MaterialDialog.Builder(ClientEditActivity.this)
            .title("客户类型")
            .items(clientTypeNames)
            .itemsCallback(new MaterialDialog.ListCallback() {
              @Override
              public void onSelection(MaterialDialog dialog, View itemView, int position,
                  CharSequence text) {
                clientType.setRightString(text);
                clientInfo.setCustomer_type(clientTypeInfos.get(position)
                    .getCustomerType_code());
              }
            })
            .
                show();
      }
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
