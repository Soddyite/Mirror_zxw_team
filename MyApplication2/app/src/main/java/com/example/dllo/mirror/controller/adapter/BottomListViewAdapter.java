package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.MyData;
import com.example.dllo.mirror.model.utils.ScreenUtils;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/15.
 * 下面的listview的适配器
 */
public class BottomListViewAdapter extends BaseAdapter {

    private List<MyData> myDatas;
    private Context context;


    public BottomListViewAdapter(Context context) {
        this.context = context;
    }

    public void setMyDatas(List<MyData> myDatas) {
        this.myDatas = myDatas;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return myDatas == null ? 0 : myDatas.size();
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_bottom_listview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(myDatas.get(position).getImage());

        // 设置item显示一个屏幕的高度
        convertView.setMinimumHeight(ScreenUtils.getScreenHeight(context));
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
