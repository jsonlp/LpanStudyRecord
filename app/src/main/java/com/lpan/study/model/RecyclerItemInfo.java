package com.lpan.study.model;

import android.support.v4.app.Fragment;

/**
 * Created by lpan on 2017/10/18.
 */

public class RecyclerItemInfo {

    private String mTitle;

    private Fragment mFragment;

    public RecyclerItemInfo() {
    }

    public RecyclerItemInfo(String title, Fragment fragment) {
        mTitle = title;
        mFragment = fragment;
    }

    public RecyclerItemInfo(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }
}
