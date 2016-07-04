package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.GoodsDetails;
import com.example.dllo.mirror.model.utils.ScreenUtils;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/14.
 * 上面listview的adapter
 */
public class TopListViewAdapter extends BaseAdapter {

    private List<GoodsDetails.DataBean.ListBean.DataInfoBean.GoodsDataBean> list;

    private Context context;
    private MyViewHolder myViewHolder;

    public TopListViewAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GoodsDetails.DataBean.ListBean.DataInfoBean.GoodsDataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("987", list.get(0).getName());
        Log.d("654", list.get(0).getLocation());

        myViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_top_listview_two, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        if (list.get(position).getName().equals("")) {
            myViewHolder.locationTv.setText(list.get(position).getLocation());
            myViewHolder.englishTv.setText(list.get(position).getEnglish());
            myViewHolder.countryTv.setText(list.get(position).getCountry());
            myViewHolder.contentTv.setText(list.get(position).getIntroContent());
            myViewHolder.nameTv.setText("");

        } else {
            myViewHolder.nameTv.setText(list.get(position).getName());
            myViewHolder.locationTv.setText("");
            myViewHolder.englishTv.setText("");
            myViewHolder.countryTv.setText("");
            myViewHolder.contentTv.setText(list.get(position).getIntroContent());
        }

        // 设置item显示一个屏幕的高度
        convertView.setMinimumHeight(ScreenUtils.getScreenHeight(context));

        return convertView;
    }

    class MyViewHolder {
        TextView locationTv;
        TextView englishTv;
        TextView countryTv;
        TextView contentTv;
        TextView nameTv;

        public MyViewHolder(View itemView) {
            locationTv = (TextView) itemView.findViewById(R.id.item_top_listview_location);
            englishTv = (TextView) itemView.findViewById(R.id.item_top_listview_english);
            countryTv = (TextView) itemView.findViewById(R.id.item_top_listview_country);
            contentTv = (TextView) itemView.findViewById(R.id.item_top_listview_content);
            nameTv = (TextView) itemView.findViewById(R.id.item_top_listview_name);
        }
    }
}
