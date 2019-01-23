package com.lpan.study.utils;

import android.text.TextUtils;

import com.lpan.study.model.TimeInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Created by lpan on 2019/1/22.
 */
public class WorkTimer {

    public static final String REGEX = "(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)\\s+([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

    public static TimeInfo handleData(String content, boolean filterDay) {
        Map<String, List<String>> map = splitData(content);
        TimeInfo hours = hours(map, filterDay);
        String[] dateFrom = getDateFrom(content);
        hours.setFromDate(dateFrom[0]);
        hours.setEndDate(dateFrom[1]);
        return hours;
    }

    public static String readText(String path) {
        String content = "";

        if (TextUtils.isEmpty(path)) {
            return content;
        }
        File file = new File(path);
        if (!file.exists()) {
            return content;
        }
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            String line = "";
            BufferedReader bufferedReader = new BufferedReader(reader);
            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }
//            content += content + "end";
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    private static List<String> toList(String content) {
        List<String> newList = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX);

        String[] split = content.split("\n");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            String temp = "";
            String[] split1 = s.split("\t");

            if (split1.length == 0) {
                temp = s;
            } else {
                temp = split1[0];
            }
            if (pattern.matcher(temp).matches()) {
                newList.add(temp);
            }
        }
        newList.add("end");
        return newList;
    }

    private static List<Long> toLongList(String content) {
        List<String> strings = toList(content);
        List<Long> longs = new ArrayList<>();
        for (String time : strings) {
            if (time.startsWith("201")) {
                longs.add(dateToStamp(time));
            }
        }
        return longs;
    }

    private static Map<String, List<String>> splitData(String content) {
        List<String> strings = toList(content);
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        String start = "";
        for (String time : strings) {
            String[] starts = time.split(" ");
            if (!TextUtils.equals(starts[0], start)) {
                if (!TextUtils.isEmpty(start)) {
                    List<String> temp = new ArrayList<>();
                    temp.addAll(list);
                    map.put(start, temp);
                    list.clear();
                }
                start = starts[0];
            }
            boolean b = time.startsWith(start);
            if (b) {
                list.add(time);
            }
        }
        return map;
    }

    private static TimeInfo hours(Map<String, List<String>> map, boolean filterDay) {
        TimeInfo timeInfo = new TimeInfo();
        List<String> rest = new ArrayList<>();

        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entries.iterator();
        float hours = 0;
        long dayFirst = 100000000000l;
        long dayLast = 0;
        String mostEarly = "";
        String mostLast = "";

        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            List<String> value = next.getValue();
            String first = value.get(0);
            String last = value.get(value.size() - 1);
            long lastTime = dateToStamp(last);
            long firstTime = dateToStamp(first);
            long maxTime = lastTime - firstTime;
            if (filterDay && maxTime <= 9 * 60 * 60 * 1000) {
                rest.add(next.getKey());
                iterator.remove();
            }
            if (after0(first) < dayFirst) {
                dayFirst = after0(first);
                mostEarly = first;
            }
            if (after0(last) > dayLast) {
                dayLast = after0(last);
                mostLast = last;
            }
            hours += maxTime / (60 * 60 * 1000f);
        }
        timeInfo.setDays(map.size());
        timeInfo.setHours(hours / map.size());
        timeInfo.setMostEarly(mostEarly);
        timeInfo.setMostLast(mostLast);
        timeInfo.setRest(rest);

        return timeInfo;
    }

    private static long dateToStamp(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
        return ts;
    }

    private static String stampToDate(long stamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(stamp);
        res = simpleDateFormat.format(date);
        return res;
    }

    private static long after0(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = dateToStamp(time);
        long day0 = now / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return now - day0;
    }

    private static String[] getDateFrom(String text) {
        String[] froms = new String[2];
        List<Long> longs = toLongList(text);
        Collections.sort(longs);
        String from = stampToDate(longs.get(0)).split(" ")[0];
        String to = stampToDate(longs.get(longs.size() - 1)).split(" ")[0];
        froms[0] = from;
        froms[1] = to;
        return froms;
    }

    public static double formatDouble1(double d) {
        return (double) Math.round(d * 100) / 100;
    }
}
