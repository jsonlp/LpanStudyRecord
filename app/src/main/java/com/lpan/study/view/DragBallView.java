package com.lpan.study.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;

/**
 * Created by lpan on 2017/9/11.
 */

public class DragBallView extends View {

    private static final int ONE_DP = ViewUtils.dp2px(AppContext.getContext(), 1);

    private static final int mMarginLeft = 50 * ONE_DP;

    private static final int DEFAULT_WIDTH = ViewUtils.getScreenWidth(AppContext.getContext()) - 2 * mMarginLeft;

    private static final int DEFAULT_HEIGHT = 400 * ONE_DP;

    private static final int SCREEN_HEIGHT = ViewUtils.getScreenHeight(AppContext.getContext());

    private static final int SCREEN_WIDTH = ViewUtils.getScreenWidth(AppContext.getContext());

    private static final int SCREEN_STATU = ViewUtils.getStatusHeight(AppContext.getContext());

    //View当前的位置
    private int rawX = 0;
    private int rawY = 0;
    //View之前的位置
    private int lastX = 0;
    private int lastY = 0;


    public DragBallView(Context context) {
        super(context);
    }

    public DragBallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DragBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DragBallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

//        canvas.drawRect(100 * ONE_DP, 100 * ONE_DP, 300 * ONE_DP, 200 * ONE_DP, paint);
        paint.setColor(Color.GREEN);
        canvas.drawLine(0, 0, getRight(), 0, paint);
        canvas.drawCircle(this.getWidth() / 2, this.getHeight() / 2, 50 * ONE_DP, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (Log.DEBUG) {
//            Log.d("DragBallView", "onMeasure--------");
//        }
//
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMeasureMode == MeasureSpec.AT_MOST && heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        } else if (widthMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
        } else if (heightMeasureMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
        } else {
            setMeasuredDimension(widthSize, heightSize);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = (int) (event.getRawX());
                rawY = (int) (event.getRawY());
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                rawX = (int) event.getRawX();
                rawY = (int) event.getRawY();
                int offsetX = rawX - lastX;
                int offsetY = rawY - lastY;
                int oldTop = getTop();
//                layout(getLeft() + offsetX,
//                        getTop() + offsetY,
//                        getRight() + offsetX,
//                        getBottom() + offsetY);
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                float scale = 1;
                int newTop = getTop();
                if (newTop > 0) {
                    //页卡状态
                    if (offsetY < 0) {
                        //向上滑动
                        changeSize(scale);
                        offsetTopAndBottom(offsetY);
                    }
                } else if (newTop == 0) {
                    //全屏状态
//                    if (offsetY > 0) {
//                        //向下滑动
//                        changeSize(scale, top, getHeight());
//                    }
                }
                lastX = rawX;
                lastY = rawY;
                break;
        }
        return true;
    }

    private double getRelation(int top, int height) {
        return (double) (top * 2 + height) / top;
    }

    private double getMaxScale(int top, int height) {
        double s = (double) ((height + 2 * top)) / height;
        return s;
    }

    private double getMinScale(int top, int height) {
        double s = (double) height / (height + 2 * top);
        return s;
    }


    private void changeSize(float scale) {
        if (scale > getMaxScaleY()) {
            scale = getMaxScaleY();
        } else if (scale < getMinScaleY()) {
            scale = getMinScaleY();
        }
        this.setScaleX(getMaxScaleX());
        this.setScaleY(getMaxScaleY());

        if (Log.DEBUG) {
            Log.d("DragBallView", "changeSize--------max=" + getMaxScaleY() + "   min=" + getMinScaleY());
        }

    }

    private float getMaxScaleY() {
        float maxY = (float) (SCREEN_HEIGHT - SCREEN_STATU) / DEFAULT_HEIGHT;
        return maxY;
    }

    private float getMinScaleY() {
        float minY = (float) DEFAULT_HEIGHT / (SCREEN_HEIGHT - SCREEN_STATU);
        return minY;
    }

    private float getMaxScaleX() {
        float maxY = (float) SCREEN_WIDTH / DEFAULT_WIDTH;
        return maxY;
    }

    private float getMinScaleX() {
        float minY = (float) DEFAULT_WIDTH / SCREEN_WIDTH;
        return minY;
    }


}
