package com.lpan.study.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;

import com.lpan.study.fragment.HomeHolderFragment;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public class MainActivity extends BaseActivity {

    private LinearLayout mContainer;

    @Override
    protected int getLayoutResource() {
        return super.getLayoutResource();
    }

    @Override
    protected void initView() {
        super.initView();
        mContainer= (LinearLayout) findViewById(R.id.container);
    }

    @Override
    protected void initData() {
        super.initData();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.container,new HomeHolderFragment()).commit();

    }
}
