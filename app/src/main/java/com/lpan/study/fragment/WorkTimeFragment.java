package com.lpan.study.fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.TimeInfo;
import com.lpan.study.utils.PermissionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import butterknife.BindView;


/**
 * Created by lpan on 2018/9/17.
 */
public class WorkTimeFragment extends ButterKnifeFragment {

    @BindView(R.id.text)
    TextView mTextView;

    private String mTextPath;

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mTextPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "time.txt";

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_work_time;
    }

    @Override
    protected void initData() {
        super.initData();
        if (PermissionUtils.hasPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            handleData();
        } else {
            PermissionUtils.requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, this, PermissionUtils.PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toastShort("allow");
                handleData();
            } else {
                toastShort("refuse");
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void handleData(){
        String text = readText(mTextPath);
        Map<String, List<String>> map = splitData(text);
        TimeInfo timeInfo = hours(map);
        mTextView.setText(timeInfo.toString());
    }

    private String readText(String path) {
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
            content += content + "end";
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    private Map<String, List<String>> splitData(String content) {
        String[] split = content.split("\n");
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();

        String start = "";
        List<String> strings = Arrays.asList(split);
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

    private TimeInfo hours(Map<String, List<String>> map) {
        TimeInfo timeInfo = new TimeInfo();
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
            long firstTime = dateToStamp(first);
            if (after0(first) < dayFirst) {
                dayFirst = after0(first);
                mostEarly = first;
            }
            if (after0(last) > dayLast) {
                dayLast = after0(last);
                mostLast = last;
            }
            long lastTime = dateToStamp(last);
            long maxTime = lastTime - firstTime;
            hours += maxTime / (60 * 60 * 1000f);
        }
        timeInfo.setDays(map.size());
        timeInfo.setHours(hours / map.size());
        timeInfo.setMostEarly(mostEarly);
        timeInfo.setMostLast(mostLast);

        return timeInfo;
    }

    private long dateToStamp(String s) {
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

    private long after0(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = dateToStamp(time);
        long day0 = now / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return now - day0;
    }
}
