package com.lpan.study.fragment.customviewtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.BitmapUtils;
import com.test.lpanstudyrecord.R;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/18.
 */

public class CanvasAndPaintFragment extends ButterKnifeFragment {

    @BindView(R.id.image1)
    ImageView mImage1;

    @BindView(R.id.image2)
    ImageView mImage2;

    @BindView(R.id.image3)
    ImageView mImage3;

    @BindView(R.id.image4)
    ImageView mImage4;

    @BindView(R.id.image5)
    ImageView mImage5;

    @BindView(R.id.image6)
    ImageView mImage6;

    @BindView(R.id.image7)
    ImageView mImage7;

    @BindView(R.id.image8)
    ImageView mImage8;

    private Paint mPaint = new Paint();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_cancas_and_paint;
    }

    @Override
    protected boolean withActionBar() {
        return true;
    }

    @Override
    protected String getActionBarTitle() {
        return "Paint and Canvas";
    }

    @Override
    protected void initData() {
        super.initData();
        Bitmap bitmap = Bitmap.createBitmap(400, 1400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        //绘制文字
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextSize(24);
        mPaint.setTextSkewX(0.5f);//倾斜度
        mPaint.setUnderlineText(true);
        mPaint.setFakeBoldText(true);//粗体
        canvas.drawText("十九大会议今天直播", 10, 100, mPaint);

        //绘制图形
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(30);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawRect(new RectF(10, 200, 350, 350), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("Paint.Join.BEVEL", 100, 270, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawRect(new RectF(10, 400, 350, 550), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("Paint.Join.MITER", 100, 470, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawRect(new RectF(10, 600, 350, 750), mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        canvas.drawText("Paint.Join.ROUND", 100, 670, mPaint);

        mImage1.setImageBitmap(bitmap);


        drawBitmap();
        drawPoint();
        drawLine();
        drawRoundRect();
        drawRoundRect();
        drawOval();
        drawArc();
        drawPath();
    }

    //1 canvas 绘制位图
    private void drawBitmap() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //原大小绘制
        canvas.drawBitmap(bitmap, 0, 0, null);

        //对图片进行缩放
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        Rect src = new Rect(0, 0, bitmapWidth, bitmapHeight);
        Rect dst = new Rect(0, bitmapHeight, bitmapWidth * 3, bitmapHeight * 3 + bitmapHeight);
        canvas.drawBitmap(bitmap, src, dst, null);

        mImage2.setImageBitmap(bitmapBuffer);
    }

    //2 canvas 绘制点
    private void drawPoint() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(4);
        canvas.drawPoint(120, 20, mPaint);

        mPaint.setColor(Color.BLUE);
        //两个数一组 画4个蓝色的点
        float[] points1 = {10, 10, 50, 50, 50, 100, 50, 150};
        canvas.drawPoints(points1, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawPoints(points1, 1, 4, mPaint);
        mImage3.setImageBitmap(bitmapBuffer);
    }

    //3 canvas 绘制线
    private void drawLine() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(1);
        int offsetDy = 50;
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(10, offsetDy * i, 300, offsetDy * i, mPaint);
        }

        mImage4.setImageBitmap(bitmapBuffer);
    }

    //4 canvas 绘制矩形/圆角矩形
    private void drawRoundRect() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();

        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(new Rect(0, 0, 200, 400), mPaint);
        canvas.drawRoundRect(new RectF(0,0,200,400),20,20,mPaint);
        mImage5.setImageBitmap(bitmapBuffer);
    }

    //5 绘制椭圆/圆
    private void drawOval() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();

        mPaint.setColor(Color.BLUE);
//        canvas.drawCircle(100,100,50,mPaint);
        canvas.drawOval(new RectF(0, 0, 400, 200), mPaint);
        mImage6.setImageBitmap(bitmapBuffer);
    }

    //6 绘制圆弧/扇形
    private void drawArc() {
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();
        mPaint.setAntiAlias(true);

        RectF rectF = new RectF(100,100,400,200);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        //先将矩形的内切圆画出
        canvas.drawOval(rectF,mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        //false是画弧线,true是画扇形

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,30,60,true,mPaint);

        mPaint.setStrokeWidth(10);
        canvas.drawArc(rectF,0,-90,false,mPaint);

        mImage7.setImageBitmap(bitmapBuffer);
    }

    //7 绘制路径
    private void drawPath(){
        Bitmap bitmapBuffer = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapBuffer);
        mPaint.reset();
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(100,100);
        path.rLineTo(300,0);
        path.rLineTo(-240,240);

//        path.close();
        canvas.drawPath(path,mPaint);
        mImage8.setImageBitmap(bitmapBuffer);

    }

    private void draw1(){

    }
}
