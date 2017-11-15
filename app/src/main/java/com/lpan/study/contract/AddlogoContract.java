package com.lpan.study.contract;

import android.graphics.Bitmap;

import com.lpan.study.presenter.BasePresenter;
import com.lpan.study.presenter.BaseView;

/**
 * Created by lpan on 2017/11/15.
 */

public interface AddlogoContract {

    interface Presenter extends BasePresenter{
        void addLogo(int imageId, int logoId);
    }

    interface View extends BaseView<Presenter>{
        void showImage(Bitmap bitmap);
    }
}
