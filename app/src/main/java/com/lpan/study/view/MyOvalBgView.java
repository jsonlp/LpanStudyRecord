package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.utils.Log;


/**
 * Created by LHL on 2017/9/7.
 */

public class MyOvalBgView extends View {

    private double mWidth;

    private Paint mPaint = new Paint();

    private float radius;

    private Path path = new Path();

    private double height;

    public MyOvalBgView(Context context) {
        super(context);
        init();
    }

    public MyOvalBgView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MyOvalBgView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }



    public void init() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        radius = 1900;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        height = (float) (radius - Math.sqrt(radius * radius - (mWidth * mWidth) / 4));
        setMeasuredDimension((int)mWidth, (int)height);

        if (Log.DEBUG) {
            Log.i("MyOvalBgView", "onMeasure: " + "..");
        }
        invalidate();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float angle;

        angle = (float) Math.toDegrees(Math.asin(mWidth/radius));

        path.reset();

        path.moveTo(0, 0);

        path.lineTo(0, (float) height);

        path.lineTo((float)mWidth, (float)height);

        if (Log.DEBUG) {
            Log.i("MyOvalBgView", "onDraw: " + "width : " + mWidth  + "   height : "  +height);
        }

        path.lineTo((float)mWidth, 0);

        path.arcTo(new RectF(-(float)(radius - mWidth/ 2), -(float)(2 * radius - height), (float)(radius+ mWidth/2),
                (float)height), (90 - angle), (90 + angle));

        path.close();
        canvas.drawPath(path, mPaint);

    }
}
