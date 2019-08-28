package hzkj.cc.yw.bean;

public class ClientTypeInfo {

  int customerType_id;
  String customerType_code;
  String customerType_name;

  public ClientTypeInfo(String customerType_code, String customerType_name) {
    this.customerType_code = customerType_code;
    this.customerType_name = customerType_name;
  }

  public int getCustomerType_id() {
    return customerType_id;
  }

  public void setCustomerType_id(int customerType_id) {
    this.customerType_id = customerType_id;
  }

  public String getCustomerType_code() {
    return customerType_code;
  }

  public void setCustomerType_code(String customerType_code) {
    this.customerType_code = customerType_code;
  }

  public String getCustomerType_name() {
    return customerType_name;
  }

  public void setCustomerType_name(String customerType_name) {
    this.customerType_name = customerType_name;
  }
}
