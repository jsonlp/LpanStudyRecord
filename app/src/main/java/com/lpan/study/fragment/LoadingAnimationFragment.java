package com.lpan.study.fragment;

import android.view.View;

import com.lpan.study.view.SimleView;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class LoadingAnimationFragment extends BaseFragment {

    private SimleView mSimleLoadingView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_loading_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mSimleLoadingView = (SimleView) view.findViewById(R.id.smile);
    }


}
