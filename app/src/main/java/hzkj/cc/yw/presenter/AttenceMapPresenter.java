package hzkj.cc.yw.presenter;

import android.content.Context;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.vise.log.ViseLog;
import java.util.Date;
import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.retrofit.RetrofitFactory;
import hzkj.cc.yw.contract.AttenceMapContract;
import hzkj.cc.yw.model.AttenceMapModel;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;

public class AttenceMapPresenter extends
    BasePresenter<AttenceMapContract.View, AttenceMapContract.Model> implements
    AttenceMapContract.Presenter {

  public final static int ATTENCESUCCESS = 0;
  public final static int GETDEPARTMENT = 1;
  public final static int LOCATESUCCESS = 2;
  private LocationClient mLocationClient;
  private BDLocationListener mBDLocationListener = new BDLocationListener() {
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
      if (bdLocation != null) {
        getView().updateSuccessUi(bdLocation, LOCATESUCCESS);
      }
    }
  };

  @Override
  AttenceMapContract.Model createModel() {
    return new AttenceMapModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    ViseLog.d(response);
    if (response.getCode() == RetrofitFactory.GETSUCCESS) {
      getView().updateSuccessUi(response.getData(), GETDEPARTMENT);
    } else {
      getView().updateSuccessUi("签到成功", ATTENCESUCCESS);
    }
  }

  @Override
  public void startLocate() {
    LocationClientOption option = new LocationClientOption();
    option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
    option.setCoorType("bd09ll");
    int span = 1000;
    option.setScanSpan(span);
    //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
    option.setIsNeedAddress(true);
    //可选，设置是否需要地址信息，默认不需要
    option.setOpenGps(true);
    //可选，默认false,设置是否使用gps
    option.setLocationNotify(true);
    //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
    option.setIsNeedLocationDescribe(true);
    //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
    option.setIsNeedLocationPoiList(true);
    //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
    option.setIgnoreKillProcess(false);
    //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
    option.SetIgnoreCacheException(false);
    //可选，默认false，设置是否收集CRASH信息，默认收集
    option.setEnableSimulateGps(false);
    //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
    //加载设置
    mLocationClient = new LocationClient((Context) getView());
    mLocationClient.registerLocationListener(mBDLocationListener);
    mLocationClient.setLocOption(option);
    mLocationClient.start();
  }

  @Override
  public void endLocate() {
    if (mLocationClient.isStarted()) {
      mLocationClient.stop();
    }
  }

  @Override
  public void startGetDepartmentCoordinate() {
    getModel().getDepartmentCoordinate(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
        .getUserId(), (RxAppCompatActivity) getView());
  }

  @Override
  public void startAttence(String antiTude, String longiTude, int type, String description,
      String distance, String limit) {
    String time = TimeUtil.dateToString(new Date(), TimeUtil.HMSSTR);
    int leaveType = 0;
    if (type == 0) {
      if (time.compareTo(limit) > 0) {
        leaveType = 0;
      } else {
        leaveType = 5;
      }
    } else {
      if (time.compareTo(limit) < 0) {
        leaveType = 1;
      } else {
        leaveType = 5;
      }
    }
    AttenceInfo attenceInfo = new AttenceInfo(
        StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
            .getMobile(), TimeUtil.dateToString(new Date(), TimeUtil.YMD), time, type + "",
        antiTude, longiTude, description, distance, leaveType + "");
    getModel().attence(attenceInfo, (RxAppCompatActivity) getView());
  }

  @Override
  public void checkDistance(double distance, int attenceDistance) {
    if (distance > attenceDistance) {
      getView().updateCheckFailUi("请在指定范围内签到", null);
    } else {
      getView().updateCheckSuccess(null);
    }
  }
}

