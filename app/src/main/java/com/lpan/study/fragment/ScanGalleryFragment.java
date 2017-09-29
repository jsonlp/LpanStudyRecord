package com.lpan.study.fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.utils.Utils;
import com.test.lpanstudyrecord.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/2/9.
 */

public class ScanGalleryFragment extends BaseFragment {

    private ListView mListView;

    private Cursor cursor;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_scan_gallery;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
    }

    @Override
    protected void initData() {
        super.initData();

        new AsyncTask<Void, Void, ArrayList<VideoInfo>>() {
            @Override
            protected ArrayList<VideoInfo> doInBackground(Void... params) {

                return scanTask();
            }

            @Override
            protected void onPostExecute(ArrayList<VideoInfo> list) {
                super.onPostExecute(list);
                //然后需要设置ListView的Adapter了，使用我们自定义的Adatper
                mListView.setAdapter(new VideoAdapter(getActivity(), list));
            }
        }.execute();
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
        ContentResolver contentResolver = getActivity().getContentResolver();
        String videoSortOrder = MediaStore.Video.Media.DATE_MODIFIED + " DESC";

        cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mediaColumns, null, null, videoSortOrder);

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

//                    ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);
                }

//                MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
//                File file = new File(info.getPath());
//                if (file.exists()) {
//                    File file1 = new File(FileUtils.getVideoFileDir(), id + ".png");
//                    if(file1.exists()){
//                        info.setThumb(file1.getAbsolutePath());
//                    }else{
//                        metadataRetriever.setDataSource(info.getPath());
//                        Bitmap frameAtTime = metadataRetriever.getFrameAtTime();
//                        FileUtils.saveBitmap(frameAtTime, file1, false, 0);
//                        info.setThumb(file1.getAbsolutePath());
//                    }
//                }


                //然后将其加入到videoList
                videoList.add(info);

            } while (cursor.moveToNext());
        }

        return videoList;

    }

    /**
     * 定义一个Adapter来显示缩略图和视频title信息
     *
     * @author Administrator
     */
    static class VideoAdapter extends BaseAdapter {

        private Context context;
        private List<VideoInfo> videoItems;

        public VideoAdapter(Context context, List<VideoInfo> data) {
            this.context = context;
            this.videoItems = data;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return videoItems.size();
        }

        @Override
        public Object getItem(int p) {
            // TODO Auto-generated method stub
            return videoItems.get(p);
        }

        @Override
        public long getItemId(int p) {
            // TODO Auto-generated method stub
            return p;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_scan_gallery, null);
                holder.thumbImage = (ImageView) convertView.findViewById(R.id.image1);
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.size = (TextView) convertView.findViewById(R.id.size);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            final VideoInfo videoInfo = videoItems.get(position);

            if (videoInfo.getThumb() != null) {
                holder.thumbImage.setImageURI(Uri.parse(videoInfo.getThumb()));
            }
            holder.title.setText(Utils.formatTime(videoInfo.getCreateTime() * 1000));
            long time = System.currentTimeMillis();
            holder.size.setText(time + "");
//            holder.title.setText(Utils.formatTime(System.currentTimeMillis()));
            holder.size.setText(Utils.formatSizeToMB(videoInfo.getSize()) + "");

            final ViewHolder finalHolder = holder;
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File file = new File(videoInfo.getPath());
                    if (file != null && file.exists()) {
                        VideoFullScreenFragment.show(context, videoInfo, finalHolder.thumbImage);
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView thumbImage;

            TextView title, size;
        }

    }


}
