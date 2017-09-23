package com.obins.anne.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.obins.anne.utils.AppConfig;
import com.obins.anne.utils.C0188L;
import com.obins.anne.utils.KeyObject;
import com.obins.anne.utils.KeyboardAlignment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DatabaseManager {
    private static String TAG = "DatabaseManager";
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;
    private AtomicInteger mOpenCounter = new AtomicInteger();

    public static synchronized void initializeInstance(SQLiteOpenHelper helper) {
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                instance = new DatabaseManager();
                mDatabaseHelper = helper;
            }
        }
    }

    public static synchronized DatabaseManager getInstance() {
        DatabaseManager databaseManager;
        synchronized (DatabaseManager.class) {
            if (instance == null) {
                throw new IllegalStateException(new StringBuilder(String.valueOf(DatabaseManager.class.getSimpleName())).append(" is not initialized, call initializeInstance(..) method first.").toString());
            }
            databaseManager = instance;
        }
        return databaseManager;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (this.mOpenCounter.incrementAndGet() == 1) {
            try {
                this.mDatabase = mDatabaseHelper.getWritableDatabase();
            } catch (SQLException e) {
                this.mDatabase = mDatabaseHelper.getReadableDatabase();
                e.printStackTrace();
            }
        }
        return this.mDatabase;
    }

    public synchronized void closeDatabase() {
        if (this.mOpenCounter.decrementAndGet() == 0) {
            this.mDatabase.close();
        }
    }

    private void operateSQL(String sql) {
        openDatabase().execSQL(sql);
        closeDatabase();
    }

    public List<MacroMember> findAllMacroMember() {
        List<MacroMember> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from macromember", new String[0]);
        while (cursor.moveToNext()) {
            MacroMember macroMember = new MacroMember();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int isOn = cursor.getInt(cursor.getColumnIndex("isOn"));
            int triggerway = cursor.getInt(cursor.getColumnIndex("triggerway"));
            int crcID = cursor.getInt(cursor.getColumnIndex("crcID"));
            int keyIndex = cursor.getInt(cursor.getColumnIndex("keyIndex"));
            int keyValue = cursor.getInt(cursor.getColumnIndex("keyValue"));
            String keyWord = cursor.getString(cursor.getColumnIndex("keyWord"));
            String macromemberName = cursor.getString(cursor.getColumnIndex("macromemberName"));
            macroMember._id = _id;
            macroMember.crcID = crcID;
            macroMember.keyWord = keyWord;
            macroMember.keyValue = keyValue;
            macroMember.keyIndex = keyIndex;
            macroMember.macromemberName = macromemberName;
            macroMember.isOn = isOn;
            macroMember.triggerway = triggerway;
            listdata.add(macroMember);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteMacroMember(int _id) {
        String sql = AppConfig.SERVER_IP;
        deleteAllMacroMemberValue(_id);
        operateSQL("delete from macromember where _id = " + _id);
    }

    public int addMacroMember(MacroMember macroMember) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into macromember(crcID, keyWord, keyValue, keyIndex, macromemberName, isOn, triggerway) values (" + macroMember.crcID + ", '" + macroMember.keyWord + "', " + macroMember.keyValue + ", " + macroMember.keyIndex + ", '" + macroMember.macromemberName + "', " + macroMember.isOn + ", " + macroMember.triggerway + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) from macromember", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyMacroMember(MacroMember macroMember) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update macromember set crcID = " + macroMember.crcID + ", keyWord = '" + macroMember.keyWord + "', keyValue = " + macroMember.keyValue + ", keyIndex = " + macroMember.keyIndex + ", macromemberName = '" + macroMember.macromemberName + "', isOn = " + macroMember.isOn + ", triggerway = " + macroMember.triggerway + " where _id = " + macroMember._id);
    }

    public void closeAllMacroMember() {
        String str = AppConfig.SERVER_IP;
        operateSQL("update macromember set isOn = 0");
    }

    public List<MacroMemberValue> findAllMacroMemberValue(int membervalue) {
        List<MacroMemberValue> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from macromembervalue where memberId = ?", new String[]{new StringBuilder(String.valueOf(membervalue)).toString()});
        while (cursor.moveToNext()) {
            MacroMemberValue macroMemberValue = new MacroMemberValue();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int memberId = cursor.getInt(cursor.getColumnIndex("memberId"));
            int valueType = cursor.getInt(cursor.getColumnIndex("valueType"));
            int value = cursor.getInt(cursor.getColumnIndex("value"));
            int downTime = cursor.getInt(cursor.getColumnIndex("downTime"));
            int upTime = cursor.getInt(cursor.getColumnIndex("upTime"));
            String valueStr = cursor.getString(cursor.getColumnIndex("valueStr"));
            macroMemberValue._id = _id;
            macroMemberValue.memberId = memberId;
            macroMemberValue.valueType = valueType;
            macroMemberValue.value = value;
            macroMemberValue.valueStr = valueStr;
            macroMemberValue.downTime = downTime;
            macroMemberValue.upTime = upTime;
            listdata.add(macroMemberValue);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteAllMacroMemberValue(int macroMemberId) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("delete from macromembervalue where memberId = " + macroMemberId);
    }

    public void deleteMacroMemberValue(int _id) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("delete from macromembervalue where _id = " + _id);
    }

    public int addMacroMemberValue(MacroMemberValue macroMemberValue) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into macromembervalue(memberId , valueType , valueStr, value, downTime, upTime) values (" + macroMemberValue.memberId + ", " + macroMemberValue.valueType + ", '" + macroMemberValue.valueStr + "', " + macroMemberValue.value + ", " + macroMemberValue.downTime + ", " + macroMemberValue.upTime + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) from macromembervalue", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyMacroMemberValue(MacroMemberValue macroMemberValue) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update macromembervalue set memberId = " + macroMemberValue.memberId + ", valueType = " + macroMemberValue.valueType + ", valueStr = '" + macroMemberValue.valueStr + "', value = " + macroMemberValue.value + ", downTime = " + macroMemberValue.downTime + ", upTime = " + macroMemberValue.upTime + " where _id = " + macroMemberValue._id);
    }

    public List<KeyboardLightEffectDB> findAllKeyboardLightEffectDB() {
        List<KeyboardLightEffectDB> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from keyboardlighteffectdb", new String[0]);
        while (cursor.moveToNext()) {
            KeyboardLightEffectDB keyboardLightEffectDB = new KeyboardLightEffectDB();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            int lightEffectID = cursor.getInt(cursor.getColumnIndex("lightEffectID"));
            String keycolor = cursor.getString(cursor.getColumnIndex("keycolor"));
            keyboardLightEffectDB._id = _id;
            keyboardLightEffectDB.name = name;
            keyboardLightEffectDB.type = type;
            keyboardLightEffectDB.keycolor = keycolor;
            keyboardLightEffectDB.lightEffectID = lightEffectID;
            listdata.add(keyboardLightEffectDB);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteKeyboardLightEffectDB(int _id) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("delete from keyboardlighteffectdb where _id = " + _id);
    }

    public int addKeyboardLightEffectDB(KeyboardLightEffectDB keyboardLightEffectDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into keyboardlighteffectdb(name, type, keycolor, lightEffectID) values ('" + keyboardLightEffectDB.name + "', " + keyboardLightEffectDB.type + ", '" + keyboardLightEffectDB.keycolor + "', " + keyboardLightEffectDB.lightEffectID + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) FROM keyboardlighteffectdb", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
            C0188L.m8i(TAG, "strid = " + strid);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyKeyboardLightEffectDB(KeyboardLightEffectDB keyboardLightEffectDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update keyboardlighteffectdb set name = '" + keyboardLightEffectDB.name + "', type = " + keyboardLightEffectDB.type + ", keycolor = '" + keyboardLightEffectDB.keycolor + "', lightEffectID = " + keyboardLightEffectDB.lightEffectID + " where _id = " + keyboardLightEffectDB._id);
    }

    public List<ColorPanel> findColorPanel() {
        List<ColorPanel> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from colorpanel", new String[0]);
        while (cursor.moveToNext()) {
            ColorPanel colorPanel = new ColorPanel();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String defaultcolor = cursor.getString(cursor.getColumnIndex("defaultcolor"));
            colorPanel._id = _id;
            colorPanel.defaultcolor = defaultcolor;
            listdata.add(colorPanel);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteColorPanel(int _id) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("delete from colorpanel where _id = " + _id);
    }

    public int addColorPanel(ColorPanel colorPanel) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into colorpanel(defaultcolor) values ('" + colorPanel.defaultcolor + "')");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) FROM colorpanel", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
            C0188L.m8i(TAG, "strid = " + strid);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyColorPanel(ColorPanel colorPanel) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update colorpanel set defaultcolor = '" + colorPanel.defaultcolor + "' where _id = " + colorPanel._id);
    }

    public List<KeyboardAlignment> findAllKeyboardAlignment() {
        List<KeyboardAlignment> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from customalignment", new String[0]);
        while (cursor.moveToNext()) {
            KeyboardAlignment keyboardAlignment = new KeyboardAlignment();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int alignmentId = cursor.getInt(cursor.getColumnIndex("alignmentId"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            keyboardAlignment._id = _id;
            keyboardAlignment.name = name;
            keyboardAlignment.content = content;
            keyboardAlignment.alignmentId = alignmentId;
            listdata.add(keyboardAlignment);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteAlignment(int _id) {
        String sql = AppConfig.SERVER_IP;
        deleteAllAlignmentValue(_id);
        operateSQL("delete from customalignment where _id = " + _id);
    }

    public int addAlignment(KeyboardAlignment keyboardAlignment) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into customalignment(name , content, alignmentId) values ('" + keyboardAlignment.name + "', '" + keyboardAlignment.content + "', " + keyboardAlignment.alignmentId + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) from customalignment", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
        }
        cursor.close();
        closeDatabase();
        if (this.mOpenCounter.incrementAndGet() == 1) {
            try {
                this.mDatabase = mDatabaseHelper.getWritableDatabase();
            } catch (SQLException e) {
                this.mDatabase = mDatabaseHelper.getReadableDatabase();
                e.printStackTrace();
            }
        }
        List<KeyObject> standardKey_List = keyboardAlignment.standardKey_List;
        int standardKey_Listlen = standardKey_List.size();
        for (int i = 0; i < standardKey_Listlen; i++) {
            KeyObject keyObject = (KeyObject) standardKey_List.get(i);
            this.mDatabase.execSQL("insert into customalignmentvalue(alignmentId , type , keyStr , keyValue ) values (" + strid + ", " + KeyObject.BASEKEY_AREA + ", '" + keyObject.keyStr + "', " + keyObject.keyValue + ")");
        }
        List<KeyObject> funKey_List = keyboardAlignment.funKey_List;
        int funKey_List_Listlen = funKey_List.size();
        for (int j = 0; j < funKey_List_Listlen; j++) {
            keyObject = (KeyObject) funKey_List.get(j);
            this.mDatabase.execSQL("insert into customalignmentvalue(alignmentId , type , keyStr , keyValue ) values (" + strid + ", " + KeyObject.FUNKEY_AREA + ", '" + keyObject.keyStr + "', " + keyObject.keyValue + ")");
        }
        closeDatabase();
        return strid;
    }

    public void modifyAlignment(KeyboardAlignment keyboardAlignment) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update customalignment set name = '" + keyboardAlignment.name + "', content = '" + keyboardAlignment.content + "', alignmentId = " + keyboardAlignment.alignmentId + " where _id = " + keyboardAlignment._id);
        if (this.mOpenCounter.incrementAndGet() == 1) {
            try {
                this.mDatabase = mDatabaseHelper.getWritableDatabase();
            } catch (SQLException e) {
                this.mDatabase = mDatabaseHelper.getReadableDatabase();
                e.printStackTrace();
            }
        }
        List<KeyObject> standardKey_List = keyboardAlignment.standardKey_List;
        int standardKey_Listlen = standardKey_List.size();
        for (int i = 0; i < standardKey_Listlen; i++) {
            KeyObject keyObject = (KeyObject) standardKey_List.get(i);
            this.mDatabase.execSQL("update customalignmentvalue set alignmentId = " + keyObject.alignmentId + ", type = " + KeyObject.BASEKEY_AREA + ", keyStr = '" + keyObject.keyStr + "', keyValue = " + keyObject.keyValue + " where _id = " + keyObject._id);
        }
        List<KeyObject> funKey_List = keyboardAlignment.funKey_List;
        int funKey_List_Listlen = funKey_List.size();
        for (int j = 0; j < funKey_List_Listlen; j++) {
            keyObject = (KeyObject) funKey_List.get(j);
            this.mDatabase.execSQL("update customalignmentvalue set alignmentId = " + keyObject.alignmentId + ", type = " + KeyObject.FUNKEY_AREA + ", keyStr = '" + keyObject.keyStr + "', keyValue = " + keyObject.keyValue + " where _id = " + keyObject._id);
        }
        closeDatabase();
    }

    public List<KeyObject> findAllAlignmentValue(int alignmentId) {
        List<KeyObject> listdata = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from customalignmentvalue where alignmentId = ?", new String[]{new StringBuilder(String.valueOf(alignmentId)).toString()});
        while (cursor.moveToNext()) {
            KeyObject keyObject = new KeyObject();
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));
            int keyValue = cursor.getInt(cursor.getColumnIndex("keyValue"));
            String keyStr = cursor.getString(cursor.getColumnIndex("keyStr"));
            keyObject._id = _id;
            keyObject.alignmentId = alignmentId;
            keyObject.keyValue = keyValue;
            keyObject.keyStr = keyStr;
            keyObject.type = type;
            listdata.add(keyObject);
        }
        cursor.close();
        closeDatabase();
        return listdata;
    }

    public void deleteAllAlignmentValue(int alignmentId) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("delete from customalignmentvalue where alignmentId = " + alignmentId);
    }

    public List<DeviceDB> findAllDeviceInfo() {
        List<DeviceDB> listDeviceDB = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from deviceinfo", new String[0]);
        while (cursor.moveToNext()) {
            DeviceDB deviceDB = new DeviceDB();
            String deviceId = cursor.getString(cursor.getColumnIndex("deviceId"));
            String macString = cursor.getString(cursor.getColumnIndex("macString"));
            int deviceType = cursor.getInt(cursor.getColumnIndex("deviceType"));
            int deviceModel = cursor.getInt(cursor.getColumnIndex("deviceModel"));
            int mainMcuVersion = cursor.getInt(cursor.getColumnIndex("mainMcuVersion"));
            int kMcuVersion = cursor.getInt(cursor.getColumnIndex("kMcuVersion"));
            int bleVersion = cursor.getInt(cursor.getColumnIndex("bleVersion"));
            deviceDB._id = cursor.getInt(cursor.getColumnIndex("_id"));
            deviceDB.deviceId = deviceId;
            deviceDB.macString = macString;
            deviceDB.deviceType = deviceType;
            deviceDB.deviceModel = deviceModel;
            deviceDB.mainMcuVersion = mainMcuVersion;
            deviceDB.kMcuVersion = kMcuVersion;
            deviceDB.bleVersion = bleVersion;
            listDeviceDB.add(deviceDB);
        }
        cursor.close();
        closeDatabase();
        return listDeviceDB;
    }

    public int addDeviceInfo(DeviceDB deviceDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into deviceinfo(deviceId , macString, deviceType, deviceModel, mainMcuVersion, kMcuVersion, bleVersion) values ('" + deviceDB.deviceId + "', '" + deviceDB.macString + "', " + deviceDB.deviceType + ", " + deviceDB.deviceModel + ", " + deviceDB.mainMcuVersion + "," + deviceDB.kMcuVersion + "," + deviceDB.bleVersion + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) FROM deviceinfo", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
            C0188L.m8i(TAG, "strid = " + strid);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyDeviceInfo(DeviceDB deviceDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update deviceinfo set macString = '" + deviceDB.macString + "', deviceType = " + deviceDB.deviceType + ", deviceModel = " + deviceDB.deviceModel + ", mainMcuVersion = " + deviceDB.mainMcuVersion + ", kMcuVersion = " + deviceDB.kMcuVersion + ", bleVersion = " + deviceDB.bleVersion + ", deviceId = '" + deviceDB.deviceId + "' where _id = " + deviceDB._id);
    }

    public List<DeviceStateDB> findAllDeviceStateInfo() {
        List<DeviceStateDB> listDeviceStateDB = new ArrayList();
        Cursor cursor = openDatabase().rawQuery("select * from devicestateinfo", new String[0]);
        while (cursor.moveToNext()) {
            DeviceStateDB deviceStateDB = new DeviceStateDB();
            String deviceId = cursor.getString(cursor.getColumnIndex("deviceId"));
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            int alignmentId = cursor.getInt(cursor.getColumnIndex("alignmentId"));
            int customAlgnmentId = cursor.getInt(cursor.getColumnIndex("customAlgnmentId"));
            deviceStateDB.deviceId = deviceId;
            deviceStateDB.alignmentId = alignmentId;
            deviceStateDB._id = _id;
            deviceStateDB.customAlgnmentId = customAlgnmentId;
            listDeviceStateDB.add(deviceStateDB);
        }
        cursor.close();
        closeDatabase();
        return listDeviceStateDB;
    }

    public int addDeviceStateInfo(DeviceStateDB deviceStateDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("insert into devicestateinfo(deviceId , alignmentId, customAlgnmentId) values ('" + deviceStateDB.deviceId + "', " + deviceStateDB.alignmentId + ", " + deviceStateDB.customAlgnmentId + ")");
        Cursor cursor = openDatabase().rawQuery("SELECT MAX(_id) FROM devicestateinfo", null);
        int strid = 0;
        if (cursor.moveToFirst()) {
            strid = cursor.getInt(0);
            C0188L.m8i(TAG, "strid = " + strid);
        }
        cursor.close();
        closeDatabase();
        return strid;
    }

    public void modifyDeviceStateInfo(DeviceStateDB deviceStateDB) {
        String sql = AppConfig.SERVER_IP;
        operateSQL("update devicestateinfo set deviceId = '" + deviceStateDB.deviceId + "', alignmentId = " + deviceStateDB.alignmentId + ", customAlgnmentId = " + deviceStateDB.customAlgnmentId + "  where _id = " + deviceStateDB._id);
    }
}
