package com.obins.anne.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATANAME = "obins.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATANAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS macromember(_id INTEGER PRIMARY KEY AUTOINCREMENT, crcID INTERGER, keyWord text, keyValue INTERGER, keyIndex INTERGER, macromemberName text, isOn INTERGER, triggerway INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS macromembervalue(_id INTEGER PRIMARY KEY AUTOINCREMENT, memberId INTERGER, valueType INTERGER, valueStr text, value INTERGER, downTime INTERGER, upTime INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS keyboardlighteffectdb(_id INTEGER PRIMARY KEY AUTOINCREMENT, name text, type INTERGER, keycolor text, lightEffectID INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS colorpanel(_id INTEGER PRIMARY KEY AUTOINCREMENT, defaultcolor text);");
        db.execSQL("CREATE TABLE IF NOT EXISTS customalignment(_id INTEGER PRIMARY KEY AUTOINCREMENT, name text, content text, alignmentId INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS customalignmentvalue(_id INTEGER PRIMARY KEY AUTOINCREMENT, alignmentId INTERGER, type INTERGER, keyStr text, keyValue INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS deviceinfo(_id INTEGER PRIMARY KEY AUTOINCREMENT, deviceId text, macString text, deviceType INTERGER, deviceModel INTERGER, mainMcuVersion INTERGER, kMcuVersion INTERGER, bleVersion INTERGER);");
        db.execSQL("CREATE TABLE IF NOT EXISTS devicestateinfo(_id INTEGER PRIMARY KEY AUTOINCREMENT, deviceId text, alignmentId INTERGER, customAlgnmentId INTERGER);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
