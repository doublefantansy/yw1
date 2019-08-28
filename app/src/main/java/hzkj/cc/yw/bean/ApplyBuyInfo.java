package hzkj.cc.yw.bean;

import lombok.Getter;

@Getter
public class ApplyBuyInfo {

  int buyId;
  //	int deptId;
//	//	int userId;
  UserInfo userInfo;
  Company company;
  Department department;
  String applicationDate;
  String yt;
  String useDate;
  Procedure procedure;
}
