package com.obins.anne.utils;

import com.obins.anne.db.DatabaseManager;
import java.util.ArrayList;
import java.util.List;

public class KeyboardAlignment {
    public static int CUSTOM_TYPE = 1;
    public static int NORMAL_TYPE = 0;
    public int _id = -1;
    public int alignmentId = 0;
    public String content;
    public List<KeyObject> funKey_List;
    public String name;
    public List<KeyObject> standardKey_List;
    public int type = -1;

    public static int getAlignmentID(KeyboardAlignment keyboardAlignment) {
        int i;
        byte[] alignmentData = new byte[144];
        byte[] alignmentStandData = new byte[61];
        byte[] alignmentFunData = new byte[61];
        byte[] alignmentStandCovData = new byte[70];
        byte[] alignmentFunCovData = new byte[70];
        for (i = 0; i < 61; i++) {
            alignmentStandData[i] = (byte) ((KeyObject) keyboardAlignment.standardKey_List.get(i)).keyValue;
        }
        for (i = 0; i < 61; i++) {
            alignmentFunData[i] = (byte) ((KeyObject) keyboardAlignment.funKey_List.get(i)).keyValue;
        }
        conversionAlignment61To70(alignmentStandData, alignmentStandCovData);
        conversionAlignment61To70(alignmentFunData, alignmentFunCovData);
        for (i = 4; i < 144; i++) {
            if (i < 74) {
                alignmentData[i] = alignmentStandCovData[i - 4];
            } else {
                alignmentData[i] = alignmentFunCovData[(i - 70) - 4];
            }
        }
        return CRC16.calcCrc16(alignmentData);
    }

    public static KeyboardAlignment turnAlignment70To61(List<Integer> dataList) {
        KeyboardAlignment keyboardAlignment = new KeyboardAlignment();
        keyboardAlignment.standardKey_List = new ArrayList();
        keyboardAlignment.funKey_List = new ArrayList();
        for (int i = 0; i < Msgprotocol.BLE_ACK_COMP_MODE; i++) {
            int jumpNum = i;
            KeyObject keyObject;
            if (i >= 70) {
                jumpNum -= 70;
                if (!(jumpNum == 40 || jumpNum == 53 || jumpNum == 54 || jumpNum == 59 || jumpNum == 60 || jumpNum == 62 || jumpNum == 63 || jumpNum == 64 || jumpNum == 65)) {
                    keyObject = new KeyObject();
                    keyObject.keyValue = ((Integer) dataList.get(i)).intValue();
                    keyObject.keyStr = (String) KeyObjectUtil.keyValueAndStringMap.get(dataList.get(i));
                    if (keyObject.keyStr == null || keyObject.keyStr.equals("null")) {
                        keyObject.keyStr = AppConfig.SERVER_IP;
                    }
                    keyboardAlignment.funKey_List.add(keyObject);
                }
            } else if (!(jumpNum == 40 || jumpNum == 53 || jumpNum == 54 || jumpNum == 59 || jumpNum == 60 || jumpNum == 62 || jumpNum == 63 || jumpNum == 64 || jumpNum == 65)) {
                keyObject = new KeyObject();
                keyObject.keyValue = ((Integer) dataList.get(i)).intValue();
                keyObject.keyStr = (String) KeyObjectUtil.keyValueAndStringMap.get(dataList.get(i));
                if (keyObject.keyStr == null || keyObject.keyStr.equals("null")) {
                    keyObject.keyStr = AppConfig.SERVER_IP;
                }
                keyboardAlignment.standardKey_List.add(keyObject);
            }
        }
        return keyboardAlignment;
    }

    public static String getAlignmentNameWithId(DatabaseManager databaseManager, int id) {
        String name = AppConfig.SERVER_IP;
        List<KeyboardAlignment> listdata = databaseManager.findAllKeyboardAlignment();
        int len = listdata.size();
        for (int i = 0; i < len; i++) {
            if (id == ((KeyboardAlignment) listdata.get(i)).alignmentId) {
                return ((KeyboardAlignment) listdata.get(i)).name;
            }
        }
        return name;
    }

    public static void conversionAlignment61To70(byte[] sourceData, byte[] deskData) {
        int i;
        int j = 0;
        for (i = 0; i < 70; i++) {
            deskData[i] = (byte) 0;
        }
        i = 0;
        while (i < 70) {
            if (!(i == 40 || i == 53 || i == 54 || i == 59 || i == 60 || i == 62 || i == 63 || i == 64 || i == 65)) {
                deskData[i] = sourceData[j];
                j++;
            }
            i++;
        }
    }
}
