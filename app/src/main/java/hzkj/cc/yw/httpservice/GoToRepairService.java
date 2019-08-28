package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.Procedure;
import hzkj.cc.yw.bean.RepairInfo;
import hzkj.cc.yw.bean.RepairInfoDetail;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GoToRepairService {

  @GET("go_to_repair/get_repair_infos/{id}/{type}/{pageNum}")
  Observable<Response<List<RepairInfo>>> getRepairInfos(@Path("id") int id, @Path("type") int type,
      @Path("pageNum") int pageNum);

  @GET("go_to_repair/search_by_project_id/{id}")
  Observable<Response<List<RepairInfo>>> searchByProjectId(@Path("id") int id);

  @GET("go_to_repair/search_by_repair_id/{id}")
  Observable<Response<List<RepairInfo>>> searchByRepairId(@Path("id") int id);

  @GET("go_to_repair/search_by_date/{startDate}/{endDate}/{status}")
  Observable<Response<List<RepairInfo>>> searchByDate(@Path("startDate") String startDate,
      @Path("endDate") String endDate, @Path("status") int status);

  @GET("go_to_repair/get_repair_info_details/{id}")
  Observable<Response<List<RepairInfoDetail>>> getRepairInfoDetails(@Path("id") int id);

  @GET("go_to_repair/get_repair_reviews/{id}")
  Observable<Response<List<Procedure>>> getRepairReviews(@Path("id") int id);
}
