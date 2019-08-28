package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface UserService {

  @POST("user/login")
  Observable<hzkj.cc.yw.bean.Response<UserInfo>> logIn(@Body UserInfo userInfo);

  @Multipart
  @POST("user/update_info")
  Observable<hzkj.cc.yw.bean.Response<UserInfo>> updateInfo(@Part("json") RequestBody id,
      @Part MultipartBody.Part file);

  @GET("user/get_user_infos")
  Observable<Response<List<UserInfo>>> getUserInfos();

  @GET("user/{str}")
  Observable<Response<List<UserInfo>>> getByLike(@Path("str") String str);

  @POST("user/update_password/{newPassword}")
  Observable<hzkj.cc.yw.bean.Response<UserInfo>> updatePassword(@Body UserInfo userInfo,
      @Path("newPassword") String newPassword);
}
