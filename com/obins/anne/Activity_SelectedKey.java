package com.obins.anne;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.KeyObject;
import com.obins.anne.utils.KeyObjectUtil;
import java.util.ArrayList;
import java.util.List;

public class Activity_SelectedKey extends BaseActivity {
    private MyGridAdapter adapter1;
    private MyGridAdapter adapter2;
    private MyGridAdapter adapter3;
    private MyGridAdapter adapter4;
    private MyGridAdapter adapter5;
    private MyGridAdapter adapter6;
    private MyGridAdapter adapter7;
    private RelativeLayout backBtn;
    private GridView gridView1;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;
    private GridView gridView6;
    private GridView gridView7;
    private List<KeyObject> keyList1;
    private List<KeyObject> keyList2;
    private List<KeyObject> keyList3;
    private List<KeyObject> keyList4;
    private List<KeyObject> keyList5;
    private List<KeyObject> keyList6;
    private List<KeyObject> keyList7;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private LinearLayout linearLayout4;
    private LinearLayout linearLayout5;
    private LinearLayout linearLayout6;
    private LinearLayout linearLayout7;
    private int type;

    class C01411 implements OnClickListener {
        C01411() {
        }

        public void onClick(View arg0) {
            Activity_SelectedKey.this.finish();
        }
    }

    public class MyGridAdapter extends BaseAdapter {
        private int backColor;
        private List<KeyObject> data;
        private LayoutInflater mInflater;

        public MyGridAdapter(Context context, List<KeyObject> data, int backColor) {
            this.mInflater = LayoutInflater.from(context);
            this.data = data;
            this.backColor = backColor;
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_selectedkey_gridviewitem, null);
                holder.relativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.relativeLayout);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.relativeLayout.setBackgroundColor(this.backColor);
            if (((KeyObject) this.data.get(position)).keyStrComplete == null || ((KeyObject) this.data.get(position)).keyStrComplete.isEmpty()) {
                holder.name_TextView.setText(((KeyObject) this.data.get(position)).keyStr);
            } else {
                holder.name_TextView.setText(((KeyObject) this.data.get(position)).keyStrComplete);
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    AppUtils.textData = ((KeyObject) MyGridAdapter.this.data.get(position)).keyStr;
                    AppUtils.keyObject = (KeyObject) MyGridAdapter.this.data.get(position);
                    Activity_SelectedKey.this.finish();
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView name_TextView;
        public RelativeLayout relativeLayout;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_selectedkey);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01411());
        initdata();
        initUI();
    }

    private void initdata() {
        AppUtils.keyObject = null;
        this.type = AppUtils.jumpToSelectKeyPage_Type;
        this.keyList1 = KeyObjectUtil.systemKeyObjectList;
        this.keyList2 = KeyObjectUtil.baseKeyObjectList;
        this.keyList3 = KeyObjectUtil.numKeyObjectList;
        this.keyList4 = new ArrayList();
        this.keyList4.addAll(KeyObjectUtil.symbolKeyObjectList);
        this.keyList5 = new ArrayList();
        this.keyList5.addAll(KeyObjectUtil.bigKeyObjectList);
        this.keyList5.addAll(KeyObjectUtil.fareaKeyObjectList);
        this.keyList6 = new ArrayList();
        this.keyList6.addAll(KeyObjectUtil.funKeyObjectList);
        this.keyList7 = new ArrayList();
        this.keyList7.addAll(KeyObjectUtil.mediaKeyObjectList);
    }

    private void initUI() {
        this.linearLayout1 = (LinearLayout) findViewById(C0182R.id.linearLayout1);
        this.linearLayout2 = (LinearLayout) findViewById(C0182R.id.linearLayout2);
        this.linearLayout3 = (LinearLayout) findViewById(C0182R.id.linearLayout3);
        this.linearLayout4 = (LinearLayout) findViewById(C0182R.id.linearLayout4);
        this.linearLayout5 = (LinearLayout) findViewById(C0182R.id.linearLayout5);
        this.linearLayout6 = (LinearLayout) findViewById(C0182R.id.linearLayout6);
        this.linearLayout7 = (LinearLayout) findViewById(C0182R.id.linearLayout7);
        this.gridView1 = (GridView) findViewById(C0182R.id.gridView1);
        this.adapter1 = new MyGridAdapter(this, this.keyList1, getResources().getColor(C0182R.color.red));
        this.gridView1.setAdapter(this.adapter1);
        this.adapter1.notifyDataSetChanged();
        this.gridView2 = (GridView) findViewById(C0182R.id.gridView2);
        this.adapter2 = new MyGridAdapter(this, this.keyList2, getResources().getColor(C0182R.color.red));
        this.gridView2.setAdapter(this.adapter2);
        this.adapter2.notifyDataSetChanged();
        this.gridView3 = (GridView) findViewById(C0182R.id.gridView3);
        this.adapter3 = new MyGridAdapter(this, this.keyList3, getResources().getColor(C0182R.color.blue));
        this.gridView3.setAdapter(this.adapter3);
        this.adapter3.notifyDataSetChanged();
        this.gridView4 = (GridView) findViewById(C0182R.id.gridView4);
        this.adapter4 = new MyGridAdapter(this, this.keyList4, getResources().getColor(C0182R.color.blue));
        this.gridView4.setAdapter(this.adapter4);
        this.adapter4.notifyDataSetChanged();
        this.gridView5 = (GridView) findViewById(C0182R.id.gridView5);
        this.adapter5 = new MyGridAdapter(this, this.keyList5, getResources().getColor(C0182R.color.blue));
        this.gridView5.setAdapter(this.adapter5);
        this.adapter5.notifyDataSetChanged();
        this.gridView6 = (GridView) findViewById(C0182R.id.gridView6);
        this.adapter6 = new MyGridAdapter(this, this.keyList6, getResources().getColor(C0182R.color.blue));
        this.gridView6.setAdapter(this.adapter6);
        this.adapter6.notifyDataSetChanged();
        this.gridView7 = (GridView) findViewById(C0182R.id.gridView7);
        this.adapter7 = new MyGridAdapter(this, this.keyList7, getResources().getColor(C0182R.color.blue));
        this.gridView7.setAdapter(this.adapter7);
        this.adapter7.notifyDataSetChanged();
        if (this.type == 1) {
            this.keyList1 = KeyObjectUtil.systemKeyAndEmptyObjectList;
            this.adapter1 = new MyGridAdapter(this, this.keyList1, getResources().getColor(C0182R.color.red));
            this.gridView1.setAdapter(this.adapter1);
            this.adapter1.notifyDataSetChanged();
        } else if (this.type == 2) {
            this.keyList1 = KeyObjectUtil.emptyObjectList;
            this.adapter1 = new MyGridAdapter(this, this.keyList1, getResources().getColor(C0182R.color.red));
            this.gridView1.setAdapter(this.adapter1);
            this.adapter1.notifyDataSetChanged();
        } else if (this.type == 3) {
            this.linearLayout1.setVisibility(8);
            this.keyList5.clear();
            this.keyList5.addAll(KeyObjectUtil.macroBigKeyObjectList);
            this.adapter5 = new MyGridAdapter(this, this.keyList5, getResources().getColor(C0182R.color.blue));
            this.gridView5.setAdapter(this.adapter5);
            this.adapter5.notifyDataSetChanged();
            this.linearLayout6.setVisibility(8);
            this.linearLayout7.setVisibility(8);
        } else if (this.type == 4 || this.type == 5) {
            this.linearLayout1.setVisibility(8);
            this.linearLayout2.setVisibility(8);
            this.linearLayout3.setVisibility(8);
            this.linearLayout4.setVisibility(8);
            this.linearLayout5.setVisibility(8);
            this.linearLayout6.setVisibility(8);
        } else if (this.type == 6 || this.type == 7) {
            this.linearLayout1.setVisibility(8);
        } else if (this.type == 8) {
            this.linearLayout1.setVisibility(8);
        }
    }
}
