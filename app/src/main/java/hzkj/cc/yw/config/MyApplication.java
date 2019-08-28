package hzkj.cc.yw.config;

import android.app.Application;
import android.support.multidex.MultiDex;
import android.util.Log;
import com.vise.log.ViseLog;
import com.vise.log.inner.LogcatTree;
import com.xuexiang.xui.XUI;

/**
 * @author cc
 */
public class MyApplication extends Application {

  private static MyApplication application;

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
//    MMKV.initialize(this);
    XUI.init(this);
    XUI.debug(true);
    ViseLog.getLogConfig()
        .configAllowLog(true)
        .configShowBorders(true)
        .configTagPrefix("myTag")
        .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-five}")
        .configLevel(Log.VERBOSE);
    ViseLog.plant(new LogcatTree());
    MultiDex.install(this);
  }

  public static MyApplication getApplication() {
    return application;
  }
}
