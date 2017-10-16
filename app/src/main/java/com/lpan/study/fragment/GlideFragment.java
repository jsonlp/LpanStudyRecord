package com.lpan.study.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.Log;
import com.lpan.study.utils.Toaster;
import com.test.lpanstudyrecord.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/9/29.
 */

public class GlideFragment extends BaseFragment implements View.OnClickListener {

    public static final String IMAGE_PATH = "http://pic6.nipic.com/20091207/3337900_161732052452_2.jpg";

    @BindView(R.id.text1)
    TextView mButton;

    @BindView(R.id.image1)
    ImageView mImageView;


    private Integer imagePaths[] = {
            R.drawable.wall01, R.drawable.wall02,
            R.drawable.wall03, R.drawable.wall04,
            R.drawable.wall05, R.drawable.wall06,
            R.drawable.wall07, R.drawable.wall08,
            R.drawable.wall09, R.drawable.wall10}; // 20个图片资源


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_glide;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        ButterKnife.bind(this,view);
    }

    @OnClick({R.id.text1})
    @Override
    public void onClick(View v) {
        if (Log.DEBUG) {
            Toaster.toastShort("[android-debug]"+"GlideFragment:  onClick   "+"");
        }
        Glide.with(this)
                .load(IMAGE_PATH)
                .placeholder(R.drawable.image_loading)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(R.drawable.ic_error)
                .override(100,100)
                .into(mImageView);
    }
}
