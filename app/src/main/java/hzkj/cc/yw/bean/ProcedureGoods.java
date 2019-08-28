package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class ProcedureGoods {

  public static final int BACKREPAIR = 3;
  public static final int APPLYBUY = 2;
  int count;
  ProcedureGood good;
}
