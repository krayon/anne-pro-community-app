package com.obins.anne;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class Fragment_CustomLightEffect_Dynamic extends Fragment {
    private static final String TAG = "Fragment_CustomLightEffect_BaseSetting";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(C0182R.layout.fragment_customlighteffect_dynamic, new RelativeLayout(getActivity()), true);
    }
}
