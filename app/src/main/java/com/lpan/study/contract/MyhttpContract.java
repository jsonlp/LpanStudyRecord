package com.lpan.study.contract;

import com.lpan.study.model.ConfigInfo;
import com.lpan.study.presenter.BasePresenter;
import com.lpan.study.presenter.BaseView;

/**
 * Created by lpan on 2017/11/17.
 */

public interface MyhttpContract {

    interface Presenter extends BasePresenter{
        void get();

        void post();

        void singleUp();

        void singleDown();
    }

    interface View extends BaseView<Presenter>{

        void showLoading(boolean show);

        void showResult(ConfigInfo configInfo);
    }
}
