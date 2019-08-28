package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.DeliverCompany;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface CommonService {

  @GET("common/get_deliver_companys")
  Observable<Response<List<DeliverCompany>>> getDeliverCompanys();

  @GET("common/get_department/{id}")
  Observable<Response<List<Department>>> getDepartmentByParentId(@Path("id") int id);

  @GET("common/get_users_by_id/{id}")
  Observable<Response<List<UserInfo>>> getUserInfosByDepartmentId(@Path("id") int id);

  @GET("common/get_project/{id}")
  Observable<Response<List<ProjectInfo>>> getProjectByProjectId(@Path("id") int id);
}
