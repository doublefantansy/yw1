package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class SupplyInfo {
  public static final int ALL=1;
  public static final int PART=2;
  public static final int COMPLETE=3;
  public static final int UNLOCK = 0;
  public static final int LOCK = 1;
  int putIn_id;
  String putIn_date;
  UserInfo putIn_person;
  ProjectInfo putIn_project;
  int putIn_state;
  SupplyGood putIn_good;
  String supplies_serial;
  String supplies_serialForm;
  String putIn_type;
  String putIn_category;
  String putIn_shelfLife;
  int putIn_lkState;
}
