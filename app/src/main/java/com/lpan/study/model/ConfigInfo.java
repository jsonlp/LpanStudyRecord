package com.lpan.study.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by lpan on 2017/11/16.
 */

@JsonObject
public class ConfigInfo {

    @JsonField
    NewsfeedThresholdInfo newsfeedBarThreshold;

    @JsonField
    int defaultTab;

    @JsonField
    boolean groupSendVideo;

    @JsonField
    String pm;

    @Override
    public String toString() {
        return "ConfigInfo{" +
                "newsfeedBarThreshold=" + newsfeedBarThreshold +
                ", defaultTab=" + defaultTab +
                ", groupSendVideo=" + groupSendVideo +
                ", pm='" + pm + '\'' +
                '}';
    }
}
