package com.lpan.study.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.lpan.study.fragment.base.BaseFragment;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.study.utils.Variables;
import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/9/7.
 */

public class UserProfileFragment extends BaseFragment {

    public static final String EXTRA_IMAGE_PATH = "extra_image_path";

    private ImageView mImageView;

    private ScrollView  mScrollView;

    private int mImageId;

    public static void show(Context context,View view, int imageId) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_IMAGE_PATH, imageId);
        FragmentUtils.navigateToInNewActivityWithTranstion(context,view, new UserProfileFragment(), bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mImageId = getArguments().getInt(EXTRA_IMAGE_PATH);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Variables.setHasInDetail(false);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user_profile;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        mImageView = (ImageView) view.findViewById(R.id.image1);
        mImageView.setImageResource(mImageId);
        ViewCompat.setTransitionName(mImageView,"image");
        mScrollView = (ScrollView) view.findViewById(R.id.scrollView);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }


}
