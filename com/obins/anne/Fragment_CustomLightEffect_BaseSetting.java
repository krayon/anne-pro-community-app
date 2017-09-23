package com.obins.anne;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import ti.android.ble.common.BluetoothLeService;

public class Fragment_CustomLightEffect_BaseSetting extends Fragment {
    private static final String TAG = "Fragment_CustomLightEffect_BaseSetting";
    private SeekBar brightness_SeekBar;
    private TextView brightness_TextView;
    private int brightness_data = 0;
    private SeekBar direction_SeekBar;
    private TextView direction_TextView;
    private RelativeLayout direction_outside_RelativeLayout;
    private boolean isVisble = true;
    private SeekBar speed_SeekBar;
    private TextView speed_TextView;
    private int speed_data = 0;
    private RelativeLayout speed_outside_RelativeLayout;

    class C01541 implements OnSeekBarChangeListener {
        C01541() {
        }

        public void onStopTrackingTouch(SeekBar arg0) {
        }

        public void onStartTrackingTouch(SeekBar arg0) {
        }

        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            String data = new StringBuilder(String.valueOf(Fragment_CustomLightEffect_BaseSetting.this.getActivity().getResources().getString(C0182R.string.light_effect_basic_setting_brightness))).append("  ").append(arg1 + 1).toString();
            AppUtils.lighteffect_brightness_data = arg1 + 1;
            Fragment_CustomLightEffect_BaseSetting.this.brightness_TextView.setText(data);
            if (BluetoothLeService.getInstance().isConnectedOk()) {
                BLEHandle.ctlLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), Fragment_CustomLightEffect_BaseSetting.this.speed_SeekBar.getProgress() + 1, Fragment_CustomLightEffect_BaseSetting.this.brightness_SeekBar.getProgress() + 1, 0);
            }
        }
    }

    class C01552 implements OnSeekBarChangeListener {
        C01552() {
        }

        public void onStopTrackingTouch(SeekBar arg0) {
        }

        public void onStartTrackingTouch(SeekBar arg0) {
        }

        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            String data = "速率  " + (arg1 + 1);
            AppUtils.lighteffect_speed_data = arg1 + 1;
            Fragment_CustomLightEffect_BaseSetting.this.speed_TextView.setText(data);
            if (BluetoothLeService.getInstance().isConnectedOk()) {
                BLEHandle.ctlLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), Fragment_CustomLightEffect_BaseSetting.this.speed_SeekBar.getProgress() + 1, Fragment_CustomLightEffect_BaseSetting.this.brightness_SeekBar.getProgress() + 1, 0);
            }
        }
    }

    class C01563 implements OnSeekBarChangeListener {
        C01563() {
        }

        public void onStopTrackingTouch(SeekBar arg0) {
        }

        public void onStartTrackingTouch(SeekBar arg0) {
        }

        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            String dataStr = AppConfig.SERVER_IP;
            switch (arg1) {
                case 0:
                    dataStr = "上";
                    break;
                case 1:
                    dataStr = "下";
                    break;
                case 2:
                    dataStr = "左";
                    break;
                case 3:
                    dataStr = "右";
                    break;
            }
            Fragment_CustomLightEffect_BaseSetting.this.direction_TextView.setText("方向  " + dataStr);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_customlighteffect_basesetting, new RelativeLayout(getActivity()), true);
        initUI(v);
        return v;
    }

    private void initUI(View v) {
        this.speed_outside_RelativeLayout = (RelativeLayout) v.findViewById(C0182R.id.speed_outside_RelativeLayout);
        this.direction_outside_RelativeLayout = (RelativeLayout) v.findViewById(C0182R.id.direction_outside_RelativeLayout);
        this.brightness_SeekBar = (SeekBar) v.findViewById(C0182R.id.brightness_SeekBar);
        this.speed_SeekBar = (SeekBar) v.findViewById(C0182R.id.speed_SeekBar);
        this.direction_SeekBar = (SeekBar) v.findViewById(C0182R.id.direction_SeekBar);
        this.brightness_TextView = (TextView) v.findViewById(C0182R.id.brightness_TextView);
        this.speed_TextView = (TextView) v.findViewById(C0182R.id.speed_TextView);
        this.direction_TextView = (TextView) v.findViewById(C0182R.id.direction_TextView);
        if (AppUtils.lighteffect_brightness_data > 0) {
            this.brightness_data = AppUtils.lighteffect_brightness_data;
        } else {
            this.brightness_data = 5;
        }
        if (AppUtils.lighteffect_speed_data > 0) {
            this.speed_data = AppUtils.lighteffect_speed_data;
        } else {
            this.speed_data = 5;
        }
        this.brightness_TextView.setText(new StringBuilder(String.valueOf(getActivity().getResources().getString(C0182R.string.light_effect_basic_setting_brightness))).append("  ").append(this.brightness_data).toString());
        this.brightness_SeekBar.setProgress(this.brightness_data - 1);
        this.speed_TextView.setText("速率  " + this.speed_data);
        this.speed_SeekBar.setProgress(this.speed_data - 1);
        this.direction_SeekBar.setProgress(2);
        this.direction_TextView.setText("方向  ");
        this.direction_SeekBar.setVisibility(4);
        this.direction_TextView.setVisibility(4);
        if (!this.isVisble) {
            this.speed_SeekBar.setEnabled(false);
            this.direction_SeekBar.setEnabled(false);
            this.direction_SeekBar.setProgress(0);
            this.speed_SeekBar.setProgress(0);
            this.speed_TextView.setText("速率");
        }
        this.brightness_SeekBar.setOnSeekBarChangeListener(new C01541());
        this.speed_SeekBar.setOnSeekBarChangeListener(new C01552());
        this.direction_SeekBar.setOnSeekBarChangeListener(new C01563());
    }

    public void setInvisble() {
        this.isVisble = false;
    }
}
