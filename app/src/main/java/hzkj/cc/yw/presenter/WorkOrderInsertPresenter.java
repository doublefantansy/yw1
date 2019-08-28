package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;
import hzkj.cc.yw.bean.ClientContactInfo;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.ErrorGradeInfo;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.WorkOrderInsertContract;
import hzkj.cc.yw.contract.WorkOrderInsertContract.Model;
import hzkj.cc.yw.model.WorkOrderInsertModel;
import hzkj.cc.yw.utils.StorageUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WorkOrderInsertPresenter extends
    BasePresenter<WorkOrderInsertContract.View, WorkOrderInsertContract.Model> implements
    WorkOrderInsertContract.Presenter {

  public static final int GETPROJECTSUCCESS = 0;
  public static final int GETPROJECTEMPTY = 1;
  public static final int GETCLIENTSUCCESS = 2;
  public static final int GETCLIENTEMPTY = 3;
  public static final int GETCLIENTCONTACTPERSONSUCCESS = 4;
  public static final int GETCLIENTCONTACTPERSONEMPTY = 5;
  public static final int GETERRORGRADESUCCESS = 6;
  public static final int GETDEVICESUCCESS = 7;
  public static final int CREATESUCCESSS = 8;
  public static final int CREATEFAIL = 9;
  public static final int UPLOADSUCCESS = 91;
  public static final int LOCKSUCCESS = 911;
  List<ProjectInfo> projectInfos;
  List<SupplyInfo> supplyInfos;
  List<ClientInfo> clientInfos;
  List<ClientContactInfo> clientContactInfos;
  List<ErrorGradeInfo> errorGradeInfos;
  List<String> names;
  List<String> models;

  @Override
  Model createModel() {
    return new WorkOrderInsertModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (tag == WorkOrderInsertModel.CHOOSEPROJECT) {
      if (response.getCode() == RetrofitFactory.NODATA) {
        getView().updateSuccessUi(null, GETPROJECTEMPTY);
      } else {
        projectInfos = (List<ProjectInfo>) response.getData();
        List<String> names = new ArrayList<>();
        for (ProjectInfo projectInfo : projectInfos) {
          names.add(projectInfo.getProject_name());
        }
        getView().updateSuccessUi(names, GETPROJECTSUCCESS);
      }
    } else if (tag == WorkOrderInsertModel.CHOOSECLIENT) {
      if (response.getCode() == RetrofitFactory.NODATA) {
        getView().updateSuccessUi(null, GETCLIENTEMPTY);
      } else {
        clientInfos = (List<ClientInfo>) response.getData();
        List<String> names = new ArrayList<>();
        for (ClientInfo clientInfo : clientInfos) {
          names.add(clientInfo.getCustomer_name());
        }
        List<String> addresss = new ArrayList<>();
        for (ClientInfo clientInfo : clientInfos) {
          addresss.add(clientInfo.getCustomer_address());
        }
        List<List<String>> list = new ArrayList<>();
        list.add(names);
        list.add(addresss);
        getView().updateSuccessUi(list, GETCLIENTSUCCESS);
      }
    } else if (tag == WorkOrderInsertModel.CHOOSECLIENTCONTACTPERSON) {
      if (response.getCode() == RetrofitFactory.NODATA) {
        getView().updateSuccessUi(null, GETCLIENTCONTACTPERSONEMPTY);
      } else {
        clientContactInfos = (List<ClientContactInfo>) response.getData();
        List<String> names = new ArrayList<>();
        for (ClientContactInfo clientContactInfo : clientContactInfos) {
          names.add(clientContactInfo.getContactper_name());
        }
        List<String> phones = new ArrayList<>();
        for (ClientContactInfo clientContactInfo : clientContactInfos) {
          phones.add(clientContactInfo.getContactper_phone());
        }
        List<List<String>> list = new ArrayList<>();
        list.add(names);
        list.add(phones);
        getView().updateSuccessUi(list, GETCLIENTCONTACTPERSONSUCCESS);
      }
    } else if (tag == WorkOrderInsertModel.CHOOSEERRORGRADES) {
      errorGradeInfos = (List<ErrorGradeInfo>) response.getData();
      List<String> names = new ArrayList<>();
      for (ErrorGradeInfo errorGradeInfo : errorGradeInfos) {
        names.add(errorGradeInfo.getGdLevel_name());
      }
      getView().updateSuccessUi(names, GETERRORGRADESUCCESS);
    } else if (tag == WorkOrderInsertModel.CHOOSEDEVICE) {
      supplyInfos = (List<SupplyInfo>) response.getData();
      names = new ArrayList<>();
      models = new ArrayList<>();
      for (SupplyInfo supplyInfo : supplyInfos) {
        names.add(
            supplyInfo.getPutIn_good().getSupplies_name() + "( ********" + supplyInfo
                .getSupplies_serial()
                .substring(supplyInfo.getSupplies_serial().length() - 9,
                    supplyInfo.getSupplies_serial().length() - 1) + ")");
        models.add(supplyInfo.getPutIn_good().getSupplies_model());
      }
      getView().updateSuccessUi(names, GETDEVICESUCCESS);
    } else if (tag == WorkOrderInsertModel.CREATEWORKORDER) {
      if (response.getCode() == RetrofitFactory.INSERTSUCCESS) {
        getView().updateSuccessUi(response.getData(), CREATESUCCESSS);
      } else {
        getView().updateSuccessUi(null, CREATEFAIL);
      }
    } else if (tag == WorkOrderInsertModel.LOCKSUPPLY) {
      if (response.getCode() == RetrofitFactory.UPDATESUCCESS) {
        List<List<String>> list = new ArrayList<>();
        list.add(names);
        list.add(models);
        getView().updateSuccessUi(list, LOCKSUCCESS);
      }
    } else {
      getView().updateSuccessUi(null, UPLOADSUCCESS);
    }
  }

  @Override
  public void startChooseProject(int agencyId) {
    getModel().chooseProject(agencyId);
  }

  @Override
  public void startChooseClient(int projectIndex) {
    getModel().chooseClient(projectInfos.get(projectIndex).getProject_id());
  }

  @Override
  public void startChooseClientContactPerson(int clientIndex) {
    getModel().chooseClientContactPerson(clientInfos.get(clientIndex).getCustomer_id());
  }

  @Override
  public void startChooseErrorGrade() {
    getModel().chooseErrorgGrade();
  }

  @Override
  public void startChooseDevice(int projectIndex) {
    getModel().chooseDevice(projectInfos.get(projectIndex).getProject_id());
  }

  @Override
  public void startCreateWorkOrder(int agencyId, int projectIndex, int clientIndex,
      int contactorIndex, String simpleDescription, String endTime, int errorGradeIndex,
      int deviceIndex, String description) {
    UserInfo userInfo = StorageUtil.getData(StorageUtil.KEY_USER,
        UserInfo.class);
    getModel().createWorkOrder(
        new WorkOrderInfo(agencyId, projectInfos.get(projectIndex).getProject_id(),
            clientInfos.get(clientIndex).getCustomer_id(), simpleDescription, endTime, description,
            errorGradeInfos.get(errorGradeIndex).getGdLevel_id(),
            clientContactInfos.get(contactorIndex).getContactper_id(),
            supplyInfos.get(deviceIndex).getPutIn_id(), userInfo.getDepartment().getDeptName(),
            userInfo.getUserId(), userInfo.getUser_Name()));
  }

  @Override
  public void startUploadPic(List<File> file, int id) {
    Map<String, RequestBody> map = new HashMap<>();
    for (File f : file) {
      // create RequestBody instance from file
      // RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
      RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), f);
      //注意：file就是与服务器对应的key,后面filename是服务器得到的文件名
      map.put("file\"; filename=\"" + f.getName(), requestFile);
    }
    getModel().uploadPic(map, id);
  }

  @Override
  public void check(List<String> strings) {
    for (int i = 0; i < strings.size(); i++) {
      if (strings.get(i).equals("")) {
        getView().updateCheckFailUi(null, i);
        return;
      }
    }
    getView().updateCheckSuccess(null);
  }

  @Override
  public void startLockSupply(int state, int index) {
    getModel().lockSupply(state, supplyInfos.get(index).getPutIn_id());
  }
}
