package hzkj.cc.yw.bean;

public class ProjectTypeInfo {

  String projectType_code;
  String projectType_name;

  public ProjectTypeInfo(String projectType_code, String projectType_name) {
    this.projectType_code = projectType_code;
    this.projectType_name = projectType_name;
  }

  public String getProjectType_code() {
    return projectType_code;
  }

  public void setProjectType_code(String projectType_code) {
    this.projectType_code = projectType_code;
  }

  public String getProjectType_name() {
    return projectType_name;
  }

  public void setProjectType_name(String projectType_name) {
    this.projectType_name = projectType_name;
  }
}
