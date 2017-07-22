package com.w.jinone.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by yutao on 2016/7/15 0015.
 * bottomBar
 */
public class BottomBarLayout extends LinearLayout {



    public BottomBarLayout(Context context) {
        super(context);
    }

    public BottomBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
