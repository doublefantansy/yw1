package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class Company {

  int cmy_id;
  String cmy_name;
  int cmy_level;
  String cmy_type;
  int cmy_parentId;
}
