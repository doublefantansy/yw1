package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.RentCarInfo;
import hzkj.cc.yw.bean.Response;
import java.util.List;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface RentCarService {


  @POST("rentcar/insert_rent_car_info")
  Observable<Response<Integer>> insertRentCarInfo(@Body RentCarInfo rentCarInfo);

  @PUT("rentcar/{type}/{id}")
  Observable<Response<Integer>> putOne(@Path("type") int type, @Body RentCarInfo rentCarInfo,
      @Path("id") int id);

  @GET("rentcar/{type}/{user_id}/{pageNum}")
  Observable<Response<List<RentCarInfo>>> getRentCarInfoByRentCar(@Path("type") int type,
      @Path("user_id") int userId,
      @Path("pageNum") int pageNum);

  @GET("rentcar/{type}/{pageNum}")
  Observable<Response<List<RentCarInfo>>> getReviewRentCarInfo(@Path("type") int type,
      @Path("pageNum") int pageNum);

  @GET("rentcar/detail/{id}/{type}")
  Observable<Response<RentCarInfo>> getOne(@Path("id") int id, @Path("type") int type);

  @Multipart
  @POST("rentcar")
  Observable<Response<String>> uploadPic(@Part("type") int type,@Part("id") int id,
      @Part MultipartBody.Part file);
}
