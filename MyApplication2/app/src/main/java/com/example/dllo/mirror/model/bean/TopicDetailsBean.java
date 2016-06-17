package com.example.dllo.mirror.model.bean;

/**
 * Created by zouliangyu on 16/6/15.
 */
public class TopicDetailsBean {
    String text;
    String title;
    String desc;

    public TopicDetailsBean() {
    }

    public TopicDetailsBean(String text, String title, String desc) {
        this.text = text;
        this.title = title;
        this.desc = desc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
