package com.lpan.study.view.actionbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lpan.R;
import com.lpan.study.context.AppContext;


/**
 * Created by lpan on 2018/4/24.
 */

public class MyActionbarView extends FrameLayout {

    private View mBackgroundView;
    private TextView mLeftText;
    private TextView mRightText;
    private TextView mTitleView;
    private ImageView mLeftIcon;
    private ImageView mRightIcon;

    public MyActionbarView(Context context) {
        super(context);
        init(context);
    }

    public MyActionbarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyActionbarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_my_actionbar, this);
        mLeftText = view.findViewById(R.id.left);
        mRightText = view.findViewById(R.id.right);
        mTitleView = view.findViewById(R.id.title);
        mBackgroundView = view.findViewById(R.id.actionbar_background);
        mLeftIcon = view.findViewById(R.id.left_icon);
        mRightIcon = view.findViewById(R.id.right_icon);

    }

    public void setStatusbarPadding(int paddingTop) {
        if (mBackgroundView != null) {
            mBackgroundView.setPadding(0, paddingTop, 0, 0);
        }
    }

    public void setRightTextEnable(boolean enable) {
        if (mRightText != null) {
            mRightText.setEnabled(enable);
        }
    }

    public void updateRight(int id, boolean enable) {
        if (mRightText != null) {
            mRightText.setText(id);
            mRightText.setEnabled(enable);
        }
    }

    public void configActionbar(ActionbarConfig config) {
        if (config == null) {
            return;
        }

        //config view
        mTitleView.setText(config.getTitle());
        mLeftText.setText(config.getLeftText());
        mRightText.setText(config.getRightText());
        if (config.getTitleId() != 0) {
            mTitleView.setText(AppContext.getContext().getString(config.getTitleId()));
        }
        if (config.getLeftTextId() != 0) {
            mLeftText.setText(AppContext.getContext().getString(config.getLeftTextId()));
        }
        if (config.getRightTextId() != 0) {
            mRightText.setText(AppContext.getContext().getString(config.getRightTextId()));
        }

        if (config.getBackgroundColor() != 0) {
            mBackgroundView.setBackgroundColor(AppContext.getContext().getResources().getColor(config.getBackgroundColor()));
        }
        if (config.getLeftTextColorId() != 0) {
            mLeftText.setTextColor(AppContext.getContext().getResources().getColor(config.getLeftTextColorId()));
        }
        if (config.getTitleTextColorId() != 0) {
            mTitleView.setTextColor(AppContext.getContext().getResources().getColor(config.getTitleTextColorId()));
        }
        if (config.getRightTextColorId() != 0) {
            mRightText.setTextColor(AppContext.getContext().getResources().getColor(config.getRightTextColorId()));
        }
        mBackgroundView.setPadding(0, config.getPaddingTop(), 0, 0);

        if (config.getLeftImageId() != 0) {
            mLeftIcon.setImageResource(config.getLeftImageId());
            mLeftIcon.setVisibility(VISIBLE);
        } else {
            mLeftIcon.setVisibility(GONE);
        }
        if (config.getRightImageId() != 0) {
            mRightIcon.setImageResource(config.getRightImageId());
            mRightIcon.setVisibility(VISIBLE);
        } else {
            mRightIcon.setVisibility(GONE);
        }

        mLeftText.setOnClickListener(config.getOnLeftClickListener());
        mLeftIcon.setOnClickListener(config.getOnLeftClickListener());
        mRightText.setOnClickListener(config.getOnRightClickListener());
        mRightIcon.setOnClickListener(config.getOnRightClickListener());
        mTitleView.setOnClickListener(config.getOnTitleClickListener());
    }

    //改变其他属性 可以直接通过该view操作
    public View getBackgroundView() {
        return mBackgroundView;
    }

    public TextView getLeftText() {
        return mLeftText;
    }

    public TextView getRightText() {
        return mRightText;
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public ImageView getLeftIcon() {
        return mLeftIcon;
    }

    public ImageView getRightIcon() {
        return mRightIcon;
    }
}
