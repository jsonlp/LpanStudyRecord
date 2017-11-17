package com.lpan.study.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by lpan on 2017/11/16.
 */
@JsonObject
public class Meta {

    @JsonField
    int cost;

    @JsonField
    String desc;

    @JsonField
    int code;

    @Override
    public String toString() {
        return "Meta{" +
                "cost=" + cost +
                ", desc='" + desc + '\'' +
                ", code=" + code +
                '}';
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
