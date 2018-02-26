package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.utils.ViewUtils;

/**
 * Created by liaopan on 2018/1/30 10:30.
 */

public class BrokenLineView extends View {
    private Paint mPaint;

    private boolean mIsRight;

    public BrokenLineView(Context context) {
        super(context);
        init();
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#b8c5cc"));
        mPaint.setStrokeWidth(ViewUtils.ONE_DP * 0.5f);
    }

    public boolean isRight() {
        return mIsRight;
    }

    public void setRight(boolean right) {
        mIsRight = right;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#ebedee"));
        if (isRight()) {
            //right
            for (int i = 0; i < 20; i++) {
                float startX = 2 + i;
                float stopX = 2 + i + 1;
                float startY = (2 * i) * 10;
                float stopY = (2 * i + 1) * 10;
                canvas.drawLine(startX, startY, stopX, stopY, mPaint);
            }
        } else {
            //left
            for (int i = 0; i < 20; i++) {
                float startX = 20 - i;
                float stopX = 20 - i - 1;
                float startY = (2 * i) * 10;
                float stopY = (2 * i + 1) * 10;
                canvas.drawLine(startX, startY, stopX, stopY, mPaint);
            }
        }

    }
}
