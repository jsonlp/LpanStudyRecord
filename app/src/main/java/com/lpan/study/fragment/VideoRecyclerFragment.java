package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpan.study.adapter.RecyclerViewAdapter;
import com.lpan.study.adapter.RecyclerViewWrapLayoutManager;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.view.TextureVideoView;
import com.lpan.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/5/17.
 */

public class VideoRecyclerFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    public static final String TAG = VideoRecyclerFragment.class.getSimpleName();

    private List<VideoInfo> mVideoInfos;

    private VideoRecyclerAdapter mAdapter;

    private static final String PATH = "video.mp4";


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_recycler;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

    }

    @Override
    protected void initData() {
        super.initData();
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setPath(getVideoPath());
        mVideoInfos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mVideoInfos.add(videoInfo);
        }
        getAdapter().addItem(mVideoInfos);

        RecyclerViewWrapLayoutManager manager = new RecyclerViewWrapLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.setAdapter(getAdapter());
    }

    public VideoRecyclerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new VideoRecyclerAdapter(getActivity());
        }
        return mAdapter;
    }

    public String getVideoPath() {
        return getVideoFileDir().getAbsolutePath() + File.separator + PATH;
    }

    public File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp" + "/");
        if (dir != null && dir.exists()) {
            return dir;
        }
        return null;
    }


    class VideoRecyclerAdapter extends RecyclerViewAdapter<VideoInfo, RecyclerView.ViewHolder> {


        public VideoRecyclerAdapter(Context context) {
            super(context);
        }

        @Override
        public int getItemCount() {
            return super.getItemCount();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_video_list_player, parent, false);
            return new VideoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.bindView(mList, position);
        }


        class VideoViewHolder extends RecyclerView.ViewHolder {

            TextureVideoView mTextureVideoView;

            TextView mTextView;

            public VideoViewHolder(View itemView) {
                super(itemView);

                mTextureVideoView = (TextureVideoView) itemView.findViewById(R.id.textureview);
                mTextView = (TextView) itemView.findViewById(R.id.order);
            }

            public void bindView(List<VideoInfo> list, final int position) {
                final VideoInfo videoInfo = list.get(position);
//                playVideo(mTextureVideoView, videoInfo.getPath());

                mTextView.setText(position + "");
                mTextureVideoView.setOnStateChangeListener(new TextureVideoView.OnStateChangeListener() {
                    @Override
                    public void onSurfaceTextureDestroyed(SurfaceTexture surface) {
                        mTextureVideoView.stop();
                        Log.d("lp-test", "-----setOnStateChangeListener   onSurfaceTextureDestroyed   position=" + position);

                    }

                    @Override
                    public void onBuffering() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onBuffering   position=" + position);

                    }

                    @Override
                    public void onPlaying() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onBuffering   position=" + position);

                    }

                    @Override
                    public void onSeek(int max, int progress) {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onSeek   position=" + position);

                    }

                    @Override
                    public void onStop() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onStop   position=" + position);

                    }

                    @Override
                    public void onPause() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onPause   position=" + position);

                    }

                    @Override
                    public void onTextureViewAvaliable() {
                        if (!mTextureVideoView.isPlaying()) {
                            playVideo(mTextureVideoView, videoInfo.getPath());
                        }
                        Log.d("lp-test", "-----setOnStateChangeListener   onTextureViewAvaliable   position=" + position);

                    }

                    @Override
                    public void playFinish() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   playFinish   position=" + position);

                    }

                    @Override
                    public void onPrepare() {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onPrepare   position=" + position);

                    }

                    @Override
                    public void onVideoSizeChanged(int vWidth, int vHeight) {
//                        Log.d("lp-test", "-----setOnStateChangeListener   onVideoSizeChanged   position=" + position);

                    }
                });
            }

            private void playVideo(TextureVideoView videoView, String path) {
                if (videoView == null) {
                    return;
                }
                if (videoView.getState() == TextureVideoView.MediaState.INIT || videoView.getState() == TextureVideoView.MediaState.RELEASE) {
                    videoView.setPath(path, true, 0);
                } else if (videoView.getState() == TextureVideoView.MediaState.PAUSE) {
                    videoView.start();
                } else if (videoView.getState() == TextureVideoView.MediaState.PLAYING) {
                    videoView.pause();
                } else if (videoView.getState() == TextureVideoView.MediaState.PREPARING) {
                    videoView.stop();
                }
            }
        }
    }

}
