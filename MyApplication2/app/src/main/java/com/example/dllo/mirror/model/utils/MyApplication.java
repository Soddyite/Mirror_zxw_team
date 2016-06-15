package com.example.dllo.mirror.model.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/6/15.
 */
public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }


}
