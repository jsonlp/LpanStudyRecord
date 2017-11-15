package com.lpan.study.fragment;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lpan.study.audio.AudioFocusCallback;
import com.lpan.study.audio.AudioFocusHelper;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.R;

import java.io.IOException;

/**
 * Created by lpan on 2016/12/21.
 */
public class AudioFocusTestFragment extends BaseFragment implements View.OnClickListener, MediaPlayer.OnPreparedListener, AudioFocusCallback {

    private static final String TAG = AudioFocusTestFragment.class.getSimpleName();

    private static final String MUSIC_PATH = "";

    private MediaPlayer mMediaPlayer;

    private AudioFocusHelper mAudioFocusHelper;

    private boolean mHasAudioFocus;

    private ImageView mPlayButton;

    private boolean mPrepared;


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mPlayButton = (ImageView) view.findViewById(R.id.play_button);
        mPlayButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_audiofocus_test;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                if (isPlaying()) {
                    pauseMusic();
                } else if (!mPrepared) {
                    setMusic(MUSIC_PATH);
                } else {
                    playMusic();
                }
                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e(TAG, "----------onPrepared v1.0");
        if (mMediaPlayer == null) {
            return;
        }
        mPrepared = true;
        playMusic();
    }

    /**
     * 判断是否正在播放
     *
     * @return
     */
    private boolean isPlaying() {
        if (mMediaPlayer == null) {
            return false;
        }
        return mMediaPlayer.isPlaying();
    }

    /**
     * 第一次设置要播放的音乐
     *
     * @param musicUrl 网络mp3 url
     */
    private void setMusic(String musicUrl) {
        if (mMediaPlayer == null) {
            return;
        }
        //本地raw文件夹下的gala.mp3文件
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.gala);
        try {
            mMediaPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
//            mMediaPlayer.setDataSource(musicUrl);
            mMediaPlayer.prepareAsync();
            mPrepared = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音乐
     */
    private void playMusic() {
        if (mMediaPlayer == null) {
            return;
        }
        if (tryToGainFocus()) {
            mMediaPlayer.start();

        }
    }

    /**
     * 暂停音乐
     */
    private void pauseMusic() {
        if (mMediaPlayer == null) {
            return;
        }
        mMediaPlayer.pause();
        giveUpFocus();
    }

    /**
     * 初始化  AudioFocus是android api 8 以上加入的,在这个地方可以判断下api版本
     */
    private void initAudioFocusHelper() {
        if (mAudioFocusHelper == null) {
            mAudioFocusHelper = new AudioFocusHelper(AudioFocusTestFragment.this, getActivity());
        }
    }

    /**
     * 获取长久音频焦点
     *
     * @return
     */
    public boolean tryToGainFocus() {
        initAudioFocusHelper();
        if (!mHasAudioFocus && mAudioFocusHelper != null && mAudioFocusHelper.requestFocus()) {
            mHasAudioFocus = true;
            return true;
        }
        return false;
    }

    /**
     * 获取短暂音频焦点(如播放语音/通知声音等)
     *
     * @return
     */
    public boolean tryToGainFocusTransient() {
        initAudioFocusHelper();
        if (!mHasAudioFocus && mAudioFocusHelper != null && mAudioFocusHelper.requestFocusTransient()) {
            mHasAudioFocus = true;
            return true;
        }
        return false;
    }

    /**
     * 放弃音频焦点
     *
     * @return
     */
    public boolean giveUpFocus() {
        if (mHasAudioFocus && mAudioFocusHelper != null && mAudioFocusHelper.abandonFocus()) {
            mHasAudioFocus = false;
            return false;
        }
        return true;
    }

    @Override
    public void onGainFocus() {
        Log.e(TAG, "   -----onGainFocus ");
        Toast.makeText(AppContext.getContext(), "  onGainFocus ", Toast.LENGTH_SHORT).show();

        playMusic();
    }

    @Override
    public void onLostFocus() {
        Log.e(TAG, "   -----onLostFocus ");
        Toast.makeText(AppContext.getContext(), "  onLostFocus ", Toast.LENGTH_SHORT).show();

        pauseMusic();
    }

    @Override
    public void onLostFocusTransient() {
        Log.e(TAG, "   -----onLostFocusTransient ");
        Toast.makeText(AppContext.getContext(), "  onLostFocusTransient ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLostFocusTransientCanDuck() {
        Log.e(TAG, "   -----onLostFocusTransientCanDuck ");
        Toast.makeText(AppContext.getContext(), "  onLostFocusTransientCanDuck ", Toast.LENGTH_SHORT).show();

    }
}
