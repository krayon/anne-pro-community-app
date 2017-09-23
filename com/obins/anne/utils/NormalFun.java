package com.obins.anne.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class NormalFun {
    public static float getVerCode(Context context) {
        float verCode = 0.0f;
        try {
            verCode = Float.parseFloat(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    public static String getPath(Context context, String path) {
        String result = AppConfig.SERVER_IP;
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return context.getFilesDir().getPath();
        }
        File path1 = new File(path);
        if (!path1.exists()) {
            path1.mkdirs();
        }
        return path;
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        new ObjectOutputStream(byteOut).writeObject(src);
        return (List) new ObjectInputStream(new ByteArrayInputStream(byteOut.toByteArray())).readObject();
    }

    public static byte[] intToByteArray(int integer) {
        int i;
        if (integer < 0) {
            i = integer ^ -1;
        } else {
            i = integer;
        }
        int byteNum = (40 - Integer.numberOfLeadingZeros(i)) / 8;
        byte[] byteArray = new byte[4];
        for (int n = 0; n < byteNum; n++) {
            byteArray[3 - n] = (byte) (integer >>> (n * 8));
        }
        return byteArray;
    }

    public static int byteArrayToInt(byte[] b) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value += (b[i] & MotionEventCompat.ACTION_MASK) << ((3 - i) * 8);
        }
        return value;
    }

    public static int hexStringToInt(String hexStr) {
        if (hexStr == null || hexStr.isEmpty() || hexStr.length() != 2) {
            return 0;
        }
        int a = hexLetterToInt(hexStr.substring(0, 1));
        return (a * 16) + hexLetterToInt(hexStr.substring(1));
    }

    public static int hexLetterToInt(String letter) {
        if (letter.equals("a") || letter.equals("A")) {
            return 10;
        }
        if (letter.equals("b") || letter.equals("B")) {
            return 11;
        }
        if (letter.equals("c") || letter.equals("C")) {
            return 12;
        }
        if (letter.equals("d") || letter.equals("D")) {
            return 13;
        }
        if (letter.equals("e") || letter.equals("E")) {
            return 14;
        }
        if (letter.equals("f") || letter.equals("F")) {
            return 15;
        }
        return Integer.valueOf(letter).intValue();
    }
}
