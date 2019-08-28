package hzkj.cc.yw.config.retrofit;

import android.text.TextUtils;

import java.io.IOException;

import hzkj.cc.yw.utils.StorageUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyIntercepter implements Interceptor {

  public static final String TOKENNAME = "Authorization";
  public static final String LOGIN_URL = "user/login";
  Request request;
  Response response;

  @Override
  public Response intercept(Chain chain) throws IOException {
    request = chain.request();
    String url = request.url()
        .toString();
//        ViseLog.d(url);
    response = chain.proceed(request);
    if (TextUtils.equals(url, RetrofitFactory.URL_TEST + LOGIN_URL)) {
      StorageUtil.putData(StorageUtil.KEY_TOKEN, response.header(TOKENNAME));
    } else {
      request = request.newBuilder()
          .header(TOKENNAME, StorageUtil.getData(StorageUtil.KEY_TOKEN, String.class))
          .method(request.method(), request.body())
          .build();
    }
    return chain.proceed(request);
  }
}

