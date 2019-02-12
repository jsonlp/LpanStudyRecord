package com.lpan.study.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lpan.R;
import com.lpan.study.adapter.CardViewAdapter;
import com.lpan.study.fragment.base.BaseActionbarFragment;
import com.lpan.study.view.actionbar.ActionbarConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2017/6/2.
 */

public class CardViewFragment extends BaseActionbarFragment {

    private CardViewAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_cardview;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyclerView.setAdapter(getAdapter());
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("111");
        list.add("111");
        list.add("111");
        getAdapter().addData(list);
        getAdapter().notifyDataSetChanged();
    }

    @Override
    protected ActionbarConfig getActionbarConfig() {
        return null;
    }

    protected BaseQuickAdapter getAdapter() {
        if (mAdapter == null) {
            mAdapter = new CardViewAdapter(R.layout.item_card_view);
        }
        return mAdapter;
    }
}
