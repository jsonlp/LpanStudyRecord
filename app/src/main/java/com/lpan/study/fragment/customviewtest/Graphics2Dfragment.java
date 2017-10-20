package com.lpan.study.fragment.customviewtest;

import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/10/20.
 */

public class Graphics2Dfragment extends ButterKnifeFragment {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_graphics_2d;
    }

    @Override
    protected boolean withActionBar() {
        return true;
    }

    @Override
    protected String getActionBarTitle() {
        return "Graphics 2D";
    }

}
