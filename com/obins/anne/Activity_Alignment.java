package com.obins.anne;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.DeviceStateDB;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.CRC16;
import com.obins.anne.utils.KeyObject;
import com.obins.anne.utils.KeyObjectUtil;
import com.obins.anne.utils.KeyboardAlignment;
import com.obins.anne.utils.KeyboardAlignmentUtil;
import com.obins.anne.utils.NormalFun;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Activity_Alignment extends BaseActivity implements OnClickListener {
    private static final int SET_ALIGNMENT_BACK = 1;
    private static final int SET_ALIGNMENT_TIMEOUT = 0;
    private static final String TAG = "Activity_Alignment";
    private static final int UPDATA_UI_ALIGNMENT = 2;
    private MyAdapter adapter = null;
    private RelativeLayout add_RelativeLayout;
    private RelativeLayout backBtn;
    private List<KeyboardAlignment> listKeyboardAlignment = null;
    private ListView listView;
    private final BroadcastReceiver mGattUpdateReceiver = new C00641();
    public Handler mHandler = new C00652();
    private IntentFilter mIntentFilter;
    private ProgressDialog progressDialog;
    private int selectedItem = -1;
    private int setAlignmentFlag = -1;
    private TextView tip_TextView;

    class C00641 extends BroadcastReceiver {
        C00641() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_Alignment.TAG, "action: " + action);
            String type;
            Message msg;
            if (BluetoothLeService.ACTION_DATA_KEYBOARD.equals(action)) {
                type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0213L.m20i(Activity_Alignment.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_ALIGNMENT_SET)) {
                    int result = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, -1);
                    msg = new Message();
                    Integer resultInteger = new Integer(result);
                    msg.what = 1;
                    msg.obj = resultInteger;
                    Activity_Alignment.this.mHandler.sendMessage(msg);
                }
            } else if (BluetoothLeService.ACTION_DATA_FLUSHUI.equals(action)) {
                type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0213L.m20i(Activity_Alignment.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_ALIGNMENT)) {
                    int alignmentID = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, 0);
                    msg = new Message();
                    Integer alignmentIDInteger = new Integer(alignmentID);
                    msg.what = 2;
                    msg.obj = alignmentIDInteger;
                    Activity_Alignment.this.mHandler.sendMessage(msg);
                }
            }
        }
    }

    class C00652 extends Handler {
        C00652() {
        }

        public void handleMessage(Message msg) {
            String name;
            switch (msg.what) {
                case 0:
                    if (Activity_Alignment.this.progressDialog.isShowing()) {
                        Activity_Alignment.this.progressDialog.cancel();
                    }
                    if (Activity_Alignment.this.setAlignmentFlag == 1) {
                        Toast.makeText(Activity_Alignment.this, Activity_Alignment.this.getResources().getString(C0182R.string.control_success), 0).show();
                        if (Activity_Alignment.this.selectedItem < 3) {
                            Activity_Alignment.this.tip_TextView.setText(new StringBuilder(String.valueOf(Activity_Alignment.this.getResources().getString(C0182R.string.layout_default))).append(Activity_Alignment.this.selectedItem + 1).toString());
                            Activity_Alignment.this.saveAlignmentId(Activity_Alignment.this.selectedItem + 1);
                        } else {
                            name = ((KeyboardAlignment) Activity_Alignment.this.listKeyboardAlignment.get(Activity_Alignment.this.selectedItem)).name;
                            if (name == null || name.isEmpty()) {
                                Activity_Alignment.this.tip_TextView.setText(Activity_Alignment.this.getResources().getString(C0182R.string.layout_custom));
                            } else {
                                Activity_Alignment.this.tip_TextView.setText(name);
                            }
                            Activity_Alignment.this.saveAlignmentId(((KeyboardAlignment) Activity_Alignment.this.listKeyboardAlignment.get(Activity_Alignment.this.selectedItem)).alignmentId);
                            Activity_Alignment.this.saveCustomAlignmentId(((KeyboardAlignment) Activity_Alignment.this.listKeyboardAlignment.get(Activity_Alignment.this.selectedItem)).alignmentId);
                            AppUtils.nowKBCustomAlignmentID = ((KeyboardAlignment) Activity_Alignment.this.listKeyboardAlignment.get(Activity_Alignment.this.selectedItem)).alignmentId;
                        }
                        Activity_Alignment.this.adapter.notifyDataSetChanged();
                        return;
                    }
                    Toast.makeText(Activity_Alignment.this, Activity_Alignment.this.getResources().getString(C0182R.string.control_faile), 0).show();
                    return;
                case 1:
                    int result = ((Integer) msg.obj).intValue();
                    Activity_Alignment.this.setAlignmentFlag = 1;
                    return;
                case 2:
                    int alignmentID = ((Integer) msg.obj).intValue();
                    C0213L.m19i(" alignmentID =" + alignmentID);
                    AppUtils.nowUseAlignmentID = alignmentID;
                    Activity_Alignment.this.selectedItem = -1;
                    if (alignmentID > 3) {
                        name = AppConfig.SERVER_IP;
                        if (!(Activity_Alignment.this.getCustomAlignmentId() == 0 || Activity_Alignment.this.getCustomAlignmentId() == -1)) {
                            name = KeyboardAlignment.getAlignmentNameWithId(AppUtils.databaseManager, AppUtils.nowKBCustomAlignmentID);
                            int customId = Activity_Alignment.this.getCustomAlignmentId();
                            Activity_Alignment.this.saveAlignmentId(customId);
                            int i = 3;
                            while (i < Activity_Alignment.this.listKeyboardAlignment.size()) {
                                if (((KeyboardAlignment) Activity_Alignment.this.listKeyboardAlignment.get(i)).alignmentId == customId) {
                                    Activity_Alignment.this.selectedItem = i;
                                } else {
                                    i++;
                                }
                            }
                        }
                        if (name == null || name.isEmpty() || name.equals(AppConfig.SERVER_IP)) {
                            Activity_Alignment.this.tip_TextView.setText(Activity_Alignment.this.getResources().getString(C0182R.string.layout_custom));
                        } else {
                            Activity_Alignment.this.tip_TextView.setText(name);
                        }
                    } else {
                        if (alignmentID >= 1) {
                            Activity_Alignment.this.selectedItem = alignmentID - 1;
                        } else {
                            Activity_Alignment.this.selectedItem = 0;
                        }
                        Activity_Alignment.this.saveAlignmentId(alignmentID);
                        Activity_Alignment.this.tip_TextView.setText(new StringBuilder(String.valueOf(Activity_Alignment.this.getResources().getString(C0182R.string.layout_default))).append(alignmentID).toString());
                    }
                    Activity_Alignment.this.adapter.notifyDataSetChanged();
                    return;
                default:
                    return;
            }
        }
    }

    class C00663 implements OnClickListener {
        C00663() {
        }

        public void onClick(View arg0) {
            Activity_Alignment.this.finish();
        }
    }

    public class MyAdapter extends BaseAdapter {
        private List<KeyboardAlignment> data;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<KeyboardAlignment> data) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return this.data.size();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(C0182R.layout.activity_alignment_listitem, null);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.content_TextView = (TextView) convertView.findViewById(C0182R.id.content_TextView);
                holder.selected_ImageView = (ImageView) convertView.findViewById(C0182R.id.selected_ImageView);
                holder.selected_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.selected_RelativeLayout);
                holder.layout_kb_ImageView = (ImageView) convertView.findViewById(C0182R.id.layout_kb_ImageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            KeyboardAlignment keyboardAlignment = (KeyboardAlignment) this.data.get(position);
            holder.name_TextView.setText(keyboardAlignment.name);
            holder.content_TextView.setText(keyboardAlignment.content);
            if (Activity_Alignment.this.selectedItem == position) {
                holder.name_TextView.setTextColor(Activity_Alignment.this.getResources().getColor(C0182R.color.main_font_orange));
                holder.layout_kb_ImageView.setBackground(Activity_Alignment.this.getResources().getDrawable(C0182R.drawable.layout_select_on));
                holder.selected_ImageView.setBackground(Activity_Alignment.this.getResources().getDrawable(C0182R.drawable.layout_check_on));
            } else {
                holder.name_TextView.setTextColor(Activity_Alignment.this.getResources().getColor(C0182R.color.main_font_gray));
                holder.layout_kb_ImageView.setBackground(Activity_Alignment.this.getResources().getDrawable(C0182R.drawable.layout_select_off));
                holder.selected_ImageView.setBackground(Activity_Alignment.this.getResources().getDrawable(C0182R.drawable.micro_check_off));
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    try {
                        Intent mIntent = new Intent(Activity_Alignment.this, Activity_AlignmentSetting.class);
                        KeyboardAlignment keyboardAlignment = new KeyboardAlignment();
                        keyboardAlignment.content = ((KeyboardAlignment) MyAdapter.this.data.get(position)).content;
                        keyboardAlignment.name = ((KeyboardAlignment) MyAdapter.this.data.get(position)).name;
                        keyboardAlignment.standardKey_List = new ArrayList();
                        keyboardAlignment.standardKey_List = NormalFun.deepCopy(((KeyboardAlignment) MyAdapter.this.data.get(position)).standardKey_List);
                        keyboardAlignment.funKey_List = new ArrayList();
                        keyboardAlignment.funKey_List = NormalFun.deepCopy(((KeyboardAlignment) MyAdapter.this.data.get(position)).funKey_List);
                        keyboardAlignment.type = ((KeyboardAlignment) MyAdapter.this.data.get(position)).type;
                        keyboardAlignment._id = ((KeyboardAlignment) MyAdapter.this.data.get(position))._id;
                        AppUtils.keyboardAlignment = keyboardAlignment;
                        Activity_Alignment.this.startActivity(mIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            holder.selected_RelativeLayout.setOnClickListener(new OnClickListener() {

                class C00681 implements Runnable {
                    C00681() {
                    }

                    public void run() {
                        try {
                            Thread.sleep(2500);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Message message = new Message();
                        message.what = 0;
                        Activity_Alignment.this.mHandler.sendMessage(message);
                    }
                }

                public void onClick(View arg0) {
                    if ((Activity_Alignment.this.progressDialog == null || !Activity_Alignment.this.progressDialog.isShowing()) && BluetoothLeService.getInstance().isConnectedOk()) {
                        Activity_Alignment.this.selectedItem = position;
                        Activity_Alignment.this.setAlignmentFlag = -1;
                        Activity_Alignment.this.progressDialog = ProgressDialog.show(Activity_Alignment.this, AppConfig.SERVER_IP, Activity_Alignment.this.getResources().getString(C0182R.string.setting));
                        new Thread(new C00681()).start();
                        KeyboardAlignment keyboardAlignment = (KeyboardAlignment) MyAdapter.this.data.get(position);
                        if (keyboardAlignment.type == KeyboardAlignment.NORMAL_TYPE) {
                            BLEHandle.setAlignmentWithId(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), position + 1);
                            return;
                        }
                        int i;
                        byte[] alignmentData = new byte[144];
                        byte[] alignmentStandData = new byte[61];
                        byte[] alignmentFunData = new byte[61];
                        byte[] alignmentStandCovData = new byte[70];
                        byte[] alignmentFunCovData = new byte[70];
                        for (i = 0; i < 61; i++) {
                            alignmentStandData[i] = (byte) ((KeyObject) keyboardAlignment.standardKey_List.get(i)).keyValue;
                        }
                        for (i = 0; i < 61; i++) {
                            alignmentFunData[i] = (byte) ((KeyObject) keyboardAlignment.funKey_List.get(i)).keyValue;
                        }
                        KeyboardAlignment.conversionAlignment61To70(alignmentStandData, alignmentStandCovData);
                        KeyboardAlignment.conversionAlignment61To70(alignmentFunData, alignmentFunCovData);
                        for (i = 4; i < 144; i++) {
                            if (i < 74) {
                                alignmentData[i] = alignmentStandCovData[i - 4];
                            } else {
                                alignmentData[i] = alignmentFunCovData[(i - 70) - 4];
                            }
                        }
                        int crcData = CRC16.calcCrc16(alignmentData);
                        if (crcData < 10) {
                            crcData += 10;
                        }
                        byte[] crcDataByte = NormalFun.intToByteArray(crcData);
                        C0213L.m19i("crcData " + crcData);
                        C0213L.m19i("crcDataByte " + crcDataByte[0]);
                        C0213L.m19i("crcDataByte " + crcDataByte[1]);
                        C0213L.m19i("crcDataByte " + crcDataByte[2]);
                        C0213L.m19i("crcDataByte " + crcDataByte[3]);
                        for (i = 0; i < 4; i++) {
                            alignmentData[i] = crcDataByte[i];
                        }
                        BLEHandle.setAlignment(BluetoothLeService.getInstance(), alignmentData);
                    }
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView content_TextView;
        public ImageView layout_kb_ImageView;
        public TextView name_TextView;
        public ImageView selected_ImageView;
        public RelativeLayout selected_RelativeLayout;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_alignment);
        initUI();
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00663());
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_KEYBOARD);
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_FLUSHUI);
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        flushUI();
        BluetoothLeService bluetoothLeService = BluetoothLeService.getInstance();
        if (bluetoothLeService != null && bluetoothLeService.isConnectedOk()) {
            BLEHandle.getAlignmentId(bluetoothLeService, (BluetoothGattCharacteristic) bluetoothLeService.mOadService.getCharacteristics().get(1));
        }
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.add_RelativeLayout:
                try {
                    int i;
                    Intent mIntent = new Intent(this, Activity_AlignmentSetting.class);
                    KeyboardAlignment keyboardAlignment = new KeyboardAlignment();
                    keyboardAlignment.name = AppConfig.SERVER_IP;
                    keyboardAlignment.type = KeyboardAlignment.CUSTOM_TYPE;
                    keyboardAlignment.content = AppConfig.SERVER_IP;
                    List<KeyObject> standardKey_List = new ArrayList();
                    for (i = 0; i < 61; i++) {
                        standardKey_List.add(KeyObjectUtil.keyObjectReserved);
                    }
                    keyboardAlignment.standardKey_List = new ArrayList();
                    keyboardAlignment.standardKey_List = NormalFun.deepCopy(standardKey_List);
                    List<KeyObject> funKey_List = new ArrayList();
                    for (i = 0; i < 61; i++) {
                        funKey_List.add(KeyObjectUtil.keyObjectReserved);
                    }
                    keyboardAlignment.funKey_List = new ArrayList();
                    keyboardAlignment.funKey_List = NormalFun.deepCopy(funKey_List);
                    AppUtils.keyboardAlignment = keyboardAlignment;
                    startActivity(mIntent);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    private void flushUI() {
        int i;
        this.listKeyboardAlignment.clear();
        this.listKeyboardAlignment.add(KeyboardAlignmentUtil.keyboardAlignment1);
        this.listKeyboardAlignment.add(KeyboardAlignmentUtil.keyboardAlignment2);
        this.listKeyboardAlignment.add(KeyboardAlignmentUtil.keyboardAlignment3);
        List<KeyboardAlignment> listdata = AppUtils.databaseManager.findAllKeyboardAlignment();
        for (i = listdata.size() - 1; i >= 0; i--) {
            KeyboardAlignment keyboardAlignment = (KeyboardAlignment) listdata.get(i);
            keyboardAlignment.standardKey_List = new ArrayList();
            keyboardAlignment.funKey_List = new ArrayList();
            List<KeyObject> keyObjectList = AppUtils.databaseManager.findAllAlignmentValue(keyboardAlignment._id);
            int keyObjectListlen = keyObjectList.size();
            C0213L.m19i("keyObjectListlen = " + keyObjectListlen);
            for (int j = 0; j < keyObjectListlen; j++) {
                KeyObject keyObject = (KeyObject) keyObjectList.get(j);
                if (keyObject.type == KeyObject.BASEKEY_AREA) {
                    keyboardAlignment.standardKey_List.add(keyObject);
                } else {
                    keyboardAlignment.funKey_List.add(keyObject);
                }
            }
            this.listKeyboardAlignment.add(keyboardAlignment);
        }
        int alignmentId = getAlignmentId();
        if (alignmentId == 0) {
            this.selectedItem = 0;
        } else if (alignmentId >= 4 || alignmentId <= 0) {
            for (i = 3; i < this.listKeyboardAlignment.size(); i++) {
                if (((KeyboardAlignment) this.listKeyboardAlignment.get(i)).alignmentId == alignmentId) {
                    this.selectedItem = i;
                    break;
                }
            }
        } else {
            this.selectedItem = alignmentId - 1;
        }
        this.adapter.notifyDataSetChanged();
    }

    private void initUI() {
        this.tip_TextView = (TextView) findViewById(C0182R.id.tip_TextView);
        int alignmentId = getAlignmentId();
        if (alignmentId > 3) {
            String name = AppConfig.SERVER_IP;
            if (AppUtils.nowKBCustomAlignmentID != 0) {
                name = KeyboardAlignment.getAlignmentNameWithId(AppUtils.databaseManager, AppUtils.nowKBCustomAlignmentID);
            }
            if (name == null || name.isEmpty() || name.equals(AppConfig.SERVER_IP)) {
                this.tip_TextView.setText(getResources().getString(C0182R.string.layout_custom));
            } else {
                this.tip_TextView.setText(name);
            }
        } else if (alignmentId == -1) {
            this.tip_TextView.setText(getResources().getString(C0182R.string.unknow));
        } else {
            this.tip_TextView.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.layout_default))).append(alignmentId).toString());
        }
        this.add_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.add_RelativeLayout);
        this.listView = (ListView) findViewById(C0182R.id.listView);
        this.listKeyboardAlignment = new ArrayList();
        this.adapter = new MyAdapter(this, this.listKeyboardAlignment);
        this.listView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
        this.add_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.add_RelativeLayout);
        this.add_RelativeLayout.setOnClickListener(this);
    }

    public void saveCustomAlignmentId(int alignmentId) {
        List<DeviceStateDB> deviceStateDBList = AppUtils.databaseManager.findAllDeviceStateInfo();
        int len = deviceStateDBList.size();
        boolean is_exit = false;
        for (int i = 0; i < len; i++) {
            DeviceStateDB deviceStateDB = (DeviceStateDB) deviceStateDBList.get(i);
            if (deviceStateDB.deviceId.equals(BluetoothLeService.deviceId)) {
                C0213L.m19i(" is exit ");
                is_exit = true;
                deviceStateDB.customAlgnmentId = alignmentId;
                AppUtils.databaseManager.modifyDeviceStateInfo(deviceStateDB);
                break;
            }
        }
        if (!is_exit) {
            C0213L.m19i(" is exit false");
            deviceStateDB = new DeviceStateDB();
            deviceStateDB.deviceId = BluetoothLeService.deviceId;
            deviceStateDB.customAlgnmentId = alignmentId;
            deviceStateDB.alignmentId = alignmentId;
            AppUtils.databaseManager.addDeviceStateInfo(deviceStateDB);
        }
    }

    public void saveAlignmentId(int alignmentId) {
        List<DeviceStateDB> deviceStateDBList = AppUtils.databaseManager.findAllDeviceStateInfo();
        int len = deviceStateDBList.size();
        boolean is_exit = false;
        for (int i = 0; i < len; i++) {
            DeviceStateDB deviceStateDB = (DeviceStateDB) deviceStateDBList.get(i);
            if (deviceStateDB.deviceId.equals(BluetoothLeService.deviceId)) {
                C0213L.m19i(" is exit ");
                is_exit = true;
                deviceStateDB.alignmentId = alignmentId;
                AppUtils.databaseManager.modifyDeviceStateInfo(deviceStateDB);
                break;
            }
        }
        if (!is_exit) {
            C0213L.m19i(" is exit false");
            deviceStateDB = new DeviceStateDB();
            deviceStateDB.deviceId = BluetoothLeService.deviceId;
            deviceStateDB.alignmentId = alignmentId;
            AppUtils.databaseManager.addDeviceStateInfo(deviceStateDB);
        }
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

    public int getCustomAlignmentId() {
        List<DeviceStateDB> deviceStateDBList = AppUtils.databaseManager.findAllDeviceStateInfo();
        int len = deviceStateDBList.size();
        for (int i = 0; i < len; i++) {
            DeviceStateDB deviceStateDB = (DeviceStateDB) deviceStateDBList.get(i);
            if (deviceStateDB.deviceId.equals(BluetoothLeService.deviceId)) {
                return deviceStateDB.customAlgnmentId;
            }
        }
        return -1;
    }
}
