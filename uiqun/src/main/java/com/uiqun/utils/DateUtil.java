package com.uiqun.utils;

import org.springframework.stereotype.Component;
import sun.util.calendar.CalendarDate;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class DateUtil implements Serializable {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 获取当前年份
     * @return
     */
    public Integer getCurrentYear(){
        Date date = new Date();
        String format = sdf.format(date);
        return Integer.parseInt(format.split("-")[0]);
    }

    /**
     * 获取当前月份
     * @return
     */
    public Integer getCurrentMonth(){
        Date date = new Date();
        String format = sdf.format(date);
        return Integer.parseInt(format.split("-")[1]);
    }

    /**
     * 获取当前为几号
     * @return
     */
    public Integer getCurrentDay(){
        Date date = new Date();
        String format = sdf.format(date);
        return Integer.parseInt(format.split("-")[2]);
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public Integer getLastDayByMonth(){
        Integer currentMonth = getCurrentMonth();
        boolean isFebruary = false;
        boolean isRunYear = false;
        switch (currentMonth){
            case 2:
                isFebruary = true;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
        }
//        if(isFebruary){
//            Integer currentYear = getCurrentYear();
//            //判断是否为世纪年是否为闰年
//            if(  (currentMonth==(currentMonth/100)*100)   &&  (currentYear%400==0)){
//                isRunYear =true;
//            //判断普通年是否非闰年
//            }else if( (currentYear%4==0) && (currentYear%100!=0)  ){
//                isRunYear = true ;
//            }
//        }
        try {
            if(isFebruary && CalendarDate.class.newInstance().isLeapYear()){
                isRunYear= true;
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return isRunYear?29:28;
    }
    /**
     * 获取当前星期
     * @return
     */
    public Integer getCurrentWeek(){
        Calendar cd = Calendar.getInstance(TimeZone.getDefault());
        return cd.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取当前为本月第几个星期
     * @return
     */
    public Integer getCurrentWeekByMonth(){
        Calendar cd = Calendar.getInstance(TimeZone.getDefault());
        return cd.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获取漂亮的date格式
     * @param o
     * @return
     */
    public String getPrettyDate(Object o){
        if(o instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date)o;
            return sdf.format(date);
        }
        return o!=null?o.toString():null;
    }
}
