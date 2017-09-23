package com.obins.anne.viewpart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;
import com.obins.anne.utils.AppConfig;
import www.robinwatch.squid.utils.C0213L;

public class RobinListView extends ListView {
    public float mDownX = 0.0f;
    public float mDownY = 0.0f;

    public RobinListView(Context context) {
        super(context);
    }

    public RobinListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RobinListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case 0:
                this.mDownX = ev.getX();
                this.mDownY = ev.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
            case 3:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                if (Math.abs(ev.getX() - this.mDownX) <= Math.abs(ev.getY() - this.mDownY)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                C0213L.m20i(AppConfig.SERVER_IP, ">>>>>>>>>");
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
