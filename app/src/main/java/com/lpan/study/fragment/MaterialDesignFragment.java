package com.lpan.study.fragment;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.BuildConfig;
import com.lpan.R;
import com.lpan.study.adapter.MaterialDesignAdapter;
import com.lpan.study.adapter.SimpleRecyclerAdapter;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.Log;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/8.
 */

public class MaterialDesignFragment extends ButterKnifeFragment implements AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    private MaterialDesignAdapter mAdapter;


//    @BindView(R.id.recyclerview)
//    RecyclerView mRecyclerView;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @BindView(R.id.appbar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.info_layout)
    View mInfoLayout;

    @BindView(R.id.iv_avatar)
    ImageView mImageView;

    @BindView(R.id.intro)
    TextView mIntro;


    private int mTotalScrollRange;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_material_design;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mAdapter = new MaterialDesignAdapter(getActivity(), getFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("" + i * 10000);
        }
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setAdapter(new SimpleRecyclerAdapter(R.layout.item_text, list));

//        mTabLayout.addTab(mTabLayout.newTab().setText("page1"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("page2"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("page3"));


        mAppBarLayout.addOnOffsetChangedListener(this);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        mTotalScrollRange = appBarLayout.getTotalScrollRange();
        if (mTotalScrollRange == 0) {
            mTotalScrollRange = appBarLayout.getTotalScrollRange();
        } else {
            int percentage = (Math.abs(verticalOffset)) * 100 / (int) (mTotalScrollRange + mTotalScrollRange * 0.7f);
            float scale = 1 - percentage / 100f;

            if (BuildConfig.DEBUG) {
                Log.d("MaterialDesignFragment", "onOffsetChanged--------" + verticalOffset + "  percent=" + percentage + "  scale=" + scale);
            }
            mInfoLayout.setScaleX(scale);
            mInfoLayout.setScaleY(scale);
            mInfoLayout.setTranslationY(mInfoLayout.getHeight() * (1 - scale) / 2);

            mIntro.setAlpha(scale - 0.5f);

        }

    }
}
