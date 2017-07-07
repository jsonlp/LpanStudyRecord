package com.lpan.study.model;

/**
 * Created by lpan on 2017/6/28.
 */

public class MiddleClassInfo {

    private long mYear;

    private long mFirstGrade;

    private long mSecondGrade;

    private long mThirdGrade;

    private long mFourthGrade;

    public MiddleClassInfo() {
    }

    public MiddleClassInfo(long year, long firstGrade, long secondGrade, long thirdGrade, long fourthGrade) {
        mYear = year;
        mFirstGrade = firstGrade;
        mSecondGrade = secondGrade;
        mThirdGrade = thirdGrade;
        mFourthGrade = fourthGrade;
    }

    public long getYear() {
        return mYear;
    }

    public void setYear(long year) {
        mYear = year;
    }

    public long getFirstGrade() {
        return mFirstGrade;
    }

    public void setFirstGrade(long firstGrade) {
        mFirstGrade = firstGrade;
    }

    public long getSecondGrade() {
        return mSecondGrade;
    }

    public void setSecondGrade(long secondGrade) {
        mSecondGrade = secondGrade;
    }

    public long getThirdGrade() {
        return mThirdGrade;
    }

    public void setThirdGrade(long thirdGrade) {
        mThirdGrade = thirdGrade;
    }

    public long getFourthGrade() {
        return mFourthGrade;
    }

    public void setFourthGrade(long fourthGrade) {
        mFourthGrade = fourthGrade;
    }

    @Override
    public String toString() {
        return "MiddleClassInfo{" +
                "mYear=" + mYear +
                ", mFirstGrade=" + mFirstGrade +
                ", mSecondGrade=" + mSecondGrade +
                ", mThirdGrade=" + mThirdGrade +
                ", mFourthGrade=" + mFourthGrade +
                '}';
    }
}
