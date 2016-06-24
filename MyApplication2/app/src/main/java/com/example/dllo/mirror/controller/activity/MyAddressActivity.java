package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.MyAddressAdapter;
import com.example.dllo.mirror.model.base.MyApplication;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 * 我的所有地址界面
 */
public class MyAddressActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;

    private MyAddressAdapter myAddressAdapter;

    private AddressDao addressDao;

    @Override
    protected int getLayout() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listview);
        // 退出
        bindView(R.id.my_address_exit_iv).setOnClickListener(this);
        // 添加地址
        bindView(R.id.add_address_btn).setOnClickListener(this);
    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_address_exit_iv:
                finish();
                break;
            case R.id.add_address_btn:
                Intent intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
            // 邹良禹
            // 大连
        }
    }

    @Override
    protected void onStart() {
        super.onStart();


        myAddressAdapter = new MyAddressAdapter(MyApplication.getContext());

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();

        List<Address> addressList = addressDao.queryBuilder().list();


        if (addressList.size() > 0) {
            myAddressAdapter.setAddressList(addressList);
            listView.setAdapter(myAddressAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
}
