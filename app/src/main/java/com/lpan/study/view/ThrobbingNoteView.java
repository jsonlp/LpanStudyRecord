package com.lpan.study.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

import com.lpan.study.utils.PausableAnimationSet;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/6/9.
 * <p>
 * desc:音频播放 动态效果
 */

public class ThrobbingNoteView extends FrameLayout {

    private LayoutAnimationController mAnimationController;
    private PausableAnimationSet mAnimationSet;
    private ViewGroup mAnimationLayoutFakeWhite;

    public ThrobbingNoteView(Context context) {
        super(context);
        init(context);
    }

    public ThrobbingNoteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ThrobbingNoteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);

    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.throbbing_note_view_layout, this);
        mAnimationLayoutFakeWhite = (ViewGroup) v.findViewById(R.id.animation_layout_fake_white);

        mAnimationController = new LayoutAnimationController(getAnimations(), 0.26f);
    }

    private Animation getAnimations() {
        if (mAnimationSet == null) {

            mAnimationSet = new PausableAnimationSet(true);

            mAnimationSet.setInterpolator(new LinearInterpolator());

            Animation animation = new ScaleAnimation(1, 1, 0.5f, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 1);
            animation.setDuration(260);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.REVERSE);

            mAnimationSet.addAnimation(animation);
        }
        return mAnimationSet;
    }

    public void startAnimation() {
        if (mAnimationLayoutFakeWhite != null) {
            mAnimationLayoutFakeWhite.setVisibility(View.VISIBLE);
            mAnimationLayoutFakeWhite.setLayoutAnimation(mAnimationController);

            if (mAnimationController != null) {
                mAnimationController.start();
            }
        }
    }

    public void stopAnimation() {
        if (mAnimationLayoutFakeWhite != null) {
            mAnimationLayoutFakeWhite.setLayoutAnimation(null);
            mAnimationLayoutFakeWhite.setVisibility(View.GONE);
        }
    }

}
