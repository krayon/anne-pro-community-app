package com.obins.anne.viewpart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;

public class RobinFragmetAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mFragments;

    public RobinFragmetAdapter(FragmentManager fm) {
        super(fm);
    }

    public RobinFragmetAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    public ArrayList<Fragment> getFragment() {
        return this.mFragments;
    }

    public int getCount() {
        return this.mFragments.size();
    }

    public Fragment getItem(int position) {
        return (Fragment) this.mFragments.get(position);
    }
}
