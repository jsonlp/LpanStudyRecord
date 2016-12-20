package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;


/**
 * Created by lpan on 2016/12/19.
 */

public class SimleLoadingView extends View {

    private int mFaceOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 100);

    private int mFaceInRadiu = ViewUtils.dp2px(AppContext.getContext(), 90);

    private int mEyesOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 30);

    private int mEyesInRadiu = ViewUtils.dp2px(AppContext.getContext(), 25);

    private int mEyesBallRadiu = ViewUtils.dp2px(AppContext.getContext(), 10);

    public static final float ONE_DP = ViewUtils.dp2px(AppContext.getContext(),1);

    private int mRedColor = 0x00da372a;

    private int mGrayColor = 0xa19992;

    private Paint mPaint;

    private int centerX;

    private int centerY;
    
    private Path mPath;
    
    private int mProgress;
    
    private int mMax;


    public SimleLoadingView(Context context) {
        super(context);
        init();
    }

    public SimleLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);


        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX, centerY, mFaceOutRadiu, mPaint);
//

        mPaint.setColor(getResources().getColor(R.color.widget_black));
        mPath.reset();
        mPath.moveTo(centerX, 0);
        mPath.arcTo(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), -90, 360 * (float) mProgress / mMax, false);
        mPath.lineTo((float) (centerX + (mFaceOutRadiu - ONE_DP) * Math.sin(Math.toRadians(360 * (float) mProgress / mMax))), (float) (centerY - (mFaceOutRadiu - ONE_DP) * Math.cos(Math.toRadians(360 * (float) mProgress / mMax))));
        mPath.arcTo(new RectF(ONE_DP, ONE_DP, getMeasuredWidth() - ONE_DP, getMeasuredHeight() - ONE_DP), -90 + 360 * (float) mProgress / mMax, -360 * (float) mProgress / mMax, false);
        mPath.close();
        canvas.drawPath(mPath, mPaint);


    }

    private void init() {
        mPaint = new Paint();
        mPath = new Path();
        centerX = ViewUtils.getScreenWidth(AppContext.getContext()) / 2;
        centerY = ViewUtils.getScreenHeight(AppContext.getContext())/2 - ViewUtils.getStatusHeight(AppContext.getContext());

        Log.d("lp-test", "----centerX=" + centerX + "   centerY=" + centerY);
    }


    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }
}
