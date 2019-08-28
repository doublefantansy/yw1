package hzkj.cc.yw.httpservice;

import java.util.List;

import hzkj.cc.yw.bean.ProcedureGoods;
import hzkj.cc.yw.bean.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ProcedureGoodsService {

  @GET("procedure_goods/{deliverId}")
  Observable<Response<List<ProcedureGoods>>> get(@Path("deliverId") int deliverId);
}
