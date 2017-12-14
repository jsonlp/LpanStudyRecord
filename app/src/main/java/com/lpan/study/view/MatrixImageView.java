package com.lpan.study.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.lpan.study.context.AppContext;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.ViewUtils;

import java.util.Arrays;

/**
 * Created by liaopan on 2017/12/14 11:04.
 */

public class MatrixImageView extends ImageView {

//    public static final int TOP = ViewUtils.getStatusHeight(AppContext.getContext());
    public static final int TOP = 0;

    public static final int MODE_NONE = 0;

    public static final int MODE_TRANSLATE = 1;

    public static final int MODE_SCALE = 2;

    private int currentMode = MODE_NONE;

    private Bitmap srcBitmap;

    private Matrix matrix;

    private Matrix saveMatrix;

    private PointF startPoint;

    private PointF middlePoint;

    private float oldDistance;

    private float oldAngle;

    private boolean touchable;

    private float minWidth;

    private float minHeight;

    private float viewWidth;

    private float viewHeight;

    public MatrixImageView(Context context) {
        super(context);
        init();
    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public MatrixImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        matrix = new Matrix();
        saveMatrix = new Matrix();
        startPoint = new PointF();
        middlePoint = new PointF();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(srcBitmap, matrix, null);
        super.onDraw(canvas);
    }

//    @Override
//    public Matrix getMatrix() {
//        return matrix;
//    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
        invalidate();
    }

    public boolean isTouchable() {
        return touchable;
    }

    public void setTouchable(boolean touchable) {
        this.touchable = touchable;
    }

    public void setBitmap(Bitmap bitmap, float viewWidth, float viewHeight) {
        this.viewWidth = viewWidth;
        this.viewHeight = viewHeight;
        setImageBitmap(bitmap);
    }

    /**
     * 用上面的方法,指定view的宽高
     *
     * @param bm
     */
    @Deprecated
    @Override
    public void setImageBitmap(Bitmap bm) {
        srcBitmap = bm;

        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();

        float scale1 = viewHeight / height;
        float scale2 = viewWidth / width;
        float scale = Math.max(scale1, scale2);
        if (height < viewHeight || width < viewWidth) {
            matrix.setScale(scale, scale);
        }
        minWidth = scale * width;
        minHeight = scale * height;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!touchable) {
            return super.onTouchEvent(event);
        }
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                saveMatrix.set(matrix);
                startPoint.set(event.getX(), event.getY());
                currentMode = MODE_TRANSLATE;

                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                oldDistance = getDistance(event);
                oldAngle = getDegree(event);
                if (oldDistance > 10f) {
                    saveMatrix.set(matrix);
                    middlePoint = getMiddlePoint(event);
                    currentMode = MODE_SCALE;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (currentMode == MODE_TRANSLATE) {
                    float dx = event.getX() - startPoint.x;
                    float dy = event.getY() - startPoint.y;
                    if (canHTranslate(srcBitmap, matrix, dx) && canVTranslate(srcBitmap, saveMatrix, dy)) {

                    } else if (canHTranslate(srcBitmap, saveMatrix, dx)) {
                        dy = 0;
                    } else if (canVTranslate(srcBitmap, saveMatrix, dy)) {
                        dx = 0;
                    } else {
                        dx = 0;
                        dy = 0;
                    }
                    if (Log.DEBUG) {
//                        Log.d("MatrixImageView", "onTouchEvent--------can not translate dx=" + dx + "   dy=" + dy + "  width=" + getPreviewWidthHeight(srcBitmap, matrix)[0] + "    height=" + getPreviewWidthHeight(srcBitmap, matrix)[1]);
                    }
                    if (dx != 0 || dy != 0) {
                        matrix.set(saveMatrix);
                        matrix.postTranslate(dx, dy);
                        invalidate();
                    }
                } else if (currentMode == MODE_SCALE) {
                    float newDistance = getDistance(event);
                    float scale = newDistance / oldDistance;

                    if (canScale(srcBitmap, matrix, scale) && newDistance > 10f) {
                        matrix.set(saveMatrix);
                        matrix.postScale(scale, scale, middlePoint.x, middlePoint.y);
                        invalidate();
                        if (Log.DEBUG) {
                            Log.d("MatrixImageView", "onTouchEvent--------can scale width=" + getPreviewWidthHeight(srcBitmap, matrix)[0] + "    height=" + getPreviewWidthHeight(srcBitmap, matrix)[1]);
                        }
                    } else {
                        if (Log.DEBUG) {
                            Log.d("MatrixImageView", "onTouchEvent--------can not scale  width=" + getPreviewWidthHeight(srcBitmap, matrix)[0] + "    height=" + getPreviewWidthHeight(srcBitmap, matrix)[1]);
                        }
                    }

                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                currentMode = MODE_NONE;
                break;
        }
        return true;
    }

    /**
     * @describe 是否可以垂直移动
     * @author lpan
     * @time 2017/12/14 下午4:34
     */
    private boolean canVTranslate(Bitmap bitmap, Matrix matrix, float dy) {
        float[] points = getBitmapPoints(bitmap, matrix);
        if (points[1] + dy >= 0) {
            Log.d("MatrixImageView", "canVTranslate--------1 point=" + points[1] + " dy=" + dy);

            return false;
        } else if (points[5] + dy <= viewHeight + TOP) {
            Log.d("MatrixImageView", "canVTranslate--------2 point=" + points[3] + " dy=" + dy);

            return false;
        }
        Log.d("MatrixImageView", "canVTranslate--------3");
        return true;
    }

    /**
     * @describe 是否可以水平移动
     * @author lpan
     * @time 2017/12/14 下午4:34
     */
    private boolean canHTranslate(Bitmap bitmap, Matrix matrix, float dx) {
        float[] points = getBitmapPoints(bitmap, matrix);
        if (points[0] + dx >= 0) {
            Log.d("MatrixImageView", "canHTranslate--------1  point=" + points[0] + " dx=" + dx);
            return false;
        } else if (points[2] + dx <= viewWidth) {
            Log.d("MatrixImageView", "canHTranslate--------2 point=" + points[4] + " dx=" + dx);
            return false;
        }
        Log.d("MatrixImageView", "canHTranslate--------3");
        return true;
    }

    private boolean canScale(Bitmap bitmap, Matrix matrix, float scale) {
        float[] previewWidthHeight = getPreviewWidthHeight(bitmap, matrix);
        if (Log.DEBUG) {
            Log.d("MatrixImageView", "canScale--------preview width=" + previewWidthHeight[0] + " preview height=" + previewWidthHeight[1] + " minWidth=" + minWidth + "  minHeight=" + minHeight + "  scale=" + scale);
        }
        //缩放后宽高小于最小值
        if (previewWidthHeight[0] * scale <= minWidth || previewWidthHeight[1] * scale <= minHeight) {
            return false;
        }

        //缩放后会有空白
        if (scale < 1) {
            float[] points = getBitmapPoints(bitmap, matrix);

            if (points[0] >= 0 || points[1] >= 0 || points[2] <= viewWidth || points[5] <= viewHeight + TOP) {
                if (Log.DEBUG) {
                    Log.e("MatrixImageView", "canScale--------1" + Arrays.toString(points) + "  viewWidth=" + viewWidth + "  viewHeight=" + viewHeight);
                }
                return false;
            } else {
                if (Log.DEBUG) {
                    Log.e("MatrixImageView", "canScale--------2" + Arrays.toString(points) + "  viewWidth=" + viewWidth + "  viewHeight=" + viewHeight);
                }
            }
        } else {
            Log.e("MatrixImageView", "canScale--------3");

        }
        return true;
    }

    private float getDistance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * x);
    }

    private float getDegree(MotionEvent event) {
        return (float) Math.atan((event.getY(1) - event.getY(0)) / (event.getX(1) - event.getX(0)) * 180f);
    }

    private PointF getMiddlePoint(MotionEvent event) {
        PointF point = new PointF();
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
        return point;
    }

    /**
     * @return
     * @describe 将matrix的点映射成坐标点
     * @author lpan
     * @time 2017/12/14 下午4:11
     * <p>
     * A------------------------------------B
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * |                                    |
     * C------------------------------------D
     * <p>
     * A(dts[0],dts[1])
     * <p>
     * B(dts[2],dts[3])
     * <p>
     * C(dts[4],dts[5])
     * <p>
     * D(dts[6],dts[7])
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
        if (Log.DEBUG) {
            Log.d("MatrixImageView", "getBitmapPoints--------dst=" + Arrays.toString(dst));
        }
        return dst;
    }

    /**
     * 预览转换后的图片宽高
     *
     * @return
     */
    public float[] getPreviewWidthHeight(Bitmap bitmap, Matrix matrix) {
        float[] widthHeight = new float[2];
        float[] bitmapPoints = getBitmapPoints(bitmap, matrix);
        float width = bitmapPoints[4] - bitmapPoints[2];
        float height = bitmapPoints[2] - bitmapPoints[0];
        widthHeight[0] = Math.abs(width);
        widthHeight[1] = Math.abs(height);
        return widthHeight;
    }

}
