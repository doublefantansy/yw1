package hzkj.cc.yw.ui.fragment;

import static hzkj.cc.yw.bean.AttenceInfo.ATTENCECOUNT;

import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.internal.LinkedTreeMap;
import com.haibin.calendarview.CalendarView;
import com.vise.log.ViseLog;
import hzkj.cc.ccrecyclerview.CcRrefreshAndLoadMoreRecyclerView;
import hzkj.cc.yw.R;
import hzkj.cc.yw.adapter.AttenceCalendarAdapter;
import hzkj.cc.yw.bean.AttenceInfo;
import hzkj.cc.yw.contract.AttenceCalendarContract;
import hzkj.cc.yw.presenter.AttenceCalendarPresenter;
import hzkj.cc.yw.utils.TimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AttenceCalendarFragment extends
    BaseFragment<AttenceCalendarContract.Presenter> implements AttenceCalendarContract.View {

  static final int LATE = 1;
  static final int EARLY = 11;
  static final int NORMAL = 111;
  @BindView(R.id.calendarView)
  CalendarView calendarView;
  @BindView(R.id.tv_month_day)
  TextView tv_month_day;
  @BindView(R.id.tv_year)
  TextView tv_year;
  @BindView(R.id.recyclerView)
  CcRrefreshAndLoadMoreRecyclerView recyclerView;
  //    @BindView(R.id.time)
//    TextView time;
  Map<String, Map<String, AttenceInfo>> datas = new LinkedTreeMap<>();
  List<String> everyDays;
  List<String> notShouldMark = new ArrayList<>();
  AttenceCalendarAdapter adapter;
  Map<String, AttenceInfo> attenceInfos = new LinkedTreeMap<>();
  Map<String, com.haibin.calendarview.Calendar> map = new HashMap<>();
  //    Map<String, List<Integer>> status = new HashMap<>();
  List<String> strings = new ArrayList<>();
  int curMonth = Calendar.getInstance()
      .get(Calendar.MONTH) + 1;
  int curYear = Calendar.getInstance()
      .get(Calendar.YEAR);

  @Override
  void doOnVisible() {
  }

  @Override
  AttenceCalendarContract.Presenter createPresenter() {
    return new AttenceCalendarPresenter();
  }

  @Override
  public int getLayoutId() {
    return R.layout.fragment_attence_calendar;
  }

  @Override
  public void initView() {
    recyclerView.setRefreshEnable(false);
    recyclerView.setLoadMoreEnable(false);
    adapter = new AttenceCalendarAdapter(getActivity(), attenceInfos);
    recyclerView.init(adapter);
    calendarView.setOnMonthChangeListener(new CalendarView.OnMonthChangeListener() {
      @Override
      public void onMonthChange(int year, int month) {
        ViseLog.d(year);
        ViseLog.d(calendarView.getCurYear());
        curMonth = month;
        curYear = year;
        if (year < Calendar.getInstance()
            .get(Calendar.YEAR) | month - 1 <= Calendar.getInstance()
            .get(Calendar.MONTH)) {
          getPresenter().startGetAttenceMessage(
              TimeUtil.getLimitDateString(year, month - 1, TimeUtil.MONTHSTART, TimeUtil.YMD),
              TimeUtil.getLimitDateString(year, month - 1, TimeUtil.MONTHEND, TimeUtil.YMD));
        }
      }
    });
    calendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
      @Override
      public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
      }

      @Override
      public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        tv_year.setText(calendar.getYear()
            + "");
        tv_month_day.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        attenceInfos.clear();
        String date =
            calendar.getYear() + "-" + TimeUtil.getFormatDay(calendar.getMonth()) + "-" + TimeUtil
                .getFormatDay(calendar.getDay());
        if (datas.get(date)
            != null) {
          attenceInfos.putAll(datas.get(
              calendar.getYear() + "-" + TimeUtil.getFormatDay(calendar.getMonth()) + "-" + TimeUtil
                  .getFormatDay(calendar.getDay())));
        }
        recyclerView.refreshComplete(true);
        ViseLog.d(datas.get(
            calendar.getYear() + "-" + TimeUtil.getFormatDay(calendar.getMonth()) + "-" + calendar
                .getDay()));
      }
    });
    tv_year.setText(Calendar.getInstance()
        .get(Calendar.YEAR) + "");
    tv_month_day.setText(TimeUtil.calenderToString(Calendar.getInstance(), TimeUtil.MDSTR));
  }

  @Override
  public void initData() {
    getData();
  }

  @Override
  public void getData() {
    getPresenter().startGetAttenceMessage(TimeUtil.getLimitDateString(Calendar.getInstance()
            .get(Calendar.YEAR), Calendar.getInstance()
            .get(Calendar.MONTH), TimeUtil.MONTHSTART, TimeUtil.YMD),
        TimeUtil.dateToString(new Date(), TimeUtil.YMD));
  }

  @Override
  public void updateSuccessUi(Object object, final int tag) {
    map.clear();
    datas = (Map<String, Map<String, AttenceInfo>>) object;
    ViseLog.d(datas);
    strings.clear();
    if (datas.get(TimeUtil.dateToString(Calendar.getInstance()
        .getTime(), TimeUtil.YMD))
        != null) {
      attenceInfos.putAll(datas.get(TimeUtil.dateToString(Calendar.getInstance()
          .getTime(), TimeUtil.YMD)));
    }
    recyclerView.refreshComplete(true);
    everyDays = TimeUtil.getWorkDaysByMonth(
        TimeUtil.getLimitDateString(curYear, curMonth - 1, TimeUtil.MONTHSTART, TimeUtil.YMD),
        curMonth == Calendar.getInstance()
            .get(Calendar.MONTH) + 1 ? TimeUtil.dateToString(new Date(), TimeUtil.YMD)
            : TimeUtil.getLimitDateString(curYear, curMonth - 1, TimeUtil.MONTHEND, TimeUtil.YMD));
    ViseLog.d(TimeUtil.getLimitDateString(Calendar.getInstance()
        .get(Calendar.YEAR), calendarView.getCurMonth() - 1, TimeUtil.MONTHSTART, TimeUtil.YMD));
    ViseLog.d(everyDays);
    Map<String, Map<String, AttenceInfo>> allDays = new HashMap<>();
    ((HashMap) allDays).putAll(datas);
    for (String everyDay : everyDays) {
      if (allDays.get(everyDay) == null) {
        allDays.put(everyDay, new TreeMap<String, AttenceInfo>());
      }
    }
    for (Map.Entry<String, Map<String, AttenceInfo>> entry : allDays.entrySet()) {
      int year = TimeUtil.stringToCalender(entry.getKey(), TimeUtil.YMD)
          .get(Calendar.YEAR);
      int month = TimeUtil.stringToCalender(entry.getKey(), TimeUtil.YMD)
          .get(Calendar.MONTH) + 1;
      int day = TimeUtil.stringToCalender(entry.getKey(), TimeUtil.YMD)
          .get(Calendar.DAY_OF_MONTH);
      if (!TimeUtil.dateToString(new Date(), TimeUtil.YMD)
          .equals(entry.getKey())) {
        if (entry.getValue()
            .size() == ATTENCECOUNT) {
          if (entry.getValue()
              .get("0")
              .getLeaveType()
              .equals(AttenceInfo.NORMAL) && entry.getValue()
              .get("1")
              .getLeaveType()
              .equals(AttenceInfo.NORMAL)) {
            map.put(
                getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGreen), "")
                    .toString(),
                getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGreen), ""));
          } else {
            map.put(getSchemeCalendar(year, month, day, getResources().getColor(R.color.red), "")
                    .toString(),
                getSchemeCalendar(year, month, day, getResources().getColor(R.color.red), ""));
          }
        } else if (entry.getValue()
            .size() == 1) {
          map.put(getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGray), "")
                  .toString(),
              getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGray), ""));
        } else {
          map.put(getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGray), "")
                  .toString(),
              getSchemeCalendar(year, month, day, getResources().getColor(R.color.myGray), ""));
        }
      }
    }
    calendarView.setSchemeDate(map);
  }

  private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day,
      int color, String text) {
    com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
    calendar.setYear(year);
    calendar.setMonth(month);
    calendar.setDay(day);
    calendar.setSchemeColor(color);
    calendar.setScheme(text);
    return calendar;
  }

  @Override
  public void updateCheckSuccess(Object object) {
  }
}
