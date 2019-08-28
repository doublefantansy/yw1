package hzkj.cc.yw.bean;

import java.util.*;
import lombok.Data;

@Data
public class WorkOrderInfo {

  public static final Map<Integer, String> MODE = new HashMap<Integer, String>() {{
    put(0, "上门服务");
    put(1, "用户送修");
    put(2, "远程服务");
    put(3, "其他");
  }};
  public static final List<String> STATUS = new ArrayList<String>() {{
    add("完成");
    add("未完成");
    add("返厂设备维修");
  }};
  public static final int USEING = 0;
  public static final int END = 1;
  int workOrders_id;
  String workOrders_date;
  UserInfo userInfo;
  ProjectInfo projectInfo;
  ClientInfo clientInfo;
  String workOrders_brief;
  String workOrders_endDate;
  ErrorGradeInfo errorGradeInfo;
  String workOrders_detailed;
  //  String workOrders_attachment;
  List<Attachment> attachment;
  double workOrders_equipsCost = 0;
  double workOrders_service = -1;
  double workOrders_fare = -1;
  double workOrders_other = -1;
  double workOrders_total = 0;
  SupplyInfo supplyInfo;
  String workOrders_code;
  String workOrders_procedure;
  int workOrders_progress;
  List<WorkOrderDetail> details;
  int details_way = -1;
  int workOrders_status;
  String workOrders_serviceDate;

  public WorkOrderInfo(int workOrders_dept, int workOrders_pro, int workOrders_customer,
      String workOrders_brief, String workOrders_endDate, String workOrders_detailed,
      int workOrders_type,
      int workOrders_person,
      int workOrders_equipId, String workOrders_agencyName, int workOrders_man,
      String workOrders_manName) {
    this.projectInfo = new ProjectInfo();
    this.projectInfo.setProject_id(workOrders_pro);
    this.clientInfo = new ClientInfo();
    this.clientInfo.setCustomer_id(workOrders_customer);
    this.workOrders_brief = workOrders_brief;
    this.workOrders_endDate = workOrders_endDate;
    this.errorGradeInfo = new ErrorGradeInfo();
    errorGradeInfo.setGdLevel_id(workOrders_type);
    final ContactorInfo contactorInfo = new ContactorInfo();
    contactorInfo.setContactper_id(workOrders_person);
    clientInfo.setContactorInfo(new ArrayList<ContactorInfo>() {{
      add(contactorInfo);
    }});
    this.supplyInfo = new SupplyInfo();
    supplyInfo.setPutIn_id(workOrders_equipId);
    this.workOrders_detailed = workOrders_detailed;
    Department department = new Department();
    department.setDeptId(workOrders_dept);
    department.setDeptName(workOrders_agencyName);
    this.userInfo = new UserInfo();
    userInfo.setUserId(workOrders_man);
    userInfo.setUser_Name(workOrders_manName);
    userInfo.setDepartment(department);
  }
}
