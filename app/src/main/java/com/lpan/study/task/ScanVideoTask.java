package com.lpan.study.task;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.lpan.study.context.AppContext;
import com.lpan.study.model.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/11/23.
 */

public class ScanVideoTask extends AsyncTask<Void, Void, List<VideoInfo>> {
    @Override
    protected List<VideoInfo> doInBackground(Void... params) {

        return scanTask();
    }

    private ArrayList<VideoInfo> scanTask() {
        String[] thumbColumns = new String[]{
                MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Thumbnails.VIDEO_ID
        };

        String[] mediaColumns = new String[]{
                MediaStore.Video.Media.DATA,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.TITLE,
                MediaStore.Video.Media.MIME_TYPE,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DATE_MODIFIED,
                MediaStore.Video.Media.WIDTH,
                MediaStore.Video.Media.HEIGHT


        };

        //首先检索SDcard上所有的video
        ContentResolver contentResolver = AppContext.getContext().getContentResolver();
        String videoSortOrder = MediaStore.Video.Media.DATE_MODIFIED + " DESC";

        Cursor cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, videoSortOrder);

        ArrayList<VideoInfo> videoList = new ArrayList<>();
        String videoPath = "";
        if (cursor.moveToFirst()) {
            do {
                VideoInfo info = new VideoInfo();
                videoPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                info.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                info.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)));
                info.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                info.setCreateTime(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED)));
                info.setWidth(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)));
                info.setHeight(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)));


                //获取当前Video对应的Id，然后根据该ID获取其Thumb
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                String selection = MediaStore.Video.Thumbnails.VIDEO_ID + "=?";
                String[] selectionArgs = new String[]{
                        id + ""
                };
                Cursor thumbCursor = contentResolver.query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbColumns, selection, selectionArgs, null);

                if (thumbCursor.moveToFirst()) {
                    info.setThumb(thumbCursor.getString(thumbCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA)));
                } else {// 没有缩略图 自己生成
                    MediaStore.Video.Thumbnails.getThumbnail(contentResolver,
                            id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
                }

                //然后将其加入到videoList
                videoList.add(info);

            } while (cursor.moveToNext());
        }

        return videoList;

    }
}
