package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class AttenceCount {

  Integer value;
  String text;

  public AttenceCount(Integer value, String text) {
    this.value = value;
    this.text = text;
  }
}
