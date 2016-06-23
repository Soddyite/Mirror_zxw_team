package com.example.dllo.mirror.controller.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.myinterface.PageItemClickListener;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by dllo on 16/6/16.
 */
public class PageFragmentAdapter extends RecyclerView.Adapter<PageFragmentAdapter.MyViewHolder> {
    private Context context;
    PageItemClickListener pageItemClickListener;

    public PageFragmentAdapter(Context context) {
        this.context = context;
    }

    public void setPageItemClickListener(PageItemClickListener pageItemClickListener) {
        this.pageItemClickListener = pageItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mainrecycle, parent, false);
        MyViewHolder myviewHolder = new MyViewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                .build();//构建完成
        ImageLoader.getInstance().displayImage("http://cdn.duitang.com/uploads/item/201511/21/20151121222701_Qym8J.jpeg", holder.imageView,options);
        if (holder!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pageItemClickListener.onClick(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }
    }
}
