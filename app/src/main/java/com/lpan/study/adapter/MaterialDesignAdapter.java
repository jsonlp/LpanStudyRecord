package com.lpan.study.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lpan.study.constants.Constants;
import com.lpan.study.fragment.SubViewPagerFragment;


/**
 * Created by lpan on 2018/3/8.
 */

public class MaterialDesignAdapter extends FragmentPagerAdapter {

    public static final int PAGE_COUNT = 3;

    private Context mContext;

    public MaterialDesignAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }



    @Override
    public Fragment getItem(int position) {
        int type = -1;
        switch (position) {
            case Constants.PAGE_TYPE_PHOTO:
                type = 0;
                break;

            case Constants.PAGE_TYPE_VIDEO:
                type = 1;
                break;

            case Constants.PAGE_TYPE_BOOK:
                type = 2;

                break;
        }
        return SubViewPagerFragment.newInstance(type);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
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
