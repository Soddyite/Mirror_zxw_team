package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.BottomListViewAdapter;
import com.example.dllo.mirror.controller.adapter.TopListViewAdapter;
import com.example.dllo.mirror.model.base.MyApplication;
import com.example.dllo.mirror.model.bean.AllBean;
import com.example.dllo.mirror.model.bean.GoodsDetails;
import com.example.dllo.mirror.model.bean.MyData;
import com.example.dllo.mirror.model.bean.MyDataTwo;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;
import com.example.dllo.mirror.model.utils.ScreenUtils;
import com.example.dllo.mirror.view.NoScrollListView;
import com.example.dllo.mirror.view.RelationListView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.autolayout.AutoFrameLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zouliangyu on 16/6/13.
 * 详情界面
 */
public class DetailsActivity extends BaseActivity implements View.OnClickListener {
    // 下层listview
    private ListView listViewBottom;
    private List<MyData> myDatas;
    private BottomListViewAdapter bottomListViewAdapter;
    // 上层listview
    private NoScrollListView listViewTop;
    private List<MyDataTwo> myDataTwos;
    private TopListViewAdapter topListViewAdapter;
    // 弹出的购买栏
    private RelativeLayout relativeLayout;
    private TextView showTv; // 佩戴效果
    private ImageView exitIv; // 退出
    private ImageView buyIv; // 购买

    private ImageView shareIv; // 分享
    private TextView contentTv;

    // 底部局
    private ImageView footerFirst;
    private ImageView footerSecond;
    private DisplayImageOptions options;


    private ImageView nullImage;
    private ImageView backGroundIv;
    private AllBean allBean;
    private GoodsDetails goodsDetails;
    private List<GoodsDetails.DataBean.ListBean> list;
    private int pos;
    private String img;
    private String name;
    private String money;

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

        backGroundIv = (ImageView) findViewById(R.id.back_ground_iv);

    }

    @Override
    protected void initData() {
        //获取所有数据
        String result = readFile("mirror_data.txt");
        Gson gson = new Gson();
        allBean = gson.fromJson(result, AllBean.class);

        Intent intent = getIntent();
        pos = intent.getIntExtra("pos", 0);
        list = (List<GoodsDetails.DataBean.ListBean>) intent.getSerializableExtra("bundle1");

        ImageLoader.getInstance().displayImage(list.get(pos).getData_info().getGoods_img(),
                backGroundIv, options);

        // 退出  佩戴图集  购买
        exitIv.setOnClickListener(this);
        showTv.setOnClickListener(this);
        buyIv.setOnClickListener(this);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .build();//构建完成
        // 头布局
        addView();

        bottomListViewAdapter = new BottomListViewAdapter(this);


//        myDatas = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            myDatas.add(new MyData(R.mipmap.glass));
//        }

        bottomListViewAdapter.setList(list.get(pos).getData_info().getDesign_des());
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
                // 当滑到第二个 item  购买栏弹出
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
                    // 隐藏购买栏
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
                if (firstVisibleItem >= 1) {
                    // 当第一个可见的Item >= 1时, 让表层ListView滑动
                    listViewTop.setScrollY((int) (getScrollY(listViewBottom) * 1.2));
                }

            }

        });

        topListViewAdapter = new TopListViewAdapter(this);
        Log.d("list", "list.get(pos).getData_info().getGoods_data().size():"
                + list.get(pos).getData_info().getGoods_data().size());

//        myDataTwos = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            myDataTwos.add(new MyDataTwo("SEE CONCEPT", "对方公司规定非活动法啊啊啊啊啊啊啊啊啊啊啊啊啊"));
//        }

        topListViewAdapter.setList(list.get(pos).getData_info().getGoods_data());
        listViewTop.setAdapter(topListViewAdapter);
        // 滚动条  不活动的时候隐藏，活动的时候也隐藏
        listViewTop.setVerticalScrollBarEnabled(true);

        // 联动方法
//        listViewTop.setRelatedListView(listViewBottom);
//        // 摩擦力
//        listViewTop.setFriction(ViewConfiguration.getScrollFriction() * 0.9f);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.show:
                // 佩戴图集
                Intent intent = new Intent(this, DressResultActivity.class);
                startActivity(intent);
                break;
            case R.id.buy:
                //购买
                Intent intentBuy = new Intent(this, BuyDetailsActivity.class);

                img = String.valueOf(list.get(pos).getData_info().getDesign_des().get(0).getImg());
                name = list.get(pos).getData_info().getGoods_name();
                money = list.get(pos).getData_info().getGoods_price();
                intentBuy.putExtra("img", img);
                intentBuy.putExtra("name", name);
                intentBuy.putExtra("money", money);
                startActivity(intentBuy);
                break;
            // 分享
            case R.id.share:
                showShare();
                break;
        }
    }

    /**
     * HeaderView
     * FooterView
     */
    public void addView() {
        // 获取屏幕宽高
        int height = ScreenUtils.getScreenHeight(MyApplication.getContext());
        int width = ScreenUtils.getScreenWidth(MyApplication.getContext());

        // 下层的listview
        // 第一个头布局, 产品介绍那个
        View viewFirst = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_first, null);
        viewFirst.setMinimumHeight(height);
        viewFirst.setMinimumWidth(width);
        // 空的头布局
        AutoFrameLayout autoFrameLayout = (AutoFrameLayout) viewFirst.findViewById(R.id.head_first_null);
        autoFrameLayout.setMinimumHeight(height);
        TextView nameTv = (TextView) viewFirst.findViewById(R.id.head_first_goods_name);
        nameTv.setText(list.get(pos).getData_info().getGoods_name());
        TextView brandTv = (TextView) viewFirst.findViewById(R.id.header_first_brand);
        brandTv.setText(list.get(pos).getData_info().getBrand());
        contentTv = (TextView) viewFirst.findViewById(R.id.header_first_content_tv);
        contentTv.setText(list.get(pos).getData_info().getInfo_des());
        TextView priceTv = (TextView) viewFirst.findViewById(R.id.head_first_goods_price);
        priceTv.setText(list.get(pos).getData_info().getGoods_price());
        // 分享
        shareIv = (ImageView) viewFirst.findViewById(R.id.share);
        shareIv.setOnClickListener(this);
        listViewBottom.addHeaderView(viewFirst);

//        // 下层listview
//        // 一整页空的头布局
//        View viewSecond = LayoutInflater.from(this).inflate(R.layout.header_third, null);
//        viewSecond.setMinimumHeight(height);
//        viewSecond.setMinimumWidth(width);
//        listViewBottom.addHeaderView(viewSecond);
        // 标题 头布局
        // 例如: 手工复古眼镜
        View viewThird = LayoutInflater.from(this).inflate(R.layout.header_second, null);
        TextView nameTv1 = (TextView) viewThird.findViewById(R.id.head_second_name_tv);
        nameTv1.setText(list.get(pos).getData_info().getBrand());
        viewThird.setMinimumHeight(height / 10);
        viewThird.setMinimumWidth(width);
        listViewBottom.addHeaderView(viewThird);


        // 上层listview
        // 一整页空的头布局
        View viewSecond1 = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.header_third, null);
        nullImage = (ImageView) viewSecond1.findViewById(R.id.null_imageview);
        nullImage.setMinimumHeight(height * 3);
        nullImage.setMinimumWidth(width);
        listViewTop.addHeaderView(viewSecond1);
        viewSecond1.setAlpha(0f);

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

    /**
     * 读取文件的方法
     *
     * @param pathName 文件名
     * @return
     */
    private String readFile(String pathName) {
        // 结果
        String result = new String();
        try {
            // 根据文件名获取Assets文件夹下的文件
            InputStream is = this.getAssets().open(pathName);
            // 读流
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(ir);
            String line = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
            ir.close();
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取滑动距离的方法
     *
     * @param listView
     * @return
     */
    public int getScrollY(ListView listView) {
        View view = listView.getChildAt(0);
        if (view == null) {
            return 0;
        }
        int firstVisibleItem = listView.getFirstVisiblePosition();
        int top = view.getTop();
        return -top + firstVisibleItem * view.getHeight();
    }

}
