package com.islxz.wechat.entity;

/**
 * Created by ASUS on 2017/7/3/0003.
 */

public class Avert {

    private int mImg;
    private String mName;
    private String mNews;
    private String mDate;
    private Boolean mDisturb;

    public int getImg() {
        return mImg;
    }

    public void setImg(int img) {
        mImg = img;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNews() {
        return mNews;
    }

    public void setNews(String news) {
        mNews = news;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public Boolean getDisturb() {
        return mDisturb;
    }

    public void setDisturb(Boolean disturb) {
        mDisturb = disturb;
    }
}
