package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.HomeBannerItem;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import rx.Observable;

public interface HomeBannerItemService {

  @GET("home_banner_items")
  Observable<Response<List<HomeBannerItem>>> getBannerItems();
}
