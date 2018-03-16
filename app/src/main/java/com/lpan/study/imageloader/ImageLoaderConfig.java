package com.lpan.study.imageloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by lpan on 2018/3/15.
 */

public class ImageLoaderConfig {

    private Context context;
    private ImageView target;
    private String url;
    private int resId;
    private String filePath;
    private Uri uri;
    private File file;

    private int blurRadius;
    private int rectRoundRadius;
    private int placeHolderResId;
    private int errorResId;
    private boolean circle;
    private SaveImageListener saveImageListener;

    private ImageLoaderConfig(Build build) {
        this.url = build.url;
        this.target = build.target;
        this.context = build.context;
        this.resId = build.resId;
        this.filePath = build.filePath;
        this.uri = build.uri;
        this.file = build.file;
        this.blurRadius = build.blurRadius;
        this.rectRoundRadius = build.rectRoundRadius;
        this.placeHolderResId = build.placeHolderResId;
        this.errorResId = build.errorResId;
        this.circle = build.circle;
        this.saveImageListener = build.saveImageListener;

    }

    public void show() {
        GlobaImageLoader.getImageLoader().start(this);
    }

    public String getUrl() {
        return url;
    }

    public int getResId() {
        return resId;
    }

    public ImageView getTarget() {
        return target;
    }

    public Context getContext() {
        return context;
    }

    public String getFilePath() {
        return filePath;
    }

    public Uri getUri() {
        return uri;
    }

    public File getFile() {
        return file;
    }

    public int getBlurRadius() {
        return blurRadius;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public int getRectRoundRadius() {
        return rectRoundRadius;
    }

    public boolean isCircle() {
        return circle;
    }

    public SaveImageListener getSaveImageListener() {
        return saveImageListener;
    }

    public static class Build {
        private Context context;
        private ImageView target;

        private String url;
        private int resId;
        private Uri uri;
        private String filePath;
        private File file;

        private int blurRadius;
        private int rectRoundRadius;
        private int placeHolderResId;
        private int errorResId;
        private boolean circle;
        private SaveImageListener saveImageListener;


        public Build(Context context) {
            this.context = context;
        }

        public void into(ImageView target) {
            this.target = target;
            start();
        }

        public Build loadUrl(String url) {
            this.url = url;
            return this;
        }

        public Build loadRes(int resId) {
            this.resId = resId;
            return this;
        }

        public Build loadFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Build loadUri(Uri uri) {
            this.uri = uri;
            return this;
        }

        public Build loadFile(File file) {
            this.file = file;
            return this;
        }

        public Build blur(int blurRadius) {
            this.blurRadius = blurRadius;
            return this;
        }

        public Build placeHolder(int placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        public Build error(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Build roundCorner(int rectRoundRadius) {
            this.rectRoundRadius = rectRoundRadius;
            return this;
        }

        public Build circle() {
            this.circle = true;
            return this;
        }

        public void download(SaveImageListener saveImageListener){
            this.saveImageListener = saveImageListener;
            start();
        }

        private void start(){
            new ImageLoaderConfig(this).show();
        }

    }
}
