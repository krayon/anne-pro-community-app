package com.obins.anne.utils;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import ti.android.ble.common.BluetoothLeService;

public class SendBigBLEData {
    private static final int OAD_BLOCK_SIZE = 14;
    private static final int OAD_BUFFER_SIZE = 20;
    private static final int PKT_INTERVAL = 40;
    private static final String TAG = "SendBigBLEData";
    private byte[] data;
    private OnFinishListener listener;
    private BluetoothGattCharacteristic mCharBlock = null;
    private List<BluetoothGattCharacteristic> mCharListOad;
    private BluetoothLeService mLeService;
    private final byte[] mOadBuffer = new byte[20];
    private BluetoothGattService mOadService;
    private ProgInfo mProgInfo;
    private boolean mProgramming = false;
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private byte[] protocolHead;

    public interface OnFinishListener {
        void OnFinsh();
    }

    private class ProgInfo {
        public int iBlocks;
        public int iBytes;
        public int nBlocks;
        public int nBytes;

        private ProgInfo() {
            this.iBytes = 0;
            this.nBytes = 0;
            this.iBlocks = 0;
            this.nBlocks = 0;
        }
    }

    private class ProgTimerTask extends TimerTask {
        private ProgTimerTask() {
        }

        public void run() {
            if (SendBigBLEData.this.mProgramming) {
                SendBigBLEData.this.onBlockTimer();
            }
        }
    }

    public SendBigBLEData(BluetoothLeService mLeService, byte[] protocolHead, byte[] data) {
        this.protocolHead = protocolHead;
        this.data = data;
        this.mLeService = mLeService;
        this.mOadService = mLeService.mOadService;
        this.mCharListOad = this.mOadService.getCharacteristics();
        this.mCharBlock = (BluetoothGattCharacteristic) this.mCharListOad.get(1);
        this.mProgInfo = new ProgInfo();
        this.mProgInfo.nBytes = data.length;
        this.mProgInfo.nBlocks = this.mProgInfo.nBytes / 14;
        if (this.mProgInfo.nBytes > this.mProgInfo.nBlocks * 14) {
            ProgInfo progInfo = this.mProgInfo;
            progInfo.nBlocks++;
        }
        this.mProgInfo.iBytes = 0;
        this.mProgInfo.iBlocks = 0;
        this.listener = null;
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }

    public void sendData() {
        this.mProgramming = true;
        this.mTimer = null;
        this.mTimer = new Timer();
        this.mTimerTask = new ProgTimerTask();
        this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0, 40);
    }

    private void onBlockTimer() {
        C0188L.m8i(TAG, "mProgInfo.iBlocks = " + this.mProgInfo.iBlocks);
        C0188L.m8i(TAG, "mProgInfo.nBlocks = " + this.mProgInfo.nBlocks);
        if (this.mProgInfo.iBlocks < this.mProgInfo.nBlocks) {
            int protocolHeadLen = this.protocolHead.length;
            for (int i = 0; i < protocolHeadLen; i++) {
                this.mOadBuffer[i] = this.protocolHead[i];
            }
            if (this.mProgInfo.iBytes + 14 < this.mProgInfo.nBytes) {
                this.mOadBuffer[3] = (byte) 16;
            } else {
                this.mOadBuffer[3] = (byte) ((this.mProgInfo.nBytes - this.mProgInfo.iBytes) + 2);
            }
            this.mOadBuffer[4] = (byte) this.mProgInfo.nBlocks;
            this.mOadBuffer[5] = (byte) this.mProgInfo.iBlocks;
            System.arraycopy(this.data, this.mProgInfo.iBytes, this.mOadBuffer, 6, this.mOadBuffer[3] - 2);
            this.mCharBlock.setValue(this.mOadBuffer);
            if (this.mLeService.writeCharacteristic(this.mCharBlock)) {
                ProgInfo progInfo = this.mProgInfo;
                progInfo.iBlocks++;
                progInfo = this.mProgInfo;
                progInfo.iBytes += 14;
            } else if (BluetoothLeService.getBtGatt() == null) {
                this.mProgramming = false;
            }
        } else {
            this.mProgramming = false;
        }
        if (!this.mProgramming) {
            this.mTimer.cancel();
            this.mTimer.purge();
            this.mTimerTask.cancel();
            this.mTimerTask = null;
            this.mProgramming = false;
            if (this.listener != null) {
                this.listener.OnFinsh();
            }
        }
    }
}
