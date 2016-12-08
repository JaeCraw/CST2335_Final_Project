package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jae on 2016-12-07.
 */
public class KitchenDataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KitchenDB.db";
    public static final int VERSION_NUMBER = 1;
    public static final String TABLE_NAME = "ApplianceTable";
    public static final String KEY_ID = "ID";
    public static final String KEY_TYPE = "TYPE";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_SETTING = "SETTING";

    public KitchenDataBaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the query
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_TYPE + " TEXT, "
                + KEY_SETTING + " INTEGER)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldver, int newVer){
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldver + " newVersion" + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
}
