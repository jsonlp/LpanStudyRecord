package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.R;
import com.lpan.study.context.AppContext;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;

/**
 * Created by liaopan on 2018/1/17 09:58.
 */

public class HalfOvalView extends View {

    private Paint mPaint;

    private RectF mRectF;

    private Path mPath;

    private int mColor;

    public static final int SCREEN_WIDTH = ViewUtils.getScreenWidth(AppContext.getContext());

    public HalfOvalView(Context context) {
        super(context);
        init();
    }

    public HalfOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HalfOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public HalfOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
        }
        float offset = SCREEN_WIDTH * 23.0f / 375.0f;
        float left = (-1.618f / 2) * SCREEN_WIDTH;
        float top = (-1.618f) * SCREEN_WIDTH + offset;
        float right = (3.618f / 2) * SCREEN_WIDTH;
        float bottom = offset;

        mRectF = new RectF(left, top, right, bottom);
        mPath = new Path();
        if (Log.DEBUG) {
            Log.d("HalfOvalView", "init--------offset=" + offset);
        }
    }

    public int getColor() {
        if (mColor == 0) {
            mColor = R.color.preset_01;
        }
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        mPaint.setStyle(Paint.Style.FILL);
        mPath.addOval(mRectF, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_WINDING);

        mPaint.setColor(getResources().getColor(getColor()));
        canvas.drawPath(mPath, mPaint);
    }
}
