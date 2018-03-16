package com.lpan.study.imageloader;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.lpan.study.context.GlideApp;
import com.lpan.study.context.GlideRequest;
import com.lpan.study.context.GlideRequests;
import com.lpan.study.imageloader.transformation.BlurTransformation;

import java.io.File;

/**
 * Created by lpan on 2018/3/15.
 */

public class GlideImageLoader implements ILoader {

    /**
     * @describe 待完善, 需要什么功能, 可以在这里查看Glide是否具有, 然后添加到ImageLoaderConfig里
     * @author lpan
     * @time 2018/3/15 下午2:35
     */
    @Override
    public void start(ImageLoaderConfig config) {
        if (config != null) {
            Context context = config.getContext();
            ImageView imageView = config.getTarget();

            GlideRequests with = GlideApp.with(context);
            GlideRequest<Drawable> load = null;


            //url
            String url = config.getUrl();
            if (!TextUtils.isEmpty(url)) {
                load = with.load(url);
            }
            //res
            int resId = config.getResId();
            if (resId != 0) {
                load = with.load(resId);
            }
            //file path
            String filePath = config.getFilePath();
            if (!TextUtils.isEmpty(filePath)) {
                load = with.load(filePath);
            }
            //file
            File file = config.getFile();
            if (file != null) {
                load = with.load(file);
            }
            //uri
            Uri uri = config.getUri();
            if (uri != null) {
                load = with.load(uri);

            }
            //placeHolder
            int placeHolderResId = config.getPlaceHolderResId();
            if (placeHolderResId != 0 && load != null) {
                load = load.placeholder(placeHolderResId);
            }
            //error
            int errorResId = config.getErrorResId();
            if (errorResId != 0 && load != null) {
                load = load.error(errorResId);
            }
            //blur
            int blurRadius = config.getBlurRadius();
            if (blurRadius != 0) {
                load = load.apply(RequestOptions.bitmapTransform(new BlurTransformation(blurRadius)));
            }
            //round radius
            int rectRoundRadius = config.getRectRoundRadius();
            if (rectRoundRadius != 0) {
                load = load.apply(RequestOptions.bitmapTransform(new RoundedCorners(rectRoundRadius)));
            }
            //circle
            if (config.isCircle()) {
                load = load.circleCrop();
            }
            //download
            final SaveImageListener saveImageListener = config.getSaveImageListener();
            if (saveImageListener != null) {
                with.asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveImageListener.getBitmap(resource);
                    }
                });
            }
            if (load != null && imageView != null) {
                load.into(imageView);
            }
        }
    }
}
