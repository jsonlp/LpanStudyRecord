package com.lpan.study.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.lpan.study.context.AppContext;
import com.lpan.study.model.ImageInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/11/14.
 */

public class ScanUtils {

    public static List<ImageInfo> scanImages(){
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.SIZE,
                MediaStore.Images.Media.DATE_MODIFIED,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT
        };
        String[] selectionArgs = getSelectionArgs();
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED + " DESC"/* LIMIT " + LIMIT*/;
        Cursor cursor = null;
        List<ImageInfo> imageInfoList = new ArrayList<ImageInfo>();

        try {
            ContentResolver contentResolver = AppContext.getContext().getContentResolver();
            cursor = contentResolver.query(uri, projection, getSelection(), selectionArgs,
                    sortOrder);
            if (cursor != null) {
                String firstImage = null;
                while (cursor.moveToNext()) {
                    String path = cursor.getString(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                    int size = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.SIZE));
                    int width = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH));
                    int height = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT));

                    long createTime = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED));

                    if (size <= 0 || TextUtils.isEmpty(path)) {
                        continue;
                    }
//                    path = FileUtils.getImagePath(Uri.parse(path));
                    if (TextUtils.isEmpty(path) || !new File(path).exists()) {
                        continue;
                    }

                    ImageInfo imageInfo = new ImageInfo();
                    imageInfo.setUrl(path);
                    imageInfoList.add(imageInfo);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return imageInfoList;
    }

    protected static String[] getSelectionArgs() {
        return new String[]{"image/jpg", "image/jpeg", "image/png", "image/bmp", "image/gif"};
    }

    protected static String getSelection() {
        StringBuilder selection = new StringBuilder();
        selection.append("(").append(MediaStore.Images.Media.MIME_TYPE).append("=? OR ")
                .append(MediaStore.Images.Media.MIME_TYPE).append("=? OR ")
                .append(MediaStore.Images.Media.MIME_TYPE).append("=? OR ")
                .append(MediaStore.Images.Media.MIME_TYPE).append("=? OR ")
                .append(MediaStore.Images.Media.MIME_TYPE).append("=?)");
        return selection.toString();
    }
}
