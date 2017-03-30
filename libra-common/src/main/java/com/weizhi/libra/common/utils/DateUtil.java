package com.weizhi.libra.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * date time util
 * 
 * @author
 */
public class DateUtil {
    private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);

    public static void main(String[] args){
//        Sting
    }
    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }


    public static Date addDays2Date(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }




    public static Date getSpecifiedDayOver(Date date, Integer intervalDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, day + intervalDay);
        return cal.getTime();
    }


    public static String getSpecialDayOverWeek(String specialDay, Integer intervalWeek) {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(specialDay);
            Date dateOver = getSpecifiedDayOver(date, 7 * intervalWeek);
            return new SimpleDateFormat("yyyy-ww").format(dateOver);
        } catch (ParseException e) {
			LOG.error(e.getMessage());
            return null;
        }
    }


    public static String getSpecialDayOverMonth(String specialDay, Integer intervalMonth) {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(specialDay);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, intervalMonth);
            String monthOver = new SimpleDateFormat("yyyyMM").format(cal.getTime());
            return monthOver;
        } catch (ParseException e) {
			LOG.error(e.getMessage());
            return null;
        }
    }


    // 将1970年以来的毫秒数转为时间
    public static Date getDateFrom1970s(Long ms1970) {
        return new Date(ms1970);
    }


    public static Long getSecondFrom1970(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTime().getTime() / 1000;
    }

    /**
     * 将字符串解析成日期类型，字符串为YYYYMMdd
     * 
     * @param dateStr
     * @return 解析后的日期
     * @since 0.1
     */
    public static Date getDate(String dateStr) {
        Date date = null;
        try {
            if (dateStr != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                date = simpleDateFormat.parse(dateStr);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return date;
    }


    public static Date getDateTime(String dateTimeStr, String format) {
        try {
            Date date = null;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            date = simpleDateFormat.parse(dateTimeStr);
            return date;
        } catch (Exception e) {
            return null;
        }
    }


    public static String formatDateByFormat(Date date, String format) {
        String strDate = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                strDate = sdf.format(date);
                return strDate;
            } catch (Exception ex) {
                return "";
            }
        }
        return "";
    }


    /**
     * 两个日期的天数差
     * 
     * @param fromDate ，toDate
     * @return 两日期的月分差，例1998-4-21~1998-4-25 相差4天 返回4
     * @since 0.1
     */
    public static int getDiffDays(Date fromDate, Date toDate) {
        return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
    }


    /**
     * 得到某年某周的第一天
     * 
     * @param year
     * @param week
     * @return
     */
    public static Date getMondayOfWeek(int year, int week) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 设置周一
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.getTime();
    }


    public static Date getMondayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK); // 星期日为1， 中国一般是周一是第一天
        if (dayOfWeek == 1) {
            c.add(GregorianCalendar.DATE, -1); // 如果是星期一, 先减一天
        }
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return c.getTime();
    }


    /**
     * date to string
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static String date2String(Date date, String dateFormat) {
        if (date == null) {
            return null;
        } else {
            return (new SimpleDateFormat(dateFormat)).format(date);
        }
    }


    /**
     * string 2 date
     * 
     * @param date
     * @param dateFormat
     * @return
     */
    public static Date string2Date(String date, String dateFormat) {
        if (date == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(dateFormat)).parse(date);
            } catch (ParseException e) {
                LOG.error("string to date error," + e);
                return null;
            }
        }
    }


    /**
     * @author:
     * @description: 根据日期获取星期
     * @param date 日期
     * @param
     * @return int 第几天 1：星期一 2：星期二 ……
     * @throws
     */
    public static int getDayOfWeekFromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }


    public static Date getCurrDate(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String sdate = sdf.format(date);
        Date ret = null;
        try {
            ret = sdf.parse(sdate);
        } catch (ParseException e) {
            LOG.error(e.getMessage());
        }
        return ret;
    }


}
