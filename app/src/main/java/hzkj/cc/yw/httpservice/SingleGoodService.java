package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SingleGood;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface SingleGoodService {

  @GET("single_good/{id}")
  Observable<Response<List<SingleGood>>> get(@Path("id") int id);
}
