package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.SupplyInfo;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface SupplyService {

  @GET("supply/{projectId}")
  Observable<Response<List<SupplyInfo>>> getDeviceByProject(@Path("projectId") int projectId);

  @GET("supply/part/{parentUuid}")
  Observable<Response<List<SupplyInfo>>> getAllPart(@Path("parentUuid") String parentUuid);

  @POST("supply/{state}/{supplyId}")
  Observable<Response<Integer>> lockSupply(@Path("state") int state,
      @Path("supplyId") int supplyId);
}
