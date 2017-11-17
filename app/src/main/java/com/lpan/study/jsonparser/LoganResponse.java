package com.lpan.study.jsonparser;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.lpan.study.model.Meta;

/**
 * Created by lpan on 2017/8/22.
 */

@JsonObject
public class LoganResponse<T> {

    @JsonField
    private Meta meta;

    @JsonField
    private T data;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LoganResponse{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
