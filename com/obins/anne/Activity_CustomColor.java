package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.ColorPanel;
import com.obins.anne.db.KeyboardLightEffectDB;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import com.obins.anne.viewpart.ColorPickerView;
import com.obins.anne.viewpart.ColorPickerView.OnColorChangedListener;
import java.util.ArrayList;
import java.util.List;
import www.robinwatch.squid.utils.C0213L;

public class Activity_CustomColor extends BaseActivity implements OnClickListener {
    private int MAX_MARK = MotionEventCompat.ACTION_MASK;
    private int MIN_MARK = 0;
    private MyGridAdapter adapter;
    private RelativeLayout backBtn;
    private RelativeLayout blue_RelativeLayout;
    private TextView blue_TextView;
    private List<Integer> colorList;
    private GridView color_GridView;
    private ColorPickerView color_picker_view;
    private RelativeLayout green_RelativeLayout;
    private TextView green_TextView;
    private int oldColor;
    private TextView preview_TextView;
    private RelativeLayout red_RelativeLayout;
    private TextView red_TextView;
    private RelativeLayout sure_RelativeLayout;

    class C00751 implements OnClickListener {
        C00751() {
        }

        public void onClick(View arg0) {
            Activity_CustomColor.this.finish();
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_customcolorgridview_listitem, null);
                holder.colorblock_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.colorblock_RelativeLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.colorblock_RelativeLayout.setBackgroundColor(((Integer) this.data.get(position)).intValue());
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Activity_CustomColor.this.preview_TextView.setTextColor(((Integer) MyGridAdapter.this.data.get(position)).intValue());
                    Activity_CustomColor.this.setRGBTextView(((Integer) MyGridAdapter.this.data.get(position)).intValue());
                    Activity_CustomColor.this.color_picker_view.setColor(((Integer) MyGridAdapter.this.data.get(position)).intValue());
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public RelativeLayout colorblock_RelativeLayout;
    }

    class C02335 implements OnColorChangedListener {
        C02335() {
        }

        public void onColorChanged(int color) {
            Activity_CustomColor.this.preview_TextView.setTextColor(color);
            Activity_CustomColor.this.setRGBTextView(color);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_customcolor);
        initdata();
        initUI();
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00751());
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.sure_RelativeLayout:
                List<ColorPanel> colorPanelList = AppUtils.databaseManager.findColorPanel();
                if (colorPanelList.size() != 0) {
                    ColorPanel colorPanel = (ColorPanel) colorPanelList.get(0);
                    List<Integer> dbcolorList = KeyboardLightEffectDB.keycolorStringToList(colorPanel.defaultcolor);
                    dbcolorList.set(AppUtils.colorPanelColorPostion, Integer.valueOf(Color.rgb(Integer.valueOf(this.red_TextView.getText().toString()).intValue(), Integer.valueOf(this.green_TextView.getText().toString()).intValue(), Integer.valueOf(this.blue_TextView.getText().toString()).intValue())));
                    colorPanel.defaultcolor = KeyboardLightEffectDB.colorListToString(dbcolorList);
                    AppUtils.databaseManager.modifyColorPanel(colorPanel);
                }
                finish();
                return;
            case C0182R.id.red_RelativeLayout:
                showInputDialog(this.red_TextView);
                return;
            case C0182R.id.green_RelativeLayout:
                showInputDialog(this.green_TextView);
                return;
            case C0182R.id.blue_RelativeLayout:
                showInputDialog(this.blue_TextView);
                return;
            default:
                return;
        }
    }

    private void showInputDialog(final TextView textview) {
        final AlertDialog dialog = new Builder(this).create();
        dialog.setView((RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(C0182R.layout.input_colornum_prompt, null));
        dialog.show();
        dialog.getWindow().setContentView(C0182R.layout.input_colornum_prompt);
        ((TextView) dialog.findViewById(C0182R.id.title_TextView)).setText(getResources().getString(C0182R.string.tip));
        final EditText content_EditText = (EditText) dialog.findViewById(C0182R.id.content_EditText);
        content_EditText.setText(textview.getText().toString());
        content_EditText.setInputType(2);
        content_EditText.setSelection(textview.getText().toString().length());
        content_EditText.setFocusable(true);
        setRegion(content_EditText);
        Button sure_Button = (Button) dialog.findViewById(C0182R.id.sure_Button);
        sure_Button.setText(getResources().getString(C0182R.string.sure));
        Button cancle_Button = (Button) dialog.findViewById(C0182R.id.cancle_Button);
        cancle_Button.setText(getResources().getString(C0182R.string.cancle));
        sure_Button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                textview.setText(content_EditText.getText().toString());
                int nowColor = Color.rgb(Integer.valueOf(Activity_CustomColor.this.red_TextView.getText().toString()).intValue(), Integer.valueOf(Activity_CustomColor.this.green_TextView.getText().toString()).intValue(), Integer.valueOf(Activity_CustomColor.this.blue_TextView.getText().toString()).intValue());
                Activity_CustomColor.this.preview_TextView.setTextColor(nowColor);
                Activity_CustomColor.this.color_picker_view.setColor(nowColor);
                dialog.cancel();
            }
        });
        cancle_Button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
    }

    private void setRegion(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1 && Activity_CustomColor.this.MIN_MARK != -1 && Activity_CustomColor.this.MAX_MARK != -1) {
                    int num = Integer.parseInt(s.toString());
                    if (num > Activity_CustomColor.this.MAX_MARK) {
                        et.setText(String.valueOf(Activity_CustomColor.this.MAX_MARK));
                    } else if (num < Activity_CustomColor.this.MIN_MARK) {
                        s = String.valueOf(Activity_CustomColor.this.MIN_MARK);
                    }
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals(AppConfig.SERVER_IP) && Activity_CustomColor.this.MIN_MARK != -1 && Activity_CustomColor.this.MAX_MARK != -1) {
                    int markVal;
                    try {
                        markVal = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        markVal = 0;
                    }
                    if (markVal > Activity_CustomColor.this.MAX_MARK) {
                        Toast.makeText(Activity_CustomColor.this.getBaseContext(), Activity_CustomColor.this.getResources().getString(C0182R.string.cant_beyond_255), 0).show();
                        et.setText(String.valueOf(Activity_CustomColor.this.MAX_MARK));
                    }
                }
            }
        });
    }

    protected void onPause() {
        super.onPause();
    }

    private void initdata() {
        this.oldColor = AppUtils.colorPanelColorData;
    }

    public void setRGBTextView(int color) {
        int red = (16711680 & color) >> 16;
        int green = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & color) >> 8;
        int blue = color & MotionEventCompat.ACTION_MASK;
        this.red_TextView.setText(new StringBuilder(String.valueOf(red)).toString());
        this.green_TextView.setText(new StringBuilder(String.valueOf(green)).toString());
        this.blue_TextView.setText(new StringBuilder(String.valueOf(blue)).toString());
        C0213L.m19i("red = " + red);
        C0213L.m19i("green = " + green);
        C0213L.m19i("blue = " + blue);
        C0213L.m19i("color = " + color);
    }

    private void initUI() {
        this.red_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.red_RelativeLayout);
        this.green_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.green_RelativeLayout);
        this.blue_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.blue_RelativeLayout);
        this.red_RelativeLayout.setOnClickListener(this);
        this.green_RelativeLayout.setOnClickListener(this);
        this.blue_RelativeLayout.setOnClickListener(this);
        this.sure_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.sure_RelativeLayout);
        this.sure_RelativeLayout.setOnClickListener(this);
        this.color_picker_view = (ColorPickerView) findViewById(C0182R.id.color_picker_view);
        this.red_TextView = (TextView) findViewById(C0182R.id.red_TextView);
        this.blue_TextView = (TextView) findViewById(C0182R.id.blue_TextView);
        this.green_TextView = (TextView) findViewById(C0182R.id.green_TextView);
        this.preview_TextView = (TextView) findViewById(C0182R.id.preview_TextView);
        this.preview_TextView.setTextColor(this.oldColor);
        setRGBTextView(this.oldColor);
        this.color_picker_view.setColor(this.oldColor);
        this.color_picker_view.setOnColorChangedListener(new C02335());
        this.color_GridView = (GridView) findViewById(C0182R.id.color_GridView);
        this.color_GridView.setSelector(new ColorDrawable(0));
        this.colorList = new ArrayList();
        this.colorList = KeyboardLighteffectUtil.defaultKeyboardColorList;
        this.adapter = new MyGridAdapter(this, this.colorList);
        this.color_GridView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }
}
