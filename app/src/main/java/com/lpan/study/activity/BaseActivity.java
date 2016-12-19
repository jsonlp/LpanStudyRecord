package com.lpan.study.activity;

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

}
