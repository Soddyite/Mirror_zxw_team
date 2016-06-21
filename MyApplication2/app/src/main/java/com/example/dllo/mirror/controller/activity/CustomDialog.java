package com.example.dllo.mirror.controller.activity;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.ease.onesecondpaydemo.utils.OrderUtils;
import com.ease.paysdk.FuQianLa;
import com.ease.paysdk.FuQianLaPay;

/**
 * Created by siqi on 16/4/5.
 * <p/>
 * 自定义Dialog
 *
 * @author siqi
 */
public class CustomDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;

    private FuQianLaPay pay;

    public CustomDialog(Activity activity) {
        super(activity, R.style.custom_dialog);
        setContentView(R.layout.dialog_custom);
        this.mActivity = activity;
        initViews();
        setProperty();
    }

    public void setPayData(String subject, String body, String amount) {

        pay = new FuQianLaPay.Builder()
                .activity(mActivity)
                .promode(true)
                .partner(Merchant.MERCHANT_NO)
                .orderID(OrderUtils.getOutTradeNo())//订单号
                .amount(Double.parseDouble(amount))//金额
                .subject(subject)//商品名称
                .body(body)//商品交易详情
                .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)//异步通知地址
                .build();
    }

    private void initViews() {
        Button btnAli = (Button) findViewById(R.id.btn_ali);
        Button btnWX = (Button) findViewById(R.id.btn_wx);
        Button btnBD = (Button) findViewById(R.id.btn_bd);
        Button btnUnionPay = (Button) findViewById(R.id.btn_up);

        btnAli.setOnClickListener(this);
        btnWX.setOnClickListener(this);
        btnBD.setOnClickListener(this);
        btnUnionPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ali:
                pay.startPay(FuQianLa.ALI);
                break;
            case R.id.btn_wx:
                pay.startPay(FuQianLa.WX);
                break;
            case R.id.btn_bd:
                pay.startPay(FuQianLa.BD);
                break;
            case R.id.btn_up:
                pay.startPay(FuQianLa.UP);
                break;
        }
        dismiss();
    }

    private void setProperty() {
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams p = window.getAttributes();
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(p);
        setCanceledOnTouchOutside(true);
    }
}
