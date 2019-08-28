package hzkj.cc.yw.ui.fragment;

import android.content.Intent;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import com.vise.log.ViseLog;
import com.xuexiang.xui.widget.button.ButtonView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.bean.AttenceLimitInfo;
import hzkj.cc.yw.contract.AttenceInContract;
import hzkj.cc.yw.presenter.AttenceInPresenter;
import hzkj.cc.yw.ui.activity.AttenceMapActivity;
import hzkj.cc.yw.utils.TimeUtil;
import hzkj.cc.yw.utils.UiUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class AttenceFragment extends BaseFragment<AttenceInContract.Presenter> implements
    AttenceInContract.View {

  @BindView(R.id.daysOfWeek)
  TextView daysOfWeek;
  @BindView(R.id.date)
  TextView date;
  @BindView(R.id.time)
  TextView time;
  @BindView(R.id.inTime)
  TextView inTime;
  @BindView(R.id.OutTime)
  TextView OutTime;
  @BindView(R.id.attenceInButton)
  ButtonView attenceIn;
  @BindView(R.id.attenceOutButton)
  ButtonView attenceOut;
  boolean attenceInEnable = true;
  boolean attenceOutEnable = true;

  @Override
  public int getLayoutId() {
    return R.layout.fragment_attence;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    ViseLog.d(data);
    getPresenter().startGetAttenceStatus();
  }

  @Override
  public void initView() {
    daysOfWeek.setText(TimeUtil.getDaysOfWeek());
    date.setText(TimeUtil.calenderToString(Calendar.getInstance(), TimeUtil.YMDSTR));
    time.setText(TimeUtil.calenderToString(Calendar.getInstance(), TimeUtil.HMSSTR));
    attenceIn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (attenceInEnable) {
          UiUtil.jumpToActivity(getActivity(), AttenceMapActivity.class,
              new ArrayMap<String, String>() {{
                put("type", "0");
                put("limit", inTime.getText()
                    .toString());
              }}, false, true, 0);
        }
      }
    });
    attenceOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (attenceOutEnable) {
          UiUtil.jumpToActivity(getActivity(), AttenceMapActivity.class,
              new ArrayMap<String, String>() {{
                put("type", "1");
                put("limit", OutTime.getText()
                    .toString());
              }}, false, true, 0);
//                    UiUtil.jumpToActivity(getActivity(), AttenceMapActivity.class, new ArrayMap<String, String>() {{
//                        put("type", "1");
//                        put("limit", OutTime.getText()
//                                .toString());
//
//                    }}, false);
        }
      }
    });
    Observable.interval(1000, TimeUnit.MILLISECONDS)
        .compose(this.<Long>bindToLifecycle())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Action1<Long>() {
          @Override
          public void call(Long aLong) {
            time.setText(TimeUtil.calenderToString(Calendar.getInstance(), TimeUtil.HMSSTR));
          }
        });
//        calendarView.addDecorator(new DayViewDecorator() {
//            @Override
//            public boolean shouldDecorate(CalendarDay day) {
//                LocalDate localDate = LocalDate.of(2019, 6, 17);
//                ViseLog.d(localDate);
//                ViseLog.d(day.getDate());
//                if (day.getDate()
//                        .equals(localDate)) {
//                    return true;
//                }
//                return false;
//            }
//
//            @Override
//            public void decorate(DayViewFacade view) {
//                view.setDaysDisabled(true);
//            }
//        });
  }

  @Override
  public void initData() {
    getData();
//        getPresenter().startGetLimitTime();
//        getPresenter().startGetAttenceStatus();
  }

  @Override
  public void getData() {
    getPresenter().startGetLimitTime();
    getPresenter().startGetAttenceStatus();
  }

  @Override
  public void updateSuccessUi(Object object, int tag) {
    if (tag == AttenceInPresenter.LIMIT) {
      List<AttenceLimitInfo> attenceLimitInfos = (List<AttenceLimitInfo>) object;
      inTime.setText(attenceLimitInfos.get(0)
          .getLimit());
      OutTime.setText(attenceLimitInfos.get(1)
          .getLimit());
    } else if (tag == AttenceInPresenter.NONEISED) {
      attenceInEnable = false;
      attenceOutEnable = false;
      attenceIn.setSolidColor(getResources().getColor(R.color.backGroundGray));
      attenceOut.setSolidColor(getResources().getColor(R.color.backGroundGray));
      attenceIn.setText((String) object);
      attenceOut.setText((String) object);
    } else if (tag == AttenceInPresenter.INISED) {
      attenceInEnable = false;
      attenceIn.setSolidColor(getResources().getColor(R.color.backGroundGray));
      attenceIn.setText((String) object);
    } else if (tag == AttenceInPresenter.OUTISED) {
      attenceOutEnable = false;
      attenceOut.setSolidColor(getResources().getColor(R.color.backGroundGray));
      attenceOut.setText((String) object);
    }
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }

  @Override
  void doOnVisible() {
  }

  @Override
  AttenceInContract.Presenter createPresenter() {
    return new AttenceInPresenter();
  }
}
