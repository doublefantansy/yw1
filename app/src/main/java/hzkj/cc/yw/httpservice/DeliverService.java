package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface DeliverService {

  @GET("deliver/{type}/{pageNum}")
  Observable<Response<List<DeliverInfo>>> getSome(@Path("type") int type,
      @Path("pageNum") int pageNum);

  @PUT("deliver/{user_id}/{id}/{nowTime}")
  Observable<Response<Integer>> putOne(@Path("user_id") int userId, @Path("id") int id,
      @Path("nowTime") String nowTime);
}
