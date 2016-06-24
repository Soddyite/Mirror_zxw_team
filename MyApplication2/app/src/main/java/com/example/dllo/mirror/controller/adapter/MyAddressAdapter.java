package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.db.Address;
import com.example.dllo.mirror.model.db.AddressDao;
import com.example.dllo.mirror.model.utils.GreenDaoSingleton;
import java.util.List;

/**
 * Created by zouliangyu on 16/6/20.
 */
public class MyAddressAdapter extends BaseAdapter {
    private int pos;

    private AddressDao addressDao;
    private Context context;

    public MyAddressAdapter(Context context) {
        this.context = context;
    }

    private List<Address> addressList;

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
        pos = position;

        addressDao = GreenDaoSingleton.getOurInstance().getAddressDao();

        ViewHolder viewHolder = null;
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

        viewHolder.delTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> addressList = addressDao.queryBuilder().list();

            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView nameTv;
        TextView addressTv;
        TextView numTv;
        TextView delTv;

        public ViewHolder(View itemView) {
            nameTv = (TextView) itemView.findViewById(R.id.item_receiver_name_tv);
            addressTv = (TextView) itemView.findViewById(R.id.item_address);
            numTv = (TextView) itemView.findViewById(R.id.item_num_tv);
            delTv = (TextView) itemView.findViewById(R.id.item_del_tv);
        }
    }
}
