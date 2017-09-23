package ti.android.ble.common;

import android.app.ProgressDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.widget.Toast;
import com.obins.anne.C0182R;
import com.obins.anne.db.DeviceDB;
import com.obins.anne.db.DeviceStateDB;
import com.obins.anne.db.KeyboardLightEffectDB;
import com.obins.anne.db.MacroMember;
import com.obins.anne.db.MacroMemberValue;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.BLEKeyboard;
import com.obins.anne.utils.DeviceIdFactory;
import com.obins.anne.utils.KeyObjectUtil;
import com.obins.anne.utils.KeyboardAlignment;
import com.obins.anne.utils.KeyboardLightEffect;
import com.obins.anne.utils.NormalFun;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import www.robinwatch.squid.utils.C0213L;

public class BluetoothLeService extends Service {
    public static final String ACTION_DATA_BLE = "ti.android.ble.common.ACTION_DATA_BLE_SET";
    public static final String ACTION_DATA_FLUSHUI = "ti.android.ble.common.ACTION_DATA_FLUSHUI";
    public static final String ACTION_DATA_FW_UPDATE = "ti.android.ble.common.ACTION_DATA_FW_UPDATE";
    public static final String ACTION_DATA_KEYBOARD = "ti.android.ble.common.ACTION_DATA_KEYBOARD";
    public static final String ACTION_DATA_LED = "ti.android.ble.common.ACTION_DATA_LED";
    public static final String ACTION_DATA_MACRO = "ti.android.ble.common.ACTION_DATA_MACRO";
    public static final String ACTION_DATA_NOTIFY = "ti.android.ble.common.ACTION_DATA_NOTIFY";
    public static final String ACTION_DATA_READ = "ti.android.ble.common.ACTION_DATA_READ";
    public static final String ACTION_DATA_WRITE = "ti.android.ble.common.ACTION_DATA_WRITE";
    public static final String ACTION_GATT_CONNECTED = "ti.android.ble.common.ACTION_GATT_CONNECTED";
    public static final String ACTION_GATT_DISCONNECTED = "ti.android.ble.common.ACTION_GATT_DISCONNECTED";
    public static final String ACTION_GATT_SERVICES_DISCOVERED = "ti.android.ble.common.ACTION_GATT_SERVICES_DISCOVERED";
    public static final String ACTION_GATT_SERVICES_DISCOVERED_COMPLETE = "ti.android.ble.common.ACTION_GATT_SERVICES_DISCOVERED_COMPLETE";
    public static final String EXTRA_ADDRESS = "ti.android.ble.common.EXTRA_ADDRESS";
    public static final String EXTRA_DATA = "ti.android.ble.common.EXTRA_DATA";
    public static final String EXTRA_STATUS = "ti.android.ble.common.EXTRA_STATUS";
    public static final String EXTRA_TYPE = "ti.android.ble.common.EXTRA_TYPE";
    public static final String EXTRA_TYPE_ALIGNMENT = "ti.android.ble.common.EXTRA_TYPE_ALIGNMENT";
    public static final String EXTRA_TYPE_ALIGNMENT_SET = "ti.android.ble.common.EXTRA_TYPE_ALIGNMENT_SET";
    public static final int EXTRA_TYPE_ALIGNMENT_SET_SUCCESS = 0;
    public static final String EXTRA_TYPE_BLE_MODE_GET_ACK = "ti.android.ble.common.EXTRA_TYPE_BLE_MODE_GET_ACK";
    public static final String EXTRA_TYPE_BLE_MODE_SET_ACK = "ti.android.ble.common.EXTRA_TYPE_BLE_MODE_SET_ACK";
    public static final String EXTRA_TYPE_FW_UPDATE_GET_VERSION_ACK = "ti.android.ble.common.EXTRA_TYPE_FW_UPDATE_GET_VERSION_ACK";
    public static final String EXTRA_TYPE_LED_GETNOWID = "ti.android.ble.common.EXTRA_TYPE_LED_GETNOWID";
    public static final String EXTRA_TYPE_LED_GETNOW_CustomID = "ti.android.ble.common.EXTRA_TYPE_LED_GETNOW_CustomID";
    public static final String EXTRA_TYPE_LED_SET = "ti.android.ble.common.EXTRA_TYPE_LED_SET";
    public static final int EXTRA_TYPE_LED_SET_SUCCESS = 0;
    public static final String EXTRA_TYPE_MACRO_DOWNLOAD_ACK = "ti.android.ble.common.EXTRA_TYPE_MACRO_DOWNLOAD_ACK";
    public static final String EXTRA_TYPE_MACRO_RECORD_KEY = "ti.android.ble.common.EXTRA_TYPE_MACRO_RECORD_KEY";
    public static final String EXTRA_TYPE_MACRO_START_RECORD = "ti.android.ble.common.EXTRA_TYPE_MACRO_START_RECORD";
    public static final String EXTRA_TYPE_MACRO_STOP_RECORD = "ti.android.ble.common.EXTRA_TYPE_MACRO_STOP_RECORD";
    public static final String EXTRA_TYPE_MACRO_UP_FINISH = "ti.android.ble.common.EXTRA_TYPE_MACRO_UP_FINISH";
    public static final String EXTRA_UUID = "ti.android.ble.common.EXTRA_UUID";
    private static final int SET_SYNC_OK = 5;
    private static final int START_SYNC_DATA = 0;
    private static final int SYSNC_ERRO_TIMEOUT = 4;
    private static final int SYSNC_ERRO_TIMEOUT_ALIGNMENT = 7;
    private static final int SYSNC_ERRO_TIMEOUT_LIGHT = 6;
    private static final int SYSNC_ERRO_TIMEOUT_MACRO = 8;
    static final String TAG = "BluetoothLeService";
    private static final int USER_ALIGNMENT_SYNC_OK = 2;
    private static final int USER_LIGHTEFFECT_SYNC_OK = 1;
    private static final int USER_MACRO_SYNC_OK = 3;
    public static BluetoothDevice device;
    public static String deviceId = AppConfig.SERVER_IP;
    private static int getMacronum = 0;
    private static int getMacronumAck = 0;
    private static int is_sysnc = 0;
    private static BluetoothLeService mThis = null;
    public static ProgressDialog progressDialog;
    public static String selectMac = AppConfig.SERVER_IP;
    private static int set_sync_code_flag = 0;
    private static int sync_user_alignment_flag = 0;
    private static int sync_user_lighteffect_flag = 0;
    private static int sync_user_macro_flag = 0;
    private final IBinder binder = new LocalBinder();
    private List<Integer> deviceIdDataList;
    private Handler handler = new C01932();
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt = null;
    private BluetoothManager mBluetoothManager = null;
    private BluetoothAdapter mBtAdapter = null;
    private volatile boolean mBusy = false;
    private BluetoothGattCallback mGattCallbacks = new C01921();
    public BluetoothGattService mOadService = null;
    private List<Integer> macroDataList;
    private List<Integer> userAlignmentList;
    private List<Integer> userLighteffectList;

    class C01921 extends BluetoothGattCallback {
        C01921() {
        }

        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (status == 2) {
                if (BluetoothLeService.this.mBluetoothGatt == null) {
                    Log.e(BluetoothLeService.TAG, "mBluetoothGatt not created!");
                    return;
                }
                String address = gatt.getDevice().getAddress();
                Log.d(BluetoothLeService.TAG, "onConnectionStateChange (" + address + ") " + newState + " status: " + status);
                switch (newState) {
                    case 0:
                        if (BluetoothLeService.this.mBluetoothGatt != null) {
                            BluetoothLeService.this.mBluetoothGatt.close();
                            BluetoothLeService.this.mBluetoothGatt.disconnect();
                            BluetoothLeService.this.mBluetoothGatt = null;
                        }
                        BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_GATT_DISCONNECTED, address, status);
                        BluetoothLeService.this.mOadService = null;
                        BluetoothLeService.is_sysnc = 0;
                        return;
                    case 2:
                        BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_GATT_CONNECTED, address, status);
                        if (BluetoothLeService.this.mBluetoothGatt == null) {
                            return;
                        }
                        if (BluetoothLeService.getInstance().getNumServices() == 0 || BluetoothLeService.this.mOadService == null) {
                            BluetoothLeService.this.mBluetoothGatt.discoverServices();
                            return;
                        }
                        return;
                    default:
                        try {
                            if (BluetoothLeService.this.mBluetoothGatt != null) {
                                BluetoothLeService.this.mBluetoothGatt.close();
                                BluetoothLeService.this.mBluetoothGatt.disconnect();
                                BluetoothLeService.this.mBluetoothGatt = null;
                            }
                            Log.e(BluetoothLeService.TAG, "New state not processed: " + newState);
                            return;
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                }
                e.printStackTrace();
            }
        }

        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED, gatt.getDevice().getAddress(), status);
            BluetoothLeService.this.checkOad();
        }

        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_DATA_NOTIFY, characteristic, 0);
            Log.i(AppConfig.SERVER_IP, "onCharacteristicChanged onCharacteristicChanged");
        }

        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_DATA_READ, characteristic, status);
            Log.i(BluetoothLeService.TAG, "onCharacteristicRead onCharacteristicRead");
        }

        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            BluetoothLeService.this.broadcastUpdate(BluetoothLeService.ACTION_DATA_WRITE, characteristic, status);
            Log.i(BluetoothLeService.TAG, "onCharacteristicWrite onCharacteristicWrite");
        }

        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            BluetoothLeService.this.mBusy = false;
            Log.i(BluetoothLeService.TAG, "onDescriptorRead");
        }

        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            BluetoothLeService.this.mBusy = false;
            Log.i(BluetoothLeService.TAG, "onDescriptorWrite");
        }
    }

    class C01932 extends Handler {
        C01932() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    BluetoothLeService.sync_user_lighteffect_flag = 0;
                    BluetoothLeService.sync_user_alignment_flag = 0;
                    BluetoothLeService.sync_user_macro_flag = 0;
                    if (AppUtils.context != null) {
                        BluetoothLeService.progressDialog = ProgressDialog.show(AppUtils.context, AppConfig.SERVER_IP, AppUtils.appUtils.getResources().getString(C0182R.string.kb_data_sync));
                        break;
                    }
                    break;
                case 1:
                    BluetoothLeService.sync_user_lighteffect_flag = 1;
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_lighteffect_success), 0).show();
                    break;
                case 2:
                    BluetoothLeService.sync_user_alignment_flag = 1;
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_alignment_success), 0).show();
                    break;
                case 3:
                    BluetoothLeService.sync_user_macro_flag = 1;
                    AppUtils.macroIsNewFlag = true;
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_macro_success), 0).show();
                    break;
                case 4:
                    if (BluetoothLeService.progressDialog != null && BluetoothLeService.progressDialog.isShowing()) {
                        BluetoothLeService.progressDialog.cancel();
                    }
                    BluetoothLeService.is_sysnc = 0;
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_fail), 0).show();
                    BLEHandle.getDeviceId(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                    break;
                case 5:
                    if (BluetoothLeService.progressDialog != null && BluetoothLeService.progressDialog.isShowing()) {
                        BluetoothLeService.progressDialog.cancel();
                    }
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_success), 0).show();
                    BLEHandle.getDeviceId(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                    break;
                case 6:
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_lighteffect_fail), 0).show();
                    break;
                case 7:
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_alignment_fail), 0).show();
                    break;
                case 8:
                    if (BluetoothLeService.progressDialog != null && BluetoothLeService.progressDialog.isShowing()) {
                        BluetoothLeService.progressDialog.cancel();
                    }
                    BluetoothLeService.is_sysnc = 0;
                    AppUtils.macroIsNewFlag = false;
                    Toast.makeText(BluetoothLeService.getInstance(), AppUtils.appUtils.getResources().getString(C0182R.string.sync_macro_fail), 0).show();
                    BLEHandle.getDeviceId(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C01943 implements Runnable {
        C01943() {
        }

        public void run() {
            try {
                Thread.sleep(3000);
                if (BluetoothLeService.sync_user_lighteffect_flag == 0) {
                    C0213L.m19i(" sync_user_lighteffect_flag == 0 ");
                    Message msg = new Message();
                    msg.what = 6;
                    BluetoothLeService.this.handler.sendMessage(msg);
                }
                C0213L.m19i(" start sync alignment");
                BLEHandle.getUserAlignment(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (BluetoothLeService.sync_user_alignment_flag == 0) {
                msg = new Message();
                msg.what = 7;
                BluetoothLeService.this.handler.sendMessage(msg);
            }
            BluetoothLeService.getMacronum = 0;
            BluetoothLeService.getMacronumAck = 0;
            while (BluetoothLeService.getMacronum <= 9) {
                try {
                    C0213L.m19i(" start sync macro");
                    BLEHandle.getMarcoFromKB(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), BluetoothLeService.getMacronum);
                    Thread.sleep(2000);
                    C0213L.m19i(" getMacronumAck = " + BluetoothLeService.getMacronumAck);
                    C0213L.m19i(" getMacronum = " + BluetoothLeService.getMacronum);
                    if (BluetoothLeService.getMacronumAck > BluetoothLeService.getMacronum || BluetoothLeService.getMacronum == 9) {
                        BluetoothLeService.getMacronum = BluetoothLeService.getMacronum + 1;
                    } else {
                        C0213L.m19i(" SYSNC_ERRO_TIMEOUT = ");
                        msg = new Message();
                        msg.what = 8;
                        BluetoothLeService.this.handler.sendMessage(msg);
                        return;
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
            C0213L.m19i(" start set sync");
            if (BluetoothLeService.sync_user_lighteffect_flag == 0 || BluetoothLeService.sync_user_alignment_flag == 0) {
                msg = new Message();
                msg.what = 4;
                BluetoothLeService.this.handler.sendMessage(msg);
                return;
            }
            BluetoothLeService.this.setSyncCode();
            try {
                Thread.sleep(1000);
                if (BluetoothLeService.set_sync_code_flag == 0) {
                    C0213L.m19i(" set_sync_code_flag == 0");
                    msg = new Message();
                    msg.what = 4;
                    BluetoothLeService.this.handler.sendMessage(msg);
                    return;
                }
                C0213L.m19i(" set_sync_code_flag != 0");
                msg = new Message();
                msg.what = 5;
                BluetoothLeService.this.handler.sendMessage(msg);
            } catch (Exception e222) {
                e222.printStackTrace();
            }
        }
    }

    class C01954 implements Runnable {
        C01954() {
        }

        public void run() {
            try {
                Thread.sleep(150);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String androidStr = new DeviceIdFactory(BluetoothLeService.getInstance()).getAndroidId();
            byte[] data = new byte[8];
            int i;
            if (androidStr == null || androidStr.isEmpty() || androidStr.length() != 16) {
                for (i = 0; i < 8; i++) {
                    data[i] = (byte) 0;
                }
            } else {
                for (i = 0; i < 8; i++) {
                    String hexString = AppConfig.SERVER_IP;
                    if (i == 7) {
                        hexString = androidStr.substring(14);
                    } else {
                        hexString = androidStr.substring(i, i + 2);
                    }
                    data[i] = (byte) NormalFun.hexStringToInt(hexString);
                }
            }
            BLEHandle.setIsNeedSyncCode(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), data);
        }
    }

    public class LocalBinder extends Binder {
        public BluetoothLeService getService() {
            return BluetoothLeService.this;
        }
    }

    private void broadcastUpdate(String action, String address, int status) {
        Intent intent = new Intent(action);
        intent.putExtra(EXTRA_ADDRESS, address);
        intent.putExtra(EXTRA_STATUS, status);
        sendBroadcast(intent);
        this.mBusy = false;
    }

    private void broadcastUpdate(String action, BluetoothGattCharacteristic characteristic, int status) {
        this.mBusy = false;
        if (action == ACTION_DATA_NOTIFY) {
            Log.i(TAG, " receive data: action = " + action);
            byte[] data = characteristic.getValue();
            int len = data.length;
            if (len > 0) {
                int i;
                List<Integer> dataList = new ArrayList();
                for (i = 0; i < len; i++) {
                    int dataInt;
                    if (data[i] >= (byte) 0) {
                        Log.i(TAG, " receive dataInt: " + data[i]);
                        dataInt = data[i];
                    } else {
                        String hexString = Integer.toHexString(data[i]);
                        String a = AppConfig.SERVER_IP;
                        if (hexString.length() == 2) {
                            a = hexString;
                        } else {
                            a = hexString.substring(6);
                        }
                        int aInt = NormalFun.hexStringToInt(a);
                        Log.i(TAG, " receive dataInt: " + aInt);
                        dataInt = aInt;
                    }
                    dataList.add(Integer.valueOf(dataInt));
                }
                int nBlock;
                int iBlock;
                int datalen;
                boolean is_exit;
                Message msg;
                Intent intent;
                int ackData;
                switch (((Integer) dataList.get(0)).intValue()) {
                    case 2:
                        List<DeviceDB> listDeviceDB;
                        DeviceDB deviceDB;
                        if (((Integer) dataList.get(2)).intValue() == 129) {
                            nBlock = ((Integer) dataList.get(4)).intValue();
                            iBlock = ((Integer) dataList.get(5)).intValue();
                            datalen = ((Integer) dataList.get(3)).intValue();
                            if (iBlock == 0) {
                                this.deviceIdDataList = new ArrayList();
                                for (i = 0; i < datalen - 2; i++) {
                                    this.deviceIdDataList.add((Integer) dataList.get(i + 6));
                                }
                                if (iBlock == nBlock - 1) {
                                    C0213L.m19i(" Finish!!");
                                    return;
                                }
                                return;
                            } else if (iBlock < nBlock - 1) {
                                for (i = 0; i < datalen - 2; i++) {
                                    this.deviceIdDataList.add((Integer) dataList.get(i + 6));
                                }
                                return;
                            } else if (iBlock == nBlock - 1) {
                                for (i = 0; i < datalen - 2; i++) {
                                    this.deviceIdDataList.add((Integer) dataList.get(i + 6));
                                }
                                String deviceIdDataListStr = AppConfig.SERVER_IP;
                                deviceIdDataListStr = dataList.get(0) + " " + dataList.get(1) + " " + dataList.get(2) + " ";
                                for (i = 0; i < this.deviceIdDataList.size(); i++) {
                                    deviceIdDataListStr = new StringBuilder(String.valueOf(deviceIdDataListStr)).append(" ").append(this.deviceIdDataList.get(i)).toString();
                                }
                                C0213L.m19i("deviceIdDataList = " + deviceIdDataListStr);
                                int deviceType = ((Integer) this.deviceIdDataList.get(0)).intValue();
                                int deviceModel = ((Integer) this.deviceIdDataList.get(1)).intValue();
                                String deviceIDStr = AppConfig.SERVER_IP;
                                for (i = 2; i < this.deviceIdDataList.size(); i++) {
                                    String hexStr = Integer.toHexString(((Integer) this.deviceIdDataList.get(i)).intValue());
                                    if (hexStr.length() == 1) {
                                        hexStr = "0" + hexStr;
                                    }
                                    deviceIDStr = new StringBuilder(String.valueOf(deviceIDStr)).append(hexStr).toString();
                                }
                                deviceId = deviceIDStr;
                                C0213L.m19i("deviceId = " + deviceId);
                                listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
                                is_exit = false;
                                for (i = 0; i < listDeviceDB.size(); i++) {
                                    if (((DeviceDB) listDeviceDB.get(i)).deviceId.equals(deviceId)) {
                                        is_exit = true;
                                        if (is_exit) {
                                            C0213L.m19i(" is_exit == false ");
                                            deviceDB = new DeviceDB();
                                            deviceDB.macString = selectMac;
                                            deviceDB.deviceId = deviceIDStr;
                                            deviceDB.deviceType = deviceType;
                                            deviceDB.deviceModel = deviceModel;
                                            deviceDB.mainMcuVersion = 0;
                                            deviceDB.kMcuVersion = 0;
                                            deviceDB.bleVersion = 0;
                                            AppUtils.databaseManager.addDeviceInfo(deviceDB);
                                            return;
                                        }
                                        C0213L.m19i(" is_exit ");
                                        return;
                                    }
                                }
                                if (is_exit) {
                                    C0213L.m19i(" is_exit ");
                                    return;
                                }
                                C0213L.m19i(" is_exit == false ");
                                deviceDB = new DeviceDB();
                                deviceDB.macString = selectMac;
                                deviceDB.deviceId = deviceIDStr;
                                deviceDB.deviceType = deviceType;
                                deviceDB.deviceModel = deviceModel;
                                deviceDB.mainMcuVersion = 0;
                                deviceDB.kMcuVersion = 0;
                                deviceDB.bleVersion = 0;
                                AppUtils.databaseManager.addDeviceInfo(deviceDB);
                                return;
                            } else {
                                return;
                            }
                        } else if (((Integer) dataList.get(2)).intValue() == 137) {
                            set_sync_code_flag = 1;
                            AppUtils.macroIsNewFlag = true;
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 136) {
                            int result = -1;
                            if (dataList.size() > 3) {
                                result = ((Integer) dataList.get(3)).intValue();
                            }
                            if (result == 1) {
                                AppUtils.macroIsNewFlag = false;
                                sync_user_lighteffect_flag = 0;
                                sync_user_alignment_flag = 0;
                                sync_user_macro_flag = 0;
                                set_sync_code_flag = 0;
                                msg = new Message();
                                msg.what = 0;
                                this.handler.sendMessage(msg);
                                C0213L.m19i(" start sync lighteffect");
                                BLEHandle.getCostomLightEffectData(getInstance(), (BluetoothGattCharacteristic) getInstance().mOadService.getCharacteristics().get(1));
                                new Thread(new C01943()).start();
                                return;
                            }
                            AppUtils.macroIsNewFlag = true;
                            BLEHandle.getDeviceId(getInstance(), (BluetoothGattCharacteristic) getInstance().mOadService.getCharacteristics().get(1));
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 133) {
                            C0213L.m19i("SYSTEM_ACK_GET_FW_VER");
                            if (dataList.size() < 5) {
                                return;
                            }
                            if (((Integer) dataList.get(3)).intValue() == 1) {
                                C0213L.m19i("SYSTEM_ACK_GET_FW_VER main mcu");
                                int mainMcuVersion = (((Integer) dataList.get(4)).intValue() * 10) + ((Integer) dataList.get(5)).intValue();
                                listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
                                C0213L.m19i("listDeviceDB.size() = " + listDeviceDB.size());
                                for (i = 0; i < listDeviceDB.size(); i++) {
                                    deviceDB = (DeviceDB) listDeviceDB.get(i);
                                    if (deviceDB.deviceId.equals(deviceId) && deviceDB.mainMcuVersion != mainMcuVersion) {
                                        C0213L.m19i("SYSTEM_ACK_GET_FW_VER get ok and set mainMcuVersion = " + mainMcuVersion);
                                        deviceDB.mainMcuVersion = mainMcuVersion;
                                        AppUtils.databaseManager.modifyDeviceInfo(deviceDB);
                                    }
                                }
                                return;
                            } else if (((Integer) dataList.get(3)).intValue() == 2) {
                                int kMcuVersion = (((Integer) dataList.get(4)).intValue() * 10) + ((Integer) dataList.get(5)).intValue();
                                listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
                                for (i = 0; i < listDeviceDB.size(); i++) {
                                    deviceDB = (DeviceDB) listDeviceDB.get(i);
                                    if (deviceDB.deviceId.equals(deviceId) && deviceDB.kMcuVersion != kMcuVersion) {
                                        deviceDB.kMcuVersion = kMcuVersion;
                                        AppUtils.databaseManager.modifyDeviceInfo(deviceDB);
                                    }
                                }
                                return;
                            } else if (((Integer) dataList.get(3)).intValue() == 3) {
                                int bleVersion = ((Integer) dataList.get(4)).intValue();
                                listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
                                for (i = 0; i < listDeviceDB.size(); i++) {
                                    deviceDB = (DeviceDB) listDeviceDB.get(i);
                                    if (deviceDB.deviceId.equals(deviceId) && deviceDB.bleVersion != bleVersion) {
                                        deviceDB.bleVersion = bleVersion;
                                        AppUtils.databaseManager.modifyDeviceInfo(deviceDB);
                                    }
                                }
                                return;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 5:
                        if (((Integer) dataList.get(2)).intValue() == 133) {
                            if (getMacronumAck == 0) {
                                AppUtils.databaseManager.closeAllMacroMember();
                            }
                            if (((Integer) dataList.get(1)).intValue() <= 12) {
                                getMacronumAck++;
                                if (((Integer) dataList.get(4)).intValue() == 0 && ((Integer) dataList.get(5)).intValue() == 0 && ((Integer) dataList.get(6)).intValue() == 0 && ((Integer) dataList.get(7)).intValue() == 0) {
                                    getMacronum = 9;
                                    msg = new Message();
                                    msg.what = 3;
                                    this.handler.sendMessage(msg);
                                    return;
                                }
                                return;
                            }
                            nBlock = ((Integer) dataList.get(4)).intValue();
                            iBlock = ((Integer) dataList.get(5)).intValue();
                            datalen = ((Integer) dataList.get(3)).intValue();
                            if (nBlock == 0 && iBlock == 0) {
                                C0213L.m19i(" Finish!!");
                                getMacronumAck++;
                                getMacronum = 9;
                                msg = new Message();
                                msg.what = 3;
                                this.handler.sendMessage(msg);
                                return;
                            }
                            boolean oneMacro_isOk = false;
                            if (iBlock == 0) {
                                this.macroDataList = new ArrayList();
                                for (i = 0; i < datalen - 2; i++) {
                                    this.macroDataList.add((Integer) dataList.get(i + 6));
                                }
                                if (iBlock == nBlock - 1) {
                                    getMacronumAck++;
                                    oneMacro_isOk = true;
                                }
                            } else if (iBlock < nBlock - 1) {
                                for (i = 0; i < datalen - 2; i++) {
                                    this.macroDataList.add((Integer) dataList.get(i + 6));
                                }
                            } else if (iBlock == nBlock - 1) {
                                getMacronumAck++;
                                oneMacro_isOk = true;
                                for (i = 0; i < datalen - 2; i++) {
                                    this.macroDataList.add((Integer) dataList.get(i + 6));
                                }
                            }
                            if (oneMacro_isOk) {
                                MacroMember macroMember;
                                int macroMemberValueLen;
                                MacroMemberValue macroMemberValue;
                                String macroDataListStr = AppConfig.SERVER_IP;
                                macroDataListStr = dataList.get(0) + " " + dataList.get(1) + " " + dataList.get(2) + " ";
                                for (i = 0; i < this.macroDataList.size(); i++) {
                                    macroDataListStr = new StringBuilder(String.valueOf(macroDataListStr)).append(" ").append(this.macroDataList.get(i)).toString();
                                }
                                C0213L.m19i("macroDataList = " + macroDataListStr);
                                byte[] macroDataIDByte = new byte[4];
                                for (i = 0; i < 4; i++) {
                                    macroDataIDByte[i] = (byte) ((Integer) this.macroDataList.get(i + 3)).intValue();
                                }
                                int macroDataID = NormalFun.byteArrayToInt(macroDataIDByte);
                                C0213L.m19i("macroDataID = " + macroDataID);
                                List<MacroMember> macroMemberList = AppUtils.databaseManager.findAllMacroMember();
                                int macroMemberListlen = macroMemberList.size();
                                boolean is_ExitmacroMember = false;
                                for (i = 0; i < macroMemberListlen; i++) {
                                    macroMember = (MacroMember) macroMemberList.get(i);
                                    C0213L.m19i("macroMember.crcID = " + macroMember.crcID);
                                    if (macroDataID == macroMember.crcID) {
                                        is_ExitmacroMember = true;
                                        macroMember.isOn = 1;
                                        AppUtils.databaseManager.modifyMacroMember(macroMember);
                                        C0213L.m19i("macroMemberValueLen = " + this.macroDataList.get(7));
                                        if (!is_ExitmacroMember) {
                                            C0213L.m20i(TAG, " The MACRO is Exit!");
                                        } else if (this.macroDataList.size() >= 11) {
                                            C0213L.m20i(TAG, " NO MACRO And Add");
                                            macroMember = new MacroMember();
                                            macroMember.crcID = macroDataID;
                                            macroMember.isOn = 1;
                                            macroMember.keyIndex = ((Integer) this.macroDataList.get(1)).intValue();
                                            macroMember.keyValue = ((Integer) this.macroDataList.get(2)).intValue();
                                            macroMember.keyWord = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(macroMember.keyValue));
                                            macroMember.macromemberName = AppUtils.appUtils.getResources().getString(C0182R.string.unknow);
                                            macroMember.triggerway = MacroMember.TRIGGERWAY_DOWN;
                                            macroMember._id = AppUtils.databaseManager.addMacroMember(macroMember);
                                            macroMemberValueLen = ((Integer) this.macroDataList.get(7)).intValue();
                                            for (i = 0; i < macroMemberValueLen; i++) {
                                                macroMemberValue = new MacroMemberValue();
                                                macroMemberValue.value = ((Integer) this.macroDataList.get((i * 5) + 8)).intValue();
                                                macroMemberValue.downTime = ((((Integer) this.macroDataList.get(((i * 5) + 8) + 2)).intValue() & MotionEventCompat.ACTION_MASK) << 8) + (((Integer) this.macroDataList.get(((i * 5) + 8) + 1)).intValue() & MotionEventCompat.ACTION_MASK);
                                                macroMemberValue.upTime = ((((Integer) this.macroDataList.get(((i * 5) + 8) + 4)).intValue() & MotionEventCompat.ACTION_MASK) << 8) + (((Integer) this.macroDataList.get(((i * 5) + 8) + 3)).intValue() & MotionEventCompat.ACTION_MASK);
                                                macroMemberValue.valueStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(macroMemberValue.value));
                                                macroMemberValue.memberId = macroMember._id;
                                                macroMemberValue.valueType = MacroMemberValue.MACROVALUE_TYPE_KEY;
                                                AppUtils.databaseManager.addMacroMemberValue(macroMemberValue);
                                            }
                                        }
                                        if (((Integer) this.macroDataList.get(0)).intValue() == 9) {
                                            C0213L.m19i(" Finish!!! ");
                                            intent = new Intent(ACTION_DATA_MACRO);
                                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_UP_FINISH);
                                            sendBroadcast(intent);
                                            msg = new Message();
                                            msg.what = 3;
                                            this.handler.sendMessage(msg);
                                            return;
                                        }
                                        return;
                                    }
                                }
                                C0213L.m19i("macroMemberValueLen = " + this.macroDataList.get(7));
                                if (!is_ExitmacroMember) {
                                    C0213L.m20i(TAG, " The MACRO is Exit!");
                                } else if (this.macroDataList.size() >= 11) {
                                    C0213L.m20i(TAG, " NO MACRO And Add");
                                    macroMember = new MacroMember();
                                    macroMember.crcID = macroDataID;
                                    macroMember.isOn = 1;
                                    macroMember.keyIndex = ((Integer) this.macroDataList.get(1)).intValue();
                                    macroMember.keyValue = ((Integer) this.macroDataList.get(2)).intValue();
                                    macroMember.keyWord = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(macroMember.keyValue));
                                    macroMember.macromemberName = AppUtils.appUtils.getResources().getString(C0182R.string.unknow);
                                    macroMember.triggerway = MacroMember.TRIGGERWAY_DOWN;
                                    macroMember._id = AppUtils.databaseManager.addMacroMember(macroMember);
                                    macroMemberValueLen = ((Integer) this.macroDataList.get(7)).intValue();
                                    for (i = 0; i < macroMemberValueLen; i++) {
                                        macroMemberValue = new MacroMemberValue();
                                        macroMemberValue.value = ((Integer) this.macroDataList.get((i * 5) + 8)).intValue();
                                        macroMemberValue.downTime = ((((Integer) this.macroDataList.get(((i * 5) + 8) + 2)).intValue() & MotionEventCompat.ACTION_MASK) << 8) + (((Integer) this.macroDataList.get(((i * 5) + 8) + 1)).intValue() & MotionEventCompat.ACTION_MASK);
                                        macroMemberValue.upTime = ((((Integer) this.macroDataList.get(((i * 5) + 8) + 4)).intValue() & MotionEventCompat.ACTION_MASK) << 8) + (((Integer) this.macroDataList.get(((i * 5) + 8) + 3)).intValue() & MotionEventCompat.ACTION_MASK);
                                        macroMemberValue.valueStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(macroMemberValue.value));
                                        macroMemberValue.memberId = macroMember._id;
                                        macroMemberValue.valueType = MacroMemberValue.MACROVALUE_TYPE_KEY;
                                        AppUtils.databaseManager.addMacroMemberValue(macroMemberValue);
                                    }
                                }
                                if (((Integer) this.macroDataList.get(0)).intValue() == 9) {
                                    C0213L.m19i(" Finish!!! ");
                                    intent = new Intent(ACTION_DATA_MACRO);
                                    intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_UP_FINISH);
                                    sendBroadcast(intent);
                                    msg = new Message();
                                    msg.what = 3;
                                    this.handler.sendMessage(msg);
                                    return;
                                }
                                return;
                            }
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 129) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_MACRO);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_START_RECORD);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 130) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_MACRO);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_STOP_RECORD);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 3 || ((Integer) dataList.get(2)).intValue() == 6) {
                            if (dataList.size() >= 11) {
                                intent = new Intent(ACTION_DATA_MACRO);
                                int[] valueInt = new int[8];
                                for (i = 0; i < 8; i++) {
                                    valueInt[i] = ((Integer) dataList.get(i + 3)).intValue();
                                }
                                intent.putExtra(EXTRA_DATA, valueInt);
                                intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_RECORD_KEY);
                                sendBroadcast(intent);
                                return;
                            }
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 132) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_MACRO);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_MACRO_DOWNLOAD_ACK);
                            sendBroadcast(intent);
                            return;
                        } else {
                            return;
                        }
                    case 6:
                        if (((Integer) dataList.get(2)).intValue() == 134) {
                            List<Integer> hostList = new ArrayList();
                            byte hostByte = (byte) ((Integer) dataList.get(3)).intValue();
                            for (i = 0; i < 8; i++) {
                                hostList.add(Integer.valueOf(hostByte & (1 << i)));
                            }
                            int linkNum = ((Integer) dataList.get(4)).intValue();
                            int nowMode = ((Integer) dataList.get(5)).intValue();
                            intent = new Intent(ACTION_DATA_BLE);
                            intent.putExtra(EXTRA_DATA, nowMode);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_BLE_MODE_GET_ACK);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 140) {
                            intent = new Intent(ACTION_DATA_BLE);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_BLE_MODE_SET_ACK);
                            sendBroadcast(intent);
                            return;
                        } else {
                            return;
                        }
                    case 7:
                        int alignmentID;
                        if (((Integer) dataList.get(2)).intValue() == 133) {
                            nBlock = ((Integer) dataList.get(4)).intValue();
                            iBlock = ((Integer) dataList.get(5)).intValue();
                            datalen = ((Integer) dataList.get(3)).intValue();
                            if (iBlock == 0) {
                                this.userAlignmentList = new ArrayList();
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userAlignmentList.add((Integer) dataList.get(i + 6));
                                }
                                return;
                            } else if (iBlock < nBlock - 1) {
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userAlignmentList.add((Integer) dataList.get(i + 6));
                                }
                                return;
                            } else if (iBlock == nBlock - 1) {
                                DeviceStateDB deviceStateDB;
                                boolean is_dutyAlignmentData;
                                List<KeyboardAlignment> listAlignment;
                                boolean is_Exit;
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userAlignmentList.add((Integer) dataList.get(i + 6));
                                }
                                String userAlignmentListStr = AppConfig.SERVER_IP;
                                userAlignmentListStr = dataList.get(0) + " " + dataList.get(1) + " " + dataList.get(2) + " ";
                                for (i = 0; i < this.userAlignmentList.size(); i++) {
                                    userAlignmentListStr = new StringBuilder(String.valueOf(userAlignmentListStr)).append(" ").append(this.userAlignmentList.get(i)).toString();
                                }
                                C0213L.m19i("userAlignmentListStr = " + userAlignmentListStr);
                                byte[] alignmentIDByte = new byte[4];
                                for (i = 0; i < 4; i++) {
                                    alignmentIDByte[i] = (byte) ((Integer) this.userAlignmentList.get(i)).intValue();
                                }
                                alignmentID = NormalFun.byteArrayToInt(alignmentIDByte);
                                if (alignmentID < 10) {
                                    alignmentID += 10;
                                }
                                List<DeviceStateDB> deviceStateDBList = AppUtils.databaseManager.findAllDeviceStateInfo();
                                is_exit = false;
                                for (i = 0; i < deviceStateDBList.size(); i++) {
                                    deviceStateDB = (DeviceStateDB) deviceStateDBList.get(i);
                                    if (deviceStateDB.deviceId.equals(deviceId)) {
                                        C0213L.m19i(" is exit ");
                                        is_exit = true;
                                        deviceStateDB.customAlgnmentId = alignmentID;
                                        AppUtils.databaseManager.modifyDeviceStateInfo(deviceStateDB);
                                        if (!is_exit) {
                                            C0213L.m19i(" is exit false");
                                            deviceStateDB = new DeviceStateDB();
                                            deviceStateDB.deviceId = deviceId;
                                            deviceStateDB.customAlgnmentId = alignmentID;
                                            deviceStateDB.alignmentId = alignmentID;
                                            AppUtils.databaseManager.addDeviceStateInfo(deviceStateDB);
                                        }
                                        AppUtils.nowKBCustomAlignmentID = alignmentID;
                                        is_dutyAlignmentData = true;
                                        if (this.userAlignmentList.size() == 144) {
                                            i = 0;
                                            while (i < 140) {
                                                if (((Integer) this.userAlignmentList.get(i + 4)).intValue() == 0) {
                                                    is_dutyAlignmentData = false;
                                                } else {
                                                    i++;
                                                }
                                            }
                                        }
                                        listAlignment = AppUtils.databaseManager.findAllKeyboardAlignment();
                                        is_Exit = false;
                                        for (i = 0; i < listAlignment.size(); i++) {
                                            if (alignmentID != ((KeyboardAlignment) listAlignment.get(i)).alignmentId) {
                                                is_Exit = true;
                                                if (!is_Exit || is_dutyAlignmentData) {
                                                    C0213L.m20i(TAG, " The Alignment is Exit!");
                                                } else {
                                                    C0213L.m20i(TAG, " NO Alignment And Add");
                                                    if (this.userAlignmentList.size() == 144) {
                                                        KeyboardAlignment keyboardAlignment = new KeyboardAlignment();
                                                        List<Integer> onlyAlignmentValueList = new ArrayList();
                                                        for (i = 0; i < 140; i++) {
                                                            onlyAlignmentValueList.add((Integer) this.userAlignmentList.get(i + 4));
                                                        }
                                                        keyboardAlignment = KeyboardAlignment.turnAlignment70To61(onlyAlignmentValueList);
                                                        keyboardAlignment.alignmentId = alignmentID;
                                                        keyboardAlignment.name = AppUtils.appUtils.getResources().getString(C0182R.string.layout_custom);
                                                        keyboardAlignment.type = KeyboardAlignment.CUSTOM_TYPE;
                                                        keyboardAlignment.content = AppConfig.SERVER_IP;
                                                        AppUtils.databaseManager.addAlignment(keyboardAlignment);
                                                    }
                                                }
                                                msg = new Message();
                                                msg.what = 2;
                                                this.handler.sendMessage(msg);
                                                return;
                                            }
                                        }
                                        if (is_Exit) {
                                        }
                                        C0213L.m20i(TAG, " The Alignment is Exit!");
                                        msg = new Message();
                                        msg.what = 2;
                                        this.handler.sendMessage(msg);
                                        return;
                                    }
                                }
                                if (is_exit) {
                                    C0213L.m19i(" is exit false");
                                    deviceStateDB = new DeviceStateDB();
                                    deviceStateDB.deviceId = deviceId;
                                    deviceStateDB.customAlgnmentId = alignmentID;
                                    deviceStateDB.alignmentId = alignmentID;
                                    AppUtils.databaseManager.addDeviceStateInfo(deviceStateDB);
                                }
                                AppUtils.nowKBCustomAlignmentID = alignmentID;
                                is_dutyAlignmentData = true;
                                if (this.userAlignmentList.size() == 144) {
                                    i = 0;
                                    while (i < 140) {
                                        if (((Integer) this.userAlignmentList.get(i + 4)).intValue() == 0) {
                                            i++;
                                        } else {
                                            is_dutyAlignmentData = false;
                                        }
                                    }
                                }
                                listAlignment = AppUtils.databaseManager.findAllKeyboardAlignment();
                                is_Exit = false;
                                while (i < listAlignment.size()) {
                                    if (alignmentID != ((KeyboardAlignment) listAlignment.get(i)).alignmentId) {
                                    } else {
                                        is_Exit = true;
                                        if (is_Exit) {
                                        }
                                        C0213L.m20i(TAG, " The Alignment is Exit!");
                                        msg = new Message();
                                        msg.what = 2;
                                        this.handler.sendMessage(msg);
                                        return;
                                    }
                                }
                                if (is_Exit) {
                                }
                                C0213L.m20i(TAG, " The Alignment is Exit!");
                                msg = new Message();
                                msg.what = 2;
                                this.handler.sendMessage(msg);
                                return;
                            } else {
                                return;
                            }
                        } else if (((Integer) dataList.get(2)).intValue() == 132) {
                            if (dataList.size() >= 4) {
                                alignmentID = ((Integer) dataList.get(3)).intValue();
                                AppUtils.nowUseAlignmentID = alignmentID;
                                C0213L.m19i("alignmentID = " + alignmentID);
                                intent = new Intent(ACTION_DATA_FLUSHUI);
                                intent.putExtra(EXTRA_DATA, alignmentID);
                                intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_ALIGNMENT);
                                sendBroadcast(intent);
                                return;
                            }
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 130) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_KEYBOARD);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_ALIGNMENT_SET);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 131) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_KEYBOARD);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_ALIGNMENT_SET);
                            sendBroadcast(intent);
                            return;
                        } else {
                            return;
                        }
                    case 9:
                        byte[] lightEffectIDByte;
                        if (((Integer) dataList.get(2)).intValue() == 137) {
                            nBlock = ((Integer) dataList.get(4)).intValue();
                            iBlock = ((Integer) dataList.get(5)).intValue();
                            datalen = ((Integer) dataList.get(3)).intValue();
                            if (iBlock == 0) {
                                this.userLighteffectList = new ArrayList();
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userLighteffectList.add((Integer) dataList.get(i + 6));
                                }
                                return;
                            } else if (iBlock < nBlock - 1) {
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userLighteffectList.add((Integer) dataList.get(i + 6));
                                }
                                return;
                            } else if (iBlock == nBlock - 1) {
                                boolean is_duty_lighteffectData;
                                for (i = 0; i < datalen - 2; i++) {
                                    this.userLighteffectList.add((Integer) dataList.get(i + 6));
                                }
                                String userLighteffectListStr = AppConfig.SERVER_IP;
                                userLighteffectListStr = dataList.get(0) + " " + dataList.get(1) + " " + dataList.get(2) + " ";
                                for (i = 0; i < this.userLighteffectList.size(); i++) {
                                    userLighteffectListStr = new StringBuilder(String.valueOf(userLighteffectListStr)).append(" ").append(this.userLighteffectList.get(i)).toString();
                                }
                                C0213L.m19i("userLighteffectList = " + userLighteffectListStr);
                                lightEffectIDByte = new byte[4];
                                for (i = 0; i < 4; i++) {
                                    lightEffectIDByte[i] = (byte) ((Integer) this.userLighteffectList.get(i)).intValue();
                                }
                                int lightEffectID = NormalFun.byteArrayToInt(lightEffectIDByte);
                                List<KeyboardLightEffectDB> keyboardLightEffectDBList = AppUtils.databaseManager.findAllKeyboardLightEffectDB();
                                int keyboardLightEffectDBListlen = keyboardLightEffectDBList.size();
                                boolean is_ExitLightEffect = false;
                                for (i = 0; i < keyboardLightEffectDBListlen; i++) {
                                    if (lightEffectID == ((KeyboardLightEffectDB) keyboardLightEffectDBList.get(i)).lightEffectID) {
                                        is_ExitLightEffect = true;
                                        is_duty_lighteffectData = true;
                                        if (this.userLighteffectList.size() == 214) {
                                            i = 0;
                                            while (i < 210) {
                                                if (((Integer) this.userLighteffectList.get(i + 4)).intValue() == 0) {
                                                    is_duty_lighteffectData = false;
                                                } else {
                                                    i++;
                                                }
                                            }
                                        }
                                        if (!is_ExitLightEffect || is_duty_lighteffectData) {
                                            C0213L.m20i(TAG, " The Lighteffect is Exit!");
                                        } else if (this.userLighteffectList.size() == 214) {
                                            C0213L.m20i(TAG, " NO Lighteffect And Add");
                                            KeyboardLightEffectDB keyboardLightEffectDB = new KeyboardLightEffectDB();
                                            keyboardLightEffectDB.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_title_user);
                                            keyboardLightEffectDB.type = KeyboardLightEffect.LIGHTEFFECT_TYPE_CUSTOM_STATIC;
                                            List<Integer> dataList210 = new ArrayList();
                                            for (i = 0; i < 210; i++) {
                                                dataList210.add((Integer) this.userLighteffectList.get(i + 4));
                                            }
                                            keyboardLightEffectDB.keycolor = KeyboardLightEffectDB.colorListToString(KeyboardLightEffect.turn210KeyColorTo61(dataList210));
                                            keyboardLightEffectDB.lightEffectID = lightEffectID;
                                            AppUtils.databaseManager.addKeyboardLightEffectDB(keyboardLightEffectDB);
                                        }
                                        msg = new Message();
                                        msg.what = 1;
                                        this.handler.sendMessage(msg);
                                        return;
                                    }
                                }
                                is_duty_lighteffectData = true;
                                if (this.userLighteffectList.size() == 214) {
                                    i = 0;
                                    while (i < 210) {
                                        if (((Integer) this.userLighteffectList.get(i + 4)).intValue() == 0) {
                                            i++;
                                        } else {
                                            is_duty_lighteffectData = false;
                                        }
                                    }
                                }
                                if (is_ExitLightEffect) {
                                }
                                C0213L.m20i(TAG, " The Lighteffect is Exit!");
                                msg = new Message();
                                msg.what = 1;
                                this.handler.sendMessage(msg);
                                return;
                            } else {
                                return;
                            }
                        } else if (((Integer) dataList.get(2)).intValue() == 129) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_LED);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_LED_SET);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 131) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_LED);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_LED_SET);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 136) {
                            ackData = -1;
                            if (dataList.size() >= 4) {
                                ackData = ((Integer) dataList.get(3)).intValue();
                            }
                            intent = new Intent(ACTION_DATA_LED);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_LED_GETNOWID);
                            sendBroadcast(intent);
                            return;
                        } else if (((Integer) dataList.get(2)).intValue() == 138) {
                            ackData = -1;
                            if (dataList.size() >= 7) {
                                lightEffectIDByte = new byte[4];
                                for (i = 0; i < 4; i++) {
                                    lightEffectIDByte[i] = (byte) ((Integer) dataList.get(i + 3)).intValue();
                                }
                                ackData = NormalFun.byteArrayToInt(lightEffectIDByte);
                            }
                            intent = new Intent(ACTION_DATA_LED);
                            intent.putExtra(EXTRA_DATA, ackData);
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_LED_GETNOW_CustomID);
                            sendBroadcast(intent);
                            return;
                        } else {
                            return;
                        }
                    case 11:
                        if (((Integer) dataList.get(1)).intValue() == 132) {
                            intent = new Intent(ACTION_DATA_FW_UPDATE);
                            intent.putExtra(EXTRA_UUID, characteristic.getUuid().toString());
                            intent.putExtra(EXTRA_DATA, characteristic.getValue());
                            intent.putExtra(EXTRA_TYPE, EXTRA_TYPE_FW_UPDATE_GET_VERSION_ACK);
                            sendBroadcast(intent);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private void setSyncCode() {
        String androidStr = new DeviceIdFactory(getInstance()).getAndroidId();
        byte[] data = new byte[8];
        int i;
        if (androidStr == null || androidStr.isEmpty() || androidStr.length() != 16) {
            for (i = 0; i < 8; i++) {
                data[i] = (byte) 0;
            }
        } else {
            for (i = 0; i < 8; i++) {
                String hexString = AppConfig.SERVER_IP;
                if (i == 7) {
                    hexString = androidStr.substring(14);
                } else {
                    hexString = androidStr.substring(i, i + 2);
                }
                data[i] = (byte) NormalFun.hexStringToInt(hexString);
            }
        }
        BLEHandle.setSyncCode(getInstance(), (BluetoothGattCharacteristic) getInstance().mOadService.getCharacteristics().get(1), data);
    }

    private boolean checkGatt() {
        if (this.mBtAdapter == null) {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return false;
        } else if (this.mBluetoothGatt == null) {
            Log.w(TAG, "BluetoothGatt not initialized");
            return false;
        } else if (!this.mBusy) {
            return true;
        } else {
            Log.w(TAG, "LeService busy");
            return false;
        }
    }

    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    public boolean onUnbind(Intent intent) {
        close();
        return super.onUnbind(intent);
    }

    public boolean initialize() {
        Log.d(TAG, "initialize");
        mThis = this;
        if (this.mBluetoothManager == null) {
            this.mBluetoothManager = (BluetoothManager) getSystemService("bluetooth");
            if (this.mBluetoothManager == null) {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }
        this.mBtAdapter = this.mBluetoothManager.getAdapter();
        if (this.mBtAdapter == null) {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }
        findBleDevice();
        return true;
    }

    public boolean onliyJudgeisConnectedOk() {
        if (device == null) {
            return false;
        }
        C0213L.m19i(" this.device != null ");
        int connState = this.mBluetoothManager.getConnectionState(device, 7);
        if (!(this.mBluetoothDeviceAddress == null || this.mBluetoothDeviceAddress.equals(device.getAddress()))) {
            connState = 0;
        }
        switch (connState) {
            case 2:
                if (this.mBluetoothGatt == null || this.mOadService == null || this.mOadService.getCharacteristics() == null || this.mOadService.getCharacteristics().size() < 2) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public boolean isConnectedOk() {
        boolean result = false;
        if (device != null) {
            C0213L.m19i(" this.device != null ");
            int connState = this.mBluetoothManager.getConnectionState(device, 7);
            if (!(this.mBluetoothDeviceAddress == null || this.mBluetoothDeviceAddress.equals(device.getAddress()))) {
                connState = 0;
            }
            switch (connState) {
                case 2:
                    if (!(this.mBluetoothGatt == null || this.mOadService == null || this.mOadService.getCharacteristics() == null || this.mOadService.getCharacteristics().size() < 2)) {
                        result = true;
                        break;
                    }
            }
        }
        if (!result) {
            C0213L.m19i(" result == false ");
            findBleDevice();
        }
        return result;
    }

    public void findBleDevice() {
        List<DeviceDB> listDeviceDB;
        int i;
        String macStringdb;
        Set<BluetoothDevice> pairedDevices = this.mBtAdapter.getBondedDevices();
        boolean is_devicelink = false;
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (selectMac == null || selectMac.isEmpty() || device.getAddress().substring(2).equals(selectMac.substring(2))) {
                    is_devicelink = false;
                    Log.i(AppConfig.SERVER_IP, "device.getAddress() = " + device.getAddress());
                    if (device.getName() != null && device.getName().length() == 15) {
                        if (device.getName().regionMatches(0, AppConfig.KEYBOARDNAME, 0, AppConfig.KEYBOARDNAME.length())) {
                            int connState = this.mBluetoothManager.getConnectionState(device, 7);
                            if (this.mBluetoothGatt == null || !(this.mBluetoothDeviceAddress == null || this.mBluetoothDeviceAddress.equals(device.getAddress()))) {
                                connState = 0;
                            }
                            switch (connState) {
                                case 0:
                                    this.mOadService = null;
                                    Log.i(AppConfig.SERVER_IP, "fuck tiosn STATE_DISCONNECTED ");
                                    if (!connect(device.getAddress())) {
                                        Log.i(AppConfig.SERVER_IP, "fuck tiosn !OK ");
                                        if (this.mBluetoothGatt != null) {
                                            this.mBluetoothGatt.close();
                                        }
                                        disconnect(device.getAddress());
                                        break;
                                    }
                                    is_devicelink = true;
                                    Log.i(AppConfig.SERVER_IP, "fuck tiosn OK ");
                                    if (this.mBluetoothGatt == null) {
                                        Log.i(AppConfig.SERVER_IP, "mBluetoothGatt == null ");
                                        break;
                                    }
                                    Log.i(AppConfig.SERVER_IP, "mBluetoothGatt != null ");
                                    if (getInstance().getNumServices() != 0 && this.mOadService != null) {
                                        Log.i(AppConfig.SERVER_IP, "BluetoothLeService.getInstance().getNumServices() != 0 ");
                                        break;
                                    }
                                    Log.i(AppConfig.SERVER_IP, "BluetoothLeService.getInstance().getNumServices() == 0 ");
                                    this.mBluetoothGatt.discoverServices();
                                    is_devicelink = false;
                                    break;
                                    break;
                                case 2:
                                    Log.i(AppConfig.SERVER_IP, "fuck tiosn STATE_CONNECTED ");
                                    is_devicelink = true;
                                    if (this.mBluetoothGatt != null) {
                                        if (getInstance().getNumServices() != 0 && this.mOadService != null) {
                                            C0213L.m19i("getNumServices() != 0");
                                            break;
                                        }
                                        C0213L.m19i("getNumServices() == 0");
                                        this.mBluetoothGatt.discoverServices();
                                        break;
                                    }
                                    break;
                            }
                        }
                        if (is_devicelink) {
                            device = device;
                            selectMac = device.getAddress();
                            listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
                            i = 0;
                            while (i < listDeviceDB.size()) {
                                macStringdb = ((DeviceDB) listDeviceDB.get(i)).macString;
                                if (macStringdb == null || macStringdb.length() <= 2 || !macStringdb.substring(2).equals(selectMac.substring(2))) {
                                    i++;
                                } else {
                                    deviceId = ((DeviceDB) listDeviceDB.get(i)).deviceId;
                                    Editor editor = getSharedPreferences(getPackageName(), 0).edit();
                                    editor.putString("DEVICEID", deviceId);
                                    editor.commit();
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!is_devicelink) {
            Toast.makeText(this, AppUtils.appUtils.getResources().getString(C0182R.string.connect_your_keyboard), 0).show();
        }
        if (deviceId != null && !deviceId.isEmpty()) {
            return;
        }
        if (selectMac == null || selectMac.isEmpty() || selectMac.length() <= 2) {
            deviceId = getSharedPreferences(getPackageName(), 0).getString("DEVICEID", AppConfig.SERVER_IP);
            return;
        }
        listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
        i = 0;
        while (i < listDeviceDB.size()) {
            macStringdb = ((DeviceDB) listDeviceDB.get(i)).macString;
            if (macStringdb == null || macStringdb.length() <= 2 || !macStringdb.substring(2).equals(selectMac.substring(2))) {
                i++;
            } else {
                deviceId = ((DeviceDB) listDeviceDB.get(i)).deviceId;
                editor = getSharedPreferences(getPackageName(), 0).edit();
                editor.putString("DEVICEID", deviceId);
                editor.commit();
                return;
            }
        }
    }

    public List<BLEKeyboard> findAllBleDevice() {
        List<BLEKeyboard> bleKeyboards = new ArrayList();
        Set<BluetoothDevice> pairedDevices = this.mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName().regionMatches(0, AppConfig.KEYBOARDNAME, 0, AppConfig.KEYBOARDNAME.length())) {
                    BLEKeyboard bleKeyboard = new BLEKeyboard();
                    bleKeyboard.name = device.getName();
                    bleKeyboard.mac = device.getAddress();
                    bleKeyboards.add(bleKeyboard);
                }
            }
        }
        return bleKeyboards;
    }

    public void setSelectMac(String mac) {
        if (selectMac != mac) {
            selectMac = mac;
            deviceId = AppConfig.SERVER_IP;
            if (device != null) {
                disconnect(device.getAddress());
            }
            findBleDevice();
        }
    }

    private void checkOad() {
        this.mOadService = null;
        List<BluetoothGattService> mServiceList = getSupportedGattServices();
        for (int i = 0; i < mServiceList.size() && this.mOadService == null; i++) {
            BluetoothGattService srv = (BluetoothGattService) mServiceList.get(i);
            if (srv.getUuid().equals(GattInfo.OAD_SERVICE_UUID)) {
                Log.i(AppConfig.SERVER_IP, " tison fuck  OAD_SERVICE_UUID");
                this.mOadService = srv;
            }
            if (srv.getUuid().equals(GattInfo.CC_SERVICE_UUID)) {
                Log.i(AppConfig.SERVER_IP, " tison fuck  CC_SERVICE_UUID");
            }
        }
        if (this.mOadService != null) {
            BLEHandle.configBLE(this, (BluetoothGattCharacteristic) this.mOadService.getCharacteristics().get(0));
            sendBroadcast(new Intent(ACTION_GATT_SERVICES_DISCOVERED_COMPLETE));
            if (is_sysnc != 1) {
                is_sysnc = 1;
                new Thread(new C01954()).start();
            }
        }
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Received start id " + startId + ": " + intent);
        initialize();
        return 1;
    }

    public void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog = null;
        }
        Log.d(TAG, "onDestroy() called");
        if (this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
        }
    }

    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (checkGatt()) {
            this.mBusy = true;
            this.mBluetoothGatt.readCharacteristic(characteristic);
            Log.i(AppConfig.SERVER_IP, "readCharacteristic readCharacteristic");
        }
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic, byte b) {
        if (!checkGatt()) {
            return false;
        }
        characteristic.setValue(new byte[]{b});
        this.mBusy = true;
        return this.mBluetoothGatt.writeCharacteristic(characteristic);
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic, boolean b) {
        if (!checkGatt()) {
            return false;
        }
        int i;
        byte[] val = new byte[1];
        if (b) {
            i = 1;
        } else {
            i = 0;
        }
        val[0] = (byte) i;
        characteristic.setValue(val);
        this.mBusy = true;
        return this.mBluetoothGatt.writeCharacteristic(characteristic);
    }

    public boolean writeCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (!checkGatt()) {
            return false;
        }
        this.mBusy = true;
        C0213L.m19i(" before  mBluetoothGatt.writeCharacteristic(characteristic) ");
        return this.mBluetoothGatt.writeCharacteristic(characteristic);
    }

    public int getNumServices() {
        if (this.mBluetoothGatt == null) {
            return 0;
        }
        return this.mBluetoothGatt.getServices().size();
    }

    public List<BluetoothGattService> getSupportedGattServices() {
        if (this.mBluetoothGatt == null) {
            return null;
        }
        return this.mBluetoothGatt.getServices();
    }

    public boolean setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enable) {
        if (!checkGatt()) {
            return false;
        }
        if (this.mBluetoothGatt.setCharacteristicNotification(characteristic, enable)) {
            BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(GattInfo.CLIENT_CHARACTERISTIC_CONFIG);
            if (clientConfig == null) {
                return false;
            }
            if (enable) {
                Log.i(TAG, "enable notification");
                clientConfig.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
            } else {
                Log.i(TAG, "disable notification");
                clientConfig.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
            }
            this.mBusy = true;
            return this.mBluetoothGatt.writeDescriptor(clientConfig);
        }
        Log.w(TAG, "setCharacteristicNotification failed");
        return false;
    }

    public boolean isNotificationEnabled(BluetoothGattCharacteristic characteristic) {
        if (!checkGatt()) {
            return false;
        }
        BluetoothGattDescriptor clientConfig = characteristic.getDescriptor(GattInfo.CLIENT_CHARACTERISTIC_CONFIG);
        if (clientConfig == null || clientConfig.getValue() != BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE) {
            return false;
        }
        return true;
    }

    public boolean connect(String address) {
        if (address == null) {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        BluetoothDevice device = this.mBtAdapter.getRemoteDevice(address);
        if (device == null) {
            Log.w(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        Log.d(TAG, "Create a new GATT connection.");
        if (this.mBluetoothGatt != null) {
            this.mBluetoothGatt.close();
            this.mBluetoothGatt.disconnect();
            this.mBluetoothGatt = null;
        }
        this.mBluetoothGatt = device.connectGatt(this, false, this.mGattCallbacks);
        this.mBluetoothDeviceAddress = address;
        int connState = this.mBluetoothManager.getConnectionState(device, 7);
        return true;
    }

    public void disconnect(String address) {
        if (this.mBtAdapter == null) {
            Log.w(TAG, "disconnect: BluetoothAdapter not initialized");
            return;
        }
        int connectionState = this.mBluetoothManager.getConnectionState(this.mBtAdapter.getRemoteDevice(address), 7);
        if (this.mBluetoothGatt != null) {
            Log.i(TAG, "disconnect");
            if (connectionState != 0) {
                this.mBluetoothGatt.disconnect();
            } else {
                Log.w(TAG, "Attempt to disconnect in state: " + connectionState);
            }
        }
    }

    public void close() {
        if (this.mBluetoothGatt != null) {
            Log.i(TAG, "close");
            this.mBluetoothGatt.close();
            this.mBluetoothGatt = null;
        }
    }

    public int numConnectedDevices() {
        if (this.mBluetoothGatt != null) {
            return this.mBluetoothManager.getConnectedDevices(7).size();
        }
        return 0;
    }

    public static BluetoothGatt getBtGatt() {
        return mThis.mBluetoothGatt;
    }

    public static BluetoothManager getBtManager() {
        return mThis.mBluetoothManager;
    }

    public static BluetoothLeService getInstance() {
        return mThis;
    }

    public boolean waitIdle(int i) {
        i /= 10;
        while (true) {
            i--;
            if (i > 0 && this.mBusy) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            return true;
        }
        return false;
    }
}
