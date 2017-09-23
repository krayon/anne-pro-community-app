package com.obins.anne.viewpart;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class RobinViewPagerNoScrollTo extends ViewPager {
    public float mDownX = 0.0f;
    public float mDownY = 0.0f;

    public RobinViewPagerNoScrollTo(Context context) {
        super(context);
    }

    public RobinViewPagerNoScrollTo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
