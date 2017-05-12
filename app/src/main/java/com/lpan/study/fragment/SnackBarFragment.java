package com.lpan.study.fragment;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;

import com.test.lpanstudyrecord.R;

/**
 * Created by lpan on 2017/4/25.
 */

public class SnackBarFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mLayout;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_snackbar;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);

        mLayout = (LinearLayout) view.findViewById(R.id.layout);

        view.findViewById(R.id.text).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Snackbar.make(mLayout, "123", Snackbar.LENGTH_LONG).show();
    }
}
