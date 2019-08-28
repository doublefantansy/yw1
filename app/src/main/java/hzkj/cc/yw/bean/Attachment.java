package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class Attachment {

  public static final int FIRST = 0;
  public static final int SECOND = 1;
  int annex_id;
  String annex_name;
  String annex_path;
  int annex_workOrders;
  int annex_state;
}
