package hzkj.cc.yw.httpservice;

import java.util.Map;

import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface HomeCountService {

  @GET("home_count/{id}")
  Observable<Response<Map<String, Integer>>> getHomeCounts(@Path("id") String id);
}
