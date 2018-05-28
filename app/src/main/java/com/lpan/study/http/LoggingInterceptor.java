package com.lpan.study.http;

import com.lpan.study.utils.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lpan on 2017/11/16.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        response = chain.proceed(request);
        if (Log.DEBUG) {
            Log.d("LoggingInterceptor", "intercept--------" + request.url().toString());
        }
        return response;
    }
}
