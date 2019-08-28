package hzkj.cc.yw.ui.activity;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.popupwindow.ViewTooltip;
import hzkj.cc.loadingdialog.AttdenceView;
import hzkj.cc.loadingdialog.CancelListener;
import hzkj.cc.loadingdialog.CcDialog;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.Department;
import hzkj.cc.yw.config.MyApplication;
import hzkj.cc.yw.contract.AttenceMapContract;
import hzkj.cc.yw.presenter.AttenceMapPresenter;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;

public class AttenceMapActivity extends BaseActivity<AttenceMapContract.Presenter> implements
    AttenceMapContract.View, EasyPermissions.PermissionCallbacks {

  @BindView(R.id.bmapView)
  MapView mapView;
  @BindView(R.id.attence)
  AttdenceView attence;
  @BindView(R.id.type)
  TextView type;
  @BindView(R.id.address)
  TextView address;
  BaiduMap mBaiduMap;
  private boolean isFirstLoc = true;
  int typeId = -1;
  String limit;
  BDLocation bdLocation;
  LatLng center;
  CcDialog ccDialog;
  Department department;
  private String permissions[] = new String[]{
      Manifest.permission.ACCESS_COARSE_LOCATION,
      Manifest.permission.ACCESS_FINE_LOCATION,
  };

  @Override
  public boolean isShowingTitleBar() {
    return true;
  }

  @Override
  public int getDrawLayout() {
    return 0;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    SDKInitializer.initialize(MyApplication.getApplication());
    super.onCreate(savedInstanceState);
  }

  @Override
  AttenceMapContract.Presenter createPresenter() {
    return new AttenceMapPresenter();
  }

  @Override
  public void getData() {
  }

  @Override
  public int getLayoutId() {
    return R.layout.attence_map_activity;
  }

  @Override
  public void initView() {
    bar.setTitle("定位");
    mBaiduMap = mapView.getMap();
    MyLocationConfiguration configuration = new MyLocationConfiguration(
        LocationMode.NORMAL, false, null);
    configuration.accuracyCircleFillColor = getResources().getColor(R.color.transparent);
    configuration.accuracyCircleStrokeColor = getResources().getColor(R.color.transparent);
    mBaiduMap.setMyLocationConfiguration(configuration);
    mBaiduMap.setMyLocationEnabled(true);
    attence.setText("签到");
    attence.setTextSize(18);
    address.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (address.getLayout()
            .getEllipsisCount(0) > 0) {
          ViewTooltip
              .on(address)
              .color(Color.BLACK)
              .position(ViewTooltip.Position.BOTTOM)
              .text(address.getText()
                  .toString())
              .clickToHide(true)
              .autoHide(true, 3000)
              .animation(new ViewTooltip.FadeTooltipAnimation(500))
              .show();
        }
      }
    });
    attence.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ccDialog = new CcDialog(AttenceMapActivity.this, CcDialog.PROGRESS_DIALOG)
            .setMessage("正在签到中");
        ccDialog.showDialog();
        getPresenter().checkDistance(DistanceUtil
                .getDistance(center, new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude())),
            department.getAttenceDistance());
      }
    });
  }

  @Override
  public void initData() {
    getPresenter().startGetDepartmentCoordinate();
    typeId = Integer.valueOf(getIntent().getStringExtra("type"));
    limit = getIntent().getStringExtra("limit");
    type.setText(typeId == 0 ? "签到" : "签退");
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == AttenceMapPresenter.GETDEPARTMENT) {
      department = (Department) object;
      center = new LatLng(department.getAntitude(), department.getLongitude());
      CircleOptions mCircleOptions = new CircleOptions().center(center)
          .radius(department.getAttenceDistance())
          .fillColor(getResources().getColor(R.color.baiduBlue)) //填充颜色
          .stroke(new Stroke(1, getResources().getColor(R.color.baiduBlue))); //边框宽和边框颜色
      mBaiduMap.addOverlay(mCircleOptions);
      if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (EasyPermissions.hasPermissions(this, permissions)) {//判断是否拥有权限
          getPresenter().startLocate();
        } else {
          EasyPermissions.requestPermissions(this, "请求必要的权限,拒绝权限可能会无法使用app", 0, permissions);
        }
      } else {
        getPresenter().startLocate();
      }
    } else if (tag == AttenceMapPresenter.ATTENCESUCCESS) {
      ccDialog.dismissDialog();
      ccDialog = new CcDialog(AttenceMapActivity.this, CcDialog.SUCCESS_DIALOG)
          .setMessage((String) object)
          .setCancelListener(new CancelListener() {
            @Override
            public void onClick(CcDialog dialog) {
              finish();
              setResult(0);
            }
          });
      ccDialog.showDialog();
    } else {
      bdLocation = (BDLocation) object;
      ViseLog.d(bdLocation);
      address.setText(bdLocation.getAddrStr());
      if (isFirstLoc) {
        isFirstLoc = false;
        LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder()
            .target(ll)
            .zoom(18f);
        //改变地图状态
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
      }
      MyLocationData data = new MyLocationData.Builder()
          .accuracy(bdLocation.getRadius())//定位精度
          .latitude(bdLocation.getLatitude())//纬度
          .longitude(bdLocation.getLongitude())//经度
          .direction(bdLocation.getDirection())//方向 可利用手机方向传感器获取 此处为方便写死
          .build();
      //设置定位数据
      mBaiduMap.setMyLocationData(data);
    }
  }

  @Override
  public void updateCheckFailUi(String msg, Object object) {
    ccDialog.dismissDialog();
    ccDialog = new CcDialog(AttenceMapActivity.this, CcDialog.FAIL_DIALOG)
        .setMessage((String) object)
        .setCancelListener(new CancelListener() {
          @Override
          public void onClick(CcDialog dialog) {
            ccDialog.dismissDialog();
          }
        });
  }

  @Override
  public void updateCheckSuccess(Object object) {
    getPresenter()
        .startAttence(bdLocation.getLatitude() + "", bdLocation.getLongitude() + "", typeId,
            bdLocation.getAddrStr(), DistanceUtil.getDistance(center,
                new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude())) + "", limit);
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    getPresenter().endLocate();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    if (requestCode == 0) {
      getPresenter().startLocate();
    }
  }

  @Override
  public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
  }
}
