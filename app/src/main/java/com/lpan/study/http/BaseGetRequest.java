package com.lpan.study.http;

import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by lpan on 2017/11/17.
 */

public abstract class BaseGetRequest<T> extends BaseRequest<T> {

    public BaseGetRequest(BaseHttpCallback<T> callback) {
        super(callback);
    }

    @Override
    public Response getResponse() {
        try {
            return MyHttpClient.getInstance().get(getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
