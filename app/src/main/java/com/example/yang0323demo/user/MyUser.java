package com.example.yang0323demo.user;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyUser {
    private String name;
    private String imageUrl;
    @Transient
    private int tempUsageCount; // not persisted
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Generated(hash = 1084219918)
    public MyUser(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
    @Generated(hash = 623865568)
    public MyUser() {
    }
}
