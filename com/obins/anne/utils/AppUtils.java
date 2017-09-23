package com.obins.anne.utils;

import android.app.Application;
import android.content.Context;
import com.obins.anne.db.DBHelper;
import com.obins.anne.db.DatabaseManager;
import java.util.List;
import ti.android.ble.common.BluetoothLeService;
import www.robinwatch.squid.Squid;

public class AppUtils extends Application {
    public static final int SELECTEDKEY_TYPE_ALIGNMENT_BASEAREA = 1;
    public static final int SELECTEDKEY_TYPE_ALIGNMENT_FUNAREA = 2;
    public static final int SELECTEDKEY_TYPE_MACRO_ADD_KEY = 8;
    public static final int SELECTEDKEY_TYPE_MACRO_FUNMACRO = 4;
    public static final int SELECTEDKEY_TYPE_MACRO_FUNMACRO_MODIFY = 5;
    public static final int SELECTEDKEY_TYPE_MACRO_KEYDOWN_MODIFY = 6;
    public static final int SELECTEDKEY_TYPE_MACRO_KEYUP_MODIFY = 7;
    public static final int SELECTEDKEY_TYPE_MACRO_MACROKEY = 3;
    public static AppUtils appUtils;
    public static int colorPanelColorData = 0;
    public static int colorPanelColorPostion = 0;
    public static Context context;
    public static DatabaseManager databaseManager;
    public static List<String> deviceMacList;
    public static boolean is_first_or_close_Lighteffect = true;
    public static int jumpToSelectKeyPage_Type = -1;
    public static KeyObject keyObject;
    public static KeyboardAlignment keyboardAlignment;
    public static KeyboardLightEffect keyboardLightEffect;
    public static int lighteffect_brightness_data = -1;
    public static int lighteffect_speed_data = -1;
    public static BluetoothLeService mBluetoothLeService;
    public static boolean macroIsNewFlag = true;
    public static String musicfilepath;
    public static String musicname;
    public static int nowKBCustomAlignmentID;
    public static int nowUseAlignmentID;
    public static int setting_devicelist_finish_flag = 0;
    public static Squid squid;
    public static String textData;
    private DBHelper dbhelper;

    public void onCreate() {
        super.onCreate();
        this.dbhelper = new DBHelper(this);
        DatabaseManager.initializeInstance(this.dbhelper);
        databaseManager = DatabaseManager.getInstance();
        squid = new Squid();
        appUtils = this;
        mBluetoothLeService = BluetoothLeService.getInstance();
    }
}
