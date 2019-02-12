package com.lpan.study.fragment;

import android.view.View;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liaopan on 2018/1/22 10:24.
 */

public class TestFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.layout1)
    View layout1;

    @BindView(R.id.layout2)
    View layout2;

    @BindView(R.id.layout3)
    View layout3;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        layout3.setVisibility(View.GONE);
    }

    @OnClick({R.id.layout1, R.id.layout2})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout1:
                layout3.setVisibility(View.GONE);
                break;

            case R.id.layout2:
                layout3.setVisibility(View.VISIBLE);
                break;
        }
    }
}
