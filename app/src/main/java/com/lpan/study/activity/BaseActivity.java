package com.lpan.study.activity;

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
}
