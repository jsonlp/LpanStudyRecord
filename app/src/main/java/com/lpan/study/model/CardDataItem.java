package com.lpan.study.model;

/**
 * 卡片数据装载对象
 */
public class CardDataItem {
    public int imagePath;
    public String userName;
    public int likeNum;
    public int imageNum;

    public CardDataItem() {
    }

    public CardDataItem(int imagePath, String userName, int likeNum, int imageNum) {
        this.imagePath = imagePath;
        this.userName = userName;
        this.likeNum = likeNum;
        this.imageNum = imageNum;
    }
}
