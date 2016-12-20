package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;

/**
 * Created by lpan on 2016/12/20.
 */

public class SimleFaceView extends View {
    private int mFaceOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 21.5f);

    private int mStrokWidth= ViewUtils.dp2px(AppContext.getContext(), 4);

    private int mFaceInRadiu = ViewUtils.dp2px(AppContext.getContext(), 19.5f);

    private int mEyesOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 7.75f);

    private int mEyesInRadiu = ViewUtils.dp2px(AppContext.getContext(), 7);

    private int mEyesBallRadiu = ViewUtils.dp2px(AppContext.getContext(), 2);

    private int mErrorDis = ViewUtils.dp2px(AppContext.getContext(), 1);//误差

    private Paint mPaint;

    private int centerX;

    private int centerY;

    private int mWhiteColor = 0x00ffffff;

    private int mGrayColor = 0xff636363;

    public SimleFaceView(Context context) {
        super(context);
        init();
    }

    public SimleFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimleFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        centerX = ViewUtils.getScreenWidth(AppContext.getContext()) / 2;
        centerY = ViewUtils.getScreenHeight(AppContext.getContext()) / 2 - ViewUtils.getStatusHeight(AppContext.getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);

        // 1画脸  画圆
        mPaint.setColor(mWhiteColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, mFaceInRadiu, mPaint);

        // 1画脸  画圆形进度
        mPaint.setColor(mGrayColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokWidth);
        RectF oval = new RectF(centerX - mFaceInRadiu, centerY - mFaceInRadiu, mFaceInRadiu + centerX, mFaceInRadiu + centerY);
        canvas.drawArc(oval, -90, 280, false, mPaint);
    }
}
