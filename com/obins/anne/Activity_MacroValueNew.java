package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.MacroMember;
import com.obins.anne.db.MacroMemberValue;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.C0188L;
import com.obins.anne.utils.CRC16;
import com.obins.anne.utils.KeyObjectUtil;
import com.obins.anne.viewpart.CircleView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;

public class Activity_MacroValueNew extends BaseActivity implements OnClickListener {
    private static final int BEYOND_MACRO_MAX = 4;
    private static final int PROMPT_VIEW_RUN = 5;
    private static final int START_RECORD_MACRO_BACK = 1;
    private static final int START_RECORD_MACRO_TIMEOUT = 0;
    private static final int STOP_RECORD_MACRO_BACK = 3;
    private static final int STOP_RECORD_MACRO_TIMEOUT = 2;
    private static String TAG = "Activity_MacroValue";
    private static int circleViewshowNum = 0;
    private static List<Integer> listItemColorId = null;
    private static List<Integer> listItemTransparentColorId = null;
    private static int prompt_view_run_flag = 1;
    private RelativeLayout RelativeLayout_1;
    private RelativeLayout RelativeLayout_2;
    private RelativeLayout RelativeLayout_3;
    private RelativeLayout RelativeLayout_4;
    private RelativeLayout RelativeLayout_5;
    private MyGridAdapter adapter;
    private RelativeLayout back_RelativeLayout;
    private CircleView circleView1;
    private CircleView circleView2;
    private CircleView circleView3;
    private CircleView circleView4;
    private CircleView circleView5;
    private TextView finish_TextView;
    private GridView gridView;
    private long lastTime;
    private int[] lastValue;
    private List<CircleView> listCircleView;
    private List<ViewData> listRecoedViewData = null;
    private List<ViewData> listViewData = null;
    private final BroadcastReceiver mGattUpdateReceiver = new C01091();
    public Handler mHandler = new C01132();
    private IntentFilter mIntentFilter;
    private MacroMember macroMember;
    private EditText macroName_EditText;
    private ProgressDialog progressDialog;
    private RelativeLayout recordMacro_RelativeLayout;
    private TextView recordMacro_TextView;
    private TextView recordMacro_tip_TextView;
    private int selectedItem = -1;
    private int startRecordMacroFlag = -1;
    private long startTime;
    private RelativeLayout triggerkey_RelativeLayout;
    private TextView triggerkey_TextView;

    class C01091 extends BroadcastReceiver {
        C01091() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_MacroValueNew.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_MACRO.equals(action)) {
                String type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0188L.m8i(Activity_MacroValueNew.TAG, "type: " + type);
                int result;
                Message msg;
                Integer resultInteger;
                if (type.equals(BluetoothLeService.EXTRA_TYPE_MACRO_START_RECORD)) {
                    result = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, -1);
                    msg = new Message();
                    resultInteger = new Integer(result);
                    msg.what = 1;
                    msg.obj = resultInteger;
                    Activity_MacroValueNew.this.mHandler.sendMessage(msg);
                } else if (type.equals(BluetoothLeService.EXTRA_TYPE_MACRO_STOP_RECORD)) {
                    result = intent.getIntExtra(BluetoothLeService.EXTRA_DATA, -1);
                    msg = new Message();
                    resultInteger = new Integer(result);
                    msg.what = 3;
                    msg.obj = resultInteger;
                    Activity_MacroValueNew.this.mHandler.sendMessage(msg);
                } else if (type.equals(BluetoothLeService.EXTRA_TYPE_MACRO_RECORD_KEY)) {
                    Activity_MacroValueNew.this.handleKeyValue(intent.getIntArrayExtra(BluetoothLeService.EXTRA_DATA));
                }
            }
        }
    }

    class C01132 extends Handler {

        class C01101 implements Runnable {
            C01101() {
            }

            public void run() {
                while (Activity_MacroValueNew.prompt_view_run_flag == 1) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what = 5;
                    Activity_MacroValueNew.this.mHandler.sendMessage(msg);
                }
            }
        }

        C01132() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (Activity_MacroValueNew.this.progressDialog.isShowing()) {
                        Activity_MacroValueNew.this.progressDialog.cancel();
                    }
                    if (Activity_MacroValueNew.this.startRecordMacroFlag == 1) {
                        Toast.makeText(Activity_MacroValueNew.this, Activity_MacroValueNew.this.getResources().getString(C0182R.string.macro_open_record_success), 0).show();
                        Activity_MacroValueNew.this.listRecoedViewData = new ArrayList();
                        Activity_MacroValueNew.this.listRecoedViewData.clear();
                        final AlertDialog alertDialog = new Builder(Activity_MacroValueNew.this).create();
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                        Window window = alertDialog.getWindow();
                        window.setContentView(C0182R.layout.macro_record_prompt);
                        Activity_MacroValueNew.this.circleView1 = (CircleView) window.findViewById(C0182R.id.circleView1);
                        Activity_MacroValueNew.this.circleView2 = (CircleView) window.findViewById(C0182R.id.circleView2);
                        Activity_MacroValueNew.this.circleView3 = (CircleView) window.findViewById(C0182R.id.circleView3);
                        Activity_MacroValueNew.this.circleView4 = (CircleView) window.findViewById(C0182R.id.circleView4);
                        Activity_MacroValueNew.this.circleView5 = (CircleView) window.findViewById(C0182R.id.circleView5);
                        Activity_MacroValueNew.this.listCircleView = new ArrayList();
                        Activity_MacroValueNew.this.listCircleView.clear();
                        Activity_MacroValueNew.this.listCircleView.add(Activity_MacroValueNew.this.circleView1);
                        Activity_MacroValueNew.this.listCircleView.add(Activity_MacroValueNew.this.circleView2);
                        Activity_MacroValueNew.this.listCircleView.add(Activity_MacroValueNew.this.circleView3);
                        Activity_MacroValueNew.this.listCircleView.add(Activity_MacroValueNew.this.circleView4);
                        Activity_MacroValueNew.this.listCircleView.add(Activity_MacroValueNew.this.circleView5);
                        Activity_MacroValueNew.prompt_view_run_flag = 1;
                        Activity_MacroValueNew.circleViewshowNum = 0;
                        new Thread(new C01101()).start();
                        Button cancle_Button = (Button) window.findViewById(C0182R.id.cancle_Button);
                        ((Button) window.findViewById(C0182R.id.ok_Button)).setOnClickListener(new OnClickListener() {
                            public void onClick(View arg0) {
                                Activity_MacroValueNew.prompt_view_run_flag = 0;
                                Activity_MacroValueNew.this.listViewData.clear();
                                Activity_MacroValueNew.this.listViewData.addAll(Activity_MacroValueNew.this.listRecoedViewData);
                                Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                                BLEHandle.stopRecordMacro(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                                alertDialog.cancel();
                            }
                        });
                        cancle_Button.setOnClickListener(new OnClickListener() {
                            public void onClick(View arg0) {
                                Activity_MacroValueNew.prompt_view_run_flag = 0;
                                BLEHandle.stopRecordMacro(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                                alertDialog.cancel();
                            }
                        });
                        return;
                    }
                    Toast.makeText(Activity_MacroValueNew.this, Activity_MacroValueNew.this.getResources().getString(C0182R.string.macro_open_record_fail), 0).show();
                    return;
                case 1:
                    int result = ((Integer) msg.obj).intValue();
                    Activity_MacroValueNew.this.startRecordMacroFlag = 1;
                    return;
                case 3:
                    Activity_MacroValueNew.this.startRecordMacroFlag = 2;
                    return;
                case 5:
                    if (Activity_MacroValueNew.prompt_view_run_flag == 1) {
                        C0188L.m7i("circleViewshowNum = " + Activity_MacroValueNew.circleViewshowNum);
                        if (Activity_MacroValueNew.circleViewshowNum > 4) {
                            Activity_MacroValueNew.this.circleViewRun(8 - Activity_MacroValueNew.circleViewshowNum);
                        } else {
                            Activity_MacroValueNew.this.circleViewRun(Activity_MacroValueNew.circleViewshowNum);
                        }
                        if (Activity_MacroValueNew.circleViewshowNum < 7) {
                            Activity_MacroValueNew.circleViewshowNum = Activity_MacroValueNew.circleViewshowNum + 1;
                            return;
                        } else {
                            Activity_MacroValueNew.circleViewshowNum = 0;
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    class C01143 implements Runnable {
        C01143() {
        }

        public void run() {
            try {
                Thread.sleep(600);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 0;
            Activity_MacroValueNew.this.mHandler.sendMessage(message);
        }
    }

    public final class KeyData {
        public static final int TYPE_KeyData_DOWN = 1;
        public static final int TYPE_KeyData_FUN = 3;
        public static final int TYPE_KeyData_UP = 2;
        public int absolutelyTime;
        public String name;
        public int type;
        public int value;
    }

    public class KeyDataComparator implements Comparator<KeyData> {
        public int compare(KeyData o1, KeyData o2) {
            if (o1.absolutelyTime >= o2.absolutelyTime) {
                return 1;
            }
            return -1;
        }
    }

    public class MyGridAdapter extends BaseAdapter {
        private List<ViewData> data;
        private LayoutInflater mInflater;

        public MyGridAdapter(Context context, List<ViewData> data) {
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
                convertView = this.mInflater.inflate(C0182R.layout.activity_macrovalue_gridviewitem, null);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.num_TextView = (TextView) convertView.findViewById(C0182R.id.num_TextView);
                holder.down_ImageView = (ImageView) convertView.findViewById(C0182R.id.down_ImageView);
                holder.up_ImageView = (ImageView) convertView.findViewById(C0182R.id.up_ImageView);
                holder.relativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.relativeLayout);
                holder.time_TextView = (TextView) convertView.findViewById(C0182R.id.time_TextView);
                holder.click_RelativeLayout1 = (RelativeLayout) convertView.findViewById(C0182R.id.click_RelativeLayout1);
                holder.click_RelativeLayout2 = (RelativeLayout) convertView.findViewById(C0182R.id.click_RelativeLayout2);
                holder.click_RelativeLayout3 = (RelativeLayout) convertView.findViewById(C0182R.id.click_RelativeLayout3);
                holder.view_LinearLayout = (LinearLayout) convertView.findViewById(C0182R.id.view_LinearLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.down_ImageView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            LayoutParams linearParams = (LayoutParams) holder.relativeLayout.getLayoutParams();
            linearParams.height = holder.down_ImageView.getMeasuredHeight();
            holder.relativeLayout.setLayoutParams(linearParams);
            holder.num_TextView.setText(new StringBuilder(String.valueOf(position + 1)).toString());
            holder.name_TextView.setText(((ViewData) this.data.get(position)).nameStr);
            holder.time_TextView.setText(new StringBuilder(String.valueOf(((ViewData) this.data.get(position)).timeValue)).append("ms").toString());
            if (((ViewData) this.data.get(position)).type == 0) {
                holder.up_ImageView.setVisibility(4);
                holder.down_ImageView.setVisibility(0);
            } else if (((ViewData) this.data.get(position)).type == 1) {
                holder.up_ImageView.setVisibility(0);
                holder.down_ImageView.setVisibility(4);
            } else if (((ViewData) this.data.get(position)).type == 2) {
                holder.up_ImageView.setVisibility(4);
                holder.down_ImageView.setVisibility(0);
            }
            if (position == Activity_MacroValueNew.this.selectedItem) {
                holder.relativeLayout.setBackgroundColor(Activity_MacroValueNew.this.getResources().getColor(C0182R.color.listitem_bk_darkgray));
            } else {
                holder.relativeLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
            }
            if (position == this.data.size() - 1) {
                if (Activity_MacroValueNew.this.judgeMacroNum()) {
                    Activity_MacroValueNew.this.recordMacro_tip_TextView.setVisibility(0);
                    String tip = Activity_MacroValueNew.this.recordMacro_tip_TextView.getText().toString();
                    String num_tip = Activity_MacroValueNew.this.getResources().getString(C0182R.string.macro_support_most_20_key_tip);
                    String time_tip = Activity_MacroValueNew.this.getResources().getString(C0182R.string.macro_support_record_60_sec);
                    if (tip == null || tip.length() == num_tip.length()) {
                        Activity_MacroValueNew.this.recordMacro_tip_TextView.setText(num_tip);
                    } else {
                        Activity_MacroValueNew.this.recordMacro_tip_TextView.setText(new StringBuilder(String.valueOf(num_tip)).append(time_tip).toString());
                    }
                } else {
                    Activity_MacroValueNew.this.recordMacro_tip_TextView.setVisibility(4);
                }
            }
            holder.click_RelativeLayout1.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    Activity_MacroValueNew.this.selectedItem = position;
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                    final ViewData viewData = (ViewData) Activity_MacroValueNew.this.listViewData.get(position);
                    final AlertDialog dialog = new Builder(Activity_MacroValueNew.this).create();
                    dialog.setView((RelativeLayout) ((LayoutInflater) Activity_MacroValueNew.this.getSystemService("layout_inflater")).inflate(C0182R.layout.input_prompt, null));
                    dialog.show();
                    dialog.getWindow().setContentView(C0182R.layout.input_prompt);
                    ((TextView) dialog.findViewById(C0182R.id.title_TextView)).setText(Activity_MacroValueNew.this.getResources().getString(C0182R.string.tip));
                    final EditText content_EditText = (EditText) dialog.findViewById(C0182R.id.content_EditText);
                    content_EditText.setText(new StringBuilder(String.valueOf(viewData.value)).toString());
                    content_EditText.setInputType(2);
                    content_EditText.setSelection(new StringBuilder(String.valueOf(viewData.value)).toString().toString().length());
                    Button sure_Button = (Button) dialog.findViewById(C0182R.id.sure_Button);
                    sure_Button.setText(Activity_MacroValueNew.this.getResources().getString(C0182R.string.sure));
                    Button cancle_Button = (Button) dialog.findViewById(C0182R.id.cancle_Button);
                    cancle_Button.setText(Activity_MacroValueNew.this.getResources().getString(C0182R.string.cancle));
                    final int i = position;
                    sure_Button.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (content_EditText.getText().toString() == null || content_EditText.getText().toString().isEmpty()) {
                                viewData.value = 0;
                            } else {
                                viewData.value = Integer.valueOf(content_EditText.getText().toString()).intValue();
                            }
                            if (viewData.value > 60000) {
                                viewData.value = 60000;
                                Toast.makeText(Activity_MacroValueNew.this, Activity_MacroValueNew.this.getResources().getString(C0182R.string.macro_support_record_60_sec), 0).show();
                            }
                            Activity_MacroValueNew.this.listViewData.set(i, viewData);
                            dialog.cancel();
                            Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                        }
                    });
                    cancle_Button.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            dialog.cancel();
                        }
                    });
                    return false;
                }
            });
            holder.click_RelativeLayout2.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    Activity_MacroValueNew.this.selectedItem = position;
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                    ViewData viewData = (ViewData) Activity_MacroValueNew.this.listViewData.get(position);
                    if (viewData.type == 0) {
                        AppUtils.jumpToSelectKeyPage_Type = 6;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    } else if (viewData.type == 1) {
                        AppUtils.jumpToSelectKeyPage_Type = 7;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    } else if (viewData.type == 2) {
                        AppUtils.jumpToSelectKeyPage_Type = 5;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    }
                    return false;
                }
            });
            holder.click_RelativeLayout3.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    Activity_MacroValueNew.this.selectedItem = position;
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                    ViewData viewData = (ViewData) Activity_MacroValueNew.this.listViewData.get(position);
                    if (viewData.type == 0) {
                        AppUtils.jumpToSelectKeyPage_Type = 6;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    } else if (viewData.type == 1) {
                        AppUtils.jumpToSelectKeyPage_Type = 7;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    } else if (viewData.type == 2) {
                        AppUtils.jumpToSelectKeyPage_Type = 5;
                        Activity_MacroValueNew.this.startActivity(new Intent(Activity_MacroValueNew.this, Activity_SelectedKey.class));
                    }
                    return false;
                }
            });
            holder.click_RelativeLayout1.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (Activity_MacroValueNew.this.selectedItem == position) {
                        Activity_MacroValueNew.this.selectedItem = -1;
                    } else {
                        Activity_MacroValueNew.this.selectedItem = position;
                    }
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                }
            });
            holder.click_RelativeLayout2.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (Activity_MacroValueNew.this.selectedItem == position) {
                        Activity_MacroValueNew.this.selectedItem = -1;
                    } else {
                        Activity_MacroValueNew.this.selectedItem = position;
                    }
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                }
            });
            holder.click_RelativeLayout3.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    if (Activity_MacroValueNew.this.selectedItem == position) {
                        Activity_MacroValueNew.this.selectedItem = -1;
                    } else {
                        Activity_MacroValueNew.this.selectedItem = position;
                    }
                    Activity_MacroValueNew.this.adapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

    public final class ViewData {
        public static final int TYPE_FUN = 2;
        public static final int TYPE_KEY_DOWN = 0;
        public static final int TYPE_KEY_UP = 1;
        public String nameStr;
        public int timeValue;
        public int type;
        public int value;
    }

    public final class ViewHolder {
        private RelativeLayout click_RelativeLayout1;
        private RelativeLayout click_RelativeLayout2;
        private RelativeLayout click_RelativeLayout3;
        public ImageView down_ImageView;
        public TextView name_TextView;
        private TextView num_TextView;
        private RelativeLayout relativeLayout;
        private TextView time_TextView;
        public ImageView up_ImageView;
        private LinearLayout view_LinearLayout;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_macroprogramingsetting);
        initdata();
        initui();
        listInitView();
        this.mIntentFilter = new IntentFilter();
        this.mIntentFilter.addAction(BluetoothLeService.ACTION_DATA_MACRO);
    }

    private void initdata() {
        listItemColorId = new ArrayList();
        listItemTransparentColorId = new ArrayList();
        listItemColorId.clear();
        listItemTransparentColorId.clear();
        listItemColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.green)));
        listItemTransparentColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.green_transparent)));
        listItemColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.purple)));
        listItemTransparentColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.purple_transparent)));
        listItemColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.blue)));
        listItemTransparentColorId.add(Integer.valueOf(getResources().getColor(C0182R.color.blue_transparent)));
        this.macroMember = (MacroMember) getIntent().getExtras().getSerializable("MacroMember");
        AppUtils.keyObject = null;
    }

    private List<MacroMemberValue> viewDataTurnMacroMemberValue() {
        int i;
        int totalTime = 0;
        int listViewDatalen = this.listViewData.size();
        List<KeyData> listKeyData = new ArrayList();
        for (i = 0; i < listViewDatalen; i++) {
            ViewData viewData = (ViewData) this.listViewData.get(i);
            KeyData keyData = new KeyData();
            keyData.name = viewData.nameStr;
            keyData.value = viewData.value;
            totalTime += viewData.timeValue;
            keyData.absolutelyTime = totalTime;
            if (viewData.type == 0) {
                keyData.type = 1;
            } else if (viewData.type == 1) {
                keyData.type = 2;
            } else {
                keyData.type = 3;
            }
            listKeyData.add(keyData);
        }
        List<MacroMemberValue> listMacroMemberValue = new ArrayList();
        int listKeyDatalen = listKeyData.size();
        for (i = 0; i < listKeyDatalen; i++) {
            keyData = (KeyData) listKeyData.get(i);
            MacroMemberValue macroMemberValue;
            if (keyData.type == 1) {
                int i2 = i;
                for (i2 = i; i2 < listKeyDatalen; i2++) {
                    KeyData keyData_up = (KeyData) listKeyData.get(i2);
                    if (keyData_up.type == 2 && keyData_up.value == keyData.value) {
                        macroMemberValue = new MacroMemberValue();
                        macroMemberValue.downTime = keyData.absolutelyTime;
                        macroMemberValue.upTime = keyData_up.absolutelyTime;
                        macroMemberValue.value = keyData.value;
                        macroMemberValue.valueStr = keyData.name;
                        macroMemberValue.valueType = MacroMemberValue.MACROVALUE_TYPE_KEY;
                        macroMemberValue.memberId = this.macroMember._id;
                        listMacroMemberValue.add(macroMemberValue);
                        break;
                    }
                }
            } else if (keyData.type == 3) {
                macroMemberValue = new MacroMemberValue();
                macroMemberValue.downTime = keyData.absolutelyTime;
                macroMemberValue.upTime = keyData.absolutelyTime;
                macroMemberValue.value = keyData.value;
                macroMemberValue.valueStr = keyData.name;
                macroMemberValue.valueType = MacroMemberValue.MACROVALUE_TYPE_MEDIA;
                macroMemberValue.memberId = this.macroMember._id;
                listMacroMemberValue.add(macroMemberValue);
            }
        }
        return listMacroMemberValue;
    }

    public void onClick(View arg0) {
        int i;
        ViewData a;
        ViewData b;
        switch (arg0.getId()) {
            case C0182R.id.back_RelativeLayout:
                finish();
                return;
            case C0182R.id.finish_TextView:
                if (judgeMacroNum()) {
                    Toast.makeText(this, getResources().getString(C0182R.string.macro_support_most_20_key_tip), 0).show();
                    return;
                } else if (this.triggerkey_TextView.getText().toString() == null || this.triggerkey_TextView.getText().toString().isEmpty()) {
                    Toast.makeText(this, getResources().getString(C0182R.string.please_input_tigger_key), 0).show();
                    return;
                } else {
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, "保存中...");
                    this.macroMember.macromemberName = this.macroName_EditText.getText().toString();
                    if (this.macroMember.macromemberName == null || this.macroMember.macromemberName.isEmpty()) {
                        this.macroMember.macromemberName = getResources().getString(C0182R.string.no_name);
                    }
                    this.macroMember.keyWord = this.triggerkey_TextView.getText().toString();
                    this.macroMember.triggerway = MacroMember.TRIGGERWAY_UP;
                    AppUtils.databaseManager.modifyMacroMember(this.macroMember);
                    AppUtils.databaseManager.deleteAllMacroMemberValue(this.macroMember._id);
                    List<MacroMemberValue> lisMacroMemberValues = viewDataTurnMacroMemberValue();
                    int listMacroMemberValuelen = lisMacroMemberValues.size();
                    byte[] data = new byte[(listMacroMemberValuelen * 5)];
                    for (i = 0; i < listMacroMemberValuelen; i++) {
                        MacroMemberValue macroMemberValue = (MacroMemberValue) lisMacroMemberValues.get(i);
                        AppUtils.databaseManager.addMacroMemberValue(macroMemberValue);
                        data[(i * 5) + 0] = (byte) macroMemberValue.value;
                        data[(i * 5) + 1] = (byte) (macroMemberValue.downTime & MotionEventCompat.ACTION_MASK);
                        data[(i * 5) + 2] = (byte) ((macroMemberValue.downTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                        data[(i * 5) + 3] = (byte) (macroMemberValue.upTime & MotionEventCompat.ACTION_MASK);
                        data[(i * 5) + 4] = (byte) ((macroMemberValue.upTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                    }
                    int crcID = CRC16.calcCrc16(data);
                    C0188L.m7i("xxxx crcID = " + crcID);
                    this.macroMember.crcID = crcID;
                    AppUtils.databaseManager.modifyMacroMember(this.macroMember);
                    if (this.progressDialog.isShowing()) {
                        this.progressDialog.cancel();
                    }
                    Toast.makeText(this, getResources().getString(C0182R.string.save_success), 0).show();
                    if (this.macroMember.isOn == 1) {
                        AppUtils.macroIsNewFlag = false;
                    }
                    finish();
                    return;
                }
            case C0182R.id.triggerkey_RelativeLayout:
                AppUtils.jumpToSelectKeyPage_Type = 3;
                startActivity(new Intent(this, Activity_SelectedKey.class));
                return;
            case C0182R.id.recordMacro_RelativeLayout:
            case C0182R.id.recordMacro_TextView:
                if (BluetoothLeService.getInstance().isConnectedOk()) {
                    this.startRecordMacroFlag = -1;
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, getResources().getString(C0182R.string.open_macro_record));
                    new Thread(new C01143()).start();
                    this.lastValue = new int[8];
                    this.startTime = System.currentTimeMillis();
                    for (i = 0; i < 8; i++) {
                        this.lastValue[i] = 0;
                    }
                    BLEHandle.startRecordMacro(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1));
                    return;
                }
                return;
            case C0182R.id.RelativeLayout_1:
                int len = this.listViewData.size();
                if (this.selectedItem >= 0 && this.selectedItem < len - 1) {
                    a = new ViewData();
                    a.nameStr = ((ViewData) this.listViewData.get(this.selectedItem)).nameStr;
                    a.type = ((ViewData) this.listViewData.get(this.selectedItem)).type;
                    a.value = ((ViewData) this.listViewData.get(this.selectedItem)).value;
                    a.timeValue = ((ViewData) this.listViewData.get(this.selectedItem)).timeValue;
                    b = new ViewData();
                    b.nameStr = ((ViewData) this.listViewData.get(this.selectedItem + 1)).nameStr;
                    b.type = ((ViewData) this.listViewData.get(this.selectedItem + 1)).type;
                    b.value = ((ViewData) this.listViewData.get(this.selectedItem + 1)).value;
                    b.timeValue = ((ViewData) this.listViewData.get(this.selectedItem + 1)).timeValue;
                    if (a.type == 0 && b.type == 1 && a.value == b.value) {
                        Toast.makeText(this, getResources().getString(C0182R.string.macro_cant_move), 0).show();
                        return;
                    }
                    this.listViewData.set(this.selectedItem, b);
                    this.listViewData.set(this.selectedItem + 1, a);
                    this.selectedItem++;
                    this.adapter.notifyDataSetChanged();
                    return;
                }
                return;
            case C0182R.id.RelativeLayout_2:
                if (this.selectedItem > 0) {
                    a = new ViewData();
                    a.nameStr = ((ViewData) this.listViewData.get(this.selectedItem)).nameStr;
                    a.type = ((ViewData) this.listViewData.get(this.selectedItem)).type;
                    a.value = ((ViewData) this.listViewData.get(this.selectedItem)).value;
                    a.timeValue = ((ViewData) this.listViewData.get(this.selectedItem)).timeValue;
                    b = new ViewData();
                    b.nameStr = ((ViewData) this.listViewData.get(this.selectedItem - 1)).nameStr;
                    b.type = ((ViewData) this.listViewData.get(this.selectedItem - 1)).type;
                    b.value = ((ViewData) this.listViewData.get(this.selectedItem - 1)).value;
                    b.timeValue = ((ViewData) this.listViewData.get(this.selectedItem - 1)).timeValue;
                    if (a.type == 1 && b.type == 0 && a.value == b.value) {
                        Toast.makeText(this, getResources().getString(C0182R.string.macro_cant_move), 0).show();
                        return;
                    }
                    this.listViewData.set(this.selectedItem, b);
                    this.listViewData.set(this.selectedItem - 1, a);
                    this.selectedItem--;
                    this.adapter.notifyDataSetChanged();
                    return;
                }
                return;
            case C0182R.id.RelativeLayout_3:
                if (this.selectedItem >= 0) {
                    ViewData viewData_del = (ViewData) this.listViewData.get(this.selectedItem);
                    if (viewData_del.type == 0) {
                        i = this.selectedItem + 1;
                        while (i < this.listViewData.size()) {
                            if (viewData_del.value == ((ViewData) this.listViewData.get(i)).value && ((ViewData) this.listViewData.get(i)).type == 1) {
                                this.listViewData.remove(i);
                                this.listViewData.remove(this.selectedItem);
                            } else {
                                i++;
                            }
                        }
                    } else if (viewData_del.type == 1) {
                        i = this.selectedItem - 1;
                        while (i >= 0) {
                            if (viewData_del.value == ((ViewData) this.listViewData.get(i)).value && ((ViewData) this.listViewData.get(i)).type == 0) {
                                this.listViewData.remove(this.selectedItem);
                                this.listViewData.remove(i);
                            } else {
                                i--;
                            }
                        }
                    } else {
                        this.listViewData.remove(this.selectedItem);
                    }
                    this.selectedItem = -1;
                    this.adapter.notifyDataSetChanged();
                    return;
                }
                return;
            case C0182R.id.RelativeLayout_4:
                AppUtils.jumpToSelectKeyPage_Type = 8;
                startActivity(new Intent(this, Activity_SelectedKey.class));
                return;
            case C0182R.id.RelativeLayout_5:
                if (this.selectedItem >= 0) {
                    ViewData viewData = (ViewData) this.listViewData.get(this.selectedItem);
                    if (viewData.type == 0) {
                        AppUtils.jumpToSelectKeyPage_Type = 6;
                        startActivity(new Intent(this, Activity_SelectedKey.class));
                        return;
                    } else if (viewData.type == 1) {
                        AppUtils.jumpToSelectKeyPage_Type = 7;
                        startActivity(new Intent(this, Activity_SelectedKey.class));
                        return;
                    } else if (viewData.type == 2) {
                        AppUtils.jumpToSelectKeyPage_Type = 5;
                        startActivity(new Intent(this, Activity_SelectedKey.class));
                        return;
                    } else {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private void initui() {
        this.recordMacro_tip_TextView = (TextView) findViewById(C0182R.id.recordMacro_tip_TextView);
        this.recordMacro_tip_TextView.setVisibility(4);
        this.recordMacro_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.recordMacro_RelativeLayout);
        this.RelativeLayout_1 = (RelativeLayout) findViewById(C0182R.id.RelativeLayout_1);
        this.RelativeLayout_2 = (RelativeLayout) findViewById(C0182R.id.RelativeLayout_2);
        this.RelativeLayout_3 = (RelativeLayout) findViewById(C0182R.id.RelativeLayout_3);
        this.RelativeLayout_4 = (RelativeLayout) findViewById(C0182R.id.RelativeLayout_4);
        this.RelativeLayout_5 = (RelativeLayout) findViewById(C0182R.id.RelativeLayout_5);
        this.RelativeLayout_1.setOnClickListener(this);
        this.RelativeLayout_2.setOnClickListener(this);
        this.RelativeLayout_3.setOnClickListener(this);
        this.RelativeLayout_4.setOnClickListener(this);
        this.RelativeLayout_5.setOnClickListener(this);
        this.triggerkey_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.triggerkey_RelativeLayout);
        this.triggerkey_RelativeLayout.setOnClickListener(this);
        this.gridView = (GridView) findViewById(C0182R.id.gridView);
        this.back_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.back_RelativeLayout);
        this.back_RelativeLayout.setOnClickListener(this);
        this.finish_TextView = (TextView) findViewById(C0182R.id.finish_TextView);
        this.finish_TextView.setOnClickListener(this);
        this.macroName_EditText = (EditText) findViewById(C0182R.id.macroName_EditText);
        this.macroName_EditText.setText(this.macroMember.macromemberName);
        this.macroName_EditText.clearFocus();
        this.triggerkey_TextView = (TextView) findViewById(C0182R.id.triggerkey_TextView);
        this.triggerkey_TextView.setText(this.macroMember.keyWord);
        this.triggerkey_TextView.clearFocus();
        this.recordMacro_TextView = (TextView) findViewById(C0182R.id.recordMacro_TextView);
        this.recordMacro_TextView.setOnClickListener(this);
        this.listViewData = new ArrayList();
        this.listViewData.clear();
        this.adapter = new MyGridAdapter(this, this.listViewData);
        this.gridView.setAdapter(this.adapter);
        this.gridView.setFocusable(false);
        this.adapter.notifyDataSetChanged();
        View view = getWindow().peekDecorView();
        if (view != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!(event.getAction() != 0 || getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null)) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        return super.onTouchEvent(event);
    }

    public void handleKeyValue(int[] dataValue) {
        int disTime;
        if (this.listRecoedViewData.size() == 0) {
            this.startTime = System.currentTimeMillis();
            this.lastTime = System.currentTimeMillis();
            disTime = 0;
        } else {
            disTime = (int) (System.currentTimeMillis() - this.lastTime);
        }
        this.lastTime = System.currentTimeMillis();
        String tip;
        String num_tip;
        String time_tip;
        if (System.currentTimeMillis() - this.startTime > 60000) {
            this.recordMacro_tip_TextView.setVisibility(0);
            tip = this.recordMacro_tip_TextView.getText().toString();
            num_tip = getResources().getString(C0182R.string.macro_support_most_20_key_tip);
            time_tip = getResources().getString(C0182R.string.macro_support_record_60_sec);
            if (tip == null || tip.length() != num_tip.length() + time_tip.length()) {
                this.recordMacro_tip_TextView.setText(time_tip);
                return;
            } else {
                this.recordMacro_tip_TextView.setText(new StringBuilder(String.valueOf(num_tip)).append(time_tip).toString());
                return;
            }
        }
        if (judgeMacroNum()) {
            this.recordMacro_tip_TextView.setVisibility(0);
            tip = this.recordMacro_tip_TextView.getText().toString();
            num_tip = getResources().getString(C0182R.string.macro_support_most_20_key_tip);
            time_tip = getResources().getString(C0182R.string.macro_support_record_60_sec);
            if (tip == null || tip.length() != num_tip.length() + time_tip.length()) {
                this.recordMacro_tip_TextView.setText(num_tip);
            } else {
                this.recordMacro_tip_TextView.setText(new StringBuilder(String.valueOf(num_tip)).append(time_tip).toString());
            }
        }
        int i = 0;
        while (i < 8) {
            if (this.lastValue[i] != dataValue[i]) {
                ViewData viewData_key;
                if (i == 0) {
                    if ((this.lastValue[i] & 1) != (dataValue[i] & 1)) {
                        if ((this.lastValue[i] & 1) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLCTRL.keyValue;
                            viewData_key.type = 0;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            viewData_key.timeValue = disTime;
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLCTRL.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 2) != (dataValue[i] & 2)) {
                        if ((this.lastValue[i] & 2) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLSHIFT.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLSHIFT.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 4) != (dataValue[i] & 4)) {
                        if ((this.lastValue[i] & 4) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLALT.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLALT.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 8) != (dataValue[i] & 8)) {
                        if ((this.lastValue[i] & 8) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLWin.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectLWin.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 16) != (dataValue[i] & 16)) {
                        if ((this.lastValue[i] & 16) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRCTRL.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRCTRL.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 32) != (dataValue[i] & 32)) {
                        if ((this.lastValue[i] & 32) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRSHIFT.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRSHIFT.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 64) != (dataValue[i] & 64)) {
                        if ((this.lastValue[i] & 64) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRALT.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRALT.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                    if ((this.lastValue[i] & 128) != (dataValue[i] & 128)) {
                        if ((this.lastValue[i] & 128) == 0) {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRWin.keyValue;
                            viewData_key.type = 0;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        } else {
                            viewData_key = new ViewData();
                            viewData_key.value = KeyObjectUtil.keyObjectRWin.keyValue;
                            viewData_key.type = 1;
                            viewData_key.timeValue = disTime;
                            viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(viewData_key.value));
                            this.listRecoedViewData.add(viewData_key);
                        }
                    }
                } else if (this.lastValue[i] == 0 && dataValue[i] != 0) {
                    viewData_key = new ViewData();
                    viewData_key.value = dataValue[i];
                    viewData_key.type = 0;
                    viewData_key.timeValue = disTime;
                    viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(dataValue[i]));
                    this.listRecoedViewData.add(viewData_key);
                } else if (this.lastValue[i] == 0 || dataValue[i] != 0) {
                    ViewData viewData_up_key = new ViewData();
                    viewData_up_key.value = this.lastValue[i];
                    viewData_up_key.type = 1;
                    viewData_up_key.timeValue = disTime;
                    viewData_up_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(this.lastValue[i]));
                    this.listRecoedViewData.add(viewData_up_key);
                    ViewData viewData_down_key = new ViewData();
                    viewData_down_key.value = dataValue[i];
                    viewData_down_key.type = 0;
                    viewData_down_key.timeValue = disTime;
                    viewData_down_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(dataValue[i]));
                    this.listRecoedViewData.add(viewData_down_key);
                } else {
                    viewData_key = new ViewData();
                    viewData_key.value = this.lastValue[i];
                    viewData_key.type = 1;
                    viewData_key.timeValue = disTime;
                    viewData_key.nameStr = (String) KeyObjectUtil.keyValueAndStringMap.get(Integer.valueOf(this.lastValue[i]));
                    this.listRecoedViewData.add(viewData_key);
                }
            }
            i++;
        }
        for (i = 0; i < 8; i++) {
            this.lastValue[i] = dataValue[i];
        }
    }

    private void circleViewRun(int data) {
        C0188L.m7i("data = " + data);
        for (int i = 0; i < 5; i++) {
            if (i == data) {
                ((CircleView) this.listCircleView.get(i)).setVisibility(4);
            } else {
                ((CircleView) this.listCircleView.get(i)).setVisibility(0);
            }
        }
    }

    private boolean judgeMacroNum() {
        int keyNum = 0;
        int len = this.listViewData.size();
        int i = 0;
        while (i < len) {
            if (((ViewData) this.listViewData.get(i)).type == 2 || ((ViewData) this.listViewData.get(i)).type == 0) {
                keyNum++;
            }
            i++;
        }
        if (keyNum >= 20) {
            return true;
        }
        return false;
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    protected void onResume() {
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        if (AppUtils.jumpToSelectKeyPage_Type == 3) {
            if (AppUtils.keyObject != null) {
                this.macroMember.keyValue = AppUtils.keyObject.keyValue;
                this.triggerkey_TextView.setText(AppUtils.keyObject.keyStr);
                AppUtils.keyObject = null;
            }
        } else if (AppUtils.jumpToSelectKeyPage_Type == 4) {
            if (AppUtils.keyObject != null) {
                if (this.selectedItem >= 0) {
                    viewData = new ViewData();
                    viewData.nameStr = AppUtils.keyObject.keyStr;
                    viewData.value = AppUtils.keyObject.keyValue;
                    viewData.type = 2;
                    if (this.listViewData.size() - 1 == this.selectedItem) {
                        this.listViewData.add(viewData);
                    } else {
                        this.listViewData.add(this.selectedItem + 1, viewData);
                    }
                    this.adapter.notifyDataSetChanged();
                } else {
                    viewData = new ViewData();
                    viewData.nameStr = AppUtils.keyObject.keyStr;
                    viewData.value = AppUtils.keyObject.keyValue;
                    viewData.type = 2;
                    this.listViewData.add(viewData);
                    this.adapter.notifyDataSetChanged();
                }
                AppUtils.keyObject = null;
            }
        } else if (AppUtils.jumpToSelectKeyPage_Type == 5) {
            if (AppUtils.keyObject != null) {
                if (this.selectedItem >= 0) {
                    viewData = (ViewData) this.listViewData.get(this.selectedItem);
                    viewData.nameStr = AppUtils.keyObject.keyStr;
                    viewData.value = AppUtils.keyObject.keyValue;
                    viewData.type = 2;
                    this.listViewData.set(this.selectedItem, viewData);
                    this.adapter.notifyDataSetChanged();
                }
                AppUtils.keyObject = null;
            }
        } else if (AppUtils.jumpToSelectKeyPage_Type == 6) {
            if (AppUtils.keyObject != null) {
                if (((ViewData) this.listViewData.get(this.selectedItem)).value != AppUtils.keyObject.keyValue) {
                    viewData_original = (ViewData) this.listViewData.get(this.selectedItem);
                    viewData_original.nameStr = AppUtils.keyObject.keyStr;
                    viewData_original.value = AppUtils.keyObject.keyValue;
                    viewData_original.type = 0;
                    this.listViewData.set(this.selectedItem, viewData_original);
                    len = this.listViewData.size();
                    i = this.selectedItem + 1;
                    while (i < len) {
                        if (((ViewData) this.listViewData.get(i)).value == viewData_original.value && ((ViewData) this.listViewData.get(i)).type == 1) {
                            viewData_new = (ViewData) this.listViewData.get(i);
                            viewData_new.nameStr = AppUtils.keyObject.keyStr;
                            viewData_new.value = AppUtils.keyObject.keyValue;
                            viewData_new.type = 1;
                            this.listViewData.set(i, viewData_new);
                            break;
                        }
                        i++;
                    }
                    this.adapter.notifyDataSetChanged();
                }
                AppUtils.keyObject = null;
            }
        } else if (AppUtils.jumpToSelectKeyPage_Type == 7) {
            if (AppUtils.keyObject != null) {
                if (((ViewData) this.listViewData.get(this.selectedItem)).value != AppUtils.keyObject.keyValue) {
                    viewData_original = (ViewData) this.listViewData.get(this.selectedItem);
                    viewData_original.nameStr = AppUtils.keyObject.keyStr;
                    viewData_original.value = AppUtils.keyObject.keyValue;
                    viewData_original.type = 1;
                    this.listViewData.set(this.selectedItem, viewData_original);
                    len = this.listViewData.size();
                    i = this.selectedItem - 1;
                    while (i >= 0) {
                        if (((ViewData) this.listViewData.get(i)).value == viewData_original.value && ((ViewData) this.listViewData.get(i)).type == 0) {
                            viewData_new = (ViewData) this.listViewData.get(i);
                            viewData_new.nameStr = AppUtils.keyObject.keyStr;
                            viewData_new.value = AppUtils.keyObject.keyValue;
                            viewData_new.type = 0;
                            this.listViewData.set(i, viewData_new);
                            break;
                        }
                        i--;
                    }
                    this.adapter.notifyDataSetChanged();
                }
                AppUtils.keyObject = null;
            }
        } else if (AppUtils.jumpToSelectKeyPage_Type == 8 && AppUtils.keyObject != null) {
            ViewData viewData_up = new ViewData();
            viewData_up.nameStr = AppUtils.keyObject.keyStr;
            viewData_up.value = AppUtils.keyObject.keyValue;
            viewData_up.type = 1;
            viewData_up.timeValue = 100;
            ViewData viewData_keydown = new ViewData();
            viewData_keydown.nameStr = AppUtils.keyObject.keyStr;
            viewData_keydown.value = AppUtils.keyObject.keyValue;
            viewData_keydown.type = 0;
            viewData_keydown.timeValue = 100;
            if (this.selectedItem < 0 || this.selectedItem == this.listViewData.size() - 1) {
                this.listViewData.add(viewData_keydown);
                this.listViewData.add(viewData_up);
            } else {
                this.listViewData.add(this.selectedItem + 1, viewData_up);
                this.listViewData.add(this.selectedItem + 1, viewData_keydown);
            }
            this.adapter.notifyDataSetChanged();
            AppUtils.keyObject = null;
        }
        super.onResume();
    }

    private void listInitView() {
        int i;
        this.listViewData.clear();
        List<MacroMemberValue> listMacroMemberValuesqlData = AppUtils.databaseManager.findAllMacroMemberValue(this.macroMember._id);
        int valuelistlenth = listMacroMemberValuesqlData.size();
        List<KeyData> listKeyData = new ArrayList();
        for (i = 0; i < valuelistlenth; i++) {
            MacroMemberValue macroMemberValue = (MacroMemberValue) listMacroMemberValuesqlData.get(i);
            if (macroMemberValue.valueType == MacroMemberValue.MACROVALUE_TYPE_KEY) {
                KeyData keyData_down = new KeyData();
                keyData_down.name = macroMemberValue.valueStr;
                keyData_down.value = macroMemberValue.value;
                keyData_down.type = 1;
                keyData_down.absolutelyTime = macroMemberValue.downTime;
                KeyData keyData_up = new KeyData();
                keyData_up.name = macroMemberValue.valueStr;
                keyData_up.value = macroMemberValue.value;
                keyData_up.type = 2;
                keyData_up.absolutelyTime = macroMemberValue.upTime;
                listKeyData.add(keyData_down);
                listKeyData.add(keyData_up);
            } else {
                KeyData keyData_media = new KeyData();
                keyData_media.name = macroMemberValue.valueStr;
                keyData_media.value = macroMemberValue.value;
                keyData_media.type = 3;
                keyData_media.absolutelyTime = macroMemberValue.downTime;
                listKeyData.add(keyData_media);
            }
        }
        Collections.sort(listKeyData, new KeyDataComparator());
        int listKeyDatalen = listKeyData.size();
        int tootalTime = 0;
        for (i = 0; i < listKeyDatalen; i++) {
            KeyData keyData = (KeyData) listKeyData.get(i);
            int disTime = keyData.absolutelyTime - tootalTime;
            tootalTime = keyData.absolutelyTime;
            ViewData viewData = new ViewData();
            viewData.nameStr = keyData.name;
            viewData.value = keyData.value;
            viewData.timeValue = disTime;
            if (keyData.type == 1) {
                viewData.type = 0;
            } else if (keyData.type == 2) {
                viewData.type = 1;
            } else {
                viewData.type = 2;
            }
            this.listViewData.add(viewData);
        }
        this.adapter.notifyDataSetChanged();
    }
}
