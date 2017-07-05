package com.islxz.wechat.entity;

import com.islxz.wechat.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Created by liuxi on 2017/7/5.
 */

public class Contact extends BaseIndexPinyinBean {
    private int img;
    private String name;

    public Contact() {
    }

    public Contact(String name) {
        this.name = name;
    }

    public Contact(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTarget() {
        return name;
    }
}
