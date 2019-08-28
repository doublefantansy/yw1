package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class ApplyBuyInfoDetail {

  int buyDetailId;
  int buyId;
  int suppliesId;
  int xqsl;
  int hdsl;
  int zksl;
  int cgsl;
  ProcedureGood procedureGood;
}
