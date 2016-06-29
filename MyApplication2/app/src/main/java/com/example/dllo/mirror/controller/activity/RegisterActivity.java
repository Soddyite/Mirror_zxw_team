package com.example.dllo.mirror.controller.activity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.UserBean;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by dllo on 16/6/13.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    Button sendCodeBtn, createUserBtn;
    private OkHttpClient client;
    OkHttpClientManager clientManager;
    EditText phoneEt, smsCodeEt, passwordEt;
    ImageView closeIv;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

        sendCodeBtn = bindView(R.id.register_send_code_btn);
        createUserBtn = bindView(R.id.register_create_user_btn);
        phoneEt = bindView(R.id.register_user_phonenumber);
        smsCodeEt = bindView(R.id.register_sms_code);
        passwordEt = bindView(R.id.register_user_password);
        closeIv = bindView(R.id.register_close_iv);

    }

    @Override
    protected void initData() {

        clientManager = OkHttpClientManager.getInstance();
        client = new OkHttpClient();
        sendCodeBtn.setOnClickListener(this);
        createUserBtn.setOnClickListener(this);
        closeIv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_close_iv:
                finish();
                break;
            case R.id.register_send_code_btn:

                String postUrl = "http://api101.test.mirroreye.cn/index.php/user/send_code";

                Map<String, String> maps = new HashMap<>();
                maps.put("phone number", phoneEt.getText().toString());
                clientManager.postAsyn(postUrl, new OkHttpClientManager.ResultCallback<UserBean>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(UserBean response) {
                        Log.d("RegisterActivity", "发送验证码");

                    }
                }, maps);

//                //请求体
//                FormEncodingBuilder builder = new FormEncodingBuilder();
//                builder.add("phone number", phoneEt.getText().toString());
//
//                //创建请求体
//                RequestBody body = builder.build();
//                //创建请求
//                Request request = new Request.Builder().url(postUrl).post(body).build();
//
//                //发出异步post请求
//                client.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Response response) throws IOException {
//
//                        Log.d("RegisterActivity", response.body().string());
//
//                    }
//                });

                break;

            case R.id.register_create_user_btn:

                String postUrl1 = "http://api101.test.mirroreye.cn/index.php/user/reg";

                Map<String, String> map = new HashMap<>();
                map.put("phone_number", phoneEt.getText().toString());
                map.put("number", smsCodeEt.getText().toString());
                map.put("password", passwordEt.getText().toString());

                clientManager.postAsyn(postUrl1, new OkHttpClientManager.ResultCallback<UserBean>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(UserBean response) {

                        Log.d("RegisterActivity", "注册成功");

                    }
                }, map);

//                //请求体
//                FormEncodingBuilder builder1 = new FormEncodingBuilder();
//                builder1.add("phone_number", phoneEt.getText().toString());
//                builder1.add("number", smsCodeEt.getText().toString());
//                builder1.add("password", passwordEt.getText().toString());
//
//
//                //创建请求体
//                RequestBody body1 = builder1.build();
//                //创建请求
//                Request request1 = new Request.Builder().url(postUrl1).post(body1).build();
//
//                //发出异步post请求
//                client.newCall(request1).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Request request, IOException e) {
//                        Log.d("RegisterActivity", "error");
//
//                    }
//
//                    @Override
//                    public void onResponse(Response response) throws IOException {
//
//                        Log.d("RegisterActivity", response.body().string());
//
//                    }
//                });

                finish();
                break;
        }

    }


}
