package com.obins.anne;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.update.AppUpdate;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.C0188L;
import com.obins.anne.utils.NormalFun;
import org.json.JSONException;
import org.json.JSONObject;
import www.robinwatch.squid.network.HttpCallback;

public class Activity_APPVersion extends BaseActivity implements OnClickListener {
    private static final int GET_APPVERSION_FAIL = 1;
    private static final int GET_APPVERSION_SUCCESS = 0;
    private RelativeLayout backBtn;
    HttpCallback getappversionback = new C02301();
    private Handler handler = new C00592();
    private TextView newver_TextView;
    private ProgressDialog progressDialog;
    private Button start_btn;
    private int stateFlag = -1;
    private TextView tip_TextView;
    private String url = AppConfig.SERVER_IP;
    private TextView version_TextView;

    class C00592 extends Handler {
        C00592() {
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Activity_APPVersion.this.progressDialog.cancel();
                    Activity_APPVersion.this.judgeupdate(msg.obj);
                    break;
                case 1:
                    Activity_APPVersion.this.progressDialog.cancel();
                    Activity_APPVersion.this.stateFlag = -1;
                    Toast.makeText(Activity_APPVersion.this, Activity_APPVersion.this.getResources().getString(C0182R.string.get_app_version_fail), 0).show();
                    break;
            }
            super.handleMessage(msg);
        }
    }

    class C00603 implements OnClickListener {
        C00603() {
        }

        public void onClick(View arg0) {
            Activity_APPVersion.this.finish();
        }
    }

    class C02301 implements HttpCallback {
        C02301() {
        }

        public void excute(String result) {
            if (result != null) {
                try {
                    if (result.startsWith("﻿")) {
                        result = result.substring(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            JSONObject json = new JSONObject(result);
            if (json.getString("code").toString().equals("10000")) {
                JSONObject jsonresult = json.getJSONObject("result");
                Message msg = new Message();
                msg.what = 0;
                msg.obj = jsonresult;
                Activity_APPVersion.this.handler.sendMessage(msg);
                return;
            }
            msg = new Message();
            msg.what = 1;
            Activity_APPVersion.this.handler.sendMessage(msg);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_appversion);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00603());
        this.stateFlag = -1;
        initUI();
    }

    private void initUI() {
        this.newver_TextView = (TextView) findViewById(C0182R.id.newver_TextView);
        this.newver_TextView.setText(AppConfig.SERVER_IP);
        this.tip_TextView = (TextView) findViewById(C0182R.id.tip_TextView);
        this.tip_TextView.setText(AppConfig.SERVER_IP);
        this.version_TextView = (TextView) findViewById(C0182R.id.version_TextView);
        this.start_btn = (Button) findViewById(C0182R.id.start_btn);
        this.start_btn.setOnClickListener(this);
        this.version_TextView.setText("V" + NormalFun.getVerCode(this));
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.start_btn:
                if (this.stateFlag == -1) {
                    this.stateFlag = 2;
                    this.progressDialog = ProgressDialog.show(this, AppConfig.SERVER_IP, getResources().getString(C0182R.string.check_app_version));
                    AppUtils.squid.getAppVersion(this.getappversionback, AppConfig.APP_NAME);
                    return;
                } else if (this.stateFlag == 0) {
                    this.stateFlag = 3;
                    AppUpdate appUpdate = new AppUpdate(this);
                    String update_savepath = AppConfig.APKFILE_PATH;
                    String update_savename = AppConfig.UPDATE_SAVENAME;
                    appUpdate.setNEWParam(this.url, NormalFun.getPath(this, update_savepath), update_savename);
                    appUpdate.startDownload();
                    return;
                } else if (this.stateFlag == 1) {
                    finish();
                    return;
                } else if (this.stateFlag == 3) {
                    finish();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void judgeupdate(JSONObject json) {
        if (json != null) {
            float newVerCode = 0.0f;
            String desc = AppConfig.SERVER_IP;
            try {
                newVerCode = Float.parseFloat(json.getString("ver"));
                this.url = json.getString("androidpath");
                desc = json.getString("desc");
            } catch (Exception e) {
                e.printStackTrace();
            }
            float vercode = NormalFun.getVerCode(this);
            C0188L.m7i("tison fuck you newVerCode = " + newVerCode + ", vercode = " + vercode);
            if (newVerCode > vercode) {
                this.stateFlag = 0;
                this.newver_TextView.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.find_new_app))).append(newVerCode).toString());
                this.tip_TextView.setText(desc);
                this.start_btn.setText(getResources().getString(C0182R.string.update));
                Toast.makeText(this, getResources().getString(C0182R.string.find_new_app_1), 0).show();
                return;
            }
            this.stateFlag = 1;
            this.start_btn.setText(getResources().getString(C0182R.string.macro_finish));
            Toast.makeText(this, getResources().getString(C0182R.string.is_new_app), 0).show();
        }
    }
}
