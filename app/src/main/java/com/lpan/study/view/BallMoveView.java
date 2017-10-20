package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.utils.Log;

/**
 * Created by lpan on 2017/10/20.
 */

public class BallMoveView extends View {

    private Paint mPaint;

    private int x;

    private int y = 100;

    private int RADIUS = 30;

    private boolean flag = false;

    public BallMoveView(Context context) {
        super(context);
        init(context);
    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    public BallMoveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);

        x = RADIUS;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(x, y, RADIUS, mPaint);

        int width = this.getMeasuredWidth();

        if (x <= RADIUS) {
            flag = true;
        }

        if (x >= width - RADIUS) {
            flag = false;
        }

        x = flag ? x + 5 : x - 5;
        if (Log.DEBUG) {
            Log.d("BallMoveView", "onDraw--------x=" + x);
        }
    }
}
