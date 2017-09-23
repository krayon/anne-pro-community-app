package com.obins.anne;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

public class Activity_CustomLightEffect extends BaseActivity implements OnClickListener {
    private RelativeLayout backBtn;
    private List<Integer> colorList;
    private RelativeLayout relativeLayout_0;
    private RelativeLayout relativeLayout_01;
    private RelativeLayout relativeLayout_02;
    private RelativeLayout relativeLayout_03;
    private RelativeLayout relativeLayout_1;
    private RelativeLayout relativeLayout_10;
    private RelativeLayout relativeLayout_11;
    private RelativeLayout relativeLayout_12;
    private RelativeLayout relativeLayout_13;
    private RelativeLayout relativeLayout_2;
    private RelativeLayout relativeLayout_20;
    private RelativeLayout relativeLayout_21;
    private RelativeLayout relativeLayout_22;
    private RelativeLayout relativeLayout_23;
    private RelativeLayout relativeLayout_3;
    private RelativeLayout relativeLayout_30;
    private RelativeLayout relativeLayout_31;
    private RelativeLayout relativeLayout_32;
    private RelativeLayout relativeLayout_33;
    private RelativeLayout relativeLayout_34;
    private RelativeLayout relativeLayout_4;
    private RelativeLayout relativeLayout_40;
    private RelativeLayout relativeLayout_41;
    private RelativeLayout relativeLayout_42;
    private RelativeLayout relativeLayout_43;
    private RelativeLayout relativeLayout_44;
    private RelativeLayout relativeLayout_45;
    private RelativeLayout relativeLayout_46;
    private RelativeLayout relativeLayout_47;
    private RelativeLayout relativeLayout_5;
    private RelativeLayout relativeLayout_6;
    private RelativeLayout relativeLayout_7;
    private RelativeLayout relativeLayout_8;
    private RelativeLayout relativeLayout_9;
    private RelativeLayout relativeLayout_a;
    private RelativeLayout relativeLayout_b;
    private RelativeLayout relativeLayout_c;
    private RelativeLayout relativeLayout_color0;
    private RelativeLayout relativeLayout_color1;
    private RelativeLayout relativeLayout_color2;
    private RelativeLayout relativeLayout_color3;
    private RelativeLayout relativeLayout_color4;
    private RelativeLayout relativeLayout_color5;
    private RelativeLayout relativeLayout_color6;
    private RelativeLayout relativeLayout_color7;
    private RelativeLayout relativeLayout_color8;
    private RelativeLayout relativeLayout_color9;
    private RelativeLayout relativeLayout_d;
    private RelativeLayout relativeLayout_e;
    private RelativeLayout relativeLayout_esc;
    private RelativeLayout relativeLayout_f;
    private RelativeLayout relativeLayout_g;
    private RelativeLayout relativeLayout_h;
    private RelativeLayout relativeLayout_i;
    private RelativeLayout relativeLayout_j;
    private RelativeLayout relativeLayout_k;
    private RelativeLayout relativeLayout_l;
    private RelativeLayout relativeLayout_m;
    private RelativeLayout relativeLayout_n;
    private RelativeLayout relativeLayout_o;
    private RelativeLayout relativeLayout_p;
    private RelativeLayout relativeLayout_q;
    private RelativeLayout relativeLayout_r;
    private RelativeLayout relativeLayout_s;
    private RelativeLayout relativeLayout_t;
    private RelativeLayout relativeLayout_u;
    private RelativeLayout relativeLayout_v;
    private RelativeLayout relativeLayout_w;
    private RelativeLayout relativeLayout_x;
    private RelativeLayout relativeLayout_y;
    private RelativeLayout relativeLayout_z;
    private int selectItemId = -1;
    private int selectItemPosition = -1;

    class C00801 implements OnClickListener {
        C00801() {
        }

        public void onClick(View arg0) {
            Activity_CustomLightEffect.this.finish();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activity_customlighteffect);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(new C00801());
        this.relativeLayout_esc = (RelativeLayout) findViewById(C0182R.id.relativeLayout_esc);
        this.relativeLayout_1 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_1);
        this.relativeLayout_2 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_2);
        this.relativeLayout_3 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_3);
        this.relativeLayout_4 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_4);
        this.relativeLayout_5 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_5);
        this.relativeLayout_6 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_6);
        this.relativeLayout_7 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_7);
        this.relativeLayout_8 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_8);
        this.relativeLayout_9 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_9);
        this.relativeLayout_0 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_0);
        this.relativeLayout_01 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_01);
        this.relativeLayout_02 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_02);
        this.relativeLayout_03 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_03);
        this.relativeLayout_10 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_10);
        this.relativeLayout_q = (RelativeLayout) findViewById(C0182R.id.relativeLayout_q);
        this.relativeLayout_w = (RelativeLayout) findViewById(C0182R.id.relativeLayout_w);
        this.relativeLayout_e = (RelativeLayout) findViewById(C0182R.id.relativeLayout_e);
        this.relativeLayout_r = (RelativeLayout) findViewById(C0182R.id.relativeLayout_r);
        this.relativeLayout_t = (RelativeLayout) findViewById(C0182R.id.relativeLayout_t);
        this.relativeLayout_y = (RelativeLayout) findViewById(C0182R.id.relativeLayout_y);
        this.relativeLayout_u = (RelativeLayout) findViewById(C0182R.id.relativeLayout_u);
        this.relativeLayout_i = (RelativeLayout) findViewById(C0182R.id.relativeLayout_i);
        this.relativeLayout_o = (RelativeLayout) findViewById(C0182R.id.relativeLayout_o);
        this.relativeLayout_p = (RelativeLayout) findViewById(C0182R.id.relativeLayout_p);
        this.relativeLayout_11 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_11);
        this.relativeLayout_12 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_12);
        this.relativeLayout_13 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_13);
        this.relativeLayout_20 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_20);
        this.relativeLayout_a = (RelativeLayout) findViewById(C0182R.id.relativeLayout_a);
        this.relativeLayout_s = (RelativeLayout) findViewById(C0182R.id.relativeLayout_s);
        this.relativeLayout_d = (RelativeLayout) findViewById(C0182R.id.relativeLayout_d);
        this.relativeLayout_f = (RelativeLayout) findViewById(C0182R.id.relativeLayout_f);
        this.relativeLayout_g = (RelativeLayout) findViewById(C0182R.id.relativeLayout_g);
        this.relativeLayout_h = (RelativeLayout) findViewById(C0182R.id.relativeLayout_h);
        this.relativeLayout_j = (RelativeLayout) findViewById(C0182R.id.relativeLayout_j);
        this.relativeLayout_k = (RelativeLayout) findViewById(C0182R.id.relativeLayout_k);
        this.relativeLayout_l = (RelativeLayout) findViewById(C0182R.id.relativeLayout_l);
        this.relativeLayout_21 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_21);
        this.relativeLayout_22 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_22);
        this.relativeLayout_23 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_23);
        this.relativeLayout_30 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_30);
        this.relativeLayout_z = (RelativeLayout) findViewById(C0182R.id.relativeLayout_z);
        this.relativeLayout_x = (RelativeLayout) findViewById(C0182R.id.relativeLayout_x);
        this.relativeLayout_c = (RelativeLayout) findViewById(C0182R.id.relativeLayout_c);
        this.relativeLayout_v = (RelativeLayout) findViewById(C0182R.id.relativeLayout_v);
        this.relativeLayout_b = (RelativeLayout) findViewById(C0182R.id.relativeLayout_b);
        this.relativeLayout_n = (RelativeLayout) findViewById(C0182R.id.relativeLayout_n);
        this.relativeLayout_m = (RelativeLayout) findViewById(C0182R.id.relativeLayout_m);
        this.relativeLayout_31 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_31);
        this.relativeLayout_32 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_32);
        this.relativeLayout_33 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_33);
        this.relativeLayout_34 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_34);
        this.relativeLayout_40 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_40);
        this.relativeLayout_41 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_41);
        this.relativeLayout_42 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_42);
        this.relativeLayout_43 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_43);
        this.relativeLayout_44 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_44);
        this.relativeLayout_45 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_45);
        this.relativeLayout_46 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_46);
        this.relativeLayout_47 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_47);
        this.relativeLayout_color0 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color0);
        this.relativeLayout_color1 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color1);
        this.relativeLayout_color2 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color2);
        this.relativeLayout_color3 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color3);
        this.relativeLayout_color4 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color4);
        this.relativeLayout_color5 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color5);
        this.relativeLayout_color6 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color6);
        this.relativeLayout_color7 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color7);
        this.relativeLayout_color8 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color8);
        this.relativeLayout_color9 = (RelativeLayout) findViewById(C0182R.id.relativeLayout_color9);
        this.relativeLayout_esc.setOnClickListener(this);
        this.relativeLayout_1.setOnClickListener(this);
        this.relativeLayout_2.setOnClickListener(this);
        this.relativeLayout_3.setOnClickListener(this);
        this.relativeLayout_4.setOnClickListener(this);
        this.relativeLayout_5.setOnClickListener(this);
        this.relativeLayout_6.setOnClickListener(this);
        this.relativeLayout_7.setOnClickListener(this);
        this.relativeLayout_8.setOnClickListener(this);
        this.relativeLayout_9.setOnClickListener(this);
        this.relativeLayout_0.setOnClickListener(this);
        this.relativeLayout_01.setOnClickListener(this);
        this.relativeLayout_02.setOnClickListener(this);
        this.relativeLayout_03.setOnClickListener(this);
        this.relativeLayout_10.setOnClickListener(this);
        this.relativeLayout_q.setOnClickListener(this);
        this.relativeLayout_w.setOnClickListener(this);
        this.relativeLayout_e.setOnClickListener(this);
        this.relativeLayout_r.setOnClickListener(this);
        this.relativeLayout_t.setOnClickListener(this);
        this.relativeLayout_y.setOnClickListener(this);
        this.relativeLayout_u.setOnClickListener(this);
        this.relativeLayout_i.setOnClickListener(this);
        this.relativeLayout_o.setOnClickListener(this);
        this.relativeLayout_p.setOnClickListener(this);
        this.relativeLayout_11.setOnClickListener(this);
        this.relativeLayout_12.setOnClickListener(this);
        this.relativeLayout_13.setOnClickListener(this);
        this.relativeLayout_20.setOnClickListener(this);
        this.relativeLayout_a.setOnClickListener(this);
        this.relativeLayout_s.setOnClickListener(this);
        this.relativeLayout_d.setOnClickListener(this);
        this.relativeLayout_f.setOnClickListener(this);
        this.relativeLayout_g.setOnClickListener(this);
        this.relativeLayout_h.setOnClickListener(this);
        this.relativeLayout_j.setOnClickListener(this);
        this.relativeLayout_k.setOnClickListener(this);
        this.relativeLayout_l.setOnClickListener(this);
        this.relativeLayout_21.setOnClickListener(this);
        this.relativeLayout_22.setOnClickListener(this);
        this.relativeLayout_23.setOnClickListener(this);
        this.relativeLayout_30.setOnClickListener(this);
        this.relativeLayout_z.setOnClickListener(this);
        this.relativeLayout_x.setOnClickListener(this);
        this.relativeLayout_c.setOnClickListener(this);
        this.relativeLayout_v.setOnClickListener(this);
        this.relativeLayout_b.setOnClickListener(this);
        this.relativeLayout_n.setOnClickListener(this);
        this.relativeLayout_m.setOnClickListener(this);
        this.relativeLayout_31.setOnClickListener(this);
        this.relativeLayout_32.setOnClickListener(this);
        this.relativeLayout_33.setOnClickListener(this);
        this.relativeLayout_34.setOnClickListener(this);
        this.relativeLayout_40.setOnClickListener(this);
        this.relativeLayout_41.setOnClickListener(this);
        this.relativeLayout_42.setOnClickListener(this);
        this.relativeLayout_43.setOnClickListener(this);
        this.relativeLayout_44.setOnClickListener(this);
        this.relativeLayout_45.setOnClickListener(this);
        this.relativeLayout_46.setOnClickListener(this);
        this.relativeLayout_47.setOnClickListener(this);
        this.relativeLayout_color0.setOnClickListener(this);
        this.relativeLayout_color1.setOnClickListener(this);
        this.relativeLayout_color2.setOnClickListener(this);
        this.relativeLayout_color3.setOnClickListener(this);
        this.relativeLayout_color4.setOnClickListener(this);
        this.relativeLayout_color5.setOnClickListener(this);
        this.relativeLayout_color6.setOnClickListener(this);
        this.relativeLayout_color7.setOnClickListener(this);
        this.relativeLayout_color8.setOnClickListener(this);
        this.relativeLayout_color9.setOnClickListener(this);
        this.colorList = new ArrayList();
        this.colorList.clear();
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.blue)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.red)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.green)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.purple)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
        this.colorList.add(new Integer(getResources().getColor(C0182R.color.orange)));
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.relativeLayout_esc:
                selectItem(C0182R.id.relativeLayout_esc, 0);
                return;
            case C0182R.id.relativeLayout_1:
                selectItem(C0182R.id.relativeLayout_1, 1);
                return;
            case C0182R.id.relativeLayout_2:
                selectItem(C0182R.id.relativeLayout_2, 2);
                return;
            case C0182R.id.relativeLayout_3:
                selectItem(C0182R.id.relativeLayout_3, 3);
                return;
            case C0182R.id.relativeLayout_4:
                selectItem(C0182R.id.relativeLayout_4, 4);
                return;
            case C0182R.id.relativeLayout_5:
                selectItem(C0182R.id.relativeLayout_5, 5);
                return;
            case C0182R.id.relativeLayout_6:
                selectItem(C0182R.id.relativeLayout_6, 6);
                return;
            case C0182R.id.relativeLayout_7:
                selectItem(C0182R.id.relativeLayout_7, 7);
                return;
            case C0182R.id.relativeLayout_8:
                selectItem(C0182R.id.relativeLayout_8, 8);
                return;
            case C0182R.id.relativeLayout_9:
                selectItem(C0182R.id.relativeLayout_9, 9);
                return;
            case C0182R.id.relativeLayout_0:
                selectItem(C0182R.id.relativeLayout_0, 10);
                return;
            case C0182R.id.relativeLayout_01:
                selectItem(C0182R.id.relativeLayout_01, 11);
                return;
            case C0182R.id.relativeLayout_02:
                selectItem(C0182R.id.relativeLayout_02, 12);
                return;
            case C0182R.id.relativeLayout_03:
                selectItem(C0182R.id.relativeLayout_03, 13);
                return;
            case C0182R.id.relativeLayout_10:
                selectItem(C0182R.id.relativeLayout_10, 14);
                return;
            case C0182R.id.relativeLayout_q:
                selectItem(C0182R.id.relativeLayout_q, 15);
                return;
            case C0182R.id.relativeLayout_w:
                selectItem(C0182R.id.relativeLayout_w, 16);
                return;
            case C0182R.id.relativeLayout_e:
                selectItem(C0182R.id.relativeLayout_e, 17);
                return;
            case C0182R.id.relativeLayout_r:
                selectItem(C0182R.id.relativeLayout_r, 18);
                return;
            case C0182R.id.relativeLayout_t:
                selectItem(C0182R.id.relativeLayout_t, 19);
                return;
            case C0182R.id.relativeLayout_y:
                selectItem(C0182R.id.relativeLayout_y, 20);
                return;
            case C0182R.id.relativeLayout_u:
                selectItem(C0182R.id.relativeLayout_u, 21);
                return;
            case C0182R.id.relativeLayout_i:
                selectItem(C0182R.id.relativeLayout_i, 22);
                return;
            case C0182R.id.relativeLayout_o:
                selectItem(C0182R.id.relativeLayout_o, 23);
                return;
            case C0182R.id.relativeLayout_p:
                selectItem(C0182R.id.relativeLayout_p, 24);
                return;
            case C0182R.id.relativeLayout_11:
                selectItem(C0182R.id.relativeLayout_11, 25);
                return;
            case C0182R.id.relativeLayout_12:
                selectItem(C0182R.id.relativeLayout_12, 26);
                return;
            case C0182R.id.relativeLayout_13:
                selectItem(C0182R.id.relativeLayout_13, 27);
                return;
            case C0182R.id.relativeLayout_20:
                selectItem(C0182R.id.relativeLayout_20, 28);
                return;
            case C0182R.id.relativeLayout_a:
                selectItem(C0182R.id.relativeLayout_a, 29);
                return;
            case C0182R.id.relativeLayout_s:
                selectItem(C0182R.id.relativeLayout_s, 30);
                return;
            case C0182R.id.relativeLayout_d:
                selectItem(C0182R.id.relativeLayout_d, 31);
                return;
            case C0182R.id.relativeLayout_f:
                selectItem(C0182R.id.relativeLayout_f, 32);
                return;
            case C0182R.id.relativeLayout_g:
                selectItem(C0182R.id.relativeLayout_g, 33);
                return;
            case C0182R.id.relativeLayout_h:
                selectItem(C0182R.id.relativeLayout_h, 34);
                return;
            case C0182R.id.relativeLayout_j:
                selectItem(C0182R.id.relativeLayout_j, 35);
                return;
            case C0182R.id.relativeLayout_k:
                selectItem(C0182R.id.relativeLayout_k, 36);
                return;
            case C0182R.id.relativeLayout_l:
                selectItem(C0182R.id.relativeLayout_l, 37);
                return;
            case C0182R.id.relativeLayout_21:
                selectItem(C0182R.id.relativeLayout_21, 38);
                return;
            case C0182R.id.relativeLayout_22:
                selectItem(C0182R.id.relativeLayout_22, 39);
                return;
            case C0182R.id.relativeLayout_23:
                selectItem(C0182R.id.relativeLayout_23, 40);
                return;
            case C0182R.id.relativeLayout_30:
                selectItem(C0182R.id.relativeLayout_30, 41);
                return;
            case C0182R.id.relativeLayout_z:
                selectItem(C0182R.id.relativeLayout_z, 42);
                return;
            case C0182R.id.relativeLayout_x:
                selectItem(C0182R.id.relativeLayout_x, 43);
                return;
            case C0182R.id.relativeLayout_c:
                selectItem(C0182R.id.relativeLayout_c, 44);
                return;
            case C0182R.id.relativeLayout_v:
                selectItem(C0182R.id.relativeLayout_v, 45);
                return;
            case C0182R.id.relativeLayout_b:
                selectItem(C0182R.id.relativeLayout_b, 46);
                return;
            case C0182R.id.relativeLayout_n:
                selectItem(C0182R.id.relativeLayout_n, 47);
                return;
            case C0182R.id.relativeLayout_m:
                selectItem(C0182R.id.relativeLayout_m, 48);
                return;
            case C0182R.id.relativeLayout_31:
                selectItem(C0182R.id.relativeLayout_31, 49);
                return;
            case C0182R.id.relativeLayout_32:
                selectItem(C0182R.id.relativeLayout_32, 50);
                return;
            case C0182R.id.relativeLayout_33:
                selectItem(C0182R.id.relativeLayout_33, 51);
                return;
            case C0182R.id.relativeLayout_34:
                selectItem(C0182R.id.relativeLayout_34, 52);
                return;
            case C0182R.id.relativeLayout_40:
                selectItem(C0182R.id.relativeLayout_40, 53);
                return;
            case C0182R.id.relativeLayout_41:
                selectItem(C0182R.id.relativeLayout_41, 54);
                return;
            case C0182R.id.relativeLayout_42:
                selectItem(C0182R.id.relativeLayout_42, 55);
                return;
            case C0182R.id.relativeLayout_43:
                selectItem(C0182R.id.relativeLayout_43, 56);
                return;
            case C0182R.id.relativeLayout_44:
                selectItem(C0182R.id.relativeLayout_44, 57);
                return;
            case C0182R.id.relativeLayout_45:
                selectItem(C0182R.id.relativeLayout_45, 58);
                return;
            case C0182R.id.relativeLayout_46:
                selectItem(C0182R.id.relativeLayout_46, 59);
                return;
            case C0182R.id.relativeLayout_47:
                selectItem(C0182R.id.relativeLayout_47, 60);
                return;
            case C0182R.id.relativeLayout_color0:
                setselectColor(getResources().getColor(C0182R.color.red));
                return;
            case C0182R.id.relativeLayout_color1:
                setselectColor(getResources().getColor(C0182R.color.dark_red));
                return;
            case C0182R.id.relativeLayout_color2:
                setselectColor(getResources().getColor(C0182R.color.orange));
                return;
            case C0182R.id.relativeLayout_color3:
                setselectColor(getResources().getColor(C0182R.color.dark_orange));
                return;
            case C0182R.id.relativeLayout_color4:
                setselectColor(getResources().getColor(C0182R.color.purple));
                return;
            case C0182R.id.relativeLayout_color5:
                setselectColor(getResources().getColor(C0182R.color.dark_purple));
                return;
            case C0182R.id.relativeLayout_color6:
                setselectColor(getResources().getColor(C0182R.color.green));
                return;
            case C0182R.id.relativeLayout_color7:
                setselectColor(getResources().getColor(C0182R.color.dark_green));
                return;
            case C0182R.id.relativeLayout_color8:
                setselectColor(getResources().getColor(C0182R.color.blue));
                return;
            case C0182R.id.relativeLayout_color9:
                setselectColor(getResources().getColor(C0182R.color.dark_blue));
                return;
            default:
                return;
        }
    }

    private void setselectColor(int color) {
        if (this.selectItemId != -1) {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(this.selectItemId);
            int strokeColor = Color.parseColor("#2E3135");
            int fillColor = color;
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(fillColor);
            gd.setCornerRadius((float) 0);
            gd.setStroke(5, strokeColor);
            relativeLayout.setBackground(gd);
            this.colorList.set(this.selectItemPosition, Integer.valueOf(color));
        }
    }

    private void selectItem(int id, int position) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(id);
        if (this.selectItemId == -1) {
            getFocus(id, position);
            this.selectItemId = id;
            this.selectItemPosition = position;
        } else if (this.selectItemId == id) {
            this.selectItemId = -1;
            lostFocus(id, position);
        } else {
            lostFocus(this.selectItemId, this.selectItemPosition);
            getFocus(id, position);
            this.selectItemId = id;
            this.selectItemPosition = position;
        }
    }

    private void getFocus(int id, int position) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(id);
        int strokeColor = Color.parseColor("#2E3135");
        int fillColor = ((Integer) this.colorList.get(position)).intValue();
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius((float) 0);
        gd.setStroke(5, strokeColor);
        relativeLayout.setBackground(gd);
    }

    private void lostFocus(int id, int position) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(id);
        int strokeColor = Color.parseColor("#2E3135");
        int fillColor = ((Integer) this.colorList.get(position)).intValue();
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius((float) 0);
        gd.setStroke(0, strokeColor);
        relativeLayout.setBackground(gd);
    }
}
