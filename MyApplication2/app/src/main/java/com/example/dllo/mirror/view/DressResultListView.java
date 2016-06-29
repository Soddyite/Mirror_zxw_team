package com.example.dllo.mirror.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by zouliangyu on 16/6/29.
 */
public class DressResultListView extends ListView {
    private boolean isTouch = true;

    public DressResultListView(Context context) {
        super(context);
    }

    public DressResultListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DressResultListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isTouch == false) {
            return isTouch;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setTouch(boolean touch) {
        isTouch = touch;
    }
}
