package com.example.dllo.mirror.model.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by zouliangyu on 16/6/13.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
