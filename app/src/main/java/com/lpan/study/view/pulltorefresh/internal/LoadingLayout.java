/**
 * ****************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */
package com.lpan.study.view.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lpan.study.view.pulltorefresh.ILoadingLayout;
import com.lpan.study.view.pulltorefresh.PullToRefreshBase;
import com.test.lpanstudyrecord.R;


@SuppressLint("ViewConstructor")
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {

    static final String LOG_TAG = "PullToRefresh-LoadingLayout";

    static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();

    protected final ImageView mHeaderImage;

    protected final ProgressBar mHeaderProgress;

    protected final PullToRefreshBase.Mode mMode;

    protected final PullToRefreshBase.Orientation mScrollDirection;

    //    private final TextView mHeaderText;
    //
    //    private final TextView mSubHeaderText;
    protected FrameLayout mInnerLayout;

    private boolean mUseIntrinsicAnimation;

    //    private CharSequence mPullLabel;
    //
    //    private CharSequence mRefreshingLabel;
    //
    //    private CharSequence mReleaseLabel;

    public LoadingLayout(Context context, final PullToRefreshBase.Mode mode, final PullToRefreshBase.Orientation scrollDirection,
                         TypedArray attrs, boolean enableBackground) {
        super(context);
        mMode = mode;
        mScrollDirection = scrollDirection;

        if (mode == PullToRefreshBase.Mode.USER_WUYA_LOADING) {
            LayoutInflater.from(context).inflate(
                    R.layout.pull_to_refresh_header_horizontal_loading, this);
        } else {

            switch (scrollDirection) {
                case HORIZONTAL:
                    LayoutInflater.from(context).inflate(
                            R.layout.pull_to_refresh_header_horizontal, this);
                    break;
                case VERTICAL:
                default:

                    LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header_vertical,
                            this);

                    break;
            }
        }
        mInnerLayout = (FrameLayout) findViewById(R.id.fl_inner);
        //        mHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_text);
        mHeaderProgress = (ProgressBar) mInnerLayout.findViewById(R.id.pull_to_refresh_progress);
        //        mSubHeaderText = (TextView) mInnerLayout.findViewById(R.id.pull_to_refresh_sub_text);
        mHeaderImage = (ImageView) mInnerLayout.findViewById(R.id.pull_to_refresh_image);

        LayoutParams lp = (LayoutParams) mInnerLayout.getLayoutParams();

        switch (mode) {
            case PULL_FROM_END:
                lp.gravity = scrollDirection == PullToRefreshBase.Orientation.VERTICAL ? Gravity.TOP : Gravity.LEFT;

                //                // Load in labels
                //                mPullLabel = context.getString(R.string.pull_to_refresh_from_bottom_pull_label);
                //                mRefreshingLabel = context
                //                        .getString(R.string.pull_to_refresh_from_bottom_refreshing_label);
                //                mReleaseLabel = context
                //                        .getString(R.string.pull_to_refresh_from_bottom_release_label);
                break;

            case PULL_FROM_START:
            default:
                lp.gravity = scrollDirection == PullToRefreshBase.Orientation.VERTICAL ? Gravity.BOTTOM
                        : Gravity.RIGHT;

                //                // Load in labels
                //                mPullLabel = context.getString(R.string.pull_to_refresh_pull_label);
                //                mRefreshingLabel = context.getString(R.string.pull_to_refresh_refreshing_label);
                //                mReleaseLabel = context.getString(R.string.pull_to_refresh_release_label);
                break;
        }

        if (enableBackground && attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderBackground)) {
            Drawable background = attrs.getDrawable(R.styleable.PullToRefresh_ptrHeaderBackground);
            if (null != background) {
                ViewCompat.setBackground(this, background);
            }
        }

        //        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance)) {
        //            TypedValue styleID = new TypedValue();
        //            attrs.getValue(R.styleable.PullToRefresh_ptrHeaderTextAppearance, styleID);
        //            setTextAppearance(styleID.data);
        //        }
        //        if (attrs.hasValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance)) {
        //            TypedValue styleID = new TypedValue();
        //            attrs.getValue(R.styleable.PullToRefresh_ptrSubHeaderTextAppearance, styleID);
        //            setSubTextAppearance(styleID.data);
        //        }
        //
        //        // Text Color attrs need to be set after TextAppearance attrs
        //        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderTextColor)) {
        //            ColorStateList colors = attrs
        //                    .getColorStateList(R.styleable.PullToRefresh_ptrHeaderTextColor);
        //            if (null != colors) {
        //                setTextColor(colors);
        //            }
        //        }
        //        if (attrs.hasValue(R.styleable.PullToRefresh_ptrHeaderSubTextColor)) {
        //            ColorStateList colors = attrs
        //                    .getColorStateList(R.styleable.PullToRefresh_ptrHeaderSubTextColor);
        //            if (null != colors) {
        //                setSubTextColor(colors);
        //            }
        //        }

        // Try and get defined drawable from Attrs
        Drawable imageDrawable = null;
        if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawable)) {
            imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawable);
        }

        // Check Specific Drawable from Attrs, these overrite the generic
        // drawable attr above
        switch (mode) {
            case PULL_FROM_START:
            default:
                if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableStart)) {
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableStart);
                }
                break;

            case PULL_FROM_END:
                if (attrs.hasValue(R.styleable.PullToRefresh_ptrDrawableEnd)) {
                    imageDrawable = attrs.getDrawable(R.styleable.PullToRefresh_ptrDrawableEnd);
                }
                break;
        }

        // If we don't have a user defined drawable, load the default
        if (null == imageDrawable && getDefaultDrawableResId() > 0) {
            imageDrawable = context.getResources().getDrawable(getDefaultDrawableResId());
        }

        // Set Drawable, and save width/height
        if (imageDrawable != null) {
            setLoadingDrawable(imageDrawable);
        }

        reset();
    }

    public void setHeight(int height) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.height = height;
        requestLayout();
    }

    public final void setWidth(int width) {
        ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) getLayoutParams();
        lp.width = width;
        requestLayout();
    }

    public int getContentSize() {
        switch (mScrollDirection) {
            case HORIZONTAL:
                if (mInnerLayout.getWidth() == 0) {
                    mInnerLayout.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                    return mInnerLayout.getMeasuredWidth();
                } else {
                    return mInnerLayout.getWidth();
                }
            case VERTICAL:
            default:
                if (mInnerLayout.getHeight() == 0) {
                    mInnerLayout.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
                    return mInnerLayout.getMeasuredHeight();
                } else {
                    return mInnerLayout.getHeight();
                }
        }
    }

    public final void hideAllViews() {
        if (mHeaderImage == null) {
            return;
        }
        mHeaderProgress.setVisibility(View.GONE);
        mHeaderImage.setVisibility(View.GONE);
        mInnerLayout.setVisibility(View.GONE);
    }

    public final void onPull(float scaleOfLayout) {
        if (!mUseIntrinsicAnimation) {
            onPullImpl(scaleOfLayout);
        }
    }

    public final void pullToRefresh() {
        //        if (null != mHeaderText) {
        //            mHeaderText.setText(mPullLabel);
        //        }

        // Now call the callback
        pullToRefreshImpl();
    }

    public final void refreshing() {
        //        if (null != mHeaderText) {
        //            mHeaderText.setText(mRefreshingLabel);
        //        }

        if (mUseIntrinsicAnimation && getDefaultDrawableResId() > 0) {
            if (mHeaderImage == null) {
                return;
            }
            ((AnimationDrawable) mHeaderImage.getDrawable()).start();
        } else {
            // Now call the callback
            refreshingImpl();
        }

        //        if (null != mSubHeaderText) {
        //            mSubHeaderText.setVisibility(View.GONE);
        //        }
    }

    public final void releaseToRefresh() {

        // Now call the callback
        releaseToRefreshImpl();
    }

    public final void reset() {
        if (mHeaderImage != null) {
            mHeaderImage.setVisibility(View.VISIBLE);
        }

        if (mUseIntrinsicAnimation) {
            if (mHeaderImage != null) {
                ((AnimationDrawable) mHeaderImage.getDrawable()).stop();
            }
        } else {
            // Now call the callback
            resetImpl();
        }

    }

    public final void setLoadingDrawable(Drawable imageDrawable) {
        if (mHeaderImage == null) {
            return;
        }
        // Set Drawable
        mHeaderImage.setImageDrawable(imageDrawable);
        mUseIntrinsicAnimation = (imageDrawable instanceof AnimationDrawable);

        // Now call the callback
        onLoadingDrawableSet(imageDrawable);
    }

    public final void showInvisibleViews() {
        if (mHeaderImage != null) {
            mHeaderProgress.setVisibility(View.VISIBLE);
            mHeaderImage.setVisibility(View.VISIBLE);
        }
    }

    public final void showProgress() {
        if (mHeaderImage != null) {
            mHeaderProgress.setVisibility(View.VISIBLE);
            mHeaderImage.setVisibility(View.GONE);
            mInnerLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Callbacks for derivative Layouts
     */

    protected abstract int getDefaultDrawableResId();

    protected abstract void onLoadingDrawableSet(Drawable imageDrawable);

    protected abstract void onPullImpl(float scaleOfLayout);

    protected abstract void pullToRefreshImpl();

    protected abstract void refreshingImpl();

    protected abstract void releaseToRefreshImpl();

    protected abstract void resetImpl();

}
