package com.islxz.wechat.IndexBar.bean;

/**
 * 索引类的汉语拼音的接口
 * Created by liuxi on 2017/7/5.
 */

public abstract class BaseIndexPinyinBean extends BaseIndexTagBean implements IIndexTargetInterface {
    private String baseIndexPinyin;//城市的拼音

    public String getBaseIndexPinyin() {
        return baseIndexPinyin;
    }

    public void setBaseIndexPinyin(String baseIndexPinyin) {
        this.baseIndexPinyin = baseIndexPinyin;
    }
}
