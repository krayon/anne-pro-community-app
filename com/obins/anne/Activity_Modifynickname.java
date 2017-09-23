package com.obins.anne;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Activity_Modifynickname extends BaseActivity {
    private TextView activity_title_tv;
    private RelativeLayout backBtn;
    private EditText devicename_et;
    private Button ok_btn;

    class C01311 implements OnClickListener {
        C01311() {
        }

        public void onClick(View v) {
            Activity_Modifynickname.this.finish();
        }
    }

    class C01322 implements OnClickListener {
        C01322() {
        }

        public void onClick(View v) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_modifynickname);
        initui();
    }

    private void initui() {
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C01311());
        this.activity_title_tv = (TextView) findViewById(C0182R.id.activity_title_tv);
        String nickname = "白雪公主";
        this.activity_title_tv.setText(nickname);
        this.ok_btn = (Button) findViewById(C0182R.id.ok_btn);
        this.devicename_et = (EditText) findViewById(C0182R.id.devicename_et);
        this.devicename_et.setText(nickname);
        this.devicename_et.setSelection(nickname.length());
        this.devicename_et.setSelectAllOnFocus(true);
        this.ok_btn.setOnClickListener(new C01322());
    }
}
