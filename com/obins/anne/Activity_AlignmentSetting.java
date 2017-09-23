package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.DensityUtil;
import com.obins.anne.utils.KeyObject;
import com.obins.anne.utils.KeyObjectUtil;
import com.obins.anne.utils.KeyboardAlignment;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import com.obins.anne.viewpart.KeyboardAlignmentView;
import java.util.ArrayList;
import java.util.List;

public class Activity_AlignmentSetting extends BaseActivity implements OnClickListener {
    private RelativeLayout backBtn;
    private EditText content_EditText;
    private RelativeLayout content_RelativeLayout;
    private RelativeLayout delete_RelativeLayout;
    private Handler handler = new C00701();
    private KeyboardAlignment keyboardAlignment;
    private KeyboardAlignmentView keyboardView1;
    private KeyboardAlignmentView keyboardView2;
    private EditText name_EditText;
    private RelativeLayout name_RelativeLayout;
    private ProgressDialog progressDialog;
    private RelativeLayout save_RelativeLayout;
    private int selectedItem1 = -1;
    private int selectedItem2 = -1;

    class C00701 extends Handler {
        C00701() {
        }

        public void handleMessage(Message msg) {
            Activity_AlignmentSetting.this.progressDialog.cancel();
            Activity_AlignmentSetting.this.finish();
            super.handleMessage(msg);
        }
    }

    class C00712 implements OnClickListener {
        C00712() {
        }

        public void onClick(View arg0) {
            Activity_AlignmentSetting.this.finish();
        }
    }

    class C00723 implements Runnable {
        C00723() {
        }

        public void run() {
            if (Activity_AlignmentSetting.this.keyboardAlignment._id < 0) {
                AppUtils.databaseManager.addAlignment(Activity_AlignmentSetting.this.keyboardAlignment);
            } else {
                AppUtils.databaseManager.modifyAlignment(Activity_AlignmentSetting.this.keyboardAlignment);
            }
            Activity_AlignmentSetting.this.handler.sendMessage(new Message());
        }
    }

    class C02316 implements KeyboardAlignmentView.OnClickListener {
        C02316() {
        }

        public void OnClick(int position) {
            Activity_AlignmentSetting.this.keyboardView2.cancleSelectItem();
            Activity_AlignmentSetting.this.selectedItem1 = position;
            Activity_AlignmentSetting.this.selectedItem2 = -1;
            AppUtils.jumpToSelectKeyPage_Type = 1;
            Activity_AlignmentSetting.this.startActivity(new Intent(Activity_AlignmentSetting.this, Activity_SelectedKey.class));
        }
    }

    class C02327 implements KeyboardAlignmentView.OnClickListener {
        C02327() {
        }

        public void OnClick(int position) {
            if (((KeyObject) Activity_AlignmentSetting.this.keyboardAlignment.funKey_List.get(position)).keyValue != KeyObjectUtil.keyObjectANNE.keyValue && ((KeyObject) Activity_AlignmentSetting.this.keyboardAlignment.funKey_List.get(position)).keyValue != KeyObjectUtil.keyObjectFN.keyValue && ((KeyObject) Activity_AlignmentSetting.this.keyboardAlignment.funKey_List.get(position)).keyValue != KeyObjectUtil.keyObjectWinLock.keyValue) {
                Activity_AlignmentSetting.this.keyboardView1.cancleSelectItem();
                Activity_AlignmentSetting.this.selectedItem2 = position;
                AppUtils.jumpToSelectKeyPage_Type = 2;
                Activity_AlignmentSetting.this.selectedItem1 = -1;
                Activity_AlignmentSetting.this.startActivity(new Intent(Activity_AlignmentSetting.this, Activity_SelectedKey.class));
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_alignmentsetting);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00712());
        initdata();
        initUI();
    }

    private void initdata() {
        this.selectedItem1 = -1;
        this.selectedItem2 = -1;
        this.keyboardAlignment = AppUtils.keyboardAlignment;
        AppUtils.keyObject = null;
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.delete_RelativeLayout:
                if (this.keyboardAlignment._id >= 0) {
                    AppUtils.databaseManager.deleteAlignment(this.keyboardAlignment._id);
                }
                finish();
                return;
            case C0182R.id.save_RelativeLayout:
                if (judgeAlignment()) {
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, getResources().getString(C0182R.string.saving));
                    this.keyboardAlignment.name = this.name_EditText.getText().toString();
                    if (this.keyboardAlignment.name == null || this.keyboardAlignment.name.isEmpty()) {
                        this.keyboardAlignment.name = getResources().getString(C0182R.string.layout_custom);
                    }
                    this.keyboardAlignment.content = this.content_EditText.getText().toString();
                    int alignmentId = KeyboardAlignment.getAlignmentID(this.keyboardAlignment);
                    if (alignmentId < 10) {
                        alignmentId += 10;
                    }
                    this.keyboardAlignment.alignmentId = alignmentId;
                    new Thread(new C00723()).start();
                    return;
                }
                Toast.makeText(this, getResources().getString(C0182R.string.layout_must_have_anne_fn_key), 0).show();
                return;
            default:
                return;
        }
    }

    private boolean judgeAlignment() {
        int len = this.keyboardAlignment.standardKey_List.size();
        boolean isExit_FN = false;
        boolean isExit_ANNE = false;
        for (int i = 0; i < len; i++) {
            if (((KeyObject) this.keyboardAlignment.standardKey_List.get(i)).keyValue == KeyObjectUtil.keyObjectFN.keyValue) {
                isExit_FN = true;
            } else if (((KeyObject) this.keyboardAlignment.standardKey_List.get(i)).keyValue == KeyObjectUtil.keyObjectANNE.keyValue) {
                isExit_ANNE = true;
            }
        }
        if (isExit_FN && isExit_ANNE) {
            return true;
        }
        return false;
    }

    private void showInputDialog(final TextView textview) {
        final AlertDialog dialog = new Builder(this).create();
        dialog.setView((RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(C0182R.layout.input_lighteffect_prompt, null));
        dialog.show();
        dialog.getWindow().setContentView(C0182R.layout.input_lighteffect_prompt);
        ((TextView) dialog.findViewById(C0182R.id.title_TextView)).setText(getResources().getString(C0182R.string.tip));
        final EditText content_EditText = (EditText) dialog.findViewById(C0182R.id.content_EditText);
        content_EditText.setText(textview.getText().toString());
        content_EditText.setSelection(textview.getText().toString().length());
        content_EditText.setFocusable(true);
        Button cancle_Button = (Button) dialog.findViewById(C0182R.id.cancle_Button);
        ((Button) dialog.findViewById(C0182R.id.sure_Button)).setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                textview.setText(content_EditText.getText().toString());
                dialog.cancel();
            }
        });
        cancle_Button.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                dialog.cancel();
            }
        });
    }

    protected void onResume() {
        KeyObject keyObject_clone;
        super.onResume();
        if (!(AppUtils.keyObject == null || this.selectedItem1 == -1)) {
            KeyObject keyObject_fun_original_clone;
            int i;
            KeyObject keyObject_fun_clone;
            if (((KeyObject) this.keyboardAlignment.standardKey_List.get(this.selectedItem1)).keyValue == KeyObjectUtil.keyObjectANNE.keyValue || ((KeyObject) this.keyboardAlignment.standardKey_List.get(this.selectedItem1)).keyValue == KeyObjectUtil.keyObjectFN.keyValue || ((KeyObject) this.keyboardAlignment.standardKey_List.get(this.selectedItem1)).keyValue == KeyObjectUtil.keyObjectLWin.keyValue) {
                this.keyboardView2.setKeyText(AppConfig.SERVER_IP, this.selectedItem1);
                keyObject_fun_original_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.funKey_List.get(this.selectedItem1)).clone();
                keyObject_fun_original_clone.keyStr = AppConfig.SERVER_IP;
                keyObject_fun_original_clone.keyValue = 0;
                this.keyboardAlignment.funKey_List.set(this.selectedItem1, keyObject_fun_original_clone);
            }
            if (AppUtils.keyObject.keyValue == KeyObjectUtil.keyObjectANNE.keyValue || AppUtils.keyObject.keyValue == KeyObjectUtil.keyObjectFN.keyValue) {
                for (i = 0; i < 61; i++) {
                    if (((KeyObject) this.keyboardAlignment.standardKey_List.get(i)).keyValue == AppUtils.keyObject.keyValue) {
                        this.keyboardView1.setKeyText(AppConfig.SERVER_IP, i);
                        KeyObject keyObject_statnd_original_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.standardKey_List.get(i)).clone();
                        keyObject_statnd_original_clone.keyStr = AppConfig.SERVER_IP;
                        keyObject_statnd_original_clone.keyValue = 0;
                        this.keyboardAlignment.standardKey_List.set(i, keyObject_statnd_original_clone);
                    }
                }
            }
            this.keyboardView1.setKeyText(AppUtils.keyObject.keyStr, this.selectedItem1);
            keyObject_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.standardKey_List.get(this.selectedItem1)).clone();
            keyObject_clone.keyStr = AppUtils.keyObject.keyStr;
            keyObject_clone.keyValue = AppUtils.keyObject.keyValue;
            this.keyboardAlignment.standardKey_List.set(this.selectedItem1, keyObject_clone);
            if (keyObject_clone.keyValue == KeyObjectUtil.keyObjectANNE.keyValue || keyObject_clone.keyValue == KeyObjectUtil.keyObjectFN.keyValue) {
                for (i = 0; i < 61; i++) {
                    if (((KeyObject) this.keyboardAlignment.funKey_List.get(i)).keyValue == keyObject_clone.keyValue) {
                        this.keyboardView2.setKeyText(AppConfig.SERVER_IP, i);
                        keyObject_fun_original_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.funKey_List.get(i)).clone();
                        keyObject_fun_original_clone.keyStr = AppConfig.SERVER_IP;
                        keyObject_fun_original_clone.keyValue = 0;
                        this.keyboardAlignment.funKey_List.set(i, keyObject_fun_original_clone);
                    }
                }
                this.keyboardView2.setKeyText(AppUtils.keyObject.keyStr, this.selectedItem1);
                keyObject_fun_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.funKey_List.get(this.selectedItem1)).clone();
                keyObject_fun_clone.keyStr = AppUtils.keyObject.keyStr;
                keyObject_fun_clone.keyValue = AppUtils.keyObject.keyValue;
                this.keyboardAlignment.funKey_List.set(this.selectedItem1, keyObject_fun_clone);
            }
            if (keyObject_clone.keyValue == KeyObjectUtil.keyObjectLWin.keyValue) {
                this.keyboardView2.setKeyText(KeyObjectUtil.keyObjectWinLock.keyStr, this.selectedItem1);
                keyObject_fun_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.funKey_List.get(this.selectedItem1)).clone();
                keyObject_fun_clone.keyStr = KeyObjectUtil.keyObjectWinLock.keyStr;
                keyObject_fun_clone.keyValue = KeyObjectUtil.keyObjectWinLock.keyValue;
                this.keyboardAlignment.funKey_List.set(this.selectedItem1, keyObject_fun_clone);
            }
        }
        if (!(AppUtils.keyObject == null || this.selectedItem2 == -1)) {
            this.keyboardView2.setKeyText(AppUtils.keyObject.keyStr, this.selectedItem2);
            keyObject_clone = (KeyObject) ((KeyObject) this.keyboardAlignment.funKey_List.get(this.selectedItem2)).clone();
            keyObject_clone.keyStr = AppUtils.keyObject.keyStr;
            keyObject_clone.keyValue = AppUtils.keyObject.keyValue;
            this.keyboardAlignment.funKey_List.set(this.selectedItem2, keyObject_clone);
        }
        AppUtils.keyObject = null;
    }

    private void initUI() {
        this.content_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.content_RelativeLayout);
        this.name_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.name_RelativeLayout);
        this.delete_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.delete_RelativeLayout);
        this.delete_RelativeLayout.setOnClickListener(this);
        this.name_EditText = (EditText) findViewById(C0182R.id.name_TextView);
        this.name_EditText.setText(this.keyboardAlignment.name);
        this.content_EditText = (EditText) findViewById(C0182R.id.content_TextView);
        this.content_EditText.setText(this.keyboardAlignment.content);
        this.save_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.save_RelativeLayout);
        this.save_RelativeLayout.setOnClickListener(this);
        if (this.keyboardAlignment.type == KeyboardAlignment.NORMAL_TYPE) {
            this.delete_RelativeLayout.setVisibility(4);
        }
        View view1 = findViewById(C0182R.id.keyboard_LinearLayout1);
        this.keyboardView1 = new KeyboardAlignmentView(view1);
        this.keyboardView1.setColor(KeyboardLighteffectUtil.grayLightEffectListt);
        this.keyboardView1.setClickable();
        List<String> strList = new ArrayList();
        int standardKey_Listlen = this.keyboardAlignment.standardKey_List.size();
        for (int i = 0; i < standardKey_Listlen; i++) {
            strList.add(((KeyObject) this.keyboardAlignment.standardKey_List.get(i)).keyStr);
        }
        this.keyboardView1.setAllKeyText(strList);
        this.keyboardView1.setOnClickListener(new C02316());
        LayoutParams keyboardView_linearParams = (LayoutParams) view1.getLayoutParams();
        keyboardView_linearParams.height = (int) (((((float) DensityUtil.getScreenWidthPixels(this)) - (2.0f * DensityUtil.dip2px(this, 10.0f))) / 15.0f) * 5.0f);
        view1.setLayoutParams(keyboardView_linearParams);
        this.keyboardView2 = new KeyboardAlignmentView(findViewById(C0182R.id.keyboard_LinearLayout2));
        this.keyboardView2.setColor(KeyboardLighteffectUtil.purpleColorList);
        this.keyboardView2.setClickable();
        List<String> strList2 = new ArrayList();
        int funKey_Listlen = this.keyboardAlignment.funKey_List.size();
        for (int j = 0; j < funKey_Listlen; j++) {
            strList2.add(((KeyObject) this.keyboardAlignment.funKey_List.get(j)).keyStr);
        }
        this.keyboardView2.setAllKeyText(strList2);
        this.keyboardView2.setOnClickListener(new C02327());
    }
}
