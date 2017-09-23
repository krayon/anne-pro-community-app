package www.robinwatch.squid.utils;

import android.support.v4.view.MotionEventCompat;
import com.obins.anne.utils.AppConfig;
import java.security.MessageDigest;

public class MD5 {
    public static String getMD5(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] md5Bytes = md5.digest();
        String res = AppConfig.SERVER_IP;
        for (byte b : md5Bytes) {
            int temp = b & MotionEventCompat.ACTION_MASK;
            if (temp <= 15) {
                res = new StringBuilder(String.valueOf(res)).append("0").toString();
            }
            res = new StringBuilder(String.valueOf(res)).append(Integer.toHexString(temp)).toString();
        }
        return res;
    }
}
