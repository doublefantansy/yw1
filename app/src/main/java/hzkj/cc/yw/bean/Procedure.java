package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class Procedure {

  public static final int isUse = 1;
  public static final int isDone = 2;
  public static final int isAbandon = 0;
  String startTime;
  String review_reason;
  String c_transactor_id;
  String review_result;
  int n_business_id;
  int n_queue_id;
  int n_process_id;
  int n_business_state;
  int n_transAction_id;
  int n_transAction_state;
  String c_node_name;
  String c_process_name;
  String d_transaction_time_begin;
  UserInfo userInfo;
}
