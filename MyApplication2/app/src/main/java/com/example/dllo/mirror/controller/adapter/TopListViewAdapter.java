package com.example.dllo.mirror.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.model.bean.MyDataTwo;
import com.example.dllo.mirror.model.utils.ScreenUtils;

import java.util.List;

/**
 * Created by zouliangyu on 16/6/14.
 * 上面listview的adapter
 */
public class TopListViewAdapter extends BaseAdapter {
    private List<MyDataTwo> myDataTwos;

    private Context context;

    public TopListViewAdapter(Context context) {
        this.context = context;
    }

    public void setMyDatasTwo(List<MyDataTwo> myDataTwos) {
        this.myDataTwos = myDataTwos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myDataTwos == null ? 0 : myDataTwos.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_details_top_listiew_, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.title.setText(myDataTwos.get(position).getTitle());
        viewHolder.details.setText(myDataTwos.get(position).getDetails());


        // 设置item显示一个屏幕的高度
        convertView.setMinimumHeight(ScreenUtils.getScreenHeight(context));

        return convertView;
    }

    class ViewHolder {
        TextView title;
        TextView details;

        public ViewHolder(View itemView) {
            title = (TextView) itemView.findViewById(R.id.title_tv);
            details = (TextView) itemView.findViewById(R.id.details_tv);
        }
    }
}
