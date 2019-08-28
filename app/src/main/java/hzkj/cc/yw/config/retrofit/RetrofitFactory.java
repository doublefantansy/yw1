package hzkj.cc.yw.config.retrofit;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author cc
 */
public class RetrofitFactory {

  public static final String BASE_URL_IMAGE = "http://15.16.22.84:2323";
  public static final int ATTENCESUCCESS = 400;
  public static final int HAVEATTENCE = 401;
  public static final int LOGINSUCCESS = 200;
  public static final int GETSUCCESS = 300;
  public static final int NODATA = 301;
  public static final int LOGINERROR = 201;
  public static final int INSERTSUCCESS = 504;
  public static final int INSERTERROR = 505;
  public static final int UPDATESUCCESS = 502;
  public static final int PROJECTEMPTY = 701;
  public static final int DEPARTMENTEMPTY = 702;
  public static final int DEPARTSUCCESS = 703;
  public static final int PROJECTSUCCESS = 704;
  public static final String URL_TEST = "http://15.16.22.114:8080/";
  private static Retrofit retrofit;
  private static RetrofitFactory retrofitHelper;

  private static OkHttpClient getOkHttpClient() {
    return new OkHttpClient.Builder()
        .addInterceptor(new MyIntercepter())
        .connectTimeout(5000, TimeUnit.MILLISECONDS)
        .build();
  }

  public static RetrofitFactory getInstance() {
    if (retrofitHelper == null) {
      retrofitHelper = new RetrofitFactory();
//            init();
    }
    return retrofitHelper;
  }

  private static void init() {
  }

  public Retrofit getRetrofit() {
    return new Retrofit.Builder().baseUrl(URL_TEST)
        .client(getOkHttpClient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
}
