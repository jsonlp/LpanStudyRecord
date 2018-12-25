package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by lpan on 2018/10/24.
 */
public class RoundedMaskImageView extends BaseImageView {

    private Paint mPaint;
    private RectF mRect;
    private Path mPath;
    private Bitmap mBitmap;

    public RoundedMaskImageView(Context context) {
        super(context);
        init();
    }

    public RoundedMaskImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedMaskImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RoundedMaskImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRect = new RectF();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = getWidth();
        mRect.bottom = getHeight();
        mPaint.setColor(Color.BLACK);
        if (mBitmap != null) {
            canvas.save();
            mPath.addRoundRect(mRect,20,20,Path.Direction.CCW);
            canvas.clipPath(mPath);
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
            canvas.restore();
        }
    }

    public void setBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        invalidate();
    }
}
