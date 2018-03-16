package com.lpan.study.imageloader;

import android.content.Context;

/**
 * Created by lpan on 2018/3/15.
 */

public class ImageLoader {

    public static ImageLoaderConfig.Build with(Context context) {
        return new ImageLoaderConfig.Build(context);
    }
}
