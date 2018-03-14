package com.lpan.study.fragment.customviewtest;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.fragment.base.ButterKnifeFragment;
import com.lpan.study.model.ActionbarConfig;

import butterknife.BindView;

/**
 * Created by lpan on 2018/3/14.
 */

public abstract class BaseActionbarFragment extends ButterKnifeFragment {

    @BindView(R.id.action_bar_background)
    View mActionBarBackground;

    @BindView(R.id.actionbar_left_text)
    TextView mActionBarLeftText;

    @BindView(R.id.actionbar_left_icon)
    ImageView mActionBarLeftButton;

    @BindView(R.id.actionbar_title)
    TextView mActionBarTitle;

    @BindView(R.id.actionbar_right_text)
    TextView mActionBarRightText;

    @BindView(R.id.actionbar_right_icon)
    ImageView mActionBarRightButton;

    @Override
    protected void initViews(View view) {
        configActionBar(view);
        super.initViews(view);

    }

    protected abstract ActionbarConfig getActionbarConfig();

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_base_actionbar;
    }

    private void configActionBar(View view) {
        ActionbarConfig config = getActionbarConfig();
        if (view == null || config == null) {
            return;
        }
        //init view
        mActionBarBackground = view.findViewById(R.id.action_bar_background);
        if (mActionBarBackground == null) {
            throw new IllegalStateException("BaseActionbarFragment needs add actionbar_layout in xml.");
        }
        mActionBarLeftText = (TextView) view.findViewById(R.id.actionbar_left_text);
        mActionBarLeftButton = (ImageView) view.findViewById(R.id.actionbar_left_icon);
        mActionBarTitle = (TextView) view.findViewById(R.id.actionbar_title);
        mActionBarRightText = (TextView) view.findViewById(R.id.actionbar_right_text);
        mActionBarRightButton = (ImageView) view.findViewById(R.id.actionbar_right_icon);


        //config view
        mActionBarLeftButton.setVisibility(config.isShowLeftButton() ? View.VISIBLE : View.GONE);
        mActionBarLeftText.setVisibility(config.isShowLeftButton() ? View.GONE : View.VISIBLE);
        mActionBarRightButton.setVisibility(config.isShowRightButton() ? View.VISIBLE : View.GONE);
        mActionBarRightText.setVisibility(config.isShowRightButton() ? View.GONE : View.VISIBLE);
        mActionBarTitle.setText(config.getTitle());
        mActionBarLeftText.setText(config.getLeftText());
        mActionBarLeftText.setText(config.getRightText());
        if (config.getBackgroundColor() != 0) {
            mActionBarBackground.setBackgroundColor(config.getBackgroundColor());
        }
        if (config.getLeftImageId() != 0) {
            mActionBarLeftButton.setImageResource(config.getLeftImageId());
        }
        if (config.getRightImageId() != 0) {
            mActionBarRightButton.setImageResource(config.getRightImageId());
        }
        if (config.getLeftClickListener() == null) {
            mActionBarLeftText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
            mActionBarLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getActivity() != null) {
                        getActivity().finish();
                    }
                }
            });
        } else {
            mActionBarLeftText.setOnClickListener(config.getRightClickListener());
            mActionBarLeftButton.setOnClickListener(config.getRightClickListener());
        }
        mActionBarRightText.setOnClickListener(config.getRightClickListener());
        mActionBarRightButton.setOnClickListener(config.getRightClickListener());
    }

    protected ActionbarConfig getDefaultActionbar(String title) {
        return new ActionbarConfig.Build()
                .setShowLeftButton(true)
                .setShowRightButton(false)
                .setTitle(title)
                .build();
    }

}
