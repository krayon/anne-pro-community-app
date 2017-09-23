package com.obins.anne.utils;

import android.os.Environment;

public class AppConfig {
    public static String APKFILE_PATH = new StringBuilder(String.valueOf(Environment.getExternalStorageDirectory().getPath())).append("/anne/apk").toString();
    public static final String APP_NAME = "obins_kb";
    public static final String ASKMSG_TIMEOUT = "20008";
    public static final int BLE_COMP_OFF = 0;
    public static final int BLE_COMP_ON = 1;
    public static final String DB_ADD_ERROR = "21001";
    public static final String DB_DELETE_ERROR = "21005";
    public static final String DB_FIND_ERROR = "21000";
    public static final String DB_SAVE_ERROR = "21004";
    public static final String DB_SELECT_ERROR = "21003";
    public static final String DEVICE_OFFLINE = "20007";
    public static final String DEVICE_TYPE = "anneProB01";
    public static final String DOWNLOAD_ERRO = "10001";
    public static final String DOWNLOAD_SUCCESS = "10000";
    public static final String FW_UPDATE_ERRO = "10002";
    public static final String FW_UPDATE_GOING = "10001";
    public static final String FW_UPDATE_SUCCESS = "10000";
    public static final String KEYBOARDNAME = "ANNE_KB_L";
    public static final String KEYBOARDNAME_COMP_OFF = "ANNE_KB_L0";
    public static final String KEYBOARDNAME_COMP_ON = "ANNE_KB_L1";
    public static final int MACRO_MAX_NUM = 20;
    public static final String NET_TIMEOUT = "12580";
    public static final String NO_DEVICE_ERROR = "20002";
    public static final String NO_ERROR = "10000";
    public static final String NO_GROUP_ERROR = "20003";
    public static final String NO_USER_ERROR = "20001";
    public static final String OTA_ERROR = "30000";
    public static final String POST_ERROR = "20000";
    public static final String SERVER_IP = "";
    public static final String SIGN_ERROR = "20005";
    public static final String TOKEN_ERROR = "20005";
    public static final String TOKEN_TIMEOUT = "20006";
    public static final String UPDATE_SAVENAME = "obinsanne_update.apk";
}
