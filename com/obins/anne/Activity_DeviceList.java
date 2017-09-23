package com.obins.anne;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.obins.anne.utils.AppConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.utils.C0213L;

public class Activity_DeviceList extends BaseActivity {
    private static final String TAG = "Activity_DeviceList";
    private MyAdapter adapter = null;
    private List<ItemObject> deviceList;
    private List<ItemObject> listObject = null;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt = null;
    private BluetoothManager mBluetoothManager = null;
    private BluetoothAdapter mBtAdapter = null;
    private BluetoothGattCallback mGattCallbacks = new C00811();
    private ListView settingsListView = null;

    class C00811 extends BluetoothGattCallback {
        C00811() {
        }

        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (Activity_DeviceList.this.mBluetoothGatt == null) {
                Log.e(Activity_DeviceList.TAG, "mBluetoothGatt not created!");
            } else if (Activity_DeviceList.this.mBluetoothGatt != null) {
                Activity_DeviceList.this.mBluetoothGatt.close();
                Activity_DeviceList.this.mBluetoothGatt.disconnect();
                Activity_DeviceList.this.mBluetoothGatt = null;
            }
        }
    }

    public final class ItemObject {
        public boolean is_Link;
        public boolean is_now_connect;
        public String nameTextView;
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_devicelist_listitem, null);
                holder.macTextView = (TextView) convertView.findViewById(C0182R.id.macTextView);
                holder.bk_ImageView = (ImageView) convertView.findViewById(C0182R.id.bk_ImageView);
                holder.top_View = convertView.findViewById(C0182R.id.top_View);
                holder.bottom_View = convertView.findViewById(C0182R.id.bottom_View);
                holder.bk_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.bk_RelativeLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.macTextView.setText(((ItemObject) this.data.get(position)).nameTextView);
            if (((ItemObject) this.data.get(position)).is_Link) {
                holder.bk_ImageView.setBackground(Activity_DeviceList.this.getResources().getDrawable(C0182R.drawable.device_list_bg_online));
            } else {
                holder.bk_ImageView.setBackground(Activity_DeviceList.this.getResources().getDrawable(C0182R.drawable.device_list_bg_offline));
            }
            if (position == this.data.size() - 1) {
                holder.top_View.setVisibility(0);
                holder.bottom_View.setVisibility(0);
            } else {
                holder.top_View.setVisibility(0);
                holder.bottom_View.setVisibility(8);
            }
            if (((ItemObject) this.data.get(position)).is_now_connect) {
                if (((ItemObject) this.data.get(position)).is_Link) {
                    holder.bk_ImageView.setBackground(Activity_DeviceList.this.getResources().getDrawable(C0182R.drawable.device_list_bg_online_select));
                } else {
                    holder.bk_ImageView.setBackground(Activity_DeviceList.this.getResources().getDrawable(C0182R.drawable.device_list_bg_offline_select));
                }
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent intent0 = new Intent(Activity_DeviceList.this, Activity_HomePage.class);
                    BluetoothLeService bluetoothLeService = BluetoothLeService.getInstance();
                    if (bluetoothLeService == null) {
                        BluetoothLeService.selectMac = ((ItemObject) Activity_DeviceList.this.deviceList.get(position)).nameTextView;
                    } else {
                        bluetoothLeService.setSelectMac(((ItemObject) Activity_DeviceList.this.deviceList.get(position)).nameTextView);
                    }
                    Activity_DeviceList.this.startActivity(intent0);
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public ImageView bk_ImageView;
        public RelativeLayout bk_RelativeLayout;
        public View bottom_View;
        public TextView macTextView;
        public View top_View;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_devicelist);
        initdata();
        initUI();
    }

    private void initdata() {
        this.deviceList = new ArrayList();
        this.mBluetoothManager = (BluetoothManager) getSystemService("bluetooth");
        if (this.mBluetoothManager != null) {
            this.mBtAdapter = this.mBluetoothManager.getAdapter();
            if (this.mBtAdapter != null) {
            }
        }
    }

    protected void onResume() {
        super.onResume();
        findDevice();
        if (this.deviceList.size() < 2) {
            startActivity(new Intent(this, Activity_HomePage.class));
            finish();
        }
    }

    public int findDevice() {
        this.deviceList.clear();
        if (this.mBluetoothManager == null) {
            return -1;
        }
        if (this.mBtAdapter == null) {
            return -1;
        }
        int i;
        Set<BluetoothDevice> pairedDevices = this.mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                if (device.getName() != null && device.getName().length() == 15) {
                    if (device.getName().regionMatches(0, AppConfig.KEYBOARDNAME, 0, AppConfig.KEYBOARDNAME.length())) {
                        ItemObject ob;
                        int connectionState;
                        String nowLinkMacString;
                        String deviceMac = device.getAddress();
                        boolean isExit = false;
                        i = 0;
                        while (i < this.deviceList.size()) {
                            if (deviceMac.substring(2).equals(((ItemObject) this.deviceList.get(i)).nameTextView.substring(2))) {
                                isExit = true;
                                ob = (ItemObject) this.deviceList.get(i);
                                connectionState = this.mBluetoothManager.getConnectionState(device, 7);
                                if (connect(device.getAddress())) {
                                    disconnect(device.getAddress());
                                    ob.is_Link = true;
                                    this.deviceList.set(i, ob);
                                }
                                if (!isExit) {
                                    ob = new ItemObject();
                                    ob.nameTextView = deviceMac;
                                    ob.is_Link = false;
                                    ob.is_now_connect = false;
                                    nowLinkMacString = BluetoothLeService.selectMac;
                                    if (!(nowLinkMacString == null || nowLinkMacString.isEmpty() || nowLinkMacString.length() <= 2)) {
                                        if (nowLinkMacString.substring(2).equals(deviceMac.substring(2))) {
                                            ob.is_now_connect = true;
                                        }
                                    }
                                    connectionState = this.mBluetoothManager.getConnectionState(device, 7);
                                    if (connect(device.getAddress())) {
                                        disconnect(device.getAddress());
                                        ob.is_Link = true;
                                    }
                                    this.deviceList.add(ob);
                                }
                            } else {
                                i++;
                            }
                        }
                        if (!isExit) {
                            ob = new ItemObject();
                            ob.nameTextView = deviceMac;
                            ob.is_Link = false;
                            ob.is_now_connect = false;
                            nowLinkMacString = BluetoothLeService.selectMac;
                            if (nowLinkMacString.substring(2).equals(deviceMac.substring(2))) {
                                ob.is_now_connect = true;
                            }
                            connectionState = this.mBluetoothManager.getConnectionState(device, 7);
                            if (connect(device.getAddress())) {
                                disconnect(device.getAddress());
                                ob.is_Link = true;
                            }
                            this.deviceList.add(ob);
                        }
                    }
                }
            }
        }
        this.listObject.clear();
        int len = this.deviceList.size();
        for (i = 0; i < len; i++) {
            this.listObject.add((ItemObject) this.deviceList.get(i));
        }
        this.adapter.notifyDataSetChanged();
        return this.deviceList.size();
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
        switch (this.mBluetoothManager.getConnectionState(device, 7)) {
            case 0:
                C0213L.m20i(TAG, "STATE_DISCONNECTED ");
                return false;
            case 2:
                C0213L.m20i(TAG, "STATE_CONNECTED ");
                break;
        }
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

    private void initUI() {
        this.listObject = new ArrayList();
        this.listObject.clear();
        int len = this.deviceList.size();
        for (int i = 0; i < len; i++) {
            this.listObject.add((ItemObject) this.deviceList.get(i));
        }
        this.adapter = new MyAdapter(this, this.listObject);
        this.settingsListView = (ListView) findViewById(C0182R.id.settingsListView);
        this.settingsListView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}
