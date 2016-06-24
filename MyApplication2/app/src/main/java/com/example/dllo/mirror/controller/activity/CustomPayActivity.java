package com.example.dllo.mirror.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ease.paysdk.FuQianLa;
import com.ease.paysdk.FuQianLaPay;
import com.ease.paysdk.bean.FuQianLaResult;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.utils.OrderUtils;

/**
 * Created by siqi on 16/3/31.
 * <p>
 * 商户APP根据自身需要，可自定义创建交易通道(Activity样式)
 *
 * @author siqi
 */
public class CustomPayActivity extends Activity implements View.OnClickListener {

    private Button btnWX, btnAli;

    private FuQianLaPay pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        btnAli = (Button) findViewById(R.id.btn_ali);
        btnWX = (Button) findViewById(R.id.btn_wx);

        btnWX.setOnClickListener(this);
        btnAli.setOnClickListener(this);

        pay = new FuQianLaPay.Builder()
                .activity(this)
                .promode(true)
                .partner(Merchant.MERCHANT_NO)
                .orderID(OrderUtils.getOutTradeNo())//订单号
                .amount(0.01)//金额
                .subject("通道前置Activity-商品名称")//商品名称
                .body("通道前置Activity-交易详情")//商品交易详情
                .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wx:
                pay.startPay(FuQianLa.WX);
                break;
            case R.id.btn_ali:
                pay.startPay(FuQianLa.ALI);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回支付结果
        if (requestCode == FuQianLa.REQUESTCODE
                && resultCode == FuQianLa.RESULTCODE
                && data != null) {
            FuQianLaResult result = data.getParcelableExtra(FuQianLa.PAYRESULT_KEY);
            Toast.makeText(this, result.payCode, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
