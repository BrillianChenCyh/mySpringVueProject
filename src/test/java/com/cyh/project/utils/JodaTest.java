package com.cyh.project.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName JodaTest
 * @Description: Joda日期使用
 * @Author chenyinghui
 * @Date 2020/3/5
 * @Version V1.0
 **/
public class JodaTest {
    public static void main(String[] args) {
        //DateTime是线程安全的
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        DateTime dateTime2 = new DateTime(2016, 10, 01, 8, 00, 00);
        System.out.println(dateTime2);
        /** 日期格式化 */
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeStr2 = dtf.print(dateTime);
        System.out.println(dateTimeStr2);
        /** JDK中的老套路，麻烦.. */
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -40);
        Date beforeDay = calendar.getTime();
        System.out.println(beforeDay);
        /** 计算日期 */
        DateTime todayDateTime = new DateTime();
//        DateTime beforeDateTime = todayDateTime.minusDays(40);
        DateTime beforeDateTime = todayDateTime.minusDays(-5);
        System.out.println(beforeDateTime);

        int diffDays2 = Days.daysBetween(beforeDateTime, todayDateTime).getDays();
        System.out.println(diffDays2);

        //Date to DateTime
        Date date = new Date();
        DateTime dateTime1 = new DateTime(date);

        //DateTime to Date
        Date date2 = dateTime.toDate();

        //Calendar to DateTime
        Calendar calendar1 = Calendar.getInstance();
        DateTime dateTime3 = new DateTime(calendar1);

        //DateTime to Calendar
        Calendar calendar2 = dateTime2.toCalendar(Locale.CHINA);

        DateTime endDate=new DateTime().withMillisOfDay(0);
        System.out.println(endDate.toDate());
        DateTime startDate = endDate.minusDays(1);
        System.out.println(startDate.toDate());
    }
}
