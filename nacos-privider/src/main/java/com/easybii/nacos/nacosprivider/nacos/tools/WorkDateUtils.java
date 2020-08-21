package com.easybii.nacos.nacosprivider.nacos.tools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
*梁兴华
 * 19/6/18
*
 * 目的：解决判断询价是否超时
 *
 * 注意节假日、工作日 一年配置一次
 *
 * 获取 n 工作日后的日期等方法
* */

public class WorkDateUtils {

//    private static final Logger logger = LoggerFactory.getLogger(WorkDateUtils.class);

    //节假日列表
    public static List<String> holidayList = new ArrayList<String>();
    //周末为工作日
    public static List<String> weekendList = new ArrayList<String>();


    // 设置 节假日  工作日
    private static void setDate(){
        // 周一到周五放假的天
        holidayList = HolidayDate.getHolidayList();
        // 周末上班
        weekendList = HolidayDate.getWeekendList();
    }



    /**
    * 获取nowDate 在 num 个工作日以后的时间
    * */
    public static Date getDatePoor(Integer num, Date nowDate) {

        // 设置日期
        setDate();
        int Updatetime = 0;
        // 判断开始时间的下一天
        for (int i=1;i <= num;i++){
                //判断日期是否是周六周日
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                c.add(Calendar.DATE, i);
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长加1
                        Updatetime += 1;
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Updatetime += 1;
                    }
                }
        }
        // 计算最后时间
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        c.add(Calendar.DATE, Updatetime+num);
        // 修改 时间 + num  为周六 的bug
        // 判断 时间是否是周日
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            // 如果是 判断周日是否要上班
            SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdfDay.format(c.getTime());
            // 判断这个周末是否在周末要加班的区间内  循环
            if(!weekendList.contains(time)){
                // 否 总时长加1
                c.add(Calendar.DATE, 1);
            }
        }
        Date date=c.getTime();
        return date;
    }

    /**
     * 获取nowDate 在 n 分钟以后的时间
     * */
    public static Date getDatePoorMinutes(Integer num, Date nowDate) {
        // 设置日期
        setDate();
        int Updatetime = 0;
        // 判断开始时间的下一个小时
        //判断日期是否是周六周日
        Calendar c1 = Calendar.getInstance();
        c1.setTime(nowDate);
        c1.add(Calendar.MINUTE, num);
        Date time1 = c1.getTime();
        int hour = DateUtils.getHour(time1);
        switch (hour){
            case 0:
                c1.add(Calendar.HOUR,9);
                break;
            case 1:
                c1.add(Calendar.HOUR,8);
                break;
            case 2:
                c1.add(Calendar.HOUR,7);
                break;
            case 3:
                c1.add(Calendar.HOUR,6);
                break;
            case 4:
                c1.add(Calendar.HOUR,5);
                break;
            case 5:
                c1.add(Calendar.HOUR,4);
                break;
            case 6:
                c1.add(Calendar.HOUR,3);
                break;
            case 7:
                c1.add(Calendar.HOUR,2);
                break;
            case 8:
                c1.add(Calendar.HOUR,1);
                break;
            case 13:
                c1.add(Calendar.HOUR,1);
                break;
            case 18:
                c1.add(Calendar.HOUR,15);
                break;
            case 19:
                c1.add(Calendar.HOUR,14);
                break;
            case 20:
                c1.add(Calendar.HOUR,13);
                break;
            case 21:
                c1.add(Calendar.HOUR,12);
                break;
            case 22:
                c1.add(Calendar.HOUR,11);
                break;
            case 23:
                c1.add(Calendar.HOUR,10);
                break;
        }
        if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                // 如果是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c1.getTime());
                // 判断这个周末是否在周末要加班的区间内  循环
                if(!weekendList.contains(time)){
                    // 否 总时长加1
                    Updatetime += 1;
                }
            }else{
                // 不是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c1.getTime());
                // 判断这天是否在放假的区间内
                if(holidayList.contains(time)){
                    // 是 总时长减24小时
                    Updatetime += 1;
                }
            }
        // 计算最后时间
        Calendar c = Calendar.getInstance();
        c.setTime(c1.getTime());
        c.add(Calendar.DATE, Updatetime);
        // 修改 时间 + num  为周六 的bug
        // 判断 时间是否是周日
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            // 如果是 判断周日是否要上班
            SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdfDay.format(c.getTime());
            // 判断这个周末是否在周末要加班的区间内  循环
            if(!weekendList.contains(time)){
                // 否 总时长加1
                c.add(Calendar.DATE, 1);
            }
        }
        Date date=c.getTime();
        return date;
    }


    /**
     * 获取nowDate 在 n 小时以后的时间
     * */
    public static Date getDatePoorHours(Double num, Date nowDate) {
        // 设置日期
        setDate();

        int Updatetime = 0;

        //将double拆分成小数和整数
        double decimalHours = num%1;
        int minutes = (int)(decimalHours * 60);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(nowDate);
        c1.add(Calendar.MINUTE, minutes);
        nowDate = c1.getTime();
        System.out.println(DateUtils.getFormatDateTime(nowDate));


        int hours = num.intValue();
        // 判断开始时间的下一个小时
        //判断日期是否是周六周日
        if(hours != 0){
            for (Integer i = 1; i <= hours; i++) {
                System.out.println(i);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(nowDate);
                calendar.add(Calendar.HOUR,1);
                nowDate = calendar.getTime();
                int hour = DateUtils.getHour(nowDate);
                addHours: switch (hour){
                    case 0:
                        calendar.add(Calendar.HOUR,9);
                        break  addHours;
                    case 1:
                        calendar.add(Calendar.HOUR,8);
                        break  addHours;
                    case 2:
                        calendar.add(Calendar.HOUR,7);
                        break  addHours;
                    case 3:
                        calendar.add(Calendar.HOUR,6);
                        break  addHours;
                    case 4:
                        calendar.add(Calendar.HOUR,5);
                        break  addHours;
                    case 5:
                        calendar.add(Calendar.HOUR,4);
                        break  addHours;
                    case 6:
                        calendar.add(Calendar.HOUR,3);
                        break  addHours;
                    case 7:
                        calendar.add(Calendar.HOUR,2);
                        break  addHours;
                    case 8:
                        calendar.add(Calendar.HOUR,1);
                        break  addHours;
                    case 13:
                        calendar.add(Calendar.HOUR,1);
                        break  addHours;
                    case 18:
                        int minute = DateUtils.getMinute(nowDate);
                        if(minute != 0) {
                            calendar.add(Calendar.HOUR, 15);
                        }
                        break  addHours;
                    case 19:
                        calendar.add(Calendar.HOUR,14);
                        break  addHours;
                    case 20:
                        calendar.add(Calendar.HOUR,13);
                        break  addHours;
                    case 21:
                        calendar.add(Calendar.HOUR,12);
                        break  addHours;
                    case 22:
                        calendar.add(Calendar.HOUR,11);
                        break  addHours;
                    case 23:
                        calendar.add(Calendar.HOUR,10);
                        break  addHours;
                }

                if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(calendar.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长加1
                        Updatetime += 1;
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(calendar.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Updatetime += 1;
                    }
                }


                // 计算最后时间
                Calendar c = Calendar.getInstance();
                c.setTime(calendar.getTime());
                c.add(Calendar.DATE, Updatetime);
                // 修改 时间 + num  为周六 的bug
                // 判断 时间是否是周日
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                    // 如果是 判断周日是否要上班
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长加1
                        c.add(Calendar.DATE, 1);
                    }
                }
                Updatetime = 0;
                nowDate=c.getTime();
            }

            return nowDate;
        }else{
            Date time1 = nowDate;
            int hour = DateUtils.getHour(time1);
            switch (hour){
                case 0:
                    c1.add(Calendar.HOUR,9);
                    break;
                case 1:
                    c1.add(Calendar.HOUR,8);
                    break;
                case 2:
                    c1.add(Calendar.HOUR,7);
                    break;
                case 3:
                    c1.add(Calendar.HOUR,6);
                    break;
                case 4:
                    c1.add(Calendar.HOUR,5);
                    break;
                case 5:
                    c1.add(Calendar.HOUR,4);
                    break;
                case 6:
                    c1.add(Calendar.HOUR,3);
                    break;
                case 7:
                    c1.add(Calendar.HOUR,2);
                    break;
                case 8:
                    c1.add(Calendar.HOUR,1);
                    break;
                case 13:
                    c1.add(Calendar.HOUR,1);
                    break;
                case 18:
                    c1.add(Calendar.HOUR,15);
                    break;
                case 19:
                    c1.add(Calendar.HOUR,14);
                    break;
                case 20:
                    c1.add(Calendar.HOUR,13);
                    break;
                case 21:
                    c1.add(Calendar.HOUR,12);
                    break;
                case 22:
                    c1.add(Calendar.HOUR,11);
                    break;
                case 23:
                    c1.add(Calendar.HOUR,10);
                    break;
            }
            if(c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                // 如果是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c1.getTime());
                // 判断这个周末是否在周末要加班的区间内  循环
                if(!weekendList.contains(time)){
                    // 否 总时长加1
                    Updatetime += 1;
                }
            }else{
                // 不是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c1.getTime());
                // 判断这天是否在放假的区间内
                if(holidayList.contains(time)){
                    // 是 总时长减24小时
                    Updatetime += 1;
                }
            }
            // 计算最后时间
            Calendar c = Calendar.getInstance();
            c.setTime(c1.getTime());
            c.add(Calendar.DATE, Updatetime);
            // 修改 时间 + num  为周六 的bug
            // 判断 时间是否是周日
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
                // 如果是 判断周日是否要上班
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c.getTime());
                // 判断这个周末是否在周末要加班的区间内  循环
                if(!weekendList.contains(time)){
                    // 否 总时长加1
                    c.add(Calendar.DATE, 1);
                }
            }
            Date date=c.getTime();
            return date;
        }
    }

    /**
     * 获取nowDate 在 num 个工作日以前的时间
     * */
    public static Date getDatePoorBefore(Integer num, Date nowDate) {
        // 设置日期
        setDate();
        int Updatetime = 0;
        // 判断开始时间的上一天
        for (int i=1;i <= num;i++){
            //判断日期是否是周六周日
            Calendar c = Calendar.getInstance();
            c.setTime(nowDate);
            c.add(Calendar.DATE, -i);
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                    c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                // 如果是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c.getTime());
                // 判断这个周末是否在周末要加班的区间内  循环
                if(!weekendList.contains(time)){
                    // 否 总时长加1
                    Updatetime += 1;
                }
            }else{
                // 不是周末
                SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                String time = sdfDay.format(c.getTime());
                // 判断这天是否在放假的区间内
                if(holidayList.contains(time)){
                    // 是 总时长减24小时
                    Updatetime += 1;
                }
            }
        }
        // 计算最后时间
        Calendar c = Calendar.getInstance();
        c.setTime(nowDate);
        c.add(Calendar.DATE, -Updatetime-num);
        // 修改 时间 + num  为周六 的bug
        // 判断 时间是否是周日
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            // 如果是 判断周日是否要上班
            SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdfDay.format(c.getTime());
            // 判断这个周末是否在周末要加班的区间内  循环
            if(!weekendList.contains(time)){
                // 否 总时长加1
                c.add(Calendar.DATE, -1);
            }
        }
        Date date=c.getTime();
        return date;
    }

    // 获取两个时间中的工作时长
    public static Long getDatePoor(Date endDate, Date nowDate) {
        // 判断差几天
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 设置日期
        setDate();
        int Updatetime = 0;
        // 差几天就循环几次，判断开始时间的下一天

        for (int i=1;i <= day;i++){
            if(i != day){
                // 获取加一天
                //判断日期是否是周六周日
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                c.add(Calendar.DATE, i);
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长减24小时
                        Updatetime += 24;
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Updatetime += 24;
                    }
                }
            }else {
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                c.add(Calendar.DATE, i);
                // 获取加一天
                //判断日期是否是周六周日
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长减24小时
                        Calendar hourc = Calendar.getInstance();
                        hourc.setTime(nowDate);
                        int sec = c.get(Calendar.HOUR_OF_DAY);
                        Updatetime += (24 - sec);
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Calendar hourc = Calendar.getInstance();
                        hourc.setTime(nowDate);
                        int sec = c.get(Calendar.HOUR_OF_DAY);
                        Updatetime += (24 - sec);
                    }
                }
            }


        }
        // 计算差多少小时
        long hour = diff / nh;
        return (hour - Updatetime);
    }


    // 获取两个时间中的工作时长（分钟）
    public static Long getDatePoorMinutes(Date endDate, Date nowDate) {
        // 判断差几天
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000*60;

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long minutes = diff / nd;
        // 设置日期
        setDate();
        int Updatetime = 0;
        // 差几天就循环几次，判断开始时间的下一天

        for (int i=1;i <= minutes;i++){
            if(i != minutes){
                // 获取加一天
                //判断日期是否是周六周日
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                c.add(Calendar.DATE, i);
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长减24小时
                        Updatetime += 24;
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Updatetime += 24;
                    }
                }
            }else {
                Calendar c = Calendar.getInstance();
                c.setTime(nowDate);
                c.add(Calendar.DATE, i);
                // 获取加一天
                //判断日期是否是周六周日
                if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                        c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
                    // 如果是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这个周末是否在周末要加班的区间内  循环
                    if(!weekendList.contains(time)){
                        // 否 总时长减24小时
                        Calendar hourc = Calendar.getInstance();
                        hourc.setTime(nowDate);
                        int sec = c.get(Calendar.HOUR_OF_DAY);
                        Updatetime += (24 - sec);
                    }
                }else{
                    // 不是周末
                    SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
                    String time = sdfDay.format(c.getTime());
                    // 判断这天是否在放假的区间内
                    if(holidayList.contains(time)){
                        // 是 总时长减24小时
                        Calendar hourc = Calendar.getInstance();
                        hourc.setTime(nowDate);
                        int sec = c.get(Calendar.HOUR_OF_DAY);
                        Updatetime += (24 - sec);
                    }
                }
            }


        }
        // 计算差多少小时
        long minute = diff / nm;
        return (minute - Updatetime);
    }

}
