package com.lpan.study.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lpan on 2017/2/8.
 */

public class Utils {

    public static final long ONE_KB = 1024;

    public static final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");

    /**
     * 转换毫秒数成“分、秒”，如“01:53”。若超过60分钟则显示“时、分、秒”，如“01:01:30
     *
     * @param
     */
    public static String converLongTimeToStr(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;

        long hour = (time) / hh;
        long minute = (time - hour * hh) / mi;
        long second = (time - hour * hh - minute * mi) / ss;

        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        if (hour > 0) {
            return strHour + ":" + strMinute + ":" + strSecond;
        } else {
            return strMinute + ":" + strSecond;
        }
    }

    public static String formatTime(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String str = dateFormat.format(time);
        return str;
    }

    public static double formatSizeToKB(long size) {
        double d = (double) size / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToMB(long size) {
        double d = (double) size / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }

    public static double formatSizeToGB(long size) {
        double d = (double) size / ONE_KB / ONE_KB / ONE_KB;
        return (double) Math.round(d * 100) / 100;
    }
}
