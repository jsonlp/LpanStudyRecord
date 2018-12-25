package com.lpan.study.model;

import java.util.List;

/**
 * Created by lpan on 2018/10/9.
 */
public class BlockInfo {

    int type;

    String title;

    List<ContentInfo> content;

    public BlockInfo() {
    }

    public BlockInfo(int type, String title, List<ContentInfo> content) {
        this.type = type;
        this.title = title;
        this.content = content;
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

    public List<ContentInfo> getContent() {
        return content;
    }

    public void setContent(List<ContentInfo> content) {
        this.content = content;
    }
}
