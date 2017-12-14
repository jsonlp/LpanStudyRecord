package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;


import com.lpan.study.context.AppContext;
import com.lpan.study.utils.ArrayUtils;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;


public class TouchImageView extends ImageView {

    public static final float VIEW_HEIGHT = ViewUtils.ONE_DP * 300;
    public static final float MARRGIN = ViewUtils.ONE_DP * 1;
    public static final float VIEW_WIDTH = (ViewUtils.getScreenWidth(AppContext.getContext()) - MARRGIN) / 2;

    private Context context;
    // 手指按下时图片的矩阵
    private Matrix downMatrix = new Matrix();
    // 手指移动时图片的矩阵
    private Matrix moveMatrix = new Matrix();
    // 多点触屏时的中心点
    private PointF midPoint = new PointF();
    // 图片的中心点坐标
    private PointF imageMidPoint = new PointF();
    // 关闭按钮的中心点坐标
    private PointF closeMidPoint = new PointF();

    // 绘制图片的边框
    private Paint paintEdge;

    private Path mPath;

    // 触控模式
    private int mode;
    private static final int NONE = 0; // 无模式
    private static final int TRANS = 1; // 拖拽模式
    private static final int ZOOM_MULTI = 4; // 多点缩放模式

    // 手指按下屏幕的X坐标
    private float downX;
    // 手指按下屏幕的Y坐标
    private float downY;
    // 手指之间的初始距离
    private float oldDistance;
    // 手指之间的初始角度
    private float oldRotation;

    // 图片目前的宽高
    private float imageCurrentWidth;
    private float imageCurrentHeight;

    // 图片最小的宽高
    private float minWidth;
    private float minHeight;

    // 绘制图片的矩阵
    private Matrix drawMatrix;
    // 原图片
    private Bitmap srcBitmap;

    //是否可旋转
    private boolean rotatable;

    public TouchImageView(Context context) {
        this(context, null);
    }

    public TouchImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setScaleType(ScaleType.MATRIX);
        paintEdge = new Paint();
        PathEffect effect = new DashPathEffect(new float[]{6 * ViewUtils.ONE_DP, 2 * ViewUtils.ONE_DP}, 1);
        paintEdge.setColor(Color.WHITE);
        paintEdge.setPathEffect(effect);
        paintEdge.setAntiAlias(true);
        mPath = new Path();
    }

    public Matrix getMatrix() {
        return drawMatrix;
    }

    public Bitmap getSrcImage() {
        return srcBitmap;
    }

    public boolean isRotatable() {
        return rotatable;
    }

    public void setRotatable(boolean rotatable) {
        this.rotatable = rotatable;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(getSrcImage(), getMatrix(), null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);
        boolean isStickerOnEdit = true;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                // 平移手势验证
//                if (isInStickerArea(event.getX(), event.getY())) {
                mode = TRANS;
                downMatrix.set(getMatrix());
                Log.d("onTouchEvent", "平移手势");
//                } else {
//                    isStickerOnEdit = false;
//                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN: // 多点触控
                mode = ZOOM_MULTI;
                oldDistance = getMultiTouchDistance(event);
                oldRotation = getMultiTouchRotation(event);
                midPoint = getMidPoint(event);
                downMatrix.set(getMatrix());
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == ZOOM_MULTI) {
//                    moveMatrix.set(downMatrix);
//                    float scale = getMultiTouchDistance(event) / oldDistance;
//                    if (isRotatable()) {
//                        float deltaRotation = getMultiTouchRotation(event) - oldRotation;
//                        moveMatrix.postRotate(deltaRotation, midPoint.x, midPoint.y);
//                    }
//                    moveMatrix.postScale(scale, scale, midPoint.x, midPoint.y);
//                    getMatrix().set(moveMatrix);
//
//                    float afaterWidth = scale * minWidth;
//                    float afterHeight = scale * minWidth;
//                    if (afaterWidth >= minWidth && afterHeight >= minHeight) {
//                        imageCurrentWidth = afaterWidth;
//                        imageCurrentHeight = afterHeight;
//                        invalidate();
//                        if (Log.DEBUG) {
//                            Log.d("TouchImageView", "onTouchEvent--------缩放后width=" + imageCurrentWidth + "  height=" + imageCurrentHeight + "   minWidth=" + minWidth + "   minHeight=" + minHeight + "  scale=" + scale);
//                        }
//                    } else {
//                        if (Log.DEBUG) {
//                            Log.d("TouchImageView", "onTouchEvent--------最小了 没法缩放了width=" + imageCurrentWidth + "  height=" + imageCurrentHeight + "   minWidth=" + minWidth + "   minHeight=" + minHeight + "  scale=" + scale);
//
//                        }
//                    }


                } else if (mode == TRANS) {
                    moveMatrix.set(downMatrix);
                    float x = (event.getX() - downX);
                    float y = (event.getY() - downY);

                    moveMatrix.postTranslate(event.getX() - downX, event.getY() - downY);
                    getMatrix().set(moveMatrix);
                    invalidate();
                    if (Log.DEBUG) {
                        Log.d("TouchImageView", "onTouchEvent--------平移  downMatrix=" + downMatrix.toString() + "  moveMatrix=" + moveMatrix + "  x=" + x + "  y=" + y);
                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                mode = NONE;
                midPoint = null;
                imageMidPoint = null;
                closeMidPoint = null;
                break;
            default:
                break;
        }
        return isStickerOnEdit;
    }

    private boolean isInStickerArea(float x, float y) {
        float[] points = getBitmapPoints(getSrcImage(), getMatrix());
//        float x = event.getX();
//        float y = event.getY();

        //对角线ad,触摸点p, 角pad<45 ,角pda<45
        //dp *  dp = ap * ap + ad * ad - 2 * ap *  ad * cos<pad
        //ap *  ap = dp * dp + ad * ad - 2 * dp * ad * cos<pda

        if (!ArrayUtils.isEmpty(points) && points.length == 8) {

            float apap = (x - points[0]) * (x - points[0]) + (y - points[1]) * (y - points[1]);
            float dpdp = (x - points[6]) * (x - points[6]) + (y - points[7]) * (y - points[7]);
            float adad = (points[0] - points[6]) * (points[0] - points[6]) + (points[1] - points[7]) * (points[1] - points[7]);


            double pad = Math.abs(Math.acos((apap + adad - dpdp) / (2 * Math.sqrt(apap) * Math.sqrt(adad))));
            double pda = Math.abs(Math.acos((dpdp + adad - apap) / (2 * Math.sqrt(dpdp) * Math.sqrt(adad))));

            return pad < Math.PI / 4 && pda < Math.PI / 4;

        }

        return false;
    }

    /**
     * 将matrix的点映射成坐标点
     *
     * @return
     */
    private float[] getBitmapPoints(Bitmap bitmap, Matrix matrix) {
        float[] dst = new float[8];
        float[] src = new float[]{
                0, 0,
                bitmap.getWidth(), 0,
                0, bitmap.getHeight(),
                bitmap.getWidth(), bitmap.getHeight()
        };
        matrix.mapPoints(dst, src);
        return dst;
    }

    /**
     * 获取手势中心点
     *
     * @param event
     */
    private PointF getMidPoint(MotionEvent event) {
        PointF point = new PointF();
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
        return point;
    }

    /**
     * 获取手指的旋转角度
     *
     * @param event
     * @return
     */
    private float getSpaceRotation(MotionEvent event, PointF imageMidPoint) {
        double deltaX = event.getX(0) - imageMidPoint.x;
        double deltaY = event.getY(0) - imageMidPoint.y;
        double radians = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radians);
    }

    /**
     * 【多点缩放】获取手指间的距离
     *
     * @param event
     * @return
     */
    private float getMultiTouchDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * 【多点旋转】获取手指间的角度
     *
     * @param event
     * @return
     */
    private float getMultiTouchRotation(MotionEvent event) {
        float deltaX = event.getX(0) - event.getX(1);
        float deltaY = event.getY(0) - event.getY(1);
        double radians = Math.atan2(deltaY, deltaX);
        return (float) Math.toDegrees(radians);
    }

    @Override
    public void setImageResource(int resId) {
        setImageBitmap(BitmapFactory.decodeResource(context.getResources(), resId));
    }


    @Override
    public void setImageBitmap(Bitmap bm) {

        this.srcBitmap = bm;
        this.drawMatrix = new Matrix();

        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();

        float scale1 = VIEW_HEIGHT / height;
        float scale2 = VIEW_WIDTH / width;
        float scale = Math.max(scale1, scale2);
        if (height < VIEW_HEIGHT || width < VIEW_WIDTH) {
            drawMatrix.setScale(scale, scale);
        }
        imageCurrentWidth = scale * width;
        imageCurrentHeight = scale * height;
        minWidth = scale * width;
        minHeight = scale * height;
        if (Log.DEBUG) {
            Log.d("TouchImageView", "setImageBitmap--------width=" + imageCurrentWidth + "  height=" + imageCurrentHeight);
        }
    }
}
