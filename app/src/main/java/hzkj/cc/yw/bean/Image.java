package hzkj.cc.yw.bean;

public class Image {

  String img;
  byte[] bytes;

  public Image(String img) {
    this.img = img;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }
}
