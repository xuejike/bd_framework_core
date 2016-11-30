package com.bidanet.bdcms.core.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/11.
 */
public class DateTool {

    public static final Calendar calendar = Calendar.getInstance();

    private void initCalendar(){
        Date date = new Date();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public DateTool(){
        initCalendar();
    }



    public Date getBeginTime(){
        calendar.add(Calendar.DATE, 0);
        return calendar.getTime();
    }

    public Date getEndTime(){
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 当月的第一天
     * @param time
     * @return
     */
    public static long fromMonth(Date time){
        Calendar nowDate = new java.util.GregorianCalendar();
        nowDate.setTime(time);
        nowDate.set(Calendar.MINUTE,0);
        nowDate.set(Calendar.SECOND,0);
        nowDate.set(Calendar.MILLISECOND,0);
        nowDate.set(Calendar.DAY_OF_MONTH,1);

        Date date = nowDate.getTime();
        long begin = date.getTime();


        return  begin;

    }

    /**
     * 当月的最后一天
     * @param time
     * @return
     */
    public static long toMonth(Date time){
        Calendar nowDate = new java.util.GregorianCalendar();
        nowDate.setTime(time);
        nowDate.set(Calendar.MINUTE,0);
        nowDate.set(Calendar.SECOND,0);
        nowDate.set(Calendar.MILLISECOND,0);
        nowDate.set(Calendar.DAY_OF_MONTH,1);
        nowDate.add(Calendar.MONTH,1);
        Date date = nowDate.getTime();
        long end = date.getTime();
        return  end;
    }
    static SimpleDateFormat ymd= new SimpleDateFormat("yyyy-MM-dd");

    //上个月第一天
    public static Long firstDayOfLastMonth(){
        String firstDay;
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        firstDay = ymd.format(cal_1.getTime());
        Long time=parseDateStr(firstDay);//日期格式转换为时间戳
        return  time;

    }


    //上个月最后一天
    public static Long lastDayOfLastMonth(){
//        String lastDay;
//        //获取前月的最后一天
//        Calendar cale = Calendar.getInstance();
//        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
//        lastDay = ymd.format(cale.getTime());
//        Long time=parseDateStr(lastDay);//日期格式转换为时间戳

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = ymd.format(c.getTime());
        Long ime=parseDateStr(first);
        return ime;

    }


    public static String timeToStrYmd(Long time){
        return ymd.format(new Date(time));
    }




    public static Long parseDateStr(String date){
        try {
            Date parse = ymd.parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String args[]) {
        Long time= DateTool.firstDayOfLastMonth();
        System.out.println(time);
        Long last= DateTool.lastDayOfLastMonth();
        System.out.print(last);
    }
}
