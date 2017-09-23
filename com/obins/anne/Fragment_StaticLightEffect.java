package com.obins.anne;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.viewpart.CircleView;
import com.obins.anne.viewpart.RightHalfCircleView;
import com.obins.anne.viewpart.RingView;
import com.obins.anne.viewpart.RobinListView;
import java.util.ArrayList;
import java.util.List;

public class Fragment_StaticLightEffect extends Fragment implements OnClickListener {
    private MyAdapter adapter = null;
    private RelativeLayout additem_RelativeLayout;
    private List<ItemObject> listObject = null;
    private RobinListView listview;
    private int selectedItem = -1;

    public final class ItemObject {
        public int colorId;
        public boolean isSelected;
        public String nameString;
        public int transparentcolorId;
    }

    public class MyAdapter extends BaseAdapter {
        private List<ItemObject> data;
        private LayoutInflater mInflater;

        class C01791 implements OnClickListener {
            C01791() {
            }

            public void onClick(View arg0) {
                Fragment_StaticLightEffect.this.adapter.notifyDataSetChanged();
            }
        }

        class C01802 implements OnClickListener {
            C01802() {
            }

            public void onClick(View arg0) {
            }
        }

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

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = this.mInflater.inflate(C0182R.layout.fragment_macroprograming_white_listitem, null);
                holder.order_TextView = (TextView) convertView.findViewById(C0182R.id.order_TextView);
                holder.left_CircleView = (CircleView) convertView.findViewById(C0182R.id.left_CircleView);
                holder.bar_view = convertView.findViewById(C0182R.id.bar_view);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.right_HalfCircleView = (RightHalfCircleView) convertView.findViewById(C0182R.id.right_HalfCircleView);
                holder.right_RingView = (RingView) convertView.findViewById(C0182R.id.right_RingView);
                holder.right_CircleView = (CircleView) convertView.findViewById(C0182R.id.right_CircleView);
                holder.right_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.right_RelativeLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.order_TextView.setText(new StringBuilder(String.valueOf(position + 1)).toString());
            holder.left_CircleView.setColor(((ItemObject) this.data.get(position)).colorId);
            holder.bar_view.setBackgroundColor(((ItemObject) this.data.get(position)).transparentcolorId);
            holder.name_TextView.setText(((ItemObject) this.data.get(position)).nameString);
            holder.right_HalfCircleView.setColor(((ItemObject) this.data.get(position)).transparentcolorId);
            holder.right_RingView.setColor(((ItemObject) this.data.get(position)).colorId);
            holder.right_CircleView.setColor(((ItemObject) this.data.get(position)).colorId);
            if (((ItemObject) this.data.get(position)).isSelected) {
                holder.right_CircleView.setVisibility(0);
            } else {
                holder.right_CircleView.setVisibility(4);
            }
            holder.right_RelativeLayout.setOnClickListener(new C01791());
            convertView.setOnClickListener(new C01802());
            return convertView;
        }
    }

    public final class ViewHolder {
        public View bar_view;
        public CircleView left_CircleView;
        public TextView name_TextView;
        public TextView order_TextView;
        public CircleView right_CircleView;
        public RightHalfCircleView right_HalfCircleView;
        public RelativeLayout right_RelativeLayout;
        public RingView right_RingView;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_staticlighteffect, new RelativeLayout(getActivity()), true);
        initui(v);
        return v;
    }

    private void initui(View v) {
        this.listview = (RobinListView) v.findViewById(C0182R.id.staticLightEffect_listView);
        ItemObject itemObject1 = new ItemObject();
        itemObject1.colorId = getActivity().getResources().getColor(C0182R.color.green);
        itemObject1.transparentcolorId = getActivity().getResources().getColor(C0182R.color.green_transparent);
        itemObject1.nameString = "默认";
        itemObject1.isSelected = false;
        ItemObject itemObject2 = new ItemObject();
        itemObject2.colorId = getActivity().getResources().getColor(C0182R.color.purple);
        itemObject2.transparentcolorId = getActivity().getResources().getColor(C0182R.color.purple_transparent);
        itemObject2.nameString = "LOL";
        itemObject2.isSelected = false;
        ItemObject itemObject3 = new ItemObject();
        itemObject3.colorId = getActivity().getResources().getColor(C0182R.color.blue);
        itemObject3.transparentcolorId = getActivity().getResources().getColor(C0182R.color.blue_transparent);
        itemObject3.nameString = "DOTA";
        itemObject3.isSelected = false;
        this.listObject = new ArrayList();
        this.listObject.clear();
        this.listObject.add(itemObject1);
        this.listObject.add(itemObject2);
        this.listObject.add(itemObject3);
        this.listObject.add(itemObject1);
        this.listObject.add(itemObject2);
        this.listObject.add(itemObject3);
        this.listObject.add(itemObject1);
        this.listObject.add(itemObject2);
        this.listObject.add(itemObject3);
        this.adapter = new MyAdapter(getActivity(), this.listObject);
        this.listview.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.additem_RelativeLayout:
                ItemObject itemObject1 = new ItemObject();
                itemObject1.colorId = getActivity().getResources().getColor(C0182R.color.green);
                itemObject1.transparentcolorId = getActivity().getResources().getColor(C0182R.color.green_transparent);
                itemObject1.nameString = "新组";
                itemObject1.isSelected = false;
                this.listObject.add(itemObject1);
                this.adapter.notifyDataSetChanged();
                return;
            default:
                return;
        }
    }
}
