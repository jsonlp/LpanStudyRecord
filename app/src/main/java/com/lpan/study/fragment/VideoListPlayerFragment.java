package com.lpan.study.fragment;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.lpan.study.adapter.AbstractAdapter;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.model.VideoInfo;
import com.lpan.study.view.TextureVideoView;
import com.test.lpanstudyrecord.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lpan on 2017/4/10.
 */

public class VideoListPlayerFragment extends BaseFragment implements AbsListView.OnScrollListener {

    public static final String TAG = VideoListPlayerFragment.class.getSimpleName();

    private ListView mListView;

    private List<VideoInfo> mVideoInfos;

    private VideoListPlayAdapter mAdapter;

    private static final String PATH = "video.mp4";

    private boolean mStopScroll;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_list_player;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setRecyclerListener(new AbsListView.RecyclerListener() {
            @Override
            public void onMovedToScrapHeap(View view) {

            }
        });

        mListView.setOnScrollListener(this);
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
        mListView.setAdapter(getAdapter());

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                int childCount = view.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = view.getChildAt(i);
                    if (childAt != null) {
                        if (childAt.getTag() != null && childAt.getTag() instanceof ViewHolder) {
                            ViewHolder holder = (ViewHolder) childAt.getTag();
                            if (holder != null) {


                                if (scrollState == SCROLL_STATE_TOUCH_SCROLL) {
                                    Log.d("VideoListPlayerFragment", "-----onScrollStateChanged-----prepare scroll");
                                    //暂停所有视频
                                    if (holder.mTextureVideoView.getState() == TextureVideoView.MediaState.PLAYING) {
                                        holder.mTextureVideoView.pause();
                                    }
                                } else if (scrollState == SCROLL_STATE_FLING) {
                                    Log.d("VideoListPlayerFragment", "-----onScrollStateChanged-----fling");

                                } else if (scrollState == SCROLL_STATE_IDLE) {
                                    Log.d("VideoListPlayerFragment", "-----onScrollStateChanged-----idle");
                                    //开始播放视频
                                    if (holder.mTextureVideoView.getState() == TextureVideoView.MediaState.PAUSE) {
                                        holder.mTextureVideoView.start();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    public VideoListPlayAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new VideoListPlayAdapter(getActivity());
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            mStopScroll = true;
        } else {
            mStopScroll = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mStopScroll) {
            View childAt = view.getChildAt(1);
            if (childAt != null) {
                Boolean visible = checkIsVisible(AppContext.getContext(), childAt);
//            Log.d(TAG, "onScroll: first=" + 0 + "   visible=" + visible);
            }
        }
    }

    public Boolean checkIsVisible(Context context, View view) {
        // 如果已经加载了，判断广告view是否显示出来，然后曝光
        int screenWidth = getScreenMetrics(context).x;
        int screenHeight = getScreenMetrics(context).y;
        Rect rect = new Rect(0, 0, screenWidth, screenHeight);
        int[] location = new int[2];
        view.getLocationInWindow(location);

        Log.d(TAG, "checkIsVisible: x=" + location[0] + "  y=" + location[1]);

        if (view.getLocalVisibleRect(rect)) {
            return true;
        } else {
            //view已不在屏幕可见区域;
            return false;
        }
    }

    /**
     * 获取屏幕宽度和高度，单位为px
     *
     * @param context
     * @return
     */
    public Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    public void playVideo(TextureVideoView videoView, String path) {
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

    class VideoListPlayAdapter extends AbstractAdapter<VideoInfo> {

        private Context mContext;

        public VideoListPlayAdapter(Context context) {
            mContext = context;
            mList = new ArrayList<>();
        }

        @Override
        public void clearItem() {
            mList.clear();
        }

        @Override
        public void addItem(VideoInfo videoInfo) {
            mList.add(videoInfo);
        }

        @Override
        public void addItem(List<VideoInfo> list) {
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
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_list_player, parent, false);
                viewholder.mTextureVideoView = (TextureVideoView) convertView.findViewById(R.id.textureview);
                viewholder.mTextView = (TextView) convertView.findViewById(R.id.order);
                convertView.setTag(viewholder);
            } else {
                viewholder = (ViewHolder) convertView.getTag();

            }

            bindView(viewholder, mList, position);
            return convertView;
        }

        private void bindView(final ViewHolder holder, List<VideoInfo> list, final int position) {
            final VideoInfo videoInfo = list.get(position);
            playVideo(holder.mTextureVideoView, videoInfo.getPath());

            holder.mTextView.setText(position + "");
            if (position % 2 == 1) { //奇数
                holder.mTextureVideoView.setVideoMode(TextureVideoView.CENTER_CROP_MODE);
            } else {
                holder.mTextureVideoView.setVideoMode(TextureVideoView.CENTER_MODE);
            }

            holder.mTextureVideoView.setOnStateChangeListener(new TextureVideoView.OnStateChangeListener() {
                @Override
                public void onSurfaceTextureDestroyed(SurfaceTexture surface) {
                    holder.mTextureVideoView.stop();
                    Log.d("lp-test", "-----setOnStateChangeListener   onSurfaceTextureDestroyed   position=" + position);

                }

                @Override
                public void onBuffering() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onBuffering   position=" + position);

                }

                @Override
                public void onPlaying() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onBuffering   position=" + position);

                }

                @Override
                public void onSeek(int max, int progress) {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onSeek   position=" + position);

                }

                @Override
                public void onStop() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onStop   position=" + position);

                }

                @Override
                public void onPause() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onPause   position=" + position);

                }

                @Override
                public void onTextureViewAvaliable() {
                    if (!holder.mTextureVideoView.isPlaying()) {
                        playVideo(holder.mTextureVideoView, videoInfo.getPath());
                    }
//                    Log.d("lp-test", "-----setOnStateChangeListener   onTextureViewAvaliable   position=" + position);

                }

                @Override
                public void playFinish() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   playFinish   position=" + position);

                }

                @Override
                public void onPrepare() {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onPrepare   position=" + position);

                }

                @Override
                public void onVideoSizeChanged(int vWidth, int vHeight) {
//                    Log.d("lp-test", "-----setOnStateChangeListener   onVideoSizeChanged   position=" + position);
                    holder.mTextureVideoView.getLayoutParams().width = AppContext.getContext().getResources().getDisplayMetrics().widthPixels;
                    holder.mTextureVideoView.getLayoutParams().height = (int) ((float) holder.mTextureVideoView.getLayoutParams().width / (float) vWidth * (float) vHeight);
                }
            });

        }


    }

    class ViewHolder {

        TextureVideoView mTextureVideoView;

        TextView mTextView;

    }
}
