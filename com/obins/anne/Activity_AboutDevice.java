package com.obins.anne;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.obins.anne.db.DeviceDB;
import com.obins.anne.utils.AppUtils;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Activity_AboutDevice extends BaseActivity {
    private MyAdapter adapter = null;
    private RelativeLayout backBtn;
    private int bleVersion;
    private String deviceId;
    private String deviceType;
    private int kMcuVersion;
    private List<ItemObject> listObject = null;
    private ListView listView = null;
    private int mainMcuVersion;

    class C00611 implements OnClickListener {
        C00611() {
        }

        public void onClick(View arg0) {
            Activity_AboutDevice.this.finish();
        }
    }

    public final class ItemObject {
        public String contentString;
        public int imagesource;
        public String titleString;
    }

    public class MyAdapter extends BaseAdapter {
        private List<ItemObject> data;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<ItemObject> data) {
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_aboutdevice_listitem, null);
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
            if (position == 0 || position == 1 || position == 2 || position == 3) {
                holder.arrow_TextView.setVisibility(4);
            } else {
                holder.arrow_TextView.setVisibility(0);
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (position == 4) {
                        Activity_AboutDevice.this.startActivity(new Intent(Activity_AboutDevice.this, Activity_FwUpdate.class));
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
        setContentView(C0182R.layout.activity_aboutdevice);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00611());
        initdata();
        initUI();
    }

    private void initdata() {
        boolean is_Exit = false;
        List<DeviceDB> listDeviceDB = AppUtils.databaseManager.findAllDeviceInfo();
        C0213L.m19i("listDeviceDB.size() = " + listDeviceDB.size());
        for (int i = 0; i < listDeviceDB.size(); i++) {
            DeviceDB deviceDB = (DeviceDB) listDeviceDB.get(i);
            if (BluetoothLeService.deviceId == null || BluetoothLeService.deviceId.isEmpty()) {
                C0213L.m19i("BluetoothLeService.deviceId == null");
            } else {
                C0213L.m19i("BluetoothLeService.deviceId == " + BluetoothLeService.deviceId);
                if (deviceDB.deviceId == null || deviceDB.deviceId.isEmpty()) {
                    C0213L.m19i("deviceDB.deviceId == null");
                } else {
                    C0213L.m19i("deviceDB.deviceId == " + deviceDB.deviceId);
                    if (deviceDB.deviceId.equals(BluetoothLeService.deviceId)) {
                        C0213L.m19i("deviceDB.deviceId.equals(BluetoothLeService.deviceId)");
                        is_Exit = true;
                        if (deviceDB.deviceModel == 2) {
                            this.deviceType = "ANNE PRO";
                        } else {
                            this.deviceType = getResources().getString(C0182R.string.unknow);
                        }
                        if (deviceDB.deviceId == null || deviceDB.deviceId.length() != 24) {
                            this.deviceId = getResources().getString(C0182R.string.unknow);
                        } else {
                            this.deviceId = deviceDB.deviceId;
                        }
                        if (deviceDB.mainMcuVersion != 0) {
                            this.mainMcuVersion = deviceDB.mainMcuVersion;
                        } else {
                            this.mainMcuVersion = -1;
                        }
                        if (deviceDB.kMcuVersion != 0) {
                            this.kMcuVersion = deviceDB.kMcuVersion;
                        } else {
                            this.kMcuVersion = -1;
                        }
                        if (deviceDB.bleVersion != 0) {
                            this.bleVersion = deviceDB.bleVersion;
                        } else {
                            this.bleVersion = -1;
                        }
                        if (!is_Exit) {
                            C0213L.m19i("is_Exit == false");
                            this.deviceType = getResources().getString(C0182R.string.unknow);
                            this.deviceId = getResources().getString(C0182R.string.unknow);
                            this.mainMcuVersion = -1;
                            this.kMcuVersion = -1;
                            this.bleVersion = -1;
                        }
                    }
                }
            }
        }
        if (!is_Exit) {
            C0213L.m19i("is_Exit == false");
            this.deviceType = getResources().getString(C0182R.string.unknow);
            this.deviceId = getResources().getString(C0182R.string.unknow);
            this.mainMcuVersion = -1;
            this.kMcuVersion = -1;
            this.bleVersion = -1;
        }
    }

    private void initUI() {
        this.listView = (ListView) findViewById(C0182R.id.listview);
        ItemObject itemObject0 = new ItemObject();
        itemObject0.titleString = getResources().getString(C0182R.string.setting_device_model);
        itemObject0.contentString = this.deviceType;
        itemObject0.imagesource = C0182R.drawable.setting_devcie_type_2;
        ItemObject itemObject1 = new ItemObject();
        itemObject1.titleString = getResources().getString(C0182R.string.setting_device_id);
        itemObject1.contentString = this.deviceId;
        itemObject1.imagesource = C0182R.drawable.set_main_id;
        ItemObject itemObject2 = new ItemObject();
        itemObject2.titleString = getResources().getString(C0182R.string.setting_main_mcu_version);
        if (this.mainMcuVersion == -1) {
            itemObject2.contentString = getResources().getString(C0182R.string.unknow);
        } else if (this.mainMcuVersion >= 10) {
            int zhenghsuVer = this.mainMcuVersion / 10;
            itemObject2.contentString = "V " + zhenghsuVer + "." + (this.mainMcuVersion - (zhenghsuVer * 10));
        } else {
            itemObject2.contentString = "V " + this.mainMcuVersion + ".0";
        }
        itemObject2.imagesource = C0182R.drawable.set_main_mcu;
        ItemObject itemObject3 = new ItemObject();
        itemObject3.titleString = getResources().getString(C0182R.string.setting_other_mcu_version);
        if (this.kMcuVersion == -1) {
            itemObject3.contentString = getResources().getString(C0182R.string.unknow);
        } else if (this.kMcuVersion >= 10) {
            zhenghsuVer = this.kMcuVersion / 10;
            itemObject3.contentString = "V " + zhenghsuVer + "." + (this.kMcuVersion - (zhenghsuVer * 10));
        } else {
            itemObject3.contentString = "V " + this.kMcuVersion + ".0";
        }
        itemObject3.imagesource = C0182R.drawable.set_main_mcu;
        ItemObject itemObject4 = new ItemObject();
        itemObject4.titleString = getResources().getString(C0182R.string.setting_ble_mcu_version);
        if (this.bleVersion != -1) {
            itemObject4.contentString = "V " + this.bleVersion + ".0";
        } else {
            itemObject4.contentString = getResources().getString(C0182R.string.unknow);
        }
        itemObject4.imagesource = C0182R.drawable.set_main_mcu_ble;
        this.listObject = new ArrayList();
        this.listObject.clear();
        this.listObject.add(itemObject0);
        this.listObject.add(itemObject1);
        this.listObject.add(itemObject2);
        this.listObject.add(itemObject3);
        this.listObject.add(itemObject4);
        this.adapter = new MyAdapter(this, this.listObject);
        this.listView.addFooterView(new ViewStub(this));
        this.listView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}
