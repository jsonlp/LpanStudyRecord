package com.lpan.study.http;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by lpan on 2017/11/17.
 */

public abstract class BasePostRequest<T> extends BaseRequest<T> {

    public BasePostRequest(BaseHttpCallback<T> callback) {
        super(callback);
    }

    @Override
    public Response getResponse() {
        try {
            return MyHttpClient.getInstance().post(getUrl(), getRequestBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract RequestBody getRequestBody();
}
