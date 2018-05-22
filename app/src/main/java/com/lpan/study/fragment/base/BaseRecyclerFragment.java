package com.lpan.study.fragment.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lpan.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/16.
 */

public abstract class BaseRecyclerFragment<T> extends BaseActionbarFragment implements BaseQuickAdapter.OnItemClickListener{

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    protected List<T> mList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(getLayoutManager());
        mRecyclerView.setAdapter(getAdapter());

        getAdapter().setOnItemClickListener(this);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected abstract BaseQuickAdapter getAdapter();

    protected RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
