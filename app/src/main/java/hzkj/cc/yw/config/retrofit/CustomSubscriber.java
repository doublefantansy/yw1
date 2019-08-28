package hzkj.cc.yw.config.retrofit;

import com.vise.log.ViseLog;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.config.MyApplication;
import hzkj.cc.yw.contract.BaseContract;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

public class CustomSubscriber extends Subscriber {

  public static final int TOKENTIMEOUT = 403;
  public static final int CONNECTFAIL = 111110;
  private BaseContract.Presenter presenter;
  Response response;
  int tag;

  public CustomSubscriber(BaseContract.Presenter presenter, int tag) {
    super();
    this.presenter = presenter;
    this.tag = tag;
  }

  @Override
  public void onCompleted() {
    if (response != null) {
      presenter.returnDataSuccess(response, tag);
    }
  }

  @Override
  public void onError(Throwable e) {
    ViseLog.d(e.getMessage());
    if (e instanceof HttpException) {
      if (((HttpException) e).code() == 403) {
        presenter.returnDataFail(MyApplication.getApplication()
            .getResources()
            .getString(R.string.tokenOutTime), TOKENTIMEOUT);
      }
    } else if (e instanceof ConnectException | e instanceof SocketTimeoutException) {
      presenter.returnDataFail(MyApplication.getApplication()
          .getResources()
          .getString(R.string.netError), CONNECTFAIL);
    } else {
      presenter.returnDataFail("未知错误", CONNECTFAIL);
    }
  }

  @Override
  public void onNext(Object object) {
    this.response = (Response) object;
  }
}