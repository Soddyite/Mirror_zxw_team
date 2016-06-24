package com.example.dllo.mirror.controller.activity;

import android.app.Application;

import com.baidu.wallet.api.BaiduWallet;

/**
 * Created by siqizhang on 16/2/1.
 *
 * @author siqizhang
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        温馨提示:若添加百度支付渠道,需要在Application添加以下代码
//        以下为百度提供文案
//        在调用百度支付任何功能之前,必须初始化,而且初始化必须放在UI线程里面。强烈建议放到应用的Application.onCreate里面
        BaiduWallet.getInstance().initWallet(this);
    }

}
