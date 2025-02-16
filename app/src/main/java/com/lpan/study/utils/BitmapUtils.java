package com.lpan.study.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.lpan.study.context.AppContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lpan on 2017/3/9.
 */

public class BitmapUtils {


    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                             int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;

        if ((height / width) > 2 || (height / width) > 2) {
            int inSampleSize = 1;
            while ((height / inSampleSize) > 720 && (width / inSampleSize) > 720) {
                inSampleSize *= 2;
            }

            return inSampleSize;
        }

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

//            final int halfHeight = height / 2;
//            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((height / inSampleSize) > reqHeight || (width / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static File saveBitmap(Bitmap mBitmap, File file, boolean isNeedRecycle, int type, int quality) {
        if (mBitmap == null) {
            return null;
        }

        if (!file.exists()) {
            File parentFile = file.getParentFile();
            parentFile.mkdirs();
            try {
                file.createNewFile();
            } catch (Exception e) {
                return null;
            }

        } else {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            if (type == 0) {
                mBitmap.compress(Bitmap.CompressFormat.PNG, quality, fos);
            } else {
                mBitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            }
            if (isNeedRecycle) {
                mBitmap.recycle();
            }
            fos.flush();
            fos.close();
            return file;
        } catch (FileNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int newWidth = w;
        int newHeight = h;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);

        return resizedBitmap;
    }

    /**
     * @param bitmap     原图
     * @param edgeLength 希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg >= edgeLength && heightOrg >= edgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    public static Bitmap compressPhotoFileToBitmap(String filePath, int max, int min) {

        int tempWidth = 2 * max;
        int tempHeight = 2 * min;

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, opts);
        opts.inSampleSize = calculateInSampleSize(opts, tempWidth, tempHeight);
        opts.inJustDecodeBounds = false;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath, opts);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap bitmap1 = null;

            /**
             * 长宽比大于2
             */
            if (min > 0 && (width / height > 2 || height / width > 2)) {
                //最短边>设定值,需要压缩
                if ((width > min && height > min)) {
                    bitmap1 = resizeFitMin(bitmap, min);
                    bitmap.recycle();
                } else {
                    return bitmap;
                }
            } else {
                //有边长>最大值
                if (width > max || height > max) {
                    bitmap1 = resizeFitMax(bitmap, max);
                    bitmap.recycle();
                } else {
                    return bitmap;
                }
            }
            return bitmap1;
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }

        return null;
    }

    /**
     * @param bitmap
     * @param max    最长边长度
     * @return
     */
    public static Bitmap resizeFitMax(Bitmap bitmap, int max) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int old = Math.max(width, height);
        float scale = (float) max / old;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);

        return resizedBitmap;
    }

    public static Bitmap resizeFitMin(Bitmap bitmap, int min) {
        Bitmap BitmapOrg = bitmap;
        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();
        int old = Math.min(width, height);
        float scale = (float) min / old;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);

        return resizedBitmap;
    }

    public static Bitmap scaleBitmapFullScreenWidth(Bitmap origin) {
        if (origin == null) {
            return null;
        }
        int screenWidth = ViewUtils.getScreenWidth(AppContext.getContext());
        int oldWidth = origin.getWidth();
        int oldHeight = origin.getHeight();
        float scale = (float) screenWidth / oldWidth;

        if (oldHeight != screenWidth) {
            Matrix matrix = new Matrix();
            matrix.postScale(scale,scale);
            Bitmap bitmap = Bitmap.createBitmap(origin,0,0,oldWidth,oldHeight,matrix,true);
            if (!origin.isRecycled()) {
                origin.recycle();
                origin=null;
            }
            return bitmap;
        }
        return null;
    }


    public static Bitmap readBitmapById(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }
}
