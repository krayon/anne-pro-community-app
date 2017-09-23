package www.robinwatch.squid.utils;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;

public class ParseControlData {
    public static String parseControlData(int type, int version, String dev_id, byte[] data) {
        int i;
        byte[] rersult = new byte[28];
        C0213L.m13d("fuck tison type = " + type + ", version = " + version + ", dev_id = " + dev_id);
        String logString = AppConfig.SERVER_IP;
        for (byte b : data) {
            logString = new StringBuilder(String.valueOf(logString)).append(Integer.toHexString(b & MotionEventCompat.ACTION_MASK)).append(" ").toString();
        }
        C0213L.m13d("data = " + logString);
        rersult[0] = (byte) type;
        rersult[1] = (byte) version;
        byte[] dev_idByte = hexStringToBytes(dev_id);
        for (i = 0; i < dev_id.length() / 2; i++) {
            rersult[i + 2] = dev_idByte[i];
        }
        for (i = 0; i < data.length; i++) {
            rersult[i + 10] = data[i];
        }
        int crc_int = CRC_XModem(rersult, 26);
        C0213L.m19i("crc_int = " + crc_int);
        rersult[26] = (byte) (crc_int & MotionEventCompat.ACTION_MASK);
        rersult[27] = (byte) (crc_int >> 8);
        logString = AppConfig.SERVER_IP;
        for (byte b2 : rersult) {
            logString = new StringBuilder(String.valueOf(logString)).append(Integer.toHexString(b2 & MotionEventCompat.ACTION_MASK)).append(" ").toString();
        }
        C0213L.m13d("rersult = " + logString);
        String rersultString = printHexString(rersult);
        C0213L.m13d("rersultString = " + rersultString);
        return rersultString;
    }

    public static String printHexString(byte[] b) {
        String result = AppConfig.SERVER_IP;
        for (byte b2 : b) {
            String hex = Integer.toHexString(b2 & MotionEventCompat.ACTION_MASK);
            if (hex.length() == 1) {
                hex = new StringBuilder(String.valueOf('0')).append(hex).toString();
            }
            result = new StringBuilder(String.valueOf(result)).append(hex.toUpperCase()).toString();
        }
        return result;
    }

    public static int CRC_XModem(byte[] bytes, int lenth) {
        int crc = 0;
        for (int index = 0; index < lenth; index++) {
            byte b = bytes[index];
            for (int i = 0; i < 8; i++) {
                boolean bit;
                boolean c15;
                if (((b >> (7 - i)) & 1) == 1) {
                    bit = true;
                } else {
                    bit = false;
                }
                if (((crc >> 15) & 1) == 1) {
                    c15 = true;
                } else {
                    c15 = false;
                }
                crc <<= 1;
                if ((c15 ^ bit) != 0) {
                    crc ^= 4129;
                }
            }
        }
        return crc & SupportMenu.USER_MASK;
    }

    static short crcData(byte[] ptr, int count) {
        short crc = (short) 0;
        for (int j = 0; j < count; j++) {
            C0213L.m19i("fuck ....  ptr[j] = " + ptr[j]);
            C0213L.m19i("fuck ....  (short)ptr[j] = " + ((short) ptr[j]));
            crc = (short) ((((short) ptr[j]) << 8) ^ crc);
            int i = 8;
            do {
                if ((32768 & crc) != 0) {
                    crc = (short) ((crc << 1) ^ 4129);
                } else {
                    crc = (short) (crc << 1);
                }
                i--;
            } while (i > 0);
        }
        return crc;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals(AppConfig.SERVER_IP)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) ((charToByte(hexChars[pos]) << 4) | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}
