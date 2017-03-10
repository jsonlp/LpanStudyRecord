package com.lpan.study.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/8/12.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        initView();

        initData();
    }

    protected void initData() {

    }

    protected void initView() {


    }

    protected int getLayoutResource(){
        return R.layout.activity_main;
    }

    @Override
    public Resources getResources() {
        //解决更换字体后UI混乱 不知道是否有效
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

}
