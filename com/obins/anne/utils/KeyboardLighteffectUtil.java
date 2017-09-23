package com.obins.anne.utils;

import com.obins.anne.C0182R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyboardLighteffectUtil {
    public static List<Integer> agengtingflagColorLightEffectList = new ArrayList();
    public static List<Integer> blueColorLightEffectList = new ArrayList();
    public static List<Integer> closeLightEffectList;
    public static List<Integer> cyanColorLightEffectList = new ArrayList();
    public static List<Integer> dandianchangliangColorLightEffectList = new ArrayList();
    public static List<Integer> dandianliangColorLightEffectList = new ArrayList();
    public static List<Integer> defaultKeyboardColorList = new ArrayList();
    public static List<Integer> defaultLightEffectListt = new ArrayList();
    public static List<Integer> etalyflagColorLightEffectList = new ArrayList();
    public static List<Integer> franceflagColorLightEffectList = new ArrayList();
    public static int grayColor = AppUtils.appUtils.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray);
    public static List<Integer> grayLightEffectListt = new ArrayList();
    public static List<Integer> greenColorLightEffectList = new ArrayList();
    public static List<Integer> huxidengColorLightEffectList = new ArrayList();
    public static List<Integer> iceblueColorLightEffectList = new ArrayList();
    public static final HashMap<Integer, String> lightEffectIDAndNameMap = new HashMap();
    public static List<Integer> mixedColorLightEffectList = new ArrayList();
    public static List<KeyboardLightEffect> normalDynamicKeyboardLightEffectList = new ArrayList();
    public static List<KeyboardLightEffect> normalStaticKeyboardLightEffectList = new ArrayList();
    public static List<Integer> orangeColorLightEffectList = new ArrayList();
    public static List<Integer> paopaotangColorLightEffectList = new ArrayList();
    public static List<Integer> pinkColorLightEffectList = new ArrayList();
    public static List<Integer> purpleColorLightEffectList = new ArrayList();
    public static List<Integer> purpleColorList = new ArrayList();
    public static List<Integer> qicailianyiColorLightEffectList = new ArrayList();
    public static List<Integer> qicaipaomadengColorLightEffectList = new ArrayList();
    public static List<Integer> redColorLightEffectList = new ArrayList();
    public static List<Integer> whiteColorLightEffectList = new ArrayList();
    public static List<Integer> wucaibinfengColorLightEffectList = new ArrayList();
    public static List<Integer> xingkongcuicanColorLightEffectList = new ArrayList();
    public static List<Integer> yellowColorLightEffectList = new ArrayList();

    static {
        int i;
        lightEffectIDAndNameMap.put(Integer.valueOf(0), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_off));
        lightEffectIDAndNameMap.put(Integer.valueOf(1), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_red));
        lightEffectIDAndNameMap.put(Integer.valueOf(2), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_yellow));
        lightEffectIDAndNameMap.put(Integer.valueOf(3), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_green));
        lightEffectIDAndNameMap.put(Integer.valueOf(4), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_cyan));
        lightEffectIDAndNameMap.put(Integer.valueOf(5), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_blue));
        lightEffectIDAndNameMap.put(Integer.valueOf(6), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_pure));
        lightEffectIDAndNameMap.put(Integer.valueOf(7), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_pink));
        lightEffectIDAndNameMap.put(Integer.valueOf(8), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_orange));
        lightEffectIDAndNameMap.put(Integer.valueOf(9), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_white));
        lightEffectIDAndNameMap.put(Integer.valueOf(10), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_france));
        lightEffectIDAndNameMap.put(Integer.valueOf(11), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_italy));
        lightEffectIDAndNameMap.put(Integer.valueOf(12), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_argentina));
        lightEffectIDAndNameMap.put(Integer.valueOf(13), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_breath));
        lightEffectIDAndNameMap.put(Integer.valueOf(14), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_rainbow));
        lightEffectIDAndNameMap.put(Integer.valueOf(15), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_breath));
        lightEffectIDAndNameMap.put(Integer.valueOf(16), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_on));
        lightEffectIDAndNameMap.put(Integer.valueOf(17), "七彩涟漪");
        lightEffectIDAndNameMap.put(Integer.valueOf(18), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_poptang));
        lightEffectIDAndNameMap.put(Integer.valueOf(19), AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_multi_colored));
        lightEffectIDAndNameMap.put(Integer.valueOf(20), "星光璀璨");
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        for (i = 0; i < 12; i++) {
            mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_red)));
        }
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        for (i = 0; i < 12; i++) {
            mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_red)));
        }
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        for (i = 0; i < 11; i++) {
            mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_red)));
        }
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        for (i = 0; i < 10; i++) {
            mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_red)));
        }
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_red)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        mixedColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.mixed_lighteffect_blue)));
        for (i = 0; i < 61; i++) {
            purpleColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_purplen)));
        }
        for (i = 0; i < 61; i++) {
            purpleColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.purple_lighteffect_purple)));
        }
        for (i = 0; i < 61; i++) {
            redColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 61; i++) {
            blueColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 61; i++) {
            greenColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 61; i++) {
            cyanColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        }
        for (i = 0; i < 61; i++) {
            yellowColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        }
        for (i = 0; i < 61; i++) {
            orangeColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        }
        for (i = 0; i < 61; i++) {
            pinkColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_pink)));
        }
        for (i = 0; i < 61; i++) {
            whiteColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 61; i++) {
            iceblueColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_iceblue)));
        }
        for (i = 0; i < 6; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 5; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 3; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 5; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 5; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 4; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 5; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 3; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 5; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 3; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_blue)));
        }
        for (i = 0; i < 1; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            franceflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 6; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 5; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 3; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 5; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 5; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 4; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 5; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 3; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 5; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 3; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_green)));
        }
        for (i = 0; i < 1; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 4; i++) {
            etalyflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        }
        for (i = 0; i < 28; i++) {
            agengtingflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        }
        for (i = 0; i < 13; i++) {
            agengtingflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        }
        for (i = 0; i < 20; i++) {
            agengtingflagColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        }
        for (i = 0; i < 61; i++) {
            huxidengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        }
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_14)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_14)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        qicaipaomadengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        for (i = 0; i < 61; i++) {
            dandianliangColorLightEffectList.add(Integer.valueOf(0));
        }
        dandianliangColorLightEffectList.set(32, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        for (i = 0; i < 61; i++) {
            dandianchangliangColorLightEffectList.add(Integer.valueOf(0));
        }
        dandianchangliangColorLightEffectList.set(36, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        for (i = 0; i < 61; i++) {
            qicailianyiColorLightEffectList.add(Integer.valueOf(0));
        }
        qicailianyiColorLightEffectList.set(34, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_red)));
        qicailianyiColorLightEffectList.set(20, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(21, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(33, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(35, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(46, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(47, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_orange)));
        qicailianyiColorLightEffectList.set(6, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(7, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(8, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(19, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(22, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(32, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(36, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(45, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        qicailianyiColorLightEffectList.set(48, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_yellow)));
        for (i = 0; i < 61; i++) {
            paopaotangColorLightEffectList.add(Integer.valueOf(0));
        }
        paopaotangColorLightEffectList.set(10, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        for (i = 14; i < 28; i++) {
            paopaotangColorLightEffectList.set(i, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        }
        paopaotangColorLightEffectList.set(38, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        paopaotangColorLightEffectList.set(51, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        paopaotangColorLightEffectList.set(59, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_cyan)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_12)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_14)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_4)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_6)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_10)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_11)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_2)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_9)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_7)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_5)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_8)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_13)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_3)));
        wucaibinfengColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.qicaibinfeng_lighteffect_1)));
        for (i = 0; i < 61; i++) {
            xingkongcuicanColorLightEffectList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_purplen)));
        }
        xingkongcuicanColorLightEffectList.set(2, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        xingkongcuicanColorLightEffectList.set(18, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        xingkongcuicanColorLightEffectList.set(40, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        xingkongcuicanColorLightEffectList.set(10, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        xingkongcuicanColorLightEffectList.set(55, Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.solid_lighteffect_white)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_1)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_2)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_3)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_4)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_5)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_6)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_7)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_8)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_9)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_10)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_11)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_12)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_13)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_14)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_15)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_16)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_17)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_18)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_19)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_20)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_21)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_22)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_23)));
        defaultKeyboardColorList.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.color_pan_24)));
        for (i = 0; i < 61; i++) {
            grayLightEffectListt.add(Integer.valueOf(AppUtils.appUtils.getResources().getColor(C0182R.color.lighteffectpage_titletip_unselected_gray)));
        }
        for (i = 0; i < 61; i++) {
            defaultLightEffectListt.add(Integer.valueOf(0));
        }
        KeyboardLightEffect keyboardLightEffectClose = new KeyboardLightEffect();
        keyboardLightEffectClose.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_off);
        keyboardLightEffectClose.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffectClose.lightColorDataList = defaultLightEffectListt;
        KeyboardLightEffect keyboardLightEffect_huxideng = new KeyboardLightEffect();
        keyboardLightEffect_huxideng.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_breath);
        keyboardLightEffect_huxideng.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_huxideng.lightColorDataList = huxidengColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_qicaipaomadeng = new KeyboardLightEffect();
        keyboardLightEffect_qicaipaomadeng.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_rainbow);
        keyboardLightEffect_qicaipaomadeng.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_qicaipaomadeng.lightColorDataList = qicaipaomadengColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_dandianliang = new KeyboardLightEffect();
        keyboardLightEffect_dandianliang.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_breath);
        keyboardLightEffect_dandianliang.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_dandianliang.lightColorDataList = dandianliangColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_dandianchangliang = new KeyboardLightEffect();
        keyboardLightEffect_dandianchangliang.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_on);
        keyboardLightEffect_dandianchangliang.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_dandianchangliang.lightColorDataList = dandianchangliangColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_qicailianyi = new KeyboardLightEffect();
        keyboardLightEffect_qicailianyi.name = "七彩涟漪";
        keyboardLightEffect_qicailianyi.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_qicailianyi.lightColorDataList = qicailianyiColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_paopaotang = new KeyboardLightEffect();
        keyboardLightEffect_paopaotang.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_single_trigger_poptang);
        keyboardLightEffect_paopaotang.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_paopaotang.lightColorDataList = paopaotangColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_wucaibingfeng = new KeyboardLightEffect();
        keyboardLightEffect_wucaibingfeng.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_multi_colored);
        keyboardLightEffect_wucaibingfeng.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_wucaibingfeng.lightColorDataList = wucaibinfengColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_xingguangcuican = new KeyboardLightEffect();
        keyboardLightEffect_xingguangcuican.name = "星光璀璨";
        keyboardLightEffect_xingguangcuican.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffect_xingguangcuican.lightColorDataList = xingkongcuicanColorLightEffectList;
        normalDynamicKeyboardLightEffectList.clear();
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffectClose);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_huxideng);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_qicaipaomadeng);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_dandianliang);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_dandianchangliang);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_paopaotang);
        normalDynamicKeyboardLightEffectList.add(keyboardLightEffect_wucaibingfeng);
        keyboardLightEffectClose = new KeyboardLightEffect();
        keyboardLightEffectClose.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_off);
        keyboardLightEffectClose.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_DYNAMIC;
        keyboardLightEffectClose.lightColorDataList = defaultLightEffectListt;
        KeyboardLightEffect keyboardLightEffect_red = new KeyboardLightEffect();
        keyboardLightEffect_red.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_red);
        keyboardLightEffect_red.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_red.lightColorDataList = redColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_blue = new KeyboardLightEffect();
        keyboardLightEffect_blue.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_blue);
        keyboardLightEffect_blue.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_blue.lightColorDataList = blueColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_green = new KeyboardLightEffect();
        keyboardLightEffect_green.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_green);
        keyboardLightEffect_green.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_green.lightColorDataList = greenColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_iceblue = new KeyboardLightEffect();
        keyboardLightEffect_iceblue.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_blue);
        keyboardLightEffect_iceblue.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_iceblue.lightColorDataList = iceblueColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_cyan = new KeyboardLightEffect();
        keyboardLightEffect_cyan.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_cyan);
        keyboardLightEffect_cyan.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_cyan.lightColorDataList = cyanColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_purple = new KeyboardLightEffect();
        keyboardLightEffect_purple.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_pure);
        keyboardLightEffect_purple.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_purple.lightColorDataList = purpleColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_yellow = new KeyboardLightEffect();
        keyboardLightEffect_yellow.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_yellow);
        keyboardLightEffect_yellow.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_yellow.lightColorDataList = yellowColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_orange = new KeyboardLightEffect();
        keyboardLightEffect_orange.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_orange);
        keyboardLightEffect_orange.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_orange.lightColorDataList = orangeColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_pink = new KeyboardLightEffect();
        keyboardLightEffect_pink.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_pink);
        keyboardLightEffect_pink.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_pink.lightColorDataList = pinkColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_white = new KeyboardLightEffect();
        keyboardLightEffect_white.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_white);
        keyboardLightEffect_white.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_white.lightColorDataList = whiteColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_franceflag = new KeyboardLightEffect();
        keyboardLightEffect_franceflag.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_france);
        keyboardLightEffect_franceflag.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_franceflag.lightColorDataList = franceflagColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_etalyflag = new KeyboardLightEffect();
        keyboardLightEffect_etalyflag.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_italy);
        keyboardLightEffect_etalyflag.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_etalyflag.lightColorDataList = etalyflagColorLightEffectList;
        KeyboardLightEffect keyboardLightEffect_agengtingflag = new KeyboardLightEffect();
        keyboardLightEffect_agengtingflag.name = AppUtils.appUtils.getResources().getString(C0182R.string.light_effect_theme_flag_argentina);
        keyboardLightEffect_agengtingflag.lighteffectType = KeyboardLightEffect.LIGHTEFFECT_TYPE_NORMAL_STATIC;
        keyboardLightEffect_agengtingflag.lightColorDataList = agengtingflagColorLightEffectList;
        normalStaticKeyboardLightEffectList.clear();
        normalStaticKeyboardLightEffectList.add(keyboardLightEffectClose);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_red);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_yellow);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_green);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_cyan);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_blue);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_purple);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_pink);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_orange);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_white);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_franceflag);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_etalyflag);
        normalStaticKeyboardLightEffectList.add(keyboardLightEffect_agengtingflag);
    }
}
