package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.lpan.study.utils.BitmapUtils;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/10/20.
 */

public class ClipView extends View {
    public ClipView(Context context) {
        super(context);

        init();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapUtils.readBitmapById(getContext(), R.drawable.wall01);

        //clipRect
        canvas.drawBitmap(bitmap, 0, 0, null);//1绘制原图
        canvas.translate(0, 500);
        //定义剪切区
        canvas.clipRect(new Rect(100, 100, 500, 500));
        canvas.drawBitmap(bitmap, 0, 0, null);//2绘制clipRect

        //clipPath
        Path path = new Path();
        path.addCircle(500,350,200, Path.Direction.CCW);
        //定义新的剪切区 并与上一个剪切区做op运算
        canvas.clipPath(path, Region.Op.UNION);
        canvas.drawBitmap(bitmap, 0, 0, null);//3绘制clipPath

    }
}
