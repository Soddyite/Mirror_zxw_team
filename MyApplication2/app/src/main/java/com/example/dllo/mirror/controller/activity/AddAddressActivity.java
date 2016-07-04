package com.example.dllo.mirror.controller.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 * 添加地址界面
 */
public class AddAddressActivity extends BaseActivity implements View.OnClickListener {
    // 输入的信息
    private EditText inputName;
    private EditText inputNum;
    private EditText inputAddress;
    // 加入数据库的
    private String name;
    private String num;
    private String address;
    private AddressDao addressDao;

    private List<Address> addressList;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {
        // 退出
        bindView(R.id.add_address_exit_iv).setOnClickListener(this);
        // 名字  手机号  地址
        inputName = (EditText) findViewById(R.id.input_name_et);
        inputNum = (EditText) findViewById(R.id.input_num_et);
        inputAddress = (EditText) findViewById(R.id.input_receive_address_et);
        // 提交地址
        bindView(R.id.commit_address).setOnClickListener(this);

    }

    @Override
    protected void initData() {

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();
        addressList = addressDao.queryBuilder().list();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_exit_iv:
                finish();
                break;
            case R.id.commit_address:

                name = inputName.getText().toString();
                num = inputNum.getText().toString();
                address = inputAddress.getText().toString();

                if (name.equals("") || num.equals("") || address.equals("")) {
                    Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
                } else {
                    Address addresses = new Address();
                    addresses.setName(name);
                    addresses.setNum(num);
                    addresses.setAddress(address);
                    addressDao.insert(addresses);
                    finish();
                    Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
