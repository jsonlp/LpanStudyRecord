package com.lpan.study.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.lpan.study.constants.Constants;
import com.lpan.study.context.AppContext;
import com.lpan.R;

/**
 * Created by lpan on 2016/8/12.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Handler mHandler = new Handler();

    protected boolean mBundleNoAnimation;

    protected boolean isUpAnim;

    protected boolean isFadeAnim;

    protected boolean isLongFadeAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        Bundle activityBundle = getIntent().getBundleExtra(Constants.EXTRAS_BUNDLE);
        if (activityBundle != null) {
            mBundleNoAnimation = activityBundle.getBoolean(Constants.NO_ANIMATION);
            isUpAnim = activityBundle.getBoolean(Constants.ANIMATION_UP);
            isFadeAnim = activityBundle.getBoolean(Constants.ANIMATION_FADE);
            isLongFadeAnim = activityBundle.getBoolean(Constants.ANIMATION_LONG_FADE);
        }

        initView();

        initData();
    }

    @Override
    public void finish() {
        super.finish();
        if (isUpAnim) {
            overridePendingTransition(R.anim.playlist_slide_in, R.anim.playlist_down_out);
        } else if (isLongFadeAnim) {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (isFadeAnim){
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        } else {
            overridePendingTransition(R.anim.fragment_slide_right_enter,
                    R.anim.fragment_slide_right_exit);
        }
    }

    protected void initData() {

    }

    protected void initView() {


    }

    protected int getLayoutResource() {
        return R.layout.activity_base;
    }


    public void toastShort(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void toastLong(String message) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(AppContext.getContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
