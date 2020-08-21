package com.easybii.nacos.nacosprivider.nacos.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * 节假日设置
 */
public class HolidayDate {
    //节假日列表
    private static List<String> holidayList = new ArrayList<String>();
    //周末为工作日
    private static List<String> weekendList = new ArrayList<String>();

    // 设置 节假日  工作日
    static{
        // 周一到周五放假的天
        holidayList.add("2019-09-13");
        holidayList.add("2019-10-01");
        holidayList.add("2019-10-02");
        holidayList.add("2019-10-03");
        holidayList.add("2019-10-04");
        holidayList.add("2019-10-07");
        // 周末上班
        weekendList.add("2019-09-29");
        weekendList.add("2019-10-12");

        // 2020年  开始----------------------------
        // 周一到周五放假的天
        holidayList.add("2020-01-01");
        holidayList.add("2020-01-23");
        holidayList.add("2020-01-24");
        holidayList.add("2020-01-27");
        holidayList.add("2020-01-28");
        holidayList.add("2020-01-29");
        holidayList.add("2020-01-30");
        holidayList.add("2020-01-31");
        holidayList.add("2020-04-06");
        holidayList.add("2020-05-01");
        holidayList.add("2020-05-04");
        holidayList.add("2020-05-05");
        holidayList.add("2020-06-25");
        holidayList.add("2020-06-26");
        holidayList.add("2020-10-01");
        holidayList.add("2020-10-02");
        holidayList.add("2020-10-05");
        holidayList.add("2020-10-06");
        holidayList.add("2020-10-07");
        holidayList.add("2020-10-08");
        // 周末上班
        weekendList.add("2020-01-19");
        weekendList.add("2020-04-26");
        weekendList.add("2020-05-09");
        weekendList.add("2020-06-28");
        weekendList.add("2020-09-27");
        weekendList.add("2020-10-10");
        // 2020年  结束----------------------------
    }

    public static List<String> getHolidayList(){
        return holidayList;
    }

    public static List<String> getWeekendList(){
        return weekendList;
    }
}
