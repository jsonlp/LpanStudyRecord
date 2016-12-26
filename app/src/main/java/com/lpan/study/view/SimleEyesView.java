package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/20.
 */

public class SimleEyesView extends View {
    private int mFaceOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 21.5f);

    private int mFaceInRadiu = ViewUtils.dp2px(AppContext.getContext(), 19.5f);

    private int mStrokWidth = ViewUtils.dp2px(AppContext.getContext(), 1.5f);

    private int mEyesOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 7.75f);

    private int mEyesInRadiu = ViewUtils.dp2px(AppContext.getContext(), 7);

    private int mEyesBallRadiu = ViewUtils.dp2px(AppContext.getContext(), 2);

    private int mEyesBallRadiuDis = ViewUtils.dp2px(AppContext.getContext(), 2); //两只眼睛的中心在外圈中心上面2dp

    private int mErrorDis = ViewUtils.dp2px(AppContext.getContext(), 1);//误差

    private Paint mPaint;

    private int centerX;

    private int centerY;

    private int mWhiteColor = 0x00ffffff;

    private int mGrayColor = 0xE8EAEB;


    public SimleEyesView(Context context) {
        super(context);
        init();
    }

    public SimleEyesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimleEyesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getMeasuredWidth()/2;
        centerY = getMeasuredHeight()/2;

    }

    private void init() {
        mPaint = new Paint();
//        centerX = ViewUtils.getScreenWidth(AppContext.getContext()) / 2;
//        centerY = ViewUtils.getScreenHeight(AppContext.getContext()) / 2 - ViewUtils.getStatusHeight(AppContext.getContext());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);

        // 2画左眼  画圆
        mPaint.setColor(mWhiteColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX - mEyesInRadiu, centerY - mEyesBallRadiuDis, mEyesInRadiu, mPaint);

        // 2画左眼  画圆形进度
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokWidth);
        RectF oval2 = new RectF(centerX - 2 * mEyesInRadiu, centerY - mEyesInRadiu - mEyesBallRadiuDis, centerX, mEyesInRadiu + centerY - mEyesBallRadiuDis);
        canvas.drawArc(oval2, 0, 360, false, mPaint);


        // 3画右眼  画圆
        mPaint.setColor(mWhiteColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX + mEyesInRadiu, centerY - mEyesBallRadiuDis, mEyesInRadiu, mPaint);

        // 3画右眼  画圆形进度
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokWidth);
        RectF oval3 = new RectF(centerX, centerY - mEyesInRadiu - mEyesBallRadiuDis, centerX + 2 * mEyesInRadiu, mEyesInRadiu + centerY - mEyesBallRadiuDis);
        canvas.drawArc(oval3, 0, 360, false, mPaint);
    }
}
