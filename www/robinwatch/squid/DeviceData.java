package www.robinwatch.squid;

import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;
import org.json.JSONObject;
import www.robinwatch.squid.utils.C0213L;

public class DeviceData {
    public static final int CHECK_END = 18;
    public static final int CHECK_START = 18;
    public static final int DATA_LEN = 19;
    public static final int DEV_ID_START = 9;
    public static final int DEV_ID__END = 16;
    public static final int DEV_STATE_END = 17;
    public static final int DEV_STATE_START = 17;
    public static final int HEAD_END = 1;
    public static final int HEAD_START = 0;
    public static final int MAC_END = 8;
    public static final int MAC_START = 3;
    public static final int VER_END = 2;
    public static final int VER_START = 2;
    public String check;
    public String dev_id;
    public String head;
    public String mac;
    public String state;
    public String ver;

    public static JSONObject parseData(byte[] data) {
        JSONObject jsonObject = new JSONObject();
        String headString = AppConfig.SERVER_IP;
        String verString = AppConfig.SERVER_IP;
        String macString = AppConfig.SERVER_IP;
        String idString = AppConfig.SERVER_IP;
        String stateString = AppConfig.SERVER_IP;
        String checkString = AppConfig.SERVER_IP;
        String readBuf = "parseData :";
        if (data.length < 20) {
            return null;
        }
        int i;
        for (i = 0; i < 19; i++) {
            readBuf = new StringBuilder(String.valueOf(readBuf)).append(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK)).append(" ").toString();
        }
        C0213L.m19i(readBuf);
        for (i = 0; i < 2; i++) {
            headString = new StringBuilder(String.valueOf(headString)).append(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK)).toString();
        }
        C0213L.m19i("headString = " + headString);
        String a;
        if (headString.equals("2018")) {
            C0213L.m19i(" Old Version ");
            for (i = 2; i < 3; i++) {
                verString = new StringBuilder(String.valueOf(verString)).append(data[i] & MotionEventCompat.ACTION_MASK).toString();
            }
            for (i = 3; i < 9; i++) {
                a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
                if (a.length() == 1) {
                    a = "0" + a;
                }
                macString = new StringBuilder(String.valueOf(macString)).append(a).toString();
            }
            for (i = 9; i < 17; i++) {
                a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
                if (a.length() == 1) {
                    a = "0" + a;
                }
                idString = new StringBuilder(String.valueOf(idString)).append(a).toString();
            }
            for (i = 17; i < 18; i++) {
                stateString = new StringBuilder(String.valueOf(stateString)).append(data[i] & MotionEventCompat.ACTION_MASK).toString();
            }
            for (i = 18; i < 19; i++) {
                checkString = new StringBuilder(String.valueOf(checkString)).append(data[i] & MotionEventCompat.ACTION_MASK).toString();
            }
            try {
                jsonObject.put("ver", verString);
                jsonObject.put("head", headString);
                jsonObject.put("mac", macString);
                jsonObject.put("id", idString);
                jsonObject.put("state", stateString);
                jsonObject.put("devicedata", AppConfig.SERVER_IP);
                jsonObject.put("check", checkString);
                return jsonObject;
            } catch (Exception e) {
                e.printStackTrace();
                return jsonObject;
            }
        }
        C0213L.m19i(" NEW Version ");
        String cmdTypeString = AppConfig.SERVER_IP;
        cmdTypeString = Integer.toHexString(data[0] & MotionEventCompat.ACTION_MASK);
        String cmdVersionString = AppConfig.SERVER_IP;
        cmdVersionString = Integer.toHexString(data[1] & MotionEventCompat.ACTION_MASK);
        String cmdIdString = AppConfig.SERVER_IP;
        for (i = 2; i < 10; i++) {
            a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
            if (a.length() == 1) {
                a = "0" + a;
            }
            cmdIdString = new StringBuilder(String.valueOf(cmdIdString)).append(a).toString();
        }
        String cmdDataString = AppConfig.SERVER_IP;
        for (i = 10; i < 26; i++) {
            a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
            if (a.length() == 1) {
                a = "0" + a;
            }
            cmdDataString = new StringBuilder(String.valueOf(cmdDataString)).append(a).toString();
        }
        String deviceStateString = AppConfig.SERVER_IP;
        for (i = 19; i < 21; i++) {
            deviceStateString = new StringBuilder(String.valueOf(deviceStateString)).append(Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK)).toString();
        }
        String hwVersionString = AppConfig.SERVER_IP;
        for (i = 10; i < 11; i++) {
            a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
            if (a.length() == 1) {
                a = "0" + a;
            }
            hwVersionString = new StringBuilder(String.valueOf(hwVersionString)).append(a).toString();
        }
        String cmdCrcString = AppConfig.SERVER_IP;
        for (i = 26; i < 28; i++) {
            a = Integer.toHexString(data[i] & MotionEventCompat.ACTION_MASK);
            if (a.length() == 1) {
                a = "0" + a;
            }
            cmdCrcString = new StringBuilder(String.valueOf(cmdCrcString)).append(a).toString();
        }
        try {
            jsonObject.put("ver", hwVersionString);
            jsonObject.put("head", cmdTypeString);
            jsonObject.put("mac", macString);
            jsonObject.put("id", cmdIdString);
            jsonObject.put("state", deviceStateString);
            jsonObject.put("devicedata", cmdDataString);
            jsonObject.put("check", cmdCrcString);
            return jsonObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jsonObject;
        }
    }
}
