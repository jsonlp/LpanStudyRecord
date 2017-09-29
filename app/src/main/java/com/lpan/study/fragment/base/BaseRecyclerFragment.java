package com.lpan.study.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpan.study.adapter.RecyclerViewAdapter;
import com.lpan.study.view.pulltorefresh.JiemoRecyclerView;
import com.lpan.study.view.pulltorefresh.RecyclerViewRefreshListView;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/9/29.
 */

public abstract class BaseRecyclerFragment extends BaseFragment {

    private RecyclerViewRefreshListView mPullRefreshRecyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);
        mPullRefreshRecyclerView = (RecyclerViewRefreshListView) view.findViewById(R.id.pull_to_refresh_recyclerview);

        initAdapter();

        return view;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_pulltorefresh_recyclerview;
    }


    protected void initAdapter() {
        getPullToRefreshView().setLayoutManager(getLayoutManger());
        if (mPullRefreshRecyclerView != null) {
            mPullRefreshRecyclerView.getRefreshableView().setAdapter(getAdapter());
        }
    }

    public RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(getActivity());
    }

    protected abstract RecyclerViewAdapter getAdapter();


    protected JiemoRecyclerView getPullToRefreshView() {
        if (mPullRefreshRecyclerView == null) {
            return null;
        }
        return mPullRefreshRecyclerView.getRefreshableView();
    }

    public RecyclerViewRefreshListView getRecyclerViewRefreshListView() {
        return mPullRefreshRecyclerView;
    }
}
