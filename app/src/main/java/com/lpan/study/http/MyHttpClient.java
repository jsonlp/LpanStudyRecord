package com.lpan.study.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lpan on 2017/11/16.
 */

public class MyHttpClient {

    private static final int DEFAULT_CONNECTION_TIMEOUT = 20;

    private static final int DEFAULT_READ_TIMEOUT = 20;

    private static final int DEFAULT_WRITE_TIMEOUT = 20;

    private static MyHttpClient sMyHttpClient;

    private OkHttpClient mOkHttpClient;

    private MyHttpClient() {
        init();
    }

    public static MyHttpClient getInstance() {
        if (sMyHttpClient == null) {
            sMyHttpClient = new MyHttpClient();
        }
        return sMyHttpClient;
    }

    public void init() {
        mOkHttpClient = new OkHttpClient();
        mOkHttpClient.setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient.setRetryOnConnectionFailure(true);
//        mOkHttpClient.interceptors().add(new LoggingInterceptor());
        mOkHttpClient.setRetryOnConnectionFailure(true);
    }

    public Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return processRequest(request);
    }

    public Response post(String url,RequestBody requestBody) throws IOException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return processRequest(request);
    }

    private Response processRequest(final Request request) throws IOException {
        if (request == null) {
            return null;
        }
        return mOkHttpClient.newCall(request).execute();
    }

}
