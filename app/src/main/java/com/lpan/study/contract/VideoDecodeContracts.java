package com.lpan.study.contract;

import com.lpan.study.model.FFmpegVideoInfo;
import com.lpan.study.presenter.BasePresenter;
import com.lpan.study.presenter.BaseView;

/**
 * Created by lpan on 2017/11/22.
 */

public interface VideoDecodeContracts {

    interface Presenter extends BasePresenter{

        void loadFFmpeg();

        void destroyFFmpeg();

        FFmpegVideoInfo getVideoInfo(String content);

        void playVideo();

        void stopVideo();

        void analysisVideo();

        void transformVideo(FFmpegVideoInfo fFmpegVideoInfo,String path);

        void hflipVideo();

        void addWaterMask();

    }

    interface View extends BaseView<Presenter>{

        void playVideo();

        void stopVideo();

        void updateProgress();
    }
}
