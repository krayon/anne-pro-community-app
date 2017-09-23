package com.obins.anne;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import www.robinwatch.squid.utils.C0213L;

public class MainActivity extends BaseActivity {
    private List<String> deviceMacList;
    private int deviceNum;
    public Handler mHandler = new C01811();

    class C01811 extends Handler {
        C01811() {
        }

        public void handleMessage(Message msg) {
            SharedPreferences settings = MainActivity.this.getSharedPreferences(MainActivity.this.getPackageName(), 0);
            int count = settings.getInt("FIRSTUSE", 0);
            Intent mIntent;
            if (count == 0 && MainActivity.this.deviceMacList.size() == 0) {
                mIntent = new Intent(MainActivity.this, Activity_LinkKBTip.class);
                AppUtils.deviceMacList = MainActivity.this.deviceMacList;
                MainActivity.this.startActivity(mIntent);
                MainActivity.this.finish();
            } else if (count != 0 || MainActivity.this.deviceMacList.size() <= 0) {
                mIntent = new Intent(MainActivity.this, Activity_DeviceList.class);
                AppUtils.deviceMacList = MainActivity.this.deviceMacList;
                MainActivity.this.startActivity(mIntent);
                MainActivity.this.finish();
            } else {
                Editor editor = settings.edit();
                editor.putInt("FIRSTUSE", 1);
                editor.commit();
                mIntent = new Intent(MainActivity.this, Activity_LinkKBTip.class);
                AppUtils.deviceMacList = MainActivity.this.deviceMacList;
                MainActivity.this.startActivity(mIntent);
                MainActivity.this.finish();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findDevice();
        goOnnextPage();
    }

    protected void onResume() {
    }

    public int findDevice() {
        BluetoothManager mBluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (mBluetoothManager == null) {
            return -1;
        }
        BluetoothAdapter mBtAdapter = mBluetoothManager.getAdapter();
        if (mBtAdapter == null) {
            return -1;
        }
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        this.deviceMacList = new ArrayList();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName() != null && device.getName().length() == 15 && device.getName().regionMatches(0, AppConfig.KEYBOARDNAME, 0, AppConfig.KEYBOARDNAME.length())) {
                    String deviceMac = device.getAddress();
                    boolean isExit = false;
                    C0213L.m19i("deviceMac = " + deviceMac);
                    for (int i = 0; i < this.deviceMacList.size(); i++) {
                        if (deviceMac.substring(2).equals(((String) this.deviceMacList.get(i)).substring(2))) {
                            isExit = true;
                            break;
                        }
                    }
                    if (!isExit) {
                        this.deviceMacList.add(deviceMac);
                    }
                }
            }
        }
        return this.deviceMacList.size();
    }

    private void goOnnextPage() {
        SharedPreferences settings = getSharedPreferences(getPackageName(), 0);
        int count = settings.getInt("FIRSTUSE", 0);
        Intent mIntent;
        if (count == 0 && this.deviceMacList.size() == 0) {
            mIntent = new Intent(this, Activity_LinkKBTip.class);
            AppUtils.deviceMacList = this.deviceMacList;
            startActivity(mIntent);
            finish();
        } else if (count != 0 || this.deviceMacList.size() <= 0) {
            mIntent = new Intent(this, Activity_DeviceList.class);
            AppUtils.deviceMacList = this.deviceMacList;
            startActivity(mIntent);
            finish();
        } else {
            Editor editor = settings.edit();
            editor.putInt("FIRSTUSE", 1);
            editor.commit();
            mIntent = new Intent(this, Activity_LinkKBTip.class);
            AppUtils.deviceMacList = this.deviceMacList;
            startActivity(mIntent);
            finish();
        }
    }
}
