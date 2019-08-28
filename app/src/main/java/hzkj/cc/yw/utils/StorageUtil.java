package hzkj.cc.yw.utils;

import android.content.Context;
import android.content.SharedPreferences;
import hzkj.cc.yw.config.MyApplication;

/**
 * 存储工具类
 */
public class StorageUtil {

  public static final String KEY_USER = "user";
  public static final String KEY_TOKEN = "token";
  static SharedPreferences sharedPreferences = MyApplication.getApplication()
      .getSharedPreferences("data", Context.MODE_MULTI_PROCESS);
  static SharedPreferences.Editor editor = sharedPreferences.edit();

  public static <T> T getData(String key, Class<T> tClass) {
    return GsonUtil.stringToObject(sharedPreferences.getString(key, ""), tClass);
  }

  public static String getStringData(String key) {
    return sharedPreferences.getString(key, "");
  }

  public static void putData(String key, Object object) {
    editor.putString(key, GsonUtil.objectToString(object)).commit();
  }

  public static void clearAll() {
    editor.clear().commit();
  }

  public static void clearOneKey(String key) {
    editor.remove(key).commit();
  }
}
