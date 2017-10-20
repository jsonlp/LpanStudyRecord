package com.lpan.study.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lpan on 2017/10/20.
 */

public class CoordinateView extends View {

    private Paint mPaint;

    public CoordinateView(Context context) {
        super(context);
        init();
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CoordinateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //(1)平移坐标系
        canvas.save();//保存现场
        for (int i = 0; i < 10; i++) {
            canvas.drawRect(0, 0, 150, 150, mPaint);
            canvas.translate(30, 30);
        }
        canvas.restore();//恢复现场


        canvas.translate(0, 500);
        //(2)缩放坐标系
        canvas.save();
        for (int i = 0; i < 20; i++) {
            canvas.drawRect(0, 0, 400, 400, mPaint);
            canvas.scale(0.9f, 0.9f, 200, 200);
        }
        canvas.restore();

        canvas.translate(0, 500);
        //(3)旋转
        canvas.save();
        canvas.drawCircle(200, 200, 200, mPaint);
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(350, 200, 400, 200, mPaint);
            canvas.rotate(30, 200, 200);
        }
        canvas.restore();

    }
}
