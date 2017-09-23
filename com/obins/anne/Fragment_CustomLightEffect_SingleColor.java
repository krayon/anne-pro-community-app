package com.obins.anne;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.obins.anne.db.ColorPanel;
import com.obins.anne.db.KeyboardLightEffectDB;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import java.util.ArrayList;
import java.util.List;

public class Fragment_CustomLightEffect_SingleColor extends Fragment {
    private static final String TAG = "Fragment_CustomLightEffect_BaseSetting";
    private static List<Integer> allkeyList;
    private static List<Integer> bigkeyList;
    private static List<Integer> letterkeyList;
    private static List<Integer> numkeyList;
    private int ackChange = 1;
    private MyGridAdapter adapter;
    private CheckBox allkey_CheckBox;
    private CheckBox bigkey_CheckBox;
    private CheckBox cancle_CheckBox;
    private List<Integer> colorList;
    private GridView color_GridView;
    private CheckBox letterkey_CheckBox;
    private CheckBox numkey_CheckBox;
    private int selecedColorItem = -1;

    class C01571 implements OnCheckedChangeListener {
        C01571() {
        }

        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            if (Fragment_CustomLightEffect_SingleColor.this.ackChange != 0) {
                if (arg1) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.allkeyList);
                    return;
                }
                ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).cancleFoucs(Fragment_CustomLightEffect_SingleColor.allkeyList);
                if (Fragment_CustomLightEffect_SingleColor.this.bigkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.bigkeyList);
                }
                if (Fragment_CustomLightEffect_SingleColor.this.numkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.numkeyList);
                }
                if (Fragment_CustomLightEffect_SingleColor.this.letterkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.letterkeyList);
                }
            }
        }
    }

    class C01582 implements OnCheckedChangeListener {
        C01582() {
        }

        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            if (Fragment_CustomLightEffect_SingleColor.this.ackChange != 0) {
                if (arg1) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.bigkeyList);
                } else if (!Fragment_CustomLightEffect_SingleColor.this.allkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).cancleFoucs(Fragment_CustomLightEffect_SingleColor.bigkeyList);
                }
            }
        }
    }

    class C01593 implements OnCheckedChangeListener {
        C01593() {
        }

        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            if (Fragment_CustomLightEffect_SingleColor.this.ackChange != 0) {
                if (arg1) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.numkeyList);
                } else if (!Fragment_CustomLightEffect_SingleColor.this.allkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).cancleFoucs(Fragment_CustomLightEffect_SingleColor.numkeyList);
                }
            }
        }
    }

    class C01604 implements OnCheckedChangeListener {
        C01604() {
        }

        public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
            if (Fragment_CustomLightEffect_SingleColor.this.ackChange != 0) {
                if (arg1) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setFocus(Fragment_CustomLightEffect_SingleColor.letterkeyList);
                } else if (!Fragment_CustomLightEffect_SingleColor.this.allkey_CheckBox.isChecked()) {
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).cancleFoucs(Fragment_CustomLightEffect_SingleColor.letterkeyList);
                }
            }
        }
    }

    class C01615 implements OnClickListener {
        C01615() {
        }

        public void onClick(View arg0) {
            ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).cancleAllSelected();
            Fragment_CustomLightEffect_SingleColor.this.cancle_CheckBox.setChecked(false);
            Fragment_CustomLightEffect_SingleColor.this.allkey_CheckBox.setChecked(false);
            Fragment_CustomLightEffect_SingleColor.this.bigkey_CheckBox.setChecked(false);
            Fragment_CustomLightEffect_SingleColor.this.numkey_CheckBox.setChecked(false);
            Fragment_CustomLightEffect_SingleColor.this.letterkey_CheckBox.setChecked(false);
        }
    }

    public class MyGridAdapter extends BaseAdapter {
        private List<Integer> data;
        private LayoutInflater mInflater;

        public MyGridAdapter(Context context, List<Integer> data) {
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
                convertView = this.mInflater.inflate(C0182R.layout.colorgridview_listitem, null);
                holder.colorblock_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.colorblock_RelativeLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (Fragment_CustomLightEffect_SingleColor.this.selecedColorItem == position) {
                int strokeColor = Color.parseColor("#FFFFFF");
                int fillColor = ((Integer) this.data.get(position)).intValue();
                GradientDrawable gd = new GradientDrawable();
                gd.setColor(fillColor);
                gd.setCornerRadius((float) 0);
                gd.setStroke(3, strokeColor);
                holder.colorblock_RelativeLayout.setBackground(gd);
            } else {
                holder.colorblock_RelativeLayout.setBackgroundColor(((Integer) this.data.get(position)).intValue());
            }
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Fragment_CustomLightEffect_SingleColor.this.selecedColorItem = position;
                    Fragment_CustomLightEffect_SingleColor.this.adapter.notifyDataSetChanged();
                    ((ActivityFragment_CustomLightEffect) Fragment_CustomLightEffect_SingleColor.this.getActivity()).setSelectedColor(((Integer) MyGridAdapter.this.data.get(position)).intValue());
                }
            });
            convertView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    Fragment_CustomLightEffect_SingleColor.this.selecedColorItem = position;
                    Fragment_CustomLightEffect_SingleColor.this.adapter.notifyDataSetChanged();
                    Intent mIntent = new Intent(Fragment_CustomLightEffect_SingleColor.this.getActivity(), Activity_CustomColor.class);
                    AppUtils.colorPanelColorData = ((Integer) MyGridAdapter.this.data.get(position)).intValue();
                    AppUtils.colorPanelColorPostion = position;
                    Fragment_CustomLightEffect_SingleColor.this.startActivity(mIntent);
                    return false;
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public RelativeLayout colorblock_RelativeLayout;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0182R.layout.fragment_customlighteffect_singlecolor, new RelativeLayout(getActivity()), true);
        this.colorList = new ArrayList();
        this.colorList.clear();
        initdata();
        initUI(v);
        this.ackChange = 1;
        return v;
    }

    private void initdata() {
        int k;
        allkeyList = new ArrayList();
        bigkeyList = new ArrayList();
        numkeyList = new ArrayList();
        letterkeyList = new ArrayList();
        for (int i = 0; i < 61; i++) {
            allkeyList.add(Integer.valueOf(i));
        }
        bigkeyList.add(Integer.valueOf(0));
        bigkeyList.add(Integer.valueOf(13));
        bigkeyList.add(Integer.valueOf(14));
        bigkeyList.add(Integer.valueOf(27));
        bigkeyList.add(Integer.valueOf(28));
        bigkeyList.add(Integer.valueOf(40));
        bigkeyList.add(Integer.valueOf(41));
        bigkeyList.add(Integer.valueOf(52));
        bigkeyList.add(Integer.valueOf(53));
        bigkeyList.add(Integer.valueOf(54));
        bigkeyList.add(Integer.valueOf(55));
        bigkeyList.add(Integer.valueOf(56));
        bigkeyList.add(Integer.valueOf(57));
        bigkeyList.add(Integer.valueOf(58));
        bigkeyList.add(Integer.valueOf(59));
        bigkeyList.add(Integer.valueOf(60));
        for (int j = 1; j < 13; j++) {
            numkeyList.add(Integer.valueOf(j));
        }
        for (k = 15; k < 27; k++) {
            letterkeyList.add(Integer.valueOf(k));
        }
        for (k = 29; k < 40; k++) {
            letterkeyList.add(Integer.valueOf(k));
        }
        for (k = 42; k < 52; k++) {
            letterkeyList.add(Integer.valueOf(k));
        }
    }

    public void onResume() {
        super.onResume();
        this.colorList.clear();
        List<ColorPanel> colorPanelList = AppUtils.databaseManager.findColorPanel();
        if (colorPanelList.size() == 0) {
            ColorPanel colorPanel = new ColorPanel();
            colorPanel.defaultcolor = KeyboardLightEffectDB.colorListToString(KeyboardLighteffectUtil.defaultKeyboardColorList);
            AppUtils.databaseManager.addColorPanel(colorPanel);
            this.colorList.addAll(KeyboardLighteffectUtil.defaultKeyboardColorList);
        } else {
            this.colorList.addAll(KeyboardLightEffectDB.keycolorStringToList(((ColorPanel) colorPanelList.get(0)).defaultcolor));
        }
        this.adapter.notifyDataSetChanged();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void initUI(View v) {
        this.allkey_CheckBox = (CheckBox) v.findViewById(C0182R.id.allkey_CheckBox);
        this.bigkey_CheckBox = (CheckBox) v.findViewById(C0182R.id.bigkey_CheckBox);
        this.numkey_CheckBox = (CheckBox) v.findViewById(C0182R.id.numkey_CheckBox);
        this.cancle_CheckBox = (CheckBox) v.findViewById(C0182R.id.cancle_CheckBox);
        this.letterkey_CheckBox = (CheckBox) v.findViewById(C0182R.id.letterkey_CheckBox);
        this.allkey_CheckBox.setOnCheckedChangeListener(new C01571());
        this.bigkey_CheckBox.setOnCheckedChangeListener(new C01582());
        this.numkey_CheckBox.setOnCheckedChangeListener(new C01593());
        this.letterkey_CheckBox.setOnCheckedChangeListener(new C01604());
        this.cancle_CheckBox.setOnClickListener(new C01615());
        this.color_GridView = (GridView) v.findViewById(C0182R.id.color_GridView);
        this.color_GridView.setSelector(new ColorDrawable(0));
        this.adapter = new MyGridAdapter(getActivity(), this.colorList);
        this.color_GridView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}
