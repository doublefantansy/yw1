package hzkj.cc.yw.bean;

public class TransActionInfo {

  int n_transAction_id;
  int n_process_id;
  int n_queue_id;
  int n_business_id;
  int n_transAction_state;
  int n_business_state;
  String n_transAction_comment;
  String c_transActor_id;
  String d_transAction_time_begin;
  String d_transAction_time_end;
  String d_transAction_time_due;
  int n_transAction_due_state;
  String c_transActor_signature;
  int n_last_bace_transAction_id;
  String user_name;
  String userName;
  String c_process_name;
  String c_node_name;
  int n_queue_number;

  public int getN_business_state() {
    return n_business_state;
  }

  public void setN_business_state(int n_business_state) {
    this.n_business_state = n_business_state;
  }

  public int getN_business_id() {
    return n_business_id;
  }

  public void setN_business_id(int n_business_id) {
    this.n_business_id = n_business_id;
  }

  public int getN_queue_number() {
    return n_queue_number;
  }

  public void setN_queue_number(int n_queue_number) {
    this.n_queue_number = n_queue_number;
  }

  public String getC_node_name() {
    return c_node_name;
  }

  public void setC_node_name(String c_node_name) {
    this.c_node_name = c_node_name;
  }

  public String getC_process_name() {
    return c_process_name;
  }

  public void setC_process_name(String c_process_name) {
    this.c_process_name = c_process_name;
  }

  public String getUser_name() {
    return user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public int getN_transAction_id() {
    return n_transAction_id;
  }

  public void setN_transAction_id(int n_transAction_id) {
    this.n_transAction_id = n_transAction_id;
  }

  public int getN_process_id() {
    return n_process_id;
  }

  public void setN_process_id(int n_process_id) {
    this.n_process_id = n_process_id;
  }

  public int getN_queue_id() {
    return n_queue_id;
  }

  public void setN_queue_id(int n_queue_id) {
    this.n_queue_id = n_queue_id;
  }

  public int getN_bussiness_id() {
    return n_business_id;
  }

  public void setN_bussiness_id(int n_business_id) {
    this.n_business_id = n_business_id;
  }

  public int getN_transAction_state() {
    return n_transAction_state;
  }

  public void setN_transAction_state(int n_transAction_state) {
    this.n_transAction_state = n_transAction_state;
  }

  public String getN_transAction_comment() {
    return n_transAction_comment;
  }

  public void setN_transAction_comment(String n_transAction_comment) {
    this.n_transAction_comment = n_transAction_comment;
  }

  public String getC_transActor_id() {
    return c_transActor_id;
  }

  public void setC_transActor_id(String c_transActor_id) {
    this.c_transActor_id = c_transActor_id;
  }

  public String getD_transAction_time_begin() {
    return d_transAction_time_begin;
  }

  public void setD_transAction_time_begin(String d_transAction_time_begin) {
    this.d_transAction_time_begin = d_transAction_time_begin;
  }

  public String getD_transAction_time_end() {
    return d_transAction_time_end;
  }

  public void setD_transAction_time_end(String d_transAction_time_end) {
    this.d_transAction_time_end = d_transAction_time_end;
  }

  public String getD_transAction_time_due() {
    return d_transAction_time_due;
  }

  public void setD_transAction_time_due(String d_transAction_time_due) {
    this.d_transAction_time_due = d_transAction_time_due;
  }

  public int getN_transAction_due_state() {
    return n_transAction_due_state;
  }

  public void setN_transAction_due_state(int n_transAction_due_state) {
    this.n_transAction_due_state = n_transAction_due_state;
  }

  public String getC_transActor_signature() {
    return c_transActor_signature;
  }

  public void setC_transActor_signature(String c_transActor_signature) {
    this.c_transActor_signature = c_transActor_signature;
  }

  public int getN_last_bace_transAction_id() {
    return n_last_bace_transAction_id;
  }

  public void setN_last_bace_transAction_id(int n_last_bace_transAction_id) {
    this.n_last_bace_transAction_id = n_last_bace_transAction_id;
  }
}
