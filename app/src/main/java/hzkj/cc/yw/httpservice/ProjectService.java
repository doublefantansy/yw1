package hzkj.cc.yw.httpservice;

import hzkj.cc.yw.bean.ProjectInfo;
import hzkj.cc.yw.bean.Response;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ProjectService {

  @GET("project/{departmentId}")
  Observable<Response<List<ProjectInfo>>> get(@Path("departmentId") int departmentId);
}
