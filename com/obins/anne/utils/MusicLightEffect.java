package com.obins.anne.utils;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.util.Log;
import java.io.IOException;
import ti.android.ble.common.BluetoothLeService;

public class MusicLightEffect {
    private static float DIVDELENTH = 15.0f;
    private static String TAG = "MusicLightEffect";
    private static float VALUEMAX = 127.0f;
    private float EVERYDIVDEVALUE = 8.0f;
    private String audioFilePath;
    private byte[] mBytes;
    private BluetoothGattCharacteristic mCharBlock;
    private boolean mFirst = true;
    private BluetoothLeService mLeService;
    private MediaPlayer mMediaPlayer;
    private Visualizer mVisualizer;

    class C01891 implements OnDataCaptureListener {
        C01891() {
        }

        public void onWaveFormDataCapture(Visualizer visualizer, byte[] bytes, int samplingRate) {
            Log.d(MusicLightEffect.TAG, "bytes === " + bytes + " ,,,,,samplingRate =====  " + samplingRate + ",len = " + bytes.length);
        }

        public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
            int len = fft.length;
            MusicLightEffect.this.updateVisualizer(fft);
            byte[] conversionDatafft = MusicLightEffect.this.conversionData(MusicLightEffect.this.mBytes);
            C0188L.m8i(MusicLightEffect.TAG, "fft.length = " + fft.length + ";conversionDatafft.length = " + conversionDatafft.length);
            BLEHandle.musicLightEffect(MusicLightEffect.this.mLeService, MusicLightEffect.this.mCharBlock, conversionDatafft);
        }
    }

    class C01902 implements OnCompletionListener {
        C01902() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
        }
    }

    public MusicLightEffect(BluetoothLeService mLeService, BluetoothGattCharacteristic mCharBlock, String audioFilePath) {
        this.mLeService = mLeService;
        this.mCharBlock = mCharBlock;
        this.audioFilePath = audioFilePath;
    }

    public void playMusicLightEffect(Context context) {
        this.mMediaPlayer = new MediaPlayer();
        try {
            if (this.audioFilePath != null && !this.audioFilePath.isEmpty()) {
                this.mMediaPlayer.setDataSource(this.audioFilePath);
                this.mMediaPlayer.prepare();
                int maxCR = Visualizer.getMaxCaptureRate();
                Log.d(TAG, "maxCR / 2= " + (maxCR / 2));
                this.mVisualizer = new Visualizer(this.mMediaPlayer.getAudioSessionId());
                Log.d(TAG, "Visualizer.getCaptureSizeRange()[1] === " + Visualizer.getCaptureSizeRange()[1]);
                this.mVisualizer.setCaptureSize(16);
                this.mVisualizer.setDataCaptureListener(new C01891(), maxCR, false, true);
                this.mVisualizer.setEnabled(true);
                this.mMediaPlayer.setOnCompletionListener(new C01902());
                this.mMediaPlayer.start();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void stopPlayMusicLightEffect() {
        this.mMediaPlayer.stop();
        this.mVisualizer.setEnabled(false);
    }

    public void updateVisualizer(byte[] fft) {
        byte[] model = new byte[16];
        model[0] = (byte) Math.abs(fft[0]);
        int i = 2;
        for (int j = 1; j < 16; j++) {
            model[j] = (byte) ((int) Math.hypot((double) fft[i], (double) fft[i + 1]));
            C0188L.m8i(TAG, "model[j] = " + model[j]);
            i += 2;
        }
        this.mBytes = model;
    }

    public byte[] conversionData(byte[] value) {
        int len = value.length;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++) {
            C0188L.m8i(TAG, "value[i] = " + value[i]);
            if (value[i] < (byte) 0) {
                result[i] = (byte) ((int) DIVDELENTH);
            } else {
                result[i] = (byte) ((int) (((float) value[i]) / this.EVERYDIVDEVALUE));
            }
            C0188L.m8i(TAG, "result[i] = " + result[i]);
            if (value[i] == (byte) 0) {
                result[i] = (byte) 0;
            } else if (((float) result[i]) < DIVDELENTH) {
                result[i] = (byte) (result[i] + 1);
            } else {
                result[i] = (byte) ((int) DIVDELENTH);
            }
            C0188L.m8i(TAG, "result[i==] = " + result[i]);
        }
        return result;
    }
}
