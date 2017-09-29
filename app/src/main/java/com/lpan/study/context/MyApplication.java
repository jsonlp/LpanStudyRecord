package com.lpan.study.context;

import android.app.Application;

import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;

/**
 * Created by lpan on 2016/12/20.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(getApplicationContext());
        int width = ViewUtils.getScreenWidth(getApplicationContext());
        int height = ViewUtils.getScreenHeight(getApplicationContext());
        int statuHeight = ViewUtils.getStatusHeight(getApplicationContext());
        if (Log.DEBUG) {
            Log.d("MyApplication", "onCreate--------width=" + width + " height=" + height + " statuHeight=" + statuHeight);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppContext.setContext(null);
    }
}
