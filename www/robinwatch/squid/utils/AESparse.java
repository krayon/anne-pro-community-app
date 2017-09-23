package www.robinwatch.squid.utils;

import android.support.v4.view.MotionEventCompat;
import android.util.Base64;
import com.obins.anne.utils.AppConfig;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESparse {
    private static final String IVKEY = "AESAPPCLIENT_KEY";

    public static String encrypt(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            cipher.init(1, new SecretKeySpec(fullZore(key, blockSize), "AES"), new IvParameterSpec(fullZore(IVKEY, blockSize)));
            return new String(Base64.encode(cipher.doFinal(fullZore(data, blockSize)), 0)).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return AppConfig.SERVER_IP;
        }
    }

    public static String decrypt(String data, String key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            cipher.init(2, new SecretKeySpec(fullZore(key, blockSize), "AES"), new IvParameterSpec(fullZore(IVKEY, blockSize)));
            return new String(cipher.doFinal(Base64.decode(data, 0))).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return AppConfig.SERVER_IP;
        }
    }

    public static byte[] fullZore(String data, int blockSize) {
        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength += blockSize - (plaintextLength % blockSize);
        }
        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
        return plaintext;
    }

    public static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        for (byte b : buf) {
            String hex = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            if (hex.length() == 1) {
                hex = new StringBuilder(String.valueOf('0')).append(hex).toString();
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
