package www.robinwatch.squid.utils;

import com.obins.anne.utils.AppConfig;

public class SignData {
    public static String SignData(String duangString, String token, String data) {
        String resultString = AppConfig.SERVER_IP;
        try {
            resultString = MD5.getMD5(new StringBuilder(String.valueOf(duangString)).append(token).append(data).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
