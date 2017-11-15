package com.lpan.study.fragment.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.study.activity.BaseActivity;
import com.lpan.R;

import butterknife.ButterKnife;

/**
 * Created by lpan on 2016/12/19.
 */

public abstract class BaseFragment extends Fragment {

    protected Handler mHandler = new Handler();

    protected View mActionBarBackground;

    protected TextView mActionBarLeftText;

    protected ImageView mActionBarLeftButton;

    protected TextView mActionBarTitle;

    protected TextView mActionBarRightText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);

        initActionBar(view);
        initViews(view);

        initData();

        return view;
    }

    protected void initData() {

    }

    protected void initViews(View view) {
        if (useButterknife()) {
            ButterKnife.bind(this, view);
        }
    }

    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    protected boolean useButterknife() {
        return false;
    }


    // config actionbar
    protected boolean withActionBar() {
        return false;
    }

    protected void initActionBar(View view) {
        if (withActionBar()) {
            mActionBarBackground = view.findViewById(R.id.action_bar_background);
            mActionBarLeftText = (TextView) view.findViewById(R.id.actionbar_left_text);
            mActionBarLeftButton = (ImageView) view.findViewById(R.id.actionbar_left_icon);
            mActionBarTitle = (TextView) view.findViewById(R.id.actionbar_title);
            mActionBarRightText = (TextView) view.findViewById(R.id.actionbar_right_text);

            mActionBarLeftText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity()!=null) {
                        getActivity().finish();
                    }
                }
            });
            mActionBarLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity()!=null) {
                        getActivity().finish();
                    }
                }
            });
            mActionBarRightText.setOnClickListener(getActionBarRightClickListener());
            mActionBarLeftButton.setVisibility(hideActionBarLeftIcon() ? View.GONE : View.VISIBLE);
            mActionBarLeftText.setVisibility(hideActionBarLeftIcon() ? View.VISIBLE : View.GONE);

            mActionBarTitle.setText(getActionBarTitle());
            mActionBarLeftText.setText(getActionBarLeftText());
            mActionBarLeftText.setText(getActionBarRightText());

        }


    }

    protected String getActionBarTitle() {
        return "";
    }

    protected String getActionBarLeftText() {
        return "";
    }

    protected String getActionBarRightText() {
        return "";
    }

    protected View.OnClickListener getActionBarRightClickListener() {
        return null;
    }

    protected boolean hideActionBarLeftIcon() {
        return false;
    }

    protected void toastShort(String message) {
        if (getActivity() != null) {
            if (getActivity() instanceof BaseActivity) {
                ((BaseActivity) getActivity()).toastShort(message);
            }
        }
    }
}
