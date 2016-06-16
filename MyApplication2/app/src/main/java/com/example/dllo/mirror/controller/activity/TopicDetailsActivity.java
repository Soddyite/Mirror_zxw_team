package com.example.dllo.mirror.controller.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.TopicDetailsAdapter;
import com.example.dllo.mirror.model.bean.TopicDetailsData;
import com.example.dllo.mirror.view.VerticalViewPager;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/15.
 */
public class TopicDetailsActivity extends BaseActivity {
    private VerticalViewPager verticalViewPager;

    private TopicDetailsAdapter topicDetailsAdapter;

    private TopicDetailsData topicDetailsData;

    @Override
    protected int getLayout() {
        return R.layout.activity_topic_details;
    }

    @Override
    protected void initView() {
        verticalViewPager = bindView(R.id.vertical_viewpager);

    }

    @Override
    protected void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest("http://lol.zhangyoubao.com/apis/rest/ItemsService/lists?cattype=news&catid=10179&page=1&i_=EAC1B788-00BC-454A-A9B9-460852CFC011&t_=1438755014&p_=17387&v_=40050303&d_=ios&osv_=8.3&version=1&a_=lol",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TopicDetailsActivityxxxxxxxxxxx", response);

                        Gson gson = new Gson();
                        topicDetailsData = gson.fromJson(response, TopicDetailsData.class);
                        Log.d("TopicDetailsAc123tivity", topicDetailsData.getData().get(0).getTitle());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TopicDetailsActivity", "123:" + 123);
            }
        });
        requestQueue.add(request);


        List<View> views = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.fragment_topic_details, null);
            views.add(view);
        }

        topicDetailsAdapter = new TopicDetailsAdapter(views);
        verticalViewPager.setAdapter(topicDetailsAdapter);
    }
}
