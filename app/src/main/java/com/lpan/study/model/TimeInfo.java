package com.lpan.study.model;

/**
 * Created by lpan on 2019/1/22.
 */
public class TimeInfo {

    private float hours;
    private int days;
    private String mostEarly;
    private String mostLast;


    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMostEarly() {
        return mostEarly;
    }

    public void setMostEarly(String mostEarly) {
        this.mostEarly = mostEarly;
    }

    public String getMostLast() {
        return mostLast;
    }

    public void setMostLast(String mostLast) {
        this.mostLast = mostLast;
    }

    @Override
    public String toString() {
        return "TimeInfo{" +
                "hours=" + hours +
                ", days=" + days +
                ", mostEarly='" + mostEarly + '\'' +
                ", mostLast='" + mostLast + '\'' +
                '}';
    }
}
