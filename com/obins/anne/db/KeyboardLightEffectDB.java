package com.obins.anne.db;

import com.obins.anne.utils.AppConfig;
import java.util.ArrayList;
import java.util.List;

public class KeyboardLightEffectDB {
    private static String SPLITSTR = "@";
    public int _id;
    public String keycolor;
    public int lightEffectID;
    public String name;
    public int type;

    public static List<Integer> keycolorStringToList(String valueStr) {
        List<Integer> dataList = new ArrayList();
        String[] array = valueStr.split(SPLITSTR);
        int len = array.length;
        int i = 0;
        while (i < len) {
            if (!(array[i] == null || array[i].isEmpty())) {
                dataList.add(Integer.valueOf(Integer.valueOf(array[i]).intValue()));
            }
            i++;
        }
        return dataList;
    }

    public static String colorListToString(List<Integer> valueList) {
        int len = valueList.size();
        String reslut = AppConfig.SERVER_IP;
        for (int i = 0; i < len; i++) {
            reslut = new StringBuilder(String.valueOf(reslut)).append(Integer.toString(((Integer) valueList.get(i)).intValue())).append(SPLITSTR).toString();
        }
        return reslut;
    }
}
