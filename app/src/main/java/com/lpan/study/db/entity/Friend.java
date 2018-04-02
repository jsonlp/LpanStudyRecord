package com.lpan.study.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by lpan on 2018/3/29.
 */

@Entity
public class Friend {

    @Index(unique = true)
    @Id()
    private String uuid;

    private int relation;

    private String data;




    @Generated(hash = 1726384459)
    public Friend(String uuid, int relation, String data) {
        this.uuid = uuid;
        this.relation = relation;
        this.data = data;
    }

    @Generated(hash = 287143722)
    public Friend() {
    }




    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRelation() {
        return this.relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
