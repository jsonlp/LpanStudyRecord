package com.lpan.study.utils;

/**
 * Created by lpan on 2017/9/13.
 */

public class Variables {

    public static boolean hasInDetail;

    private static int exitIndex;

    public static int getExitIndex() {
        return exitIndex;
    }

    public static void setExitIndex(int exitIndex) {
        Variables.exitIndex = exitIndex;
    }

    public static boolean isHasInDetail() {
        return hasInDetail;
    }

    public static void setHasInDetail(boolean hasInDetail) {
        Variables.hasInDetail = hasInDetail;
    }
}
