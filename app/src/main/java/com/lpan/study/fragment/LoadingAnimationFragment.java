package com.lpan.study.fragment;

import android.view.View;
import android.widget.TextView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.smilefaceview.SmileLoadingView;
import com.lpan.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class LoadingAnimationFragment extends BaseFragment {

    private SmileLoadingView mSimleLoadingView;

    private TextView mTextView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_loading_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mSimleLoadingView = (SmileLoadingView) view.findViewById(R.id.smile);

        mTextView = (TextView) view.findViewById(R.id.text1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
