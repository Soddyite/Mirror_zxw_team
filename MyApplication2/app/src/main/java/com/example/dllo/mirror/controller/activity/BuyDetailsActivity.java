package com.example.dllo.mirror.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;

import com.ease.paysdk.FuQianLa;
import com.ease.paysdk.FuQianLaPay;
import com.ease.paysdk.bean.FuQianLaResult;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.utils.Merchant;
import com.example.dllo.mirror.model.utils.OrderUtils;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 * 购买详情界面
 */
public class BuyDetailsActivity extends BaseActivity implements View.OnClickListener {
    PopupWindow popupWindow;
    private FuQianLaPay pay;
    TextView addAddress;
    RelativeLayout relativeLayout;

    // 添加地址后显示的内容
    private TextView receiverTv; // 收件人
    private TextView colonOne;
    private TextView nameTv;
    private TextView addressTv;
    private TextView colonTwo;
    private TextView address; // 地址
    private TextView numTv; // 电话号
    // 数据库以及缓存
    private AddressDao addressDao;
    private String name;
    private String address1;
    private String num;
    private List<Address> addressList;
    // 添加地址下面的商品图片价格配送
    private ImageView imageView;
    private TextView titleTv;
    private TextView moneyTv;

    private DisplayImageOptions options;

    private String imgUrl;
    private String name1;
    private String money;

    @Override
    protected int getLayout() {
        return R.layout.activity_buy_details;
    }

    @Override
    protected void initView() {
        // 退出
        bindView(R.id.buy_details_exit_iv).setOnClickListener(this);

        // 添加地址
        addAddress = bindView(R.id.buy_details_add_address);
        addAddress.setOnClickListener(this);

        // 购买
        bindView(R.id.buy_btn).setOnClickListener(this);

        relativeLayout = bindView(R.id.buy_details_relativelayout);
        relativeLayout.setOnClickListener(this);
        // 添加地址后显示的内容
        receiverTv = (TextView) findViewById(R.id.buy_details_receiver_tv);
        colonOne = (TextView) findViewById(R.id.buy_details_colon_tv);
        nameTv = (TextView) findViewById(R.id.buy_details_receiver_name_tv);
        addressTv = (TextView) findViewById(R.id.buy_details_address_tv);
        colonTwo = (TextView) findViewById(R.id.buy_details_colon);
        address = (TextView) findViewById(R.id.buy_details_address);
        numTv = (TextView) findViewById(R.id.buy_details_num_tv);

        imageView = (ImageView) findViewById(R.id.buy_details_glass_iv);
        titleTv = (TextView) findViewById(R.id.buy_details_title_tv);
        moneyTv = (TextView) findViewById(R.id.buy_details_money_tv);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        imgUrl = intent.getStringExtra("img");
        name1 = intent.getStringExtra("name");
        money = intent.getStringExtra("money");
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .build();//构建完成

        ImageLoader.getInstance().displayImage(imgUrl, imageView, options);
        titleTv.setText(name1);
        moneyTv.setText(money);

        initPopup();

        pay = new FuQianLaPay.Builder()
                .activity(this)
                .promode(true)
                .partner(Merchant.MERCHANT_NO)
                .orderID(OrderUtils.getOutTradeNo())//订单号
                .amount(0.01)//金额
                .subject("通道前置Activity-商品名称")//商品名称
                .body("通道前置Activity-交易详情")//商品交易详情
                .notifyUrl(Merchant.MERCHANT_NOTIFY_URL)
                .build();

    }

    private void initPopup() {

        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_custom, null);

        LinearLayout btnWX, btnAli;

        btnWX = (LinearLayout) view.findViewById(R.id.activity_custom_wxpay);
        btnAli = (LinearLayout) view.findViewById(R.id.activity_custom_alipay);

        btnWX.setOnClickListener(this);
        btnAli.setOnClickListener(this);

        popupWindow.setContentView(view);

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
                if (!popupWindow.isShowing()) {
                    popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);
                }

                break;
            case R.id.buy_details_relativelayout:
                if (popupWindow.isShowing()) {

                    popupWindow.dismiss();
                }
                break;

            case R.id.activity_custom_wxpay:
                pay.startPay(FuQianLa.WX);
                break;
            case R.id.activity_custom_alipay:
                pay.startPay(FuQianLa.ALI);
                break;

        }
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //返回支付结果
        if (requestCode == FuQianLa.REQUESTCODE
                && resultCode == FuQianLa.RESULTCODE
                && data != null) {
            FuQianLaResult result = data.getParcelableExtra(FuQianLa.PAYRESULT_KEY);
            Toast.makeText(this, result.payCode, Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

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
