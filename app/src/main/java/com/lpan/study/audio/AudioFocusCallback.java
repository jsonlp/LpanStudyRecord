package com.lpan.study.audio;

/**
 * Created by lpan on 2016/12/16.
 */

public interface AudioFocusCallback {

    void onGainFocus();

    void onLostFocus();

    void onLostFocusTransient();

    void onLostFocusTransientCanDuck();

}
