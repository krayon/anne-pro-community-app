package com.obins.anne;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import com.obins.anne.utils.AppUtils;
import java.util.List;

public class BaseActivity extends Activity {
    private static boolean isActive = true;

    protected void onPause() {
        super.onStop();
        if (!isAppOnForeground()) {
            isActive = false;
        }
        AppUtils.context = null;
    }

    protected void onResume() {
        super.onResume();
        isActive = true;
        AppUtils.context = this;
    }

    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService("activity");
        String packageName = getApplicationContext().getPackageName();
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(packageName) && appProcess.importance == 100) {
                return true;
            }
        }
        return false;
    }
}
