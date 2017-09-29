package com.lpan.study.view.pulltorefresh.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import com.lpan.study.view.pulltorefresh.PullToRefreshBase;
import com.test.lpanstudyrecord.R;


public class JiemoLoadingLayout extends LoadingLayout {

    LoadingJiemoAnimationView mLoadingJiemoAnimationView;

    public JiemoLoadingLayout(Context context, PullToRefreshBase.Mode mode,
            PullToRefreshBase.Orientation scrollDirection, TypedArray attrs,
            boolean enableBackground) {
        super(context, mode, scrollDirection, attrs, enableBackground);
        mLoadingJiemoAnimationView = (LoadingJiemoAnimationView) findViewById(R.id.layout);
    }

    @Override
    protected int getDefaultDrawableResId() {
        return -1;
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {

    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {
        mLoadingJiemoAnimationView.setVisibility(VISIBLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // mLoadingWuyaAnimationView.stopLoadAnimation();
            if (scaleOfLayout > 2) {
                scaleOfLayout = 2f;
            }
            mLoadingJiemoAnimationView.setAlpha(scaleOfLayout / 2f);
        }
    }

    @Override
    protected void pullToRefreshImpl() {
        if (mLoadingJiemoAnimationView != null) {
            mLoadingJiemoAnimationView.startLoadAnimation();
        }
    }

    @Override
    protected void refreshingImpl() {
        mLoadingJiemoAnimationView.setVisibility(VISIBLE);
        mLoadingJiemoAnimationView.continueLoadingAnimation();
    }

    @Override
    protected void releaseToRefreshImpl() {

    }

    @Override
    protected void resetImpl() {
        if (mLoadingJiemoAnimationView != null) {
            mLoadingJiemoAnimationView.stopLoadAnimation();
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (mLoadingJiemoAnimationView != null) {
            mLoadingJiemoAnimationView.setVisibility(visibility);
        }
    }
}
