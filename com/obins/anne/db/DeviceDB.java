package com.obins.anne.db;

public class DeviceDB {
    public static final int DEVICE_MODEL_ANNE = 1;
    public static final int DEVICE_MODEL_ANNE_PRO = 2;
    public static final int DEVICE_TYPE_KEYBOARD = 1;
    public static final int DEVICE_TYPE_MOUSE = 2;
    public int _id;
    public int bleVersion;
    public String deviceId;
    public int deviceModel;
    public int deviceType;
    public int kMcuVersion;
    public String macString;
    public int mainMcuVersion;
}
