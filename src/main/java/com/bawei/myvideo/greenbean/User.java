package com.bawei.myvideo.greenbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by 王利博 on 2018/7/4.
 */


@Entity(nameInDb = "user")
public class User {
    //id 自动增长
    @Id(autoincrement = true)
    private  Long id;
    @Property
    private String name;
    @Property
    private String strurl;
    @Property
    private String uid;
    @Generated(hash = 2019732622)
    public User(Long id, String name, String strurl, String uid) {
        this.id = id;
        this.name = name;
        this.strurl = strurl;
        this.uid = uid;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setStrurl(String strurl) {
        this.strurl = strurl;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStrurl() {
        return strurl;
    }
    public String getUid() {
        return uid;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name  +
                ", url='" + strurl  +
                ", uid='" + uid  +
                '}';
    }
}