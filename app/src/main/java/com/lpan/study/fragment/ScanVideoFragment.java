package com.lpan.study.fragment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.task.ScanVideoTask;
import com.lpan.study.utils.Utils;
import com.lpan.R;
import com.lpan.study.view.smilefaceview.SmileLoadingView;

import java.io.File;
import java.util.List;

/**
 * Created by lpan on 2017/2/9.
 */

public class ScanVideoFragment extends BaseFragment {

    private ListView mListView;

    private SmileLoadingView mSmileLoadingView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_scan_gallery;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
        mSmileLoadingView = (SmileLoadingView) view.findViewById(R.id.smileloadinbg);

    }

    @Override
    protected void initData() {
        super.initData();

        new ScanVideoTask(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mSmileLoadingView.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(List<VideoInfo> videoInfos) {
                super.onPostExecute(videoInfos);
                mSmileLoadingView.setVisibility(View.GONE);

                //然后需要设置ListView的Adapter了，使用我们自定义的Adatper
                mListView.setAdapter(new VideoAdapter(getActivity(), videoInfos));
            }
        }.execute();
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
            holder.size.setText(Utils.formatSizeToMB(videoInfo.getSize()) + "MB");

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
