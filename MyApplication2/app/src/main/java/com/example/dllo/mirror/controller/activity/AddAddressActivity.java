package com.example.dllo.mirror.controller.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 * 添加地址界面
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    private EditText inputName;
    private EditText inputNum;
    private EditText inputAddress;
    private String name;
    private String num;
    private String address;
    private AddressDao addressDao;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        // 退出
        bindView(R.id.add_address_exit_iv).setOnClickListener(this);
        // 输出名字  手机号  地址
        inputName = (EditText) findViewById(R.id.input_name_et);
        inputNum = (EditText) findViewById(R.id.input_num_et);
        inputAddress = (EditText) findViewById(R.id.input_receive_address_et);
        // 提交地址
        bindView(R.id.commit_address).setOnClickListener(this);

    }

    @Override
    protected void initData() {

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();


//        // 遍历数据库, 是否添加过
//        List<Address> addressList = addressDao.queryBuilder().list();
//        if (addressList.size() > 0){
//            for (Address address : addressList) {
//                if (address.getNum().toString().equals(num.toString())){
//
//                }
//            }

//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_exit_iv:
                finish();
                break;
            case R.id.commit_address:
                List<Address> addressList = new ArrayList<>();
                name = inputName.getText().toString();
                num = inputNum.getText().toString();
                address = inputAddress.getText().toString();

                Log.d("AddAddressActivity", name);
                Log.d("AddAddressActivity", num);
                Log.d("AddAddressActivity", address);

                Address addresses = new Address();
                addresses.setName(name);
                addresses.setNum(num);
                addresses.setAddress(address);

//                addressList.add(0, addresses);

                addressDao.insert(addresses);

                finish();
                break;
        }
    }
}
