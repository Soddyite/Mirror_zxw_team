package com.example.dllo.mirror.controller.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.mirror.model.base.MyApplication;

/**
 * Created by zouliangyu on 16/6/13.
 */
public abstract class BaseFragment extends Fragment {
    private Context mContext;


    public Context getmContext() {
        return mContext;
    }

    /**
     * 加载布局
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayout(), container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = MyApplication.getContext();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    // 加载数据
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 加载布局
     *
     * @return 布局id
     */
    protected abstract int getLayout();

    /**
     * 加载组件
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 加载数据
     */
    protected abstract void initData();

    // 组件实例化不需要转型
    protected <T extends View> T bindView(int id) {
        return (T) getView().findViewById(id);
    }
}
