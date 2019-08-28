package hzkj.cc.yw.bean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class DeliverInfo {

  public static final int HASGET = 1;
  public static final int HASNOTGET = 0;
  int logisticsId;
  Procedure procedure;
  Department getDepartment;
  UserInfo getUser;
  String sjAddress;
  DeliverCompany kdgs;
  String kddh;
  int js;
  String fhdz;
  Department postDepartment;
  UserInfo postUser;
  String fhsj;
  String shsj;
  int clState;
  int fyfx;
  String stateString;
  List<SingleGood> singleGoods;

  public DeliverInfo(UserInfo getUser, DeliverCompany kdgs, String kddh, UserInfo postUser,
      String fhsj) {
    this.getUser = getUser;
    this.kdgs = kdgs;
    this.kddh = kddh;
    this.postUser = postUser;
    this.fhsj = fhsj;
  }
}
