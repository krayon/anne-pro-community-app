package com.obins.anne.viewpart;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.obins.anne.C0182R;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import java.util.ArrayList;
import java.util.List;

public class KeyboardView implements OnClickListener {
    private List<Integer> colordataList;
    private boolean isClickable = false;
    private View keyboardView;
    private List<RelativeLayout> relativeLayoutView_List;
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
    private List<Integer> selecteddataList;

    public KeyboardView(View view) {
        this.keyboardView = view;
        initView();
    }

    public List<Integer> getColordataList() {
        return this.colordataList;
    }

    private void initView() {
        this.relativeLayout_esc = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_esc);
        this.relativeLayout_1 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_1);
        this.relativeLayout_2 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_2);
        this.relativeLayout_3 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_3);
        this.relativeLayout_4 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_4);
        this.relativeLayout_5 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_5);
        this.relativeLayout_6 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_6);
        this.relativeLayout_7 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_7);
        this.relativeLayout_8 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_8);
        this.relativeLayout_9 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_9);
        this.relativeLayout_0 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_0);
        this.relativeLayout_01 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_01);
        this.relativeLayout_02 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_02);
        this.relativeLayout_03 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_03);
        this.relativeLayout_10 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_10);
        this.relativeLayout_q = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_q);
        this.relativeLayout_w = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_w);
        this.relativeLayout_e = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_e);
        this.relativeLayout_r = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_r);
        this.relativeLayout_t = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_t);
        this.relativeLayout_y = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_y);
        this.relativeLayout_u = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_u);
        this.relativeLayout_i = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_i);
        this.relativeLayout_o = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_o);
        this.relativeLayout_p = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_p);
        this.relativeLayout_11 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_11);
        this.relativeLayout_12 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_12);
        this.relativeLayout_13 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_13);
        this.relativeLayout_20 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_20);
        this.relativeLayout_a = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_a);
        this.relativeLayout_s = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_s);
        this.relativeLayout_d = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_d);
        this.relativeLayout_f = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_f);
        this.relativeLayout_g = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_g);
        this.relativeLayout_h = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_h);
        this.relativeLayout_j = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_j);
        this.relativeLayout_k = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_k);
        this.relativeLayout_l = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_l);
        this.relativeLayout_21 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_21);
        this.relativeLayout_22 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_22);
        this.relativeLayout_23 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_23);
        this.relativeLayout_30 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_30);
        this.relativeLayout_z = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_z);
        this.relativeLayout_x = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_x);
        this.relativeLayout_c = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_c);
        this.relativeLayout_v = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_v);
        this.relativeLayout_b = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_b);
        this.relativeLayout_n = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_n);
        this.relativeLayout_m = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_m);
        this.relativeLayout_31 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_31);
        this.relativeLayout_32 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_32);
        this.relativeLayout_33 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_33);
        this.relativeLayout_34 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_34);
        this.relativeLayout_40 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_40);
        this.relativeLayout_41 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_41);
        this.relativeLayout_42 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_42);
        this.relativeLayout_43 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_43);
        this.relativeLayout_44 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_44);
        this.relativeLayout_45 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_45);
        this.relativeLayout_46 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_46);
        this.relativeLayout_47 = (RelativeLayout) this.keyboardView.findViewById(C0182R.id.relativeLayout_47);
        this.relativeLayoutView_List = new ArrayList();
        this.relativeLayoutView_List.add(this.relativeLayout_esc);
        this.relativeLayoutView_List.add(this.relativeLayout_1);
        this.relativeLayoutView_List.add(this.relativeLayout_2);
        this.relativeLayoutView_List.add(this.relativeLayout_3);
        this.relativeLayoutView_List.add(this.relativeLayout_4);
        this.relativeLayoutView_List.add(this.relativeLayout_5);
        this.relativeLayoutView_List.add(this.relativeLayout_6);
        this.relativeLayoutView_List.add(this.relativeLayout_7);
        this.relativeLayoutView_List.add(this.relativeLayout_8);
        this.relativeLayoutView_List.add(this.relativeLayout_9);
        this.relativeLayoutView_List.add(this.relativeLayout_0);
        this.relativeLayoutView_List.add(this.relativeLayout_01);
        this.relativeLayoutView_List.add(this.relativeLayout_02);
        this.relativeLayoutView_List.add(this.relativeLayout_03);
        this.relativeLayoutView_List.add(this.relativeLayout_10);
        this.relativeLayoutView_List.add(this.relativeLayout_q);
        this.relativeLayoutView_List.add(this.relativeLayout_w);
        this.relativeLayoutView_List.add(this.relativeLayout_e);
        this.relativeLayoutView_List.add(this.relativeLayout_r);
        this.relativeLayoutView_List.add(this.relativeLayout_t);
        this.relativeLayoutView_List.add(this.relativeLayout_y);
        this.relativeLayoutView_List.add(this.relativeLayout_u);
        this.relativeLayoutView_List.add(this.relativeLayout_i);
        this.relativeLayoutView_List.add(this.relativeLayout_o);
        this.relativeLayoutView_List.add(this.relativeLayout_p);
        this.relativeLayoutView_List.add(this.relativeLayout_11);
        this.relativeLayoutView_List.add(this.relativeLayout_12);
        this.relativeLayoutView_List.add(this.relativeLayout_13);
        this.relativeLayoutView_List.add(this.relativeLayout_20);
        this.relativeLayoutView_List.add(this.relativeLayout_a);
        this.relativeLayoutView_List.add(this.relativeLayout_s);
        this.relativeLayoutView_List.add(this.relativeLayout_d);
        this.relativeLayoutView_List.add(this.relativeLayout_f);
        this.relativeLayoutView_List.add(this.relativeLayout_g);
        this.relativeLayoutView_List.add(this.relativeLayout_h);
        this.relativeLayoutView_List.add(this.relativeLayout_j);
        this.relativeLayoutView_List.add(this.relativeLayout_k);
        this.relativeLayoutView_List.add(this.relativeLayout_l);
        this.relativeLayoutView_List.add(this.relativeLayout_21);
        this.relativeLayoutView_List.add(this.relativeLayout_22);
        this.relativeLayoutView_List.add(this.relativeLayout_23);
        this.relativeLayoutView_List.add(this.relativeLayout_30);
        this.relativeLayoutView_List.add(this.relativeLayout_z);
        this.relativeLayoutView_List.add(this.relativeLayout_x);
        this.relativeLayoutView_List.add(this.relativeLayout_c);
        this.relativeLayoutView_List.add(this.relativeLayout_v);
        this.relativeLayoutView_List.add(this.relativeLayout_b);
        this.relativeLayoutView_List.add(this.relativeLayout_n);
        this.relativeLayoutView_List.add(this.relativeLayout_m);
        this.relativeLayoutView_List.add(this.relativeLayout_31);
        this.relativeLayoutView_List.add(this.relativeLayout_32);
        this.relativeLayoutView_List.add(this.relativeLayout_33);
        this.relativeLayoutView_List.add(this.relativeLayout_34);
        this.relativeLayoutView_List.add(this.relativeLayout_40);
        this.relativeLayoutView_List.add(this.relativeLayout_41);
        this.relativeLayoutView_List.add(this.relativeLayout_42);
        this.relativeLayoutView_List.add(this.relativeLayout_43);
        this.relativeLayoutView_List.add(this.relativeLayout_44);
        this.relativeLayoutView_List.add(this.relativeLayout_45);
        this.relativeLayoutView_List.add(this.relativeLayout_46);
        this.relativeLayoutView_List.add(this.relativeLayout_47);
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
            default:
                return;
        }
    }

    private void selectItem(int relativeLayoutId, int position) {
        if (this.selecteddataList == null) {
            this.selecteddataList = new ArrayList();
            for (int i = 0; i < 61; i++) {
                this.selecteddataList.add(Integer.valueOf(0));
            }
        }
        if (((Integer) this.selecteddataList.get(position)).intValue() == 1) {
            lostFocus(position);
        } else {
            getFocus(position);
        }
    }

    public void setClickable() {
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
    }

    public void setColor(List<Integer> dataList) {
        this.colordataList = dataList;
        int len = this.relativeLayoutView_List.size();
        for (int i = 0; i < len; i++) {
            if (((Integer) dataList.get(i)).intValue() == 0) {
                ((RelativeLayout) this.relativeLayoutView_List.get(i)).setBackgroundColor(KeyboardLighteffectUtil.grayColor);
            } else {
                ((RelativeLayout) this.relativeLayoutView_List.get(i)).setBackgroundColor(((Integer) dataList.get(i)).intValue());
            }
        }
    }

    public void setSelectedColor(int colorValue) {
        if (this.selecteddataList != null && this.selecteddataList.size() >= 61) {
            for (int i = 0; i < 61; i++) {
                if (((Integer) this.selecteddataList.get(i)).intValue() == 1) {
                    setSelectedItemColor((RelativeLayout) this.relativeLayoutView_List.get(i), colorValue);
                    this.colordataList.set(i, Integer.valueOf(colorValue));
                }
            }
        }
    }

    public void setSelectedItemColor(RelativeLayout relativeLayout, int colorValue) {
        int strokeColor = Color.parseColor("#FFFFFF");
        int fillColor = colorValue;
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius((float) 0);
        gd.setStroke(3, strokeColor);
        relativeLayout.setBackground(gd);
    }

    public void getFocus(int position) {
        if (this.selecteddataList == null) {
            this.selecteddataList = new ArrayList();
            for (int i = 0; i < 61; i++) {
                this.selecteddataList.add(Integer.valueOf(0));
            }
        }
        this.selecteddataList.set(position, Integer.valueOf(1));
        RelativeLayout relativeLayout = (RelativeLayout) this.relativeLayoutView_List.get(position);
        int strokeColor = Color.parseColor("#FFFFFF");
        int fillColor = ((Integer) this.colordataList.get(position)).intValue();
        if (fillColor == 0) {
            fillColor = KeyboardLighteffectUtil.grayColor;
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius((float) 0);
        gd.setStroke(3, strokeColor);
        relativeLayout.setBackground(gd);
    }

    public void cancleAllSelected() {
        int i;
        if (this.selecteddataList == null) {
            this.selecteddataList = new ArrayList();
            for (i = 0; i < 61; i++) {
                this.selecteddataList.add(Integer.valueOf(0));
            }
            return;
        }
        for (i = 0; i < 61; i++) {
            if (((Integer) this.selecteddataList.get(i)).intValue() != 0) {
                lostFocus(i);
                this.selecteddataList.set(i, Integer.valueOf(1));
            }
        }
    }

    public int getSelectedColor() {
        int colorData = -1;
        if (this.selecteddataList == null) {
            return -1;
        }
        int len = this.selecteddataList.size();
        for (int i = 0; i < len; i++) {
            if (((Integer) this.selecteddataList.get(i)).intValue() == 1) {
                if (colorData == -1) {
                    colorData = ((Integer) this.colordataList.get(i)).intValue();
                } else if (colorData != ((Integer) this.colordataList.get(i)).intValue()) {
                    return -1;
                }
            }
        }
        return colorData;
    }

    public void lostFocus(int position) {
        if (this.selecteddataList == null) {
            this.selecteddataList = new ArrayList();
            for (int i = 0; i < 61; i++) {
                this.selecteddataList.add(Integer.valueOf(0));
            }
        }
        this.selecteddataList.set(position, Integer.valueOf(0));
        RelativeLayout relativeLayout = (RelativeLayout) this.relativeLayoutView_List.get(position);
        int strokeColor = Color.parseColor("#2E3135");
        int fillColor = ((Integer) this.colordataList.get(position)).intValue();
        if (fillColor == 0) {
            fillColor = KeyboardLighteffectUtil.grayColor;
        }
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(fillColor);
        gd.setCornerRadius((float) 0);
        gd.setStroke(0, strokeColor);
        relativeLayout.setBackground(gd);
    }
}
