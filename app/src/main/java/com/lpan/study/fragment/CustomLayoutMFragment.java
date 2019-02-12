package com.lpan.study.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.R;
import com.lpan.study.adapter.SimpleRecyclerAdapter;
import com.lpan.study.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CustomLayoutMFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private SimpleRecyclerAdapter mAdapter;

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
        getAdapter().addData(list);
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.layout_header,null);
//        getAdapter().addHeaderView(header);
        mRecyclerView.setAdapter(getAdapter());

    }

    private RecyclerView.LayoutManager getLayoutManager() {
//        return new CustomLayoutManager();
        return new LinearLayoutManager(getActivity());
    }

    private SimpleRecyclerAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new SimpleRecyclerAdapter(R.layout.item_normal_user_with_swipe);
        }
        return mAdapter;
    }

}
