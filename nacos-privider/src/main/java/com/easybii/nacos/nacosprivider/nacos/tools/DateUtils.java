package com.easybii.nacos.nacosprivider.nacos.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);


    /**
     * 将double转为 date
     */
    public static Date double2Date(Double d) {
        try {
            Date t;
            Calendar base = Calendar.getInstance();
            SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd");
            //Delphi的日期类型从1899-12-30开始

            base.set(1899, 11, 30, 0, 0, 0);
            base.add(Calendar.DATE, d.intValue());
            base.add(Calendar.MILLISECOND, (int) ((d % 1) * 24 * 60 * 60 * 1000));
            t = base.getTime();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取当日开始时间  年月日
     */
    public static Date getStartDate() {
        Date date = new Date(System.currentTimeMillis());
        //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getTodayStart() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTime();
    }

    public static Date getTodayEnd() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return currentDate.getTime();
    }

    public static Date getYesterdayBegin() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTime();
    }

    public static Date getYesterdayEnd() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -1);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        return currentDate.getTime();
    }

    public static Date getWeekStart() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return currentDate.getTime();
    }

    public static Date getWeekEnd() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.setFirstDayOfWeek(Calendar.MONDAY);
        currentDate.set(Calendar.HOUR_OF_DAY, 23);
        currentDate.set(Calendar.MINUTE, 59);
        currentDate.set(Calendar.SECOND, 59);
        currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return currentDate.getTime();
    }

    public static boolean isSunDay(Date date) {
        final Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static Date getLastWeekBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getLastWeekEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_YEAR, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取指定年月的开始时间
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getBeginTime(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定年月的开始时间
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getEndTime(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    public static LocalDateTime getToday0H() {
        return LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    public static LocalDateTime getYesterday0H() {
        return LocalDateTime.now().minusDays(1L).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取N天前
     */
    public static Date getNDayBefore(Date time, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, -n);
        return calendar.getTime();
    }

    /**
     * 获取N天后
     */
    public static Date getNDayAfter(Date time, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date getDayBegin(Date beginTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

    public static Date getDayEnd(Date endTime) {
        Calendar calendar = Calendar.getInstance();
        if (endTime == null) {
            calendar.setTime(new Date());
        } else {
            calendar.setTime(endTime);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();

    }

    public static Date getDate(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            logger.warn("parse date exception: ", e);
        }
        return null;
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String getFormatDateDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getFormatDateTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date getFormatDateDay(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getFormatDateYearAndMonth(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }


    public static Date getFormatDateDayHHmmss(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMonth(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getYear(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDay(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        try {
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 年份的第一天
     * @return
     */
    public static Date getYearStartTime(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.roll(Calendar.DAY_OF_YEAR, 0);
        return calendar.getTime();
    }

    /**
     * 年份的最后一天
     * @return
     */
    public static Date getYearEndTime(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.getTime();
    }

    /**
     * 根据当前时间获取几天后的日期或者几天前的日期
     */
    public static Date getBeforeOrAfterDate(Date currentDate, int num) {
        Calendar calendar = Calendar.getInstance();//获取日历
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, num);
        Date d = calendar.getTime();//把日历转换为Date
        return d;
    }

    /**
     * 未来n月时间
     * @param currentDate
     * @param n
     * @return
     */
    public static Date getNextMonthTime(Date currentDate, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, n);
        Date m = c.getTime();
        return m;
    }

    /**
     * 前n月时间
     * @param currentDate
     * @param n
     * @return
     */
    public static Date getBeforeMonthTime(Date currentDate, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.MONTH, -n);
        Date m = c.getTime();
        return m;
    }

    /**
     * 判断一个日期是否在一个时间段之内
     *
     * @Param date  当前时间
     * @Param beginDate  开始时间
     * @Param endDate 结束时间
     */
    public static boolean isInDate(Date date, Date beginDate, Date endDate) {
        long dateTime = date.getTime();
        long beginDateTime = beginDate.getTime();
        long endDateTime = endDate.getTime();
        if (dateTime >= beginDateTime && dateTime < endDateTime) {
            return true;
        } else {
            return false;
        }
    }


    //获取指定年月的第一天
    public static Date monthBegin(Integer annual, Integer month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, annual);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 获取指定年月的最后一天
     */
    public static Date monthEnd(Integer annual, Integer month) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, annual);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return c.getTime();
    }

    /**
     * 判断日期是否是周六周日
     */
    public static int isWeekday(Date t) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return 6;
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return 0;
        }
        return -1;
    }


    /**
     * 年份差
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     * @throws ParseException
     */
    public static int yearsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

    /**
     * 月份差
     */
    /**
     * 获取两个时间点的月份差
     *
     * @param d1 第一个时间点
     * @param d2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int monthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2)
            yearInterval--;
         // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2)
                monthInterval--;
        monthInterval %= 12;
        int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);
        return monthsDiff;
    }

    /**
     * 判读时间差距，两个时间相差多少小时
     */
    public static double getHoursDiff(Date startTime,Date endTime) {
        long diff = endTime.getTime() - startTime.getTime();
        double hours = BigDecimalUtils.div(diff,1000*60*60,2);
        return hours;
    }

    /**
     * 月初
     */
    public static Date firstMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 月末
     */
    public static Date lastMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 星期几
     */
    public static int week(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2) {
            //同一年
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++) {
                if(i%4==0 && i%100!=0 || i%400==0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2-day1) ;
        } else {
            //不同年
            return day2-day1;
        }
    }
}
