package com.lpan.study.fragment;


import android.support.v7.widget.RecyclerView;

import com.lpan.R;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.view.actionbar.ActionbarConfig;

import butterknife.BindView;


/**
 * Created by lpan on 2018/4/24.
 */

public class HttpStudyFragment extends BaseActionbarFragment {

    @BindView(R.id.recyclerview) RecyclerView mRecyclerView;


    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("android net");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_http_study;
    }
}
