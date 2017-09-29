package com.lpan.study.view.pulltorefresh.listener;

public interface InternalRefreshListener {

    public void refreshModeOnTouchEvent(float deltaMotion);

    public void refreshModeOnBackEvent(float ratio);

}
