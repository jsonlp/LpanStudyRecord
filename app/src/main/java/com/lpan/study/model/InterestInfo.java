package com.lpan.study.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by lpan on 2017/11/17.
 */
@JsonObject
public class InterestInfo {

    @JsonField
    String parentName;

    @JsonField
    String name;

    @JsonField
    String id;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

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

    @Override
    public String toString() {
        return "InterestInfo{" +
                "parentName='" + parentName + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
