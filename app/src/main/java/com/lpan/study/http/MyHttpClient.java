package com.lpan.study.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
        mOkHttpClient = mOkHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new LoggingInterceptor())
                .build();
    }

    public Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return processRequest(request);
    }

    public Response post(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return processRequest(request);
    }

    private Response processRequest(final Request request) throws IOException {
        if (request == null) {
            return null;
        }
        return mOkHttpClient.newCall(request).execute();
    }

    public OkHttpClient getOkHttpClient(){
        return mOkHttpClient;
    }

}
