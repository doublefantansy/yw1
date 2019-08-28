package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.ApplyBuyInfo;
import hzkj.cc.yw.bean.ApplyBuyInfoDetail;
import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ApplyInfoService {

  @GET("apply_buy/get_apply_buy/{mobile}/{type}/{pageNum}")
  Observable<Response<List<ApplyBuyInfo>>> getAppleBuyInfos(@Path("mobile") String mobile,
      @Path("type") int type, @Path("pageNum") int pageNum);

  @GET("apply_buy/get_reviews/{buyId}")
  Observable<Response<List<Procedure>>> getApplyBuyReviews(@Path("buyId") int buyId);

  @GET("apply_buy/get_infos/{buyId}")
  Observable<Response<List<ApplyBuyInfoDetail>>> getApplyBuyDetails(@Path("buyId") int buyId);

  @GET("apply_buy/search_id/{mobile}/{id}")
  Observable<Response<List<ApplyBuyInfo>>> searchById(@Path("id") int id,
      @Path("mobile") String mobile);

  @GET("apply_buy/{userId}/{startTime}/{endTime}/{agencyId}/{status}")
  Observable<Response<List<ApplyBuyInfo>>> searchByTime(@Path("userId") int userId,
      @Path("startTime") String startTime, @Path("endTime") String endTime,
      @Path("agencyId") int agencyId, @Path("status") int status);
}
