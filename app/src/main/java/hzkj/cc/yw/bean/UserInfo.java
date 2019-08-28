package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class UserInfo {

  //  int id;
  int userId;
  String password;
  String userName;
  String user_Name;
  String userType;
  String mobile;
  String jpushId;
  String sex;
  String icon;
  String user_addr;
  Department department;

  public UserInfo() {
  }

  public UserInfo(String mobile, String password) {
    this.mobile = mobile;
    this.password = password;
  }
}
