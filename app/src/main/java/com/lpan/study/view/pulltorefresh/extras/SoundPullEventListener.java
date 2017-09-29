/**
 * ****************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * *****************************************************************************
 */

package com.lpan.study.view.pulltorefresh.extras;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.util.SparseIntArray;
import android.view.View;

import com.lpan.study.context.AppContext;
import com.lpan.study.view.pulltorefresh.PullToRefreshBase;

import java.util.HashMap;
import java.util.prefs.Preferences;


public class SoundPullEventListener<V extends View> implements
        PullToRefreshBase.OnPullEventListener<V> {

    //    private final Context mContext;

    private static SparseIntArray sSoundIds = new SparseIntArray();

    private static SoundPool sSoundPool;

    private final HashMap<PullToRefreshBase.State, Integer> mSoundMap;

    /**
     * Constructor
     *
     */
    public SoundPullEventListener() {
        //        mContext = context;
        mSoundMap = new HashMap<PullToRefreshBase.State, Integer>();

    }

    @Override
    public final void onPullEvent(PullToRefreshBase<V> refreshView, PullToRefreshBase.State event, PullToRefreshBase.Mode direction) {
        Integer soundResIdObj = mSoundMap.get(event);
        if (null != soundResIdObj) {
            playSound(soundResIdObj.intValue());
        }

    }

    /**
     * Set the Sounds to be played when a Pull Event happens. You specify
     * which sound plays for which events by calling this method multiple
     * times for each event.
     * <p/>
     * If you've already set a sound for a certain event, and add another
     * sound for that event, only the new sound will be played.
     *
     * @param event - The event for which the sound will be played.
     * @param resId - Resource Id of the sound file to be played (e.g.
     *        <var>R.raw.pull_sound</var>)
     */
    public void addSoundEvent(PullToRefreshBase.State event, int resId) {
        mSoundMap.put(event, resId);
    }

    /**
     * Clears all of the previously set sounds and events.
     */
    public void clearSounds() {
        mSoundMap.clear();
    }

    //    /**
    //     * Gets the current (or last) MediaPlayer instance.
    //     */
    //    public MediaPlayer getCurrentMediaPlayer() {
    //        return mCurrentMediaPlayer;
    //    }

    public void playSound(final int resId) {
        // Stop current player, if there's one playing
//        final AudioManager audioManager = (AudioManager) AppContext.getContext().getSystemService(
//                Context.AUDIO_SERVICE);

//        if (!Preferences.getInstance(AppContext.getContext()).getEffectMode()
//                || audioManager.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
//            return;
//        }

//        final int streamType = SensorController.getInstance(AppContext.getContext())
//                .getStreamType();
//        if (sSoundPool == null) {
//            sSoundPool = new SoundPool(6, streamType, 0); // max值是State个数（6种）
//        }
//
//        if (sSoundIds.get(resId) == 0) {
//            int soundId = sSoundPool.load(AppContext.getContext(), resId, 1);
//            sSoundIds.put(resId, soundId);
//            sSoundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
//
//                @Override
//                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
//                    float actualVolume = (float) audioManager.getStreamVolume(streamType);
//                    float maxVolume = (float) audioManager.getStreamMaxVolume(streamType);
//                    float volume = actualVolume / maxVolume;
//                    soundPool.play(sSoundIds.get(resId), volume, volume, 1, 0, 1f);
//                }
//            });
//        } else {
//            float actualVolume = (float) audioManager.getStreamVolume(streamType);
//            float maxVolume = (float) audioManager.getStreamMaxVolume(streamType);
//            float volume = actualVolume / maxVolume;
//            sSoundPool.play(sSoundIds.get(resId), volume, volume, 1, 0, 1f);
//        }

    }

}
