package com.lpan.study.view.actionbar;

import android.view.View;

/**
 * Created by lpan on 2018/3/14.
 */

public class ActionbarConfig {

    private String title;

    private String leftText;

    private String rightText;

    private int titleId;

    private int leftTextId;

    private int rightTextId;

    private int backgroundColor;

    private int leftImageId;

    private int rightImageId;

    private View.OnClickListener onLeftClickListener;

    private View.OnClickListener onRightClickListener;

    private View.OnClickListener onTitleClickListener;

    private int leftTextColorId;
    private int titleTextColorId;
    private int rightTextColorId;
    private int paddingTop;


    private ActionbarConfig(Build build) {
        this.title = build.title;
        this.leftText = build.leftText;
        this.rightText = build.rightText;
        this.titleId = build.titleId;
        this.leftTextId = build.leftTextId;
        this.rightTextId = build.rightTextId;
        this.backgroundColor = build.backgroundColor;
        this.leftImageId = build.leftImageId;
        this.rightImageId = build.rightImageId;
        this.onLeftClickListener = build.onLeftClickListener;
        this.onRightClickListener = build.onRightClickListener;
        this.onTitleClickListener = build.onTitleClickListener;
        this.leftTextColorId = build.leftTextColorId;
        this.leftTextColorId = build.leftTextColorId;
        this.titleTextColorId = build.titleTextColorId;
        this.rightTextColorId = build.rightTextColorId;
        this.paddingTop = build.paddingTop;

    }

    public String getTitle() {
        return title;
    }

    public String getLeftText() {
        return leftText;
    }

    public String getRightText() {
        return rightText;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getLeftTextId() {
        return leftTextId;
    }

    public int getRightTextId() {
        return rightTextId;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getLeftImageId() {
        return leftImageId;
    }

    public int getRightImageId() {
        return rightImageId;
    }

    public View.OnClickListener getOnLeftClickListener() {
        return onLeftClickListener;
    }

    public View.OnClickListener getOnRightClickListener() {
        return onRightClickListener;
    }

    public View.OnClickListener getOnTitleClickListener() {
        return onTitleClickListener;
    }

    public int getLeftTextColorId() {
        return leftTextColorId;
    }

    public int getTitleTextColorId() {
        return titleTextColorId;
    }

    public int getRightTextColorId() {
        return rightTextColorId;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public static class Build {

        private String title;

        private String leftText;

        private String rightText;

        private int titleId;

        private int leftTextId;

        private int rightTextId;

        private int backgroundColor;

        private int leftImageId;

        private int rightImageId;

        private View.OnClickListener onLeftClickListener;

        private View.OnClickListener onRightClickListener;

        private View.OnClickListener onTitleClickListener;

        private int leftTextColorId;
        private int titleTextColorId;
        private int rightTextColorId;
        private int paddingTop;

        public Build() {

        }

        public Build setTitle(String title) {
            this.title = title;
            return this;
        }

        public Build setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Build setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }


        public Build setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Build setLeftImageId(int leftImageId) {
            this.leftImageId = leftImageId;
            return this;
        }

        public Build setRightImageId(int rightImageId) {
            this.rightImageId = rightImageId;
            return this;
        }

        public Build setOnLeftClickListener(View.OnClickListener onLeftClickListener) {
            this.onLeftClickListener = onLeftClickListener;
            return this;
        }

        public Build setOnRightClickListener(View.OnClickListener onRightClickListener) {
            this.onRightClickListener = onRightClickListener;
            return this;
        }

        public Build setOnTitleClickListener(View.OnClickListener onTitleClickListener) {
            this.onTitleClickListener = onTitleClickListener;
            return this;
        }

        public Build setTitleId(int titleId) {
            this.titleId = titleId;
            return this;
        }

        public Build setLeftTextId(int leftTextId) {
            this.leftTextId = leftTextId;
            return this;
        }

        public Build setRightTextId(int rightTextId) {
            this.rightTextId = rightTextId;
            return this;
        }

        public Build setLeftTextColorId(int leftTextColorId) {
            this.leftTextColorId = leftTextColorId;
            return this;

        }

        public Build setTitleTextColorId(int titleTextColorId) {
            this.titleTextColorId = titleTextColorId;
            return this;

        }

        public Build setRightTextColorId(int rightTextColorId) {
            this.rightTextColorId = rightTextColorId;
            return this;

        }

        public Build setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
            return this;
        }

        public ActionbarConfig build() {
            return new ActionbarConfig(this);
        }
    }

}
