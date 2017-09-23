package com.obins.anne;

import android.support.v4.app.FragmentActivity;
import com.obins.anne.utils.AppUtils;

public class BaseActivityFragment extends FragmentActivity {
    protected void onResume() {
        super.onResume();
        AppUtils.context = this;
    }

    protected void onPause() {
        super.onPause();
        AppUtils.context = null;
    }
}
