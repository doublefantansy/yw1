package hzkj.cc.yw.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间操作工具类
 */
public class TimeUtil {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
  public final static String YMD = "yyyy-MM-dd";
  public final static String YMDHMS = "yyyy-MM-dd:HH:mm:ss";
  public final static String YMDSTR = "yyyy年MM月dd日";
  public final static String MDSTR = "MM月dd日";
  public final static String HMSSTR = "HH:mm:ss";
  public final static String HMSTR = "HH:mm";
  public final static int MONTHSTART = 0;
  public final static int MONTHEND = 1;
  public final static int PART_YMD = 2;
  public final static int PART_HMS = 3;
  private static final int[] DAYS_MONTH_R = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private static final int[] DAYS_MONTH_NR = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static String getDaysOfWeek() {
    String result = null;
    switch (Calendar.getInstance()
        .get(Calendar.DAY_OF_WEEK)) {
      case 1: {
        result = "星期日";
        break;
      }
      case 2: {
        result = "星期一";
        break;
      }
      case 3: {
        result = "星期二";
        break;
      }
      case 4: {
        result = "星期三";
        break;
      }
      case 5: {
        result = "星期四";
        break;
      }
      case 6: {
        result = "星期五";
        break;
      }
      case 7: {
        result = "星期六";
        break;
      }
    }
    return result;
  }

  public static String getFormatDay(int time) {
    if (time < 10) {
      return "0" + time;
    }
    return time + "";
  }
  public static String format(String time,String pattern) {
    try {
      simpleDateFormat.applyPattern(pattern);
      return simpleDateFormat.format(simpleDateFormat.parse(time));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
  public static String calenderToString(java.util.Calendar calendar, String pattern) {
    simpleDateFormat.applyPattern(pattern);
    return simpleDateFormat.format(calendar.getTime());
  }

  public static Calendar stringToCalender(String s, String pattern) {
    simpleDateFormat.applyPattern(pattern);
    Calendar calendar = Calendar.getInstance();
    try {
      calendar.setTime(simpleDateFormat.parse(s));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return calendar;
  }

  public static String dateToString(Date date, String pattern) {
    simpleDateFormat.applyPattern(pattern);
    return simpleDateFormat.format(date);
  }

  public static String getLimitDateString(int year, int month, int var, String pattern) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, month);
    calendar.set(Calendar.YEAR, year);
    switch (var) {
      case MONTHSTART: {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        break;
      }
      case MONTHEND: {
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        break;
      }
    }
    return calenderToString(calendar, pattern);
  }

  public static List<String> getWorkDaysByMonth(String startStr, String endStr) {
    List<String> worksDay = new ArrayList<>();
    String[] holiday = new String[]{
        "12-30", "12-31", "1-1", "2-4", "2-5", "2-6", "2-7", "2-8",
        "2-9", "2-10", "4-5", "4-6", "4-7", "5-1", "5-2", "5-3", "5-4", "6-7", "6-8", "6-9", "9-13",
        "9-14", "9-15", "10-1", "10-2", "10-3", "10-4", "10-5", "10-6", "10-7"
    };
    List<String> holidays = Arrays.asList(holiday);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = null;
    Date endDate = null;
    try {
      startDate = dateFormat.parse(startStr);
      endDate = dateFormat.parse(endStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    Calendar start = Calendar.getInstance();
    start.setTime(startDate);
    Calendar end = Calendar.getInstance();
    end.setTime(endDate);
    while (true) {
      if (dateFormat.format(start.getTime())
          .equals(dateFormat.format(end.getTime()))) {
        break;
      }
      if (!((start.get(Calendar.DAY_OF_WEEK)) == 1 | (start.get(Calendar.DAY_OF_WEEK)) == 7)) {
        if (!holidays
            .contains(start.get(Calendar.MONTH) + 1 + "-" + start.get(Calendar.DAY_OF_MONTH))) {
          worksDay.add(TimeUtil.dateToString(start.getTime(), TimeUtil.YMD));
        }
      }
      start.add(Calendar.DAY_OF_MONTH, 1);
    }
    return worksDay;
  }
}
