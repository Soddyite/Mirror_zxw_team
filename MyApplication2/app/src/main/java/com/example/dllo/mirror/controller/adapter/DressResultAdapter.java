package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.GoodsDetails;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/16.
 * 佩戴效果页面的适配器
 */
public class DressResultAdapter extends BaseAdapter {
    private int[] image;
    private Context context;
    private DisplayImageOptions options;
    private ViewHolder viewHolder;

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
        viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_dress_result, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        options = new DisplayImageOptions.Builder()
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                .build();//构建完成
//        for (int i = 0; i < list.get(position).getData_info().getStory_data().getImg_array().size(); i++) {
//            ImageLoader.getInstance().displayImage(list.get(position).getData_info().getStory_data().getImg_array().get(i)
//                    , viewHolder.imageView, options);
//        }
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
