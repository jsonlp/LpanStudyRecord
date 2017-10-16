package com.lpan.study.utils;

import android.content.Context;
import android.widget.Toast;

import com.lpan.study.context.AppContext;

public class Toaster {

    private static Toast sToast;

    public static void toastShort(int resId) {
        toast(AppContext.getContext().getString(resId), Toast.LENGTH_SHORT);
    }

    public static void toastShort(String msg) {
        toast(msg, Toast.LENGTH_SHORT);
    }

    public static void toastLong(int resId) {
        toast(AppContext.getContext().getString(resId), Toast.LENGTH_LONG);
    }

    public static void toastLong(String msg) {
        toast(msg, Toast.LENGTH_LONG);
    }

    public static void toast(int resId, int duration) {
        toast(AppContext.getContext().getString(resId), duration);
    }

    public static void toast(CharSequence charSequence, int duration) {
        if (sToast == null) {
            sToast = Toast.makeText(AppContext.getContext(), charSequence, duration);
        } else {
            sToast.setText(charSequence);
        }
        sToast.show();
    }
}
