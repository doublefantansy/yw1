package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class RepairInfoDetail {

  int backDetailId;
  int backInfoId;
  int putInSupplies_id;
  int sl;
  String backBe;
  int backState;
  String backDetailLast;
  ProcedureGood good;
  String statusString;
  ProjectInfo projectInfo;
}
