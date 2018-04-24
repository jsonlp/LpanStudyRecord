package com.lpan.study.fragment.base;

import android.support.v7.widget.RecyclerView;

import com.lpan.R;
import com.lpan.study.view.actionbar.MyActionbarView;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/16.
 */

public abstract class BaseRecyclerFragment extends ButterKnifeFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;



}
