package com.example.dllo.mirror.controller.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dllo.mirror.R;
import com.example.dllo.mirror.controller.adapter.MainAdapter;
import com.example.dllo.mirror.controller.adapter.PageFragmentAdapter;
import com.example.dllo.mirror.model.utils.OkHttpClientManager;

/**
 * Created by dllo on 16/6/16.
 */
@SuppressLint("ValidFragment")
public class PageFragment extends Fragment implements View.OnClickListener {
    private String title;
    private View titleLayout;
    private RecyclerView recyclerView;
    private TextView oneTV, twoTV, threeTV, fourTV, fiveTV, toOneTV, titleTv;
    private ImageView shopCar;
    private PageFragmentAdapter pageFragmentAdapter;
    private HongXiangListener hongXiangListener;
    private PopupWindow pop;
    ScaleAnimation scaleAnimation;

    @SuppressLint("ValidFragment")
    public PageFragment(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        titleLayout = view.findViewById(R.id.pagefragment_titlelayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.pagefragment_recycleView);
        titleTv = (TextView) view.findViewById(R.id.pagefragment_title);
        shopCar = (ImageView) view.findViewById(R.id.pagefragment_shopcar);
        //购物车页不显示RecyclerView,显示购物车图标
        if (title.equals("我的購物車")) {
            recyclerView.setVisibility(View.INVISIBLE);
            shopCar.setVisibility(View.VISIBLE);
        }
        titleTv.setText(title);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        hongXiangListener = (HongXiangListener) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        popUpWindow();
        titleLayout.setOnClickListener(this);
        pageFragmentAdapter = new PageFragmentAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 20));
        recyclerView.setAdapter(pageFragmentAdapter);
        scaleAnimation = new ScaleAnimation(1, 1.5f, 1, 1.5f);
        scaleAnimation.setDuration(1000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pagefragment_titlelayout:
                recyclerView.setVisibility(View.INVISIBLE);
                titleLayout.setVisibility(View.INVISIBLE);
                shopCar.setVisibility(View.INVISIBLE);
                pop.showAsDropDown(titleLayout);
                switch (title) {
                    case "瀏覽所有類型":
                        TextViewChange(oneTV);
                        break;
                    case "瀏覽平光鏡":
                        TextViewChange(twoTV);
                        break;
                    case "瀏覽太陽鏡":
                        TextViewChange(threeTV);
                        break;
                    case "專題分享":
                        TextViewChange(fourTV);
                        break;
                    case "我的購物車":
                        TextViewChange(fiveTV);
                        break;
                }
                break;
            case R.id.pop_one:
                hongXiangListener.change(0);
                pop.dismiss();
                break;
            case R.id.pop_two:
                hongXiangListener.change(1);
                pop.dismiss();
                break;
            case R.id.pop_three:
                hongXiangListener.change(2);
                pop.dismiss();
                break;
            case R.id.pop_four:
                hongXiangListener.change(3);
                pop.dismiss();
                break;
            case R.id.pop_five:
                hongXiangListener.change(4);
                pop.dismiss();
                break;
            case R.id.pop_toone:
                hongXiangListener.change(0);
                pop.dismiss();
                break;

        }
    }

    /**
     * 传入一个textView,改变pop上TextView的字体颜色
     *
     * @param textview
     */
    private void TextViewChange(TextView textview) {
        textview.setTextColor(0xffffff00);
        textview.setAnimation(scaleAnimation);
    }

    /**
     * popupWindow的集体设置
     */
    private void popUpWindow() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.pop_view, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setAnimationStyle(R.style.pop_anim);
        //popupWindow的隐藏监听
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //无论如何标题都要隐藏
                titleLayout.setVisibility(View.VISIBLE);
                //我的购物车界面特殊,判断
                if (!(title.equals("我的購物車"))) {
                    recyclerView.setVisibility(View.VISIBLE);
                }
                if (title.equals("我的購物車")) {
                    shopCar.setVisibility(View.VISIBLE);
                }

            }
        });
        pop.setFocusable(true);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setTouchable(true);
        oneTV = (TextView) view.findViewById(R.id.pop_one);
        twoTV = (TextView) view.findViewById(R.id.pop_two);
        threeTV = (TextView) view.findViewById(R.id.pop_three);
        fourTV = (TextView) view.findViewById(R.id.pop_four);
        fiveTV = (TextView) view.findViewById(R.id.pop_five);
        toOneTV = (TextView) view.findViewById(R.id.pop_toone);
        oneTV.setOnClickListener(this);
        twoTV.setOnClickListener(this);
        threeTV.setOnClickListener(this);
        fourTV.setOnClickListener(this);
        fiveTV.setOnClickListener(this);
        toOneTV.setOnClickListener(this);

    }
    public interface HongXiangListener {
        void change(int pos);
    }

}