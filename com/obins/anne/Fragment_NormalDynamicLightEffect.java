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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.DensityUtil;
import com.obins.anne.utils.KeyboardLightEffect;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import com.obins.anne.viewpart.KeyboardView;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Fragment_NormalDynamicLightEffect extends Fragment {
    private static final int SET_LIGHTEFFECT_BACK = 1;
    private static final int SET_LIGHTEFFECT_TIMEOUT = 0;
    private static final String TAG = "Fragment_NormalDynamicLightEffect";
    private static int setLighteffectFlag = -1;
    private MyAdapter adapter = null;
    private List<KeyboardLightEffect> listKeyboardLightEffect = null;
    private ListView listView;
    private final BroadcastReceiver mGattUpdateReceiver = new C01702();
    public Handler mHandler = new C01691();
    private IntentFilter mIntentFilter;
    private ProgressDialog progressDialog;

    class C01691 extends Handler {
        C01691() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (Fragment_NormalDynamicLightEffect.this.progressDialog.isShowing()) {
                        Fragment_NormalDynamicLightEffect.this.progressDialog.cancel();
                    }
                    if (Fragment_NormalDynamicLightEffect.setLighteffectFlag == 1) {
                        Toast.makeText(Fragment_NormalDynamicLightEffect.this.getActivity(), Fragment_NormalDynamicLightEffect.this.getActivity().getResources().getString(C0182R.string.control_success), 0).show();
                        return;
                    } else {
                        Toast.makeText(Fragment_NormalDynamicLightEffect.this.getActivity(), Fragment_NormalDynamicLightEffect.this.getActivity().getResources().getString(C0182R.string.control_faile), 0).show();
                        return;
                    }
                case 1:
                    int result = ((Integer) msg.obj).intValue();
                    Fragment_NormalDynamicLightEffect.setLighteffectFlag = 1;
                    return;
                default:
                    return;
            }
        }
    }

    class C01702 extends BroadcastReceiver {
        C01702() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Fragment_NormalDynamicLightEffect.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_LED.equals(action)) {
                String type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0213L.m20i(Fragment_NormalDynamicLightEffect.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_LED_SET)) {
                    int result = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, -1);
                    Message msg = new Message();
                    Integer resultInteger = new Integer(result);
                    msg.what = 1;
                    msg.obj = resultInteger;
                    Fragment_NormalDynamicLightEffect.this.mHandler.sendMessage(msg);
                }
            }
        }
    }

    public class MyAdapter extends BaseAdapter {
        private List<KeyboardLightEffect> data;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<KeyboardLightEffect> data) {
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
                convertView = this.mInflater.inflate(C0182R.layout.lighteffect_listitem, null);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.select_CircleView = (RelativeLayout) convertView.findViewById(C0182R.id.select_CircleView);
                holder.keyboard_LinearLayout = (LinearLayout) convertView.findViewById(C0182R.id.keyboard_LinearLayout);
                holder.edit_ImageView = (ImageView) convertView.findViewById(C0182R.id.edit_ImageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            KeyboardLightEffect keyboardLightEffect = (KeyboardLightEffect) this.data.get(position);
            holder.name_TextView.setText(keyboardLightEffect.name);
            if (ActivityFragment_LightEffect.noarmalDynamicSelected != position) {
                holder.select_CircleView.setVisibility(4);
                holder.edit_ImageView.setVisibility(4);
            } else {
                holder.select_CircleView.setVisibility(0);
                holder.edit_ImageView.setVisibility(0);
            }
            if (position == 0) {
                holder.edit_ImageView.setVisibility(4);
            }
            LayoutParams keyboardView_linearParams = (LayoutParams) holder.keyboard_LinearLayout.getLayoutParams();
            keyboardView_linearParams.height = (int) (((((float) DensityUtil.getScreenWidthPixels(Fragment_NormalDynamicLightEffect.this.getActivity())) - (2.0f * DensityUtil.dip2px(Fragment_NormalDynamicLightEffect.this.getActivity(), 10.0f))) / 15.0f) * 5.0f);
            holder.keyboard_LinearLayout.setLayoutParams(keyboardView_linearParams);
            new KeyboardView(holder.keyboard_LinearLayout).setColor(keyboardLightEffect.lightColorDataList);
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (Fragment_NormalDynamicLightEffect.this.progressDialog == null || !Fragment_NormalDynamicLightEffect.this.progressDialog.isShowing()) {
                        ActivityFragment_LightEffect.cancleAllSelected();
                        ActivityFragment_LightEffect.noarmalDynamicSelected = position;
                        ((ActivityFragment_LightEffect) Fragment_NormalDynamicLightEffect.this.getActivity()).flushUI();
                        if (BluetoothLeService.getInstance().isConnectedOk()) {
                            Fragment_NormalDynamicLightEffect.setLighteffectFlag = -1;
                            Fragment_NormalDynamicLightEffect.this.progressDialog = ProgressDialog.show(Fragment_NormalDynamicLightEffect.this.getActivity(), AppConfig.SERVER_IP, Fragment_NormalDynamicLightEffect.this.getActivity().getResources().getString(C0182R.string.control_light_now));
                            final int i = position;
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        if (AppUtils.is_first_or_close_Lighteffect) {
                                            Thread.sleep(2000);
                                        } else {
                                            Thread.sleep(600);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    if (i == 0) {
                                        AppUtils.is_first_or_close_Lighteffect = true;
                                    } else {
                                        AppUtils.is_first_or_close_Lighteffect = false;
                                    }
                                    Message message = new Message();
                                    message.what = 0;
                                    Fragment_NormalDynamicLightEffect.this.mHandler.sendMessage(message);
                                }
                            }).start();
                            if (position == 0) {
                                BLEHandle.normalLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), 0);
                                return;
                            }
                            BLEHandle.normalLightEffect(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), position + 12);
                        }
                    }
                }
            });
            holder.edit_ImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent mIntent = new Intent(Fragment_NormalDynamicLightEffect.this.getActivity(), ActivityFragment_CustomLightEffect.class);
                    KeyboardLightEffect keyboardLightEffect = new KeyboardLightEffect();
                    keyboardLightEffect.name = ((KeyboardLightEffect) MyAdapter.this.data.get(position)).name;
                    keyboardLightEffect.lighteffectType = ((KeyboardLightEffect) MyAdapter.this.data.get(position)).lighteffectType;
                    keyboardLightEffect.lightColorDataList = new ArrayList();
                    keyboardLightEffect.lightColorDataList.addAll(((KeyboardLightEffect) MyAdapter.this.data.get(position)).lightColorDataList);
                    AppUtils.keyboardLightEffect = keyboardLightEffect;
                    Fragment_NormalDynamicLightEffect.this.startActivity(mIntent);
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public ImageView edit_ImageView;
        public LinearLayout keyboard_LinearLayout;
        public TextView name_TextView;
        public RelativeLayout select_CircleView;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_normaldynamiclighteffect, new RelativeLayout(getActivity()), true);
        initView(v);
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_LED);
        return v;
    }

    private void initView(View v) {
        this.listView = (ListView) v.findViewById(C0182R.id.listView);
        this.listKeyboardLightEffect = KeyboardLighteffectUtil.normalDynamicKeyboardLightEffectList;
        this.adapter = new MyAdapter(getActivity(), this.listKeyboardLightEffect);
        this.listView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        flushUI();
    }

    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        getActivity().unregisterReceiver(this.mGattUpdateReceiver);
    }

    public void flushUI() {
        if (this.adapter != null) {
            this.adapter.notifyDataSetChanged();
        }
    }
}
