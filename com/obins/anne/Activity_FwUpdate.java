package com.obins.anne;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.DeviceDB;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.C0188L;
import com.obins.anne.utils.Conversion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.network.HttpCallback;

public class Activity_FwUpdate extends BaseActivity implements OnClickListener {
    private static final int FILE_BUFFER_SIZE = 262144;
    private static final String FW_FILE_A = "SimpleBLEPeripheral_imageA_V5.bin";
    private static final String FW_FILE_B = "SimpleBLEPeripheral_imageB_V3.bin";
    private static final String FW_FILE_MAINMUC = "mainmcu.bin";
    private static final int GATT_WRITE_TIMEOUT = 100;
    private static final int HAL_FLASH_WORD_SIZE = 4;
    private static final int OAD_BLOCK_SIZE = 16;
    private static final int OAD_BUFFER_SIZE = 20;
    private static final short OAD_CONN_INTERVAL = (short) 10;
    private static final int OAD_HEAD_SIZE = 2;
    private static final int OAD_IMG_HDR_SIZE = 8;
    private static final short OAD_SUPERVISION_TIMEOUT = (short) 100;
    private static final int PKT_INTERVAL = 50;
    private static String TAG = "Activity_FwUpdate";
    private static int getFWVersionFlag = 0;
    private final int CHECK_FW_TIMEOUT = 6;
    private final int FW_DOWNLOAD_ERRO = 5;
    private final int FW_DOWNLOAD_SUCCESS = 4;
    private final int GET_SERVE_FW_VERSION_ERRO = 3;
    private final int HAVE_NEW_VERSION = 2;
    private final int IS_NEW_VERSION = 1;
    private final int UPDATE_UI = 7;
    private RelativeLayout back_RelativeLayout;
    HttpCallback checkFwVersionback = new C02342();
    private String desk_fw_url;
    private int desk_version;
    private int dev_version;
    HttpCallback downLoadFWFileback = new C02354();
    private long filelen;
    private Handler handler = new C00843();
    private BluetoothGattCharacteristic mCharBlock = null;
    private BluetoothGattCharacteristic mCharIdentify = null;
    private List<BluetoothGattCharacteristic> mCharListOad;
    private final byte[] mFileBuffer = new byte[262144];
    private ImgHdr mFileImgHdr = new ImgHdr();
    private final BroadcastReceiver mGattUpdateReceiver = new C00831();
    private IntentFilter mIntentFilter;
    private BluetoothLeService mLeService;
    private final byte[] mOadBuffer = new byte[20];
    private BluetoothGattService mOadService;
    private ProgInfo mProgInfo = new ProgInfo();
    private boolean mProgramming = false;
    private ImgHdr mTargImgHdr = new ImgHdr();
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private TextView newversiontip;
    private String newversiontipString;
    private ProgressBar progressBar;
    private TextView progressBartip;
    private ProgressDialog progressDialog;
    private String savename;
    private String savepath;
    private Button start_btn;
    private TextView tip;
    private int version_state = 0;

    class C00831 extends BroadcastReceiver {
        C00831() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_FwUpdate.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_FW_UPDATE.equals(action)) {
                byte[] value = intent.getByteArrayExtra(BluetoothLeService.EXTRA_DATA);
                String uuidStr = intent.getStringExtra(BluetoothLeService.EXTRA_UUID);
                if (value.length > 3) {
                    char c;
                    if (Activity_FwUpdate.this.progressDialog != null && Activity_FwUpdate.this.progressDialog.isShowing()) {
                        Activity_FwUpdate.this.progressDialog.cancel();
                    }
                    Activity_FwUpdate.getFWVersionFlag = 1;
                    Activity_FwUpdate.this.mTargImgHdr.ver = Conversion.buildUint16(value[3], value[2]);
                    ImgHdr access$3 = Activity_FwUpdate.this.mTargImgHdr;
                    if ((Activity_FwUpdate.this.mTargImgHdr.ver & 1) == 1) {
                        c = 'B';
                    } else {
                        c = 'A';
                    }
                    access$3.imgType = Character.valueOf(c);
                    Activity_FwUpdate.this.mTargImgHdr.ver_true = (short) (Activity_FwUpdate.this.mTargImgHdr.ver >> 1);
                    Activity_FwUpdate.this.mTargImgHdr.len = Conversion.buildUint16(value[5], value[4]);
                    Toast.makeText(Activity_FwUpdate.this, new StringBuilder(String.valueOf(Activity_FwUpdate.this.getResources().getString(C0182R.string.check_kb_fw_ver))).append(Activity_FwUpdate.this.mTargImgHdr.ver_true).toString(), 0).show();
                    Activity_FwUpdate.this.displayImageInfo(Activity_FwUpdate.this.mTargImgHdr);
                    Message message = new Message();
                    message.what = 1;
                    Activity_FwUpdate.this.handler.sendMessage(message);
                    AppUtils.squid.checkFwVersion(AppConfig.DEVICE_TYPE, Activity_FwUpdate.this.checkFwVersionback);
                }
            } else if (BluetoothLeService.ACTION_DATA_WRITE.equals(action)) {
                int status = intent.getIntExtra(BluetoothLeService.EXTRA_STATUS, 0);
                if (status != 0) {
                    Log.e(Activity_FwUpdate.TAG, "Write failed: " + status);
                    Toast.makeText(context, "GATT error: status=" + status, 0).show();
                }
            }
        }
    }

    class C00843 extends Handler {
        C00843() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (Activity_FwUpdate.this.progressDialog != null && Activity_FwUpdate.this.progressDialog.isShowing()) {
                        Activity_FwUpdate.this.progressDialog.cancel();
                    }
                    Activity_FwUpdate.this.start_btn.setText(Activity_FwUpdate.this.getResources().getString(C0182R.string.sure));
                    Activity_FwUpdate.this.version_state = 2;
                    Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.is_new_fw), 0).show();
                    return;
                case 2:
                    if (Activity_FwUpdate.this.progressDialog != null && Activity_FwUpdate.this.progressDialog.isShowing()) {
                        Activity_FwUpdate.this.progressDialog.cancel();
                    }
                    Activity_FwUpdate.this.version_state = 1;
                    Activity_FwUpdate.this.tip.setText(new StringBuilder(String.valueOf(Activity_FwUpdate.this.getResources().getString(C0182R.string.now_ble_fw_ver))).append(Activity_FwUpdate.this.mTargImgHdr.ver_true).append(Activity_FwUpdate.this.getResources().getString(C0182R.string.find_new_ver)).append(Activity_FwUpdate.this.desk_version).toString());
                    Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.find_new_ver_1), 0).show();
                    Activity_FwUpdate.this.start_btn.setText(Activity_FwUpdate.this.getResources().getString(C0182R.string.start_update));
                    Activity_FwUpdate.this.newversiontip.setText(Activity_FwUpdate.this.newversiontipString);
                    return;
                case 3:
                    if (Activity_FwUpdate.this.progressDialog != null && Activity_FwUpdate.this.progressDialog.isShowing()) {
                        Activity_FwUpdate.this.progressDialog.cancel();
                    }
                    Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.get_fw_erro), 0).show();
                    return;
                case 4:
                    Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.get_fw_success), 0).show();
                    Activity_FwUpdate.this.startProgramming(Activity_FwUpdate.this.savepath, Activity_FwUpdate.this.savename);
                    return;
                case 5:
                    Activity_FwUpdate.this.start_btn.setEnabled(true);
                    Activity_FwUpdate.this.start_btn.setBackground(Activity_FwUpdate.this.getResources().getDrawable(C0182R.drawable.gradientbutton_cyan));
                    Activity_FwUpdate.this.start_btn.setText(Activity_FwUpdate.this.getResources().getString(C0182R.string.start_update));
                    Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.get_fw_erro), 0).show();
                    return;
                case 6:
                    if (Activity_FwUpdate.getFWVersionFlag != 1 && Activity_FwUpdate.this.progressDialog != null && Activity_FwUpdate.this.progressDialog.isShowing()) {
                        Activity_FwUpdate.this.progressDialog.cancel();
                        Toast.makeText(Activity_FwUpdate.this, Activity_FwUpdate.this.getResources().getString(C0182R.string.get_fw_erro), 0).show();
                        return;
                    }
                    return;
                case 7:
                    Activity_FwUpdate.this.progressBar.setVisibility(0);
                    Activity_FwUpdate.this.progressBar.setProgress((Activity_FwUpdate.this.mProgInfo.iBlocks * Activity_FwUpdate.GATT_WRITE_TIMEOUT) / Activity_FwUpdate.this.mProgInfo.nBlocks);
                    return;
                default:
                    return;
            }
        }
    }

    class C00855 implements Runnable {
        C00855() {
        }

        public void run() {
            try {
                Thread.sleep(600);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 6;
            Activity_FwUpdate.this.handler.sendMessage(message);
        }
    }

    class C00866 implements Runnable {
        C00866() {
        }

        public void run() {
            Activity_FwUpdate.this.displayStats();
            Activity_FwUpdate.this.stopProgramming();
        }
    }

    private class ImgHdr {
        Character imgType;
        short len;
        byte[] uid;
        short ver;
        short ver_true;

        private ImgHdr() {
            this.uid = new byte[4];
        }
    }

    private class ProgInfo {
        short iBlocks;
        int iBytes;
        int iTimeElapsed;
        int mTick;
        short nBlocks;

        private ProgInfo() {
            this.iBytes = 0;
            this.iBlocks = (short) 0;
            this.nBlocks = (short) 0;
            this.iTimeElapsed = 0;
            this.mTick = 0;
        }

        void reset() {
            this.iBytes = 0;
            this.iBlocks = (short) 0;
            this.iTimeElapsed = 0;
            this.mTick = 0;
            this.nBlocks = (short) (Activity_FwUpdate.this.mFileImgHdr.len / 4);
        }
    }

    private class ProgTimerTask extends TimerTask {

        class C00871 implements Runnable {
            C00871() {
            }

            public void run() {
                Activity_FwUpdate.this.displayStats();
            }
        }

        private ProgTimerTask() {
        }

        public void run() {
            ProgInfo access$20 = Activity_FwUpdate.this.mProgInfo;
            access$20.mTick++;
            if (Activity_FwUpdate.this.mProgramming) {
                Activity_FwUpdate.this.onBlockTimer();
                if (Activity_FwUpdate.this.mProgInfo.mTick % Activity_FwUpdate.PKT_INTERVAL == 0) {
                    Activity_FwUpdate.this.runOnUiThread(new C00871());
                }
            }
        }
    }

    class C02342 implements HttpCallback {
        C02342() {
        }

        public void excute(String result) {
            C0188L.m7i("checkFwVersionback:" + result);
            if (result != null) {
                try {
                    if (result.startsWith("﻿")) {
                        result = result.substring(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            JSONObject json = new JSONObject(result);
            C0188L.m8i("xx", "json.getString(code)=" + json.getString("code"));
            Message message;
            if (json.getString("code").toString().equals("10000")) {
                JSONArray jArray = json.getJSONArray("result");
                HashMap<String, String> noteHashMap = new HashMap();
                Activity_FwUpdate.this.newversiontipString = "优化蓝牙";
                Activity_FwUpdate.this.desk_version = 2;
                Activity_FwUpdate.this.desk_fw_url = "http://192.168.9.8/mg/ble_bin_file/SimpleBLEPeripheral_imageB_v2.bin";
                if (!true) {
                    message = new Message();
                    message.what = 1;
                    Activity_FwUpdate.this.handler.sendMessage(message);
                } else if (Activity_FwUpdate.this.desk_version > Activity_FwUpdate.this.mTargImgHdr.ver_true) {
                    message = new Message();
                    message.what = 2;
                    Activity_FwUpdate.this.handler.sendMessage(message);
                } else {
                    message = new Message();
                    message.what = 1;
                    Activity_FwUpdate.this.handler.sendMessage(message);
                }
            } else if (json.getString("code").toString().equals(AppConfig.OTA_ERROR)) {
                message = new Message();
                message.what = 1;
                Activity_FwUpdate.this.handler.sendMessage(message);
            } else {
                message = new Message();
                message.what = 3;
                Activity_FwUpdate.this.handler.sendMessage(message);
            }
        }
    }

    class C02354 implements HttpCallback {
        C02354() {
        }

        public void excute(String result) {
            C0188L.m7i("downLoadFWFileback:" + result);
            if (result != null) {
                try {
                    if (result.startsWith("﻿")) {
                        result = result.substring(1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            JSONObject json = new JSONObject(result);
            C0188L.m8i("xx", "json.getString(code)=" + json.getString("code"));
            if (json.getString("code").toString().equals("10000")) {
                Activity_FwUpdate.this.filelen = new File(Activity_FwUpdate.this.savepath, Activity_FwUpdate.this.savename).length();
                C0188L.m7i(" filelen === " + Activity_FwUpdate.this.filelen);
                Message message = new Message();
                message.what = 4;
                Activity_FwUpdate.this.handler.sendMessage(message);
                return;
            }
            message = new Message();
            message.what = 5;
            Activity_FwUpdate.this.handler.sendMessage(message);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_fwupdate);
        initdata();
        initui();
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_FW_UPDATE);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    private void displayImageInfo(ImgHdr mTargImgHdr) {
        this.version_state = 1;
        int imgVer = mTargImgHdr.ver >> 1;
        int imgSize = mTargImgHdr.len * 4;
        String s = String.format("Type: %c Ver.: %d Size: %d", new Object[]{mTargImgHdr.imgType, Integer.valueOf(imgVer), Integer.valueOf(imgSize)});
        this.tip.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.now_ble_fw_ver))).append(mTargImgHdr.ver_true).toString());
    }

    private void initdata() {
        this.mLeService = BluetoothLeService.getInstance();
        if (BLEHandle.judgeBLEconnect(this.mLeService)) {
            this.mOadService = this.mLeService.mOadService;
            this.mCharListOad = this.mOadService.getCharacteristics();
            this.mCharListOad = this.mOadService.getCharacteristics();
            this.mCharIdentify = (BluetoothGattCharacteristic) this.mCharListOad.get(0);
            this.mCharBlock = (BluetoothGattCharacteristic) this.mCharListOad.get(1);
        }
    }

    private void initui() {
        this.back_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.back_RelativeLayout);
        this.back_RelativeLayout.setOnClickListener(this);
        this.newversiontip = (TextView) findViewById(C0182R.id.newversiontip);
        this.newversiontip.setText(AppConfig.SERVER_IP);
        this.progressBartip = (TextView) findViewById(C0182R.id.progressBartip);
        this.progressBartip.setText(AppConfig.SERVER_IP);
        this.tip = (TextView) findViewById(C0182R.id.tip);
        this.tip.setText(getResources().getString(C0182R.string.unknow));
        List<DeviceDB> listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
        for (int i = 0; i < listDeviceDB.size(); i++) {
            DeviceDB deviceDB = (DeviceDB) listDeviceDB.get(i);
            if (!(deviceDB.deviceId == null || deviceDB.deviceId.isEmpty() || BluetoothLeService.deviceId == null || BluetoothLeService.deviceId.isEmpty() || !deviceDB.deviceId.equals(BluetoothLeService.deviceId) || deviceDB.bleVersion == 0)) {
                this.tip.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.now_ble_fw_ver))).append(deviceDB.bleVersion).toString());
            }
        }
        this.start_btn = (Button) findViewById(C0182R.id.start_btn);
        this.progressBar = (ProgressBar) findViewById(C0182R.id.progressBar);
        this.progressBar.setVisibility(4);
        this.start_btn.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0182R.id.start_btn:
                if (this.version_state == 0) {
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, getResources().getString(C0182R.string.checking_fw_version));
                    getFWVersionFlag = 0;
                    new Thread(new C00855()).start();
                    checkfw();
                    return;
                } else if (this.version_state == 1) {
                    this.savepath = getFilesDir().getPath();
                    this.savename = "v" + this.desk_version + ".bin";
                    C0188L.m7i(this.savepath + "/" + this.savename);
                    AppUtils.squid.downLoadFWFileWithUrl(this.desk_fw_url, this.savepath, this.savename, this.downLoadFWFileback);
                    this.start_btn.setEnabled(false);
                    this.start_btn.setBackground(getResources().getDrawable(C0182R.drawable.gradientbutton_gray));
                    this.start_btn.setText("固件升级中");
                    return;
                } else if (this.version_state == 2) {
                    finish();
                    return;
                } else {
                    return;
                }
            case C0182R.id.back_RelativeLayout:
                finish();
                return;
            default:
                return;
        }
    }

    private void startProgramming(String filepath, String filename) {
        C0188L.m8i(TAG, "startProgramming");
        if (this.mTargImgHdr.imgType.equals(Character.valueOf('A'))) {
            C0188L.m8i(TAG, "mTargImgHdr.imgType.equals('A')");
            loadFile(filepath, filename, false);
        } else {
            C0188L.m8i(TAG, "mTargImgHdr.imgType.equals('B')");
            loadFile(filepath, filename, false);
        }
        this.mProgramming = true;
        this.mProgInfo.reset();
        byte[] buf = new byte[12];
        buf[0] = Conversion.loUint16(this.mFileImgHdr.ver);
        buf[1] = Conversion.hiUint16(this.mFileImgHdr.ver);
        buf[2] = Conversion.loUint16(this.mFileImgHdr.len);
        buf[3] = Conversion.hiUint16(this.mFileImgHdr.len);
        System.arraycopy(this.mFileImgHdr.uid, 0, buf, 4, 4);
        this.mCharIdentify.setValue(buf);
        this.mLeService.writeCharacteristic(this.mCharIdentify);
        this.mTimer = null;
        this.mTimer = new Timer();
        this.mTimerTask = new ProgTimerTask();
        this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0, 50);
    }

    private boolean loadFile(String filepath, String name, boolean isAsset) {
        boolean ready = true;
        try {
            String str;
            File f = new File(filepath, name);
            if (f.exists()) {
                C0188L.m7i("f.exists()");
            } else {
                C0188L.m7i("!!!f.exists()");
            }
            C0188L.m7i("f.length(): " + f.length());
            this.filelen = (long) ((short) ((int) (f.length() / 4)));
            InputStream stream = new FileInputStream(f);
            stream.read(this.mFileBuffer, 0, this.mFileBuffer.length);
            stream.close();
            this.mFileImgHdr.ver = Conversion.buildUint16(this.mFileBuffer[5], this.mFileBuffer[4]);
            this.mFileImgHdr.len = Conversion.buildUint16(this.mFileBuffer[7], this.mFileBuffer[6]);
            this.mFileImgHdr.imgType = Character.valueOf((this.mFileImgHdr.ver & 1) == 1 ? 'B' : 'A');
            System.arraycopy(this.mFileBuffer, 8, this.mFileImgHdr.uid, 0, 4);
            if (this.mFileImgHdr.imgType == this.mTargImgHdr.imgType) {
                ready = false;
            }
            C0188L.m7i("Image " + this.mFileImgHdr.imgType + " selected.\n");
            if (ready) {
                str = "Ready to program device!\n";
            } else {
                str = "Incompatible image, select alternative!\n";
            }
            C0188L.m7i(str);
        } catch (IOException e) {
            C0188L.m7i("File open failed: " + filepath + "\n");
        }
        return false;
    }

    private void onBlockTimer() {
        ProgInfo progInfo;
        C0188L.m8i(TAG, "mProgInfo.iBlocks = " + this.mProgInfo.iBlocks);
        C0188L.m8i(TAG, "mProgInfo.nBlocks = " + this.mProgInfo.nBlocks);
        if (this.mProgInfo.iBlocks < this.mProgInfo.nBlocks) {
            this.mProgramming = true;
            this.mOadBuffer[0] = (byte) 11;
            this.mOadBuffer[1] = (byte) 1;
            this.mOadBuffer[2] = Conversion.loUint16(this.mProgInfo.iBlocks);
            this.mOadBuffer[3] = Conversion.hiUint16(this.mProgInfo.iBlocks);
            System.arraycopy(this.mFileBuffer, this.mProgInfo.iBytes, this.mOadBuffer, 4, 16);
            this.mCharBlock.setValue(this.mOadBuffer);
            if (this.mLeService.writeCharacteristic(this.mCharBlock)) {
                progInfo = this.mProgInfo;
                progInfo.iBlocks = (short) (progInfo.iBlocks + 1);
                progInfo = this.mProgInfo;
                progInfo.iBytes += 16;
                Message message = new Message();
                message.what = 7;
                this.handler.sendMessage(message);
            } else if (BluetoothLeService.getBtGatt() == null) {
                this.mProgramming = false;
            }
        } else {
            this.mProgramming = false;
        }
        progInfo = this.mProgInfo;
        progInfo.iTimeElapsed += PKT_INTERVAL;
        if (!this.mProgramming) {
            runOnUiThread(new C00866());
        }
    }

    private void displayStats() {
        float a = (float) ((this.mProgInfo.iBlocks * GATT_WRITE_TIMEOUT) / this.mProgInfo.nBlocks);
        this.progressBar.setProgress((int) a);
        this.progressBartip.setText(((int) a) + " %");
    }

    private void stopProgramming() {
        this.mTimer.cancel();
        this.mTimer.purge();
        this.mTimerTask.cancel();
        this.mTimerTask = null;
        this.mProgramming = false;
        if (this.mProgInfo.iBlocks == this.mProgInfo.nBlocks) {
            this.progressBar.setProgress(GATT_WRITE_TIMEOUT);
            this.progressBartip.setText("100 %");
            this.start_btn.setEnabled(true);
            this.start_btn.setBackground(getResources().getDrawable(C0182R.drawable.gradientbutton_cyan));
            Toast.makeText(this, "固件升级成功", 0).show();
            this.start_btn.setText(getResources().getString(C0182R.string.sure));
            this.version_state = 2;
            this.tip.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.now_ble_fw_ver))).append(this.desk_version).toString());
            this.newversiontip.setText(AppConfig.SERVER_IP);
            return;
        }
        this.start_btn.setEnabled(true);
        this.start_btn.setBackground(getResources().getDrawable(C0182R.drawable.gradientbutton_cyan));
        this.start_btn.setText("开始升级");
        Toast.makeText(this, "固件升级失败", 0).show();
    }

    private void checkfw() {
        BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
        if (BLEHandle.judgeBLEconnect(mBluetoothLeService)) {
            BLEHandle.getTargetImageInfo(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(0));
            return;
        }
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.cancel();
        }
        Toast.makeText(this, getResources().getString(C0182R.string.find_no_keyboard), 0).show();
    }
}
