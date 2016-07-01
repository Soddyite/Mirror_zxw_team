package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/24.
 * 编辑界面
 */
public class EditAddressActivity extends BaseActivity implements View.OnClickListener {

    private ImageView exitIv;
    private TextView editName;
    private TextView editNum;
    private TextView editAddress;
    private Button changeBtn;

    private AddressDao addressDao;
    private List<Address> addressList;

    private int pos;
    private int position;

    @Override
    protected int getLayout() {
        return R.layout.activity_edit_address;
    }

    @Override
    protected void initView() {
        exitIv = (ImageView) findViewById(R.id.edit_address_exit_iv);
        exitIv.setOnClickListener(this);
        editName = (TextView) findViewById(R.id.edit_name_et);
        editNum = (TextView) findViewById(R.id.edit_num_et);
        editAddress = (TextView) findViewById(R.id.edit_receive_address_et);
        changeBtn = (Button) findViewById(R.id.commit_change);
        changeBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();
        addressList = addressDao.queryBuilder().list();

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);
        position = addressList.size() - pos - 1;

        Log.d("123", "position:" + position);

        editName.setText(addressList.get(position).getName());
        editAddress.setText(addressList.get(position).getAddress());
        editNum.setText(addressList.get(position).getNum());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit_address_exit_iv:
                finish();
                break;
            case R.id.commit_change:

                String name = editName.getText().toString();
                String num = editNum.getText().toString();
                String address = editAddress.getText().toString();

                addressDao.update(new Address(Long.valueOf(position + 1), name, num, address));

                finish();
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
