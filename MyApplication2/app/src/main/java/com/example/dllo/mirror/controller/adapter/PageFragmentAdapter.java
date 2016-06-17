package com.example.dllo.mirror.controller.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.myinterface.PageItemClickListener;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;

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
