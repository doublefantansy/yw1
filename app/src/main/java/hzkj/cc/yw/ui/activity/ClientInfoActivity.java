package hzkj.cc.yw.ui.activity;

import android.os.Bundle;
import butterknife.BindView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.contract.ClientInfoContract;
import hzkj.cc.yw.presenter.ClientInfoPresenter;

public class ClientInfoActivity extends BaseActivity<ClientInfoContract.Presenter> implements
    ClientInfoContract.View {

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
  private ClientInfo clientInfo;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  ClientInfoContract.Presenter createPresenter() {
    return new ClientInfoPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_client_info;
  }

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public void initView() {
    bar.setTitle("客户详情");
  }

  @Override
  public void initData() {
//    clientInfo = GsonUtil
//        .stringToObject(getIntent().getStringExtra("clientInfo"), ClientInfo.class);
//    Field[] fields = clientInfo.getClass()
//        .getDeclaredFields();
//    for (Field field : fields) {
//      try {
//        field.setAccessible(true);
//        String methodName = field.getName()
//            .substring(0, 1)
//            .toUpperCase() + field.getName()
//            .substring(1);
//        Method get = clientInfo.getClass()
//            .getMethod("get" + methodName);
//        Method set = clientInfo.getClass()
//            .getMethod("set" + methodName, String.class);
//        if (get.invoke(clientInfo) == null) {
//          set.invoke(clientInfo, "未设置");
//        }
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
//    clientName.setRightString(clientInfo.getCustomer_name());
//    clientType.setRightString(clientInfo.getCustomerType_name());
//    projectType.setRightString(clientInfo.getProjectType_name());
//    projectName.setRightString(clientInfo.getProject_name());
////    contactPerson.setRightString(clientInfo.getCustomer_contact());
////    contactPersonSection.setRightString(clientInfo.getCustomer_contactDept());
////    contactPersonTel.setRightString(clientInfo.getCustomer_phone());
////    contactPersonJob.setRightString(clientInfo.getCustomer_job());
//    address.setRightString(clientInfo.getCustomer_address());
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
  }


  @Override
  public void updateCheckSuccess(Object object) {
  }
}
