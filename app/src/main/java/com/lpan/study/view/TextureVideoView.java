package com.lpan.study.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;

@SuppressLint("NewApi")
public class TextureVideoView extends TextureView implements
        SurfaceTextureListener {

    private static final String TAG = "TextureVideoView";

    private boolean playFinished = false;//是否播放完毕,在onCompletion中值修改为true，表示播放完毕，不在调用onSeek

    private MediaPlayer mediaPlayer;

    private MediaState mediaState;

    private Surface mSurface;

    private String loacalPath;

    private String mVideoUrl;

    private int progress;

    private int total;

    private int mVideoWidth;//视频宽度

    private int mVideoHeight;//视频高度

    public static final int CENTER_CROP_MODE = 1;//中心裁剪模式

    public static final int CENTER_MODE = 2;//一边中心填充模式

    public int mVideoMode = 0;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    private OnStateChangeListener onStateChangeListener;

    public void setOnStateChangeListener(
            OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }


    private OnInfoListener onInfoListener = new OnInfoListener() {
        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            if (onStateChangeListener != null && mediaState != MediaState.PAUSE) {
                onStateChangeListener.onPlaying();
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    onStateChangeListener.onBuffering();
                } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    onStateChangeListener.onPlaying();
                }
            }
            return false;
        }
    };

    private OnBufferingUpdateListener bufferingUpdateListener = new OnBufferingUpdateListener() {
        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            if (onStateChangeListener != null) {
                //在某些情况下视频一次性缓冲100%，有些手机竟然不调用OnInfo回调，比如小米2，导致缓冲指示器一直显示，所以在此处添加此代码
                if (percent == 100 && mediaState != MediaState.PAUSE) {
                    mediaState = MediaState.PLAYING;
                    onStateChangeListener.onPlaying();
                }
                if (mediaState == MediaState.PLAYING) {
                    if (playFinished)
                        return;
                    onStateChangeListener.onSeek(mediaPlayer.getDuration(),
                            mediaPlayer.getCurrentPosition());
                }
            }
        }
    };

    public TextureVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        setSurfaceTextureListener(this);
    }

    public String getLoacalPath() {
        return loacalPath;
    }

    public void setLoacalPath(String loacalPath) {
        this.loacalPath = loacalPath;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                          int width, int height) {
        mSurface = new Surface(surfaceTexture);
        if (mediaPlayer == null) {
            if (mediaPlayer == null) {
                mediaPlayer = new MediaPlayer();
            }
            mediaPlayer
                    .setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            playFinished = false;
                            mediaPlayer.start();
                            mediaState = MediaState.PLAYING;
                        }
                    });
            mediaPlayer.setOnInfoListener(onInfoListener);
            mediaPlayer.setOnBufferingUpdateListener(bufferingUpdateListener);
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (onStateChangeListener != null) {
                        if (mediaState != MediaState.PLAYING)
                            return;
                        onStateChangeListener.playFinish();
                        playFinished = true;
                    }
                }
            });
            mediaPlayer.setOnErrorListener(new OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mediaPlayer.reset();
                    mediaState = MediaState.INIT;
                    onStateChangeListener.onStop();
                    return false;
                }
            });

            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    mVideoHeight = mediaPlayer.getVideoHeight();
                    mVideoWidth = mediaPlayer.getVideoWidth();
                    updateTextureViewSize(mVideoMode);
                    if (onStateChangeListener!=null){
                        onStateChangeListener.onVideoSizeChanged(mVideoWidth,mVideoHeight);
                    }
                }
            });
        }
        mediaPlayer.setSurface(mSurface);
        mediaState = MediaState.INIT;
        if (onStateChangeListener != null) {
            onStateChangeListener.onTextureViewAvaliable();
        }
    }

    public void stop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mediaState == MediaState.INIT) {
                        return;
                    }
                    if (mediaState == MediaState.PREPARING) {
                        mediaPlayer.reset();
                        mediaState = MediaState.INIT;
                        return;
                    }
                    if (mediaState == MediaState.PAUSE) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaState = MediaState.INIT;
                        return;
                    }
                    if (mediaState == MediaState.PLAYING) {
                        mediaPlayer.pause();
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaState = MediaState.INIT;
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (null != mediaPlayer)
                        mediaPlayer.reset();
                    mediaState = MediaState.INIT;
                } finally {
                    Message.obtain(mHandler, STOP).sendToTarget();
                }
            }
        }).start();
    }

    private final int STOP = 0;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STOP:
                    if (onStateChangeListener != null) {
                        onStateChangeListener.onStop();
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        if (onStateChangeListener != null) {
            onStateChangeListener.onSurfaceTextureDestroyed(surface);
        }

        if (mSurface != null) {
            mSurface.release();
        }
        return false;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width,
                                            int height) {
        updateTextureViewSize(mVideoMode);

    }

    public void setVideoMode(int mode){
        mVideoMode=mode;
    }

    /**
     *
     * @param mode Pass {@link #CENTER_CROP_MODE} or {@link #CENTER_MODE}. Default
     * value is 0.
     */
    public void updateTextureViewSize(int mode){
        if (mode==CENTER_MODE){
            updateTextureViewSizeCenter();
        }else if (mode == CENTER_CROP_MODE){
            updateTextureViewSizeCenterCrop();
        }
    }

    //重新计算video的显示位置，裁剪后全屏显示
    private void updateTextureViewSizeCenterCrop(){

        float sx = (float) getWidth() / (float) mVideoWidth;
        float sy = (float) getHeight() / (float) mVideoHeight;

        Matrix matrix = new Matrix();
        float maxScale = Math.max(sx, sy);

        //第1步:把视频区移动到View区,使两者中心点重合.
        matrix.preTranslate((getWidth() - mVideoWidth) / 2, (getHeight() - mVideoHeight) / 2);

        //第2步:因为默认视频是fitXY的形式显示的,所以首先要缩放还原回来.
        matrix.preScale(mVideoWidth / (float) getWidth(), mVideoHeight / (float) getHeight());

        //第3步,等比例放大或缩小,直到视频区的一边超过View一边, 另一边与View的另一边相等. 因为超过的部分超出了View的范围,所以是不会显示的,相当于裁剪了.
        matrix.postScale(maxScale, maxScale, getWidth() / 2, getHeight() / 2);//后两个参数坐标是以整个View的坐标系以参考的

        setTransform(matrix);
        postInvalidate();
    }

    //重新计算video的显示位置，让其全部显示并据中
    private void updateTextureViewSizeCenter(){

        float sx = (float) getWidth() / (float) mVideoWidth;
        float sy = (float) getHeight() / (float) mVideoHeight;

        Matrix matrix = new Matrix();

        //第1步:把视频区移动到View区,使两者中心点重合.
        matrix.preTranslate((getWidth() - mVideoWidth) / 2, (getHeight() - mVideoHeight) / 2);

        //第2步:因为默认视频是fitXY的形式显示的,所以首先要缩放还原回来.
        matrix.preScale(mVideoWidth / (float) getWidth(), mVideoHeight / (float) getHeight());

        //第3步,等比例放大或缩小,直到视频区的一边和View一边相等.如果另一边和view的一边不相等，则留下空隙
        if (sx >= sy){
            matrix.postScale(sy, sy, getWidth() / 2, getHeight() / 2);
        }else{
            matrix.postScale(sx, sx, getWidth() / 2, getHeight() / 2);
        }

        setTransform(matrix);
        postInvalidate();
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }


    public void setPath(String path, boolean looping, float volume) {
//        if (mediaState == MediaState.PREPARING) {
//            stop();
//            return;
//        }
        loacalPath = path;

        mediaPlayer.reset();
        mediaPlayer.setLooping(looping);
        mediaPlayer.setVolume(volume, volume);
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            if (onStateChangeListener != null) {
                onStateChangeListener.onPrepare();
            }
            mediaState = MediaState.PREPARING;
        } catch (Exception e) {
            mediaPlayer.reset();
            mediaState = MediaState.INIT;
        }
    }

    public void pause() {
        mediaPlayer.pause();
        mediaState = MediaState.PAUSE;
        if (onStateChangeListener != null) {
            onStateChangeListener.onPause();
        }
    }

    public void start() {
        playFinished = false;
        mediaPlayer.start();
        mediaState = MediaState.PLAYING;
    }

    public boolean isPlaying() {
        return getState() == MediaState.PLAYING;
    }

    public void seekTo(int msec) {
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.seekTo(msec);
    }

    public int getCurrentPosition() {
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        if (mediaPlayer == null) {
            return 0;
        }
        return mediaPlayer.getDuration();
    }


    public enum MediaState {
        INIT, PREPARING, PLAYING, PAUSE, RELEASE;
    }

    public MediaState getState() {
        return mediaState;
    }

    public interface OnStateChangeListener {
        void onSurfaceTextureDestroyed(SurfaceTexture surface);

        void onBuffering();

        void onPlaying();

        void onSeek(int max, int progress);

        void onStop();

        void onPause();

        void onTextureViewAvaliable();

        void playFinish();

        void onPrepare();

        void onVideoSizeChanged(int vWidth,int vHeight);

    }
}
