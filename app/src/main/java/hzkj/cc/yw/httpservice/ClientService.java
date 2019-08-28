package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.ClientContactInfo;
import hzkj.cc.yw.bean.ClientInfo;
import hzkj.cc.yw.bean.Response;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ClientService {

  @GET("client/search/project/{projectId}")
  Observable<Response<List<ClientInfo>>> searchByProjectId(@Path("projectId") int projectId);
  @GET("client/search/customer/{customerId}")
  Observable<Response<List<ClientContactInfo>>> searchByCustomerId(@Path("customerId") int customerId);
  @GET("client/{pageNum}")
  Observable<Response<List<ClientInfo>>> getClientInfo(@Path("pageNum") int pageNum);
//  @POST("client/deleteOne/{id}")
//  Observable<Response<Integer>> deleteOne(@Path("id") int id);
//
//  @POST("client/update_client_info")
//  Observable<Response<Integer>> updateClientInfo(@Body ClientInfo clientInfo);
//
//  @GET("client/get_project_infos/{projectType}")
//  Observable<Response<List<ProjectInfo>>> getProjectInfos(@Path("projectType") String projectType);
//
//  @GET("client/get_client_type_infos")
//  Observable<Response<List<ClientTypeInfo>>> getClientTypeInfos();

//  @GET("client/{projectId}/{pageNum}")
//  Observable<Response<List<ClientInfo>>> searchByProject(@Path("projectId") int projectId,
//      @Path("pageNum") int pageNum);
//  @POST("client/insert_client_info")
//  Observable<Response<Integer>> insertClientInfo(@Body ClientInfo clientInfo);
//
//  @GET("client/get_project_type_infos")
//  Observable<Response<List<ProjectTypeInfo>>> getProjectTypeInfos();
}
