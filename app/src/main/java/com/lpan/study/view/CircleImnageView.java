package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by lpan on 2016/12/27.
 */

public class CircleImnageView extends ImageView {

    private static final String TAG = "CircleImageView";

    private int mWidth;

    private int mHeight;

    private CircleDrawable mCircleDrawable;

    private float mStrokeWidth = 0;

    private int mColor;

    private int mPreColor;

    private boolean hideBorder;

    public CircleImnageView(Context context) {
        super(context);
    }

    public CircleImnageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImnageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }

        super.setImageDrawable(getCircleDrawable(bitmap));
    }

    private CircleDrawable getCircleDrawable(Bitmap bitmap) {

        if (getLayoutParams() != null) {
            mWidth = getLayoutParams().width;
            mHeight = getLayoutParams().height;
        }
        if (mWidth <= 0 || mWidth <= 0) {
            if (mWidth == -1) {
                mWidth = getMeasuredWidth();
            }
            Log.e(TAG, "size is  err " + mWidth + "  height=" + mHeight);
        }
        mCircleDrawable = new CircleDrawable(bitmap, mWidth, mHeight);
        mCircleDrawable.setHideBorder(hideBorder);
        setStrokeStyle(mStrokeWidth, mColor);
        return mCircleDrawable;
    }

    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    public void setStrokeStyle(float width, int color) {
        this.mStrokeWidth = width;
        this.mColor = color;
        if (mCircleDrawable != null) {
            mCircleDrawable.setStrokeWidth(width);
            mCircleDrawable.setColor(color);
            mCircleDrawable.invalidateSelf();
        }
    }
}
