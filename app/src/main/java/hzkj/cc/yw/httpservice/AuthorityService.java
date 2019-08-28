package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface AuthorityService {

  @GET("authority/{moduleId}")
  Observable<Response<List<UserInfo>>> getModuleAuthority(@Path("moduleId") int moduleId);
}
