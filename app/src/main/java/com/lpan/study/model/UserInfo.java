package com.lpan.study.model;

import android.support.annotation.NonNull;

import com.lpan.study.utils.LetterUtil;

import java.util.Comparator;

/**
 * Created by lpan on 2017/4/6.
 */

public class UserInfo implements Comparable<UserInfo> {

    String name;

    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserInfo() {
    }

    public UserInfo(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull UserInfo o) {
        String name1 = getName();
        String name2 = o.getName();
        String pinYin1 = LetterUtil.getPinYin(name1);
        String pinYin2 = LetterUtil.getPinYin(name2);

        String firstLetter1 = pinYin1.substring(0, 1).toUpperCase();
        if (!firstLetter1.matches("[A-Z]")) {
            firstLetter1 = "↑";
        }

        String firstLetter2 = pinYin2.substring(0, 1).toUpperCase();
        if (!firstLetter2.matches("[A-Z]")) {
            firstLetter2 = "↑";
        }
        if (firstLetter1.equals("↑") && !firstLetter2.equals("↑")) {
            return 1;
        } else if (!firstLetter1.equals("↑") && firstLetter2.equals("↑")) {
            return -1;
        } else {
            return pinYin1.compareToIgnoreCase(pinYin2);
        }
    }
}
