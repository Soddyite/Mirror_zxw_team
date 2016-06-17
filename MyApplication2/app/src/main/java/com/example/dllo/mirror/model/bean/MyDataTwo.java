package com.example.dllo.mirror.model.bean;

/**
 * Created by zouliangyu on 16/6/14.
 */
public class MyDataTwo {
    private String title;
    private String details;

    public MyDataTwo() {
    }

    public MyDataTwo(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
