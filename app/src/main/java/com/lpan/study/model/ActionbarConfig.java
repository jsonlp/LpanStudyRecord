package com.lpan.study.model;

import android.view.View;

/**
 * Created by lpan on 2018/3/14.
 */

public class ActionbarConfig {

    private String title;

    private String leftText;

    private String rightText;

    private boolean showLeftButton;

    private boolean showRightButton;

    private int backgroundColor;

    private int leftImageId;

    private int rightImageId;

    private View.OnClickListener leftClickListener;

    private View.OnClickListener rightClickListener;


    private ActionbarConfig(Build build) {
        this.showLeftButton = build.showLeftButton;
        this.showRightButton = build.showRightButton;
        this.title = build.title;
        this.leftText = build.leftText;
        this.rightText = build.rightText;
        this.backgroundColor = build.backgroundColor;
        this.leftImageId = build.leftImageId;
        this.rightImageId = build.rightImageId;
        this.leftClickListener = build.leftClickListener;
        this.rightClickListener = build.rightClickListener;

    }

    public boolean isShowLeftButton() {
        return showLeftButton;
    }

    public boolean isShowRightButton() {
        return showRightButton;
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

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getLeftImageId() {
        return leftImageId;
    }

    public int getRightImageId() {
        return rightImageId;
    }

    public View.OnClickListener getLeftClickListener() {
        return leftClickListener;
    }

    public View.OnClickListener getRightClickListener() {
        return rightClickListener;
    }


    public static class Build {

        private boolean showLeftButton;

        private boolean showRightButton;

        private String title;

        private String leftText;

        private String rightText;

        private int backgroundColor;

        private int leftImageId;

        private int rightImageId;

        private View.OnClickListener leftClickListener;

        private View.OnClickListener rightClickListener;

        public Build() {

        }

        public Build setShowLeftButton(boolean showLeftButton) {
            this.showLeftButton = showLeftButton;
            return this;
        }

        public Build setShowRightButton(boolean showRightButton) {
            this.showRightButton = showRightButton;
            return this;
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

        public Build setLeftClickListener(View.OnClickListener leftClickListener) {
            this.leftClickListener = leftClickListener;
            return this;
        }

        public Build setRightClickListener(View.OnClickListener rightClickListener) {
            this.rightClickListener = rightClickListener;
            return this;
        }

        public ActionbarConfig build() {
            return new ActionbarConfig(this);
        }
    }

}
