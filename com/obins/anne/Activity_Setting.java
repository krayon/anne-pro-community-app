package com.obins.anne;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.DeviceDB;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.NormalFun;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;

public class Activity_Setting extends BaseActivity {
    private static final String TAG = "Activity_Setting";
    private MyAdapter adapter1 = null;
    private MyAdapter adapter2 = null;
    private RelativeLayout backBtn;
    private int clickNum = 0;
    private long lastClickTime = 0;
    private List<ItemObject> listObject1 = null;
    private List<ItemObject> listObject2 = null;
    private ProgressDialog progressDialog;
    private ListView settingsListView1 = null;
    private ListView settingsListView2 = null;

    class C01431 implements OnClickListener {
        C01431() {
        }

        public void onClick(View arg0) {
            Activity_Setting.this.finish();
        }
    }

    public final class ItemObject {
        public String contentString;
        public int imagesource;
        public String titleString;
    }

    public class MyAdapter extends BaseAdapter {
        private List<ItemObject> data;
        private int listViewNum;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<ItemObject> data, int num) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
            this.listViewNum = num;
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_setting_listitem, null);
                holder.logoImageView = (ImageView) convertView.findViewById(C0182R.id.logoImageView);
                holder.titleTextView = (TextView) convertView.findViewById(C0182R.id.titleTextView);
                holder.contentTextView = (TextView) convertView.findViewById(C0182R.id.contentTextView);
                holder.diver_View = convertView.findViewById(C0182R.id.diver_View);
                holder.arrow_TextView = (ImageView) convertView.findViewById(C0182R.id.arrow_TextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.logoImageView.setBackgroundResource(((ItemObject) this.data.get(position)).imagesource);
            holder.titleTextView.setText(((ItemObject) this.data.get(position)).titleString);
            holder.contentTextView.setText(((ItemObject) this.data.get(position)).contentString);
            if (position == 0) {
                holder.diver_View.setVisibility(4);
            } else {
                holder.diver_View.setVisibility(0);
            }
            if (this.listViewNum == 1 && position == 0) {
                holder.arrow_TextView.setVisibility(4);
            } else {
                holder.arrow_TextView.setVisibility(0);
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (MyAdapter.this.listViewNum == 1) {
                        if (position == 0) {
                            if (Activity_Setting.this.lastClickTime == 0) {
                                Activity_Setting.this.clickNum = 0;
                                Activity_Setting.this.lastClickTime = System.currentTimeMillis();
                            } else {
                                long disTime = System.currentTimeMillis() - Activity_Setting.this.lastClickTime;
                                Activity_Setting.this.lastClickTime = System.currentTimeMillis();
                                if (disTime <= 500) {
                                    Activity_Setting access$1 = Activity_Setting.this;
                                    access$1.clickNum = access$1.clickNum + 1;
                                } else {
                                    Activity_Setting.this.clickNum = 0;
                                }
                            }
                            if (Activity_Setting.this.clickNum == 10) {
                                Activity_Setting.this.clickNum = 0;
                                Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_MusicList.class));
                            }
                        } else if (position == 1) {
                            Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_AboutDevice.class));
                        } else if (position == 2) {
                            Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_Mode.class));
                        }
                    } else if (position == 0) {
                        Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_Setting_DeviceList.class));
                    } else if (position == 1) {
                        Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_APPVersion.class));
                    } else if (position == 2) {
                        Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_Manual.class));
                    } else {
                        Activity_Setting.this.startActivity(new Intent(Activity_Setting.this, Activity_Aboutobins.class));
                    }
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        private ImageView arrow_TextView;
        public TextView contentTextView;
        private View diver_View;
        public ImageView logoImageView;
        public TextView titleTextView;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_setting);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01431());
        this.settingsListView1 = (ListView) findViewById(C0182R.id.settingsListView1);
        this.settingsListView1.setHeaderDividersEnabled(true);
        this.settingsListView2 = (ListView) findViewById(C0182R.id.settingsListView2);
        this.settingsListView2.setHeaderDividersEnabled(true);
        final BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
        if (mBluetoothLeService.isConnectedOk()) {
            BLEHandle.getMCUVersion(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), 1);
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    BLEHandle.getMCUVersion(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), 2);
                    try {
                        Thread.sleep(50);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    BLEHandle.getMCUVersion(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), 3);
                    try {
                        Thread.sleep(50);
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    BLEHandle.getDeviceId(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1));
                }
            }).start();
        }
    }

    private void initdata() {
    }

    public void onResume() {
        if (AppUtils.setting_devicelist_finish_flag == 1) {
            AppUtils.setting_devicelist_finish_flag = 0;
            finish();
        }
        super.onResume();
        flushUI();
    }

    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    private void flushUI() {
        ItemObject itemObject1 = new ItemObject();
        itemObject1.imagesource = C0182R.drawable.set_device_type;
        itemObject1.titleString = getResources().getString(C0182R.string.setting_device_model);
        itemObject1.contentString = getResources().getString(C0182R.string.unknow);
        List<DeviceDB> listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
        for (int i = 0; i < listDeviceDB.size(); i++) {
            DeviceDB deviceDB = (DeviceDB) listDeviceDB.get(i);
            if (!(deviceDB.deviceId == null || deviceDB.deviceId.isEmpty() || BluetoothLeService.deviceId == null || BluetoothLeService.deviceId.isEmpty() || !deviceDB.deviceId.equals(BluetoothLeService.deviceId))) {
                if (deviceDB.deviceModel == 2) {
                    itemObject1.contentString = "ANNE PRO";
                } else if (deviceDB.deviceModel == 1) {
                    itemObject1.contentString = "ANNE";
                }
            }
        }
        ItemObject itemObject2 = new ItemObject();
        itemObject2.imagesource = C0182R.drawable.set_device_info;
        itemObject2.titleString = getResources().getString(C0182R.string.setting_device_info);
        itemObject2.contentString = AppConfig.SERVER_IP;
        ItemObject itemObject3 = new ItemObject();
        itemObject3.imagesource = C0182R.drawable.set_device_mode;
        itemObject3.titleString = getResources().getString(C0182R.string.setting_run_mode);
        itemObject3.contentString = AppConfig.SERVER_IP;
        String devideName = AppConfig.SERVER_IP;
        if (BluetoothLeService.device != null) {
            devideName = BluetoothLeService.device.getName();
        }
        if (devideName == null || devideName.length() <= 11) {
            Toast.makeText(this, getResources().getString(C0182R.string.connect_your_keyboard), 0).show();
            itemObject3.contentString = getResources().getString(C0182R.string.unknow);
        } else if (BluetoothLeService.device.getName().substring(0, 10).equals(AppConfig.KEYBOARDNAME_COMP_OFF)) {
            itemObject3.contentString = getResources().getString(C0182R.string.setting_mode_normal);
        } else {
            itemObject3.contentString = getResources().getString(C0182R.string.setting_mode_com);
        }
        this.listObject1 = new ArrayList();
        this.listObject1.clear();
        this.listObject1.add(itemObject1);
        this.listObject1.add(itemObject2);
        this.listObject1.add(itemObject3);
        this.adapter1 = new MyAdapter(this, this.listObject1, 1);
        this.settingsListView1.addFooterView(new ViewStub(this));
        this.settingsListView1.setAdapter(this.adapter1);
        this.adapter1.notifyDataSetChanged();
        ItemObject itemObject4 = new ItemObject();
        itemObject4.imagesource = C0182R.drawable.set_app;
        itemObject4.titleString = getResources().getString(C0182R.string.setting_app_version);
        itemObject4.contentString = AppConfig.SERVER_IP;
        itemObject4.contentString = "V " + NormalFun.getVerCode(this);
        ItemObject itemObject5 = new ItemObject();
        itemObject5.imagesource = C0182R.drawable.set_online_datasheet;
        itemObject5.titleString = getResources().getString(C0182R.string.setting_online_manual);
        itemObject5.contentString = AppConfig.SERVER_IP;
        ItemObject itemObject6 = new ItemObject();
        itemObject6.imagesource = C0182R.drawable.set_about_us;
        itemObject6.titleString = getResources().getString(C0182R.string.setting_about_obins);
        itemObject6.contentString = AppConfig.SERVER_IP;
        ItemObject itemObject7 = new ItemObject();
        itemObject7.imagesource = C0182R.drawable.set_devcie_list;
        itemObject7.titleString = getResources().getString(C0182R.string.setting_device_list);
        itemObject7.contentString = AppConfig.SERVER_IP;
        this.listObject2 = new ArrayList();
        this.listObject2.clear();
        this.listObject2.add(itemObject7);
        this.listObject2.add(itemObject4);
        this.listObject2.add(itemObject5);
        this.listObject2.add(itemObject6);
        this.adapter2 = new MyAdapter(this, this.listObject2, 2);
        this.settingsListView2.addFooterView(new ViewStub(this));
        this.settingsListView2.setAdapter(this.adapter2);
        this.adapter2.notifyDataSetChanged();
    }
}
