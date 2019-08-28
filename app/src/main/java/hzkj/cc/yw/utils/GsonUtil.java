package hzkj.cc.yw.utils;

import com.google.gson.Gson;

/**
 * gson处理工具类
 */
public class GsonUtil {

  static Gson gson = new Gson();

  public static <T> T stringToObject(String jsonString, Class<T> tClass) {
    return gson.fromJson(jsonString, tClass);
  }

  public static String objectToString(Object object) {
    return gson.toJson(object);
  }
}
