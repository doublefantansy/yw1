package hzkj.cc.yw.bean;

import java.util.*;
import lombok.*;

@Data
public class ClientInfo implements Cloneable {

  int customer_id;
  String customer_code;
  String customer_name;
  String customer_type;
  String customer_project;
  List<ContactorInfo> contactorInfo;
  String customer_address;
  String customer_lal;
  String customer_projectType;
  String project_name;
  String projectType_name;
  String customerType_name;

  public ClientInfo() {
  }
}
