package com.lpan.study.model;

/**
 * Created by lpan on 2017/2/15.
 */

public class FFmpegVideoInfo {
    int width;

    int height;

    int bitrate;

    int rotate;

    double duration;

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "FFmpegVideoInfo{" +
                "width=" + width +
                ", height=" + height +
                ", bitrate=" + bitrate +
                ", rotate=" + rotate +
                ", duration=" + duration +
                '}';
    }
}
