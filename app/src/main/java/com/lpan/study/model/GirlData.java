package com.lpan.study.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lpan on 2018/5/28.
 */

public class GirlData {
    public boolean error;
    public @SerializedName("results") List<GirlInfo> beauties;

    @Override
    public String toString() {
        return "GirlData{" +
                "error=" + error +
                ", beauties=" + beauties +
                '}';
    }
}
