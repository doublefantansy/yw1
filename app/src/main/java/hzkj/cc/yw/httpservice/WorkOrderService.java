package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.ErrorGradeInfo;
import hzkj.cc.yw.bean.FaultType;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.WorkOrderInfo;
import java.util.List;
import java.util.Map;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

public interface WorkOrderService {

  @GET("work_order")
  Observable<Response<List<ErrorGradeInfo>>> getErrorGrades();

  @GET("work_order/faultType")
  Observable<Response<List<FaultType>>> getFaultTypes();

  @GET("work_order/{id}")
  Observable<Response<WorkOrderInfo>> getOne(@Path("id") int id);

  @POST("work_order/procedure")
  Observable<Response<WorkOrderInfo>> doProcedure(@Body WorkOrderInfo workOrderInfo);

  @POST("work_order")
  Observable<Response<Integer>> createWorkOrder(@Body WorkOrderInfo workOrderInfo);

  @GET("work_order/{userId}/{type}/{pageNum}")
  Observable<Response<List<WorkOrderInfo>>> getWorkOrders(@Path("userId") int userId,
      @Path("type") int type, @Path("pageNum") int pageNum);

  @Multipart
  @POST("work_order/upload")
  Observable<Response<Integer>> uploadPic(@PartMap Map<String, RequestBody> maps,
      @Part("id") int id, @Part("state") int state);
}
