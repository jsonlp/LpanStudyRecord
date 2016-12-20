package com.lpan.study.context;

import android.content.Context;

/**
 * Created by lpan on 2016/12/20.
 */

public class AppContext {

    public static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppContext.context = context;
    }
}
