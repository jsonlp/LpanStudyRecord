package com.lpan.study.model;

/**
 * Created by lpan on 2018/3/16.
 */

public class BaseRecyclerItem {

    private int type;

    private String title;

    private int leftIconResId;

    private int rightIconResId;

    private String url;

    public BaseRecyclerItem() {
    }

    public BaseRecyclerItem(int type, String title, int leftIconResId, int rightIconResId) {
        this.type = type;
        this.title = title;
        this.leftIconResId = leftIconResId;
        this.rightIconResId = rightIconResId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLeftIconResId() {
        return leftIconResId;
    }

    public void setLeftIconResId(int leftIconResId) {
        this.leftIconResId = leftIconResId;
    }

    public int getRightIconResId() {
        return rightIconResId;
    }

    public void setRightIconResId(int rightIconResId) {
        this.rightIconResId = rightIconResId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
