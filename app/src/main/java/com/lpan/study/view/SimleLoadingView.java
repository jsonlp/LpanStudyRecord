package com.lpan.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class SimleLoadingView extends FrameLayout {

    private int mFaceOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 21.5f);

    private int mFaceInRadiu = ViewUtils.dp2px(AppContext.getContext(), 19.5f);

    private int mStrokOutWidth = ViewUtils.dp2px(AppContext.getContext(), 4);

    private int mStrokInWidth = ViewUtils.dp2px(AppContext.getContext(), 1.5f);

    private int mEyesOutRadiu = ViewUtils.dp2px(AppContext.getContext(), 7.75f);

    private int mEyesInRadiu = ViewUtils.dp2px(AppContext.getContext(), 7);

    private int mEyesBallRadiu = ViewUtils.dp2px(AppContext.getContext(), 2);

    private int mEyesBallRadiuDis = ViewUtils.dp2px(AppContext.getContext(), 2); //两只眼睛的中心在外圈中心上面2dp

    private int mErrorDis = ViewUtils.dp2px(AppContext.getContext(), 1);//误差

    private int mScreenheight;

    private int mScreenStatus;

    private int mWhiteColor = 0x00ffffff;

    private int mGrayColor = 0xE8EAEB;

    private int centerX;

    private int centerY;

    private SimleFaceView mSmileViewFace;
    private SimleEyesView mSimleEyesView;
    private SimleLeftEyeView mSimleLeftEyeView;
    private SimleRightEyeView mSimleRightEyeView;
    private FrameLayout mBackground;

    public SimleLoadingView(Context context) {
        this(context, null);

    }

    public SimleLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimleLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getMeasuredWidth();
        centerY = getMeasuredHeight();
        startAnimation();
    }

    private void init(Context context) {
        mScreenheight = ViewUtils.getScreenHeight(AppContext.getContext());
        mScreenStatus = ViewUtils.getStatusHeight(AppContext.getContext());

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.smile_layout, this, false);
        mSmileViewFace = (SimleFaceView) view.findViewById(R.id.smileView1);
        mSimleEyesView = (SimleEyesView) view.findViewById(R.id.smileView2);
        mSimleLeftEyeView = (SimleLeftEyeView) view.findViewById(R.id.smileView3);
        mSimleRightEyeView = (SimleRightEyeView) view.findViewById(R.id.smileView4);
        mBackground = (FrameLayout) view.findViewById(R.id.background);

//        rotateView(mSmileViewFace, 0.5f, 0.5f);
//        rotateView(mSimleLeftEyeView, getPovit(centerX,mEyesInRadiu),0.5f);
//        rotateView(mSimleRightEyeView, getPovit(centerX, (-mEyesInRadiu)), getPovit(centerY, mEyesBallRadiu + 6 * mEyesBallRadiuDis));


        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        addView(view, params);
    }

    private void rotateView(View view, float pivotX, float pivotY) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_PARENT, pivotX, Animation.RELATIVE_TO_PARENT, pivotY);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setDuration(1000);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        view.startAnimation(rotateAnimation);
    }

    public void startAnimation() {
        rotateView(mSmileViewFace, 0.5f, 0.5f);
        rotateView(mSimleLeftEyeView, getPovit(centerX, mEyesOutRadiu + 4 * mErrorDis), getPovit(centerY, mEyesBallRadiuDis + 2 * mErrorDis));
        rotateView(mSimleRightEyeView, getPovit(centerX, -(mEyesOutRadiu + 8 * mErrorDis)), getPovit(centerY, mEyesBallRadiuDis + 2 * mErrorDis));
    }

    public void stopAnimation() {

    }

    private float getPovit(int widthOrHeight, int distance) {
        Log.e("lp-test", "----p = " + ((widthOrHeight - distance) * 0.5f) / widthOrHeight);
        return ((widthOrHeight - distance) * 0.5f) / widthOrHeight;
    }

    public void setBackColor(int color) {
        if (mBackground != null) {
            mBackground.setBackgroundColor(color);
        }
    }


}
