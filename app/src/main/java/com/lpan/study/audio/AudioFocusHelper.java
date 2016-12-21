package com.lpan.study.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;

/**
 * Created by lpan on 2016/12/16.
 */
public class AudioFocusHelper implements OnAudioFocusChangeListener {

    AudioManager mAM;

    AudioFocusCallback mCallback;

    public AudioFocusHelper(AudioFocusCallback callback, Context context) {
        mAM = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mCallback = callback;
    }

    /**
     * 请求长久焦点
     *
     * @return true代表获取成功
     */
    public boolean requestFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == mAM.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
    }

    /**
     * 请求短暂焦点
     *
     * @return true代表获取成功
     */
    public boolean requestFocusTransient() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == mAM.requestAudioFocus(this,
                AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
    }

    /**
     * 放弃焦点
     *
     * @return true代表成功
     */
    public boolean abandonFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED == mAM.abandonAudioFocus(this);
    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        if (mCallback == null) {
            return;
        }
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                mCallback.onGainFocus();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                mCallback.onLostFocus();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                mCallback.onLostFocusTransient();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mCallback.onLostFocusTransientCanDuck();
                break;
            default:
        }
    }
}
