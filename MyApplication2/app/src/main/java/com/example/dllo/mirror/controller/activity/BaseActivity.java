package com.example.dllo.mirror.controller.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by zouliangyu on 16/6/13.
 */
public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    // 加载布局
    protected abstract int getLayout();

    // 加载组件
    protected abstract void initView();

    // 加载数据
    protected abstract void initData();

    // 组件实例化不需要转型
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

}
