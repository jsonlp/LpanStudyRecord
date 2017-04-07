package com.lpan.study.model;

/**
 * Created by lpan on 2017/4/5.
 */

public class ProvinceTabInfo {

    private String provinceName;

    private int count;

    private int firstIndex;

    private boolean isSelected;

    public ProvinceTabInfo() {

    }

    public ProvinceTabInfo(String provinceName, int count, int firstIndex) {
        this.provinceName = provinceName;
        this.count = count;
        this.firstIndex = firstIndex;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
