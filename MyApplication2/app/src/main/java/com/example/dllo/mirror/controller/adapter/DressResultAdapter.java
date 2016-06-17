package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.mirror.R;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/16.
 * 佩戴效果页面的适配器
 */
public class DressResultAdapter extends BaseAdapter {
    private int[] image;
    private Context context;

    public DressResultAdapter(Context context) {
        this.context = context;
    }


    public void setImage(int[] image) {
        this.image = image;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return image == null ? 0 : image.length;
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
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_dress_result, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(image[position]);
        return convertView;
    }


    class ViewHolder {
        ImageView imageView;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.item_dress_iv);
        }
    }
}
