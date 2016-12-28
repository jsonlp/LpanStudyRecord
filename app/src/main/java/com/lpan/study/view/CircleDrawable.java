package com.lpan.study.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import com.lpan.study.context.AppContext;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/27.
 */

public class CircleDrawable extends Drawable {


    private static final String TAG = "CircleDrawable";

    public final int DEFAULT_BORDER_COLOR = AppContext.getContext().getResources()
            .getColor(R.color.hint_gray);

    protected final RectF mBorderRect = new RectF();

    protected Paint mPaint = new Paint();

    protected RectF mRect = new RectF();

    protected Paint mBorderPaint = new Paint();

    protected float mOrgWidth;

    protected float mOrgHeight;

    private boolean hideBorder;

    public CircleDrawable(Bitmap bitmap, float orgWidth, float orgHeight) {
        mPaint.setAntiAlias(true);

        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        bitmapShader.setLocalMatrix(getCenterCropMatrix(srcWidth, srcHeight, orgWidth,
                orgHeight));

        mPaint.setShader(bitmapShader);

        mRect.right = orgWidth;
        mRect.bottom = orgHeight;

        mBorderRect.right = orgWidth;
        mBorderRect.bottom = orgHeight;

        this.mOrgWidth = orgWidth;
        this.mOrgHeight = orgHeight;

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(DEFAULT_BORDER_COLOR);

    }

    public Matrix getCenterCropMatrix(int srcWidth, int srcHeight, float orgWidth,
                                             float orgHeight) {

        float sy = 0.0F;
        float sx = 0.0F;
        float scale = 0.0F;

        if (srcWidth * orgHeight > srcHeight * orgWidth) {
            scale = orgHeight / srcHeight;
            sx = 0.5F * (orgWidth - scale * srcWidth + .5f);
        } else {
            scale = orgWidth / srcWidth;
            sy = 0.5F * (orgHeight - scale * srcHeight + .5f);
        }

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        matrix.postTranslate(sx, sy);

        return matrix;
    }

    public void setHideBorder(boolean hideBorder) {
        this.hideBorder = hideBorder;
    }

    public void setColor(int color) {
        mBorderPaint.setColor(color);
    }


    @Override
    public void draw(Canvas canvas) {

        canvas.drawOval(mRect, mPaint);

        if (!hideBorder) {
            canvas.drawOval(mBorderRect, mBorderPaint);
        }

    }

    @Override
    public void setAlpha(int alpha) {
        int oldAlpha = mPaint.getAlpha();
        if (alpha != oldAlpha) {
            mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    public void setStrokeWidth(float width) {
        if (width > 0) {
            mBorderPaint.setStrokeWidth(width);
            float w = width / 2.0f;
            mBorderRect.left = w;
            mBorderRect.top = w;
            mBorderRect.right = mOrgWidth - w;
            mBorderRect.bottom = mOrgHeight - w;
        }
    }
}
