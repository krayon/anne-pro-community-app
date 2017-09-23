package com.obins.anne.db;

import java.io.Serializable;

public class MacroMemberValue implements Serializable {
    public static int MACROVALUE_TYPE_KEY = 0;
    public static int MACROVALUE_TYPE_MEDIA = 1;
    public int _id;
    public int downTime;
    public int memberId;
    public int upTime;
    public int value;
    public String valueStr;
    public int valueType;
}
