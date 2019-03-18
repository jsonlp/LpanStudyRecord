package com.lpan.study.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

import com.lpan.study.context.AppContext;

/**
 * Created by lpan on 2016/12/20.
 */

public class ViewUtils {

    public static final float ONE_DP = dp2pxppp(AppContext.getContext(), 1);


    public static int dp2px(float dpValue) {
        return dp2px(AppContext.context,dpValue);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static float dp2pxppp(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }

    public static float spToPx(Context context, int spValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, metrics);
    }


    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }


    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 有一边超过最大值把长边缩小到最大值
     *
     * @param max
     * @param arr
     * @return
     */
    public static int[] scaleToMax(int max, int[] arr) {
        int width = arr[0];
        int height = arr[1];
        if (width > max || height > max) {
            float r = (float) width / height;
            if (r > 1) {
                width = max;
                height = (int) (max / r + 0.5f);
            } else if (r < 1) {
                height = max;
                width = (int) (max * r + 0.5f);
            } else {
                height = max;
                width = max;
            }
            arr[0] = width;
            arr[1] = height;
        }

        return arr;
    }

    /**
     * 有一边不到最小值,把长边拉伸到最小值
     *
     * @param min
     * @param arr
     * @return
     */
    public static int[] scaleToMin(int min, int[] arr) {
        int width = arr[0];
        int height = arr[1];
        if (width < min || height < min) {
            float r = (float) width / height;
            if (r > 1) {
                width = min;
                height = (int) (width / r + 0.5f);
            } else if (r < 1) {
                height = min;
                width = (int) (height * r + 0.5f);
            } else {
                width = min;
                height = min;
            }
            arr[0] = width;
            arr[1] = height;
        }
        return arr;
    }

    /**
     * 获取状态栏的高度
     *
     * @param context
     */
    public static int getStatusHeight(Context context) {
        int statusBarHeight1 = -1;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
            return statusBarHeight1;
        }
        Log.e("lp-test", "状态栏-方法1:" + statusBarHeight1);
        return 0;
    }

    public static int getActionbarHeight() {
        return getDimenPx(AppContext.getContext(), 50);
    }

    public static int getDimenPx(Context context, int ResId) {
        return context.getResources().getDimensionPixelSize(ResId);
    }
}
