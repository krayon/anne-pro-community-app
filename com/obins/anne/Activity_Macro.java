package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.MacroMember;
import com.obins.anne.db.MacroMemberValue;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.BLEHandle;
import com.obins.anne.utils.C0188L;
import com.obins.anne.utils.SendBigBLEData.OnFinishListener;
import com.obins.anne.viewpart.CircleView;
import java.util.ArrayList;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;

public class Activity_Macro extends BaseActivity implements OnClickListener {
    private static final int DOWNLOAD_DIS_TIME = 50;
    private static final int DOWNLOAD_MACRO_BACK = 1;
    private static final int DOWNLOAD_MACRO_TIMEOUT = 0;
    private static String TAG = "Activity_Macro";
    private static boolean first_in_page = true;
    private static List<Integer> listItemColorId = null;
    private static List<Integer> listItemTransparentColorId = null;
    private MyAdapter adapter = null;
    private RelativeLayout add_RelativeLayout;
    private RelativeLayout backBtn;
    private int downLoadNum = 0;
    private int downloadMacroAckNum = 0;
    private List<MacroMember> listMacroMember = null;
    List<MacroMemberValue> listMacroMemberValue;
    private ListView listview;
    private final BroadcastReceiver mGattUpdateReceiver = new C00981();
    public Handler mHandler = new C00992();
    private IntentFilter mIntentFilter;
    private int macroMemberPosition = 0;
    private ProgressDialog progressDialog;
    private int selectedItem = -1;
    private CircleView tip_CircleView;
    private RelativeLayout use_RelativeLayout;
    private TextView use_TextView;

    class C00981 extends BroadcastReceiver {
        C00981() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(Activity_Macro.TAG, "action: " + action);
            if (BluetoothLeService.ACTION_DATA_MACRO.equals(action)) {
                String type = intent.getStringExtra(BluetoothLeService.EXTRA_TYPE);
                C0188L.m8i(Activity_Macro.TAG, "type: " + type);
                if (type.equals(BluetoothLeService.EXTRA_TYPE_MACRO_DOWNLOAD_ACK)) {
                    Activity_Macro activity_Macro = Activity_Macro.this;
                    activity_Macro.downloadMacroAckNum = activity_Macro.downloadMacroAckNum + 1;
                }
            }
        }
    }

    class C00992 extends Handler {
        C00992() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (Activity_Macro.this.progressDialog.isShowing()) {
                        Activity_Macro.this.progressDialog.cancel();
                    }
                    if (Activity_Macro.this.downloadMacroAckNum == 10) {
                        AppUtils.macroIsNewFlag = true;
                        Activity_Macro.this.tip_CircleView.setVisibility(4);
                        Toast.makeText(Activity_Macro.this, Activity_Macro.this.getResources().getString(C0182R.string.control_success), 0).show();
                        return;
                    }
                    Toast.makeText(Activity_Macro.this, Activity_Macro.this.getResources().getString(C0182R.string.control_faile), 0).show();
                    return;
                default:
                    return;
            }
        }
    }

    class C01003 implements OnClickListener {
        C01003() {
        }

        public void onClick(View arg0) {
            Activity_Macro.this.finish();
        }
    }

    class C01014 implements Runnable {
        C01014() {
        }

        public void run() {
            try {
                int num = Activity_Macro.this.getMacroOnNum();
                Thread.sleep((long) ((num * 1750) + ((10 - num) * 600)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Message message = new Message();
            message.what = 0;
            Activity_Macro.this.mHandler.sendMessage(message);
        }
    }

    class C01025 implements Runnable {
        C01025() {
        }

        public void run() {
            try {
                Thread.sleep(350);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Activity_Macro.this.downLoadMacro();
        }
    }

    public class MyAdapter extends BaseAdapter {
        private List<MacroMember> data;
        private LayoutInflater mInflater;

        public MyAdapter(Context context, List<MacroMember> data) {
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
                convertView = this.mInflater.inflate(C0182R.layout.fragment_macroprograming_listitem, null);
                holder.order_TextView = (TextView) convertView.findViewById(C0182R.id.order_TextView);
                holder.name_TextView = (TextView) convertView.findViewById(C0182R.id.name_TextView);
                holder.selected_ImageView = (ImageView) convertView.findViewById(C0182R.id.selected_ImageView);
                holder.selected_key_ImageView = (ImageView) convertView.findViewById(C0182R.id.selected_key_ImageView);
                holder.right_RelativeLayout = (RelativeLayout) convertView.findViewById(C0182R.id.right_RelativeLayout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.order_TextView.setText(((MacroMember) this.data.get(position)).keyWord);
            holder.name_TextView.setText(((MacroMember) this.data.get(position)).macromemberName);
            if (((MacroMember) this.data.get(position)).macromemberName == null || ((MacroMember) this.data.get(position)).macromemberName.isEmpty()) {
                holder.name_TextView.setText(Activity_Macro.this.getResources().getString(C0182R.string.no_name));
            }
            if (((MacroMember) this.data.get(position)).isOn == 1) {
                holder.selected_ImageView.setBackground(Activity_Macro.this.getResources().getDrawable(C0182R.drawable.micro_check_on));
                holder.selected_key_ImageView.setBackground(Activity_Macro.this.getResources().getDrawable(C0182R.drawable.micro_list_bg_on));
            } else {
                holder.selected_ImageView.setBackground(Activity_Macro.this.getResources().getDrawable(C0182R.drawable.micro_check_off));
                holder.selected_key_ImageView.setBackground(Activity_Macro.this.getResources().getDrawable(C0182R.drawable.micro_list_bg_off));
            }
            holder.right_RelativeLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    MacroMember macroMember = (MacroMember) MyAdapter.this.data.get(position);
                    if (macroMember.isOn == 1) {
                        macroMember.isOn = 0;
                    } else if (Activity_Macro.this.getMacroOnNum() >= 10) {
                        Toast.makeText(Activity_Macro.this, Activity_Macro.this.getResources().getString(C0182R.string.macro_support_most_10_tip), 0).show();
                        return;
                    } else {
                        macroMember.isOn = 1;
                    }
                    AppUtils.macroIsNewFlag = false;
                    AppUtils.databaseManager.modifyMacroMember(macroMember);
                    Activity_Macro.this.flushList();
                }
            });
            convertView.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    MacroMember macroMember = (MacroMember) Activity_Macro.this.listMacroMember.get(position);
                    Intent intent = new Intent(Activity_Macro.this, Activity_MacroValueNew.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("MacroMember", macroMember);
                    C0188L.m7i("macroMember.crcID = " + macroMember.crcID);
                    intent.putExtras(bundle);
                    Activity_Macro.this.startActivity(intent);
                }
            });
            convertView.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View arg0) {
                    final AlertDialog alertDialog = new Builder(Activity_Macro.this).create();
                    alertDialog.show();
                    Window window = alertDialog.getWindow();
                    window.setContentView(C0182R.layout.delete_prompt);
                    ((TextView) window.findViewById(C0182R.id.title_TextView)).setText(Activity_Macro.this.getResources().getString(C0182R.string.tip));
                    ((TextView) window.findViewById(C0182R.id.content_TextView)).setText(Activity_Macro.this.getResources().getString(C0182R.string.macro_delete_tip));
                    Button delete_Button = (Button) window.findViewById(C0182R.id.delete_Button);
                    delete_Button.setText(Activity_Macro.this.getResources().getString(C0182R.string.sure));
                    Button cancle_Button = (Button) window.findViewById(C0182R.id.cancle_Button);
                    cancle_Button.setText(Activity_Macro.this.getResources().getString(C0182R.string.cancle));
                    final int i = position;
                    delete_Button.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            if (((MacroMember) Activity_Macro.this.listMacroMember.get(i)).isOn == 1) {
                                AppUtils.macroIsNewFlag = false;
                            }
                            AppUtils.databaseManager.deleteMacroMember(((MacroMember) Activity_Macro.this.listMacroMember.get(i))._id);
                            Activity_Macro.this.flushList();
                            alertDialog.cancel();
                        }
                    });
                    cancle_Button.setOnClickListener(new OnClickListener() {
                        public void onClick(View arg0) {
                            alertDialog.cancel();
                        }
                    });
                    return false;
                }
            });
            return convertView;
        }
    }

    public final class ViewHolder {
        public TextView name_TextView;
        public TextView order_TextView;
        public RelativeLayout right_RelativeLayout;
        private ImageView selected_ImageView;
        private ImageView selected_key_ImageView;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_macro);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01003());
        first_in_page = true;
        initdata();
        initui();
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
    }

    private void initui() {
        this.tip_CircleView = (CircleView) findViewById(C0182R.id.tip_CircleView);
        this.use_TextView = (TextView) findViewById(C0182R.id.use_TextView);
        this.use_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.use_RelativeLayout);
        this.use_RelativeLayout.setOnClickListener(this);
        this.listview = (ListView) findViewById(C0182R.id.listview);
        this.add_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.add_RelativeLayout);
        this.add_RelativeLayout.setOnClickListener(this);
        View view = getWindow().peekDecorView();
        if (view != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        this.listMacroMember = new ArrayList();
        this.listMacroMember.clear();
        this.adapter = new MyAdapter(this, this.listMacroMember);
        this.listview.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!(event.getAction() != 0 || getCurrentFocus() == null || getCurrentFocus().getWindowToken() == null)) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        return super.onTouchEvent(event);
    }

    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        unregisterReceiver(this.mGattUpdateReceiver);
    }

    protected void onResume() {
        super.onResume();
        registerReceiver(this.mGattUpdateReceiver, this.mIntentFilter);
        flushList();
    }

    private void flushList() {
        this.listMacroMember.clear();
        List<MacroMember> listMacroMembersqlData = AppUtils.databaseManager.findAllMacroMember();
        int listlenth = listMacroMembersqlData.size();
        for (int i = 0; i < listlenth; i++) {
            this.listMacroMember.add((MacroMember) listMacroMembersqlData.get(i));
        }
        if (first_in_page && !AppUtils.macroIsNewFlag) {
            first_in_page = false;
            Toast.makeText(this, getResources().getString(C0182R.string.macro_modify_tip), 0).show();
        }
        if (AppUtils.macroIsNewFlag) {
            this.tip_CircleView.setVisibility(4);
        } else {
            this.tip_CircleView.setVisibility(0);
        }
        this.adapter.notifyDataSetChanged();
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.add_RelativeLayout:
                View view = getWindow().peekDecorView();
                if (view != null) {
                    ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                MacroMember macroMember = new MacroMember();
                macroMember.isOn = 0;
                macroMember.crcID = 0;
                macroMember.keyIndex = 0;
                macroMember.keyValue = 0;
                macroMember.keyWord = AppConfig.SERVER_IP;
                macroMember.macromemberName = AppConfig.SERVER_IP;
                macroMember.triggerway = MacroMember.TRIGGERWAY_DOWN;
                macroMember._id = AppUtils.databaseManager.addMacroMember(macroMember);
                Intent intent = new Intent(this, Activity_MacroValueNew.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("MacroMember", macroMember);
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            case C0182R.id.use_RelativeLayout:
                if (!BluetoothLeService.getInstance().isConnectedOk()) {
                    return;
                }
                if (this.progressDialog == null || !this.progressDialog.isShowing()) {
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, getResources().getString(C0182R.string.macro_download_tip));
                    new Thread(new C01014()).start();
                    this.downLoadNum = 0;
                    this.macroMemberPosition = 0;
                    this.downloadMacroAckNum = 0;
                    if (getMacroOnNum() != 0) {
                        downLoadMacro();
                        return;
                    } else {
                        downLoadMacro();
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    public void closeMacro(int num) {
        BLEHandle.downLoadMacro(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), new byte[]{(byte) num, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, null);
    }

    public int getMacroOnNum() {
        int result = 0;
        for (int i = 0; i < this.listMacroMember.size(); i++) {
            if (((MacroMember) this.listMacroMember.get(i)).isOn == 1) {
                result++;
            }
        }
        return result;
    }

    public void downLoadMacro() {
        int len = this.listMacroMember.size();
        if (this.downLoadNum <= 9) {
            int i;
            boolean is_exit = false;
            for (i = this.macroMemberPosition; i < len; i++) {
                if (((MacroMember) this.listMacroMember.get(i)).isOn == 1) {
                    this.macroMemberPosition = i;
                    is_exit = true;
                    break;
                }
            }
            if (is_exit) {
                this.listMacroMemberValue = AppUtils.databaseManager.findAllMacroMemberValue(((MacroMember) this.listMacroMember.get(this.macroMemberPosition))._id);
                final int listMacroMemberValuelen = this.listMacroMemberValue.size();
                byte[] data = new byte[((listMacroMemberValuelen * 5) + 8)];
                data[0] = (byte) this.downLoadNum;
                data[1] = (byte) ((MacroMember) this.listMacroMember.get(this.macroMemberPosition)).keyIndex;
                data[2] = (byte) ((MacroMember) this.listMacroMember.get(this.macroMemberPosition)).keyValue;
                data[7] = (byte) listMacroMemberValuelen;
                int crcID = ((MacroMember) this.listMacroMember.get(this.macroMemberPosition)).crcID;
                C0188L.m7i("listMacroMember.get(macroMemberPosition).crcID = " + ((MacroMember) this.listMacroMember.get(this.macroMemberPosition)).crcID);
                this.macroMemberPosition++;
                for (i = 0; i < listMacroMemberValuelen; i++) {
                    MacroMemberValue macroMemberValue = (MacroMemberValue) this.listMacroMemberValue.get(i);
                    data[((i * 5) + 8) + 0] = (byte) macroMemberValue.value;
                    data[((i * 5) + 8) + 1] = (byte) (macroMemberValue.downTime & MotionEventCompat.ACTION_MASK);
                    data[((i * 5) + 8) + 2] = (byte) ((macroMemberValue.downTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                    data[((i * 5) + 8) + 3] = (byte) (macroMemberValue.upTime & MotionEventCompat.ACTION_MASK);
                    data[((i * 5) + 8) + 4] = (byte) ((macroMemberValue.upTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                    C0188L.m7i("macroMemberValue.downTime = " + macroMemberValue.downTime);
                    C0188L.m7i("macroMemberValue.downTime & 0x000000ff = " + (macroMemberValue.downTime & MotionEventCompat.ACTION_MASK));
                    C0188L.m7i("macroMemberValue.downTime & 0x0000ff00 = " + ((macroMemberValue.downTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8));
                    C0188L.m7i("macroMemberValue.upTime = " + macroMemberValue.upTime);
                    C0188L.m7i("macroMemberValue.upTime & 0x000000ff = " + (macroMemberValue.upTime & MotionEventCompat.ACTION_MASK));
                    C0188L.m7i("macroMemberValue.upTime & 0x0000ff00 = " + ((macroMemberValue.upTime & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8));
                    C0188L.m7i("=============");
                }
                data[3] = (byte) ((ViewCompat.MEASURED_STATE_MASK & crcID) >> 24);
                data[4] = (byte) ((16711680 & crcID) >> 16);
                data[5] = (byte) ((crcID & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8);
                data[6] = (byte) ((crcID & MotionEventCompat.ACTION_MASK) >> 0);
                BLEHandle.downLoadMacro(BluetoothLeService.getInstance(), (BluetoothGattCharacteristic) BluetoothLeService.getInstance().mOadService.getCharacteristics().get(1), data, new OnFinishListener() {
                    public void OnFinsh() {
                        Activity_Macro activity_Macro = Activity_Macro.this;
                        activity_Macro.downLoadNum = activity_Macro.downLoadNum + 1;
                        final int i = listMacroMemberValuelen;
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    if (i < 3) {
                                        Thread.sleep(600);
                                    } else if (i < 10) {
                                        Thread.sleep(1200);
                                    } else {
                                        Thread.sleep(1700);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Activity_Macro.this.downLoadMacro();
                            }
                        }).start();
                    }
                });
                return;
            }
            closeMacro(this.downLoadNum);
            this.downLoadNum++;
            new Thread(new C01025()).start();
        }
    }
}
