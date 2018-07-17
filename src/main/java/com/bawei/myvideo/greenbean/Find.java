package com.bawei.myvideo.greenbean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 王利博 on 2018/7/5.
 */
@Entity(nameInDb = "find")
public class Find {
    //id 自动增长
    @Id(autoincrement = true)
    private  Long id;
    @Property
    private String name;
    @Generated(hash = 1534957898)
    public Find(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 234473000)
    public Find() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
