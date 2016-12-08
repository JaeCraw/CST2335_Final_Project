package com.example.jae.cst2335_final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Jacob Crawford
 *
 * database helper to create and retreave the database
 */
public class KitchenDataBaseHelper extends SQLiteOpenHelper {

    /**
     * database name
     */
    public static final String DATABASE_NAME = "KitchenDB.db";

    /**
     * version number
     */
    public static final int VERSION_NUMBER = 1;

    /**
     * table name
     */
    public static final String TABLE_NAME = "ApplianceTable";

    /**
     * primary key
     */
    public static final String KEY_ID = "ID";

    /**
     * key for appliance type
     */
    public static final String KEY_TYPE = "TYPE";

    /**
     * key for appliance name
     */
    public static final String KEY_NAME = "NAME";

    /**
     * key for last used settings
     */
    public static final String KEY_SETTING = "SETTING";

    /**
     * Constructor
     * @param ctx
     */
    public KitchenDataBaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUMBER);
    }

    /**
     * called when the database has not been created yet
     * @param db
     */
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

    /**
     * called when the version number is higher that the current verion of the database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * called when the version nuber is lower than the current database
     * @param db
     * @param oldver
     * @param newVer
     */
    public void onDowngrade(SQLiteDatabase db, int oldver, int newVer){
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldver + " newVersion" + newVer);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
}
