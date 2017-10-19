package com.lpan.study.fragment.customviewtest;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.study.adapter.CustomViewTestAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.RecyclerItemInfo;
import com.test.lpanstudyrecord.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/18.
 */

public class CustomViewTestFragment extends ButterKnifeFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    private CustomViewTestAdapter mAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_view_test;
    }

    @Override
    protected boolean withActionBar() {
        return true;
    }

    @Override
    protected String getActionBarTitle() {
        return "custom view base use";
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(getAdapter());
    }

    public CustomViewTestAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new CustomViewTestAdapter(getActivity());
        }
        return mAdapter;
    }

    @Override
    protected void initData() {
        super.initData();
        List<RecyclerItemInfo> list = new ArrayList<>();
        list.add(new RecyclerItemInfo("The drawing process of the view", new DrawingViewFragment()));
        list.add(new RecyclerItemInfo("Point and Rect class", new PointAndRectFragment()));
        list.add(new RecyclerItemInfo("Paint and Canvas", new CanvasAndPaintFragment()));

        getAdapter().addItem(list);
        getAdapter().notifyDataSetChanged();
    }
}
