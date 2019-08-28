package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.DeliverInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SingleGood;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface SingleDeliverService {

  @POST("single_deliver/{kdgs}/{kddh}/{fhsj}/{fhrId}/{sjrId}")
  Observable<Response<Integer>> post(@Path("kdgs") int kdgs, @Path("kddh") String kddh,
      @Path("fhsj") String fhsj, @Path("fhrId") int fhrId, @Path("sjrId") int sjrId
      , @Body List<SingleGood> singleGoods);

  @GET("single_deliver/{user_id}/{type}/{pageNum}")
  Observable<Response<List<DeliverInfo>>> get(@Path("user_id") int userId, @Path("type") int type,
      @Path("pageNum") int pageNum);
//    @GET("deliver/get_single_goods/{id}")
//    Observable<Response<List<SingleGood>>> getSingleGoodsById(@Path("id") int id);

  @PUT("single_deliver/{id}/{nowTime}")
  Observable<Response<Integer>> put(@Path("id") int id, @Path("nowTime") String nowTime);
}
