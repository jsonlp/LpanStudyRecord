package com.lpan.study.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.constants.Constants;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.Toaster;
import com.lpan.R;
import com.lpan.study.utils.Utils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lpan on 2017/10/10.
 */

public class DialogFragment extends ButterKnifeFragment implements View.OnClickListener {

    @BindView(R.id.text1)
    TextView mTextView1;

    @BindView(R.id.text2)
    TextView mTextView2;

    @BindView(R.id.text3)
    TextView mTextView3;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments()!=null) {
            boolean slide = getArguments().getBoolean(Constants.ANIMATION_SLIDE);
            boolean explode = getArguments().getBoolean(Constants.ANIMATION_EXPLODE);
            boolean fade21 = getArguments().getBoolean(Constants.ANIMATION_FADE21);

            if (Utils.hasLollipop()) {
                if (slide) {
                    getActivity().getWindow().setEnterTransition(new Slide().setDuration(1500));
                    getActivity().getWindow().setExitTransition(new Slide().setDuration(1500));
                }else if(explode){
                    getActivity().getWindow().setEnterTransition(new Explode().setDuration(1500));
                    getActivity().getWindow().setExitTransition(new Explode().setDuration(1500));
                }else if(fade21){
                    getActivity().getWindow().setEnterTransition(new Fade().setDuration(1500));
                    getActivity().getWindow().setExitTransition(new Fade().setDuration(1500));
                }
            }
        }

    }

    @OnClick({R.id.text1, R.id.text2, R.id.text3})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text1:
                showDialog();
                break;

            case R.id.text2:
                showToast();
                break;

            case R.id.text3:
                showSnackBar();
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
        build.setTitle("title")
                .setMessage("content")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastShort("ok");
                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

    private void showToast() {
        Toaster.toastShort("111111");
    }

    private void showSnackBar() {
        Snackbar.make(mTextView1, "2222", Snackbar.LENGTH_LONG)
                .setAction("yes", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastShort("yes");
                    }
                })
                .show();
    }
}
