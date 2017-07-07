package com.lpan.study.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

public class PausableAnimationSet extends AnimationSet {

    private long mElapsedAtPause = 0;

    private boolean mPaused = false;

    public PausableAnimationSet(boolean shareInterpolator) {
        super(shareInterpolator);
    }

    public PausableAnimationSet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean getTransformation(long currentTime, Transformation outTransformation) {

        if (mPaused && mElapsedAtPause == 0) {
            mElapsedAtPause = currentTime - getStartTime();
        }

        if (mPaused) {
            setStartTime(currentTime - mElapsedAtPause);
        }

        return super.getTransformation(currentTime, outTransformation);
    }

    public void pause() {
        mElapsedAtPause = 0;
        mPaused = true;
    }

    public void resume() {
        mPaused = false;
    }

}
