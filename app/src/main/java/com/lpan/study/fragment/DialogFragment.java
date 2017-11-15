package com.lpan.study.fragment;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.utils.Toaster;
import com.lpan.R;

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
