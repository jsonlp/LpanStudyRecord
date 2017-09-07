package com.lpan.study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lpan.study.activity.BaseActivity;
import com.lpan.study.context.AppContext;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2016/12/19.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);

        initViews(view);

        initData();

        return view;
    }

    protected void initData() {

    }

    protected void initViews(View view) {


    }

    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    protected void toastShort(String message){
        if (getActivity()!=null) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity)  getActivity()).toastShort(message);
            }
        }
    }

    protected void toastLong(String message){
        if (getActivity()!=null) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity)  getActivity()).toastLong(message);
            }
        }
    }
}
