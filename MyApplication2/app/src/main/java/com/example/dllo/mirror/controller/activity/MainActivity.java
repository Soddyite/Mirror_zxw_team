package com.example.dllo.mirror.controller.activity;

import android.content.Intent;

import android.support.v4.app.Fragment;
import android.view.View;
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



    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        verticalViewPager = (VerticalViewPager) findViewById(R.id.main_viewpager);
        log = (TextView) findViewById(R.id.main_log);
        mainAdapter = new MainAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initData() {

        usersDao = GreenDaoSingleton.getOurInstance().getUsersDao();

        fragmentList = new ArrayList<>();
        //像Viewpager加入Fragment,通过构造方法传入标题
        for (int i = 0; i < 5; i++) {
            PageFragment pageFragment =new PageFragment(titles[i]);
            fragmentList.add(pageFragment);
        }
        mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.setFragmentList(fragmentList);
        verticalViewPager.setAdapter(mainAdapter);

        List<Users> userBeanList = usersDao.queryBuilder().list();

        if (userBeanList.size()==0){
            log.setText("登陆");
            //登录
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), SignUpActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            log.setText("购物车");

            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    verticalViewPager.setCurrentItem(4);
                }
            });

        }




    }

    //回掉的接口
    @Override

    public void change(int pos) {
        verticalViewPager.setCurrentItem(pos);

    }


}
