package hzkj.cc.yw.contract;

import hzkj.cc.yw.bean.WorkOrderInfo;
import java.io.File;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;

public interface WorkOrderInsertContract {

  interface Model extends BaseContract.Model {

    void chooseProject(int agencyId);

    void chooseClient(int projectId);

    void chooseClientContactPerson(int clientId);

    void chooseErrorgGrade();

    void chooseDevice(int projectId);

    void createWorkOrder(WorkOrderInfo workOrderInfo);

    void uploadPic(Map<String, RequestBody> file, int id);

    void lockSupply(int state,int supplyId);
  }

  interface View extends BaseContract.View {

  }

  interface Presenter extends BaseContract.Presenter {

    void startChooseProject(int projectIndex);

    void startChooseClient(int projectIndex);

    void startChooseClientContactPerson(int clientIndex);

    void startChooseErrorGrade();

    void startChooseDevice(int projectIndex);

    void startCreateWorkOrder(int agencyId, int projectIndex, int clientIndex, int contactorIndex,
        String simpleDescription, String endTime, int errorGradeIndex, int deviceIndex,
        String description);

    void startUploadPic(List<File> file, int id);

    void check(List<String> strings);

    void startLockSupply(int state,int supplyId);
  }
}
