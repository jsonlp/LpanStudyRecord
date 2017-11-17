package com.lpan.study.http;


import com.lpan.study.model.Meta;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.Toaster;

/**
 * Created by lpan on 2017/11/16.
 */

public abstract class BaseHttpCallback<T> {

    protected void onRequestStart() {

    }

    protected void onRequestFinished() {

    }

    public abstract void onRequestSuccess(T t);

    protected void onRequestFail(Meta meta) {
        Toaster.toastShort(meta == null ? "meta is null" : meta.getDesc());
        Log.e("BaseHttpCallback", "onRequestFail--------" + (meta == null ? "meta is null" : meta.getDesc()) + "  " + Thread.currentThread().getName());
    }

}
