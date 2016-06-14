package com.example.dllo.mirror.controller.activity;

import android.view.View;
import android.widget.Button;

import com.example.dllo.mirror.R;


/**
 * Created by dllo on 16/6/13.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    Button sendCodeBtn;
    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        sendCodeBtn = bindView(R.id.register_send_code_btn);

    }

    @Override
    protected void initDate() {
        sendCodeBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

    }
}
