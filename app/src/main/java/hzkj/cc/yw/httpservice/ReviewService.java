package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.TransActionInfo;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface ReviewService {

  @GET("review/get_review_rentcar_infos/{status}")
  Observable<Response<List<TransActionInfo>>> getReviewInfos(@Path("status") String status);

  @GET("review/get_review_record_infos/{code}")
  Observable<Response<List<TransActionInfo>>> getReviewRecords(@Path("code") String code);

  @POST("review/end_transAction/{businessCode}/{transActionCode}/{status}/{person}/{advice}")
  Observable<Response<Integer>> endTransAction(@Path("businessCode") String businessCode,
      @Path("transActionCode") String transActionCode, @Path("status") String status,
      @Path("person") String person, @Path("advice") String advice);
}
