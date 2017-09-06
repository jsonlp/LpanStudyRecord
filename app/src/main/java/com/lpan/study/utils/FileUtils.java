package com.lpan.study.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.lpan.study.context.AppContext;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by lpan on 2017/1/16.
 */

public class FileUtils {

    public static String readTextFile(String filePath) {
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new FileReader(filePath));
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ((line = bReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean saveContent(String data, String fileName) {
        boolean result = Boolean.FALSE;

        ByteArrayInputStream inputStream = null;
        BufferedOutputStream out = null;

        try {
            inputStream = new ByteArrayInputStream(data.getBytes());
            out = new BufferedOutputStream(AppContext.getContext()
                    .openFileOutput(fileName, Context.MODE_PRIVATE));
            int lg = -1;
            byte buffer[] = new byte[8 * 1024];
            while ((lg = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, lg);
            }
        } catch (Exception e) {
            result = Boolean.TRUE;
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (out != null) {
                    out.close();
                }

            } catch (IOException e) {
                result = Boolean.TRUE;
                e.printStackTrace();
            }
        }

        return result;
    }


    public static boolean writeTextFile(String content, String fileName) {
        BufferedWriter bWriter = null;
        try {
            bWriter = new BufferedWriter(new FileWriter(fileName, true));
            bWriter.write(content);
            bWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
    获取本地视频的缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height,
                                           int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    public static File saveBitmap(Bitmap mBitmap, File file, boolean isNeedRecycle, int type) {
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
                mBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            } else {
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
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

    public static File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp" + "/" + "videos/");
        if (dir != null && dir.exists()) {
            return dir;
        }
        return null;
    }

    public static File getImageFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp");
        if (dir != null && dir.exists()) {
            return dir;
        }
        return null;
    }

    @SuppressLint("NewApi")
    public static String getImagePath(Uri uri) {
        String scheme = uri.getScheme();
        String imagePath = uri.getPath();
        if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            //直接就是uri.getPath
        } else if (Utils.hasKitkat()
                && DocumentsContract.isDocumentUri(AppContext.getContext(), uri)) {
            try {
                String wholeID = DocumentsContract.getDocumentId(uri);
                String id = wholeID.split(":")[1];
                String[] column = {MediaStore.Images.Media.DATA};
                String sel = MediaStore.Images.Media._ID + "=?";
                Cursor cursor = AppContext
                        .getContext()
                        .getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel,
                                new String[]{id}, null);
                int columnIndex = cursor.getColumnIndex(column[0]);
                if (cursor.moveToFirst()) {
                    imagePath = cursor.getString(columnIndex);
                }
                cursor.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            //这里只处理图片
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = AppContext.getContext().getContentResolver()
                    .query(uri, proj, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                imagePath = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
            }
            if (cursor != null) {
                cursor.close();
            }

        }
        return imagePath;
    }

}
