package com.lpan.study.utils;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    public static void toastShort(Context context, int resId) {
        toast(context, context.getString(resId), Toast.LENGTH_SHORT);
    }

    public static void toastShort(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_SHORT);
    }

    public static void toastLong(Context context, int resId) {
        toast(context, context.getString(resId), Toast.LENGTH_LONG);
    }

    public static void toastLong(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_LONG);
    }

    public static void toast(Context context, int resId, int duration) {
        toast(context, context.getString(resId), duration);
    }

    public static void toast(Context context, CharSequence charSequence, int duration) {
        Toast.makeText(context, charSequence, duration).show();
    }

    public static void toast(Context context, String format, Object[] args, int duration) {
        toast(context, String.format(format, args), duration);
    }
}
