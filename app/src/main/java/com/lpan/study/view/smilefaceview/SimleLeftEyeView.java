package com.lpan.study.view.smilefaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.lpan.R;

/**
 * Created by lpan on 2016/12/20.
 */

public class SimleLeftEyeView extends View {
    private int mFaceOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 21.5f);

    private int mFaceInRadiu = ViewUtils.dp2px(AppContext.getContext(), 19.5f);

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

    public SimleLeftEyeView(Context context) {
        super(context);
        init();
    }

    public SimleLeftEyeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SimleLeftEyeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
//        centerX = ViewUtils.getScreenWidth(AppContext.getContext()) / 2;
//        centerY = ViewUtils.getScreenHeight(AppContext.getContext()) / 2 - ViewUtils.getStatusHeight(AppContext.getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getMeasuredWidth()/2;
        centerY = getMeasuredHeight()/2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAntiAlias(true);

        //4画左眼珠
        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(centerX - mEyesInRadiu - mEyesBallRadiu - mErrorDis, centerY - mEyesBallRadiuDis - mEyesBallRadiu -mErrorDis, mEyesBallRadiu, mPaint);


    }
}
