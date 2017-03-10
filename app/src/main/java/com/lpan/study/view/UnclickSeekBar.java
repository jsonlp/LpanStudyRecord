package com.lpan.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * Created by lpan on 2017/3/6.
 */

public class UnclickSeekBar extends SeekBar {

    private float mDownX, mUpX, mDownY, mUpY;

    public UnclickSeekBar(Context context) {
        super(context);
    }

    public UnclickSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UnclickSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mDownX = event.getX();
            mDownY = event.getY();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            mUpX = event.getX();
            mUpY = event.getY();

            if (Math.abs(mUpX - mDownX) > 50 || Math.abs(mUpY - mDownY) > 50) {

            } else {
                return false;
            }
        }
        return super.onTouchEvent(event);
    }
}
