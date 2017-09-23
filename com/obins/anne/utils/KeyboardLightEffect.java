package com.obins.anne.utils;

import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import java.util.ArrayList;
import java.util.List;

public class KeyboardLightEffect {
    public static int LIGHTEFFECT_TYPE_CUSTOM_DYNAMIC = 3;
    public static int LIGHTEFFECT_TYPE_CUSTOM_STATIC = 2;
    public static int LIGHTEFFECT_TYPE_NORMAL_DYNAMIC = 0;
    public static int LIGHTEFFECT_TYPE_NORMAL_STATIC = 1;
    public int _id = -1;
    public List<Integer> lightColorDataList;
    public int lighteffectType;
    public String name;

    public static int getKeyboardLightEffectID(List<Integer> dataList) {
        int i;
        byte[] resultByte = new byte[214];
        int j = 0;
        for (i = 0; i < 214; i++) {
            resultByte[i] = (byte) 0;
        }
        i = 0;
        while (i < 70) {
            if (!(i == 40 || i == 53 || i == 54 || i == 59 || i == 60 || i == 62 || i == 63 || i == 64 || i == 65)) {
                int colorData = ((Integer) dataList.get(j)).intValue();
                int g = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & colorData) >> 8;
                int b = colorData & MotionEventCompat.ACTION_MASK;
                resultByte[(i * 3) + 4] = (byte) ((16711680 & colorData) >> 16);
                resultByte[((i * 3) + 4) + 1] = (byte) g;
                resultByte[((i * 3) + 4) + 2] = (byte) b;
                j++;
            }
            i++;
        }
        return CRC16.calcCrc16(resultByte, 4, 210);
    }

    public static byte[] turn61keyColorTo70RGB(List<Integer> dataList) {
        int i;
        byte[] resultByte = new byte[214];
        int j = 0;
        for (i = 0; i < 214; i++) {
            resultByte[i] = (byte) 0;
        }
        i = 0;
        while (i < 70) {
            if (!(i == 40 || i == 53 || i == 54 || i == 59 || i == 60 || i == 62 || i == 63 || i == 64 || i == 65)) {
                int colorData = ((Integer) dataList.get(j)).intValue();
                int g = (MotionEventCompat.ACTION_POINTER_INDEX_MASK & colorData) >> 8;
                int b = colorData & MotionEventCompat.ACTION_MASK;
                resultByte[(i * 3) + 4] = (byte) ((16711680 & colorData) >> 16);
                resultByte[((i * 3) + 4) + 1] = (byte) g;
                resultByte[((i * 3) + 4) + 2] = (byte) b;
                j++;
            }
            i++;
        }
        byte[] bArr = new byte[4];
        bArr = NormalFun.intToByteArray(CRC16.calcCrc16(resultByte, 4, 210));
        for (i = 0; i < 4; i++) {
            C0188L.m7i("crcByte[i] = " + bArr[i]);
            resultByte[i] = bArr[i];
        }
        return resultByte;
    }

    public static List<Integer> turn210KeyColorTo61(List<Integer> dataList) {
        List<Integer> result = new ArrayList();
        int i = 0;
        while (i < 70) {
            if (!(i == 40 || i == 53 || i == 54 || i == 59 || i == 60 || i == 62 || i == 63 || i == 64 || i == 65)) {
                result.add(Integer.valueOf(Color.rgb(((Integer) dataList.get(i * 3)).intValue(), ((Integer) dataList.get((i * 3) + 1)).intValue(), ((Integer) dataList.get((i * 3) + 2)).intValue())));
            }
            i++;
        }
        return result;
    }
}
