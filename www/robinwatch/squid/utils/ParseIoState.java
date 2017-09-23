package www.robinwatch.squid.utils;

import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;

public class ParseIoState {

    public static class DeviceState {
        public int fw_ver;
        public int guardState;
        public String io1;
        public String io2;
        public int off_time_hour;
        public int off_time_minute;
        public String off_week;
        public int on_time_hour;
        public int on_time_minute;
        public String on_week;
    }

    public static DeviceState parse(String value) {
        DeviceState deviceState = new DeviceState();
        if (value == null || value.isEmpty() || value.length() != 32) {
            return null;
        }
        byte[] valueByte = hexStringToBytes(value);
        deviceState.fw_ver = valueByte[0] & MotionEventCompat.ACTION_MASK;
        deviceState.guardState = valueByte[1];
        deviceState.off_time_hour = valueByte[2];
        deviceState.off_time_minute = valueByte[3];
        deviceState.off_week = parseWeek(value.substring(8, 10));
        deviceState.on_time_hour = valueByte[5];
        deviceState.on_time_minute = valueByte[6];
        deviceState.on_week = parseWeek(value.substring(14, 16));
        deviceState.io2 = new StringBuilder(String.valueOf(valueByte[8])).toString();
        deviceState.io1 = new StringBuilder(String.valueOf(valueByte[9])).toString();
        return deviceState;
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

    private static String parseWeek(String value) {
        int i;
        String result = AppConfig.SERVER_IP;
        byte[] a = value.getBytes();
        result = Integer.toBinaryString(uniteBytes(a[0], a[1]));
        String result_cp = AppConfig.SERVER_IP;
        int length = result.length();
        for (i = 0; i < length; i++) {
            result_cp = new StringBuilder(String.valueOf(result_cp)).append(result.substring((length - i) - 1, length - i)).toString();
        }
        for (i = result_cp.length(); i < 8; i++) {
            result_cp = new StringBuilder(String.valueOf(result_cp)).append("0").toString();
        }
        return result_cp;
    }

    public static byte uniteBytes(byte src0, byte src1) {
        return (byte) (((byte) (Byte.decode("0x" + new String(new byte[]{src0})).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{src1})).byteValue());
    }
}
