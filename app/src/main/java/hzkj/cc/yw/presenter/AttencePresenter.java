package hzkj.cc.yw.presenter;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Calendar;

import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Response;
import hzkj.cc.yw.bean.UserInfo;
import hzkj.cc.yw.config.MyApplication;
import hzkj.cc.yw.contract.AttenceContract;
import hzkj.cc.yw.model.AttenceModel;
import hzkj.cc.yw.utils.StorageUtil;
import hzkj.cc.yw.utils.TimeUtil;

public class AttencePresenter extends
    BasePresenter<AttenceContract.View, AttenceContract.Model> implements
    AttenceContract.Presenter {

  private String type;
  LocationClient locationClient;

  @Override
  AttenceContract.Model createModel() {
    initLocationOption();
    return new AttenceModel();
  }

  @Override
  public void returnDataSuccess(Response response, int tag) {
    if (response.getCode() == 400) {
      getView().updateSuccessUi(response, 0);
    } else if (response.getCode() == 401 | response.getCode() == 402) {
      getView().updateSuccessUi(response, -1);
    } else {
      getView().updateSuccessUi(response.getData(), 1);
    }
  }

  @Override
  public void startGetAttenceMessage() {
    getModel().getAttenceMessage(StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
            .getMobile(), TimeUtil.getLimitDateString(Calendar.getInstance()
            .get(Calendar.YEAR), Calendar.getInstance()
            .get(Calendar.MONTH), TimeUtil.MONTHSTART, TimeUtil.YMD),
        TimeUtil.getLimitDateString(Calendar.getInstance()
            .get(Calendar.YEAR), Calendar.getInstance()
            .get(Calendar.MONTH) + 1, TimeUtil.MONTHEND, TimeUtil.YMD), (RxFragment) getView());
  }

  @Override
  public void startStartAttence(String type) {
    this.type = type;
    locationClient.start();
  }

  @Override
  public void checkDate(String startDate, String endDate) {
    if (startDate.compareTo(endDate) > 0) {
      getView().updateCheckFailUi(MyApplication.getApplication()
          .getResources()
          .getString(R.string.StartDateNotLateEndDate), NORMAL);
    } else if (startDate.equals("") || endDate.equals("")) {
      getView().updateCheckFailUi(MyApplication.getApplication()
          .getResources()
          .getString(R.string.StartDateAndLateEndDateNotNull), NORMAL);
    } else {
      getView().updateCheckSuccess(null);
    }
  }

  private void initLocationOption() {
//定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
    locationClient = new LocationClient(MyApplication.getApplication()
        .getApplicationContext());
//声明LocationClient类实例并配置定位参数
    LocationClientOption locationOption = new LocationClientOption();
    MyLocationListener myLocationListener = new MyLocationListener();
    locationClient.registerLocationListener(myLocationListener);
    locationOption.setCoorType("bd09ll");
    locationOption.setIsNeedAddress(true);
    locationClient.setLocOption(locationOption);
  }

  /**
   * 实现定位回调
   */
  public class MyLocationListener extends BDAbstractLocationListener {

    @Override
    public void onReceiveLocation(BDLocation location) {
//            double latitude = location.getLatitude();
//            double longiTude = location.getLongitude();
//            String place = location.getAddrStr();
//            AttenceLimitInfo attenceLimitInfo = new AttenceLimitInfo();
//            attenceLimitInfo.setAntiTude((float) latitude);
//            attenceLimitInfo.setLongiTude((float) longiTude);
//            attenceLimitInfo.setDescription(place);
//            getModel().startAttence(attenceLimitInfo, StorageUtil.getData(StorageUtil.KEY_USER, UserInfo.class)
//                    .getMobile(), TimeUtil.getLimitDateString(Calendar.getInstance(), -1, TimeUtil.YMD), TimeUtil.getPartOfTime(Calendar.getInstance()
//                    .getTime(), TimeUtil.PART_HMS
//            ), type, (RxFragment) getView());
//            locationClient.stop();
    }
  }
}
