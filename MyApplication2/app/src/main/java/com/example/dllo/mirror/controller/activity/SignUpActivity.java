package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.UserBean;
import com.example.dllo.mirror.model.db.Users;
import com.example.dllo.mirror.model.db.UsersDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;


/**
 * Created by dllo on 16/6/13.
 */
public class SignUpActivity extends BaseActivity implements View.OnClickListener {

    private Button creatUserButton, signupBtn;
    private ImageView sinaIv, weixinIv;
    private EditText phoneEt, passwordEt;
    private OkHttpClient client;
    private Platform platform;
    private UsersDao usersDao;
    ImageView closeIv;


    @Override
    protected int getLayout() {
        return R.layout.activity_signup;
    }

    @Override
    protected void initView() {

        platform = null;
        ShareSDK.initSDK(this);


        client = new OkHttpClient();
        closeIv = bindView(R.id.signup_close_iv);
        sinaIv = bindView(R.id.signup_sina_signup);
        weixinIv = bindView(R.id.signup_weixin_signup);
        creatUserButton = bindView(R.id.signup_create_user_btn);
        signupBtn = bindView(R.id.signup_sigup_btn);
        phoneEt = bindView(R.id.signup_user_phonenumber);
        passwordEt = bindView(R.id.signup_user_password);

    }

    @Override
    protected void initData() {
        closeIv.setOnClickListener(this);

        creatUserButton.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        sinaIv.setOnClickListener(this);
        weixinIv.setOnClickListener(this);
        usersDao = GreenDaoSingleton.getOurInstance().getUsersDao();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signup_close_iv:
                finish();
                break;
            case R.id.signup_create_user_btn:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.signup_sigup_btn:

                String postUrl1 = "http://api101.test.mirroreye.cn/index.php/user/login";

                //请求体
                FormEncodingBuilder builder1 = new FormEncodingBuilder();
                builder1.add("phone_number", phoneEt.getText().toString());
                builder1.add("password", passwordEt.getText().toString());


                //创建请求体
                RequestBody body1 = builder1.build();
                //创建请求
                Request request1 = new Request.Builder().url(postUrl1).post(body1).build();

                //发出异步post请求
                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                        Log.d("SignUpActivity", "error");

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {

                        Log.d("SignUpActivity", response.body().string());


                        addDb(response.body().string());


                    }
                });


                break;


            case R.id.signup_sina_signup:

                Log.d("SignUpActivity", "sina");

                signUpOther(SinaWeibo.NAME);

                break;

            case R.id.signup_weixin_signup:

                Log.d("SignUpActivity", "weixin");

                signUpOther(Wechat.NAME);

                break;


        }
    }


    private void signUpOther(String name) {

        platform = ShareSDK.getPlatform(name);
        platform.SSOSetting(false);  //设置false表示使用SSO授权方式

        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.d("SignUpActivity", "success");
                Log.d("SignUpActivity", platform.getDb().getUserId());
                Log.d("SignUpActivity", platform.getDb().getToken());
                usersDao.deleteAll();
                usersDao.insert(new Users(1l, platform.getDb().getUserId(), platform.getDb().getToken()));

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.d("SignUpActivity", "error");

            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.d("SignUpActivity", "cancel");

            }
        }); // 设置分享事件回调

//        platform.showUser(null);//获取接口的回调,成功的话可以得到用户信息
        platform.authorize();


    }

    private void addDb(String response) {
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(response, new TypeToken<UserBean>() {
        }.getType());
        usersDao.deleteAll();
        usersDao.insert(new Users(1l, bean.getData().getUid(), bean.getData().getToken()));
    }


}
