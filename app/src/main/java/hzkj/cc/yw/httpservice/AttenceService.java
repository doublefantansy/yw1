package hzkj.cc.yw.httpservice;

import java.util.List;
import java.util.Map;

import hzkj.cc.yw.bean.AttenceCount;
import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.AttenceLimitInfo;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface AttenceService {

  @GET("attence/get_attence_message/{attenceMobile}/{attenceDateStart}/{attenceDateEnd}")
  Observable<Response<Map<String, Map<String, AttenceInfo>>>> getAttenceMessage(
      @Path("attenceMobile") String attenceMobile, @Path("attenceDateStart") String attenceStart,
      @Path("attenceDateEnd") String attenceDateEnd);

  @GET("attence/get_attence_count/{attenceMobile}/{attenceDateStart}/{attenceDateEnd}")
  Observable<Response<List<AttenceCount>>> getAttenceCount(
      @Path("attenceMobile") String attenceMobile, @Path("attenceDateStart") String attenceStart,
      @Path("attenceDateEnd") String attenceDateEnd);

  @POST("attence/start_attence")
  Observable<Response<Integer>> startAttence(@Body AttenceInfo attenceInfo);

  @GET("attence/get_limit_time")
  Observable<Response<List<AttenceLimitInfo>>> getLimitTime();

  @GET("attence/get_department_coordinate/{user_id}")
  Observable<Response<Department>> getDepartmentCoordinate(@Path("user_id") int userId);
}
