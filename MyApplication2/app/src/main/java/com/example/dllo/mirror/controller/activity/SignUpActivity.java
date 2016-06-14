package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.dllo.mirror.R;


/**
 * Created by dllo on 16/6/13.
 */
public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    Button creatUserButton;

    @Override
    protected int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initView() {
        creatUserButton = bindView(R.id.signup_create_user_btn);

    }

    @Override
    protected void initDate() {

        creatUserButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signup_create_user_btn:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
