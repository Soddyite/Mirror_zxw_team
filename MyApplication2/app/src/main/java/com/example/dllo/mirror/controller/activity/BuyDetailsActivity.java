package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 */
public class BuyDetailsActivity extends BaseActivity implements View.OnClickListener {

    private TextView receiverTv; // 收件人
    private TextView colonOne;
    private TextView nameTv;
    private TextView addressTv;
    private TextView colonTwo;
    private TextView address; // 地址
    private TextView numTv; // 电话号


    private AddressDao addressDao;
    private String name;
    private String address1;
    private String num;
    private List<Address> addressList;

    @Override
    protected int getLayout() {
        return R.layout.activity_buy_details;
    }

    @Override
    protected void initView() {
        // 退出
        bindView(R.id.buy_details_exit_iv).setOnClickListener(this);
        // 添加地址
        bindView(R.id.buy_details_add_address).setOnClickListener(this);
        // 购买
        bindView(R.id.buy_btn).setOnClickListener(this);


        receiverTv = (TextView) findViewById(R.id.buy_details_receiver_tv);
        colonOne = (TextView) findViewById(R.id.buy_details_colon_tv);
        nameTv = (TextView) findViewById(R.id.buy_details_receiver_name_tv);
        addressTv = (TextView) findViewById(R.id.buy_details_address_tv);
        colonTwo = (TextView) findViewById(R.id.buy_details_colon);
        address = (TextView) findViewById(R.id.buy_details_address);

        numTv = (TextView) findViewById(R.id.buy_details_num_tv);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buy_details_exit_iv:
                finish();
                break;
            case R.id.buy_details_add_address:
                Intent intentAddress = new Intent(this, MyAddressActivity.class);
                startActivity(intentAddress);
                break;
            // 购买
            case R.id.buy_btn:

                break;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sharedPreferences = getSharedPreferences("address", MODE_PRIVATE);
        name = sharedPreferences.getString("name", "---");
        address1 = sharedPreferences.getString("address", "---");
        num = sharedPreferences.getString("num", "---");

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();

        addressList = addressDao.queryBuilder().list();


        if (addressList.size() == 1) {
            Log.d("BuyDetailsActivity521", addressList.get(0).getName());
            receiverTv.setVisibility(View.VISIBLE);
            colonOne.setVisibility(View.VISIBLE);
            nameTv.setText(addressList.get(0).getName());

            addressTv.setVisibility(View.VISIBLE);

            colonTwo.setVisibility(View.VISIBLE);

            address.setText(addressList.get(0).getAddress());

            numTv.setText(addressList.get(0).getNum());

        } else if (addressList.size() > 1) {
            if (!name.equals("---")) {

                receiverTv.setVisibility(View.VISIBLE);
                colonOne.setVisibility(View.VISIBLE);

                nameTv.setText(name);

                addressTv.setVisibility(View.VISIBLE);
                colonTwo.setVisibility(View.VISIBLE);
                address.setText(address1);

                numTv.setText(num);
            } else {
                receiverTv.setVisibility(View.VISIBLE);
                colonOne.setVisibility(View.VISIBLE);
                nameTv.setText(addressList.get(0).getName());

                addressTv.setVisibility(View.VISIBLE);

                colonTwo.setVisibility(View.VISIBLE);

                address.setText(addressList.get(0).getAddress());

                numTv.setText(addressList.get(0).getNum());
            }
        } else {

            receiverTv.setVisibility(View.GONE);
            colonOne.setVisibility(View.GONE);

            nameTv.setText("请添加收件人信息");

            addressTv.setVisibility(View.GONE);
            colonTwo.setVisibility(View.GONE);
            address.setText("");

            numTv.setText("");
        }

        receiverTv.setTextColor(Color.BLACK);
        colonOne.setTextColor(Color.BLACK);
        nameTv.setTextColor(Color.BLACK);
        addressTv.setTextColor(Color.BLACK);
        colonTwo.setTextColor(Color.BLACK);
        address.setTextColor(Color.BLACK);

        numTv.setTextColor(Color.BLACK);
    }


}
