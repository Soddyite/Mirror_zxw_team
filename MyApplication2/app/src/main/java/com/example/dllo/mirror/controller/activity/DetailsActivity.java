package com.example.dllo.mirror.controller.activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.BottomListViewAdapter;
import com.example.dllo.mirror.controller.adapter.TopListViewAdapter;
import com.example.dllo.mirror.model.base.MyApplication;
import com.example.dllo.mirror.model.bean.MyData;
import com.example.dllo.mirror.model.bean.MyDataTwo;
import com.example.dllo.mirror.model.utils.ScreenUtils;
import com.example.dllo.mirror.view.RelationListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/13.
 * 详情界面
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {
    // 下层listview
    private RelationListView listViewBottom;
    private List<MyData> myDatas;
    private BottomListViewAdapter bottomListViewAdapter;
    // 上层listview
    private RelationListView listViewTop;
    private List<MyDataTwo> myDataTwos;
    private TopListViewAdapter topListViewAdapter;
    // 弹出的购买栏
    private RelativeLayout relativeLayout;
    private TextView showTv; // 佩戴效果
    private ImageView exitIv; // 退出
    private ImageView buyIv; // 购买
    private ImageView shareIv; // 分享

    @Override
    protected int getLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void initView() {
        listViewBottom = bindView(R.id.lv1);
        listViewTop = bindView(R.id.lv2);
        // 购买栏
        relativeLayout = bindView(R.id.relativelayout);
        // 佩戴效果
        showTv = bindView(R.id.show);
        // 退出
        exitIv = bindView(R.id.exit);
        // 购买
        buyIv = bindView(R.id.buy);


    }

    @Override
    protected void initData() {
        exitIv.setOnClickListener(this);
        showTv.setOnClickListener(this);
        buyIv.setOnClickListener(this);

        // 头布局
        getHeaderView();

        bottomListViewAdapter = new BottomListViewAdapter(this);
        myDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myDatas.add(new MyData(R.mipmap.glass));
        }
        bottomListViewAdapter.setMyDatas(myDatas);
        listViewBottom.setAdapter(bottomListViewAdapter);
        // 滚动条  不活动的时候隐藏，活动的时候也隐藏
        listViewBottom.setVerticalScrollBarEnabled(true);

        // 下面的listview的滑动监听
        listViewBottom.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d("MainActivity", "popup.getVisibility():" + relativeLayout.getVisibility());
                if (firstVisibleItem >= 2) {
                    if (relativeLayout.getVisibility() != View.VISIBLE) {
                        TranslateAnimation translateAnimation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, -1, Animation.RELATIVE_TO_PARENT, 0,
                                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0);
                        translateAnimation.setDuration(500);
                        relativeLayout.setAnimation(translateAnimation);
                        relativeLayout.startAnimation(translateAnimation);
                    }

                    relativeLayout.setVisibility(View.VISIBLE);

                } else {
                    if (relativeLayout.getVisibility() == View.VISIBLE) {
                        TranslateAnimation translateAnimation = new TranslateAnimation(
                                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, -1,
                                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_PARENT, 0);
                        translateAnimation.setDuration(500);
                        relativeLayout.setAnimation(translateAnimation);
                        relativeLayout.startAnimation(translateAnimation);
                    }
                    relativeLayout.setVisibility(View.INVISIBLE);

                }


//                listViewBottom.setFriction(ViewConfiguration.getScrollFriction());
            }

        });

        topListViewAdapter = new TopListViewAdapter(this);
        myDataTwos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myDataTwos.add(new MyDataTwo("SEE CONCEPT", "对方公司规定非活动法啊啊啊啊啊啊啊啊啊啊啊啊啊"));
        }

        topListViewAdapter.setMyDatasTwo(myDataTwos);
        listViewTop.setAdapter(topListViewAdapter);
        // 滚动条  不活动的时候隐藏，活动的时候也隐藏
        listViewTop.setVerticalScrollBarEnabled(true);

        // 联动方法
        listViewTop.setRelatedListView(listViewBottom);
        // 摩擦力
        listViewTop.setFriction(ViewConfiguration.getScrollFriction() * 0.9f);
//        listViewTop.setVelocityScale(0.6f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.show:
                break;
            case R.id.buy:
                break;
            // 分享
            case R.id.share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * 下面listview的 头布局
     */
    public void getHeaderView() {

        // 获取屏幕宽高
        int height = ScreenUtils.getScreenHeight(MyApplication.getContext());
        int width = ScreenUtils.getScreenWidth(MyApplication.getContext());

        // 下层listview
        // 一整页空的头布局
        View viewSecond = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_third, null);
        viewSecond.setMinimumHeight(height * 2);
        viewSecond.setMinimumWidth(width);
        listViewBottom.addHeaderView(viewSecond);
        // 标题
        // 例如: 手工复古眼镜
        View viewThird = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_second, null);
        viewThird.setMinimumHeight(height / 10);
        viewThird.setMinimumWidth(width);
        listViewBottom.addHeaderView(viewThird);


        // 上层listveiw
        // 第一个头布局
        View viewFirst = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_first, null);
        viewFirst.setMinimumHeight(height);
        viewFirst.setMinimumWidth(width);
        shareIv = (ImageView) viewFirst.findViewById(R.id.share);
        shareIv.setOnClickListener(this);
        listViewTop.addHeaderView(viewFirst);
        // 一整页空的头布局
        View viewSecond1 = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_third, null);
        viewSecond1.setMinimumHeight(height + height / 10);
        viewSecond1.setMinimumWidth(width);
        listViewTop.addHeaderView(viewSecond1);


    }
}
