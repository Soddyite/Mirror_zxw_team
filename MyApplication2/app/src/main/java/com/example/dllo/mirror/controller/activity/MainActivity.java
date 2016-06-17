package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
<<<<<<< HEAD

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;


=======
import android.view.View;
>>>>>>> ccea5a67daac590639b1b9411d8691c1afb751d3
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.MainAdapter;
import com.example.dllo.mirror.controller.fragment.PageFragment;
import com.example.dllo.mirror.model.utils.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public class MainActivity extends BaseActivity implements PageFragment.HongXiangListener {
    private MainAdapter mainAdapter;
    private VerticalViewPager verticalViewPager;
    private List<Fragment> fragmentList;
    private String[] titles = {"瀏覽所有類型", "瀏覽平光鏡", "瀏覽太陽鏡", "專題分享", "我的購物車"};
    private TextView log;


=======
public class MainActivity extends BaseActivity{
>>>>>>> ccea5a67daac590639b1b9411d8691c1afb751d3
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
<<<<<<< HEAD
        verticalViewPager = (VerticalViewPager) findViewById(R.id.main_viewpager);
        log = (TextView) findViewById(R.id.main_log);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initData() {
        fragmentList = new ArrayList<>();
        //像Viewpager加入Fragment,通过构造方法传入标题
        for (int i = 0; i < 5; i++) {
            PageFragment pageFragment = new PageFragment(titles[i]);
            fragmentList.add(pageFragment);
        }
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragmentList(fragmentList);
        verticalViewPager.setAdapter(mainAdapter);
        //登录
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SignUpActivity.class);
                startActivity(intent);
            }
        });
=======
>>>>>>> ccea5a67daac590639b1b9411d8691c1afb751d3

    }

    //回掉的接口
    @Override
<<<<<<< HEAD
    public void change(int pos) {
        verticalViewPager.setCurrentItem(pos);
=======
    protected void initData() {

>>>>>>> ccea5a67daac590639b1b9411d8691c1afb751d3
    }


}
