package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class AuthorityInfo {

  public static final int RENTCARAPPLY = 92;
  public static final int REPORT = 83;
  public static final int ATTENCE = 43;
  public static final int MESSAGE = 104;

  int module_id;
  String module_name;
  int mudule_state;
}
