package com.lpan.study.model;

/**
 * Created by lpan on 2017/4/5.
 */

public class SchoolInfo {
    String name;

    String province;

    public SchoolInfo() {
    }

    public SchoolInfo(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
