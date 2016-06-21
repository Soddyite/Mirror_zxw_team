package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.view.View;

import com.example.dllo.mirror.R;

/**
 * Created by zouliangyu on 16/6/20.
 */
public class BuyDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayout() {
        return R.layout.activity_buy_details;
    }

    @Override
    protected void initView() {
        // 退出
        bindView(R.id.buy_details_exit_iv).setOnClickListener(this);
        // 添加地址
        bindView(R.id.buy_details_add_address).setOnClickListener(this);
        // 购买
        bindView(R.id.buy_btn).setOnClickListener(this);
    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_details_exit_iv:
                finish();
                break;
            case R.id.buy_details_add_address:
                Intent intentAddress = new Intent(this, MyAddressActivity.class);
                startActivity(intentAddress);
                break;
            // 购买
            case R.id.buy_btn:

                break;

        }
    }
}
