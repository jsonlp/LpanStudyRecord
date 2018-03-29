package com.lpan.study.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.activity.TransparentActivity;
import com.lpan.study.constants.Constants;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.FragmentUtils;
import com.lpan.study.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by lpan on 2017/11/27.
 */

public class TransAnimationFragment extends ButterKnifeFragment implements View.OnClickListener {

    @BindView(R.id.text8)
    TextView mElement1;

    @BindView(R.id.text9)
    TextView mElement2;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_trans_animation;
    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3,
            R.id.text4, R.id.text5, R.id.text6,
            R.id.text7, R.id.text8, R.id.text9})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text1:

                FragmentUtils.navigateWithNoAnimation(getActivity(), new MyDialogFragment());
                break;

            case R.id.text2:
                FragmentUtils.navigateWithUpAnimation(getActivity(), new MyDialogFragment());
                break;


            case R.id.text3:
                FragmentUtils.navigateWithFadeAnimation(getActivity(), new MyDialogFragment());

                break;

            case R.id.text4:
                FragmentUtils.navigateWithLongFadeAnimation(getActivity(), new MyDialogFragment());

                break;

            case R.id.text5:
                FragmentUtils.navigateWithSlideAnimation(getActivity(), new MyDialogFragment());

                break;

            case R.id.text6:
                FragmentUtils.navigateWithExplodeAnimation(getActivity(), new MyDialogFragment());

                break;

            case R.id.text7:
                FragmentUtils.navigateWithFade21Animation(getActivity(), new MyDialogFragment());

                break;

            case R.id.text8:
                FragmentUtils.navigateWithShareElementAnimation(getActivity(), new ShareElementFragment(), v, "textview");

                break;

            case R.id.text9:
                navigation(getActivity(), new ShareElementFragment(), mElement1, "textview", mElement2, "textview2");
                break;
        }
    }

    private void navigation(Activity activity, Fragment fragment, View view1, String viewName1, View view2, String viewName2) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.ANIMATION_SHARE_ELEMENT, true);

        Intent intent = new Intent(activity, TransparentActivity.class);
        intent.putExtra(Constants.EXTRAS_CLASS_NAME, fragment.getClass()
                .getName());
        intent.putExtra(Constants.EXTRAS_BUNDLE, bundle);
        if (Utils.hasLollipop()) {
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, Pair.create(view1, viewName1), Pair.create(view2, viewName2)).toBundle());
        }
    }
}
