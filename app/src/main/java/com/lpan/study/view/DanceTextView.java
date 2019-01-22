package com.lpan.study.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

/**
 * Created by lpan on 2018/12/28.
 */
public class DanceTextView extends TextView {

    private int number;

    public DanceTextView(Context context) {
        super(context);
    }

    public DanceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DanceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void dance(int start,int end){
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(this,"number",start,end);
        objectAnimator.setDuration(3000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }

    public int getNumber() {
        return number;
    }

    @Keep
    public void setNumber(int number) {
        this.number = number;
        setText(String.valueOf(number));
    }
}
