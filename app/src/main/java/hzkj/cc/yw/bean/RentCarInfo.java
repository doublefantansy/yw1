package hzkj.cc.yw.bean;

import java.util.List;
import lombok.Data;

@Data
public class RentCarInfo {

  public static final int HASNOTREVIEW = 0;
  public static final int HASREVIEW = 3;
  public static final int REVIEWSUCCESS = 1;
  public static final int REVIEWFAIL = 2;
  UserInfo userInfo;
  int application_id;
  String application_date;
  ProjectInfo projectInfo;
  List<UserInfo> persons;
  String application_days;
  String application_price;
  String application_totalMoney;
  String application_paymentStruct;
  String application_reason;
  String application_type;
  String application_carDate;
  String application_carNum;
  String application_carType;
  String application_carOwnerName;
  String application_carOwnerTel;
  Procedure procedure;
  int status;
  String pic1;
  String pic2;

  public RentCarInfo(UserInfo userInfo, String application_carDate, ProjectInfo projectInfo,
      List<UserInfo> application_persons, String application_reason,
      String application_carOwnerName, String application_carOwnerTel,
      String application_carType, String application_carNum,
      String application_price, String application_days, String application_totalMoney,
      String application_paymentStruct, String application_date) {
    this.userInfo = userInfo;
    this.application_carDate = application_carDate;
    this.projectInfo = projectInfo;
    this.persons = application_persons;
    this.application_reason = application_reason;
    this.application_carOwnerName = application_carOwnerName;
    this.application_carOwnerTel = application_carOwnerTel;
    this.application_carType = application_carType;
    this.application_carNum = application_carNum;
    this.application_price = application_price;
    this.application_days = application_days;
    this.application_totalMoney = application_totalMoney;
    this.application_paymentStruct = application_paymentStruct;
    this.application_date = application_date;
  }
}
