package com.lpan.study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lpan.R;
import com.lpan.study.adapter.SimpleRecyclerAdapter;
import com.lpan.study.constants.Constants;
import com.lpan.study.fragment.base.ButterKnifeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/8.
 */

public class SubViewPagerFragment extends ButterKnifeFragment {

    public static final String PAGE_TYPE = "type";

    private int mType;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    public static SubViewPagerFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt(PAGE_TYPE, type);
        SubViewPagerFragment subViewPagerFragment = new SubViewPagerFragment();
        subViewPagerFragment.setArguments(bundle);

        return subViewPagerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt(PAGE_TYPE);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_subviewpager;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(getPageTitle(mType) + i);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new SimpleRecyclerAdapter(R.layout.item_text, list));
    }


    private String getPageTitle(int type) {
        String title = "";
        switch (type) {
            case Constants.PAGE_TYPE_PHOTO:
                title = "photo";
                break;

            case Constants.PAGE_TYPE_VIDEO:
                title = "video";
                break;

            case Constants.PAGE_TYPE_BOOK:
                title = "book";
                break;
        }
        return title;
    }


}
