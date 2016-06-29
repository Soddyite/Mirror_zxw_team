package com.example.dllo.mirror.controller.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.GoodsDetails;
import com.example.dllo.mirror.model.myinterface.PageItemClickListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/6/16.
 */
public class PageFragmentAdapter extends RecyclerView.Adapter<PageFragmentAdapter.MyViewHolder> {
    private Context context;
    PageItemClickListener pageItemClickListener;
    private List<GoodsDetails.DataBean.ListBean> list;
    private String goods_img, brand, goods_price, product_area, story_url, story_title;
    private String goods_name;


    public void setList(List<GoodsDetails.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

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
                .cacheOnDisk(true) //设置下载的图片是否缓存在SD卡中
                .considerExifParams(true) //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT) //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565) //设置图片的解码类型//
                //.decodingOptions(android.graphics.BitmapFactory.Options decodingOptions) //设置图片的解码配置
                .delayBeforeLoading(0) //int delayInMillis为你设置的下载前的延迟时间
                .resetViewBeforeLoading(false)//设置图片在下载前是否重置，复位
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间

                .build();//构建完成
        ImageLoader imageLoader = ImageLoader.getInstance();
        int type = Integer.parseInt(list.get(position).getType());
        goods_img = list.get(position).getData_info().getGoods_img();
        brand = list.get(position).getData_info().getBrand();
        story_url = list.get(position).getData_info().getStory_img();
        story_title = list.get(position).getData_info().getStory_title();
        goods_price = list.get(position).getData_info().getGoods_price();
        product_area = list.get(position).getData_info().getProduct_area();
        goods_name = list.get(position).getData_info().getGoods_name();
        holder.goods_name.setText(goods_name);
        holder.goods_price.setText(goods_price);
        holder.product_area.setText(product_area);
        if (type == 2) {
            holder.goods_name.setVisibility(View.GONE);
            holder.goods_price.setVisibility(View.GONE);
            holder.product_area.setVisibility(View.GONE);
            holder.brand.setText(story_title);
            ImageLoader.getInstance().displayImage(story_url, holder.imageView, options);
            //Picasso.with(context).load(story_url).fit().into(holder.imageView);
        } else {
            holder.brand.setText(brand);
            imageLoader.displayImage(goods_img, holder.imageView, options);

            // Picasso.with(context).load(goods_img).fit().into(holder.imageView);


        }
        if (holder != null) {
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
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        private TextView goods_price, goods_name, product_area, brand;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            goods_price = (TextView) itemView.findViewById(R.id.item_glass_goods_price);
            product_area = (TextView) itemView.findViewById(R.id.item_glass_product_area);
            brand = (TextView) itemView.findViewById(R.id.item_glass_brand);
            goods_name = (TextView) itemView.findViewById(R.id.item_goods_name);

        }
    }
}
