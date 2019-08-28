package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class RepairInfo {

  int backInfoId;
  Department department;
  Procedure procedure;
  //    ProjectInfo projectInfo;
  String backTitle;
  String backTime;
  Company company;
  String operDate;
  UserInfo userInfo;
}
