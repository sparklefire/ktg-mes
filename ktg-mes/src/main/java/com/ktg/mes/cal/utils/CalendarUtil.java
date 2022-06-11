package com.ktg.mes.cal.utils;

import com.ktg.mes.cal.domain.CalCalendar;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CalendarUtil {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Long getDateDiff(Date start,Date end){
        LocalDate sdate = LocalDate.parse(sdf.format(start),formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    public static Long getDateDiff(String  start,Date end){
        LocalDate sdate = LocalDate.parse(start,formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    public static Long getDateDiff(Date  start,String end){
        LocalDate sdate = LocalDate.parse(sdf.format(start),formatter);
        LocalDate edate = LocalDate.parse(sdf.format(end),formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    /**
     * 计算两个日期之间的天数差值
     * @param start
     * @param end
     * @return
     */
    public static Long getDateDiff(String  start,String end){
        LocalDate sdate = LocalDate.parse(start,formatter);
        LocalDate edate = LocalDate.parse(end,formatter);
        return edate.until(sdate,ChronoUnit.DAYS);
    }

    /**
     * 获取指定月份的所有日期
     * @param d
     * @return
     */
    public static List<CalCalendar> getDays(Date d){
        List<CalCalendar> lst=new ArrayList();
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        while (!date.after(monthEnd)) {
            CalCalendar cal = new CalCalendar();
            cal.setTheDay(sdf.format(date));
            lst.add(cal);
            date = getNext(date);
        }
        return lst;
    }
}
