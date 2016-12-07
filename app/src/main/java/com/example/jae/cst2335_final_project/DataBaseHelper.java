package com.example.jae.cst2335_final_project;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by bmohm90 on 2016-10-19.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = ".db";
    private static final int VERSION_NUM = 5;
    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "message";
    public static final String CHAT_TABLE = "chat_table";
    public static final String CREATE_CHAT_TABLE = "CREATE TABLE " + CHAT_TABLE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MESSAGE + " TEXT NOT NULL);";


    private static final String ACTIVITY_NAME = "DatabaseHelper";


    public DataBaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CHAT_TABLE);
        Log.i(ACTIVITY_NAME, "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CHAT_TABLE);
        onCreate(db);
        Log.i(ACTIVITY_NAME, "Calling onUpgrade, oldVertion=" + olderVersion + " newVersion=" + newVersion);
    }

}