package com.lpan.study.imageloader;

/**
 * Created by lpan on 2018/3/15.
 */

public class GlobaImageLoader {

    private static ILoader loader;

    /**
    * @describe 配置选用哪个loader,此处用的是GlideImageLoader,之后可以替换成其他
    * @author lpan
    * @time 2018/3/15 下午2:29
    */
    public static ILoader getImageLoader() {
        if (loader == null) {
            loader = new GlideImageLoader();
        }
        return loader;
    }
}
