package com.lpan.study.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lpan.study.view.TextureVideoView;
import com.test.lpanstudyrecord.R;

import java.util.List;

/**
 * Created by lpan on 2017/2/8.
 */

public class VideoPlayAdapter extends AbstractAdapter<String> {

    private Context mContext;

    public VideoPlayAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void clearItem() {
        mList.clear();
    }

    @Override
    public void addItem(String s) {
        mList.add(s);
    }

    @Override
    public void addItem(List<String> list) {
        mList.addAll(list);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            viewholder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_list, parent, false);
            viewholder.mTextureVideoView = (TextureVideoView) convertView.findViewById(R.id.textureview);
            viewholder.mPlayButton = (ImageView) convertView.findViewById(R.id.play_button);
            viewholder.mSeekBar = (SeekBar) convertView.findViewById(R.id.seekbar);
            viewholder.mLocalOrNet = (TextView) convertView.findViewById(R.id.text);
            viewholder.mTimeRecord = (TextView) convertView.findViewById(R.id.duration);

            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder) convertView.getTag();

        }

        bindView(viewholder,mList,position);
        return convertView;
    }

    private void bindView(ViewHolder holder,List<String> list, int position) {

    }

    class ViewHolder {

        TextureVideoView mTextureVideoView;

        ImageView mPlayButton;

        SeekBar mSeekBar;

        TextView mLocalOrNet, mTimeRecord;
    }
}
