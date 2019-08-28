package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class Department {

  int deptId;
  int cmy_id;
  Department parent;
  int deptparentId;
  String deptName;
  int deptNumber;
  float antitude;
  float longitude;
  int attenceDistance;
}
