package hzkj.cc.yw.bean;

public class TimeBean {

  String name;
  String time;
  String content;
  String result;
  int src;

  public TimeBean(String name, String time, String content, String result, int src) {
    this.name = name;
    this.time = time;
    this.content = content;
    this.result = result;
    this.src = src;
  }

  public int getSrc() {
    return src;
  }

  public void setSrc(int src) {
    this.src = src;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
