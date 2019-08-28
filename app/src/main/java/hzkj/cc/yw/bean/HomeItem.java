package hzkj.cc.yw.bean;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import lombok.Data;

@Data
public class HomeItem {

  public static final String APPLYBUY = "2";
  public static final String GOTOREPAIR = "3";
  public static final String RENTCAR = "1";
  public static final String DELIVER = "物流流程";

  String title;
  Drawable drawable;
  Intent intent;
  android.view.View view;
  int tag;


  public HomeItem(int tag,String title, Drawable drawable, Intent intent) {
    this.title = title;
    this.drawable = drawable;
    this.intent = intent;
    this.tag=tag;
  }
  public HomeItem(String title, Drawable drawable, Intent intent) {
    this.title = title;
    this.drawable = drawable;
    this.intent = intent;

  }
}
