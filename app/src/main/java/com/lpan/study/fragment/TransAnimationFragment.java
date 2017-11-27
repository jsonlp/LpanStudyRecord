package com.lpan.study.fragment;

import android.view.View;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.FragmentUtils;

import butterknife.OnClick;

/**
 * Created by lpan on 2017/11/27.
 */

public class TransAnimationFragment extends ButterKnifeFragment implements View.OnClickListener {

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_trans_animation;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text1:

                FragmentUtils.navigateWithNoAnimation(getActivity(), new DialogFragment());
                break;

            case R.id.text2:
                FragmentUtils.navigateWithUpAnimation(getActivity(), new DialogFragment());
                break;


            case R.id.text3:
                FragmentUtils.navigateWithFadeAnimation(getActivity(), new DialogFragment());

                break;

            case R.id.text4:
                FragmentUtils.navigateWithLongFadeAnimation(getActivity(), new DialogFragment());

                break;
        }
    }
}
