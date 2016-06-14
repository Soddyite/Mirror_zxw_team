package com.example.dllo.mirror.controller.activity;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/6/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initDate();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initDate();


    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


}
