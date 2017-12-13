package com.lpan.study.fragment;



import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.view.TouchImageView;


import butterknife.BindView;

/**
 * Created by liaopan on 2017/12/11 16:44.
 */

public class TouchImageFragment extends ButterKnifeFragment {

    @BindView(R.id.image1)
    TouchImageView image1;

    @BindView(R.id.image2)
    TouchImageView image2;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_touch_image;
    }

    @Override
    protected void initData() {
        super.initData();
        image1.setImageResource(R.drawable.wall06);
//        image2.setImageResource(R.drawable.wall06);

//        image1.setImageResource(R.drawable.edit_close);
        image2.setImageResource(R.drawable.edit_close);
        image2.setRotatable(true);
    }

}
