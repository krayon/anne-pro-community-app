package com.obins.anne.viewpart;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class RobinViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public float mDownX = 0.0f;
    public float mDownY = 0.0f;

    public RobinViewPager(Context context) {
        super(context);
    }

    public RobinViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public void scrollTo(int x, int y) {
        if (this.isCanScroll) {
            super.scrollTo(x, y);
        }
    }
}
