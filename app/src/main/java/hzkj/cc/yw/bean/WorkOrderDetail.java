package hzkj.cc.yw.bean;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class WorkOrderDetail {

  public static final Map<Integer, String> SOURCE = new HashMap<Integer, String>() {{
    put(0, "二级库存备件");
    put(1, "项目遗留物资");
  }};
  public static final Map<Integer, String> STANDARD = new HashMap<Integer, String>() {{
    put(-1, "");
    put(0, "保内");
    put(1, "过期");
    put(2, "雷击");
    put(3, "破损");
    put(4, "其他");
  }};
  public static final Map<Integer, String> TYPE = new HashMap<Integer, String>() {{
    put(0, "维修");
    put(1, "更换");
    put(2, "拆卸返修");
    put(3, "项目物资安装");
    put(4, "项目物资拆除");
  }};
  public static final int REPLACE = 1;
  public static final int HASED = 0;
  public static final int LEAVE = 1;
  int details_id;
  int details_standard = -1;
  int details_type = -1;
  SupplyInfo details_equip;
  int details_source = -1;
  String details_unit;
  SupplyInfo details_nequip;
  String details_reason;
  int details_workOrdersId;
  FaultType faultType;
  double details_price = -1;
  String details_start;
  String details_end;
}
