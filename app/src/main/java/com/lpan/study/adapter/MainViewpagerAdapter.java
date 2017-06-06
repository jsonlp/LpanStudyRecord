package com.lpan.study.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lpan.study.utils.CollectionUtils;

import java.util.List;

/**
 * Created by lpan on 2017/6/6.
 */

public class MainViewpagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = MainViewpagerAdapter.class.getSimpleName();

    public static final int ITEM_COUNT = 4;


    private List<Fragment> mList;

    public MainViewpagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        mList = list;

    }

    @Override
    public Fragment getItem(int position) {
        if (CollectionUtils.isEmpty(mList)) {
            return null;
        }
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(mList) ? 0 : mList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        return super.instantiateItem(container, position);
    }
}
