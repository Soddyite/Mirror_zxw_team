package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.activity.EditAddressActivity;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import com.example.dllo.mirror.model.utils.ScreenUtils;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 */
public class MyAddressAdapter extends BaseAdapter {
    private AddressDao addressDao;
    private Context context;
    private ViewHolder viewHolder;
    private List<Address> addressList;

    public MyAddressAdapter(Context context) {
        this.context = context;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return addressList == null ? 0 : addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();

        viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameTv.setText(addressList.get(addressList.size() - position - 1).getName());
        viewHolder.addressTv.setText(addressList.get(addressList.size() - position - 1).getAddress());
        viewHolder.numTv.setText(addressList.get(addressList.size() - position - 1).getNum());

        viewHolder.relativeLayout.setMinimumWidth(ScreenUtils.getScreenWidth(context));
        // 编辑
        viewHolder.editIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditAddressActivity.class);
                intent.putExtra("position", position);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        viewHolder.delIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressDao.delete(addressList.get(addressList.size() - position - 1));
                addressList.remove(addressList.size() - position - 1);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView nameTv;
        TextView addressTv;
        TextView numTv;
        TextView delTv;
        RelativeLayout relativeLayout;
        ImageView editIv;
        ImageView delIv;

        public ViewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_receiver_name_tv);
            addressTv = (TextView) itemView.findViewById(R.id.item_address);
            numTv = (TextView) itemView.findViewById(R.id.item_num_tv);
            delTv = (TextView) itemView.findViewById(R.id.item_del_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_address_relativelayout);
            editIv = (ImageView) itemView.findViewById(R.id.item_edit_iv);
            delIv = (ImageView) itemView.findViewById(R.id.item_del_iv);

        }
    }


}
