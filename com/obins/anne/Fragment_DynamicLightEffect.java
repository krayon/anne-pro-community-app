package com.obins.anne;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.utils.BLEHandle;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;

public class Fragment_DynamicLightEffect extends Fragment {
    private MyAdapter adapter = null;
    private ListView dynamicLightEffect_listView;
    private List<ItemObject> listObject = null;
    private int selectItem = 0;

    public final class ItemObject {
        public boolean isCanturnSpd;
        public String lighteffectString;
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_lighteffect_listitem, null);
                holder.selectImageView = (ImageView) convertView.findViewById(C0182R.id.selectImageView);
                holder.lighteffectnameTextView = (TextView) convertView.findViewById(C0182R.id.lighteffectnameTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (Fragment_DynamicLightEffect.this.selectItem == position) {
                holder.selectImageView.setVisibility(0);
            } else {
                holder.selectImageView.setVisibility(4);
            }
            holder.lighteffectnameTextView.setText(((ItemObject) this.data.get(position)).lighteffectString);
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (!((ItemObject) MyAdapter.this.data.get(position)).lighteffectString.equals("自定义")) {
                        BluetoothLeService mBluetoothLeService = BluetoothLeService.getInstance();
                        if (mBluetoothLeService.isConnectedOk()) {
                            BLEHandle.normalLightEffect(mBluetoothLeService, (BluetoothGattCharacteristic) mBluetoothLeService.mOadService.getCharacteristics().get(1), position);
                        }
                        if (Fragment_DynamicLightEffect.this.selectItem == position) {
                            Fragment_DynamicLightEffect.this.selectItem = -1;
                        } else {
                            Fragment_DynamicLightEffect.this.selectItem = position;
                        }
                        Fragment_DynamicLightEffect.this.adapter.notifyDataSetChanged();
                    }
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView lighteffectnameTextView;
        public ImageView selectImageView;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_dynamiclighteffect, new RelativeLayout(getActivity()), true);
        initui(v);
        return v;
    }

    private void initui(View v) {
        this.dynamicLightEffect_listView = (ListView) v.findViewById(C0182R.id.dynamicLightEffect_listView);
        ItemObject itemObject1 = new ItemObject();
        itemObject1.lighteffectString = "LED 关闭";
        itemObject1.isCanturnSpd = false;
        ItemObject itemObject2 = new ItemObject();
        itemObject2.lighteffectString = "静态纯色 红色";
        itemObject2.isCanturnSpd = false;
        ItemObject itemObject3 = new ItemObject();
        itemObject3.lighteffectString = "静态纯色 黄色";
        itemObject3.isCanturnSpd = false;
        ItemObject itemObject4 = new ItemObject();
        itemObject4.lighteffectString = "静态纯色 绿色";
        itemObject4.isCanturnSpd = false;
        ItemObject itemObject5 = new ItemObject();
        itemObject5.lighteffectString = "静态纯色 青色";
        itemObject5.isCanturnSpd = false;
        ItemObject itemObject = new ItemObject();
        itemObject.lighteffectString = "静态纯色 蓝色";
        itemObject.isCanturnSpd = false;
        itemObject = new ItemObject();
        itemObject.lighteffectString = "静态纯色 紫色";
        itemObject.isCanturnSpd = false;
        itemObject = new ItemObject();
        itemObject.lighteffectString = "静态纯色 粉红";
        itemObject.isCanturnSpd = false;
        itemObject = new ItemObject();
        itemObject.lighteffectString = "静态纯色 橙色";
        itemObject.isCanturnSpd = false;
        ItemObject itemObject10 = new ItemObject();
        itemObject10.lighteffectString = "纯色呼吸灯";
        itemObject10.isCanturnSpd = false;
        ItemObject itemObject11 = new ItemObject();
        itemObject11.lighteffectString = "七彩呼吸灯 方向A";
        itemObject11.isCanturnSpd = false;
        ItemObject itemObject12 = new ItemObject();
        itemObject12.lighteffectString = "七彩呼吸灯 方向B";
        itemObject12.isCanturnSpd = false;
        ItemObject itemObject13 = new ItemObject();
        itemObject13.lighteffectString = "七彩呼吸灯 方向C";
        itemObject13.isCanturnSpd = false;
        ItemObject itemObject14 = new ItemObject();
        itemObject14.lighteffectString = "七彩呼吸灯 方向D";
        itemObject14.isCanturnSpd = false;
        ItemObject itemObject15 = new ItemObject();
        itemObject15.lighteffectString = "单色触发灯 默认红色";
        itemObject15.isCanturnSpd = false;
        ItemObject itemObject16 = new ItemObject();
        itemObject16.lighteffectString = "自定义";
        itemObject16.isCanturnSpd = false;
        this.listObject = new ArrayList();
        this.listObject.clear();
        this.listObject.add(itemObject1);
        this.listObject.add(itemObject2);
        this.listObject.add(itemObject3);
        this.listObject.add(itemObject4);
        this.listObject.add(itemObject5);
        this.listObject.add(itemObject);
        this.listObject.add(itemObject);
        this.listObject.add(itemObject);
        this.listObject.add(itemObject);
        this.listObject.add(itemObject10);
        this.listObject.add(itemObject11);
        this.listObject.add(itemObject12);
        this.listObject.add(itemObject13);
        this.listObject.add(itemObject14);
        this.listObject.add(itemObject15);
        this.adapter = new MyAdapter(getActivity(), this.listObject);
        this.dynamicLightEffect_listView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}
