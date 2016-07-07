package com.jack.mc.cyg.cygproject.cyg.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jack on 16/6/29.
 */
public class CygTimeUtil {

    /**
     * 获取现在的时间，返回想要的时间格式（Date类型）
     *
     * @param format 想要的时间格式
     * @return 返回想要的时间格式
     */
    public static Date getNowDate(String format) {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateString = sdf.format(currentTime);
        //解析字符串的文本，用来声称Date
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = sdf.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在的时间，返回想要的时间格式（String类型）
     *
     * @param format
     * @return
     */
    public static String getNowDateToString(String format) {
        Date currentTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(currentTime);
    }

    /**
     * 二个'小时'时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHourTimeSpace(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDayTimeSpace(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            java.util.Date date = myFormatter.parse(sj1);
            java.util.Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 将时间戳转为代表"距现在多久之前"的字符串
     *
     * @param timeStr 时间戳
     * @return
     */
    public static String getStandardDate(String timeStr) {
        StringBuffer sb = new StringBuffer();
        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t * 1000);
        long mill = (long) Math.ceil(time / 1000);//秒前
        long minute = (long) Math.ceil(time / 60 / 1000.0f);// 分钟前
        long hour = (long) Math.ceil(time / 60 / 60 / 1000.0f);// 小时
        long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000.0f);// 天前
        if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }
}
