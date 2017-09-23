package ti.android.ble.common;

import android.bluetooth.BluetoothDevice;

public class BleDeviceInfo {
    private BluetoothDevice mBtDevice;
    private int mRssi;

    public BleDeviceInfo(BluetoothDevice device, int rssi) {
        this.mBtDevice = device;
        this.mRssi = rssi;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBtDevice;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public void updateRssi(int rssiValue) {
        this.mRssi = rssiValue;
    }
}
