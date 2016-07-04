package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import com.example.dllo.mirror.R;

/**
 * Created by zouliangyu on 16/7/4.
 */
public class WelcomeActivity extends BaseActivity {
    CountDownTimer timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }
}
