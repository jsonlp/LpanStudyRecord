package com.lpan.study.fragment;

import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.lpan.study.contract.VideoDecodeContracts;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.presenter.impl.VideoDecodePresenter;
import com.lpan.study.utils.Utils;
import com.lpan.study.view.TextureVideoView;
import com.lpan.study.view.UnclickSeekBar;
import com.lpan.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by lpan on 2017/2/7.
 */

public class VideoDecodeFragment extends ButterKnifeFragment implements View.OnClickListener, TextureVideoView.OnStateChangeListener, SeekBar.OnSeekBarChangeListener
        , VideoDecodeContracts.View {

    @BindView(R.id.textureview)
    TextureVideoView mTextureVideoView;

    @BindView(R.id.play_button)
    ImageView mPlayButton;

    @BindView(R.id.seekbar)
    UnclickSeekBar mSeekBar;

    @BindView(R.id.duration)
    TextView mTimeRecord;

    @BindView(R.id.button1)
    TextView mTransform;

    @BindView(R.id.hflip)
    TextView mHfilp;

    @BindView(R.id.add_water_mask)
    TextView mAddWaterMask;

    private Timer mTimer;

    private TimerTask mTimerTask;

    private VideoDecodeContracts.Presenter mPresenter;

    static final Handler mHandler = new Handler(Looper.getMainLooper()) {
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_video_play;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new VideoDecodePresenter(this);
        mPresenter.start();
        mPresenter.loadFFmpeg();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.stopVideo();
        mPresenter.destroyFFmpeg();

    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mTextureVideoView.setOnStateChangeListener(this);
        mTextureVideoView.setVideoMode(TextureVideoView.CENTER_MODE);

        mPlayButton.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
        mTransform.setOnClickListener(this);
        mHfilp.setOnClickListener(this);
        mAddWaterMask.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play_button:
                mPresenter.playVideo();
                break;

            case R.id.button1:
                mPresenter.analysisVideo();
                break;

            case R.id.hflip:
                mPresenter.hflipVideo();
                break;

            case R.id.add_water_mask:
                mPresenter.addWaterMask();
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
    public void onVideoSizeChanged(int vWidth, int vHeight) {
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mTextureVideoView.getLayoutParams();
////        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTextureVideoView.getLayoutParams();
//        params.width = getResources().getDisplayMetrics().widthPixels;
//        params.height = (int) ((float) params.width / (float) vWidth * (float) vHeight);
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

    @Override
    public void setPresenter(VideoDecodeContracts.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void playVideo() {
        if (mTextureVideoView.isPlaying()) {
            mTextureVideoView.pause();
        } else {
            String url = "http://v-test.jiemosrc.com/tuJqCRX7sDXjdLiG475ldg.mp4";
//            mTextureVideoView.setPathFromAssets();
            mTextureVideoView.setPath(url,true,1);
            mPlayButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void stopVideo() {
        if (mTextureVideoView != null && mTextureVideoView.isPlaying()) {
            mTextureVideoView.stop();
        }
    }

    @Override
    public void updateProgress() {

    }
}
