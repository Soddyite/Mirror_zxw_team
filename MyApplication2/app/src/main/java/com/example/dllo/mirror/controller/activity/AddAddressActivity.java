package com.example.dllo.mirror.controller.activity;

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

<<<<<<< HEAD

//        // 遍历数据库, 是否添加过
//        List<Address> addressList = addressDao.queryBuilder().list();
//        if (addressList.size() > 0){
//            for (Address address : addressList) {
//                if (address.getNum().toString().equals(num.toString())){
//
//                }
//            }

//        }


=======
        addressList = addressDao.queryBuilder().list();
>>>>>>> 7e657aac5f37423c4eb9a71530b0d407e083b628

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_address_exit_iv:
                finish();
                break;
            case R.id.commit_address:
<<<<<<< HEAD

=======
>>>>>>> 7e657aac5f37423c4eb9a71530b0d407e083b628
                name = inputName.getText().toString();
                num = inputNum.getText().toString();
                address = inputAddress.getText().toString();

                if (name.equals("") || num.equals("") || address.equals("")) {
                    Toast.makeText(this, "请填写信息", Toast.LENGTH_SHORT).show();
                } else  {
<<<<<<< HEAD
                    Log.d("AddAddressActivity", name);
                    Log.d("AddAddressActivity", num);
                    Log.d("AddAddressActivity", address);
=======
>>>>>>> 7e657aac5f37423c4eb9a71530b0d407e083b628
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
