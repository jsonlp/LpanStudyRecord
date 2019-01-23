package com.lpan.study.model;

import java.util.List;

/**
 * Created by lpan on 2019/1/22.
 */
public class TimeInfo {

    private float hours;
    private int days;
    private String mostEarly;
    private String mostLast;
    private List<String> rest;
    private String fromDate;
    private String endDate;


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

    public List<String> getRest() {
        return rest;
    }

    public void setRest(List<String> rest) {
        this.rest = rest;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endData) {
        this.endDate = endData;
    }

    @Override
    public String toString() {
        return "TimeInfo{" +
                "hours=" + hours +
                ", days=" + days +
                ", mostEarly='" + mostEarly + '\'' +
                ", mostLast='" + mostLast + '\'' +
                ", rest=" + rest +
                ", fromDate='" + fromDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
