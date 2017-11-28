package com.lpan.study.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;

/**
 * Created by lpan on 2017/11/28.
 */

public class ShareElementFragment extends ButterKnifeFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_share_element;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
