package com.lpan.study.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.view.smilefaceview.SimleLoadingView;
import com.lpan.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class LoadingAnimationFragment extends BaseFragment {
    String mString = "打快点聊得来的劳动力低廉的劳动力带来了多大了斗罗大陆\n打快点聊得来的劳动力低廉的劳动力带来了多大了斗罗大陆打快点聊得来的劳动力低廉的劳动力带来了多大了斗罗大陆打快点聊得来的劳动力低廉的劳动力带来了多大了斗罗大陆";

    private SimleLoadingView mSimleLoadingView;

    private TextView mTextView;

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Toast.makeText(AppContext.getContext(), "  loading over ", Toast.LENGTH_SHORT).show();
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_loading_animation;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mSimleLoadingView = (SimleLoadingView) view.findViewById(R.id.smile);
        mHandler.postDelayed(mRunnable, 5000);

        mTextView = (TextView) view.findViewById(R.id.text1);

        mTextView.setText(mString);

        if (mString.length() > 50) {
            mTextView.setText(mString.substring(0, 50) + "!!!");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mRunnable);
        mHandler = null;
    }
}
