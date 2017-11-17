package com.lpan.study.http;

import com.bluelinelabs.logansquare.ParameterizedType;
import com.lpan.study.jsonparser.LoganResponse;
import com.lpan.study.jsonparser.MyJsonParser;
import com.lpan.study.model.Meta;
import com.lpan.study.model.MetaCode;
import com.lpan.study.utils.Log;
import com.squareup.okhttp.Response;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lpan on 2017/11/17.
 */

public abstract class BaseRequest<T> {

    BaseHttpCallback<T> mCallback;

    public BaseRequest(BaseHttpCallback<T> callback) {
        mCallback = callback;
    }

    public abstract String getUrl();

    public abstract ParameterizedType<LoganResponse<T>> getType();

    public abstract Response getResponse();


    public void start() {
        if (mCallback != null) {
            mCallback.onRequestStart();
        }
        if (Log.DEBUG) {
            Log.d("BaseRequest", "start--------url=" + getUrl());
        }

        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Response response = getResponse();
                e.onNext(response);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Exception {
                        if (mCallback != null) {
                            mCallback.onRequestFinished();
                        }
//
                        if (response != null && response.isSuccessful()) {
                            onSuccess(response);
                        } else {
                            onError(null);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mCallback != null) {
                            mCallback.onRequestFinished();
                        }

                        onError(null);
                        if (Log.DEBUG) {
                            Log.d("BaseRequest", "accept--------" + throwable);
                        }
                    }
                });
    }

    public void onError(Meta meta) {
        if (mCallback != null) {
            mCallback.onRequestFail(meta);
        } else {
            Log.e("BaseRequest", "onError--------callback is null");
        }
    }

    public void onSuccess(Response response) throws IOException {
        if (mCallback == null) {
            Log.e("BaseRequest", "onSuccess--------callback is null");
            return;
        }
        LoganResponse<T> tLoganResponse = MyJsonParser.getInstance().readValue(response.body().string(), getType());
        if (tLoganResponse != null) {
            Meta meta = tLoganResponse.getMeta();
            T data = tLoganResponse.getData();
            if (meta != null && meta.getCode() == MetaCode.METACODE_SUCCESS && data != null) {
                mCallback.onRequestSuccess(data);
            } else {
                mCallback.onRequestFail(meta);
            }
        } else {
            mCallback.onRequestFail(null);
        }
    }
}
