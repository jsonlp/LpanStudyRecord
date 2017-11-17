package com.lpan.study.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by lpan on 2017/11/16.
 */
@JsonObject
public class NewsfeedThresholdInfo {

    @JsonField
    int numberOfPeople;

    @JsonField
    int numberOfPost;

    @JsonField
    int numberOfShowShouldaKnow;


    @Override
    public String toString() {
        return "NewsfeedThresholdInfo{" +
                "numberOfPeople=" + numberOfPeople +
                ", numberOfPost=" + numberOfPost +
                ", numberOfShowShouldaKnow=" + numberOfShowShouldaKnow +
                '}';
    }
}
