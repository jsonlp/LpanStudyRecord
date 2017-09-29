package com.lpan.study.view.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by haizhu on 15/5/7.
 */
public class JiemoRecyclerView extends RecyclerView {

    public JiemoRecyclerView(Context context) {
        super(context);
    }

    public JiemoRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JiemoRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (!isEnabled()) {
            return true;
        }
        try {
            return super.onTouchEvent(e);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!isEnabled()) {
            //如果disable 了  就应该不让他滑动  fix by haizhu
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (!isEnabled()) {
            return true;
        }
        return super.onInterceptTouchEvent(e);
    }
}
