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
import com.example.dllo.mirror.model.bean.MyData;
import com.example.dllo.mirror.model.utils.ScreenUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/15.
 * 下面的listview的适配器
 */
public class BottomListViewAdapter extends BaseAdapter {

    private List<GoodsDetails.DataBean.ListBean.DataInfoBean.DesignDesBean> list;
    private Context context;
    private DisplayImageOptions options;


    public BottomListViewAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<GoodsDetails.DataBean.ListBean.DataInfoBean.DesignDesBean> list) {
        this.list = list;
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_bottom_listview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .build();//构建完成
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),
                viewHolder.imageView, options);


//        viewHolder.imageView.setImageResource(myDatas.get(position).getImage());

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
