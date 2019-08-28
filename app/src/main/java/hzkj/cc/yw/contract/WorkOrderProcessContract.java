package hzkj.cc.yw.contract;

import hzkj.cc.yw.bean.WorkOrderDetail;
import hzkj.cc.yw.bean.WorkOrderInfo;
import java.io.File;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;

public interface WorkOrderProcessContract {

  interface Model extends BaseContract.Model {

    void getPartDevice(String projectId, boolean isOld);

    void updateProcedure(WorkOrderInfo workOrderInfo);

    void getOne(int id);

    void insertPic(Map<String, RequestBody> files, int id, int state, boolean isEvaluate);

    void updateDetail(WorkOrderDetail detail, int mode);

    void getFaultTypes();
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void chooseGetOldPartDevice(String parentUuid);

    void getOldDeviceDetail(int p);

    void chooseGetNewPartDevice(String parentUuid);

    void getNewDeviceDetail(int p);

    /**
     * 第二步
     */
    void startUpdateProcedure(WorkOrderInfo workOrderInfo);

    void startGetOne(int id);

    void startInsertPic(List<File> files, int id, int state, boolean isEvaluate);

    void startGetFaultTypes();

    void getFaultTypeDetail(int p);

    void checkProcedure(String procedure, int files);

    void checkService(String date, String status);

    void checkFiles(int size);

    void checkPrice(int serviceMode, String servicePrice, String trafficPrice,
        String otherPrice, List<WorkOrderDetail> details);
  }
}
