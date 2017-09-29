package com.lpan.study.view.pulltorefresh.listener;

import android.view.MotionEvent;

public interface InternalStretchListener {

    public boolean stretchModeOnInterceptTouchEvent(MotionEvent event);

    public boolean stretchModeOnTouchEvent(MotionEvent event);

    public boolean isStretchModeEnable();
}
