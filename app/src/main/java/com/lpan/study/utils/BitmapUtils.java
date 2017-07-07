package com.lpan.study.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lpan on 2017/3/9.
 */

public class BitmapUtils {

    public static Bitmap compressPhotoFileToBitmap(String filePath, int width, int height) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;

        BitmapFactory.decodeFile(filePath, opts);
        opts.inSampleSize = calculateInSampleSize(opts, width, height);
        opts.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(filePath, opts);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }

        return null;
    }

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
}
