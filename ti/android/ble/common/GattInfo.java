package ti.android.ble.common;

import android.content.res.XmlResourceParser;
import com.obins.anne.utils.AppConfig;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParserException;

public class GattInfo {
    public static final UUID CC_SERVICE_UUID = UUID.fromString("f000ccc0-0451-4000-b000-000000000000");
    public static final UUID CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    public static final UUID OAD_SERVICE_UUID = UUID.fromString("f000ffc0-0451-4000-b000-000000000000");
    private static Map<String, String> mDescrMap = new HashMap();
    private static Map<String, String> mNameMap = new HashMap();
    private static final String uuidBtSigBase = "0000****-0000-1000-8000-00805f9b34fb";
    private static final String uuidTiBase = "f000****-0451-4000-b000-000000000000";

    public GattInfo(XmlResourceParser xpp) {
        try {
            readUuidData(xpp);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String uuidToName(UUID uuid) {
        return uuidToName(toShortUuidStr(uuid).toUpperCase());
    }

    public static String getDescription(UUID uuid) {
        return (String) mDescrMap.get(toShortUuidStr(uuid).toUpperCase());
    }

    public static boolean isTiUuid(UUID u) {
        return u.toString().replace(toShortUuidStr(u), "****").equals(uuidTiBase);
    }

    public static boolean isBtSigUuid(UUID u) {
        return u.toString().replace(toShortUuidStr(u), "****").equals(uuidBtSigBase);
    }

    public static String uuidToString(UUID u) {
        String uuidStr;
        if (isBtSigUuid(u)) {
            uuidStr = toShortUuidStr(u);
        } else {
            uuidStr = u.toString();
        }
        return uuidStr.toUpperCase();
    }

    private static String toShortUuidStr(UUID u) {
        return u.toString().substring(4, 8);
    }

    private static String uuidToName(String uuidStr16) {
        return (String) mNameMap.get(uuidStr16);
    }

    private void readUuidData(XmlResourceParser xpp) throws XmlPullParserException, IOException {
        xpp.next();
        String tagName = null;
        String uuid = null;
        String descr = null;
        int eventType = xpp.getEventType();
        while (eventType != 1) {
            if (eventType != 0) {
                if (eventType == 2) {
                    tagName = xpp.getName();
                    uuid = xpp.getAttributeValue(null, "uuid");
                    descr = xpp.getAttributeValue(null, "descr");
                } else if (eventType != 3 && eventType == 4 && tagName.equalsIgnoreCase("item") && !uuid.isEmpty()) {
                    uuid = uuid.replace("0x", AppConfig.SERVER_IP);
                    mNameMap.put(uuid, xpp.getText());
                    mDescrMap.put(uuid, descr);
                }
            }
            eventType = xpp.next();
        }
    }
}
