package com.lpan.study.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by lpan on 2017/11/17.
 */
@JsonObject
public class SuperInnteresInfo {

    @JsonField
    String desc;

    @JsonField
    InterestInfo interest;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public InterestInfo getInterest() {
        return interest;
    }

    public void setInterest(InterestInfo interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "SuperInnteresInfo{" +
                "desc='" + desc + '\'' +
                ", interest=" + interest +
                '}';
    }
}
