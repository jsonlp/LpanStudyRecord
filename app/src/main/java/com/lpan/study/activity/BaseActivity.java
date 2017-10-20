package com.lpan.study.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.lpan.study.context.AppContext;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/8/12.
 */
public abstract class BaseActivity extends FragmentActivity {

    protected Handler mHandler= new Handler();

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
        return R.layout.activity_base;
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

    public void toastShort(String message){
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(AppContext.getContext(),message,Toast.LENGTH_SHORT).show();
        }
    }

    public void toastLong(String message){
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(AppContext.getContext(),message,Toast.LENGTH_LONG).show();
        }
    }

    private void update(){
        //add in v2.0
    }
}
