package com.obins.anne.utils;

import java.io.Serializable;

public class KeyObject implements Serializable, Cloneable {
    public static int BASEKEY_AREA = 0;
    public static int FUNKEY_AREA = 1;
    public int _id = -1;
    public int alignmentId;
    public String keyStr;
    public String keyStrComplete;
    public int keyValue;
    public int type;

    public Object clone() {
        KeyObject keyObject = null;
        try {
            return (KeyObject) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return keyObject;
        }
    }
}
