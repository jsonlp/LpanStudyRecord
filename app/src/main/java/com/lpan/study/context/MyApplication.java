package com.lpan.study.context;

import android.app.Application;

/**
 * Created by lpan on 2016/12/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppContext.setContext(null);
    }
}
