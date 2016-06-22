package com.example.dllo.mirror.controller.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.TopicDetailsAdapter;
import com.example.dllo.mirror.model.base.MyApplication;
import com.example.dllo.mirror.model.bean.TopicDetailsData;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;
import com.example.dllo.mirror.view.VerticalViewPager;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zouliangyu on 16/6/15.
 * 专题分享
 */
public class TopicDetailsActivity extends BaseActivity implements View.OnClickListener {
    // 垂直viewpager
    private VerticalViewPager verticalViewPager;
    // 加载view的adapter
    private TopicDetailsAdapter topicDetailsAdapter;
    // 数据类
    private TopicDetailsData topicDetailsData;
    // 详情里面的介绍
    private TextView textView;
    private TextView titleTv;
    private TextView descTc;
    // 底部的图片
    private ImageView bottomIv;
    // 假数据(图片)
    private int[] image = {R.mipmap.piao1, R.mipmap.piao2, R.mipmap.piao3, R.mipmap.piao4, R.mipmap.piao5};

    @Override
    protected int getLayout() {
        return R.layout.activity_topic_details;
    }

    @Override
    protected void initView() {
        verticalViewPager = bindView(R.id.vertical_viewpager);
        bottomIv = bindView(R.id.bottom_iv);

    }

    @Override
    protected void initData() {


        // 退出
        bindView(R.id.exit_iv).setOnClickListener(this);
        // 分享
        bindView(R.id.share_iv).setOnClickListener(this);


        Map<String, String> params = new HashMap<String, String>();
        params.put("token", "");
        params.put("device_type", "1");

//        OkHttpClientManager.post()

        OkHttpClientManager.postAsyn("http://lol.zhangyoubao.com/apis/rest/ItemsService/lists?cattype=news&catid=10179&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438755014&p_=17387&v_=40050303&d_=ios&osv_=8.3&version=1&a_=lol",
                new OkHttpClientManager.ResultCallback<TopicDetailsData>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(TopicDetailsData response) {

                        topicDetailsData = response;
                        // 加载的view的集合
                        List<View> views = new ArrayList<>();
                        // 数据页数  5应该是   topicDetailsData.getData().size()
                        for (int i = 0; i < 5; i++) {
                            // 同一个view
                            View view = LayoutInflater.from(MyApplication.getContext()).inflate(
                                    R.layout.activity_topic_details_viewpager, null);
                            textView = (TextView) view.findViewById(R.id.text_tv);
                            titleTv = (TextView) view.findViewById(R.id.title_tv);
                            descTc = (TextView) view.findViewById(R.id.desc_tv);
                            textView.setText(topicDetailsData.getData().get(i).getId());
                            titleTv.setText(topicDetailsData.getData().get(i).getTitle());
                            descTc.setText(topicDetailsData.getData().get(i).getDesc());
                            views.add(view);
                        }

                        topicDetailsAdapter = new TopicDetailsAdapter(views);
                        verticalViewPager.setAdapter(topicDetailsAdapter);
                        // viewpager滑动监听
                        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                            }

                            @Override
                            public void onPageSelected(int position) {
                                bottomIv.setImageResource(image[position]);
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {


                            }
                        });
                    }
                }, params);


//        RequestQueue requestQueue = Volley.newRequestQueue(TopicDetailsActivity.this);
//        StringRequest request = new StringRequest("http://lol.zhangyoubao.com/apis/rest/ItemsService/lists?cattype=news&catid=10179&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438755014&p_=17387&v_=40050303&d_=ios&osv_=8.3&version=1&a_=lol",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//1
//                        Gson gson = new Gson();
//                        topicDetailsData = gson.fromJson(response, TopicDetailsData.class);
//
//                        // 加载的view的集合
//                        List<View> views = new ArrayList<>();
//                        // 数据页数  5应该是   topicDetailsData.getData().size()
//                        for (int i = 0; i < 5; i++) {
//                            // 同一个view
//                            View view = LayoutInflater.from(MyApplication.getContext()).inflate(
//                                    R.layout.activity_topic_details_viewpager, null);
//                            textView = (TextView) view.findViewById(R.id.text_tv);
//                            titleTv = (TextView) view.findViewById(R.id.title_tv);
//                            descTc = (TextView) view.findViewById(R.id.desc_tv);
//                            textView.setText(topicDetailsData.getData().get(i).getId());
//                            titleTv.setText(topicDetailsData.getData().get(i).getTitle());
//                            descTc.setText(topicDetailsData.getData().get(i).getDesc());
//                            views.add(view);
//
//                        }
//
//                        topicDetailsAdapter = new TopicDetailsAdapter(views);
//                        verticalViewPager.setAdapter(topicDetailsAdapter);
//                        // viewpager滑动监听
//                        verticalViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                            @Override
//                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                            }
//
//                            @Override
//                            public void onPageSelected(int position) {
//                                bottomIv.setImageResource(image[position]);
//                            }
//
//                            @Override
//                            public void onPageScrollStateChanged(int state) {
//
//                            }
//                        });
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        });
//        requestQueue.add(request);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 退出
            case R.id.exit_iv:
                finish();
                break;
            // 分享
            case R.id.share_iv:
                showShare();
                break;
        }
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }
}
