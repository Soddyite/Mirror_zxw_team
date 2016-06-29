package com.example.dllo.mirror.controller.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mirror.R;

import com.example.dllo.mirror.controller.adapter.PageFragmentAdapter;

import com.example.dllo.mirror.controller.activity.DetailsActivity;
import com.example.dllo.mirror.controller.activity.TopicDetailsActivity;
import com.example.dllo.mirror.model.base.MyApplication;
import com.example.dllo.mirror.model.bean.GoodsDetails;
import com.example.dllo.mirror.model.db.Users;
import com.example.dllo.mirror.model.db.UsersDao;
import com.example.dllo.mirror.model.myinterface.PageItemClickListener;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/6/16.
 */

public class PageFragment extends BaseFragment implements View.OnClickListener, PageItemClickListener {
    private String title;
    private View titleLayout;
    private RecyclerView recyclerView;
    private TextView oneTV, twoTV, threeTV, fourTV, fiveTV, toOneTV, titleTv, exitTv;
    private ImageView shopCar;
    private PageFragmentAdapter pageFragmentAdapter;
    private HongXiangListener hongXiangListener;
    private PopupWindow pop;
    ScaleAnimation scaleAnimation;
    UsersDao usersDao;


    @SuppressLint("ValidFragment")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //子类引用父类对象
        hongXiangListener = (HongXiangListener) activity;
    }
    public PageFragment() {
    }
    //构造方法需要传入一个titile,从而实现父用
    @SuppressLint("ValidFragment")
    public PageFragment(String title) {
        this.title = title;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_page;
    }

    @Override
    protected void initView(View view) {
        titleLayout = view.findViewById(R.id.pagefragment_titlelayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.pagefragment_recycleView);
        titleTv = (TextView) view.findViewById(R.id.pagefragment_title);
        shopCar = (ImageView) view.findViewById(R.id.pagefragment_shopcar);
        //购物车页不显示RecyclerView,显示购物车图标
        if (title.equals("我的購物車")) {
            recyclerView.setVisibility(View.INVISIBLE);
            shopCar.setVisibility(View.VISIBLE);
        }
        titleTv.setText(title);
    }
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        popUpWindow();
        titleLayout.setOnClickListener(this);
        pageFragmentAdapter = new PageFragmentAdapter(getActivity());
        pageFragmentAdapter.setPageItemClickListener(this);
        //读json
        String result = new String();
        String pathName = "mirror_data.txt";
        try {
            // 根据文件名获取Assets文件夹下的文件
            InputStream is = getmContext().getAssets().open(pathName);
            // 读流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            // 关流
            br.close();
            is.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //解析
        Gson gson = new Gson();
        GoodsDetails goodsDetails = gson.fromJson(result, GoodsDetails.class);
        List<GoodsDetails.DataBean.ListBean> list = new ArrayList();
        //判断究竟是哪一个Fragment,传不同的list
        if (title.equals("瀏覽所有類型")) {
            for (GoodsDetails.DataBean.ListBean listBean : goodsDetails.getData().getList()) {
                list.add(listBean);
            }
            pageFragmentAdapter.setList(list);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        } else if (title.equals("瀏覽平光鏡") || title.equals("瀏覽太陽鏡")) {
            for (GoodsDetails.DataBean.ListBean listBean : goodsDetails.getData().getList()) {
                if (listBean.getType().equals("1")) {
                    list.add(listBean);
                }
            }
            pageFragmentAdapter.setList(list);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        } else if (title.equals("專題分享")) {
            for (GoodsDetails.DataBean.ListBean listBean : goodsDetails.getData().getList()) {
                if (listBean.getType().equals("2")) {
                    list.add(listBean);
                }

            }
            pageFragmentAdapter.setList(list);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 10));
        }
        recyclerView.setAdapter(pageFragmentAdapter);

    }

    //======================================================================
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //标题
            case R.id.pagefragment_titlelayout:
                recyclerView.setVisibility(View.INVISIBLE);
                titleLayout.setVisibility(View.INVISIBLE);
                shopCar.setVisibility(View.INVISIBLE);
                pop.showAsDropDown(titleLayout);
                switch (title) {
                    case "瀏覽所有類型":
                        TextViewChange(oneTV);
                        break;
                    case "瀏覽平光鏡":
                        TextViewChange(twoTV);
                        break;
                    case "瀏覽太陽鏡":
                        TextViewChange(threeTV);
                        break;
                    case "專題分享":
                        TextViewChange(fourTV);
                        break;
                    case "我的購物車":
                        TextViewChange(fiveTV);
                        break;
                }
                break;
            //瀏覽所有類型
            case R.id.pop_one:
                hongXiangListener.change(0);
                pop.dismiss();
                break;
            //瀏覽平光鏡
            case R.id.pop_two:
                hongXiangListener.change(1);
                pop.dismiss();
                break;
            //瀏覽太陽鏡
            case R.id.pop_three:
                hongXiangListener.change(2);
                pop.dismiss();
                break;
            //專題分享
            case R.id.pop_four:
                hongXiangListener.change(3);
                pop.dismiss();
                break;
            //我的購物車
            case R.id.pop_five:
                hongXiangListener.change(4);
                pop.dismiss();
                break;
            //返回首页
            case R.id.pop_toone:
                hongXiangListener.change(0);
                pop.dismiss();
                break;
            //退出
            case R.id.main_exit:
                usersDao = GreenDaoSingleton.getOurInstance().getUsersDao();
                List<Users> userses = usersDao.queryBuilder().list();
                if (userses.size() > 0) {
                    showDialog();

                } else {

                    Toast.makeText(MyApplication.getContext(), "还未登陆", Toast.LENGTH_SHORT).show();
                }
                pop.dismiss();
                break;


        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("确定退出登录");
        builder.setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        usersDao = GreenDaoSingleton.getOurInstance().getUsersDao();
                        usersDao.deleteAll();
                        List<Users> userses = usersDao.queryBuilder().list();
                        Log.d("PageFragment", "userses.size():" + userses.size());

                    }
                })
                .show();

    }

    /**
     * 传入一个textView,改变pop上TextView的字体颜色
     *
     * @param textview
     */
    private void TextViewChange(TextView textview) {
        scaleAnimation = new ScaleAnimation(1, 1.1f, 1, 1.1f);
        scaleAnimation.setDuration(1000);
        textview.setTextColor(0x99ffffff);
        textview.setAnimation(scaleAnimation);
    }

    /**
     * popupWindow的集体设置
     */
    private void popUpWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_view, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.pop_anim);
        //popupWindow的隐藏监听
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                //无论如何标题都要隐藏
                titleLayout.setVisibility(View.VISIBLE);
                //我的购物车界面特殊,判断
                if (!(title.equals("我的購物車"))) {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                if (title.equals("我的購物車")) {
                    shopCar.setVisibility(View.VISIBLE);
                }

            }
        });
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setTouchable(true);
        oneTV = (TextView) view.findViewById(R.id.pop_one);
        twoTV = (TextView) view.findViewById(R.id.pop_two);
        threeTV = (TextView) view.findViewById(R.id.pop_three);
        fourTV = (TextView) view.findViewById(R.id.pop_four);
        fiveTV = (TextView) view.findViewById(R.id.pop_five);
        toOneTV = (TextView) view.findViewById(R.id.pop_toone);
        exitTv = (TextView) view.findViewById(R.id.main_exit);
        oneTV.setOnClickListener(this);
        twoTV.setOnClickListener(this);
        threeTV.setOnClickListener(this);
        fourTV.setOnClickListener(this);
        fiveTV.setOnClickListener(this);
        toOneTV.setOnClickListener(this);
        exitTv.setOnClickListener(this);


    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(int id) {
        if (isNetworkConnected(getmContext()) == false) {
            Toast.makeText(getActivity(), "请打开网络连接", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent();
            if (title.equals("專題分享")) {
                intent.setClass(getmContext(), TopicDetailsActivity.class);
                startActivity(intent);
            } else {
                intent.setClass(getmContext(), DetailsActivity.class);
                startActivity(intent);
            }
        }


    }

    //接口
    public interface HongXiangListener {
        void change(int pos);
    }

    //判断网络连接
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

}

