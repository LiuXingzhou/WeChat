package com.islxz.wechat.entity;

/**
 * Created by liuxi on 2017/7/5.
 */

public class News {

    private boolean self;
    private String name;
    private int img;
    private String news;

    public News() {
        
    }

    public News(boolean self, String name, int img, String news) {
        this.self = self;
        this.name = name;
        this.img = img;
        this.news = news;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }
}
