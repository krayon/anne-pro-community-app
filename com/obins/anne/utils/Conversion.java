package com.obins.anne.utils;

import android.support.v4.view.MotionEventCompat;
import java.util.Formatter;

public class Conversion {
    public static byte loUint16(short v) {
        return (byte) (v & MotionEventCompat.ACTION_MASK);
    }

    public static byte hiUint16(short v) {
        return (byte) (v >> 8);
    }

    public static short buildUint16(byte hi, byte lo) {
        return (short) ((hi << 8) + (lo & MotionEventCompat.ACTION_MASK));
    }

    public static String BytetohexString(byte[] b, int len) {
        StringBuilder sb = new StringBuilder(b.length * 3);
        Formatter formatter = new Formatter(sb);
        for (int i = 0; i < len; i++) {
            if (i < len - 1) {
                formatter.format("%02X:", new Object[]{Byte.valueOf(b[i])});
            } else {
                formatter.format("%02X", new Object[]{Byte.valueOf(b[i])});
            }
        }
        formatter.close();
        return sb.toString();
    }

    static String BytetohexString(byte[] b, boolean reverse) {
        StringBuilder sb = new StringBuilder(b.length * 3);
        Formatter formatter = new Formatter(sb);
        int i;
        if (reverse) {
            for (i = b.length - 1; i >= 0; i--) {
                if (i > 0) {
                    formatter.format("%02X:", new Object[]{Byte.valueOf(b[i])});
                } else {
                    formatter.format("%02X", new Object[]{Byte.valueOf(b[i])});
                }
            }
        } else {
            for (i = 0; i < b.length; i++) {
                if (i < b.length - 1) {
                    formatter.format("%02X:", new Object[]{Byte.valueOf(b[i])});
                } else {
                    formatter.format("%02X", new Object[]{Byte.valueOf(b[i])});
                }
            }
        }
        formatter.close();
        return sb.toString();
    }

    public static int hexStringtoByte(String sb, byte[] results) {
        int i = 0;
        boolean j = false;
        if (sb != null) {
            int k = 0;
            while (k < sb.length()) {
                if ((sb.charAt(k) >= '0' && sb.charAt(k) <= '9') || ((sb.charAt(k) >= 'a' && sb.charAt(k) <= 'f') || (sb.charAt(k) >= 'A' && sb.charAt(k) <= 'F'))) {
                    if (j) {
                        results[i] = (byte) (results[i] + ((byte) Character.digit(sb.charAt(k), 16)));
                        i++;
                    } else {
                        results[i] = (byte) (Character.digit(sb.charAt(k), 16) << 4);
                    }
                    if (j) {
                        j = false;
                    } else {
                        j = true;
                    }
                }
                k++;
            }
        }
        return i;
    }

    public static boolean isAsciiPrintable(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (!isAsciiPrintable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAsciiPrintable(char ch) {
        return ch >= ' ' && ch < '';
    }
}
