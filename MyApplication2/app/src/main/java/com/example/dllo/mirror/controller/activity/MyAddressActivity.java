package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.MyAddressAdapter;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 * 我的所有地址界面
 */
public class MyAddressActivity extends BaseActivity implements View.OnClickListener {
    private ListView listView;

    private MyAddressAdapter myAddressAdapter;

    private AddressDao addressDao;
    private List<Address> addressList;
    private int pos;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", addressList.get(addressList.size() - position - 1).getName());
                editor.putString("address", addressList.get(addressList.size() - position - 1).getAddress());
                editor.putString("num", addressList.get(addressList.size() - position - 1).getNum());
                editor.commit();

                finish();
                Toast.makeText(MyAddressActivity.this, "设置默认地址成功", Toast.LENGTH_SHORT).show();
//                addressList.get(position).getId();
//                Intent intent = new Intent(MyAddressActivity.this, BuyDetailsActivity.class);
//                startActivity(intent);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_address_exit_iv:
                finish();
                break;
            // 添加按钮
            case R.id.add_address_btn:
                Intent intent = new Intent(this, AddAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        myAddressAdapter = new MyAddressAdapter(this);
        myAddressAdapter.setActivity(this);

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();

        addressList = addressDao.queryBuilder().list();

        if (addressList.size() > 0) {
            myAddressAdapter.setAddressList(addressList);
            listView.setAdapter(myAddressAdapter);
        }

    }
}
