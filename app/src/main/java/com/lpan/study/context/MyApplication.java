package com.lpan.study.context;

import android.app.Application;

import com.lpan.study.utils.CrashUtils;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;

import java.io.IOException;

/**
 * Created by lpan on 2016/12/20.
 */

public class MyApplication extends Application implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.setContext(getApplicationContext());
        int width = ViewUtils.getScreenWidth(getApplicationContext());
        int height = ViewUtils.getScreenHeight(getApplicationContext());
        int statuHeight = ViewUtils.getStatusHeight(getApplicationContext());
        if (Log.DEBUG) {
            Log.d("MyApplication", "onCreate--------width=" + width + " height=" + height + " statuHeight=" + statuHeight + "  one dp=" + ViewUtils.ONE_DP);
        }

        this.mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppContext.setContext(null);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (Log.DEBUG) {
            try {
                android.os.Debug.dumpHprofData("/sdcard/panda_dump.hprof");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (Log.DEBUG) {
            CrashUtils.crashLog(ex);
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }

        mUncaughtExceptionHandler.uncaughtException(thread, ex);
    }
}
