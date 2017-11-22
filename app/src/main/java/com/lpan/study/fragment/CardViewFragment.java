package com.lpan.study.fragment;

import android.support.v7.widget.CardView;
import android.view.View;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.Utils;
import com.lpan.study.utils.ViewUtils;
import com.lpan.study.view.CircleImageView;

import butterknife.BindView;

/**
 * Created by lpan on 2017/6/2.
 */

public class CardViewFragment extends ButterKnifeFragment {

    @BindView(R.id.cardview)
    CardView mCardView;


    @BindView(R.id.circle_image)
    CircleImageView mCircleImageView;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_custom_cardview;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mCircleImageView.setImageResource(R.drawable.cat_image);

        configCard();
    }

    private void configCard() {
//        mCardView.setRadius(ViewUtils.ONE_DP * 15);
//        mCardView.setBackgroundColor(Color.GRAY);
        if (Utils.hasLollipop()) {
            mCardView.setElevation(20 * ViewUtils.ONE_DP);
        }
//        mCardView.setMaxCardElevation(20 * ViewUtils.ONE_DP);

    }
}
