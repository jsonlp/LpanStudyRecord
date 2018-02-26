package com.lpan.study.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.Log;
import com.lpan.R;
import com.lpan.study.utils.ViewUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/9/11.
 */

public class AnimationFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.alpha)
    TextView mAlpha;

    @BindView(R.id.translationX)
    TextView mTranslationX;

    @BindView(R.id.translationY)
    TextView mTranslationY;

    @BindView(R.id.scaleX)
    TextView mScaleX;

    @BindView(R.id.scaleY)
    TextView mScaleY;

    @BindView(R.id.rotation)
    TextView mRotation;

    @BindView(R.id.animatorset)
    TextView mAnimatorSet;

    @BindView(R.id.animatorxml)
    TextView mAnimatorXml;

    @BindView(R.id.text1)
    View mText;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        ButterKnife.bind(this, view);

    }

    @OnClick({R.id.alpha, R.id.translationX, R.id.translationY, R.id.scaleX, R.id.scaleY, R.id.rotation, R.id.animatorset, R.id.animatorxml, R.id.text1})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alpha:
                ObjectAnimator alpha = ObjectAnimator.ofFloat(mText, "alpha", 0.f, 1.f);
                alpha.setDuration(5000);
//                alpha.setRepeatCount(ValueAnimator.INFINITE);
                alpha.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        if (Log.DEBUG) {
                            Log.d("AnimationFragment", "onAnimationStart--------");
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (Log.DEBUG) {
                            Log.d("AnimationFragment", "onAnimationEnd--------");
                        }
                    }
                });
                alpha.start();

                break;

            case R.id.translationX:
                ObjectAnimator translationX = ObjectAnimator.ofFloat(mText, "translationX", 0.f, 500f);
                translationX.setDuration(5000);
                translationX.start();
                break;

            case R.id.translationY:
                ObjectAnimator translationY = ObjectAnimator.ofFloat(mText, "translationY", 0.f, 500f);
                translationY.setDuration(5000);
                translationY.start();
                break;

            case R.id.scaleX:
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(mText, "scaleX", 1.f, 3f);
                scaleX.setDuration(5000);
                scaleX.start();
                break;

            case R.id.scaleY:
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(mText, "scaleY", 1.f, 3f);
                scaleY.setDuration(5000);
                scaleY.start();
                break;

            case R.id.rotation:
                ObjectAnimator rotation = ObjectAnimator.ofFloat(mText, "rotation", 0.f, 90f);
                rotation.setDuration(5000);
                rotation.start();
                break;

            case R.id.animatorset:
//                ObjectAnimator translationX1 = ObjectAnimator.ofFloat(mText, "translationX", 0.f, 300f);
//                ObjectAnimator translationY1 = ObjectAnimator.ofFloat(mText, "translationY", 0.f, 300f);
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(mText, "scaleX", 0f, 1f);
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(mText, "scaleY", 0f, 1f);
//                mText.setPivotY(ViewUtils.getScreenHeight(AppContext.getContext()));
                int statuHeight = ViewUtils.getStatusHeight(AppContext.getContext());
                int screenHright = ViewUtils.getScreenHeight(AppContext.getContext());

//                //左上角
//                mText.setPivotX(0);
//                mText.setPivotY(0);

//                //左下角
//                mText.setPivotX(0);
//                mText.setPivotY(mText.getHeight());

                //右上角
//                mText.setPivotX(mText.getWidth());
//                mText.setPivotY(0);

                //右下角
                mText.setPivotX(mText.getWidth());
                mText.setPivotY(mText.getHeight());

                float pivotX = mText.getPivotX();
                float pivotY = mText.getPivotY();
                if (Log.DEBUG) {
                    Log.d("AnimationFragment", "onClick--------x=" + pivotX + "  y=" + pivotY + "  top=" + mText.getTop());
                }
//                ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mText, "alpha", 0.f, 1.f);
//                ObjectAnimator rotation1 = ObjectAnimator.ofFloat(mText, "rotation", 0.f, 180f);

                AnimatorSet set = new AnimatorSet();
//                set.play(alpha1)
//                        .with(scaleX1)
//                        .with(scaleY1)
//                        .with(translationY1)
//                        .with(translationX1)
//                        .after(rotation1);

                set.play(scaleX1)
                        .with(scaleY1);
                set.setDuration(5000);
                set.start();
                break;

            case R.id.animatorxml:
                Animator animator = AnimatorInflater.loadAnimator(getActivity(), R.animator.set_1);
                animator.setTarget(mText);
                animator.start();

                break;

            case R.id.text1:
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.2f, 1f, 0.2f, 1f, Animation.RELATIVE_TO_SELF,1f, Animation.RELATIVE_TO_SELF,1f);
                scaleAnimation.setDuration(1000);
                mText.startAnimation(scaleAnimation);

                break;
        }
    }


}
