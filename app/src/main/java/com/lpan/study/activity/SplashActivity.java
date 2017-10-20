package com.lpan.study.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/9/6.
 */

public class SplashActivity extends BaseActivity{


    private ImageView mImageView;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        mImageView = (ImageView) findViewById(R.id.image1);
        mImageView.setImageResource(R.drawable.wall01);


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },500);
    }
}
