package com.lpan.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ViewUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class SimleView extends FrameLayout {

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

    public SimleView(Context context) {
        this(context, null);

    }

    public SimleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        centerX = ViewUtils.getScreenWidth(AppContext.getContext()) / 2;
        centerY = ViewUtils.getScreenHeight(AppContext.getContext()) / 2 - ViewUtils.getStatusHeight(AppContext.getContext());
        mScreenheight = ViewUtils.getScreenHeight(AppContext.getContext());
        mScreenStatus = ViewUtils.getStatusHeight(AppContext.getContext());

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.smile_layout, this, false);
        mSmileViewFace = (SimleFaceView) view.findViewById(R.id.smileView1);
        mSimleEyesView = (SimleEyesView) view.findViewById(R.id.smileView2);
        mSimleLeftEyeView = (SimleLeftEyeView) view.findViewById(R.id.smileView3);
        mSimleRightEyeView = (SimleRightEyeView) view.findViewById(R.id.smileView4);

        rotateView(mSmileViewFace, 0.5f, getPovit(mScreenheight, mScreenStatus));
        rotateView(mSimleLeftEyeView, getPovit(centerX, (mEyesInRadiu)), getPovit(centerY, mEyesBallRadiu + 6 * mEyesBallRadiuDis));
        rotateView(mSimleRightEyeView, getPovit(centerX, (-mEyesInRadiu)), getPovit(centerY, mEyesBallRadiu + 6 * mEyesBallRadiuDis));


        LayoutParams params = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(view, params);
    }

    private void rotateView(View view, float pivotX, float pivotY) {
//        LinearInterpolator interpolator = new LinearInterpolator();// 以常量速率改变
//        AccelerateDecelerateInterpolator interpolator=new AccelerateDecelerateInterpolator();  //在动画开始与结束的地方速率改变比较慢，在中间的时候加速
//        AccelerateInterpolator interpolator=new AccelerateInterpolator();//在动画开始的地方速率改变比较慢，然后开始加速
        RotateAnimation rotateAnimation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_PARENT, pivotX, Animation.RELATIVE_TO_PARENT, pivotY);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setDuration(1000);

//        if(mAnimationType ==0){
//            rotateAnimation.setInterpolator(new LinearInterpolator());
//        }else if(mAnimationType ==1){
//            rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        }else if(mAnimationType ==2){
//            rotateAnimation.setInterpolator(new AccelerateInterpolator());
//        }
        rotateAnimation.setInterpolator(new LinearInterpolator());

        view.startAnimation(rotateAnimation);
    }

    private float getPovit(int widthOrHeight, int distance) {
        return ((widthOrHeight - distance) * 0.5f) / widthOrHeight;
    }


}
