package com.example.dllo.mirror.controller.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ease.paysdk.FuQianLa;
import com.ease.paysdk.bean.FuQianLaResult;

/**
 * Created by siqi on 16/4/5.
 * <p/>
 * 商户APP根据自身需要，可自定义创建交易通道(Dialog样式)
 *
 * @author siqi
 */
public class CustomPayDialogActivity extends Activity implements View.OnClickListener {

    private CustomDialog dialog;

    private EditText etCommodity, etDetails, etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        etCommodity = (EditText) findViewById(R.id.et_commodity);
        etDetails = (EditText) findViewById(R.id.et_details);
        etAmount = (EditText) findViewById(R.id.et_amount);
        Button btnPay = (Button) findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(this);

        etCommodity.setText("通道前置Dialog——商品名称");
        etDetails.setText("通道前置Dialog——交易详情");
        etAmount.setText("0.01");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pay:
                if (dialog == null)
                    dialog = new CustomDialog(this);
                dialog.setPayData(etCommodity.getText().toString(),
                        etDetails.getText().toString(),
                        etAmount.getText().toString());
                if (!dialog.isShowing())
                    dialog.show();
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
