package com.obins.anne;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import www.robinwatch.squid.utils.C0213L;

public class Activity_LinkKBTip extends BaseActivity {
    private RelativeLayout backBtn;
    private List<String> deviceMacList;
    private int deviceNum;
    private Button goOn_Button;
    public Handler mHandler = new C00951();

    class C00951 extends Handler {
        C00951() {
        }

        public void handleMessage(Message msg) {
            Editor editor = Activity_LinkKBTip.this.getSharedPreferences(Activity_LinkKBTip.this.getPackageName(), 0).edit();
            editor.putInt("FIRSTUSE", 1);
            editor.commit();
            Intent mIntent = new Intent(Activity_LinkKBTip.this, Activity_DeviceList.class);
            AppUtils.deviceMacList = Activity_LinkKBTip.this.deviceMacList;
            Activity_LinkKBTip.this.startActivity(mIntent);
            Activity_LinkKBTip.this.finish();
        }
    }

    class C00962 implements Runnable {
        C00962() {
        }

        public void run() {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Activity_LinkKBTip.this.checkDevice() >= 1) {
                Message message = new Message();
                message.what = 1;
                Activity_LinkKBTip.this.mHandler.sendMessage(message);
            }
        }
    }

    class C00973 implements OnClickListener {
        C00973() {
        }

        public void onClick(View arg0) {
            Toast.makeText(Activity_LinkKBTip.this, Activity_LinkKBTip.this.getResources().getString(C0182R.string.connect_your_keyboard), 0).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_linkkbtip);
        new Thread(new C00962()).start();
        this.goOn_Button = (Button) findViewById(C0182R.id.goOn_Button);
        this.goOn_Button.setOnClickListener(new C00973());
    }

    private int checkDevice() {
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
                C0213L.m19i("device.getName() = " + device.getName());
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
}
