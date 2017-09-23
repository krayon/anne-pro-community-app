package com.obins.anne.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

public class DeviceIdFactory {
    public static final String PREFS_ANDROID_ID = "android_id";
    public static final String PREFS_DEVICE_ID = "device_id";
    public static final String PREFS_DEVICE_UUID = "device_uuid";
    public static final String PREFS_MAC_ID = "mac_id";
    protected static String androidId;
    protected static String deviceId;
    protected static String macId;

    public DeviceIdFactory(Context context) {
        if (deviceId == null || androidId == null) {
            synchronized (DeviceIdFactory.class) {
                SharedPreferences prefs = context.getSharedPreferences(context.getPackageName(), 0);
                String id1 = prefs.getString(PREFS_DEVICE_ID, null);
                String id2 = prefs.getString(PREFS_ANDROID_ID, null);
                String id3 = prefs.getString(PREFS_MAC_ID, null);
                if (id1 != null) {
                    deviceId = id1;
                } else {
                    deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                    prefs.edit().putString(PREFS_DEVICE_ID, deviceId).commit();
                }
                if (id2 != null) {
                    androidId = id2;
                } else {
                    androidId = Secure.getString(context.getContentResolver(), PREFS_ANDROID_ID);
                    prefs.edit().putString(PREFS_ANDROID_ID, androidId).commit();
                }
                if (id3 != null) {
                    macId = id3;
                } else {
                    macId = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
                    prefs.edit().putString(PREFS_MAC_ID, macId).commit();
                }
            }
        }
    }

    public String getDeviceId() {
        if (deviceId == null) {
            return "000000000000000";
        }
        return deviceId;
    }

    public String getAndroidId() {
        return androidId;
    }

    public String getMacId() {
        return macId;
    }
}
