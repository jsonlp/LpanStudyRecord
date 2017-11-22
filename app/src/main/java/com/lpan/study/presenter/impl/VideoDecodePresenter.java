package com.lpan.study.presenter.impl;

import android.os.Environment;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.lpan.study.constants.FilePathConstants;
import com.lpan.study.context.AppContext;
import com.lpan.study.contract.VideoDecodeContracts;
import com.lpan.study.model.FFmpegVideoInfo;
import com.lpan.study.utils.Utils;

import java.io.File;

/**
 * Created by lpan on 2017/11/22.
 */

public class VideoDecodePresenter implements VideoDecodeContracts.Presenter {

    public static final String TAG = VideoDecodePresenter.class.getSimpleName();

    private static final String VIDEO_PATH_TRANSFOEM = FilePathConstants.EXTERNAL_STORAGE_DIR + File.separator + "transform.mp4";

    private static final String VIDEO_PATH_HFLIP = FilePathConstants.EXTERNAL_STORAGE_DIR + File.separator + "hflip.mp4";

    private static final String VIDEO_PATH_ADDWATERMASK = FilePathConstants.EXTERNAL_STORAGE_DIR + File.separator + "addwatermask.mp4";

    private static final String IMAGE_PATH_ADDWATERMASK = FilePathConstants.EXTERNAL_STORAGE_DIR + File.separator + "logo.png";


    private VideoDecodeContracts.View mView;

    public VideoDecodePresenter(VideoDecodeContracts.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadFFmpeg() {
        try {
            FFmpeg.getInstance(AppContext.getContext()).loadBinary(null);
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroyFFmpeg() {
        FFmpeg.getInstance(AppContext.getContext()).killRunningProcesses();
        FFmpeg.clearInstance();
    }

    @Override
    public FFmpegVideoInfo getVideoInfo(String content) {

        FFmpegVideoInfo videoInfo = new FFmpegVideoInfo();
        String[] split = content.split("\n");
        for (String str : split) {
            if (str.contains("Stream") && str.contains("Video:")) {
                String[] split1 = str.split(", ");
                for (String s : split1) {
                    if (s.endsWith("kb/s")) {
                        String[] split2 = s.split(" kb/s");
                        videoInfo.setBitrate(Integer.parseInt(split2[0]));
                    }
                    if (s.contains("x")) {
                        //1080x1920 [SAR 1:1 DAR 9:16]
                        // 或者1080x1920
                        // 或者Stream #0:0(eng): Video: h264 (High) (avc1 / 0x31637661)
                        if (s.contains(" ")) {
                            String[] split2 = s.split(" ");
                            for (String s1 : split2) {
                                if (s1.matches("[\\d]{3,4}x[\\d]{3,4}")) {
                                    String[] xes = s1.split("x");
                                    videoInfo.setWidth(Integer.parseInt(xes[0]));
                                    videoInfo.setHeight(Integer.parseInt(xes[1]));
                                }
                            }
                        } else {
                            if (s.matches("[\\d]{3,4}x[\\d]{3,4}")) {
                                String[] xes = s.split("x");
                                videoInfo.setWidth(Integer.parseInt(xes[0]));
                                videoInfo.setHeight(Integer.parseInt(xes[1]));
                            }
                        }
                    }
                }
            }
            if (str.contains("Duration:")) {
                // Duration: 00:00:05.31, start: 0.000000, bitrate: 17222 kb/s
                String[] duration = str.split(", ");
                if (!TextUtils.isEmpty(duration[0]) && duration[0].contains("Duration")) {
                    String[] time = duration[0].split(": ");
                    String[] time2 = time[1].split(":");

                    double hour = Double.parseDouble(time2[0]);
                    double minute = Double.parseDouble(time2[1]);
                    double second = Double.parseDouble(time2[2]);

                    double total = (hour * 60 + minute) * 60 + second;
                    videoInfo.setDuration(total);
                }

            }

            if (str.contains("rotate")) {
                String[] split1 = str.split(": ");
                videoInfo.setRotate(Integer.parseInt(split1[1]));
            }
        }
        android.util.Log.d("lp-test", videoInfo.toString());
        return videoInfo;
    }

    @Override
    public void playVideo() {
        mView.playVideo();
    }

    @Override
    public void stopVideo() {
        mView.stopVideo();
    }

    @Override
    public void analysisVideo() {
        final String path = VIDEO_PATH_TRANSFOEM;
        String[] cmd = new String[]{"-i", path};
        try {
            FFmpeg.getInstance(AppContext.getContext()).execute(cmd, new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {
                    Log.d("lp-test", "   onSuccess" + message);
                }

                @Override
                public void onProgress(String message) {
                    Log.d("lp-test", "   onProgress" + message);

                }

                @Override
                public void onFailure(String message) {
                    Log.d("lp-test", "   onFailure" + message);
                    FFmpegVideoInfo fFmpegVideoInfo = getVideoInfo(message);
                    transformVideo(fFmpegVideoInfo, path);
                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void transformVideo(FFmpegVideoInfo fFmpegVideoInfo, String path) {

        if (fFmpegVideoInfo == null) {
            return;
        }

        File out = new File(getVideoFileDir(), "transform_"+System.currentTimeMillis() + ".mp4");
        final long startTime = System.currentTimeMillis();
        String[] cmd = null;

        int width = fFmpegVideoInfo.getWidth();
        int height = fFmpegVideoInfo.getHeight();
        int rotate = fFmpegVideoInfo.getRotate();
        int bitrate = fFmpegVideoInfo.getBitrate();

        String vf = "";
        String vb = "";

        int max = Math.max(width, height);
        if (max > 960) {
            if ((rotate == 90 || rotate == 270)) {
                vf = "scale=-2:960";
            } else {
                vf = "scale=960:-2";
            }
        } else {
            if ((rotate == 90 || rotate == 270)) {
                vf = "scale=-2:" + max;
            }
        }

        if (bitrate > 800) {
            vb = "800k";
        }
        if (TextUtils.isEmpty(vf) && TextUtils.isEmpty(vb)) {
            Log.d("lp-test", "transformVideo  1  " + fFmpegVideoInfo.toString());
            return;
        } else if (!TextUtils.isEmpty(vf) && !TextUtils.isEmpty(vb)) {
            cmd = new String[]{"-i", path, "-vf", vf, "-vb", vb, "-r", "24", "-preset", "superfast", "-ab", "96k", "-fs", "10M", out.getAbsolutePath(), "-debug", "v"};
            Log.d("lp-test", "transformVideo  2  " + fFmpegVideoInfo.toString());


        } else if (!TextUtils.isEmpty(vf) && TextUtils.isEmpty(vb)) {
            cmd = new String[]{"-i", path, "-vf", vf, "-r", "24", "-preset", "superfast", "-ab", "96k", "-fs", "10M", out.getAbsolutePath(), "-debug", "v"};
            Log.d("lp-test", "transformVideo  3  " + fFmpegVideoInfo.toString());


        } else if (TextUtils.isEmpty(vf) && !TextUtils.isEmpty(vb)) {
            cmd = new String[]{"-i", path, "-vb", vb, "-r", "24", "-preset", "superfast", "-ab", "96k", "-fs", "10M", out.getAbsolutePath(), "-debug", "v"};
            Log.d("lp-test", "transformVideo  4  " + fFmpegVideoInfo.toString());

        }

        try {
            FFmpeg.getInstance(AppContext.getContext()).execute(cmd, new FFmpegExecuteResponseHandler() {

                @Override
                public void onSuccess(String message) {
                    Toast.makeText(AppContext.getContext(), "onSuccess", Toast.LENGTH_SHORT).show();
                    Log.d("lp-test", "   onSuccess  " + message);

                }

                @Override
                public void onProgress(String message) {
                    Log.d("lp-test", "   onProgress  " + message);

                }

                @Override
                public void onFailure(String message) {
                    Toast.makeText(AppContext.getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                    Log.d("lp-test", "   onFailure  " + message);

                }

                @Override
                public void onStart() {
                    Toast.makeText(AppContext.getContext(), "onStart", Toast.LENGTH_SHORT).show();
                    Log.d("lp-test", "   onStart  ");

                }

                @Override
                public void onFinish() {
                    long costTime = System.currentTimeMillis() - startTime;

                    Log.d("lp-test", "   onFinish  cost time   " + Utils.converLongTimeToStr(costTime));

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void hflipVideo() {
        String path = VIDEO_PATH_HFLIP;

        String out = "/storage/emulated/0/Jiemoapp/111.mp4";
        String[] cmd = new String[]{"-i", path, "-vf", "hflip", "-preset", "superfast", out};
//        String[] cmd = new String[]{"-filters"};
        final long startTime = System.currentTimeMillis();
        try {
            FFmpeg.getInstance(AppContext.getContext()).execute(cmd, new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {
                    Log.d("VideoRecordFragment", "onSuccess----" + message);

                }

                @Override
                public void onProgress(String message) {
                    Log.d("VideoRecordFragment", "onProgress----" + message);
                }

                @Override
                public void onFailure(String message) {
                    Log.d("VideoRecordFragment", "onFailure----" + message);
                }

                @Override
                public void onStart() {
                    Log.d("VideoRecordFragment", "onStart----");
                }

                @Override
                public void onFinish() {
                    long cost = System.currentTimeMillis() - startTime;
                    Log.d("VideoRecordFragment", "onFinish----cost= " + cost);
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            Log.d("VideoRecordFragment", "FFmpegCommandAlreadyRunningException----" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void addWaterMask() {
        String videoPath = VIDEO_PATH_ADDWATERMASK;
        String imagePath = IMAGE_PATH_ADDWATERMASK;
        final File out = new File(getVideoFileDir(), System.currentTimeMillis() + ".mp4");

        String[] cmd = new String[]{"-i", videoPath, "-i", imagePath, "-filter_complex", "overlay=0:0", "-preset", "superfast", "-movflags", "-faststart", "-b:v", "1024k", "-g", "30", out.getAbsolutePath()};
        try {
            final long time = SystemClock.elapsedRealtime();
            FFmpeg.getInstance(AppContext.getContext())
                    .execute(cmd, new FFmpegExecuteResponseHandler() {
                        @Override
                        public void onSuccess(String message) {

                            Log.i("lp-test", "onSuccess       message = " + message + "   cost = " + (SystemClock.elapsedRealtime() - time));
                            Log.i("lp-test", "onSuccess       path = " + out.getAbsolutePath());

                        }

                        @Override
                        public void onProgress(String message) {
                            Log.i("lp-test", "onProgress       message = " + message);
                        }

                        @Override
                        public void onFailure(String message) {
                            Log.i("lp-test", "onFailure       message = " + message);
                        }

                        @Override
                        public void onStart() {
                            Log.i("lp-test", "onStart");
                        }

                        @Override
                        public void onFinish() {
                            Log.i("lp-test", "onFinish" + "   cost = " + (SystemClock.elapsedRealtime() - time));
                            FFmpeg.clearInstance();
                        }
                    });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
            Log.i("lp-test", "onSuccess       message = " + e.getMessage());
        }

    }

    public File getVideoFileDir() {
        File dir = new File(Environment.getExternalStorageDirectory() + "/");
        if (dir.exists()) {
            return dir;
        }
        return null;
    }

}
