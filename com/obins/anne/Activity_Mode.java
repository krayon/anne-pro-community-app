package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.BLEHandle;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Activity_Mode extends BaseActivity {
    private static final int BLE_MODE_SET_BACK = 1;
    private static final int BLE_MODE_SET_TIMEOUT = 2;
    private static final String TAG = "Activity_Mode";
    private RelativeLayout backBtn;
    private Switch comp_switch;
    private final BroadcastReceiver mGattUpdateReceiver = new C01241();
    public Handler mHandler = new C01252();
    private IntentFilter mIntentFilter;
    private ProgressDialog progressDialog;
    private int recoverFlag = 0;
    private int setModeflag = 0;

    class C01241 extends BroadcastReceiver {
        C01241() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_Mode.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_BLE.equals(action)) {
                String type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0213L.m20i(Activity_Mode.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_BLE_MODE_SET_ACK)) {
                    int result = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, -1);
                    Message msg = new Message();
                    Integer resultInteger = new Integer(result);
                    msg.what = 1;
                    Activity_Mode.this.mHandler.sendMessage(msg);
                }
            }
        }
    }

    class C01252 extends Handler {
        C01252() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Activity_Mode.this.setModeflag = 1;
                    return;
                case 2:
                    if (Activity_Mode.this.progressDialog.isShowing()) {
                        Activity_Mode.this.progressDialog.cancel();
                    }
                    if (Activity_Mode.this.setModeflag == 1) {
                        Toast.makeText(Activity_Mode.this, Activity_Mode.this.getResources().getString(C0182R.string.control_success), 0).show();
                        return;
                    } else {
                        Toast.makeText(Activity_Mode.this, Activity_Mode.this.getResources().getString(C0182R.string.control_faile), 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    class C01263 implements OnClickListener {
        C01263() {
        }

        public void onClick(View arg0) {
            Activity_Mode.this.finish();
        }
    }

    class C01304 implements OnCheckedChangeListener {
        C01304() {
        }

        public void onCheckedChanged(CompoundButton arg0, final boolean arg1) {
            if (Activity_Mode.this.recoverFlag == 1) {
                Activity_Mode.this.recoverFlag = 0;
            } else if (BluetoothLeService.getInstance().isConnectedOk()) {
                final AlertDialog alertDialog = new Builder(Activity_Mode.this).create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setContentView(C0182R.layout.delete_prompt);
                ((TextView) window.findViewById(C0182R.id.title_TextView)).setText(Activity_Mode.this.getResources().getString(C0182R.string.tip));
                ((TextView) window.findViewById(C0182R.id.content_TextView)).setText(Activity_Mode.this.getResources().getString(C0182R.string.is_sure_modify_mode));
                Button delete_Button = (Button) window.findViewById(C0182R.id.delete_Button);
                delete_Button.setText(Activity_Mode.this.getResources().getString(C0182R.string.sure));
                Button cancle_Button = (Button) window.findViewById(C0182R.id.cancle_Button);
                cancle_Button.setText(Activity_Mode.this.getResources().getString(C0182R.string.cancle));
                delete_Button.setOnClickListener(new OnClickListener() {

                    class C01271 implements Runnable {
                        C01271() {
                        }

                        public void run() {
                            try {
                                Thread.sleep(600);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Message message = new Message();
                            message.what = 2;
                            Activity_Mode.this.mHandler.sendMessage(message);
                        }
                    }

                    public void onClick(View arg0) {
                        Activity_Mode.this.setModeflag = -1;
                        Activity_Mode.this.progressDialog = ProgressDialog.show(Activity_Mode.this, AppConfig.SERVER_IP, Activity_Mode.this.getResources().getString(C0182R.string.setting));
                        new Thread(new C01271()).start();
                        if (arg1) {
                            BLEHandle.setMode(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 1);
                        } else {
                            BLEHandle.setMode(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 0);
                        }
                        alertDialog.cancel();
                    }
                });
                cancle_Button.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        boolean z = true;
                        Activity_Mode.this.recoverFlag = 1;
                        Switch access$6 = Activity_Mode.this.comp_switch;
                        if (arg1) {
                            z = false;
                        }
                        access$6.setChecked(z);
                        alertDialog.cancel();
                    }
                });
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_mode);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01263());
        initUI();
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_BLE);
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        if (BluetoothLeService.getInstance().isConnectedOk()) {
            this.comp_switch.setClickable(true);
            return;
        }
        Toast.makeText(this, getResources().getString(C0182R.string.connect_your_keyboard), 0).show();
        this.comp_switch.setClickable(false);
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    private void initUI() {
        this.comp_switch = (Switch) findViewById(C0182R.id.comp_switch);
        this.recoverFlag = 0;
        String devideName = AppConfig.SERVER_IP;
        if (BluetoothLeService.device != null) {
            devideName = BluetoothLeService.device.getName();
        }
        if (devideName != null && devideName.length() > 11) {
            if (BluetoothLeService.device.getName().substring(0, 10).equals(AppConfig.KEYBOARDNAME_COMP_OFF)) {
                this.comp_switch.setChecked(false);
            } else {
                this.comp_switch.setChecked(true);
            }
        }
        this.comp_switch.setOnCheckedChangeListener(new C01304());
    }
}
