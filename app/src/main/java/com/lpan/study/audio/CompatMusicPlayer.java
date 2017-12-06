package com.lpan.study.audio;


import android.media.MediaPlayer;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by liaopan on 2017/12/5 15:18.
 */

public class CompatMusicPlayer {

    private MediaPlayer.OnCompletionListener mOnCompletionListener;

    private MediaPlayer.OnErrorListener mOnErrorListener;

    private MediaPlayer mMediaPlayer;

    public CompatMusicPlayer(){
        init();
    }

    public void init(){
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
        }
    }

    public void setDataSource(String source) throws IOException {
        if (mMediaPlayer!=null) {
            mMediaPlayer.setDataSource(source);
        }
    }

    public void setDataSource(FileDescriptor fd, long offset, long length) throws IOException {
        if (mMediaPlayer!=null) {
            mMediaPlayer.setDataSource(fd, offset, length);
        }
    }

    public void prepareAsync(){
        if (mMediaPlayer!=null) {
            mMediaPlayer.prepareAsync();
        }
    }

    public void start(){
        if (mMediaPlayer!=null) {
            mMediaPlayer.start();
        }
    }

    public void pause(){
        if (mMediaPlayer!=null) {
            mMediaPlayer.pause();
        }
    }

    public boolean isPlaying(){
        if (mMediaPlayer!=null) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }


    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        if (mMediaPlayer!=null) {
            mMediaPlayer.setOnPreparedListener(onPreparedListener);
        }
    }


    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        if (mMediaPlayer!=null) {
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
        }
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        mOnErrorListener = onErrorListener;
        if (mMediaPlayer!=null) {
            mMediaPlayer.setOnErrorListener(onErrorListener);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mMediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        mMediaPlayer = mediaPlayer;
    }
}
