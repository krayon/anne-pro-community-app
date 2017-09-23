package com.obins.anne.db;

import java.io.Serializable;

public class MacroMember implements Serializable {
    public static int MACROMEMBER_OFF = 0;
    public static int MACROMEMBER_ON = 1;
    public static int TRIGGERWAY_DOWN = 0;
    public static String TRIGGERWAY_DOWN_VALUE = "PRESS";
    public static int TRIGGERWAY_UP = 1;
    public static String TRIGGERWAY_UP_VALUE = "UP";
    public int _id;
    public int crcID;
    public int isOn;
    public int keyIndex;
    public int keyValue;
    public String keyWord;
    public String macromemberName;
    public int triggerway;
}
