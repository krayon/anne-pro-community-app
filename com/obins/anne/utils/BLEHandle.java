package com.obins.anne.utils;

import android.bluetooth.BluetoothGattCharacteristic;
import com.obins.anne.utils.SendBigBLEData.OnFinishListener;
import ti.android.ble.common.BluetoothLeService;

public class BLEHandle {
    private static final int GATT_WRITE_TIMEOUT = 100;
    private static final String TAG = "BLEHandle";

    public static boolean judgeBLEconnect(BluetoothLeService mBluetoothLeService) {
        return mBluetoothLeService.isConnectedOk();
    }

    public static void getDeviceId(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 2, (byte) 1, (byte) 1});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void setIsNeedSyncCode(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, byte[] data) {
        byte[] mOadBuffer = new byte[11];
        mOadBuffer[0] = (byte) 2;
        mOadBuffer[1] = (byte) 9;
        mOadBuffer[2] = (byte) 8;
        for (int i = 0; i < 8; i++) {
            mOadBuffer[i + 3] = data[i];
        }
        mCharBlock.setValue(mOadBuffer);
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void setSyncCode(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, byte[] data) {
        byte[] mOadBuffer = new byte[11];
        mOadBuffer[0] = (byte) 2;
        mOadBuffer[1] = (byte) 9;
        mOadBuffer[2] = (byte) 9;
        for (int i = 0; i < 8; i++) {
            mOadBuffer[i + 3] = data[i];
        }
        mCharBlock.setValue(mOadBuffer);
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getNowUseLightEffectId(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 9, (byte) 1, (byte) 8});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getCostomLightEffectData(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 9, (byte) 1, (byte) 9});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getCostomLightEffectID(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 9, (byte) 1, (byte) 10});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void setCustomLightEffect(BluetoothLeService mLeService, byte[] data) {
        new SendBigBLEData(mLeService, new byte[]{(byte) 9, (byte) -41, (byte) 3}, data).sendData();
    }

    public static void normalLightEffect(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int styleId) {
        mCharBlock.setValue(new byte[]{(byte) 9, (byte) 2, (byte) 1, (byte) styleId});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void ctlLightEffect(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int speed, int brightness, int director) {
        mCharBlock.setValue(new byte[]{(byte) 9, (byte) 4, (byte) 2, (byte) speed, (byte) brightness, (byte) director});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void musicLightEffect(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, byte[] fft) {
        byte[] mOadBuffer = new byte[17];
        mOadBuffer[0] = (byte) 9;
        mOadBuffer[1] = (byte) 15;
        mOadBuffer[2] = (byte) 6;
        for (int i = 0; i < 14; i++) {
            mOadBuffer[i + 3] = fft[i];
        }
        mCharBlock.setValue(mOadBuffer);
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void gameLightEffect(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int styleId) {
    }

    public static void getUserAlignment(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 7, (byte) 1, (byte) 5});
        if (mLeService.writeCharacteristic(mCharBlock)) {
            C0188L.m7i(" success !!");
        } else {
            C0188L.m7i(" fail    !!");
        }
    }

    public static void getAlignmentId(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 7, (byte) 1, (byte) 4});
        if (mLeService.writeCharacteristic(mCharBlock)) {
            C0188L.m7i("getAlignmentId success!!");
        } else {
            C0188L.m7i("getAlignmentId fail!!");
        }
    }

    public static void setAlignment(BluetoothLeService mLeService, byte[] data) {
        new SendBigBLEData(mLeService, new byte[]{(byte) 7, (byte) -111, (byte) 2}, data).sendData();
    }

    public static void setAlignmentWithId(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int id) {
        mCharBlock.setValue(new byte[]{(byte) 7, (byte) 2, (byte) 3, (byte) id});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void startRecordMacro(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 5, (byte) 1, (byte) 1});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void stopRecordMacro(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 5, (byte) 1, (byte) 2});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void downLoadMacro(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, byte[] data, OnFinishListener listener) {
        if (data.length > 17) {
            SendBigBLEData sendBigBLEData = new SendBigBLEData(mLeService, new byte[]{(byte) 5, (byte) (data.length + 1), (byte) 4}, data);
            sendBigBLEData.sendData();
            sendBigBLEData.setOnFinishListener(listener);
            return;
        }
        byte[] mOadBuffer = new byte[(data.length + 3)];
        mOadBuffer[0] = (byte) 5;
        mOadBuffer[1] = (byte) (data.length + 1);
        mOadBuffer[2] = (byte) 4;
        for (int i = 0; i < data.length; i++) {
            mOadBuffer[i + 3] = data[i];
        }
        mCharBlock.setValue(mOadBuffer);
        boolean success = mLeService.writeCharacteristic(mCharBlock);
        if (listener != null) {
            listener.OnFinsh();
        }
    }

    public static void getMarcoFromKB(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int idex) {
        mCharBlock.setValue(new byte[]{(byte) 5, (byte) 2, (byte) 5, (byte) idex});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void configBLE(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharIdentify) {
        if (enableNotification(mLeService, mCharIdentify, true)) {
            C0188L.m7i("enableNotification OK!");
        } else {
            C0188L.m7i("enableNotification ERRO!");
        }
    }

    public static boolean writeCharacteristic(BluetoothLeService mLeService, BluetoothGattCharacteristic c, byte v) {
        boolean ok = mLeService.writeCharacteristic(c, v);
        if (ok) {
            return mLeService.waitIdle(GATT_WRITE_TIMEOUT);
        }
        return ok;
    }

    public static boolean enableNotification(BluetoothLeService mLeService, BluetoothGattCharacteristic c, boolean enable) {
        boolean ok = mLeService.setCharacteristicNotification(c, enable);
        if (ok) {
            return mLeService.waitIdle(GATT_WRITE_TIMEOUT);
        }
        return ok;
    }

    public static void getMacAddress(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 10, (byte) 0});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getFWVersion(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        mCharBlock.setValue(new byte[]{(byte) 10, (byte) 0});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getTargetImageInfo(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        boolean ok = enableNotification(mLeService, mCharBlock, true);
        if (ok) {
            C0188L.m7i("enableNotification ok!!!");
            ok = writeCharacteristic(mLeService, mCharBlock, (byte) 0);
        } else {
            C0188L.m7i("enableNotification fail!!!");
        }
        if (ok) {
            C0188L.m7i("enableNotification ok!!!");
            ok = writeCharacteristic(mLeService, mCharBlock, (byte) 1);
            return;
        }
        C0188L.m7i("enableNotification fail!!!");
    }

    public static void setMode(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int mode) {
        mCharBlock.setValue(new byte[]{(byte) 6, (byte) 2, (byte) 12, (byte) mode});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getBLEHostList(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock) {
        byte[] mOadBuffer = new byte[4];
        mOadBuffer[0] = (byte) 6;
        mOadBuffer[1] = (byte) 1;
        mOadBuffer[2] = (byte) 6;
        mCharBlock.setValue(mOadBuffer);
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }

    public static void getMCUVersion(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, int idex) {
        mCharBlock.setValue(new byte[]{(byte) 2, (byte) 2, (byte) 5, (byte) idex});
        boolean success = mLeService.writeCharacteristic(mCharBlock);
    }
}
