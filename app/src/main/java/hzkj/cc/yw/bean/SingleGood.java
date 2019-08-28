package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class SingleGood {

  String supplies_name;
  String supplies_category;
  String remark;
  String supplies_count;
  String supplies_unit;

  public SingleGood(String supplies_name, String supplies_category, String supplies_count,
      String supplies_unit, String remark) {
    this.supplies_name = supplies_name;
    this.supplies_category = supplies_category;
    this.remark = remark;
    this.supplies_count = supplies_count;
    this.supplies_unit = supplies_unit;
  }
}
