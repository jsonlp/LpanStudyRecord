package com.lpan.study.model;

/**
 * Created by liaopan on 2017/11/30 10:03.
 */

public class ContactInfo {

    int id;

    String name;

    String number;

    String sortKey;

    public ContactInfo() {
    }

    public ContactInfo(int id, String name, String number, String sortKey) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.sortKey = sortKey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }
}
