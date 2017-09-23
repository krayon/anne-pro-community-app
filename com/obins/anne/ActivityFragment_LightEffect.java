package com.obins.anne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.KeyboardLightEffect;
import com.obins.anne.utils.KeyboardLighteffectUtil;
import com.obins.anne.viewpart.RobinFragmetAdapter;
import com.obins.anne.viewpart.RobinViewPager;
import java.util.ArrayList;

public class ActivityFragment_LightEffect extends BaseActivityFragment implements OnClickListener {
    private static String TAG = "ActivityFragment_LightEffect";
    public static int customSelected = -1;
    public static int noarmalDynamicSelected = -1;
    public static int noarmalStaticSelected = -1;
    public static int whoIsSelected = -1;
    private RelativeLayout add_RelativeLayout;
    private RelativeLayout backBtn;
    private Fragment_CustomLightEffect fragment_CustomLightEffect = null;
    private Fragment_NormalDynamicLightEffect fragment_NormalDynamicLightEffect = null;
    private Fragment_NormalStaticLightEffect fragment_NormalStaticLightEffect = null;
    private TextView lefttitle_TextView;
    private ArrayList<Fragment> mFragmentList;
    private RobinViewPager mPager = null;
    private TextView middletitle_TextView;
    private RobinFragmetAdapter rfa;
    private TextView righttitle_TextView;

    class C02291 implements OnPageChangeListener {
        C02291() {
        }

        public void onPageSelected(int arg0) {
            if (arg0 == 0) {
                ActivityFragment_LightEffect.this.add_RelativeLayout.setVisibility(4);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextSize(18.0f);
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
            } else if (arg0 == 1) {
                ActivityFragment_LightEffect.this.add_RelativeLayout.setVisibility(4);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextSize(18.0f);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
            } else if (arg0 == 2) {
                ActivityFragment_LightEffect.this.add_RelativeLayout.setVisibility(0);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextSize(11.0f);
                ActivityFragment_LightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextSize(18.0f);
                ActivityFragment_LightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_LightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activityfragment_lighteffect);
        initUi();
    }

    public static void cancleAllSelected() {
        noarmalDynamicSelected = -1;
        noarmalStaticSelected = -1;
        customSelected = -1;
    }

    public void flushUI() {
        this.fragment_NormalDynamicLightEffect.flushUI();
        this.fragment_NormalStaticLightEffect.flushUI();
        this.fragment_CustomLightEffect.flushUI();
    }

    private void initUi() {
        this.add_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.add_RelativeLayout);
        this.add_RelativeLayout.setOnClickListener(this);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(this);
        this.middletitle_TextView = (TextView) findViewById(C0182R.id.middletitle_TextView);
        this.lefttitle_TextView = (TextView) findViewById(C0182R.id.lefttitle_TextView);
        this.righttitle_TextView = (TextView) findViewById(C0182R.id.righttitle_TextView);
        this.middletitle_TextView.setOnClickListener(this);
        this.lefttitle_TextView.setOnClickListener(this);
        this.righttitle_TextView.setOnClickListener(this);
        this.mPager = (RobinViewPager) findViewById(C0182R.id.viewpager);
        this.mFragmentList = new ArrayList();
        if (this.fragment_NormalDynamicLightEffect == null) {
            this.fragment_NormalDynamicLightEffect = new Fragment_NormalDynamicLightEffect();
        }
        if (this.fragment_NormalStaticLightEffect == null) {
            this.fragment_NormalStaticLightEffect = new Fragment_NormalStaticLightEffect();
        }
        if (this.fragment_CustomLightEffect == null) {
            this.fragment_CustomLightEffect = new Fragment_CustomLightEffect();
        }
        this.mFragmentList.add(this.fragment_NormalDynamicLightEffect);
        this.mFragmentList.add(this.fragment_NormalStaticLightEffect);
        this.mFragmentList.add(this.fragment_CustomLightEffect);
        this.rfa = new RobinFragmetAdapter(getSupportFragmentManager(), this.mFragmentList);
        this.mPager.setAdapter(this.rfa);
        this.mPager.setCurrentItem(1);
        this.add_RelativeLayout.setVisibility(4);
        this.mPager.setOnPageChangeListener(new C02291());
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.backBtn:
                finish();
                return;
            case C0182R.id.add_RelativeLayout:
                Intent intent = new Intent(this, ActivityFragment_CustomLightEffect.class);
                KeyboardLightEffect keyboardLightEffect = new KeyboardLightEffect();
                keyboardLightEffect.name = AppConfig.SERVER_IP;
                keyboardLightEffect.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_CUSTOM_STATIC;
                keyboardLightEffect.lightColorDataList = new ArrayList();
                keyboardLightEffect.lightColorDataList.addAll(KeyboardLighteffectUtil.defaultLightEffectListt);
                AppUtils.keyboardLightEffect = keyboardLightEffect;
                startActivity(intent);
                return;
            case C0182R.id.lefttitle_TextView:
                this.mPager.setCurrentItem(0);
                return;
            case C0182R.id.righttitle_TextView:
                this.mPager.setCurrentItem(2);
                return;
            case C0182R.id.middletitle_TextView:
                this.mPager.setCurrentItem(1);
                return;
            default:
                return;
        }
    }
}
