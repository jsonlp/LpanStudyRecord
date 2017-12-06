package com.lpan.study.fragment;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lpan.study.audio.AudioFocusCallback;
import com.lpan.study.audio.AudioFocusHelper;
import com.lpan.study.audio.CompatMusicPlayer;
import com.lpan.study.constants.HttpConstants;
import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.R;

import java.io.IOException;

/**
 * Created by lpan on 2016/12/21.
 */
public class AudioFocusTestFragment extends BaseFragment implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, AudioFocusCallback {

    private static final String TAG = AudioFocusTestFragment.class.getSimpleName();

    private static final String MUSIC_PATH = HttpConstants.SONG_URL;

    private CompatMusicPlayer mJiemoMusicPlayer;

    private AudioFocusHelper mAudioFocusHelper;

    private boolean mHasAudioFocus;

    private ImageView mPlayButton;

    private TextView mTextView;

    private TextView mNetMusic;

    private boolean mPrepared;

    public static final String AUDIO_FOCUS = "         例如，一个用户正在听音乐，同时另一个应用程序有很重要的事需要通知用户，由于吵闹的音乐用户可能不会听到提示音。从Android 2.2开始,Android平台为应用程序提供了一个方式来协商设备的音频输出，这个机制被称为音频焦点。\n" +
            "\n" +
            "  当您的应用程序需要输出音频，如音乐或一个通知,这时你就必须请求音频焦点。一旦得到焦点，它就可以自由的使用声音输出设备，同时它会不断监听焦点的更改。如果它被通知已经失去了音频焦点，它会要么立即杀死音频或立即降低到一个安静的水平（被称为“ducking”——有一个标记,指示哪一个是适当的）当它再次接收焦点时，继续不断播放。\n" +
            "\n" +
            "  音频焦点是自然的合作，应用程序都期望（强烈鼓励）遵守音频焦点指南，但规则并不是系统强制执行的。如果应用程序失去音频焦点后想要播放嘈杂的音乐，在系统中没有什么会阻止他。然而,这样可能会让用户有更糟糕的体验,并可能卸载这运行不当的应用程序。";


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mPlayButton = (ImageView) view.findViewById(R.id.play_button);
        mTextView = (TextView) view.findViewById(R.id.text1);
        mNetMusic = (TextView) view.findViewById(R.id.text2);
        mNetMusic.setOnClickListener(this);
        mPlayButton.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mJiemoMusicPlayer = new CompatMusicPlayer();
        mJiemoMusicPlayer.setOnPreparedListener(this);
        mJiemoMusicPlayer.setOnCompletionListener(this);
        mJiemoMusicPlayer.setOnErrorListener(this);
        mTextView.setText(AUDIO_FOCUS);
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

            case R.id.text2:

                break;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e(TAG, "----------onPrepared v1.0");
        if (mJiemoMusicPlayer == null) {
            return;
        }
        mPrepared = true;
        playMusic();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.e("AudioFocusTestFragment", "onCompletion--------");
        mPlayButton.setImageResource(R.drawable.music_pause_icon);

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e("AudioFocusTestFragment", "onError--------");
        mPlayButton.setImageResource(R.drawable.music_pause_icon);

        return false;
    }

    /**
     * 判断是否正在播放
     *
     * @return
     */
    private boolean isPlaying() {
        if (mJiemoMusicPlayer == null) {
            return false;
        }
        return mJiemoMusicPlayer.isPlaying();
    }

    /**
     * 第一次设置要播放的音乐
     *
     * @param musicUrl 网络mp3 url
     */
    private void setMusic(String musicUrl) {
        if (mJiemoMusicPlayer == null) {
            return;
        }
        //本地raw文件夹下的gala.mp3文件
        AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.gala);
        try {
            mJiemoMusicPlayer.setDataSource(file.getFileDescriptor(),
                    file.getStartOffset(), file.getLength());
//            mJiemoMusicPlayer.setDataSource(musicUrl);
            mJiemoMusicPlayer.prepareAsync();
            mPrepared = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音乐
     */
    private void playMusic() {
        if (mJiemoMusicPlayer == null) {
            return;
        }
        if (tryToGainFocus()) {
            mJiemoMusicPlayer.start();
            mPlayButton.setImageResource(R.drawable.music_play_icon);

        }
    }

    /**
     * 暂停音乐
     */
    private void pauseMusic() {
        if (mJiemoMusicPlayer == null) {
            return;
        }
        mJiemoMusicPlayer.pause();
        mPlayButton.setImageResource(R.drawable.music_pause_icon);
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
