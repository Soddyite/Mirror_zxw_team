package com.example.dllo.mirror.controller.activity;

import android.support.v4.view.ViewPager;
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
import com.example.dllo.mirror.view.VerticalViewPager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/15.
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

        RequestQueue requestQueue = Volley.newRequestQueue(TopicDetailsActivity.this);
        StringRequest request = new StringRequest("http://lol.zhangyoubao.com/apis/rest/ItemsService/lists?cattype=news&catid=10179&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438755014&p_=17387&v_=40050303&d_=ios&osv_=8.3&version=1&a_=lol",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        topicDetailsData = gson.fromJson(response, TopicDetailsData.class);

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
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(request);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit_iv:
                finish();
                break;
            case R.id.share_iv:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
