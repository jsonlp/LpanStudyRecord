package com.lpan.study.view.pathanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by liaopan on 2018/1/22 15:23.
 */

public class PaperPlaneView extends ImageView {

    private boolean isBusy;

    public PaperPlaneView(Context context) {
        super(context);
    }

    public PaperPlaneView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaperPlaneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaperPlaneView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }


    public void fly(float degree, PointF startPoint, PointF controlPoint, PointF endPoint) {
        this.setVisibility(VISIBLE);
        AnimatorPath path = new AnimatorPath();
        path.moveTo(startPoint.x, startPoint.y);
        path.secondBesselCurveTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);

        if (degree != 0) {
            this.setPivotX(getWidth() / 2);
            this.setPivotY(getHeight() / 2);
            this.setRotation(degree);
        }
        ObjectAnimator anim = ObjectAnimator.ofObject(this, "fly", new PathEvaluator(), path.getPoints().toArray());
        anim.setInterpolator(new DecelerateInterpolator());
        anim.setDuration(3000);
        anim.start();
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isBusy = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isBusy = false;
                PaperPlaneView.this.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 设置View的属性通过ObjectAnimator.ofObject()的反射机制来调用
     *
     * @param newLoc
     */
    public void setFly(PathPoint newLoc) {
        this.setTranslationX(newLoc.mX);
        this.setTranslationY(newLoc.mY);
    }


}
