package com.lpan.study.fragment;

import android.view.View;
import android.widget.Button;

import com.lpan.study.view.SimleLoadingView;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class LoadingAnimationFragment extends BaseFragment implements View.OnClickListener {

    private SimleLoadingView mSimleLoadingView;

    private Button mButton1;

    private Button mButton2;

    private Button mButton3;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_loading_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mSimleLoadingView = (SimleLoadingView) view.findViewById(R.id.smile);
        mButton1 = (Button) view.findViewById(R.id.button1);
        mButton2 = (Button) view.findViewById(R.id.button2);
        mButton3 = (Button) view.findViewById(R.id.button3);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
//                mSimleLoadingView.setAnimationType(0);
//                mSimleLoadingView.rotateView();
                break;
            case R.id.button2:
//                mSimleLoadingView.setAnimationType(1);
//                mSimleLoadingView.rotateView();

                break;
            case R.id.button3:
//                mSimleLoadingView.setAnimationType(2);
//                mSimleLoadingView.rotateView();

                break;
        }
    }
}
