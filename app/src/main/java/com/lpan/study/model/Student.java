package com.lpan.study.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lpan on 2018/3/29.
 */

@Entity
public class Student {

    @Id(autoincrement = true)
    private long stuId;

    @Index(unique = true)
    private String stuNo;

    private String stuName;

    private String stuSex;

    private String stuScore;

    private int height;

    @Generated(hash = 16687046)
    public Student(long stuId, String stuNo, String stuName, String stuSex,
            String stuScore, int height) {
        this.stuId = stuId;
        this.stuNo = stuNo;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuScore = stuScore;
        this.height = height;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getStuId() {
        return this.stuId;
    }

    public void setStuId(long stuId) {
        this.stuId = stuId;
    }

    public String getStuNo() {
        return this.stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return this.stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return this.stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuScore() {
        return this.stuScore;
    }

    public void setStuScore(String stuScore) {
        this.stuScore = stuScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuNo='" + stuNo + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", stuScore='" + stuScore + '\'' +
                '}';
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
