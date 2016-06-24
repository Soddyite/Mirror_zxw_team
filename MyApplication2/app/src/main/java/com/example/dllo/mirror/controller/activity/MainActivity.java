package com.example.dllo.mirror.controller.activity;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.MainAdapter;
import com.example.dllo.mirror.controller.fragment.PageFragment;
import com.example.dllo.mirror.model.bean.UserBean;
import com.example.dllo.mirror.model.db.Users;
import com.example.dllo.mirror.model.db.UsersDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import com.example.dllo.mirror.model.utils.VerticalViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements PageFragment.HongXiangListener {
    private MainAdapter mainAdapter;
    private VerticalViewPager verticalViewPager;
    private List<Fragment> fragmentList;
    private String[] titles = {"瀏覽所有類型", "瀏覽平光鏡", "瀏覽太陽鏡", "專題分享", "我的購物車"};
    private TextView log;
    UsersDao usersDao;
    private List<Users> userBeanList;
    ImageView main_mirror;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        main_mirror = (ImageView) findViewById(R.id.main_mirror);

        verticalViewPager = (VerticalViewPager) findViewById(R.id.main_viewpager);
        log = (TextView) findViewById(R.id.main_log);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        initTextView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        initTextView();

    }



    public void initTextView(){

        usersDao = GreenDaoSingleton.getOurInstance().getUsersDao();
        userBeanList = usersDao.queryBuilder().list();

        if (userBeanList.size() > 0) {

            log.setText("购物车");


        } else {

            log.setText("登陆");


        }

        //登录
        log.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Log.d("MainActivity", "userBeanList.size():" + userBeanList.size());


                userBeanList = usersDao.queryBuilder().list();

                if (userBeanList.size() > 0) {

                    log.setText("购物车");

                    verticalViewPager.setCurrentItem(4);


                } else {

                    log.setText("登陆");

                    Intent intent = new Intent(getApplication(), SignUpActivity.class);
                    startActivity(intent);

                }


            }
        });


    }



    @Override
    protected void initData() {



        main_mirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("MainActivity", "userBeanList.size():" + userBeanList.size());
            }
        });



        fragmentList = new ArrayList<>();
        //像Viewpager加入Fragment,通过构造方法传入标题
        for (int i = 0; i < 5; i++) {
            PageFragment pageFragment = new PageFragment(titles[i]);
            fragmentList.add(pageFragment);
        }
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragmentList(fragmentList);
        verticalViewPager.setAdapter(mainAdapter);






    }

    //回掉的接口
    @Override

    public void change(int pos) {
        verticalViewPager.setCurrentItem(pos);


    }

}
