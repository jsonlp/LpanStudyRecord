package com.lpan.study.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.R;
import com.lpan.study.adapter.SimpleRecyclerAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.view.CustomLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*************************************************************************************
 * Author: liaopan
 * Date: 2018/7/13
 *******************************************************************/

public class CustomLayoutMFragment extends ButterKnifeFragment{

    @BindView(R.id.recyclerview)
    RecyclerView  mRecyclerView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_layout_manager;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("title" + i);
        }
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(new SimpleRecyclerAdapter(R.layout.item_text,list));
    }

    private RecyclerView.LayoutManager getLayoutManager(){
        return new CustomLayoutManager();
    }

}
