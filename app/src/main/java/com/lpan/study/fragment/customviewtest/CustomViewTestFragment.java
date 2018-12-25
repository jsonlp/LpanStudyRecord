package com.lpan.study.fragment.customviewtest;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.study.adapter.CustomViewTestAdapter;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.model.RecyclerItemInfo;
import com.lpan.R;
import com.lpan.study.view.actionbar.ActionbarConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2017/10/18.
 */

public class CustomViewTestFragment extends BaseActionbarFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    private CustomViewTestAdapter mAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_view_test;
    }


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(getAdapter());
    }

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return getDefaultActionbar("custom view");
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
        list.add(new RecyclerItemInfo("rounded mask", new RoundedMaskFragment()));
        list.add(new RecyclerItemInfo("The drawing process of the view", new DrawingViewFragment()));
        list.add(new RecyclerItemInfo("Point and Rect class", new PointAndRectFragment()));
        list.add(new RecyclerItemInfo("Paint and Canvas", new CanvasAndPaintFragment()));
        list.add(new RecyclerItemInfo("Graphics 2D", new Graphics2Dfragment()));
        list.add(new RecyclerItemInfo("Matrix", new MatrixFragment()));
        list.add(new RecyclerItemInfo("path animation", new PathAnimationFragment()));
        list.add(new RecyclerItemInfo("outline", new OutlineFragment()));

        getAdapter().addItem(list);
        getAdapter().notifyDataSetChanged();
    }
}
