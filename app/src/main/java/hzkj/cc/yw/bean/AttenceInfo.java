package hzkj.cc.yw.bean;

import lombok.Data;

@Data
public class AttenceInfo {

  //
  public static final String NORMAL = "5";
  public static final String LATE = "0";
  public static final String EARLY = "1";
  public static final int ATTENCECOUNT = 2;
  //    public static final String late = "1";
  private String attenceMobile;
  private String attenceDate;
  private String attenceTime;
  private String attenceType;
  private String leaveType;
  private String antiTude;
  private String longiTude;
  private String baiduGpsComment;
  private String distance;

  public AttenceInfo(String attenceMobile, String attenceDate, String attenceTime,
      String attenceType, String antiTude, String longiTude, String baiduGpsComment,
      String distance, String leaveType) {
    this.attenceMobile = attenceMobile;
    this.attenceDate = attenceDate;
    this.attenceTime = attenceTime;
    this.attenceType = attenceType;
    this.antiTude = antiTude;
    this.longiTude = longiTude;
    this.baiduGpsComment = baiduGpsComment;
    this.distance = distance;
    this.leaveType = leaveType;
  }
}
