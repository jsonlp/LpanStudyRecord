package com.lpan.study.utils;


import com.test.lpanstudyrecord.BuildConfig;

public class Log {

    public static boolean SEND_LOG = false;

    private static int count = 0;

    //    默認值爲BuildConfig.DEBUG
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static void setSendLogEnabel(boolean open) {
        SEND_LOG = open;
        if (open) {
            DEBUG = open;
            count = 0;
        } else {
            DEBUG = BuildConfig.DEBUG;
        }
    }

    public static void i(String tag, String msg) {
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.i(tag, "--------" + msg);
        }
    }

    public static void checkSendLog(String tag, String msg) {
        if (SEND_LOG) {
            count++;
            if (count > 100) {
                setSendLogEnabel(false);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (Log.DEBUG) {
            android.util.Log.v(tag, "--------" + msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG) {
            android.util.Log.d(tag, "--------" + msg);
        }
    }

    public static void d(String tag, String format, Object... args) {
        if (Log.DEBUG) {
            android.util.Log.d(tag, logFormat(format, args));
        }
    }

    public static void w(String tag, String msg) {
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.w(tag, "--------" + msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.w(tag, "--------" + msg, tr);
        }
    }

    public static void w(String tag, String format, Object... args) {
        String msg = logFormat(format, args);
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.e(tag, "--------" + msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        checkSendLog(tag, msg + "\n" + tr.getMessage());
        if (Log.DEBUG) {
            android.util.Log.e(tag, "--------" + msg, tr);
        }
    }

    public static void e(String tag, String format, Object... args) {
        String msg = logFormat(format, args);
        checkSendLog(tag, msg);

        if (Log.DEBUG) {
            android.util.Log.e(tag, msg);
        }
    }

    private static String prettyArray(String[] array) {
        if (array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int len = array.length - 1;
        for (int i = 0; i < len; i++) {
            sb.append(array[i]);
            sb.append(", ");
        }
        sb.append(array[len]);
        sb.append("]");
        return sb.toString();
    }

    private static String logFormat(String format, Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String[]) {
                args[i] = prettyArray((String[]) args[i]);
            }
        }
        String s = String.format(format, args);
        s = "[" + Thread.currentThread().getId() + "] " + s;
        return s;
    }


}
