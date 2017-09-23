package com.obins.anne;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class Activity_Aboutobins extends BaseActivity {
    private RelativeLayout backBtn;

    class C00631 implements OnClickListener {
        C00631() {
        }

        public void onClick(View arg0) {
            Activity_Aboutobins.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_aboutobins);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00631());
    }
}
