package com.lpan.study.fragment;

import android.view.View;

import com.github.captain_miao.optroundcardview.OptRoundCardView;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/6/2.
 */

public class CustomCardViewFragment extends BaseFragment {

    private OptRoundCardView mCardView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_cardview;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);


//        mCardView.setRadius(20 * 3);

//        mCardView.showCorner(false, false, false, false);

    }
}
