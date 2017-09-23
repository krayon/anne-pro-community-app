package com.obins.anne.utils;

import android.content.Context;

public class DensityUtil {
    public static float dip2px(Context context, float dpValue) {
        return (dpValue * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static float px2dip(Context context, float pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public static int getScreenWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeigthPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
