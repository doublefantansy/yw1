package hzkj.cc.yw.presenter;

import com.vise.log.ViseLog;
import hzkj.cc.yw.bean.FaultType;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SupplyInfo;
import hzkj.cc.yw.bean.WorkOrderDetail;
import hzkj.cc.yw.bean.WorkOrderInfo;
import hzkj.cc.yw.contract.WorkOrderProcessContract;
import hzkj.cc.yw.model.WorkOrderProcessModel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class WorkOrderProcessPresenter extends
    BasePresenter<WorkOrderProcessContract.View, WorkOrderProcessContract.Model> implements
    WorkOrderProcessContract.Presenter {

  public static final int GETOLDPARTDEVICESUCCESS = 0;
  public static final int GETNEWPARTDEVICESUCCESS = 5;
  public static final int GETOLDPARTDEVICEMPTY = 7;
  public static final int GETOLDPARTDEVICEDETAILSUCCESS = 1;
  public static final int GETNEWPARTDEVICEDETAILSUCCESS = 2;
  public static final int GETONEWORKORDERSUCCESS = 3;
  public static final int UPDATEPICSUCCESS = 4;
  public static final int UPDATEPROCEDURESUCCESS = 6;
  public static final int GETFAULTTYPESUCCESS = 61;
  public static final int GETFAULTTYPEDETAILSUCCESS = 6111;
  public static final int UPDATEPICEVALUATESUCCESS = 611111;
  public static final int CHECK_FAIL_PROCEDURE = 2323;
  public static final int CHECK_SUCCESS_PROCEDURE = 2324;
  public static final int CHECK_FAIL_PRICE = 2321;
  public static final int CHECK_SUCCESS_PRICE = 23211;
  public static final int CHECK_FAIL_SERVICE = 2328;
  public static final int CHECK_SUCCESS_SERVICE = 2329;
  public static final int CHECK_FAIL_EVALUATE = 2330;
  public static final int CHECK_SUCCESS_EVALUATE = 2331;
  List<SupplyInfo> devices;
  List<FaultType> faultTypes;

  @Override
  WorkOrderProcessContract.Model createModel() {
    return new WorkOrderProcessModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (tag == WorkOrderProcessModel.GETOLDPARTDEVICE) {
      devices = (List<SupplyInfo>) response.getData();
      if (devices.size() != 0) {
        List<String> names = new ArrayList<>();
        for (SupplyInfo device : devices) {
          names.add(
              device.getPutIn_good().getSupplies_name() + "( ********" + device
                  .getSupplies_serial()
                  .substring(device.getSupplies_serial().length() - 9,
                      device.getSupplies_serial().length() - 1) + ")");
        }
        getView().updateSuccessUi(names, GETOLDPARTDEVICESUCCESS);
      } else {
        getView().updateSuccessUi(null, GETOLDPARTDEVICEMPTY);
      }
    } else if (tag == WorkOrderProcessModel.GETONEWORKORDER) {
      getView().updateSuccessUi(response.getData(), GETONEWORKORDERSUCCESS);
    } else if (tag == WorkOrderProcessModel.GETFAULTTYPES) {
      faultTypes = (List<FaultType>) response.getData();
      List<String> names = new ArrayList<>();
      for (FaultType faultType : faultTypes) {
        names.add(faultType.getFaultName());
      }
      getView().updateSuccessUi(names, GETFAULTTYPESUCCESS);
    } else if (tag == WorkOrderProcessModel.UPLOADPIC) {
      if (((Integer) response.getData()) > 0) {
        getView().updateSuccessUi(null, UPDATEPICSUCCESS);
      }
    } else if (tag == WorkOrderProcessModel.UPDATEPROCEDURE) {
      getView().updateSuccessUi(response.getData(), UPDATEPROCEDURESUCCESS);
    } else if (tag == WorkOrderProcessModel.UPLOADEVALUATEPIC) {
      getView().updateSuccessUi(response.getData(), UPDATEPICEVALUATESUCCESS);
    } else {
      devices = (List<SupplyInfo>) response.getData();
      List<String> names = new ArrayList<>();
      for (SupplyInfo device : devices) {
        names.add(
            device.getPutIn_good().getSupplies_name() + "( ********" + device
                .getSupplies_serial()
                .substring(device.getSupplies_serial().length() - 9,
                    device.getSupplies_serial().length() - 1) + ")");
      }
      getView().updateSuccessUi(names, GETNEWPARTDEVICESUCCESS);
    }
  }

  @Override
  public void chooseGetOldPartDevice(String parentUuid) {
    ViseLog.d(parentUuid);
    getModel().getPartDevice(parentUuid, true);
  }

  @Override
  public void getOldDeviceDetail(int p) {
    getView().updateSuccessUi(devices.get(p), GETOLDPARTDEVICEDETAILSUCCESS);
  }

  @Override
  public void chooseGetNewPartDevice(String parentUuid) {
    getModel().getPartDevice(parentUuid, false);
  }

  @Override
  public void getNewDeviceDetail(int p) {
    getView().updateSuccessUi(devices.get(p), GETNEWPARTDEVICEDETAILSUCCESS);
  }

  @Override
  public void startUpdateProcedure(WorkOrderInfo workOrderInfo) {
    ViseLog.d(workOrderInfo);
    getModel().updateProcedure(workOrderInfo);
  }

  @Override
  public void startGetOne(int id) {
    getModel().getOne(id);
  }

  @Override
  public void startInsertPic(List<File> files, int id, int state, boolean isEvaluate) {
    Map<String, RequestBody> map = new HashMap<>();
    for (File f : files) {
      // create RequestBody instance from file
      // RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
      RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), f);
      //注意：file就是与服务器对应的key,后面filename是服务器得到的文件名
      map.put("file\"; filename=\"" + f.getName(), requestFile);
    }
    getModel().insertPic(map, id, state, isEvaluate);
  }

  @Override
  public void startGetFaultTypes() {
    getModel().getFaultTypes();
  }

  @Override
  public void getFaultTypeDetail(int p) {
    getView().updateSuccessUi(faultTypes.get(p), GETFAULTTYPEDETAILSUCCESS);
  }

  @Override
  public void checkProcedure(String procedure, int files) {
    if (procedure.equals("") || files == 0) {
      getView().updateCheckFailUi(null, CHECK_FAIL_PROCEDURE);
      return;
    }
    getView().updateCheckSuccess(CHECK_SUCCESS_PROCEDURE);
  }

  @Override
  public void checkService(String date, String status) {
    if (date.equals("") || status.equals("")) {
      getView().updateCheckFailUi(null, CHECK_FAIL_SERVICE);
      return;
    }
    getView().updateCheckSuccess(CHECK_SUCCESS_SERVICE);
  }

  @Override
  public void checkFiles(int size) {
    if (size == 0) {
      getView().updateCheckFailUi(null, CHECK_FAIL_EVALUATE);
      return;
    }
    getView().updateCheckSuccess(CHECK_SUCCESS_EVALUATE);
  }

  @Override
  public void checkPrice(int serviceMode, String servicePrice, String trafficPrice,
      String otherPrice, List<WorkOrderDetail> details) {
    if (serviceMode == -1 | servicePrice.equals("")
        || trafficPrice.equals("")
        || otherPrice.equals("")) {
      getView().updateCheckFailUi(null, CHECK_FAIL_PRICE);
      return;
    }
    for (WorkOrderDetail detail : details) {
      if (detail.getDetails_standard() == -1 || detail.getFaultType() == null|| detail
          .getDetails_reason() == null || detail.getDetails_reason().equals("")) {
        getView().updateCheckFailUi(null, CHECK_FAIL_PRICE);
        return;
      }
      if (detail.getFaultType().getId() == FaultType.DEVICE) {
        if (detail.getDetails_type() == -1 || detail.getDetails_source() == -1) {
          getView().updateCheckFailUi(null, CHECK_FAIL_PRICE);
          return;
        }
        if (detail.getDetails_type() != WorkOrderDetail.REPLACE) {
          if (detail.getDetails_equip().getPutIn_id() == 0) {
            getView().updateCheckFailUi(null, CHECK_FAIL_PRICE);
            return;
          }
        } else {
          if (detail.getDetails_start() == null || detail.getDetails_end() == null || detail
              .getDetails_end().equals("") || detail.getDetails_start().equals("")
              || detail.getDetails_price() == -1 || detail.getDetails_nequip().getPutIn_id() == 0) {
            getView().updateCheckFailUi(null, CHECK_FAIL_PRICE);
            return;
          }
        }
      }
    }
    getView().updateCheckSuccess(CHECK_SUCCESS_PRICE);
  }
}
