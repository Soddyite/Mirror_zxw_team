package com.example.dllo.mirror.controller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.dllo.mirror.model.base.MyApplication;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by dllo on 16/6/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        getSupportActionBar().hide();
        //去掉手机的状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getLayout());
        initView();
        initData();

    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    /**
     * 加载组件
     */
    protected abstract void initView();

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 初始化组件
     *
     * @param id  组件id
     * @param <T> view的子类
     * @return
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }


}
