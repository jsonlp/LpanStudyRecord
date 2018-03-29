package com.lpan.study.model;

import android.support.annotation.NonNull;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.lpan.study.utils.LetterUtil;

/**
 * Created by lpan on 2017/11/17.
 */

@JsonObject
public class FriendInfo implements Comparable<FriendInfo>{

    public static final int TYPE_GOOD_FRIEND = 1;

    public static final int TYPE_FRIEND = 2;

    @JsonField
    private int relation;

    @JsonField
    private UserInfo userInfo;

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public int compareTo(@NonNull FriendInfo o) {
        if (getUserInfo() == null || o.getUserInfo() == null) {
            return -1;
        }
        String name1 = getUserInfo().getName();
        String name2 = o.getUserInfo().getName();
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
