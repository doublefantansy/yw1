package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class FaultType {

  public  static final int DEVICE=3;
  int id;
  String faultName;

  public FaultType(String faultName) {
    this.faultName = faultName;
  }
}
