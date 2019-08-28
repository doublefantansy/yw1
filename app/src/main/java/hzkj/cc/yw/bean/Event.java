package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class Event {

  int status;
  Object object;

  public Event(int status, Object object) {
    this.status = status;
    this.object = object;
  }
}
