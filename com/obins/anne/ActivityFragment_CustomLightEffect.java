package com.obins.anne;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.obins.anne.db.KeyboardLightEffectDB;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.AppUtils;
import com.obins.anne.utils.DensityUtil;
import com.obins.anne.utils.KeyboardLightEffect;
import com.obins.anne.viewpart.KeyboardView;
import com.obins.anne.viewpart.RobinFragmetAdapter;
import com.obins.anne.viewpart.RobinViewPagerNoScrollTo;
import java.util.ArrayList;
import java.util.List;

public class ActivityFragment_CustomLightEffect extends BaseActivityFragment implements OnClickListener {
    private static String TAG = "ActivityFragment_CustomLightEffect";
    private RelativeLayout backBtn;
    private TextView colortip_TextView;
    private RelativeLayout delete_RelativeLayout;
    private Fragment_CustomLightEffect_BaseSetting fragment_CustomLightEffect_BaseSetting = null;
    private Fragment_CustomLightEffect_Dynamic fragment_CustomLightEffect_Dynamic = null;
    private Fragment_CustomLightEffect_SingleColor fragment_CustomLightEffect_SingleColor = null;
    private KeyboardLightEffect keyboardLightEffect;
    private KeyboardView keyboardView;
    public LinearLayout keyboard_LinearLayout;
    private View leftbuttom_View;
    private TextView lefttitle_TextView;
    private ArrayList<Fragment> mFragmentList;
    private RobinViewPagerNoScrollTo mPager = null;
    private View middlebuttom_View;
    private TextView middletitle_TextView;
    private EditText name_TextView;
    private RobinFragmetAdapter rfa;
    private View rightbuttom_View;
    private TextView righttitle_TextView;
    private RelativeLayout save_RelativeLayout;

    class C02281 implements OnPageChangeListener {
        C02281() {
        }

        public void onPageSelected(int arg0) {
            if (arg0 == 0) {
                ActivityFragment_CustomLightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
                ActivityFragment_CustomLightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.leftbuttom_View.setVisibility(0);
                ActivityFragment_CustomLightEffect.this.middlebuttom_View.setVisibility(4);
                ActivityFragment_CustomLightEffect.this.rightbuttom_View.setVisibility(4);
            } else if (arg0 == 1) {
                ActivityFragment_CustomLightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
                ActivityFragment_CustomLightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.leftbuttom_View.setVisibility(4);
                ActivityFragment_CustomLightEffect.this.middlebuttom_View.setVisibility(0);
                ActivityFragment_CustomLightEffect.this.rightbuttom_View.setVisibility(4);
            } else if (arg0 == 2) {
                ActivityFragment_CustomLightEffect.this.lefttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.middletitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray));
                ActivityFragment_CustomLightEffect.this.righttitle_TextView.setTextColor(ActivityFragment_CustomLightEffect.this.getResources().getColor(C0182R.color.lighteffectpage_titletip_selected_orange));
                ActivityFragment_CustomLightEffect.this.leftbuttom_View.setVisibility(4);
                ActivityFragment_CustomLightEffect.this.middlebuttom_View.setVisibility(4);
                ActivityFragment_CustomLightEffect.this.rightbuttom_View.setVisibility(0);
            }
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0182R.layout.activityfragment_customlighteffect);
        initdata();
        initUi();
    }

    private void initdata() {
        this.keyboardLightEffect = AppUtils.keyboardLightEffect;
    }

    private void initUi() {
        this.delete_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.delete_RelativeLayout);
        this.save_RelativeLayout = (RelativeLayout) findViewById(C0182R.id.save_RelativeLayout);
        if (this.keyboardLightEffect.lighteffectType == KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC || this.keyboardLightEffect.lighteffectType == KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC) {
            this.delete_RelativeLayout.setVisibility(4);
        }
        this.delete_RelativeLayout.setOnClickListener(this);
        this.save_RelativeLayout.setOnClickListener(this);
        this.name_TextView = (EditText) findViewById(C0182R.id.name_TextView);
        this.name_TextView.setText(this.keyboardLightEffect.name);
        this.backBtn = (RelativeLayout) findViewById(C0182R.id.backBtn);
        this.backBtn.setOnClickListener(this);
        this.middletitle_TextView = (TextView) findViewById(C0182R.id.middletitle_TextView);
        this.lefttitle_TextView = (TextView) findViewById(C0182R.id.lefttitle_TextView);
        this.righttitle_TextView = (TextView) findViewById(C0182R.id.righttitle_TextView);
        this.leftbuttom_View = findViewById(C0182R.id.leftbuttom_View);
        this.middlebuttom_View = findViewById(C0182R.id.middlebuttom_View);
        this.rightbuttom_View = findViewById(C0182R.id.rightbuttom_View);
        this.middletitle_TextView.setOnClickListener(this);
        this.lefttitle_TextView.setOnClickListener(this);
        this.righttitle_TextView.setOnClickListener(this);
        this.keyboard_LinearLayout = (LinearLayout) findViewById(C0182R.id.keyboard_LinearLayout);
        this.colortip_TextView = (TextView) findViewById(C0182R.id.colortip_TextView);
        this.colortip_TextView.setText(AppConfig.SERVER_IP);
        LayoutParams keyboardView_linearParams = (LayoutParams) this.keyboard_LinearLayout.getLayoutParams();
        keyboardView_linearParams.height = (int) (((((float) DensityUtil.getScreenWidthPixels(this)) - (2.0f * DensityUtil.dip2px(this, 10.0f))) / 15.0f) * 5.0f);
        this.keyboard_LinearLayout.setLayoutParams(keyboardView_linearParams);
        this.keyboardView = new KeyboardView(this.keyboard_LinearLayout);
        this.keyboardView.setColor(this.keyboardLightEffect.lightColorDataList);
        this.keyboardView.setClickable();
        this.mPager = (RobinViewPagerNoScrollTo) findViewById(C0182R.id.viewpager);
        this.mFragmentList = new ArrayList();
        if (this.fragment_CustomLightEffect_BaseSetting == null) {
            this.fragment_CustomLightEffect_BaseSetting = new Fragment_CustomLightEffect_BaseSetting();
        }
        if (this.fragment_CustomLightEffect_SingleColor == null) {
            this.fragment_CustomLightEffect_SingleColor = new Fragment_CustomLightEffect_SingleColor();
        }
        if (this.fragment_CustomLightEffect_Dynamic == null) {
            this.fragment_CustomLightEffect_Dynamic = new Fragment_CustomLightEffect_Dynamic();
        }
        this.mFragmentList.add(this.fragment_CustomLightEffect_BaseSetting);
        this.mFragmentList.add(this.fragment_CustomLightEffect_SingleColor);
        this.mFragmentList.add(this.fragment_CustomLightEffect_Dynamic);
        this.rfa = new RobinFragmetAdapter(getSupportFragmentManager(), this.mFragmentList);
        this.mPager.setAdapter(this.rfa);
        this.mPager.setCurrentItem(0);
        if (this.keyboardLightEffect.lighteffectType != KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC) {
            this.fragment_CustomLightEffect_BaseSetting.setInvisble();
        }
        this.mPager.setOnPageChangeListener(new C02281());
    }

    public void cancleAllSelected() {
        this.keyboardView.cancleAllSelected();
    }

    public void setFocus(List<Integer> valueList) {
        int len = valueList.size();
        for (int i = 0; i < len; i++) {
            this.keyboardView.getFocus(((Integer) valueList.get(i)).intValue());
        }
        setColorTip();
    }

    public void cancleFoucs(List<Integer> valueList) {
        int len = valueList.size();
        for (int i = 0; i < len; i++) {
            this.keyboardView.lostFocus(((Integer) valueList.get(i)).intValue());
        }
        setColorTip();
    }

    public void setSelectedColor(int colorValue) {
        this.keyboardView.setSelectedColor(colorValue);
        setColorTip();
    }

    private void setColorTip() {
        int colorData = this.keyboardView.getSelectedColor();
        if (colorData == -1) {
            this.colortip_TextView.setText(AppConfig.SERVER_IP);
            return;
        }
        int green = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & colorData) >> 8;
        int blue = colorData & MotionEventCompat.ACTION_MASK;
        this.colortip_TextView.setText(new StringBuilder(String.valueOf(getResources().getString(C0182R.string.color))).append(" R:").append((16711680 & colorData) >> 16).append(" G:").append(green).append(" B:").append(blue).toString());
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case C0182R.id.backBtn:
                finish();
                return;
            case C0182R.id.name_TextView:
                final AlertDialog dialog = new Builder(this).create();
                dialog.setView((RelativeLayout) ((LayoutInflater) getSystemService("layout_inflater")).inflate(C0182R.layout.input_lighteffect_prompt, null));
                dialog.show();
                dialog.getWindow().setContentView(C0182R.layout.input_lighteffect_prompt);
                ((TextView) dialog.findViewById(C0182R.id.title_TextView)).setText(getResources().getString(C0182R.string.please_input_lighteffect_name));
                final EditText content_EditText = (EditText) dialog.findViewById(C0182R.id.content_EditText);
                content_EditText.setText(this.name_TextView.getText().toString());
                content_EditText.setSelection(this.name_TextView.getText().toString().length());
                content_EditText.setFocusable(true);
                Button cancle_Button1 = (Button) dialog.findViewById(C0182R.id.cancle_Button);
                ((Button) dialog.findViewById(C0182R.id.sure_Button)).setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        if (content_EditText.getText().toString().length() != 0) {
                            ActivityFragment_CustomLightEffect.this.name_TextView.setText(content_EditText.getText().toString());
                        }
                        dialog.cancel();
                    }
                });
                cancle_Button1.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        dialog.cancel();
                    }
                });
                return;
            case C0182R.id.delete_RelativeLayout:
                final AlertDialog alertDialog = new Builder(this).create();
                alertDialog.show();
                Window window = alertDialog.getWindow();
                window.setContentView(C0182R.layout.delete_prompt);
                ((TextView) window.findViewById(C0182R.id.title_TextView)).setText(getResources().getString(C0182R.string.tip));
                ((TextView) window.findViewById(C0182R.id.content_TextView)).setText(getResources().getString(C0182R.string.is_delete_lighteffect));
                Button delete_Button = (Button) window.findViewById(C0182R.id.delete_Button);
                delete_Button.setText(getResources().getString(C0182R.string.sure));
                Button cancle_Button = (Button) window.findViewById(C0182R.id.cancle_Button);
                cancle_Button.setText(getResources().getString(C0182R.string.cancle));
                delete_Button.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        alertDialog.cancel();
                        AppUtils.databaseManager.deleteKeyboardLightEffectDB(ActivityFragment_CustomLightEffect.this.keyboardLightEffect._id);
                        ActivityFragment_CustomLightEffect.this.finish();
                    }
                });
                cancle_Button.setOnClickListener(new OnClickListener() {
                    public void onClick(View arg0) {
                        alertDialog.cancel();
                    }
                });
                return;
            case C0182R.id.save_RelativeLayout:
                KeyboardLightEffectDB keyboardLightEffectDB = new KeyboardLightEffectDB();
                keyboardLightEffectDB.name = this.name_TextView.getText().toString();
                if (keyboardLightEffectDB.name == null || keyboardLightEffectDB.name.isEmpty()) {
                    keyboardLightEffectDB.name = getResources().getString(C0182R.string.light_effect_title_user);
                }
                keyboardLightEffectDB.type = KeyboardLightEffect.LIGHTEFFECT_TYPE_CUSTOM_STATIC;
                keyboardLightEffectDB._id = this.keyboardLightEffect._id;
                keyboardLightEffectDB.keycolor = KeyboardLightEffectDB.colorListToString(this.keyboardView.getColordataList());
                keyboardLightEffectDB.lightEffectID = KeyboardLightEffect.getKeyboardLightEffectID(this.keyboardView.getColordataList());
                if (this.keyboardLightEffect._id == -1) {
                    this.keyboardLightEffect._id = AppUtils.databaseManager.addKeyboardLightEffectDB(keyboardLightEffectDB);
                } else {
                    AppUtils.databaseManager.modifyKeyboardLightEffectDB(keyboardLightEffectDB);
                }
                Toast.makeText(this, getResources().getString(C0182R.string.save_success), 0).show();
                finish();
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
