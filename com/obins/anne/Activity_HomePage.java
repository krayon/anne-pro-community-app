package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.db.DeviceStateDB;
import com.obins.anne.update.AppUpdate;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.DensityUtil;
import com.obins.anne.utils.KeyboardAlignment;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import com.obins.anne.utils.NormalFun;
import com.obins.anne.viewpart.CircleView;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.network.HttpCallback;
import www.robinwatch.squid.utils.C0213L;

public class Activity_HomePage extends BaseActivity implements OnClickListener {
    private static final int GET_APPVERSION_FAIL = 1;
    private static final int GET_APPVERSION_SUCCESS = 0;
    private static String TAG = "Activity_HomePage";
    private static final int UPDATA_UI_ALIGNMENT = 2;
    private static final int UPDATA_UI_LEDID = 3;
    private static BluetoothManager mBluetoothManager;
    private RelativeLayout alignment_RelativeLayout;
    private TextView alignment_TextView;
    private RelativeLayout bk_RelativeLayout;
    private String desc = AppConfig.SERVER_IP;
    private TextView editon_TextView;
    private TextView fw_TextView;
    private int getMacronum = 0;
    HttpCallback getappversionback = new C02361();
    private Handler handler = new C00882();
    private RelativeLayout info_RelativeLayout;
    private boolean is_first_open = true;
    private boolean is_first_start = true;
    private boolean is_page_show = false;
    private RelativeLayout lighteffect_RelativeLayout;
    private TextView lighteffect_TextView;
    private boolean mBleSupported = true;
    private BluetoothLeService mBluetoothLeService = null;
    private BluetoothAdapter mBtAdapter = null;
    private IntentFilter mFilter;
    private final BroadcastReceiver mGattUpdateReceiver = new C00893();
    private IntentFilter mIntentFilter;
    private int mNumDevs = 0;
    private TextView mac_TextView;
    private RelativeLayout macro_RelativeLayout;
    private TextView macro_TextView;
    private boolean pageis_appear = false;
    private RelativeLayout setting_RelativeLayout;
    private CircleView tip_CircleView;
    private RelativeLayout tip_RelativeLayout;
    private TextView tip_TextView;
    private String url = AppConfig.SERVER_IP;

    class C00882 extends Handler {
        C00882() {
        }

        public void handleMessage(Message msg) {
            String name;
            switch (msg.what) {
                case 0:
                    Activity_HomePage.this.judgeupdate(msg.obj);
                    break;
                case 2:
                    int alignmentID = ((Integer) msg.obj).intValue();
                    C0213L.m19i(" alignmentID =" + alignmentID);
                    if (alignmentID <= 3) {
                        if (alignmentID != 0) {
                            Activity_HomePage.this.alignment_TextView.setText("默认配列" + alignmentID);
                            break;
                        } else {
                            Activity_HomePage.this.alignment_TextView.setText("默认配列1");
                            break;
                        }
                    }
                    name = AppConfig.SERVER_IP;
                    if (AppUtils.nowKBCustomAlignmentID != 0) {
                        name = KeyboardAlignment.getAlignmentNameWithId(AppUtils.databaseManager, AppUtils.nowKBCustomAlignmentID);
                    }
                    if (name != null && !name.isEmpty() && !name.equals(AppConfig.SERVER_IP)) {
                        Activity_HomePage.this.alignment_TextView.setText(name);
                        break;
                    } else {
                        Activity_HomePage.this.alignment_TextView.setText("用户自定义配列");
                        break;
                    }
                    break;
                case 3:
                    int ledID = ((Integer) msg.obj).intValue();
                    if (ledID != 128) {
                        if (ledID != -1) {
                            name = (String) KeyboardLighteffectUtil.lightEffectIDAndNameMap.get(Integer.valueOf(ledID));
                            if (name != null && !name.isEmpty()) {
                                Activity_HomePage.this.lighteffect_TextView.setText(name);
                                break;
                            } else {
                                Activity_HomePage.this.lighteffect_TextView.setText("灯效ID:" + ledID + ", 解析错误");
                                break;
                            }
                        }
                        Activity_HomePage.this.lighteffect_TextView.setText("获取错误");
                        break;
                    }
                    Activity_HomePage.this.lighteffect_TextView.setText("用户自定义灯效");
                    break;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C00893 extends BroadcastReceiver {
        C00893() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_HomePage.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_FLUSHUI.equals(action)) {
                String type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0213L.m20i(Activity_HomePage.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_ALIGNMENT)) {
                    int alignmentID = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, 0);
                    Message msg = new Message();
                    Integer alignmentIDInteger = new Integer(alignmentID);
                    msg.what = 2;
                    msg.obj = alignmentIDInteger;
                    Activity_HomePage.this.handler.sendMessage(msg);
                }
            }
        }
    }

    class C00904 implements Runnable {
        C00904() {
        }

        public void run() {
            try {
                Thread.sleep(600);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Activity_HomePage.this.is_first_start) {
                Activity_HomePage.this.is_first_start = false;
                AppUtils.squid.getAppVersion(Activity_HomePage.this.getappversionback, AppConfig.APP_NAME);
            }
        }
    }

    class C00947 implements Runnable {

        class C00931 implements Runnable {
            C00931() {
            }

            public void run() {
                BluetoothLeService bluetoothLeService = BluetoothLeService.getInstance();
                if (bluetoothLeService != null && Activity_HomePage.this.is_page_show) {
                    if (bluetoothLeService.onliyJudgeisConnectedOk()) {
                        Activity_HomePage.this.bk_RelativeLayout.setBackground(Activity_HomePage.this.getResources().getDrawable(C0182R.drawable.device_list_bg_online));
                    } else {
                        Activity_HomePage.this.bk_RelativeLayout.setBackground(Activity_HomePage.this.getResources().getDrawable(C0182R.drawable.device_list_bg_offline));
                    }
                    Activity_HomePage.this.mac_TextView.setText(BluetoothLeService.selectMac);
                }
            }
        }

        C00947() {
        }

        public void run() {
            try {
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Activity_HomePage.this.runOnUiThread(new C00931());
        }
    }

    class C02361 implements HttpCallback {
        C02361() {
        }

        public void excute(String result) {
            if (result != null) {
                try {
                    if (result.startsWith("﻿")) {
                        result = result.substring(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            JSONObject json = new JSONObject(result);
            if (json.getString("code").toString().equals("10000")) {
                JSONObject jsonresult = json.getJSONObject("result");
                Message msg = new Message();
                msg.what = 0;
                msg.obj = jsonresult;
                Activity_HomePage.this.handler.sendMessage(msg);
                return;
            }
            msg = new Message();
            msg.what = 1;
            Activity_HomePage.this.handler.sendMessage(msg);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_homepage);
        initData();
        initUi();
        judgeAppVersion();
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_KEYBOARD);
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_FLUSHUI);
        this.is_first_open = true;
    }

    private void initData() {
    }

    private void judgeAppVersion() {
        new Thread(new C00904()).start();
    }

    private void judgeupdate(JSONObject json) {
        if (json != null) {
            float newVerCode = 0.0f;
            try {
                newVerCode = Float.parseFloat(json.getString("ver"));
                this.url = json.getString("androidpath");
                this.desc = json.getString("desc");
            } catch (Exception e) {
                e.printStackTrace();
            }
            float vercode = NormalFun.getVerCode(this);
            C0213L.m19i("tison fuck you newVerCode = " + newVerCode + ", vercode = " + vercode);
            if (newVerCode > vercode) {
                this.tip_TextView.setText("1");
                if (BluetoothLeService.progressDialog == null || !BluetoothLeService.progressDialog.isShowing()) {
                    showUpdatDialog();
                }
            }
        }
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.tip_RelativeLayout:
                showUpdatDialog();
                return;
            case C0182R.id.lighteffect_RelativeLayout:
                startActivity(new Intent(this, ActivityFragment_LightEffect.class));
                return;
            case C0182R.id.macro_RelativeLayout:
                startActivity(new Intent(this, Activity_Macro.class));
                return;
            case C0182R.id.alignment_RelativeLayout:
                startActivity(new Intent(this, Activity_Alignment.class));
                return;
            case C0182R.id.setting_RelativeLayout:
                startActivity(new Intent(this, Activity_Setting.class));
                return;
            default:
                return;
        }
    }

    private void showUpdatDialog() {
        if (this.is_page_show) {
            StringBuffer sb = new StringBuffer();
            sb.append(this.desc);
            final AlertDialog dialog = new Builder(this).create();
            dialog.setView((RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(C0182R.layout.update_prompt, null));
            dialog.show();
            dialog.getWindow().setContentView(C0182R.layout.update_prompt);
            ((TextView) dialog.findViewById(C0182R.id.title_TextView)).setText("发现新版本");
            ((TextView) dialog.findViewById(C0182R.id.content_TextView)).setText(sb.toString());
            Button cancle_Button = (Button) dialog.findViewById(C0182R.id.cancle_Button);
            ((Button) dialog.findViewById(C0182R.id.sure_Button)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    AppUpdate appUpdate = new AppUpdate(Activity_HomePage.this);
                    String update_savepath = AppConfig.APKFILE_PATH;
                    String update_savename = AppConfig.UPDATE_SAVENAME;
                    appUpdate.setNEWParam(Activity_HomePage.this.url, NormalFun.getPath(Activity_HomePage.this, update_savepath), update_savename);
                    appUpdate.startDownload();
                    dialog.cancel();
                    Activity_HomePage.this.tip_RelativeLayout.setVisibility(4);
                }
            });
            cancle_Button.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    dialog.cancel();
                }
            });
        }
    }

    private void initUi() {
        this.mac_TextView = (TextView) findViewById(C0182R.id.mac_TextView);
        this.bk_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.bk_RelativeLayout);
        this.lighteffect_TextView = (TextView) findViewById(C0182R.id.lighteffect_TextView);
        this.alignment_TextView = (TextView) findViewById(C0182R.id.alignment_TextView);
        this.macro_TextView = (TextView) findViewById(C0182R.id.macro_TextView);
        this.fw_TextView = (TextView) findViewById(C0182R.id.fw_TextView);
        this.lighteffect_TextView.setText(AppConfig.SERVER_IP);
        this.alignment_TextView.setText(AppConfig.SERVER_IP);
        this.macro_TextView.setText(AppConfig.SERVER_IP);
        this.fw_TextView.setText(AppConfig.SERVER_IP);
        this.tip_TextView = (TextView) findViewById(C0182R.id.tip_TextView);
        this.tip_CircleView = (CircleView) findViewById(C0182R.id.tip_CircleView);
        this.tip_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.tip_RelativeLayout);
        this.tip_RelativeLayout.setOnClickListener(this);
        this.tip_RelativeLayout.setVisibility(4);
        this.info_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.info_RelativeLayout);
        this.editon_TextView = (TextView) findViewById(C0182R.id.editon_TextView);
        this.lighteffect_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.lighteffect_RelativeLayout);
        this.macro_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.macro_RelativeLayout);
        this.alignment_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.alignment_RelativeLayout);
        this.setting_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.setting_RelativeLayout);
        this.lighteffect_RelativeLayout.setOnClickListener(this);
        this.macro_RelativeLayout.setOnClickListener(this);
        this.alignment_RelativeLayout.setOnClickListener(this);
        this.setting_RelativeLayout.setOnClickListener(this);
        LayoutParams lighteffect_RelativeLayout_linearParams = (LayoutParams) this.lighteffect_RelativeLayout.getLayoutParams();
        LayoutParams macro_RelativeLayout_linearParams = (LayoutParams) this.macro_RelativeLayout.getLayoutParams();
        LayoutParams alignment_RelativeLayout_linearParams = (LayoutParams) this.alignment_RelativeLayout.getLayoutParams();
        LayoutParams setting_RelativeLayout_linearParams = (LayoutParams) this.setting_RelativeLayout.getLayoutParams();
        float keyboardWidht = ((float) (DensityUtil.getScreenWidthPixels(this) / 2)) - DensityUtil.dip2px(this, 12.0f);
        lighteffect_RelativeLayout_linearParams.height = (int) keyboardWidht;
        this.lighteffect_RelativeLayout.setLayoutParams(lighteffect_RelativeLayout_linearParams);
        macro_RelativeLayout_linearParams.height = (int) keyboardWidht;
        this.macro_RelativeLayout.setLayoutParams(macro_RelativeLayout_linearParams);
        alignment_RelativeLayout_linearParams.height = (int) keyboardWidht;
        this.alignment_RelativeLayout.setLayoutParams(alignment_RelativeLayout_linearParams);
        setting_RelativeLayout_linearParams.height = (int) keyboardWidht;
        this.setting_RelativeLayout.setLayoutParams(setting_RelativeLayout_linearParams);
        if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            this.mBleSupported = false;
        }
        mBluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        this.mBtAdapter = mBluetoothManager.getAdapter();
        if (this.mBtAdapter == null) {
            this.mBleSupported = false;
        }
        startBluetoothLeService();
    }

    public void onDestroy() {
        Log.d(TAG, "Destroy");
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        this.is_page_show = true;
        this.mac_TextView.setText(BluetoothLeService.selectMac);
        new Thread(new C00947()).start();
    }

    public int getAlignmentId() {
        List<DeviceStateDB> deviceStateDBList = AppUtils.databaseManager.findAllDeviceStateInfo();
        int len = deviceStateDBList.size();
        for (int i = 0; i < len; i++) {
            DeviceStateDB deviceStateDB = (DeviceStateDB) deviceStateDBList.get(i);
            if (deviceStateDB.deviceId.equals(BluetoothLeService.deviceId)) {
                return deviceStateDB.alignmentId;
            }
        }
        return -1;
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        this.is_page_show = false;
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    private void startBluetoothLeService() {
        startService(new Intent(this, BluetoothLeService.class));
    }
}
