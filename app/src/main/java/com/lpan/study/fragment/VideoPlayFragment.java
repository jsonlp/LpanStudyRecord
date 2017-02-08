package com.lpan.study.fragment;

import android.graphics.SurfaceTexture;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.lpan.study.context.AppContext;
import com.lpan.study.utils.Utils;
import com.lpan.study.view.TextureVideoView;
import com.test.lpanstudyrecord.R;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by lpan on 2017/2/7.
 */

public class VideoPlayFragment extends BaseFragment implements View.OnClickListener, TextureVideoView.OnStateChangeListener, SeekBar.OnSeekBarChangeListener {

    private TextureVideoView mTextureVideoView;

    private ImageView mPlayButton;

    private SeekBar mSeekBar;

    private TextView  mTextView3, mTextView4, mTimeRecord;

    private static final String PATH = "video2.mp4";

    private static final String URL = "http://svideo.spriteapp.com/video/2016/0703/7b5bc740-4134-11e6-ac2b-d4ae5296039d_wpd.mp4";


    private Timer mTimer;

    private TimerTask mTimerTask;

    static final Handler mHandler = new Handler(Looper.getMainLooper()) {
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_play;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mTextureVideoView!=null && mTextureVideoView.isPlaying()){
            mTextureVideoView.stop();
        }
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mTextureVideoView = (TextureVideoView) view.findViewById(R.id.textureview);
        mSeekBar = (SeekBar) view.findViewById(R.id.seekbar);
        mPlayButton = (ImageView) view.findViewById(R.id.play_button);
        mTextView3 = (TextView) view.findViewById(R.id.text3);
        mTextView4 = (TextView) view.findViewById(R.id.text4);
        mTimeRecord = (TextView) view.findViewById(R.id.duration);

        mTextureVideoView.setOnStateChangeListener(this);
        mPlayButton.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mTextView3.setOnClickListener(this);
        mTextView4.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
    }

    private void initTimeTask() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //设置时间
                        mTimeRecord.setText(String.valueOf(Utils.converLongTimeToStr(mTextureVideoView.getCurrentPosition()) + " / " + Utils.converLongTimeToStr(mTextureVideoView.getDuration())));
                        //进度条
                        int progress = mTextureVideoView.getCurrentPosition();
                        mSeekBar.setProgress(progress);
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 0, 1000);
    }

    private void destroyTimeTask() {
        if (mTimer != null && mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    public File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + "Jiemoapp" + "/" + "videos/");
        if (dir != null && dir.exists()) {
            return dir;
        }
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                if (mTextureVideoView.isPlaying()) {
                    mTextureVideoView.pause();
                } else {
                    mTextureVideoView.start();
                }

                break;

            case R.id.text3:
                if(mTextureVideoView.isPlaying()){
                    mTextureVideoView.pause();
                }
                String local = getVideoFileDir().getAbsolutePath() + File.separator + PATH;
                mTextureVideoView.setPath(local, false, 1);
                mPlayButton.setVisibility(View.GONE);
                break;

            case R.id.text4:
                if(mTextureVideoView.isPlaying()){
                    mTextureVideoView.pause();
                }
                String url = URL;
                mTextureVideoView.setPath(url, false, 1);
                mPlayButton.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mTextureVideoView.pause();
        destroyTimeTask();
    }

    @Override
    public void onBuffering() {

    }

    @Override
    public void onPlaying() {
        initTimeTask();
        mSeekBar.setMax(mTextureVideoView.getDuration());
        mPlayButton.setVisibility(View.GONE);

    }

    @Override
    public void onSeek(int max, int progress) {
    }

    @Override
    public void onTextureViewAvaliable() {
    }

    @Override
    public void playFinish() {
        mTextureVideoView.pause();
        mPlayButton.setVisibility(View.VISIBLE);

    }

    @Override
    public void onPrepare() {

    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        int after = seekBar.getProgress();
        int duration = mTextureVideoView.getDuration();
        double percent = after * 0.01;
        int msec = (int) (percent * duration);
        Log.d("lp-test", " -- msec=" + msec);
        mTimeRecord.setText(String.valueOf(Utils.converLongTimeToStr(mTextureVideoView.getCurrentPosition()) + " / " + Utils.converLongTimeToStr(mTextureVideoView.getDuration())));

        mTextureVideoView.seekTo(after);
        if (!mTextureVideoView.isPlaying()) {
            mTextureVideoView.start();
            mPlayButton.setVisibility(View.GONE);
        }
    }
}
