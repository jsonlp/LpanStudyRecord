package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2016/12/27.
 */

public class GroupImageView extends View {

    private static final String TAG = "GroupImageView";

    private List<Bitmap> mBitmaps = new ArrayList<>();

    private int centerX;

    private int centerY;

    private Paint mPaint;

    private int mWidth;

    private int mHeight;

    public GroupImageView(Context context) {
        super(context);
    }

    public GroupImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public List<Bitmap> getBitmaps() {
        return mBitmaps;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        mBitmaps = bitmaps;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = getMeasuredWidth() / 2;
        centerY = getMeasuredHeight() / 2;
        Log.d(TAG, " centerX= " + centerX + "   centerY= " + centerY + " widthMeasureSpec=" + widthMeasureSpec + " heightMeasureSpec=" + heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        Path path = new Path();

        mPaint.setColor(getResources().getColor(R.color.red));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        path.moveTo(100,100);
        path.lineTo(300, 300);
        path.lineTo(100, 300);


        path.close();
        canvas.drawPath(path,mPaint);
    }
}
