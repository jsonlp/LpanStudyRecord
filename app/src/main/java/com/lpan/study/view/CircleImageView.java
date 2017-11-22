package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.lpan.R;


public class CircleImageView extends ImageView {

    private static final String TAG = "CircleImageView";

    private int mWidth;

    private int mHeight;

    private CircleDrawable mCircleDrawable;

    private float mStrokeWidth = 0;

    private int mColor;

    private boolean hideBorder;

    private Bitmap mBitmap;

    protected Drawable mOriginalDrawable;


    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CircleImageView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    protected void init() {
        if(mOriginalDrawable == null) {
            mColor = getContext().getResources().getColor(R.color.hint_gray);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setColor(getContext().getResources().getColor(R.color.bg_f2));
            drawable.setShape(GradientDrawable.OVAL);
            setOriginalDrawable(drawable);
        }

    }

    public void setOriginalDrawable(Drawable drawable) {
        mOriginalDrawable = drawable;
    }


    public void setSize(int size) {
        this.mWidth = size;
        this.mHeight = size;
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }

        super.setImageDrawable(getCircleDrawable(bitmap));
    }

    protected Drawable getGradientAnimationDrawable(Bitmap bitmap) {
        TransitionDrawable td = new TransitionDrawable(new Drawable[] { mOriginalDrawable,
                getCircleDrawable(bitmap) });
        td.startTransition(200);
        return td;
    }

    @Override
    public void setImageResource(int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        setImageBitmap(bitmap);
    }


    public void setSuperImageResource(@DrawableRes int resId) {
        super.setImageResource(resId);
    }

    public void hideBorder(boolean hide) {
        hideBorder = hide;
    }


    public CircleDrawable getCircleDrawable(Bitmap bitmap) {
        mBitmap = bitmap;
        if (getLayoutParams() != null) {
            mWidth = getLayoutParams().width;
            mHeight = getLayoutParams().height;
        }
        if (mWidth <= 0 || mWidth <= 0) {
            if (mWidth == -1) {
                mWidth = getMeasuredWidth();
            }
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

    public Bitmap getBitmap() {
        return mBitmap;
    }
}
